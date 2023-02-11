package com.example.quizappgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class MainAdminPanel : AppCompatActivity() {
    lateinit var recycler: RecyclerView
    var textList = mutableListOf<QuizModel>()
    private lateinit var quizName: EditText
    private lateinit var addToQuiz: Button
    private lateinit var chackedVisible: CheckBox
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_admin_panel)

        addToQuiz = findViewById(R.id.addToQuiz)
        quizName = findViewById(R.id.quizName)
        chackedVisible = findViewById(R.id.chackedVisible)
        recycler = findViewById(R.id.quizRecycler)


        addToQuiz.setOnClickListener {
            val id = UUID.randomUUID().toString()
            val name = quizName.text.toString()
            val visible = chackedVisible.isChecked

            val quizModel = QuizModel(id, name, visible, 0)
            uploadQuiz(quizModel)
        }
        recycler.layoutManager = LinearLayoutManager(applicationContext)
        recycler.adapter = QuizAdapter(textList)
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
                    Log.d("ad", "Works")


                }
                .addOnFailureListener {
                    Log.d("ad", "bad")
                }
        }
    }
}