package br.unifor.android_estoque_manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.unifor.android_estoque_manager.ui.screen.LoginScreen
import br.unifor.android_estoque_manager.ui.screen.ManagerScreen
import br.unifor.android_estoque_manager.ui.theme.AndroidEstoqueManagerTheme

const val LOGIN = "login"
const val MANAGER = "manager"



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AndroidEstoqueManagerTheme {
                AppNavigation()
            }
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = LOGIN
    ) {
        composable(LOGIN) {
            LoginScreen(
                onLoginSuccess = {

                    navController.navigate(MANAGER)
                }
            )
        }

        composable(MANAGER) {
            ManagerScreen()

        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppNavigationPreview() {
    AndroidEstoqueManagerTheme {
        AppNavigation()
    }
}