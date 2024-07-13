package com.karim.medicine.ui.mainscreen

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.karim.medicine.ui.mainscreen.component.MedicineApp
import com.karim.medicine.ui.theme.MedicineTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainScreenActivity : ComponentActivity() {
    private val sharedViewModel by viewModels<SharedViewModel>()

    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MedicineTheme {
                MedicineApp(Modifier.fillMaxSize(),sharedViewModel = sharedViewModel)
            }
        }
    }
}
