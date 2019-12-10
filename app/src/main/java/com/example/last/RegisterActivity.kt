package com.example.last

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_signup.*

class RegisterActivity : AppCompatActivity() {

    private var inputEmail : EditText? = null
    private var inputPass : EditText? = null
    private var btnRegisterActivity : Button? = null

    private var auth : FirebaseAuth? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()

        btnRegisterActivity =  findViewById(R.id.register) as Button
        inputEmail = findViewById(R.id.signup_mail) as EditText
        inputPass = findViewById(R.id.signup_pass) as EditText

        register!!.setOnClickListener(View.OnClickListener {
            val email = inputEmail!!.text.toString().trim()
            val password = inputPass!!.text.toString().trim()

            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter your email Address!", Toast.LENGTH_LONG)
                    .show()
                return@OnClickListener
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(applicationContext, "Enter your Password", Toast.LENGTH_LONG).show()
                return@OnClickListener
            }
            if (password.length < 6) {
                Toast.makeText(
                    applicationContext,
                    "Password too short, enter mimimum 6 characters",
                    Toast.LENGTH_LONG
                ).show()
                return@OnClickListener
            }

            auth!!.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    Toast.makeText(
                        this@RegisterActivity,
                        "Sign Up Success" + task.isSuccessful,
                        Toast.LENGTH_SHORT
                    ).show()
                    if (!task.isSuccessful) {
                        Toast.makeText(
                            this@RegisterActivity,
                            "User Not Created",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@OnCompleteListener
                    } else {
                        startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                        finish()
                    }
                })
        })
    }
}
