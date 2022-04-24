package den.ter.onlineshop.presentation.screens.start.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import den.ter.onlineshop.R
import den.ter.onlineshop.domain.models.besthotmodel.HomeStore
import kotlinx.android.synthetic.main.hot_item.view.*
import kotlinx.android.synthetic.main.product_item.view.image


class HotAdapter(private val context: Context) : RecyclerView.Adapter<HotAdapter.HotViewHolder>() {


    var listBest = emptyList<HomeStore>()

    class HotViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HotViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hot_item, parent, false)
        return HotViewHolder(view)
    }

    override fun onBindViewHolder(holder: HotViewHolder, position: Int) {
        holder.itemView.name_hot.text = listBest[position].title
        holder.itemView.desc.text = listBest[position].subtitle
        if(listBest[position].is_new){
            holder.itemView.view.visibility = View.VISIBLE
        } else{
            holder.itemView.view.visibility = View.INVISIBLE
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
    fun setList(list2: List<HomeStore>) {
        listBest = list2
        notifyDataSetChanged()
    }
}