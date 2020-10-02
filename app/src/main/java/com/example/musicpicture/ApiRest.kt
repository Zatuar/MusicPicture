package com.example.musicpicture

import retrofit2.Call
import retrofit2.http.GET


interface ApiRest {
    @GET("data.json")
    fun loadMusics(): Call<List<Music>>
}