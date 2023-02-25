package com.example.quizappgame.user.panel

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.quizappgame.admin.panel.QuizModel
import com.example.quizappgame.R

class QuizAdapterUser(
    private var items: List<QuizModel>
) : RecyclerView.Adapter<QuizAdapterUser.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.activity_main_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quizNameToUser = holder.itemView.findViewById<TextView>(R.id.quizNameToUser)
        quizNameToUser.text = items[position].getQuizName()

        val positionOfIDQuiz = items[position]

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, QuestionActivityUser::class.java)
            intent.putExtra("id", positionOfIDQuiz.getQuizID())
            holder.itemView.context.startActivity(intent)
        }


    }


    override fun getItemCount(): Int {
        return items.size
    }


}