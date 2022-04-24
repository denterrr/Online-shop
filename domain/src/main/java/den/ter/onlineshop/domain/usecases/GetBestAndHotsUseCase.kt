package den.ter.onlineshop.domain.usecases

import den.ter.onlineshop.domain.repository.RepositoryInterface

class GetBestAndHotsUseCase(private val repository: RepositoryInterface) {
    suspend fun execute() = repository.getBestAndHots()
}