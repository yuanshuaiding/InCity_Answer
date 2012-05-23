package com.yinxc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ResultActivity extends Activity {

	private TextView txt_total;
	private TextView txt_answered;
	private TextView txt_correct;
	private TextView txt_result;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent=getIntent();
		int total=intent.getExtras().getInt("total");
		int answered=intent.getExtras().getInt("answered");
		int correct=intent.getExtras().getInt("correct");
		double result=(correct*100)/total;
		setContentView(R.layout.result);
		txt_total=(TextView)findViewById(R.id.txt_total);
		txt_answered=(TextView)findViewById(R.id.txt_answered);
		txt_correct=(TextView)findViewById(R.id.txt_correct);
		txt_result=(TextView)findViewById(R.id.txt_result);
		txt_total.setText("��Ŀ������ "+total);
		txt_answered.setText("�Ѵ�����Ŀ�� "+answered);
		txt_correct.setText("�����Ŀ�� "+correct);
		txt_result.setText("��ȷ�ʣ� "+result+"%");
	}
}
