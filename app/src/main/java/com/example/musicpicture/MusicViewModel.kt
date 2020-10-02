package com.example.musicpicture

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MusicViewModel {
    private val mediaMusic = MediaPlayer()
    private val audioURL = "https://www.android-examples.com/wp-content/uploads/2016/04/Thunder-rumble.mp3"
    val text: MutableLiveData<String> = MutableLiveData()
    private val musicList: RecyclerView? = null

    init {
        text.value = "This is Music Fragment"
    }
    fun setButtonPlayer(root: View?, context: Context?) {
        mediaMusic.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())
        mediaMusic.setDataSource(context!!, Uri.parse(audioURL))
        mediaMusic.prepareAsync()

        val playPause = root!!.findViewById<Button>(R.id.playpause)
        playPause.setOnClickListener {
            if (mediaMusic.isPlaying) {
                mediaMusic.pause()
                Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show()
            } else {
                mediaMusic.start()
                Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun callAPI(context: Context?){
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("http://raw.githubusercontent.com/Zatuar/Projet4A_Mobile/master/data/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val response: ApiRest = retrofit.create(ApiRest::class.java)
        val call: Call<List<Music>> = response.loadMusics()
        call.enqueue(object : Callback<List<Music> > {
            override fun onResponse(call: Call<List<Music>>, response: Response<List<Music>>) {
                showMusic(response.body()!!,context)
            }

            override fun onFailure(call: Call<List<Music>>, t: Throwable) {
                Log.d("ERROR", "API ERROR")
            }
        })
    }

    private fun showMusic(list: List<Music>,context: Context?) {
        musicList!!.setHasFixedSize(true)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context!!)
        musicList.layoutManager = mLayoutManager
        val mAdapter: RecyclerView.Adapter<*> = MyAdapter(list, object : ClickItemListenner {
            override fun onItemClick(item: Music?) {
                    //selectMe(item)
            }
        }, context)
        musicList.adapter = mAdapter
        val mDivider = DividerItemDecoration(musicList.context, DividerItemDecoration.VERTICAL)
        musicList.addItemDecoration(mDivider)
    }
}