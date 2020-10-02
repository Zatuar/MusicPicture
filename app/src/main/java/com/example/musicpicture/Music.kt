package com.example.musicpicture

import com.google.gson.annotations.SerializedName

class Music {
    @SerializedName("icon")
    private var icon: String? = null
    fun getIcon(): String? {
        return icon
    }
    fun setIcon(icon: String) {
        this.icon = icon
    }
    @SerializedName("title")
    private var title: String? = null
    fun getTitle(): String? {
        return title
    }
    fun setTitle(title: String) {
        this.title = title
    }
    @SerializedName("author")
    private var author: String? = null
    fun getAuthor(): String? {
        return author
    }
    fun setAuthor(author: String) {
        this.author = author
    }
    @SerializedName("album")
    private var album: String? = null
    fun getAlbum(): String? {
        return album
    }
    fun setAlbum(album: String) {
        this.album = album
    }
    @SerializedName("style")
    private var style: String? = null
    fun getStyle(): String? {
        return style
    }
    fun getStyle(style: String) {
        this.style = style
    }
    @SerializedName("time")
    private var time: Int? = null
    fun getTime(): Int? {
        return time
    }
    fun setTime(time: Int) {
        this.time = time
    }
    @SerializedName("link")
    private var link: String? = null
    fun getLink(): String? {
        return link
    }
    fun setLink(link: String) {
        this.link = link
    }

}