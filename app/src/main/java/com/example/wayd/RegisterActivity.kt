package com.example.wayd

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class RegisterActivity : AppCompatActivity() {

    private lateinit var txtName:EditText
    private lateinit var txtLastName:EditText
    private lateinit var txtEmail:EditText
    private lateinit var txtPassword:EditText
    private lateinit var progressBar: ProgressBar
    private lateinit var dbReference: DatabaseReference
    private lateinit var database: FirebaseDatabase
    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        txtName=findViewById(R.id.txtName)
        txtLastName=findViewById(R.id.txtLastName)
        txtEmail=findViewById(R.id.txtEmail)
        txtPassword=findViewById(R.id.txtPassword)

        progressBar=findViewById(R.id.progressBar3)

        database=FirebaseDatabase.getInstance()
        auth= FirebaseAuth.getInstance()

        dbReference=database.reference.child("User")


    }

    fun register(view: View) {
        createNewAccount()
    }

    private fun createNewAccount (){
        val name:String=txtName.text.toString()
        val lastname:String=txtLastName.toString()
        val email:String=txtName.text.toString()
        val password:String=txtName.text.toString()

        if(!TextUtils.isEmpty(name) &&!TextUtils.isEmpty(lastname) &&!TextUtils.isEmpty(email) &&!TextUtils.isEmpty(password) ){
            progressBar.visibility=View.INVISIBLE
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) {
                   task ->
                    if(task.isSuccessful){

//                        val user:FirebaseUser?=auth.currentUser
//                        verifyEmail(user)
//
//                        val userBD= user?.uid?.let { dbReference.child(it) }
//
//                        if (userBD != null) {
//                            userBD.child("Name").setValue(name)
//                        }
//                        if (userBD != null) {
//                            userBD.child("LastName").setValue(lastname)
//                        }

                        action()
                        Toast.makeText(this,"usuario resgistrado exitosamente",Toast.LENGTH_LONG).show()

                    }
                }


        }
    }

    private fun action (){
        startActivity(Intent(this,LoginActivity::class.java))


    }

    private fun verifyEmail(user: FirebaseUser?){
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this) {
                task ->
                if(task.isComplete) {
                    Toast.makeText(this, "Email enviado", Toast.LENGTH_SHORT).show()
                }else{
                    Toast.makeText(this, "Error al enviar el correo", Toast.LENGTH_SHORT).show()
                }
            }

    }
}