package com.telcovas.guessthesong.myWines


class MyWinsModel(dateYear: String?, quizName: String?, score: String?) {
    private var title: String
    private var genre: String
    private var year: String
    init {
        this.title = dateYear!!
        this.genre = quizName!!
        this.year = score!!
    }
    fun getTitle(): String? {
        return title
    }
    fun setTitle(name: String?) {
        title = name!!
    }
    fun getYear(): String? {
        return year
    }
    fun setYear(year: String?) {
        this.year = year!!
    }
    fun getGenre(): String? {
        return genre
    }
    fun setGenre(genre: String?) {
        this.genre = genre!!
    }
}