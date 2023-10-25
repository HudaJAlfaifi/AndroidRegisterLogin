package com.example.day2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Intent
import android.widget.Button
import android.widget.Toast

import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button2 = findViewById<Button>(R.id.button2)
        val emailEditText = findViewById<EditText>(R.id.editTextTextEmailAddress)
        val passwordEditText = findViewById<EditText>(R.id.editTextTextPassword)
        val logInButton = findViewById<Button>(R.id.button)

        button2.setOnClickListener {
            val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }

        logInButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()

            if (email.isEmpty() || password.isEmpty()) {
                // Display an error message to the user
                Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                // Check if the user exists in the database
                val userRepository = UserRepository(this)
                val userExists = userRepository.checkUserExists(email, password)

                if (userExists) {
                    // User exists, redirect to Activity3
                    val intent = Intent(this, MainActivity4::class.java)
                    startActivity(intent)
                } else {
                    // User doesn't exist, show a message to sign up
                    Toast.makeText(this, "You don't have an account. Please sign up.", Toast.LENGTH_SHORT).show()
                }
            }
        }



    }

}