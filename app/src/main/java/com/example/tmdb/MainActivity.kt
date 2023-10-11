package com.example.tmdb

import android.content.Intent
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.example.MovieStruct
import com.example.tmdb.Utils.Constant.API_KEY
import com.example.tmdb.Utils.Constant.BASE_URL
import com.example.tmdb.Utils.Constant.IMAGE_BASE_URL
import com.example.tmdb.databinding.ActivityMainBinding
import com.google.gson.Gson

class MainActivity : AppCompatActivity() {

    private lateinit var newRecyclerView: RecyclerView

    val adapter : TMDB_Adapter by lazy { TMDB_Adapter() }



//    val URL : String  = "https://api.themoviedb.org/3/movie/popular?api_key=83d01f18538cb7a275147492f84c3698"

    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)


        binding.recycleViewTMDB.adapter = adapter

        newRecyclerView = binding.recycleViewTMDB
        newRecyclerView.layoutManager = LinearLayoutManager(this)
        newRecyclerView.setHasFixedSize(true)



        getData_parseTo_TMDB_adapter()



    }

    fun getData_parseTo_TMDB_adapter(){

        var queue = Volley.newRequestQueue(this)

        val stringRequeust = StringRequest(Request.Method.GET,
            BASE_URL+API_KEY,
            { response ->
                var resObject = Gson().fromJson(response, MovieStruct::class.java)

                val dataArray = resObject.results

                adapter.dataArray = dataArray
                adapter.notifyDataSetChanged()

                adapter.setOnClickListener(object : TMDB_Adapter.onItemClickListener{
                    override fun onItemClick(position: Int) {

                        var imageURI : String = "$IMAGE_BASE_URL"+dataArray[position].posterPath

                        val intent: Intent = Intent(this@MainActivity, TMDB_Detail_Screen::class.java)
                        intent.putExtra("title", dataArray[position].title)
                        intent.putExtra("originalTitle", dataArray[position].originalTitle)
                        intent.putExtra("backdropPth", dataArray[position].backdropPath)
                        intent.putExtra("voteAvg", dataArray[position].voteAverage)
                        intent.putExtra("voteCount", dataArray[position].voteCount)
                        intent.putExtra("overview", dataArray[position].overview)

//                        var date = dataArray[position].releaseDate
//                        val year = date?.split("-")
//                        Log.e("date", "date "+date)
//                        Log.e("date", "date "+ (year?.get(0) ?: year))

                        intent.putExtra("releaseDate", dataArray[position].releaseDate)
                        intent.putExtra("posterPath", imageURI)
                        startActivity(intent)
                    }

                })

            },
            { error ->
                Toast.makeText(this,
                    "Error : "+error.message, Toast.LENGTH_LONG).show()
            }

            )
        queue.add(stringRequeust)
    }


}