package den.ter.onlineshop.presentation.screens.start.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import den.ter.onlineshop.R
import den.ter.onlineshop.domain.models.besthotmodel.BestSeller
import den.ter.onlineshop.presentation.screens.start.StartFragment
import kotlinx.android.synthetic.main.product_item.view.*


class BestAdapter(private val context: Context, private val navController: NavController) : RecyclerView.Adapter<BestAdapter.BestViewHolder>() {

    val PRODUCT_KEY = "PRODUCT_KEY"
    var listBest = emptyList<BestSeller>()

    class BestViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BestViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return BestViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: BestViewHolder, position: Int) {
        holder.itemView.name.text = listBest[position].title
        holder.itemView.price.text = "$${listBest[position].price_without_discount}"
        holder.itemView.old_price.text = "$${listBest[position].discount_price}"
        if (listBest[position].is_favorites) {
            holder.itemView.heart.setImageResource(R.drawable.ic_favorite_true)
        } else {
            holder.itemView.heart.setImageResource(R.drawable.ic_heart)
        }

        Glide.with(context)
            .load(listBest[position].picture)
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.load_image)
            .fallback(R.drawable.load_image)
            .into(holder.itemView.image)
    }

    override fun getItemCount(): Int = listBest.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list2: List<BestSeller>) {
        listBest = list2
        notifyDataSetChanged()
    }

    override fun onViewAttachedToWindow(holder: BestViewHolder) {
        super.onViewAttachedToWindow(holder)
        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putSerializable(PRODUCT_KEY, listBest[holder.adapterPosition])
            navController.navigate(R.id.action_startFragment_to_productDetailFragment, bundle)
        }
    }

    override fun onViewDetachedFromWindow(holder: BestViewHolder) {
        holder.itemView.setOnClickListener(null)
    }
}