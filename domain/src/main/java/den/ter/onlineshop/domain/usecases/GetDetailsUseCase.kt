package den.ter.onlineshop.domain.usecases

import den.ter.onlineshop.domain.repository.RepositoryInterface

class GetDetailsUseCase(private val repository: RepositoryInterface) {
    suspend fun execute() = repository.getDetails()
}