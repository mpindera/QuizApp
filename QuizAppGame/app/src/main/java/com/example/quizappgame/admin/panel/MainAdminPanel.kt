package com.example.quizappgame.admin.panel

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappgame.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import java.util.UUID

class MainAdminPanel : AppCompatActivity() {

    // Initialize the properties
    private lateinit var recycler: RecyclerView
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

        val listOfQuizzes = mutableListOf(QuizModel())
        val adapter = QuizAdapter(listOfQuizzes)

        /*
        Initialize idQuiz ; name ; visible
        After that create declaration of a new quizModel variable, which is an instance of the class
        Add to listOfQuizzes
        */

        addToQuiz.setOnClickListener {
            val idQuiz = UUID.randomUUID().toString()
            val name = quizName.text.toString()
            val visible = chackedVisible.isChecked
            val createdAt: Long = System.currentTimeMillis()
            val quizModel = QuizModel(idQuiz, name, visible, createdAt)
            listOfQuizzes.add(quizModel)
            uploadQuiz(quizModel)
            recycler.adapter = adapter
            recycler.layoutManager = LinearLayoutManager(this)
            loadQuiz()
        }

    }

    private fun loadQuiz() {
        FirebaseFirestore.getInstance().collection("quiz").get()
            .addOnSuccessListener { querySnapshot ->
                val quizList = mutableListOf<QuizModel>()
                for (document in querySnapshot) {
                    val quiz = document.toObject(QuizModel::class.java)
                    quizList.add(quiz)
                }
                val adapter = QuizAdapter(quizList.sortedByDescending { it.getCreatedAt() })
                recycler.adapter = adapter
                recycler.layoutManager = LinearLayoutManager(this)
            }
            .addOnFailureListener { exception ->
                exception.stackTrace

            }
    }

    private fun uploadQuiz(quizModel: QuizModel) {
        FirebaseFirestore.getInstance().collection("quiz").document(quizModel.getQuizID())
            .set(quizModel)
        quizName.text.clear()

    }

    override fun onResume() {
        super.onResume()
        loadQuiz()
        if (FirebaseAuth.getInstance().currentUser == null) {
            FirebaseAuth.getInstance().signInAnonymously()
                .addOnSuccessListener {
                    Log.d("ad", "Works")
                }
                .addOnFailureListener {
                    Log.d("ad", "bad")
                }
        } else {
            loadQuiz()
        }
    }
}