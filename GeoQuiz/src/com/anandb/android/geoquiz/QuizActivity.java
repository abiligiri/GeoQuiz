package com.anandb.android.geoquiz;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity {
	private Button mTrueButton;
	private Button mFalseButton;
	private int mCurrentQuestion;
	private Button mNextButton;
	private TextView mQuestionView;
	private static final String TAG = "QuizActivity";
	private static final String CURRENT_INDEX = "CurrentIndex";
	
	private TrueFalse mQuestionBank[] = new TrueFalse[] {
		new TrueFalse(R.string.question_oceans, true),
		new TrueFalse(R.string.question_asia, true),
		new TrueFalse(R.string.question_mideast, false),
		new TrueFalse(R.string.question_africa, false),
		new TrueFalse(R.string.question_america, true)
	};
	
	private void checkAnswer(boolean userResponse) {
		if (userResponse == mQuestionBank[mCurrentQuestion].isTrueQuestion()) {
			Toast.makeText(QuizActivity.this, R.string.correct_toast, Toast.LENGTH_SHORT).show();
		} else {
			Toast.makeText(QuizActivity.this, R.string.incorrect_toast, Toast.LENGTH_SHORT).show();
		}
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d(TAG, "onCreate");
		setContentView(R.layout.activity_quiz);
		
		if (savedInstanceState != null) {
			mCurrentQuestion = savedInstanceState.getInt(CURRENT_INDEX, 0);
		} else {
			mCurrentQuestion = 0;
		}
		
		mTrueButton = (Button) findViewById(R.id.true_button);
		mFalseButton = (Button) findViewById(R.id.false_button);
		mQuestionView = (TextView) findViewById(R.id.question_view);
		mNextButton = (Button) findViewById(R.id.next_button);
		
		mQuestionView.setText(mQuestionBank[mCurrentQuestion].getQuestion());
		
		mTrueButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkAnswer(true);
				
			}
		});
		
		mFalseButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				checkAnswer(false);
			}
		});
		
		mNextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mCurrentQuestion = (mCurrentQuestion + 1) % mQuestionBank.length;
				mQuestionView.setText(mQuestionBank[mCurrentQuestion].getQuestion());
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.quiz, menu);
		return true;
	}
	
	@Override
	public void onStart() {
		Log.d(TAG, "onStart");
		super.onStart();
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.d(TAG, "onResume");
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.d(TAG, "onPause");
	}
	
	@Override
	public void onStop() {
		super.onStop();
		Log.d(TAG, "onStop");
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG,"onDestroy");
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(CURRENT_INDEX, mCurrentQuestion);
	}
}
