package br.unifor.android_estoque_manager.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.unifor.android_estoque_manager.model.itemModel

class EstoqueViewModel : ViewModel() {
    private val _items = MutableLiveData<List<itemModel>>()
    val items: LiveData<List<itemModel>> = _items

    init {
        loadSampleItems()
    }

    private fun loadSampleItems() {
        // Receber informaçõs do banco
        _items.value = listOf(
            itemModel(1, "Barra Olímpica", 3, "20kg"),
            itemModel(2, "Anilha 25kg", 5, "Apenas com autorização")
        )
    }


    fun updateQuantity(itemId: Int, newQuantity: Int) {
        _items.value = _items.value?.map { item ->
            if (item.id == itemId) item.copy(quantidade = newQuantity) else item
        }
    }

    fun removeItem(itemId: Int) {
        _items.value = _items.value?.filter { it.id != itemId }
    }

    fun addItem(item: itemModel) {
        val currentList = _items.value.orEmpty()
        val updatedList = currentList + item
        _items.value = updatedList
        //updateDB()
    }

    fun updtadeBD(){
        //implementar
        // this.items -> BD
        print("updateBD")
    }
}

