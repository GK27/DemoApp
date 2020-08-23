package com.example.demoapp.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.data.Question

class QuestionsAdapter(
    private var questionList: List<Question>,
    val context: Context

) : RecyclerView.Adapter<QuestionViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): QuestionViewHolder {
        return QuestionViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.inflate_question_item,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: QuestionViewHolder, position: Int) {
        val repo = questionList.get(position)
        holder.bindData(repo, context)

    }

    override fun getItemCount(): Int = questionList.size

    fun getList(): List<Question> = questionList

    fun replaceData(questons: List<Question>) {
        this.questionList = questons
        notifyDataSetChanged()
    }
}