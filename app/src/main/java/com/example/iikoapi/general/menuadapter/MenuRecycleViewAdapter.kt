package com.example.iikoapi.general.menuadapter

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.iikoapi.R
import com.example.iikoapi.models.ProductItem
import com.example.iikoapi.openedmenuitem.OpenedMenuItem
import kotlinx.android.synthetic.main.open_menu_item_for_recycler_view.view.*

class MenuRecycleViewAdapter(private var context: Context, var commonPos : Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>()
{

    private val TAG: String = "AppDebug"

    private var items: List<ProductItem> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return BlogViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.open_menu_item_for_recycler_view,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder) {
            is BlogViewHolder -> {
                holder.run { bind(items[position]) }
            }
        }

        holder.itemView.setOnClickListener{
            var intent = Intent(context, OpenedMenuItem::class.java)
//            intent.putExtra("image", items[position].image)
//            intent.putExtra("name", items[position].name)
            intent.putExtra("position", position)
            intent.putExtra("comonPos", commonPos)
            context.startActivity(intent, ActivityOptions.makeCustomAnimation(context, R.anim.enter_anim_right, R.anim.exit_anim_right).toBundle())
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    fun submitList(blogList: List<ProductItem>){
        items = blogList
    }

    class BlogViewHolder
    constructor(itemView: View): RecyclerView.ViewHolder(itemView){

        val produtc_image = itemView.product_image
        val product_name = itemView.product_name

        fun bind(productItem: ProductItem){

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(itemView.context)
                .applyDefaultRequestOptions(requestOptions)
                .load(productItem.image)
                .into(produtc_image)
            product_name.text = productItem.name

        }

    }

}