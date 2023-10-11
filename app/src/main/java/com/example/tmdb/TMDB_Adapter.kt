package com.example.tmdb

import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.MoviesResponces

class TMDB_Adapter() : RecyclerView.Adapter<TMDB_Adapter.MyViewHolder> () {

    var dataArray: ArrayList<MoviesResponces> = ArrayList()


    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnClickListener(listener : onItemClickListener){
        mListener = listener
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        var item = LayoutInflater.from(parent.context).inflate(R.layout.item_list,
            parent, false)
        return  MyViewHolder(item, mListener)
    }

    override fun getItemCount(): Int {
        return dataArray.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        var currItem = dataArray[position]
        var imageURI : String = "https://image.tmdb.org/t/p/w500"+currItem.posterPath
        Glide.with(holder.itemView).load(imageURI).into(holder.image)
        holder.title.setText(currItem.title)


        val date = currItem.releaseDate?.split("-")
        val getYear = (date?.get(0))

//        Log.e("date Adap", "date "+date)
//        Log.e("date Adap", "date "+getYear)

        if (getYear?.toInt() == 2023){

            holder.year.setText(currItem.releaseDate)


            holder.year.setTextColor(Color.parseColor("#f2230e"))

        }
        else{
        holder.year.setText(currItem.releaseDate)

    }


        holder.rating.setText(currItem.voteAverage.toString())

    }


    class MyViewHolder(itemView:View, listener : onItemClickListener): RecyclerView.ViewHolder(itemView){
        var image : ImageView = itemView.findViewById(R.id.title_Img)
        var title : TextView = itemView.findViewById(R.id.title_desc)
        var year : TextView = itemView.findViewById(R.id.year)
        var rating : TextView = itemView.findViewById(R.id.rating)
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}