package com.example.quizappgame

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class QuizAdapter(private val items: List<QuizModel>) :
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context)
            .inflate(R.layout.admin_panel_test, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quizModel = items[position]
        holder.bindView(quizModel)
    }

    override fun getItemCount() = items.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        private val addToQuiz: Button
        private val quizName: EditText
        private val chackedVisible: CheckBox
        private val quizName2: TextView

        init {
            addToQuiz = itemView.findViewById(R.id.addToQuiz)
            quizName = itemView.findViewById(R.id.quizName)
            chackedVisible = itemView.findViewById(R.id.chackedVisible)
            quizName2 = itemView.findViewById(R.id.quizName2)
        }

        fun bindView(quizModel: QuizModel) {
            quizName2.text = quizModel.getQuizName()
        }
    }
}
