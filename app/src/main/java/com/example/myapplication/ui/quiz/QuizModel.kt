package com.example.myapplication.ui.quiz

class QuizModel(private var mQuestion: Int, private var mAnswer: Boolean) {
    fun getmQuestion(): Int {
        return mQuestion
    }

    fun setmQuestion(mQuestion: Int) {
        this.mQuestion = mQuestion
    }

    fun ismAnswer(): Boolean {
        return mAnswer
    }

    fun setmAnswer(mAnswer: Boolean) {
        this.mAnswer = mAnswer
    }
}