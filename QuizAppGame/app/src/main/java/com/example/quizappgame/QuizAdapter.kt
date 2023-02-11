package com.example.quizappgame

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class QuizAdapter(
    private var items: List<QuizModel>
) : RecyclerView.Adapter<QuizAdapter.ViewHolder>() {


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.admin_panel_test, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quizName2 = holder.itemView.findViewById<TextView>(R.id.quizName2)
        quizName2.text = items[position].getQuizName()
        val chackedVisible = holder.itemView.findViewById<CheckBox>(R.id.chackedVisible)
        chackedVisible.isChecked = items[position].getVisible()
    }
}


