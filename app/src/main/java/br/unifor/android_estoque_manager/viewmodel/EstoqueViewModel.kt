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
        _items.value = listOf(
            itemModel(1, "Processador Intel i7", 15, "10ª geração"),
            itemModel(2, "Memória RAM 16GB", 23, "DDR4 3200MHz")
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
}