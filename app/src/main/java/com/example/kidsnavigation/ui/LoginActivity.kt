package com.example.kidsnavigation.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.kidsnavigation.MainActivity
import com.example.kidsnavigation.databinding.ActivityLoginBinding
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var auth: FirebaseAuth
    private var user: FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        auth = Firebase.auth

        binding.btnChild.setOnClickListener {
            binding.etUserName.visibility = View.VISIBLE
            binding.etPwd.visibility = View.INVISIBLE
            binding.btnLogin.visibility = View.VISIBLE
        }

        binding.btnParent.setOnClickListener {
            binding.etUserName.visibility = View.VISIBLE
            binding.etPwd.visibility = View.VISIBLE
            binding.btnLogin.visibility = View.VISIBLE
        }

        binding.btnLogin.setOnClickListener {
            if (binding.etUserName.text.isNotBlank()) {
                val userName = binding.etUserName.text.toString()
                auth.createUserWithEmailAndPassword(if(userName.contains("@"))userName else "$userName@da.de",if(binding.etPwd.isVisible) binding.etPwd.text.toString().trim() else "123456")
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            user = auth.currentUser
                            user!!.sendEmailVerification()?.addOnCompleteListener {
                                Toast.makeText(this, "Mail versendet", Toast.LENGTH_SHORT).show()
                            }

                            startActivity(Intent(this, MainActivity::class.java))


                        } else {
                            auth.signInWithEmailAndPassword(if(userName.contains("@"))userName else "$userName@da.de", if(binding.etPwd.isVisible) binding.etPwd.text.toString().trim() else "123456")
                                .addOnCompleteListener { task ->
                                    if (task.isSuccessful) {
                                        Log.d("TAG", "onCreate: ${auth.currentUser?.email}")
                                        startActivity(Intent(this, MainActivity::class.java))
                                    }
                                }

                        }
                    }
            }

        }

    }
}