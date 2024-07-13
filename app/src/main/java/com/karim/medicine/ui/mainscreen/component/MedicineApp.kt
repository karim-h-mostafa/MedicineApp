package com.karim.medicine.ui.mainscreen.component

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.karim.core.util.common.ErrorManager
import com.karim.medicine.ui.greetingscreen.GreetingScreen
import com.karim.medicine.ui.loginscreen.LoginScreen
import com.karim.medicine.ui.mainscreen.SharedViewModel
import com.karim.medicine.ui.medicinedetailsscreen.MedicineDetailsScreen
import kotlinx.serialization.Serializable

@Composable
fun MedicineApp(modifier: Modifier = Modifier, sharedViewModel: SharedViewModel) {
    val navController = rememberNavController()
    val errorState by ErrorManager.error.collectAsStateWithLifecycle()
    errorState?.let {
        ErrorDialog(message = it) {
            ErrorManager.clearError()
        }
    }
    Scaffold(
        modifier = modifier,
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Login,
            modifier = modifier.padding(innerPadding)
        ) {
            composable<Screen.Login> {
                LoginScreen(modifier) { userName ->
                    navController.navigate(Screen.Greeting(userName)) {
                        popUpTo(Screen.Login) {
                            inclusive = true
                        }
                    }
                }
            }
            composable<Screen.Greeting> {
                val arg = it.toRoute<Screen.Greeting>()
                GreetingScreen(modifier, arg.userName) { medicine ->
                    sharedViewModel.setSelectedMedicine(medicine)
                    navController.navigate(Screen.MedicineDetails)
                }
            }
            composable<Screen.MedicineDetails> {
                MedicineDetailsScreen(modifier, sharedViewModel)
            }
        }
    }


}


@Serializable
sealed class Screen() {
    @Serializable
    data object Login : Screen()

    @Serializable
    data class Greeting(val userName: String) : Screen()

    @Serializable
    data object MedicineDetails : Screen()
}