package com.example.quizappgame

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.RadioButton
import com.example.quizappgame.databinding.ActivityQuestionBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.util.*

class QuestionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityQuestionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityQuestionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val quizID = intent.getStringExtra("id")

        binding.uploadQuestion.setOnClickListener {
            var answer = ""
            val questionID = UUID.randomUUID().toString()
            val question = binding.questionTitle.text.toString()
            val choice1 = binding.choice1.text.toString()
            val choice2 = binding.choice2.text.toString()
            val choice3 = binding.choice3.text.toString()
            val choice4 = binding.choice4.text.toString()
            val visible: Boolean = binding.showCheckBox.isChecked
            val a = findViewById<RadioButton>(binding.radioGroup.checkedRadioButtonId)
            when (a.text.toString()) {
                "1" -> {
                    answer = choice1
                }
                "2" -> {
                    answer = choice2
                }
                "3" -> {
                    answer = choice3
                }
                "4" -> {
                    answer = choice4
                }
            }
            FirebaseFirestore.getInstance().collection("question").document(questionID)
                .set(
                    mapOf(
                        "questionID" to questionID,
                        "quizID" to quizID,
                        "question" to question,
                        "choice1" to choice1,
                        "choice2" to choice2,
                        "choice3" to choice3,
                        "choice4" to choice4,
                        "correctAnswer" to answer,
                        "visible" to visible
                    )
                )


        }

    }
}