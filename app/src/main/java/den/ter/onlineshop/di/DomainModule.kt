package den.ter.onlineshop.di

import den.ter.onlineshop.domain.usecases.GetBestAndHotsUseCase
import den.ter.onlineshop.domain.usecases.GetCartUseCase
import den.ter.onlineshop.domain.usecases.GetDetailsUseCase
import org.koin.dsl.module

val domainModule = module {

    factory<GetBestAndHotsUseCase> {
        GetBestAndHotsUseCase(repository = get())
    }

    factory<GetCartUseCase> {
        GetCartUseCase(repository = get())
    }

    factory<GetDetailsUseCase> {
        GetDetailsUseCase(repository = get())
    }

}