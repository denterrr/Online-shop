package den.ter.onlineshop.presentation.screens.start.adapters

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import den.ter.onlineshop.R
import kotlinx.android.synthetic.main.category_item.view.*


class CategoryAdapter() : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {


    private val listCategory = listOf<String>(
        "Phones",
        "Computers",
        "Health",
        "Books",
        "Sport"
    )
    private val listImages = listOf<Int>(
        R.drawable.ic_phone,
        R.drawable.ic_comp,
        R.drawable.ic_health,
        R.drawable.ic_books,
        R.drawable.ic_heartmain
    )

    private var clicked_pos = 0

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.category_item, parent, false)
        return CategoryViewHolder(view)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.itemView.title.text = listCategory[position]
        holder.itemView.icon.setImageResource(listImages[position])
        holder.itemView.setOnClickListener {
            clicked_pos = holder.adapterPosition
            notifyDataSetChanged()
        }
        if(clicked_pos==position){
            holder.itemView.title.setTextColor(Color.parseColor("#FF6E4E"))
            holder.itemView.backgr.setCardBackgroundColor(Color.parseColor("#FF6E4E"))
            holder.itemView.icon.setColorFilter(Color.WHITE)
        }else{
            holder.itemView.title.setTextColor(Color.parseColor("#010035"))
            holder.itemView.backgr.setCardBackgroundColor(Color.WHITE)
            holder.itemView.icon.setColorFilter(Color.parseColor("#E5E5E5"))
        }

    }

    override fun getItemCount(): Int = listCategory.size

    @SuppressLint("NotifyDataSetChanged")
    fun update() {
        notifyDataSetChanged()
    }

}