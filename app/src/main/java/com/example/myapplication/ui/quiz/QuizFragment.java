package com.example.myapplication.ui.quiz;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.myapplication.R;
import com.example.myapplication.databinding.FragmentQuizBinding;

public class QuizFragment extends Fragment {

    private FragmentQuizBinding binding;
    private TextView mTxtQuestion;
    private Button btnF;
    private Button btnT;
    private int mQuestionIndex = 0;
    private int mQuizQuestion;
    private ProgressBar mProgressBar;
    private TextView mQuizstatsTextView;
    private int mUserScore;

    private final QuizModel[] questionCollection = new QuizModel[]{
            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, true),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, true),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, true),
    };

    final int USER_PROGRESS = (int) Math.ceil(100.0 / questionCollection.length);

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentQuizBinding.inflate(inflater, container, false);

        mTxtQuestion = binding.txtQuestion;
        QuizModel q1 = questionCollection[mQuestionIndex];
        mQuizQuestion = q1.getmQuestion();
        mTxtQuestion.setText(mQuizQuestion);
        mProgressBar = binding.quizPB;
        mQuizstatsTextView = binding.txtQuizStats;

        btnT = binding.btnTrue;
        btnT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(true);
                changeQuestionOnButtonClick();

            }
        });

        btnF = binding.btnFalse;
        btnF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                evaluateUserAnswer(false);
                changeQuestionOnButtonClick();

            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    private void changeQuestionOnButtonClick() {
        mQuestionIndex = (mQuestionIndex + 1) % 6;
        if (mQuestionIndex == 0) {
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(getActivity());
            quizAlert.setCancelable(false);
            quizAlert.setTitle("Quiz is Finished");
            quizAlert.setMessage("Your Score is " + mUserScore);
            quizAlert.setPositiveButton("Finish the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    getActivity().finish();
                }
            });
            quizAlert.show();
        } else {
            mQuizQuestion = questionCollection[mQuestionIndex].getmQuestion();
            mTxtQuestion.setText(mQuizQuestion);
            mProgressBar.incrementProgressBy(USER_PROGRESS);
            mQuizstatsTextView.setText(mUserScore + "" + "/ 6");
        }
    }

    private void evaluateUserAnswer(boolean userGuess) {
        boolean currentQuestionAnswer = questionCollection[mQuestionIndex].ismAnswer();
        if (currentQuestionAnswer == userGuess) {
            mUserScore++;
        } else {
        }
    }

}
