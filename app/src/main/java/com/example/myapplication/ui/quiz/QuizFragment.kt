package com.example.myapplication.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentQuizBinding

class QuizFragment : Fragment() {
    private var binding: FragmentQuizBinding? = null
    private var mTxtQuestion: TextView? = null
    private var btnF: Button? = null
    private var btnT: Button? = null
    private var mQuestionIndex = 0
    private var mQuizQuestion = 0
    private var mProgressBar: ProgressBar? = null
    private var mQuizstatsTextView: TextView? = null
    private var mUserScore = 0
    private val questionCollection = arrayOf(
            QuizModel(R.string.q1, true),
            QuizModel(R.string.q2, true),
            QuizModel(R.string.q3, true),
            QuizModel(R.string.q4, true),
            QuizModel(R.string.q5, true),
            QuizModel(R.string.q6, true))
    val USER_PROGRESS = Math.ceil(100.0 / questionCollection.size).toInt()
    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentQuizBinding.inflate(inflater, container, false)
        mTxtQuestion = binding!!.txtQuestion
        val q1 = questionCollection[mQuestionIndex]
        mQuizQuestion = q1.getmQuestion()
        mTxtQuestion!!.setText(mQuizQuestion)
        mProgressBar = binding!!.quizPB
        mQuizstatsTextView = binding!!.txtQuizStats
        btnT = binding!!.btnTrue
        btnT!!.setOnClickListener {
            evaluateUserAnswer(true)
            changeQuestionOnButtonClick()
        }
        btnF = binding!!.btnFalse
        btnF!!.setOnClickListener {
            evaluateUserAnswer(false)
            changeQuestionOnButtonClick()
        }
        return binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun changeQuestionOnButtonClick() {
        mQuestionIndex = (mQuestionIndex + 1) % 6
        if (mQuestionIndex == 0) {
            val quizAlert = AlertDialog.Builder(activity!!)
            quizAlert.setCancelable(false)
            quizAlert.setTitle("Quiz is Finished")
            quizAlert.setMessage("Your Score is $mUserScore")
            quizAlert.setPositiveButton("Finish the Quiz") { dialogInterface, i -> activity!!.finish() }
            quizAlert.show()
        } else {
            mQuizQuestion = questionCollection[mQuestionIndex].getmQuestion()
            mTxtQuestion!!.setText(mQuizQuestion)
            mProgressBar!!.incrementProgressBy(USER_PROGRESS)
            mQuizstatsTextView!!.text = "$mUserScore/ 6"
        }
    }

    private fun evaluateUserAnswer(userGuess: Boolean) {
        val currentQuestionAnswer = questionCollection[mQuestionIndex].ismAnswer()
        if (currentQuestionAnswer == userGuess) {
            mUserScore++
        } else {
        }
    }
}