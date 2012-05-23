package com.yinxc;

import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegistActivity extends Activity {
	private String action="http://172.16.104.251:8082/regist/";
	private EditText etv_loginName;
	private EditText etv_password;
	private EditText etv_repassword;
	private EditText etv_email;
	private Button btn_regist;
	private HttpPost httpRequest;
	private HttpResponse httpResponse;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.regist);
		findViews();
		setListeners();
	}

	private void findViews() {
		etv_loginName=(EditText)findViewById(R.id.etxt_loginName);
		etv_password=(EditText)findViewById(R.id.etxt_password);
		etv_repassword=(EditText)findViewById(R.id.etxt_repassword);
		etv_email=(EditText)findViewById(R.id.etxt_email);
		btn_regist=(Button)findViewById(R.id.btn_regist);
	}

	private void setListeners() {
		btn_regist.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				accountRegist();
			}
		});
	}

	private void accountRegist() {
		String loginName=etv_loginName.getText().toString().trim();
		String password=etv_password.getText().toString().trim();
		String repassword=etv_repassword.getText().toString().trim();
		String email=etv_email.getText().toString().trim();
		boolean flag=validate(loginName,password,repassword,email);
		if(flag){
			/*建立HttpPost连接*/ 
	        httpRequest=new HttpPost(action); 
	        /*Post传送变数必须用NameValuePair[]阵列储存*/ 
	        ArrayList<NameValuePair> params = new ArrayList<NameValuePair>(); 
	        params.add(new BasicNameValuePair("loginName",loginName)); 
	        params.add(new BasicNameValuePair("password", password));
	        params.add(new BasicNameValuePair("repassword", repassword));
	        params.add(new BasicNameValuePair("email", email));
	        try { 
	            //发出HTTP request 
	            httpRequest.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8)); 
	            //取得HTTP response 
	            httpResponse=new DefaultHttpClient().execute(httpRequest); 
	            //若状态码为200 
	            if(httpResponse.getStatusLine().getStatusCode()==200){ 
	                //取出响应应字串 
	                String strResult=EntityUtils.toString(httpResponse.getEntity()); 
	                Toast.makeText(getApplicationContext(), strResult, 2000).show();
	            }else{ 
	            	Toast.makeText(getApplicationContext(), "注册失败", 1000).show();
	            } 
	        } catch (Exception e) { 
	        	e.printStackTrace();
	        } 
		}else{
			Toast.makeText(getApplicationContext(), "输入有误！", 1000).show();
		}
	}

	private boolean validate(String loginName, String password, String repassword,
			String email) {
		//TODO:...
		return true;
	}
	
}
