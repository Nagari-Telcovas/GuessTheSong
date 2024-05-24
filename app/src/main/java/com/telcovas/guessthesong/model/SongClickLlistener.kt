package com.telcovas.guessthesong.model

interface SongClickListener {
    fun onSelectClicked(optionName: String?, type: String?)
}