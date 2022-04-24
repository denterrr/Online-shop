package den.ter.onlineshop.di

import den.ter.onlineshop.data.api.RetrofitInstance
import den.ter.onlineshop.data.repository.MyRepository
import den.ter.onlineshop.domain.repository.RepositoryInterface
import org.koin.dsl.module

val dataModule = module {

    single<RetrofitInstance> {
        RetrofitInstance
    }

    single<RepositoryInterface> {
        MyRepository(retrofit = get())
    }

}