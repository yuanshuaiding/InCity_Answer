package com.yinxc;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionDetail extends Activity {

	private TextView txt_question;
	private RadioGroup choiceGroup;
	private TextView toptitle;
	private ImageButton btn_pre;
	private ImageButton btn_next;
	private int index;
	private ImageButton btn_answer;
	private Button btn_check;
	private Question question;
	private Button btn_back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.questiondetail);
		Intent intent = getIntent();
		final int questionId = intent.getExtras().getInt("questionId");
		// 选中的问题
		question = DataHelper.questionList.get(questionId);
		index = questionId;
		txt_question = (TextView) findViewById(R.id.txt_question);
		ViewGroup topguid = (ViewGroup) getLayoutInflater().inflate(R.layout.topguid, null);
		toptitle = (TextView) topguid.findViewById(R.id.txt_title);
		btn_back=(Button)topguid.findViewById(R.id.back);
		btn_back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		btn_answer = (ImageButton) findViewById(R.id.btn_answer);
		btn_answer.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				int checkedid = choiceGroup.getCheckedRadioButtonId();

				if (!question.isAnswered()) {
					DataHelper.answeredNum++;
					question.setAnswered(true);
					if (question.getAnswer().equals(checkedid + "")) {
						DataHelper.correctNum++;
					}
					//答完题后会自动跳到下一题
					buildQuestion(++index);
				} else {
					Toast.makeText(getApplicationContext(), "该题已答过！", 1000)
							.show();
				}
			}
		});
		btn_pre = (ImageButton) findViewById(R.id.btn_pre);
		btn_pre.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				buildQuestion(--index);
			}
		});
		btn_next = (ImageButton) findViewById(R.id.btn_next);
		btn_next.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				buildQuestion(++index);
			}
		});
		btn_check = (Button) findViewById(R.id.btn_check);
		btn_check.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.putExtra("total", DataHelper.questionList.size());
				intent.putExtra("answered", DataHelper.answeredNum);
				intent.putExtra("correct", DataHelper.correctNum);
				intent.setClass(getApplicationContext(), ResultActivity.class);
				startActivity(intent);
			}
		});
		choiceGroup = (RadioGroup) findViewById(R.id.rdg_choice);

		buildQuestion(index);
	}

	// 显示问答中的指定问题及选项
	public void buildQuestion(int index) {

		if (index >= 0 && index < DataHelper.questionList.size()) {
			question = DataHelper.questionList.get(index);
			txt_question.setText(question.getContent());
			toptitle.setText((index + 1) + "/" + DataHelper.questionList.size());
			int radioId = 1;
			if (choiceGroup.getChildCount() > 0) {
				choiceGroup.removeAllViews();
				choiceGroup.clearCheck();
			}
			for (String cstr : question.getChoices()) {
				RadioButton child = new RadioButton(getApplicationContext());
				child.setId(radioId++);
				child.setText(cstr);
				child.setTextColor(Color.parseColor("#000000"));
				choiceGroup.addView(child);
			}
		} else {
			if (index < 0) {
				this.index = 0;
			}
			if (index >= DataHelper.questionList.size()) {
				this.index = DataHelper.questionList.size() - 1;
				// 点击到最后一题时提示已经到最后一题，并显示查看结果按钮
				btn_check.setVisibility(View.VISIBLE);
			}
		}

	}

}
