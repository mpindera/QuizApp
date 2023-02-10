package com.example.quizappgame


class QuizModel {
    private var quizID: String
    private var quizName: String
    private var visible: Boolean
    private var duration: Long

    constructor() {
        this.quizID = ""
        this.quizName = ""
        this.visible = false
        this.duration = 0
    }

    constructor(quizID: String, quizName: String, visible: Boolean, duration: Long) {
        this.quizID = quizID
        this.quizName = quizName
        this.visible = visible
        this.duration = duration
    }

    fun getQuizID(): String {
        return quizID
    }

    fun setQuizID(quizID: String) {
        this.quizID = quizID
    }

    fun getQuizName(): String {
        return quizName
    }

    fun setQuizName(quizName: String) {
        this.quizName = quizName
    }

    fun getVisible(): Boolean {
        return visible
    }

    fun setVisible(visible: Boolean) {
        this.visible = visible
    }

    fun getDuration(): Long {
        return duration
    }

    fun setDuration(duration: Long) {
        this.duration = duration
    }
}
