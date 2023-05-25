package com.sheoran.telephonykotlin

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.telephony.SmsManager
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

@Suppress("DEPRECATION")
class Message : AppCompatActivity() {
    lateinit var  button: Button
    lateinit var editnumber:EditText
    lateinit var editmsg:EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_message)

        button = findViewById(R.id.idBtnSendMessage)
        editnumber = findViewById(R.id.idEdtPhone)
        editmsg = findViewById(R.id.idEdtMessage)
        button.setOnClickListener {
            sendmassage()
        }

    }

     fun sendmassage() {
        val permissionCheck= ContextCompat.checkSelfPermission(this,Manifest.permission.SEND_SMS)
         if (permissionCheck==PackageManager.PERMISSION_GRANTED){
             mymsg()
         }
         else{
             ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.SEND_SMS),
                 101)
         }
    }

    private fun mymsg() {
     val num: String=  editnumber.text.toString().trim()
        val msg :String = editmsg.text.toString().trim()
        if (num==""||msg==""){
            Toast.makeText(this,"Field cannot be empty",Toast.LENGTH_LONG).show()
        }else{
            if (TextUtils.isDigitsOnly(num)){
                val smsManager:SmsManager=SmsManager.getDefault()
                smsManager.sendTextMessage(num,null,msg,null,null)
                Toast.makeText(this,"Message sent",Toast.LENGTH_LONG).show()
            }
            else{
                Toast.makeText(this,"Please enter correct number",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode==101){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                mymsg()
            }
            else{
                Toast.makeText(this,"Permission Required",Toast.LENGTH_LONG).show()
            }
        }
    }
}