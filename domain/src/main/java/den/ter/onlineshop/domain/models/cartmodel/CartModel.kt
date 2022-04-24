package den.ter.onlineshop.domain.models.cartmodel

data class CartModel(
    val basket: List<Basket>,
    val delivery: String,
    val id: String,
    val total: Int
)