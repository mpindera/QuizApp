package com.example.quizappgame.user.panel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import com.example.quizappgame.admin.panel.QuestionModel
import com.example.quizappgame.R
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore

class QuestionActivityUser : AppCompatActivity() {
    lateinit var quizID: String
    private var questionModelList = mutableListOf<QuestionModel>()
    private var position: Int = 0
    private var totalQuest: Int = 0
    private var answer = ""

    private lateinit var quizName: TextView
    private lateinit var quizQuestion: TextView
    private lateinit var firstOption: TextView
    private lateinit var secondOption: TextView
    private lateinit var thirdOption: TextView
    private lateinit var fourthOption: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_question_user)


        quizID = intent.getStringExtra("id").toString()

        quizName = findViewById(R.id.quizNameInGame)
        quizQuestion = findViewById(R.id.quizQuestionText)
        firstOption = findViewById(R.id.firstOption)
        secondOption = findViewById(R.id.secondOption)
        thirdOption = findViewById(R.id.thirdOption)
        fourthOption = findViewById(R.id.fourthOption)

        firstOption.text = ""
        secondOption.text = ""
        thirdOption.text = ""
        fourthOption.text = ""

        loadQuestions()

        val nextQ = findViewById<Button>(R.id.nextQ)
        val cardViewFirst = findViewById<CardView>(R.id.cardViewFirst)
        val cardViewSecond = findViewById<CardView>(R.id.cardViewTwo)
        val cardViewThird = findViewById<CardView>(R.id.cardViewThrid)
        val cardViewFour = findViewById<CardView>(R.id.cardViewFour)

        val cardViewArray = listOf(cardViewFirst, cardViewSecond, cardViewThird, cardViewFour)

        cardViewArray.forEach { cardView ->
            cardView.setOnClickListener {
                val selectedView = when (cardView) {
                    cardViewFirst -> firstOption.text
                    cardViewSecond -> secondOption.text
                    cardViewThird -> thirdOption.text
                    cardViewFour -> fourthOption.text
                    else -> ""
                }
                if (selectedView == answer) {
                    Toast.makeText(this, "Great", Toast.LENGTH_SHORT).show()
                }
            }


        }

        nextQ.setOnClickListener {
            position += 1
            showQsOneByOne(position)
        }
    }

    /*   button.setOnClickListener {
           // Wykonaj akcję po kliknięciu przycisku
           // ...

           // Utwórz obiekt Handler
           val handler = Handler()

           // Umieść akcję, która ma zostać wykonana z opóźnieniem 2 sekund, w kolejce wątku głównego
           handler.postDelayed({
               // Akcja do wykonania po upływie 2 sekund
               // ...
           }, 2000) // 2000 ms = 2 sekundy
       }*/
    private fun loadQuestions() {
        FirebaseFirestore.getInstance().collection("question").whereEqualTo("quizID", quizID).get()
            .addOnSuccessListener { queryDocument ->
                val dsList: List<DocumentSnapshot> = queryDocument.documents
                totalQuest = dsList.size
                for (ds in dsList) {
                    val questionModel: QuestionModel? = ds.toObject(QuestionModel::class.java)
                    questionModelList.add(questionModel!!)
                    print(questionModelList)
                }
                if (dsList.isNotEmpty()) {
                    showQsOneByOne(position)
                }
            }
            .addOnFailureListener { e ->
                Log.w("QuestionActivityUser", "Error getting questions: ", e)
            }
    }

    private fun showQsOneByOne(i: Int) {
        if (i >= 0 && i < questionModelList.size) {
            val questionModel: QuestionModel = questionModelList[i]


            answer = questionModel.getCorrectAnswer()

            quizQuestion.text = questionModel.getQuizQuestion()
            quizName.text = questionModel.getQuestionNameQuiz()

            firstOption.text = questionModel.getChoice1()
            secondOption.text = questionModel.getChoice2()
            thirdOption.text = questionModel.getChoice3()
            fourthOption.text = questionModel.getChoice4()
        }
    }

    override fun onResume() {
        super.onResume()
        if (quizID == null) {
            finish()
        }
    }
}