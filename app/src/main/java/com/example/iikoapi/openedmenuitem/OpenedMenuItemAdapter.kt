package com.example.iikoapi.openedmenuitem

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.iikoapi.R
import com.example.iikoapi.general.GeneralActivity
import com.example.iikoapi.startapp.datatype.Product
import kotlinx.android.synthetic.main.opened_item_for_view_pager.view.*
import kotlin.String as String1

class OpenedMenuItemAdapter(private var items : List<Product>, private var context: Context, var commonPosition : Int) : RecyclerView.Adapter<OpenedMenuItemAdapter.OpenedMenuItemViewHolder>() {

    inner class OpenedMenuItemViewHolder(view : View) : RecyclerView.ViewHolder(view){
        private val img = view.findViewById<ImageView>(R.id.image_inside)
        private val name = view.findViewById<TextView>(R.id.text_inside)

        fun bind(data : Product) {

            val requestOptions = RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)

            Glide.with(context)
                .applyDefaultRequestOptions(requestOptions)
                .load(try{data.images[0].imageUrl}catch (e:Exception){"dd"})
                .into(img)

            name.text = data.name
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OpenedMenuItemViewHolder {
        return OpenedMenuItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.opened_item_for_view_pager,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OpenedMenuItemViewHolder, position: Int) {
        var isInvisible = 1f
        val currentItem = items[position]
        holder.bind(currentItem)
        holder.itemView.go_back.setOnClickListener {
            var intent = Intent(context, GeneralActivity::class.java)
            intent.putExtra("back_from", commonPosition)

            context.startActivity(intent, ActivityOptions.makeCustomAnimation(context, R.anim.enter_anim_left, R.anim.exit_anim_left).toBundle())
        }
        holder.itemView.info_button.setOnClickListener {
            Log.d("tag", "pressed")
            var list = arrayListOf("1", "2", "3")
            var adapter = ArrayAdapter(context, android.R.layout.simple_list_item_1, list)
            holder.itemView.info_list_view.adapter = adapter
            holder.itemView.info_list_view.animate().alpha(isInvisible)
            isInvisible = if(isInvisible == 1f){ 0f }else{ 1f }

        }
    }


}


