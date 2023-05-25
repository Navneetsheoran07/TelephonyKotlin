package com.sheoran.telephonykotlin

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

class Email : AppCompatActivity() {
    lateinit var emailEditText: EditText
    lateinit var emailEditSubject: EditText
    lateinit var emailEditMessage: EditText
    lateinit var recipientMail: String
    lateinit var emailSubject: String
    lateinit var emailMessage: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email)
        emailEditText = findViewById(R.id.edit_mail_address)
        emailEditSubject = findViewById(R.id.edit_mail_subject)
        emailEditMessage = findViewById(R.id.edit_mail_message)
    }
    fun sendEmail(view: View){

        recipientMail = emailEditText.text.toString().trim()
        emailSubject= emailEditSubject.text.toString().trim()
        emailMessage = emailEditMessage.text.toString().trim()

        val intent = Intent(Intent.ACTION_SEND)
        intent.data= Uri.parse("mailto:$recipientMail")
intent.putExtra(Intent.EXTRA_EMAIL,recipientMail)
        intent.putExtra(Intent.EXTRA_SUBJECT,emailSubject)
        intent.putExtra(Intent.EXTRA_TEXT,emailMessage)

        intent.type = "text/plain"
        startActivity(Intent.createChooser(intent,"Select your Email App"))

    }
}