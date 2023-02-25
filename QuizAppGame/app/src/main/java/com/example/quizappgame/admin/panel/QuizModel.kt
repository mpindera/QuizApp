package com.example.quizappgame.admin.panel


class QuizModel {
    private var quizID: String
    private var quizName: String
    private var visible: Boolean
    private var createdAt: Long = System.currentTimeMillis()

    constructor() {
        this.quizID = ""
        this.quizName = ""
        this.visible = false
        this.createdAt = 0
    }

    constructor(quizID: String, quizName: String, visible: Boolean, createdAt: Long) {
        this.quizID = quizID
        this.quizName = quizName
        this.visible = visible
        this.createdAt = createdAt
    }

    fun getCreatedAt(): Long {
        return createdAt
    }

    fun setCreatedAt(createdAt: Long) {
        this.createdAt = createdAt
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
}
