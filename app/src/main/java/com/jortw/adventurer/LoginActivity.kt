package com.jortw.adventurer

import android.app.UiAutomation
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.jortw.adventurer.Registering.NewAccountActivity
import com.jortw.adventurer.Registering.RegisterActivity
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.emailtext
import kotlinx.android.synthetic.main.activity_login.passwordtext


class LoginActivity : AppCompatActivity() {

    private lateinit var mGoogleSignInClient: GoogleSignInClient
    lateinit var gso: GoogleSignInOptions
    val RC_SIGN_IN: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(getString(R.string.default_web_client_id))
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso)

        val register = findViewById<TextView>(R.id.buttontoregister)



        loginbutton.setOnClickListener{

            val email = emailtext.text.toString()
            val password = passwordtext.text.toString()

            if (email == "" || password == "")
                Snackbar.make(findViewById(R.id.relLayout1), "Please fill all the forms", Snackbar.LENGTH_SHORT).show()
            else
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener{
                        if (!it.isSuccessful)  {Snackbar.make(findViewById(R.id.relLayout1),"Incorrect username or password", Snackbar.LENGTH_SHORT).show(); return@addOnCompleteListener}

                        Log.d("Main", "Succesfully signed in as: " + email)
                        val intent = Intent(this, MainActivity::class.java)
                        startActivity(intent)
                    }


        }

        buttontoregister.setOnClickListener {

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
        googlesignin.setOnClickListener(View.OnClickListener {
            view: View? ->  SignInGoogle()
        })


    }

    private fun SignInGoogle(){
        val signInIntent: Intent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, RC_SIGN_IN)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == RC_SIGN_IN){
            val task: Task<GoogleSignInAccount> = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleResult(task)
        }
    }



    private fun handleResult(completedTask: Task<GoogleSignInAccount>){
        try {
            val account: GoogleSignInAccount? = completedTask.getResult(ApiException::class.java)
            val intent = Intent(this, NewAccountActivity::class.java)
            startActivity(intent)

        }catch (e: ApiException){
            Snackbar.make(findViewById(R.id.relLayout1), e.toString(), Snackbar.LENGTH_SHORT).show()
        }

    }
}