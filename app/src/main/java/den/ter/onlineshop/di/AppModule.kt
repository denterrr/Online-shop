package den.ter.onlineshop.di

import den.ter.onlineshop.presentation.screens.cart.CartViewModel
import den.ter.onlineshop.presentation.screens.product.ProductDetailViewModel
import den.ter.onlineshop.presentation.screens.start.StartViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    viewModel<StartViewModel>{
        StartViewModel(
            getBestAndHotsUseCase = get(),
            getCartUseCase = get()
        )
    }

    viewModel<CartViewModel>{
        CartViewModel(
            getCartUseCase = get()
        )
    }

    viewModel<ProductDetailViewModel>{
        ProductDetailViewModel(
            getDetailsUseCase = get()
        )
    }
}