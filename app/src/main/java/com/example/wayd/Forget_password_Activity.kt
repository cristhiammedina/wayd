package com.example.wayd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isInvisible
import com.google.firebase.auth.FirebaseAuth

class Forget_password_Activity : AppCompatActivity() {

    private lateinit var txtEmail: EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_password)

        txtEmail=findViewById(R.id.txtEmail)
        progressBar=findViewById(R.id.progressBar)

        auth= FirebaseAuth.getInstance()



    }

    fun send(view: View){
        val email=txtEmail.text.toString()

        if(!TextUtils.isEmpty(email))
            auth.sendPasswordResetEmail(email)
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        progressBar.visibility=view.visibility
                    }
                        startActivity(Intent(this,ProfileActivity::class.java))
                    }else{
                        Toast.makeText(this, "Error al enviar el email", Toast.LENGTH_SHORT).show()





                }

    }

}