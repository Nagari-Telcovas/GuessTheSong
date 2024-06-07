package com.telcovas.guessthesong.leaderBoard

class LeaderBoardModel(currentPos: String?, currentText: String?, compName: String?, score: Int?) {
    private var title: String
    private var currentText: String
    private var genre: String
    private var year: Int
    init {
        this.title = currentPos!!
        this.currentText = currentText!!
        this.genre = compName!!
        this.year = score!!
    }
    fun getTitle(): String? {
        return title
    }
    fun setTitle(name: String?) {
        title = name!!
    }
    fun getYear(): Int? {
        return year
    }
    fun setYear(year: Int?) {
        this.year = year!!
    }
    fun getGenre(): String? {
        return genre
    }
    fun setGenre(genre: String?) {
        this.genre = genre!!
    }

    fun getCurrentText(): String? {
        return currentText
    }
    fun setCurrentText(currentText: String?) {
        this.currentText = currentText!!
    }

}