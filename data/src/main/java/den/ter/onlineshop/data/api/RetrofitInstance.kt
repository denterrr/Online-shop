package den.ter.onlineshop.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object RetrofitInstance {

    const val BASE_URL = "https://run.mocky.io/"

    private val retrofit by lazy{
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    val apiService:ApiService by lazy{
        retrofit.create(ApiService::class.java)
    }
}