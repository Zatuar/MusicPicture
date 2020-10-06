package com.example.musicpicture

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.fragment_music.view.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MusicViewModel {
    private val mediaMusic = MediaPlayer()
    private val audioURL = "https://www.android-examples.com/wp-content/uploads/2016/04/Thunder-rumble.mp3"
    private lateinit var playPause: ImageButton

    init {
        mediaMusic.setAudioAttributes(AudioAttributes.Builder().setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build())
    }
    fun setButtonPlayer(root: View?, context: Context?) {
        playPause = root!!.findViewById(R.id.playpause)
        playPause.setOnClickListener {
            if (mediaMusic.isPlaying) {
                mediaMusic.pause()
                playPause.setImageResource(R.drawable.ic_play)
                Toast.makeText(context, "Stop", Toast.LENGTH_SHORT).show()
            } else {
                mediaMusic.start()
                playPause.setImageResource(R.drawable.ic_pause)
                Toast.makeText(context, "Start", Toast.LENGTH_SHORT).show()
            }
        }
    }
    fun callAPI(root: View, context: Context?){
        //musicList = root.findViewById(R.id.musicList)
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl("https://raw.githubusercontent.com/Zatuar/MusicPicture/master/data/")
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
        val response: ApiRest = retrofit.create(ApiRest::class.java)
        val call: Call<List<Music>> = response.loadMusics()
        call.enqueue(object : Callback<List<Music> > {
            override fun onResponse(call: Call<List<Music>>, response: Response<List<Music>>) {
                print(response.body())
                showMusic(response.body()!!,context,root)
            }

            override fun onFailure(call: Call<List<Music>>, t: Throwable) {
                Log.d("ERROR", "API ERROR")
            }
        })
    }

    private fun showMusic(
        list: List<Music>,
        context: Context?,
        root: View
    ) {
        val musicList: RecyclerView = root.findViewById(R.id.musicList)
        musicList.setHasFixedSize(true)
        val mLayoutManager: RecyclerView.LayoutManager = LinearLayoutManager(context!!)
        musicList.layoutManager = mLayoutManager
        val mAdapter: RecyclerView.Adapter<*> = MyAdapter(list, object : ClickItemListenner {
            override fun onItemClick(item: Music?) {
                mediaMusic.stop()
                mediaMusic.setDataSource(context, Uri.parse(item!!.getLink()))
                mediaMusic.prepareAsync()
                mediaMusic.start()
                playPause.setImageResource(R.drawable.ic_pause)
                //setPlayingMusic()
            }
        }, context)
        musicList.adapter = mAdapter
        val mDivider = DividerItemDecoration(musicList.context, DividerItemDecoration.VERTICAL)
        musicList.addItemDecoration(mDivider)
    }

    private fun setPlayingMusic() {

    }
}