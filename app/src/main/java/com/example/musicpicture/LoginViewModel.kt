package com.example.musicpicture

import android.content.Intent
import android.text.TextUtils
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class LoginViewModel : ViewModel() {


    private val TAG = "FirebaseEmailPassword"
    private var mAuth: FirebaseAuth? = null
    private var user : FirebaseUser? =null

    fun connected() {
        user = mAuth!!.currentUser
    }

    fun initializeFB() {
        mAuth = FirebaseAuth.getInstance()
    }
    fun createAccount(
        activity: ActivityLogin,
        email: String,
        password: String
    ) {

        Log.e(TAG, "createAccount:$email")
        if (!validateForm(activity,email, password)) {
            return
        }

        mAuth!!.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Log.e(TAG, "createAccount: Success!")

                    // update UI with the signed-in user's information
                    val user = mAuth!!.currentUser
                    sendEmailVerification(activity)
                } else {
                    Log.e(TAG, "createAccount: Fail!", task.exception)
                    Toast.makeText(activity, "Authentication failed!", Toast.LENGTH_SHORT).show()
                }
            }

    }

    fun signIn(
        activity: ActivityLogin,
        email: String,
        password: String
    ) {
        Log.e(this.toString(), "signIn:$email")
        if (!validateForm(activity,email, password)) {
            return
        }

        mAuth!!.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(activity) { task ->
                if (task.isSuccessful) {
                    Log.e(this.toString(), "signIn: Success!")
                    Toast.makeText(activity, "Authentication succeed!", Toast.LENGTH_SHORT).show()
                    val monIntent =  Intent(activity,ActivityNavigation::class.java)
                    activity.startActivity(monIntent)
                    // update UI with the signed-in user's information
                    val user = mAuth!!.getCurrentUser()
                } else {
                    Log.e(this.toString(), "signIn: Fail!", task.exception)
                    Toast.makeText(activity, "Authentication failed!", Toast.LENGTH_SHORT).show()
                }
            }

    }
    private fun sendEmailVerification(activity: ActivityLogin) {

        val user = mAuth!!.currentUser
        user!!.sendEmailVerification()
            .addOnCompleteListener(ActivityLogin()) { task ->

                if (task.isSuccessful) {
                    Toast.makeText(activity, "Verification email sent to " + user.email!!, Toast.LENGTH_SHORT).show()
                } else {
                    Log.e(TAG, "sendEmailVerification failed!", task.exception)
                    Toast.makeText(activity, "Failed to send verification email.", Toast.LENGTH_SHORT).show()
                }
            }

    }

    private fun validateForm(activity: ActivityLogin, email: String, password: String): Boolean {

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(activity, "Enter email address!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(activity, "Enter password!", Toast.LENGTH_SHORT).show()
            return false
        }
        if (password.length < 6) {
            Toast.makeText(activity, "Password too short, enter minimum 6 characters!", Toast.LENGTH_SHORT).show()
            return false
        }

        return true
    }


}