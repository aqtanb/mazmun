package com.aqtanb.mazmun.app

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aqtanb.mazmun.core.ui.theme.MazmunTheme
import com.aqtanb.mazmun.feature.auth.SignInScreen
import com.aqtanb.mazmun.feature.auth.SignInViewModel
import com.aqtanb.mazmun.feature.profile.ProfileScreen
import org.koin.androidx.viewmodel.ext.android.viewModel as koinViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MazmunTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "sign_in") {
                        composable("sign_in") {
                            val vm: SignInViewModel by koinViewModel<SignInViewModel>()
                            val state by vm.state.collectAsStateWithLifecycle()
                            LaunchedEffect(Unit) {
                                if (vm.getCurrentUser() != null) {
                                    navController.navigate("profile")
                                }
                            }
                            LaunchedEffect(state.isSignInSuccessful) {
                                if (state.isSignInSuccessful) {
                                    Toast.makeText(this@MainActivity, "Sign in successful", Toast.LENGTH_LONG).show()
                                    navController.navigate("profile")
                                    vm.resetState()
                                }
                            }
                            SignInScreen(
                                state = state,
                                onSignInClick = { vm.onSignInClick() }
                            )
                        }
                        composable("profile") {
                            val vm: SignInViewModel by koinViewModel<SignInViewModel>()
                            ProfileScreen(
                                userData = vm.getCurrentUser(),
                                onSignOut = {
                                    vm.onSignOutClick()
                                    Toast.makeText(this@MainActivity, "Signed out", Toast.LENGTH_LONG).show()
                                    navController.popBackStack()
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}