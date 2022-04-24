package den.ter.onlineshop.presentation.screens.product

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import den.ter.onlineshop.R
import kotlinx.android.synthetic.main.viewpager_product_item.view.*

class ViewPagerAdapter(private var viewPager2: ViewPager2, private val context: Context) :
    RecyclerView.Adapter<ViewPagerAdapter.PagerViewHolder>() {
    private var listImages = emptyList<String>()

    class PagerViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.viewpager_product_item, parent, false)
        return PagerViewHolder(view)
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        Glide.with(context)
            .load(listImages[position])
            .centerCrop()
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .placeholder(R.drawable.load_image)
            .fallback(R.drawable.load_image)
            .into(holder.itemView.iv_pager)
    }

    override fun getItemCount(): Int = listImages.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list2: List<String>) {
        listImages = list2
        notifyDataSetChanged()
    }
}