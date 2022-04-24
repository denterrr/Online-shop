package den.ter.onlineshop.domain.repository

import den.ter.onlineshop.domain.models.besthotmodel.BestAndHotModel
import den.ter.onlineshop.domain.models.cartmodel.CartModel
import den.ter.onlineshop.domain.models.detailmodel.DetailModel
import retrofit2.Response

interface RepositoryInterface {

    suspend fun getBestAndHots(): Response<BestAndHotModel>

    suspend fun getCart(): Response<CartModel>

    suspend fun getDetails(): Response<DetailModel>
}