package den.ter.onlineshop.data.repository

import den.ter.onlineshop.data.api.RetrofitInstance
import den.ter.onlineshop.domain.models.besthotmodel.BestAndHotModel
import den.ter.onlineshop.domain.models.cartmodel.CartModel
import den.ter.onlineshop.domain.models.detailmodel.DetailModel
import den.ter.onlineshop.domain.repository.RepositoryInterface
import retrofit2.Response

class MyRepository(private val retrofit: RetrofitInstance): RepositoryInterface {

    override suspend fun getBestAndHots(): Response<BestAndHotModel>{
        return retrofit.apiService.getBestAndHots()
    }


    override suspend fun getCart(): Response<CartModel> {
        return retrofit.apiService.getCart()
    }


    override suspend fun getDetails(): Response<DetailModel>{
        return retrofit.apiService.getDetails()
    }
}