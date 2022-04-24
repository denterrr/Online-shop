package den.ter.onlineshop.presentation.screens.cart

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import den.ter.onlineshop.data.repository.MyRepository
import den.ter.onlineshop.domain.models.cartmodel.CartModel
import den.ter.onlineshop.domain.usecases.GetCartUseCase
import kotlinx.coroutines.launch

class CartViewModel(private val getCartUseCase: GetCartUseCase) : ViewModel() {

    private val _resp = MutableLiveData<CartModel>()

    val resp: LiveData<CartModel>
        get() = _resp


    fun getCart() = viewModelScope.launch {
        getCartUseCase.execute().let {
            if (it.isSuccessful) {
                _resp.postValue(it.body())
            } else {
                Log.d("mytag", "Error Response")
            }
        }
    }
}