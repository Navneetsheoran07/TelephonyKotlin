package com.sheoran.telephonykotlin

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.widget.ListView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
@SuppressLint("Range")
class Contacts : AppCompatActivity() {
    var listView:ListView?= null

    var custumAdapter:CustumAdapter?=null
    var modelclassArrayList:ArrayList<ModelClass>?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        listView = findViewById(R.id.listView)

        modelclassArrayList = ArrayList()
        if (ContextCompat.checkSelfPermission(
                this,Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED
        ){
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.READ_CONTACTS),123
            )
        }
        else{

            contactFatch()
        }
    }


    private fun contactFatch() {

        val phone = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
        null,null,null,ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" ASC")

        while (phone!!.moveToNext()){
            val name = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))

            val phonenumber = phone.getString(phone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))


            val modelClass = ModelClass()
            modelClass.setNames(name)
            modelClass.setNumbers(phonenumber)
            modelclassArrayList!!.add(modelClass)
        }
        phone.close()

        custumAdapter= CustumAdapter(this,modelclassArrayList!!)
        listView!!.adapter= custumAdapter
    }
}