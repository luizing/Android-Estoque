package br.unifor.android_estoque_manager.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.unifor.android_estoque_manager.model.itemModel

@Composable
fun ItemComponent(
    item: itemModel,
    onIncrement: () -> Unit,
    onDecrement: () -> Unit,
    onRemove: () -> Unit,
    modifier: Modifier = Modifier
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = item.nome,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )

                // Botão de remoção como texto
                TextButton(
                    onClick = onRemove,
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = MaterialTheme.colorScheme.error
                    )
                ) {
                    Text("Remover")
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            if (item.descricao.isNotEmpty()) {
                Text(
                    text = item.descricao,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Botão de decremento como texto
                Button(
                    onClick = onDecrement,
                    enabled = item.quantidade > 0,
                    modifier = Modifier.width(100.dp)
                ) {
                    Text("-")
                }

                Spacer(modifier = Modifier.width(8.dp))

                Text(
                    text = "Qtd: ${item.quantidade}",
                    fontSize = 16.sp,
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Botão de incremento como texto
                Button(
                    onClick = onIncrement,
                    modifier = Modifier.width(100.dp)
                ) {
                    Text("+")
                }
            }
        }
    }
}

@Preview
@Composable
fun ItemComponentPreview() {
    val sampleItem = itemModel(
        id = 1,
        nome = "Halter 20kg",
        quantidade = 3,
        descricao = "Guardado no armário"
    )

    ItemComponent(
        item = sampleItem,
        onIncrement = {},
        onDecrement = {},
        onRemove = {}
    )
}