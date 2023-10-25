package com.example.day2
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import android.widget.Button
import android.widget.EditText
import android.util.Log


class MainActivity2 : AppCompatActivity() {
    private lateinit var emailEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var userTypeSpinner: Spinner
    private lateinit var signUpButton: Button

    private val userRepository by lazy { UserRepository(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        emailEditText = findViewById(R.id.editTextTextEmailAddress)
        passwordEditText = findViewById(R.id.editTextTextPassword)
        userTypeSpinner = findViewById(R.id.spinner)
        signUpButton = findViewById(R.id.button)

        val userTypes = arrayOf("Student", "Teacher", "Mother")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, userTypes)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        userTypeSpinner.adapter = adapter

        signUpButton.setOnClickListener {
            val email = emailEditText.text.toString()
            val password = passwordEditText.text.toString()
            val userType = userTypeSpinner.selectedItem.toString()
            if (email.isEmpty() || password.isEmpty()) {
                // Display an error message to the user
                Toast.makeText(this, "Email and password cannot be empty", Toast.LENGTH_SHORT).show()
            } else {
                if (userRepository.insertUser(email, password, userType)) {
                    Log.d(email, "User inserted successfully")
                    // You can display a success message or navigate to another activity
                }
            }
        }
        val button2 = findViewById<Button>(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }}






