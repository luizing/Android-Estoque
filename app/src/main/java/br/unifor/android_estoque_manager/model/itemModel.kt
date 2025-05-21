package br.unifor.android_estoque_manager.model

data class itemModel(
    val id: Int,
    val nome: String, //Fixo
    val quantidade: Int, //Funcao para ++ e -- (min 0)
    val descricao: String
)