package com.example.wayd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class LoginActivity : AppCompatActivity() {

    private lateinit var txtUser: EditText
    private lateinit var txtPassword: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        txtUser=findViewById(R.id.txtUser)
        txtPassword=findViewById(R.id.txtPassword)
        progressBar=findViewById(R.id.progressBar)
        auth= FirebaseAuth.getInstance()
    }

    fun forgotPasword(view: View) {
        startActivity(Intent(this,Forget_password_Activity::class.java))

    }
    fun register(view: View) {
        startActivity(Intent(this,RegisterActivity::class.java))

    }
    fun login(view: View) {
        loginUser()

    }

    private fun loginUser(){
        val user:String=txtUser.text.toString()
        val password:String=txtPassword.text.toString()

        if(!TextUtils.isEmpty(user) && !TextUtils.isEmpty(password)){
            progressBar.visibility=View.INVISIBLE
            auth.signInWithEmailAndPassword(user,password)
                .addOnCompleteListener(this){
                    task->

                    if (task.isSuccessful) {
                        action()
                    }else{
                        Toast.makeText(this, "Error al enviar el autenticacion", Toast.LENGTH_SHORT).show()


                    }
                }


        }
    }
    private fun action(){
        startActivity(Intent(this,ProfileActivity::class.java))

    }
}