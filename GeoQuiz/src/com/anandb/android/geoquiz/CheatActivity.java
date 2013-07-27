package com.anandb.android.geoquiz;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CheatActivity extends Activity {
	public static String IS_TRUE_ANSWER_KEY = "com.anandb.android.geoquiz.CheatActivity.true_answer_key";
	public static String IS_CHEATER_KEY = "com.anandb.android.geoquiz.CheatActivity.cheater_key";
	private static String ANSWER_SHOWN_KEY = "answer_shown";
	private Button mShowAnswerButton;
	private TextView mAnswerTextView;
	private boolean mTrueAnswer, mAnswerShown;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		if (savedInstanceState != null) {
			mAnswerShown = savedInstanceState.getBoolean(ANSWER_SHOWN_KEY, false);
		}
		
		setContentView(R.layout.activity_cheat);
	
		mTrueAnswer = getIntent().getBooleanExtra(IS_TRUE_ANSWER_KEY, false);
		
		mShowAnswerButton = (Button) findViewById(R.id.showAnswerButton);
		mAnswerTextView = (TextView) findViewById(R.id.answer_text_view);
		
		if (mAnswerShown) {
			if (mTrueAnswer) {
				mAnswerTextView.setText(R.string.correct_toast);
			} else {
				mAnswerTextView.setText(R.string.incorrect_toast);
			}
		}
		
		setAnswerShownResult(mAnswerShown);
		
		mShowAnswerButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (mTrueAnswer) {
					mAnswerTextView.setText(R.string.correct_toast);
				} else {
					mAnswerTextView.setText(R.string.incorrect_toast);
				}
				mAnswerShown = true;
				setAnswerShownResult(true);
			}
		});
	}
	
	public void setAnswerShownResult(boolean answerShown) {
		Intent i = new Intent();
		i.putExtra(IS_CHEATER_KEY, answerShown);
		setResult(RESULT_OK, i);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putBoolean(ANSWER_SHOWN_KEY, mAnswerShown);
	}
}
