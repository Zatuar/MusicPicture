package com.example.musicpicture

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject


class ActivityLogin : AppCompatActivity() {
    private val loginViewModel: LoginViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loginViewModel.initializeFB()
        login.setOnClickListener {
            loginViewModel.signIn(this,useremail.text.toString(),password.text.toString())
        }
        register.setOnClickListener {
            loginViewModel.createAccount(this,useremail.text.toString(),password.text.toString())
        }
    }
    override fun onStart() {
        super.onStart()
        loginViewModel.connected()
    }
}