package com.jortw.adventurer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.signin.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthCredential
import com.google.firebase.auth.GoogleAuthProvider
import com.jortw.adventurer.ui.main.SectionsPagerAdapter
import de.hdodenhof.circleimageview.CircleImageView
import kotlinx.android.synthetic.main.layout_topbar.*

class MainActivity : AppCompatActivity() {


    private lateinit var auth: FirebaseAuth
    lateinit var signInOptions: GoogleSignInOptions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val sectionsPagerAdapter = SectionsPagerAdapter(this, supportFragmentManager)
        val viewPager: ViewPager = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = this.findViewById(R.id.tabs)
        tabs.setupWithViewPager(viewPager)
        auth = FirebaseAuth.getInstance()

        val currentUser = auth.currentUser
        if (currentUser == null){
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }


        profilepicview.setOnClickListener(View.OnClickListener {
            Snackbar.make(findViewById(R.id.view_pager),"Navigating to account activity", Snackbar.LENGTH_SHORT).show()
            val intent = Intent(this, AccountActivity::class.java)
            startActivity(intent)
        })


        Snackbar.make(findViewById(R.id.view_pager),"Login successful", Snackbar.LENGTH_SHORT).show()
    }
}