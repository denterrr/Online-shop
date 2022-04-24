package den.ter.onlineshop.domain.models.besthotmodel



data class BestAndHotModel(
    val best_seller: List<BestSeller>,
    val home_store: List<HomeStore>
)