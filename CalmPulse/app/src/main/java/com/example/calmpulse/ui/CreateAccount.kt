package com.example.calmpulse.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.calmpulse.R
import com.google.firebase.auth.FirebaseAuth

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateAccount(navController: NavController) {
    // State variables for username, email, and password
    val username = remember { mutableStateOf("") }
    val email = remember { mutableStateOf("") }
    val password = remember { mutableStateOf("") }
    val isUsernameValid = remember { mutableStateOf(true) }
    val isEmailValid = remember { mutableStateOf(true) }
    val isPasswordValid = remember { mutableStateOf(true) }
    var showErrorMessage by remember { mutableStateOf(false) }

    val context = LocalContext.current
    val firebaseAuth = FirebaseAuth.getInstance()
    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold(
        containerColor = Color(0xFFF5F5F5) // Set background color for the entire screen
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .background(Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .verticalScroll(rememberScrollState()) // Enable vertical scrolling
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                // Logo
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = "Logo",
                    modifier = Modifier
                        .padding(bottom = 14.dp)
                        .size(80.dp)
                )

                // Title
                Text(
                    text = "Calm Pulse",
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF5E503F), // Brown color
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                // Subtitle
                Text(
                    text = "Hey, Enter your details to enjoy this beautiful app",
                    fontSize = 14.sp,
                    color = Color(0xFFBBC0CC),
                    modifier = Modifier.padding(bottom = 24.dp)
                )

                // Username Input Field
                TextField(
                    value = username.value,
                    onValueChange = {
                        username.value = it
                        isUsernameValid.value = it.isNotEmpty()
                    },
                    placeholder = { Text("Username", color = Color(0xFFBBC0CC)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.AccountCircle,
                            contentDescription = "Username Icon",
                            tint = Color(0xFFBBC0CC)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp)),
                    isError = !isUsernameValid.value,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black, // Input text color
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = if (!isUsernameValid.value) Color.Red else Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black // Cursor color
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = { keyboardController?.hide() })
                )
                if (!isUsernameValid.value) {
                    Text(
                        text = "Username cannot be empty",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                // Email Input Field
                TextField(
                    value = email.value,
                    onValueChange = {
                        email.value = it
                        isEmailValid.value = android.util.Patterns.EMAIL_ADDRESS.matcher(it).matches()
                    },
                    placeholder = { Text("Email", color = Color(0xFFBBC0CC)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email Icon",
                            tint = Color(0xFFBBC0CC)
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp)),
                    isError = !isEmailValid.value,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black, // Input text color
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = if (!isEmailValid.value) Color.Red else Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black // Cursor color
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Next),
                    keyboardActions = KeyboardActions(onNext = { keyboardController?.hide() })
                )
                if (!isEmailValid.value) {
                    Text(
                        text = "Invalid email format",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                // Password Input Field
                TextField(
                    value = password.value,
                    onValueChange = {
                        password.value = it
                        isPasswordValid.value = it.length >= 6
                    },
                    placeholder = { Text("Password", color = Color(0xFFBBC0CC)) },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password Icon",
                            tint = Color(0xFFBBC0CC)
                        )
                    },
                    visualTransformation = PasswordVisualTransformation(),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .background(Color(0xFFF5F5F5), shape = RoundedCornerShape(12.dp)),
                    isError = !isPasswordValid.value,
                    colors = TextFieldDefaults.textFieldColors(
                        focusedTextColor = Color.Black, // Input text color
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = if (!isPasswordValid.value) Color.Red else Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent,
                        cursorColor = Color.Black // Cursor color
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
                    keyboardActions = KeyboardActions(onDone = { keyboardController?.hide() })
                )
                if (!isPasswordValid.value) {
                    Text(
                        text = "Password must be at least 6 characters",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 4.dp)
                    )
                }

                // Validation Error Message
                if (showErrorMessage) {
                    Text(
                        text = "Please correct the errors above",
                        color = Color.Red,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                // Create Account Button
                Button(
                    onClick = {
                        isUsernameValid.value = username.value.isNotEmpty()
                        isEmailValid.value = android.util.Patterns.EMAIL_ADDRESS.matcher(email.value).matches()
                        isPasswordValid.value = password.value.length >= 6

                        if (isUsernameValid.value && isEmailValid.value && isPasswordValid.value) {
                            firebaseAuth.createUserWithEmailAndPassword(
                                email.value.trim(),
                                password.value.trim()
                            ).addOnCompleteListener { task ->
                                if (task.isSuccessful) {
                                    Toast.makeText(context, "Account created successfully!", Toast.LENGTH_SHORT).show()
                                    navController.navigate("SelectBreathingExercise")
                                } else {
                                    Toast.makeText(context, "Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                                }
                            }
                        } else {
                            showErrorMessage = true
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 8.dp)
                        .height(48.dp),
                    shape = RoundedCornerShape(24.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD3FFA3)) // Light green
                ) {
                    Text(
                        text = "Create Account",
                        fontWeight = FontWeight.Bold,
                        fontSize = 16.sp,
                        color = Color.Black
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}
