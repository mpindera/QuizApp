package com.example.quizappgame

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.coroutines.coroutineContext

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

        val positionOfIDQuiz = items[position]
        val buttonUpdate = holder.itemView.findViewById<Button>(R.id.updateQuiz)
        val buttonDelete = holder.itemView.findViewById<Button>(R.id.deleteQuiz)

        holder.itemView.setOnClickListener {
            val intent = Intent(holder.itemView.context, QuestionActivity::class.java)
            intent.putExtra("id", positionOfIDQuiz.getQuizID())
            holder.itemView.context.startActivity(intent)
        }


        buttonUpdate.setOnClickListener {
            FirebaseFirestore.getInstance().collection("quiz")
                .document(positionOfIDQuiz.getQuizID())
                .update("quizName", quizName2.text.toString(), "visible", chackedVisible.isChecked)
            Toast.makeText(
                holder.itemView.context, "Setting has been updated", Toast.LENGTH_SHORT
            ).show()
        }

        buttonDelete.setOnClickListener {
            FirebaseFirestore.getInstance().collection("quiz")
                .document(positionOfIDQuiz.getQuizID()).delete()
            Toast.makeText(
                holder.itemView.context, "Quiz has been deleted", Toast.LENGTH_SHORT
            ).show()

            val updatedQuizList = items.filter { it.getQuizID() != positionOfIDQuiz.getQuizID() }

            items = updatedQuizList
            notifyDataSetChanged()
        }
    }

}


