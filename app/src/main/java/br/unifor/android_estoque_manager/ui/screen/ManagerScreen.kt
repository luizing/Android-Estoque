// ManagerScreen.kt
package br.unifor.android_estoque_manager.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.unifor.android_estoque_manager.model.itemModel
import br.unifor.android_estoque_manager.ui.component.ItemComponent

@Composable
fun ManagerScreen(
    items: List<itemModel>,
    onItemQuantityChange: (Int, Int) -> Unit,
    onItemRemove: (Int) -> Unit,
    onItemAdd: (itemModel) -> Unit,
    modifier: Modifier = Modifier
) {
    var showDialog by remember { mutableStateOf(false) }
    var itemName by remember { mutableStateOf("") }
    var itemQuantity by remember { mutableStateOf("") }
    var itemObs by remember { mutableStateOf("") }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            FloatingActionButton(onClick = { showDialog = true }) {
                Icon(Icons.Default.Add, contentDescription = "Adicionar Item")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                text = "Estoque",
                style = MaterialTheme.typography.headlineMedium,
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)
            )

            if (items.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text("Nenhum item no estoque")
                }
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(items) { item ->
                        ItemComponent(
                            item = item,
                            onIncrement = {
                                onItemQuantityChange(item.id, item.quantidade + 1)
                            },
                            onDecrement = {
                                onItemQuantityChange(item.id, (item.quantidade - 1).coerceAtLeast(0))
                            },
                            onRemove = {
                                onItemRemove(item.id)
                            },
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
            }
        }

        // Diálogo de adição
        if (showDialog) {
            AlertDialog(
                onDismissRequest = { showDialog = false },
                confirmButton = {
                    TextButton(onClick = {
                        if (itemName.isNotBlank() && itemQuantity.toIntOrNull() != null) {
                            val newItem = itemModel(
                                id = (items.maxOfOrNull { it.id } ?: 0) + 1,
                                nome = itemName,
                                quantidade = itemQuantity.toInt(),
                                descricao = itemObs
                            )
                            onItemAdd(newItem)
                            itemName = ""
                            itemQuantity = ""
                            itemObs = ""
                            showDialog = false
                        }
                    }) {
                        Text("Adicionar")
                    }
                },
                dismissButton = {
                    TextButton(onClick = { showDialog = false }) {
                        Text("Cancelar")
                    }
                },
                title = { Text("Novo Item") },
                text = {
                    Column {
                        OutlinedTextField(
                            value = itemName,
                            onValueChange = { itemName = it },
                            label = { Text("Nome") },
                            singleLine = true
                        )
                        OutlinedTextField(
                            value = itemQuantity,
                            onValueChange = { itemQuantity = it },
                            label = { Text("Quantidade") },
                            singleLine = true,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                        OutlinedTextField(
                            value = itemObs,
                            onValueChange = { itemObs = it },
                            label = { Text("Observações") },
                            singleLine = false,
                            modifier = Modifier.padding(top = 8.dp)
                        )
                    }
                }
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ManagerScreenPreview() {
    val sampleItems = listOf(
        itemModel(1, "Barra Olímpica", 3, "20kg"),
        itemModel(2, "Anilha 25kg", 5, "Apenas com autorização")
    )

    ManagerScreen(
        items = sampleItems,
        onItemQuantityChange = { _, _ -> },
        onItemRemove = { _ -> },
        onItemAdd = { println("Novo item: $it") }
    )
}
