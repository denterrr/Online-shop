package den.ter.onlineshop.presentation.screens.cart

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import den.ter.onlineshop.R
import den.ter.onlineshop.domain.models.cartmodel.Basket
import kotlinx.android.synthetic.main.cart_item.view.*
import kotlinx.android.synthetic.main.cart_item.view.image

class CartAdapter(private val context: Context) :
    RecyclerView.Adapter<CartAdapter.CartViewHolder>() {
    var listIt = emptyList<Basket>()

    class CartViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CartViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        return CartViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: CartViewHolder, position: Int) {
        Glide.with(context)
            .load(listIt[position].images)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.load_image)
            .fallback(R.drawable.load_image)
            .into(holder.itemView.image)

        holder.itemView.title.text = listIt[position].title
        holder.itemView.count.text = "1"
        holder.itemView.price.text =
            "$${holder.itemView.count.text.toString().toInt() * listIt[position].price}"
    }

    override fun getItemCount(): Int = listIt.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(listAdded: List<Basket>) {
        listIt = listAdded
        notifyDataSetChanged()
    }
}