package com.example.iikoapi.openedmenuitem

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.iikoapi.R
import com.example.iikoapi.general.GeneralActivity
import com.example.iikoapi.startapp.datatype.Product
import kotlinx.android.synthetic.main.opened_item_for_view_pager.view.*

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
        return OpenedMenuItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.opened_item_for_view_pager, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: OpenedMenuItemViewHolder, position: Int) {
        val currentItem = items[position]
        holder.bind(currentItem)
        val myView = holder.itemView
        showInfo(myView.info_fragment_RL, position)
        myView.go_back.setOnClickListener {
            val intent = Intent(context, GeneralActivity::class.java)
            intent.putExtra("back_from", commonPosition)

            context.startActivity(intent, ActivityOptions.makeCustomAnimation(context, R.anim.enter_anim_left, R.anim.exit_anim_left).toBundle())
        }

        myView.RL.layoutParams = (RelativeLayout.LayoutParams(0,0))

        myView.RL.setOnTouchListener { _, _ ->
            myView.info_fragment_RL.animate().alpha(0f).duration = 200
            myView.RL.layoutParams = (RelativeLayout.LayoutParams(0,0))
            true
        }

        myView.info_button.setOnClickListener {
            myView.info_fragment_RL.animate().alpha(1f).duration = 200
            myView.RL.layoutParams = (RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,RelativeLayout.LayoutParams.MATCH_PARENT))
        }

        showGroupModifier(listOf("text1", "text2", "text3", "text4"), myView.group_modifier, context)

        showSingledModifier(items, myView.singled_modifier, context)

    }

    private fun showInfo(view: RelativeLayout, position: Int){

        view.info_name.text = items[position].name
        view.info_carbohydrates.text = try {
            items[position].carbohydrateAmount!!.toInt()
        }catch (e : Exception){}.toString()
        view.info_energy.text = try {
            items[position].energyAmount!!.toInt()
        }catch (e : Exception){0}.toString()
        view.info_proteins.text =  try {
            items[position].fiberAmount!!.toInt()
        }catch (e : Exception){0}.toString()

    }

    private fun showGroupModifier(data : List<String>, lavashLayout : LinearLayout, context: Context){
        for(x in data){
            val textName = TextView(context)
            textName.text = x
            textName.setOnClickListener {
                lavashLayout.children.forEach {
                    it as TextView
                    it.setBackgroundColor(Color.BLACK)
                    it.setTextColor(Color.WHITE)
                }
                textName.setBackgroundColor(Color.WHITE)
                textName.setTextColor(Color.BLACK)
            }
            textName.layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, (100 / data.size).toFloat())
            lavashLayout.addView(textName)
            textName.setBackgroundColor(Color.BLACK)
            textName.setTextColor(Color.WHITE)
        }

        (lavashLayout.getChildAt(0) as TextView).setBackgroundColor(Color.WHITE)
        (lavashLayout.getChildAt(0)as TextView).setTextColor(Color.BLACK)

    }

    private fun showSingledModifier(data : List<Product>, tableLayout : TableLayout, context: Context){

        val COLUMNS = 3
        val ROWS = data.size / COLUMNS
        val EXTRA_ITEMS = data.size - ROWS * COLUMNS


        for (i in 0 until ROWS) {
            val tableRow = TableRow(context)
            tableRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
            for (j in 0 until COLUMNS) {

                tableRow.addView(createViewForTableRow(context, data[i * 3 + j]))
            }
            tableLayout.addView(tableRow, i)
        }

        val tableRow = TableRow(context)
        tableRow.layoutParams = TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT)
        for(i in 0 until EXTRA_ITEMS){
            tableRow.addView(createViewForTableRow(context, data[ROWS * COLUMNS + i]))
        }
        if(EXTRA_ITEMS != 0) {
            val spaceParam = TableRow.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            for(i in 0 until COLUMNS - EXTRA_ITEMS){
                val space = Space(context)
                space.layoutParams = spaceParam
                tableRow.addView(space)
            }
        }
        tableLayout.addView(tableRow)
    }

    private fun createViewForTableRow(context: Context, product: Product) : LinearLayout{
        val linearLayout = LinearLayout(context)
        linearLayout.orientation = LinearLayout.VERTICAL
        val param = TableRow.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
        linearLayout.layoutParams = param

        val img = ImageView(context)
        val requestOptions = RequestOptions().placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background)
        Glide.with(img.context).applyDefaultRequestOptions(requestOptions).load(try{product.images[0].imageUrl}catch (e:Exception){"dd"}).into(img)
        val par = LinearLayout.LayoutParams(200, 200);
        par.gravity = Gravity.CENTER
        img.layoutParams = par
        linearLayout.addView(img)

        val name = TextView(context)
        name.text = product.name
        name.gravity = Gravity.CENTER

        linearLayout.addView(name)

        val price = TextView(context)
        price.text = product.description
        price.gravity = Gravity.CENTER

        linearLayout.addView(price)


        linearLayout.setOnClickListener {
            Log.d("tag", product.name)
        }
        return linearLayout
    }
}


