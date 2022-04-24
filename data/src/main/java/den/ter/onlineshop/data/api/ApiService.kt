package den.ter.onlineshop.data.api


import den.ter.onlineshop.domain.models.besthotmodel.BestAndHotModel
import den.ter.onlineshop.domain.models.cartmodel.CartModel
import den.ter.onlineshop.domain.models.detailmodel.DetailModel
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("v3/654bd15e-b121-49ba-a588-960956b15175")
    suspend fun getBestAndHots(): Response<BestAndHotModel>

    @GET("v3/53539a72-3c5f-4f30-bbb1-6ca10d42c149")
    suspend fun getCart(): Response<CartModel>

    @GET("v3/6c14c560-15c6-4248-b9d2-b4508df7d4f5")
    suspend fun getDetails(): Response<DetailModel>
}