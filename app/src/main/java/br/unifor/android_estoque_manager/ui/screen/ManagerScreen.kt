// ManagerScreen.kt
package br.unifor.android_estoque_manager.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    modifier: Modifier = Modifier,
    innerPadding: PaddingValues = PaddingValues(0.dp)
)
    {
        Column(
            modifier = modifier
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
    }

@Preview(showBackground = true)
@Composable
fun ManagerScreenPreview() {
    val sampleItems = listOf(
        itemModel(1, "Processador Intel i7", 15, "10ª geração"),
        itemModel(2, "Memória RAM 16GB", 23, "DDR4 3200MHz")
    )

    ManagerScreen(
        items = sampleItems,
        onItemQuantityChange = { id, newQuantity -> },
        onItemRemove = { id -> }
    )
}