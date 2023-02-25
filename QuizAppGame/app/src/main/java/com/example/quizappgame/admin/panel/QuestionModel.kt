package com.example.quizappgame.admin.panel

class QuestionModel {

    private var questionID: String
    private var quizID: String
    var question: String

    var quizName: String

    private var choice1: String
    private var choice2: String
    private var choice3: String
    private var choice4: String

    private var correctAnswer: String
    private var visible: Boolean = false

    constructor() {
        this.questionID = ""
        this.quizID = ""
        this.question = ""
        this.quizName = ""
        this.choice1 = ""
        this.choice2 = ""
        this.choice3 = ""
        this.choice4 = ""
        this.correctAnswer = ""
        this.visible = false
    }

    constructor(
        questionID: String,
        quizID: String,
        question: String,
        quizName: String,
        choice1: String,
        choice2: String,
        choice3: String,
        choice4: String,
        correctAnswer: String,
        visible: Boolean
    ) {
        this.questionID = questionID
        this.quizID = quizID
        this.question = question
        this.quizName = quizName
        this.choice1 = choice1
        this.choice2 = choice2
        this.choice3 = choice3
        this.choice4 = choice4
        this.correctAnswer = correctAnswer
        this.visible = visible
    }

    fun getQuestionID(): String {
        return questionID
    }

    fun setQuestionID(questionID: String) {
        this.questionID = questionID
    }

    fun getQuestionNameQuiz(): String {
        return quizName
    }

    fun getQuizQuestion(): String {
        return question
    }

    fun setQuizQuestion(question: String) {
        this.question = question
    }

    fun setQuestionNameQuiz(quizName: String) {
        this.quizName = quizName
    }


    fun getQuizID(): String {
        return quizID
    }

    fun setQuizID(quizID: String) {
        this.quizID = quizID
    }

    //E/SpannableStringBuilder: SPAN_EXCLUSIVE_EXCLUSIVE spans cannot have a zero length
    fun getChoice1(): String {
        return choice1
    }

    fun setChoice1(choice1: String) {
        this.choice1 = choice1
    }

    fun getChoice2(): String {
        return choice2
    }

    fun setChoice2(choice2: String) {
        this.choice2 = choice2
    }

    fun getChoice3(): String {
        return choice3
    }

    fun setChoice3(choice3: String) {
        this.choice3 = choice3
    }

    fun getChoice4(): String {
        return choice4
    }

    fun setChoice4(choice4: String) {
        this.choice4 = choice4
    }

    fun getCorrectAnswer(): String {
        return correctAnswer
    }

    fun setCorrectAnswer(correctAnswer: String) {
        this.correctAnswer = correctAnswer
    }

    fun getVisible(): Boolean {
        return visible
    }

    fun setVisible(visible: Boolean) {
        this.visible = visible
    }
}