package den.ter.onlineshop.presentation.screens.start

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import den.ter.onlineshop.data.repository.MyRepository
import den.ter.onlineshop.domain.models.besthotmodel.BestAndHotModel
import den.ter.onlineshop.domain.models.cartmodel.CartModel
import den.ter.onlineshop.domain.usecases.GetBestAndHotsUseCase
import den.ter.onlineshop.domain.usecases.GetCartUseCase
import den.ter.onlineshop.domain.usecases.GetDetailsUseCase
import kotlinx.coroutines.launch

class StartViewModel(
    private val getBestAndHotsUseCase: GetBestAndHotsUseCase,
    private val getCartUseCase: GetCartUseCase,
) : ViewModel() {

    private val _resp = MutableLiveData<BestAndHotModel>()

    val resp: LiveData<BestAndHotModel>
        get() = _resp

    private val _resp2 = MutableLiveData<CartModel>()

    val respCart: LiveData<CartModel>
        get() = _resp2


    fun getBestsAndHots() = viewModelScope.launch {
        getBestAndHotsUseCase.execute().let {
            if (it.isSuccessful) {
                _resp.postValue(it.body())
            } else {
                Log.d("mytag", "Error Response")
            }
        }
    }

    fun getCart() = viewModelScope.launch {
        getCartUseCase.execute().let {
            if (it.isSuccessful) {
                _resp2.postValue(it.body())
            } else {
                Log.d("mytag", "Error Response")
            }
        }
    }
}