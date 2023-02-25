package com.example.quizappgame.admin.panel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import com.example.quizappgame.databinding.ActivityQuestionBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class QuestionActivity : AppCompatActivity() {
    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val uploadQuestionToList = binding.uploadQuestionToList
        val questionTitle = binding.questionTitle

        val radioGroup = binding.radioGroup

        val choiceFirst = binding.choice1
        val choiceSecond = binding.choice2
        val choiceThird = binding.choice3
        val choiceFourth = binding.choice4

        val quizID = intent.getStringExtra("id")
        val quizName = intent.getStringExtra("nameOfQuiz")

        uploadQuestionToList.setOnClickListener {
            Log.d("ddod", quizID.toString())
            var answer = ""
            val questionID = UUID.randomUUID().toString()
            val question = questionTitle.text.toString()

            val choiceFirst1 = choiceFirst.text.toString()
            val choiceSecond2 = choiceSecond.text.toString()
            val choiceThird3 = choiceThird.text.toString()
            val choiceFourth4 = choiceFourth.text.toString()

            val a = findViewById<RadioButton>(radioGroup.checkedRadioButtonId)
            when (a.text.toString()) {
                "1" -> {
                    answer = choiceFirst1
                }
                "2" -> {
                    answer = choiceSecond2
                }
                "3" -> {
                    answer = choiceThird3
                }
                "4" -> {
                    answer = choiceFourth4
                }
            }
            if (question.isNotEmpty() && choiceFirst1.isNotEmpty() && choiceSecond2.isNotEmpty()
                && choiceThird3.isNotEmpty() && choiceFourth4.isNotEmpty() && answer.isNotEmpty()
            ) {
                FirebaseFirestore.getInstance().collection("question").document(questionID)
                    .set(
                        mapOf(
                            "questionID" to questionID,
                            "quizID" to quizID,
                            "question" to question,
                            "choice1" to choiceFirst1,
                            "choice2" to choiceSecond2,
                            "choice3" to choiceThird3,
                            "choice4" to choiceFourth4,
                            "correctAnswer" to answer,
                            "quizName" to quizName
                        )
                    )
                    .addOnSuccessListener {
                        Toast.makeText(this, "Question added successfully!", Toast.LENGTH_SHORT)
                            .show()
                        finish()
                    }
                    .addOnFailureListener { exception ->
                        Toast.makeText(
                            this,
                            "Failed to add question: ${exception.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            } else {
                Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show()
            }
        }

    }
}