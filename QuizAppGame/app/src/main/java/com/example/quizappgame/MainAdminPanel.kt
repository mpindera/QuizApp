package com.example.quizappgame

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class MainAdminPanel : AppCompatActivity() {
    private lateinit var quizName: EditText
    private lateinit var addToQuiz: Button
    private lateinit var chackedVisible: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_admin_panel)

        addToQuiz = findViewById(R.id.addToQuiz)
        quizName = findViewById(R.id.quizName)
        chackedVisible = findViewById(R.id.chackedVisible)


        addToQuiz.setOnClickListener {
            val id = UUID.randomUUID().toString()
            val name = quizName.text.toString()
            val visible = chackedVisible.isChecked

            val quizModel = QuizModel(id, name, visible, 0)
            uploadQuiz(quizModel)
        }
    }

    private fun uploadQuiz(quizModel: QuizModel) {
        FirebaseFirestore.getInstance().collection("quiz").document(quizModel.getQuizID())
            .set(quizModel)
        quizName.text.clear()
    }

    override fun onResume() {
        super.onResume()



        if (FirebaseAuth.getInstance().currentUser == null) {
            FirebaseAuth.getInstance().signInAnonymously()
                .addOnSuccessListener {

                }
                .addOnFailureListener {

                }
        }
    }
}