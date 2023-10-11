package com.example.tmdb

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.tmdb.databinding.ActivityMainBinding

class TMDB_Detail_Screen : AppCompatActivity() {

//    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tmdb_detail_screen)

//        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
//        setContentView(binding.root)

        var image : ImageView = findViewById(R.id.backdropImage)
//        Log.e("imgPath", "image : "+image)
        var title : TextView = findViewById(R.id.titleScreen2)
        var voteAvg : TextView = findViewById(R.id.voteAvg)
        var voteCount:TextView = findViewById(R.id.voteCount)
        var year: TextView = findViewById(R.id.year)
        var overview : TextView = findViewById(R.id.overview)

        var bundle:Bundle? = intent.extras

        val getBackDropImage = bundle?.get("backdropPth")
        val getBackDropImageURL : String = "https://image.tmdb.org/t/p/w500"+getBackDropImage
//        val posterImage = bundle?.getBundle("posterPath")
        val getTitle = bundle?.get("title")
        val getVoteAvg = bundle?.get("voteAvg")
        val getVoteCount = bundle?.get("voteCount")
        val getDate = bundle?.get("releaseDate").toString()
        val getOverView = bundle?.get("overview")

        getBackDropImage?.let { it ->
            Glide.with(this).load(getBackDropImageURL).into(image)
        }

        getTitle?.let { it ->
            title.text = it.toString()
        }

        getVoteAvg?.let { it ->
            voteAvg.text = it.toString()
        }

        getVoteCount?.let { it ->
            voteCount.text = it.toString()
        }



        val d = getDate?.split("-")
        val getYear = (d?.get(0))


//        Log.e("date", "date "+ getDate)
//        Log.e("date", "date "+ (d?.get(0) ?: year))
//        Log.e("date", "date "+ getYear)

        if (getYear?.toInt() == 2023){
            getDate?.let { it ->
                year.text = it.toString()
            }

            year.setTextColor(Color.parseColor("#f2230e"))

        }
        else{
            getDate?.let { it ->
                year.text = it.toString()
            }
        }
        getDate?.let { it ->
            year.text = it.toString()
        }

        getOverView?.let { it ->
            overview.text = it.toString()
        }




    }
}