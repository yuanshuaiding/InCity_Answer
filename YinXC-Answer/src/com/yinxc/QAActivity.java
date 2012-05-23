package com.yinxc;

import java.io.InputStream;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class QAActivity extends Activity {

	private View backBtn;
	private ListView list;
	private List<Question> data;
	private LayoutInflater inflater;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		DataHelper.correctNum=0;
		DataHelper.answeredNum=0;
		setContentView(R.layout.question);
		backBtn = findViewById(R.id.back);
		backBtn.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
		inflater=getLayoutInflater();
		InputStream in=getResources().openRawResource(R.raw.qa);
		data=XmlPullParseUtil.parseXmlFile(in);
		DataHelper.questionList=data;
		list = (ListView) findViewById(R.id.list);
		BaseAdapter adapter = new BaseAdapter() {
			
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				ViewGroup vg=(ViewGroup) inflater.inflate(R.layout.list_item, null);
				TextView tv_index=(TextView)vg.findViewById(R.id.txt_index);
				tv_index.setText((position+1)+". ");
				TextView tv=(TextView)vg.findViewById(R.id.question);
				tv.setText(data.get(position).getContent());
				return vg;
			}
			
			@Override
			public long getItemId(int position) {
				return position;
			}
			
			@Override
			public Object getItem(int position) {
				return position;
			}
			
			@Override
			public int getCount() {
				return data.size();
			}
		};
		list.setAdapter(adapter);
		list.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent intent=new Intent();
				intent.putExtra("questionId", position);
				intent.setClass(getApplicationContext(), QuestionDetail.class);
				startActivity(intent);
			}
		});
	}
	
}
