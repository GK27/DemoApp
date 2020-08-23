package com.example.demoapp.ui

import android.content.Context
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.data.Question
import com.example.demoapp.data.Value
import com.example.demoapp.util.Constant.Companion.DROPDOWN
import com.example.demoapp.util.Constant.Companion.EMAIL
import com.example.demoapp.util.Constant.Companion.IMAGE
import com.example.demoapp.util.Constant.Companion.MULTI_SELECT
import com.example.demoapp.util.Constant.Companion.RADIO
import com.example.demoapp.util.Constant.Companion.SELECT
import com.example.demoapp.util.Constant.Companion.TEXT
import kotlinx.android.synthetic.main.inflate_question_item.view.*

class QuestionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    private lateinit var question: Question
    private var checkBoxesList: MutableList<CheckBox>? = null
    fun bindData(ques: Question, context: Context) {
        this.question = ques
        checkBoxesList = mutableListOf()
        with(ques) {
            itemView.questionTxt.text = question
            if (!answers.isNullOrEmpty()) {
                itemView.answerTxt.visibility = View.VISIBLE
                itemView.answerTxt.text = answers
            } else {
                when (type) {
                    EMAIL, TEXT -> {
                        itemView.editText.setText("")
                        itemView.editText.visibility = View.VISIBLE
                        itemView.radioGroup.visibility = View.GONE
                        itemView.spinner.visibility = View.GONE
                        itemView.imageView.visibility = View.GONE
                        itemView.checkBoxLay.visibility = View.GONE
                        itemView.editText.addTextChangedListener(object : TextWatcher {
                            override fun beforeTextChanged(
                                s: CharSequence,
                                start: Int,
                                count: Int,
                                after: Int
                            ) {
                            }

                            override fun onTextChanged(
                                s: CharSequence,
                                start: Int,
                                before: Int,
                                count: Int
                            ) {
                            }

                            override fun afterTextChanged(aValue: Editable) {
                                answers = aValue.toString()
                            }
                        })
                    }

                    RADIO -> {
                        itemView.radioGroup.visibility = View.VISIBLE
                        itemView.editText.visibility = View.GONE
                        itemView.imageView.visibility = View.GONE
                        itemView.spinner.visibility = View.GONE
                        itemView.checkBoxLay.visibility = View.GONE
                        itemView.radioGroup.setOnCheckedChangeListener { group, checkedId ->
                            val aSelectRD = group.findViewById(checkedId) as RadioButton
                            answers = aSelectRD.text.toString()
                        }
                    }
                    DROPDOWN -> {
                        itemView.spinner.visibility = View.VISIBLE
                        itemView.radioGroup.visibility = View.GONE
                        itemView.editText.visibility = View.GONE
                        itemView.checkBoxLay.visibility = View.GONE
                        itemView.imageView.visibility = View.GONE
                        if (!values.isNullOrEmpty()) {
                            for (i in values!!) {
                                if (i.value == SELECT)
                                    values?.removeAt(0)
                                break
                            }
                            values!!.add(0, Value())
                            val dataAdapter: ArrayAdapter<Value> =
                                ArrayAdapter<Value>(context, R.layout.simple_spinner_item, values!!)
                            itemView.spinner.adapter = dataAdapter
                            itemView.spinner.onItemSelectedListener = object :
                                AdapterView.OnItemSelectedListener {
                                override fun onItemSelected(
                                    spinner: AdapterView<*>, v: View,
                                    pos: Int, arg3: Long
                                ) {
                                    if (pos == 0) {
                                        answers = ""
                                    } else
                                        answers = values!![pos].value!!
                                }

                                override fun onNothingSelected(arg0: AdapterView<*>?) {
                                    // TODO Auto-generated method stub
                                }
                            }
                        } else {
                        }
                    }
                    IMAGE -> {
                        itemView.imageView.visibility = View.VISIBLE
                        itemView.radioGroup.visibility = View.GONE
                        itemView.editText.visibility = View.GONE
                        itemView.spinner.visibility = View.GONE
                        itemView.checkBoxLay.visibility = View.GONE
                        itemView.imageView.settings.loadWithOverviewMode = true
                        itemView.imageView.settings.useWideViewPort = true
                        url?.let { itemView.imageView.loadUrl(it) }

                    }
                    MULTI_SELECT -> {
                        itemView.checkBoxLay.visibility = View.VISIBLE
                        itemView.radioGroup.visibility = View.GONE
                        itemView.editText.visibility = View.GONE
                        itemView.imageView.visibility = View.GONE
                        itemView.spinner.visibility = View.GONE
                        itemView.checkBoxLay.removeAllViews()

                        if (!values.isNullOrEmpty()) {
                            for (i in values!!) {
                                val cb = CheckBox(context)
                                cb.text = i.value
                                checkBoxesList?.add(cb)
                                itemView.checkBoxLay.addView(cb)
                            }
                        }
                        if (!checkBoxesList.isNullOrEmpty()) {
                            for (i in checkBoxesList!!) {
                                i.setOnCheckedChangeListener(CompoundButton.OnCheckedChangeListener { _, isChecked ->
                                    if (isChecked) {
                                        if (!answers.isNullOrEmpty())
                                            answers = answers + "," + i.text.toString()
                                        else {
                                            answers = i.text.toString()
                                        }
                                    } else {
                                        answers = ""
                                    }
                                })
                            }
                        } else {
                        }

                    }
                    //

                    else -> {
                    }
                }
            }
        }
    }
}