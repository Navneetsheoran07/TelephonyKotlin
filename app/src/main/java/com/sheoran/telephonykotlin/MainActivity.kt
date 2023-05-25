package com.sheoran.telephonykotlin

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    lateinit var button: Button
    lateinit var  editText: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById(R.id.editTextPhone)
        button = findViewById(R.id.button)

        button.setOnClickListener {

            makeCall()
        }
    }

    private fun makeCall() {
       val number :String = editText.text.toString()
        if (number.isNotEmpty()){
            if (ContextCompat.checkSelfPermission(this,Manifest.permission.CALL_PHONE)!=PackageManager
                    .PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CALL_PHONE),234)
            }
            else{
                val dail = "tel:$number"
                startActivity(Intent(Intent.ACTION_CALL, Uri.parse(dail)))
            }

        }
        else{
            Toast.makeText(this,"Enter phone number",Toast.LENGTH_LONG).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode==234){
            if (grantResults.isNotEmpty()&&grantResults[0]==PackageManager.PERMISSION_GRANTED){
                makeCall()
            }
            else{
                Toast.makeText(this,"Permisson Denied",Toast.LENGTH_LONG).show()
            }
        }
    }
}