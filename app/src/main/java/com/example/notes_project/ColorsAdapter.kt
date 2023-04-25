package com.example.notes_project

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView

interface onItemClickListener{
    fun onItemClick(prevPos: Int, position: Int)
}
class ColorsAdapter(val listener: onItemClickListener, val colors: List<Int>): RecyclerView.Adapter<ColorsAdapter.MyViewHolder>(){
    lateinit var tickVisibiliyies: MutableList<Boolean>
    inner class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var card: MaterialCardView
        var imgView: ImageView
        init {
            card = itemView.findViewById<MaterialCardView>(R.id.card)
            imgView = itemView.findViewById(R.id.tick)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.imgView.visibility = if(tickVisibiliyies[position]) View.VISIBLE else View.INVISIBLE
        holder.card.setCardBackgroundColor(colors.get(position))
        holder.card.setOnClickListener {
            println("The ${tickVisibiliyies}, ${tickVisibiliyies.indexOf(true)}")
            listener.onItemClick(tickVisibiliyies.indexOf(true), position)
            //holder.imgView.visibility = if(tickVisibiliyies[position]) View.VISIBLE else View.INVISIBLE
        }
    }

    fun<T> List<T>.posOf(el: T): Int?{
        var i = 0
        var index: Int? = null
        repeat(this.size){
            if(this[i] == el){
                index = i
            }
            i++;
        }
        return index
    }


    override fun getItemCount(): Int {
        return colors.size
    }

    fun updateList(newTickVisibilities: MutableList<Boolean>){
        tickVisibiliyies = newTickVisibilities
        notifyDataSetChanged()
    }

}