package com.example.quizappgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappgame.databinding.ActivityMainUserBinding
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivityUser : AppCompatActivity() {
    private lateinit var quizAdapter: QuizAdapterUser
    lateinit var recycler: RecyclerView
    private lateinit var bindingActivity: ActivityMainUserBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingActivity = ActivityMainUserBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_user)
        FirebaseApp.initializeApp(this)
        recycler = findViewById(R.id.recyclerUser)
        quizAdapter = QuizAdapterUser(mutableListOf())
        recycler.adapter = quizAdapter
        recycler.layoutManager = LinearLayoutManager(this)



    }

    private fun loadQuiz() {
        if (FirebaseApp.getApps(this).isNotEmpty()) { // check if Firebase app is initialized
            FirebaseFirestore.getInstance().collection("quiz").get()
                .addOnSuccessListener { querySnapshot ->
                    val quizList = mutableListOf<QuizModel>()
                    for (document in querySnapshot) {
                        val quiz = document.toObject(QuizModel::class.java)
                        quizList.add(quiz)
                    }
                    val adapter = QuizAdapterUser(quizList)
                    recycler.adapter = adapter
                    recycler.layoutManager = LinearLayoutManager(this)
                }
                .addOnFailureListener { exception ->
                    exception.stackTrace
                }
        }
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