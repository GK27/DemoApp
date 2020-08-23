package com.example.demoapp.ui

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.example.demoapp.R
import com.example.demoapp.data.Question
import com.example.demoapp.util.Constant.Companion.DROPDOWN
import com.example.demoapp.util.Constant.Companion.EMAIL
import com.example.demoapp.util.Constant.Companion.INDEX_VALUE
import com.example.demoapp.util.Constant.Companion.MANDATORY
import com.example.demoapp.util.Constant.Companion.MULTI_SELECT
import com.example.demoapp.util.Constant.Companion.NETWORK_CONNECTION_CHECK
import com.example.demoapp.util.Constant.Companion.RADIO
import com.example.demoapp.util.Constant.Companion.TEXT
import com.example.demoapp.util.isEmailValid
import com.example.demoapp.util.isNetworkAvailable
import com.example.demoapp.viewmodels.FormViewModel
import com.example.demoapp.viewmodels.getViewModelFactory
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_form_page.*


class FormFragment : Fragment() {

    private val viewModel by viewModels<FormViewModel> { getViewModelFactory() }

    private var mAdapter: QuestionsAdapter? = null

    private var mAnswerAdapter: QuestionsAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_form_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setRefreshLayout()
        loadValues()
        clickListener()
    }

    private fun clickListener() {
        submit.setOnClickListener {
            val list = mAdapter?.getList()
            if (list != null) {
                for (item in list!!) {
                    if (item.mandatory == MANDATORY) {
                        when (item.type) {
                            EMAIL, TEXT -> {
                                if (item.type == TEXT) {
                                    if (item.answers == "") {
                                        Toast.makeText(
                                            requireContext(),
                                            "${getString(R.string.alert_enter_the)}${item.question}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        return@setOnClickListener
                                    }
                                } else {
                                    if (item.answers == "") {
                                        Toast.makeText(
                                            requireContext(),
                                            "${getString(R.string.alert_enter_the)}${item.question}",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        return@setOnClickListener
                                    } else if (!isEmailValid(item.answers!!)) {
                                        Toast.makeText(
                                            requireContext(),
                                            getString(R.string.alert_valid_mail),
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        return@setOnClickListener
                                    }
                                }
                            }
                            RADIO -> {
                                if (item.answers == "") {
                                    Toast.makeText(
                                        requireContext(),
                                        "${getString(R.string.alert_choose)}${item.question}",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@setOnClickListener
                                }
                            }
                            MULTI_SELECT -> {
                                if (item.answers == "") {
                                    Toast.makeText(
                                        requireContext(),
                                        getString(R.string.alert_choose_any),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@setOnClickListener
                                }
                            }
                            DROPDOWN -> {
                                if (item.answers == "") {
                                    Toast.makeText(
                                        requireContext(),
                                        getString(R.string.alert_select_any),
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    return@setOnClickListener
                                }
                            }
                        }
                    }


                }
                // val aITem = Gson().toJson(list)
                // Toast.makeText(requireContext(), aITem, Toast.LENGTH_SHORT).show()
                showAnswerAlert(answer = list)
            }


            //list
        }
    }

    private fun loadValues() {

        progressbar.visibility = VISIBLE

        mAdapter = activity?.let {
            QuestionsAdapter(
                questionList = emptyList(),
                context = it
            )
        }
        if (context?.let { isNetworkAvailable(it) }!!) {
            viewModel.fetchReposForm(forceRefresh = false, index = INDEX_VALUE)

        } else
            Snackbar.make(repos_list, NETWORK_CONNECTION_CHECK, Snackbar.LENGTH_SHORT)
                .show()

        //SetValue
        repos_list.adapter = activity?.let {
            mAdapter
        }

        //Retry
        val retryListener: View.OnClickListener = View.OnClickListener {
            viewModel.fetchReposForm(forceRefresh = true, index = INDEX_VALUE)
        }
        viewModel.error.observe(viewLifecycleOwner, Observer { error ->
            Snackbar.make(repos_list, error, Snackbar.LENGTH_SHORT)
                .setAction(R.string.retry, retryListener).show()
        })
        viewModel.success.observe(viewLifecycleOwner, Observer { status ->
            progressbar.visibility = GONE
            submit.visibility = VISIBLE
        })

        viewModel.reQue.observe(viewLifecycleOwner, Observer { reQue ->
            refresh_layout.isRefreshing = false
            mAdapter?.replaceData(reQue)
        })
    }

    private fun setRefreshLayout() {
        progressbar.visibility = GONE
        refresh_layout.setColorSchemeColors(
            ContextCompat.getColor(requireActivity(), R.color.colorPrimary),
            ContextCompat.getColor(requireActivity(), R.color.colorAccent),
            ContextCompat.getColor(requireActivity(), R.color.colorPrimaryDark)
        )
        refresh_layout.setOnRefreshListener {
            viewModel.fetchReposForm(forceRefresh = true, index = INDEX_VALUE)
        }
    }

    private fun showAnswerAlert(answer: List<Question>) {
        val builder = AlertDialog.Builder(requireContext())
        val inflater = layoutInflater
        builder.setTitle(getString(R.string.ans_title))
        val dialogLayout = inflater.inflate(R.layout.inflate_popup_answer, null)
        val answerRC = dialogLayout.findViewById<RecyclerView>(R.id.answer_list)
        builder.setView(dialogLayout)

        //Answer item
        mAnswerAdapter = activity?.let {
            QuestionsAdapter(
                questionList = answer,
                context = it
            )
        }
        answerRC.adapter = mAnswerAdapter

        builder.setOnDismissListener(DialogInterface.OnDismissListener {
            //Rest value
            answer.forEach { it.answers = "" }
            mAdapter?.replaceData(answer)
        })

        builder.show()


    }
}