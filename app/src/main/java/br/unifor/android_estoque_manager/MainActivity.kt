package br.unifor.android_estoque_manager

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import br.unifor.android_estoque_manager.model.itemModel
import br.unifor.android_estoque_manager.ui.screen.LoginScreen
import br.unifor.android_estoque_manager.ui.screen.ManagerScreen
import br.unifor.android_estoque_manager.ui.theme.AndroidEstoqueManagerTheme
import br.unifor.android_estoque_manager.viewmodel.EstoqueViewModel


const val LOGIN = "login"
const val MANAGER = "manager"

class MainActivity : ComponentActivity() {
    private lateinit var viewModel: EstoqueViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = ViewModelProvider(this)[EstoqueViewModel::class.java]

        setContent {
            AndroidEstoqueManagerTheme {
                AppNavigation(viewModel = viewModel)
            }
        }
    }
}

@Composable
fun AppNavigation(viewModel: EstoqueViewModel) {
    val navController = rememberNavController()
    val items by viewModel.items.observeAsState(initial = emptyList())

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
            ManagerScreen(
                items = items,
                onItemQuantityChange = { id, newQuantity ->
                    viewModel.updateQuantity(id, newQuantity)
                },
                onItemRemove = { id ->
                    viewModel.removeItem(id)
                },
                onItemAdd = { newItem ->
                    viewModel.addItem(newItem)
                }
            )
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun AppNavigationPreview() {
    AndroidEstoqueManagerTheme {

        val mockItems = listOf(
            itemModel(1, "Item 1", 10, "Descrição 1"),
            itemModel(2, "Item 2", 5, "Descrição 2")
        )

        ManagerScreen(
            items = mockItems,
            onItemQuantityChange = { _, _ -> },
            onItemRemove = { _ -> },
            onItemAdd = { newItem -> println("Novo item: $newItem") }
        )
    }
}