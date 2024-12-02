package com.example.calmpulse.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calmpulse.ui.theme.CalmPulseTheme

@Composable
fun ThirdWelcomeScreen(modifier: Modifier = Modifier, onNavigate: () -> Unit){


        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {


            Text(
                text = "Unleash Your Inner Calm",
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 24.dp)
            )
            Button(onClick = { onNavigate() }) {
                Text(text = "Let's Go!")
            }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewThirdWelcomeScreen() {
    ThirdWelcomeScreen(modifier = Modifier, onNavigate = {})
}
