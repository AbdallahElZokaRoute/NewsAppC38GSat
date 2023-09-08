package com.route.newsappc38gsat

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.route.newsappc38gsat.ui.theme.NewsAppC38GSatTheme

class SplashActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Handler(Looper.getMainLooper()).postDelayed({
                val intent = Intent(this@SplashActivity, MainActivity::class.java)
                startActivity(intent)
                finish()
            }, 2500)
            NewsAppC38GSatTheme {
                // A surface container using the 'background' color from the theme
                SplashContent()
            }
        }
    }
}

@Composable
fun SplashContent() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .paint(
                painterResource(id = R.drawable.pattern),
                contentScale = ContentScale.FillBounds
            ),

        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo Description",
            modifier = Modifier.fillMaxSize(0.5F)
        )
        Spacer(modifier = Modifier.fillMaxHeight(0.35F))
        Image(
            painter = painterResource(id = R.drawable.signature),
            contentDescription = "App Signature",
            modifier = Modifier
                .padding(8.dp)
                .fillMaxSize(0.5F),
        )


    }

}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SplashPreview() {
    NewsAppC38GSatTheme {
        SplashContent()
    }
}