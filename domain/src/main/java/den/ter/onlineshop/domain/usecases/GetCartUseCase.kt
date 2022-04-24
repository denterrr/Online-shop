package den.ter.onlineshop.domain.usecases

import den.ter.onlineshop.domain.repository.RepositoryInterface

class GetCartUseCase(private val repository: RepositoryInterface) {
    suspend fun execute() = repository.getCart()
}