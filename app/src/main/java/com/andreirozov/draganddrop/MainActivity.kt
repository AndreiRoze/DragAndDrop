package com.andreirozov.draganddrop

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.andreirozov.draganddrop.ui.ContentScreen
import com.andreirozov.draganddrop.ui.theme.DragAndDropTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DragAndDropApp()
        }
    }
}

@Composable
private fun DragAndDropApp() {
    DragAndDropTheme {
        ContentScreen()
    }
}