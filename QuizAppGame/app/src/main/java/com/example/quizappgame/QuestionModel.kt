package com.example.quizappgame

class QuestionModel {

    private var questionID: String
    private var quizID: String
    private var question: String

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
        this.choice1 = choice1
        this.choice2 = choice2
        this.choice3 = choice3
        this.choice4 = choice4
        this.correctAnswer = correctAnswer
        this.visible = visible
    }


}