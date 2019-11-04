package com.jortw.adventurer

import android.app.UiAutomation
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
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
import com.google.firebase.database.FirebaseDatabase
import com.jortw.adventurer.R.*
import com.jortw.adventurer.R.drawable.ic_sword
import com.jortw.adventurer.Registering.NewAccountActivity
import com.jortw.adventurer.Registering.RegisterActivity
import kotlinx.android.synthetic.main.activity_account.*
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_login.emailtext
import kotlinx.android.synthetic.main.activity_login.passwordtext
import kotlinx.android.synthetic.main.layout_topbar.*
import java.lang.reflect.Array


class AccountActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var database: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_account)
        database = FirebaseDatabase.getInstance()
        auth = FirebaseAuth.getInstance()

        val rolespinner: Spinner = findViewById(id.roleSpinner)
        ArrayAdapter.createFromResource(
            this,
            array.role_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            rolespinner.adapter = adapter
        }

        val imagearraw: IntArray = intArrayOf(ic_sword, drawable.ic_shield)

        val orientationSpinner: Spinner = findViewById(id.orentationspinner)
        ArrayAdapter.createFromResource(
            this,
            array.orientation_text_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            orientationSpinner.adapter = adapter
        }

        backbutton.setOnClickListener(View.OnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        })


        updateinfo.setOnClickListener(View.OnClickListener {
            Snackbar.make(findViewById(id.mainLayout),"Information updated", Snackbar.LENGTH_SHORT).show()

        })
    }
}