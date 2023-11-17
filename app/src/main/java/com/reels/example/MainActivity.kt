package com.reels.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.reels.example.presentation.ReelsScreen
import com.reels.example.ui.theme.ReelsExampleTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReelsExampleTheme {

                ReelsScreen()

            }
        }
    }
}
