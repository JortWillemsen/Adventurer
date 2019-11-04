package com.jortw.adventurer.Registering

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.jortw.adventurer.MainActivity
import com.jortw.adventurer.R
import kotlinx.android.synthetic.main.activity_login.emailtext
import kotlinx.android.synthetic.main.activity_login.passwordtext
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)


        registerbutton.setOnClickListener{

            val email = emailtext.text.toString()
            val password = passwordtext.text.toString()
            val confirmpassword = confirmpasswordtext.text.toString()


            if (confirmpassword == "" || email == "" || password == ""){
                Snackbar.make(findViewById(R.id.relLayout1), "Please fill all the forms", Snackbar.LENGTH_SHORT).show()
            }
            else if(password == confirmpassword){
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener{
                        if (!it.isSuccessful) {Snackbar.make(findViewById(R.id.relLayout1), "User already exists", Snackbar.LENGTH_SHORT).show() ; return@addOnCompleteListener}

                        //Succesfully signed in
                        Log.d("Main", "Succesfully registerd in as: " + email)
                        val intent = Intent(this, NewAccountActivity::class.java)
                        startActivity(intent)
                    }
            }else{
                Snackbar.make(findViewById(R.id.relLayout1), "Passwords do not match", Snackbar.LENGTH_SHORT).show()
            }


        }
    }

}