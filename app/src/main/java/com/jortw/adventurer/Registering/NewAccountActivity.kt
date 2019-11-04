package com.jortw.adventurer.Registering

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.jortw.adventurer.R
import kotlinx.android.synthetic.main.activity_new_account.*
import android.R.attr.start
import android.animation.ArgbEvaluator
import android.animation.ObjectAnimator
import android.content.Intent
import android.graphics.Color
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jortw.adventurer.MainActivity



class NewAccountActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_account)

        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference()
        val mAuth: FirebaseAuth

        var name: String
        var role: String
        var age: String
        var userid: String

        playerText.setOnClickListener(View.OnClickListener {

            ObjectAnimator.ofObject(
                playerText, "textColor",
                ArgbEvaluator(),
                Color.GRAY, Color.GREEN
            ).setDuration(1000).start()
            if (masterText.currentTextColor == Color.GREEN){
                ObjectAnimator.ofObject(
                    masterText, "textColor",
                    ArgbEvaluator(),
                    Color.GREEN, Color.GRAY
                ).setDuration(1000).start()
            }
        })

        masterText.setOnClickListener(View.OnClickListener {
            ObjectAnimator.ofObject(
                masterText, "textColor",
                ArgbEvaluator(),
                Color.GRAY, Color.GREEN
            ).setDuration(1000).start()

            if (playerText.currentTextColor == Color.GREEN){
                ObjectAnimator.ofObject(
                    playerText, "textColor",
                    ArgbEvaluator(),
                    Color.GREEN, Color.GRAY
                ).setDuration(1000).start()
            }
        })
        registerbutton.setOnClickListener(View.OnClickListener {

            if(nameText.text.toString() == "" || ageText.text.toString() == ""){
                Snackbar.make(findViewById(R.id.relLayout1), "Please fill all the forms", Snackbar.LENGTH_SHORT).show()
            }

            name = nameText.text.toString()
            if(playerText.currentTextColor == Color.GREEN){
                role = "Player"
            }else{
                role = "Dungeon Master"
            }
            age = ageText.text.toString()

            userid = FirebaseAuth.getInstance().currentUser?.uid.toString()

            myRef.child("Users").child(userid).child("Name").setValue(name)
            myRef.child("Users").child(userid).child("Age").setValue(age)
            myRef.child("Users").child(userid).child("Role").setValue(role)

            Log.d("NewAccount", "Succesfully signed in as: " + name)
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)




        })

    }
}