package com.video.videoproject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.video.videoproject.register.RegisterActivity;
import com.video.videoproject.util.ToastUtils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginActivity  extends Activity implements OnClickListener{
	private ImageView iv_toregist,iv_phone_clear,iv_pwd_clear,iv_remeber_pwd;
	private EditText et_phone,et_password;
	private Button btn_login;
	private static boolean isCheck = false; 
	
	private static String URL = "http://58.58.197.181:10029/api/v1/video/UserLogin";
	
	@SuppressLint("NewApi") 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		initLayout();
		if(isCheck){
			iv_remeber_pwd.setImageResource(R.drawable.pic_regist_check_remberpwd);
		}
	}

	
	private void initLayout() {
		et_phone = (EditText)findViewById(R.id.et_login_phone);
		et_phone.addTextChangedListener(phoneWatcher);
		et_password = (EditText)findViewById(R.id.et_login_password);
		et_password.addTextChangedListener(pwdWatcher);
		iv_toregist = (ImageView)findViewById(R.id.iv_login_toregist);
		iv_phone_clear = (ImageView)findViewById(R.id.iv_phone_clear);
		iv_pwd_clear = (ImageView)findViewById(R.id.iv_password_clear);
		btn_login = (Button)findViewById(R.id.btn_login);
		iv_remeber_pwd = (ImageView)findViewById(R.id.iv_check_remeberpwd);
		
		iv_remeber_pwd.setOnClickListener(this);
		iv_phone_clear.setOnClickListener(this);
		iv_pwd_clear.setOnClickListener(this);
		btn_login.setOnClickListener(this);
		iv_toregist.setOnClickListener(this);
	}
	/*
	 * 验证号码 手机号 固话均可
	 * 
	 */
	public static boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;
	
		String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
		CharSequence inputStr = phoneNumber;
	
		Pattern pattern = Pattern.compile(expression);
	
		Matcher matcher = pattern.matcher(inputStr);
	
		if (matcher.matches() ) {
			isValid = true;
		}
	
		return isValid;

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.iv_phone_clear:
			et_phone.getText().clear();
			break;
		case R.id.iv_password_clear:
			et_password.getText().clear();
			break;
		case R.id.iv_login_toregist:
			Intent intent = new Intent(LoginActivity.this,
					RegisterActivity.class);
			startActivity(intent);
			break;
		case R.id.iv_check_remeberpwd:
			if(isCheck){
				//提示是否取消收藏
				isCheck = false;
				iv_remeber_pwd.setImageResource(R.drawable.pic_regist_remeberpwd);
			}else {
				isCheck=true;
				//如果已经收藏，则显示收藏后的效果
				iv_remeber_pwd.setImageResource(R.drawable.pic_regist_check_remberpwd);
			}
			break;
		case R.id.btn_login:
			if(!TextUtils.isEmpty(et_phone.getText().toString())){
//				if(isPhoneNumberValid(et_phone.getText().toString())){
					if(!TextUtils.isEmpty(et_password.getText().toString())){
						String result = toLogin(et_phone.getText().toString(),et_password.getText().toString());
						int res = Integer.valueOf(result);
						if(res>0){
							ToastUtils.ToastMessage(getApplicationContext(), "登录成功");
						}else{
							ToastUtils.ToastMessage(getApplicationContext(), "登录失败");
						}
					}else{
						ToastUtils.ToastMessage(getApplicationContext(), "请输入密码");
					}
//				}else{
//					ToastUtils.ToastMessage(getApplicationContext(), "请输入正确的手机号");
//				}
			}else{
				ToastUtils.ToastMessage(getApplicationContext(), "请输入手机号");
			}
			break;
		default:
			break;
		}
	}
	/**
	 * 登录
	 */
	private String toLogin(String phone,String password) {
		HttpURLConnection conn = null;
		PrintWriter printWriter = null;  
        BufferedReader bufferedReader = null;
        StringBuffer responseResult = new StringBuffer();  
		try {
//			http://58.58.197.181:10029/api/v1/video/UserLogin?phone=121&password=123
	        String newUrl = URL +"?username="+ phone + "&password=" + password;
			URL url = new URL(newUrl);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");// 提交模式
	        conn.setRequestProperty("Content-Type", "plain/text; charset=UTF-8");  
	        
	        // 设置通用的请求属性  
            conn.setRequestProperty("accept", "*/*");  
            conn.setRequestProperty("connection", "Keep-Alive");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);
            conn.connect();
            
//            DataOutputStream out = new DataOutputStream(conn
//                    .getOutputStream());
//            // The URL-encoded contend
//            // 正文，正文内容其实跟get的URL中 '? '后的参数字符串一致
//            String content = "username=" + URLEncoder.encode(phone, "UTF-8");
//            content +="&password="+URLEncoder.encode(password, "UTF-8");;
//            // DataOutputStream.writeBytes将字符串中的16位的unicode字符以8位的字符形式写到流里面
//            out.writeBytes(content);
//
//            out.flush();
//            out.close(); 
            
            // 根据ResponseCode判断连接是否成功  
            int responseCode = conn.getResponseCode();  
            if (responseCode != 200) {  
            	System.out.println("Not Success");
            } else {  
            	System.out.println("Success");
            }  
            // 定义BufferedReader输入流来读取URL的ResponseData  
            bufferedReader = new BufferedReader(new InputStreamReader(  
                    conn.getInputStream()));  
            String line;  
            while ((line = bufferedReader.readLine()) != null) {  
                responseResult.append(line);  
                System.out.println(line);
            }  
			/* 关闭DataOutputStream */
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			conn.disconnect();  
            try {  
                if (printWriter != null) {  
                    printWriter.close();  
                }  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
            } catch (IOException ex) {  
                ex.printStackTrace();  
            } 
		}
		return responseResult.toString();
	}
	private TextWatcher phoneWatcher = new TextWatcher() {  
	          
        @Override 
        public void beforeTextChanged(CharSequence s, int start, int count,  
                int after) {  
            // TODO Auto-generated method stub  
        }  
 
         @Override    
        public void onTextChanged(CharSequence s, int start, int before,     
                int count) {     
        	if (s.length() > 0) {
        		if(et_password.getText().length()>0){
        			btn_login.setEnabled(true);
            		btn_login.setBackgroundResource(R.drawable.pic_regist_normal_button);
        		}
     			iv_phone_clear.setVisibility(View.VISIBLE);
     		} else {
 				btn_login.setEnabled(false);
 				btn_login.setBackgroundResource(R.drawable.pic_btn_disable);
     			iv_phone_clear.setVisibility(View.GONE);
     		}
        }

		@Override
		public void afterTextChanged(Editable arg0) {
			
		}                    
    };  
    private TextWatcher pwdWatcher = new TextWatcher() {  
        
        @Override 
        public void beforeTextChanged(CharSequence s, int start, int count,  
                int after) {  
            // TODO Auto-generated method stub  
        }  
 
         @Override    
        public void onTextChanged(CharSequence s, int start, int before,     
                int count) {     
        	 if (s.length() > 0) {
        		 if(et_phone.getText().length()>0){
        			 btn_login.setEnabled(true);
             		 btn_login.setBackgroundResource(R.drawable.pic_regist_normal_button);
        		 }
     			 iv_pwd_clear.setVisibility(View.VISIBLE);
     		} else {
 				 btn_login.setEnabled(false);
 				 btn_login.setBackgroundResource(R.drawable.pic_btn_disable);
     			 iv_pwd_clear.setVisibility(View.GONE);
     		}
        }

		@Override
		public void afterTextChanged(Editable arg0) {
			
		}                    
    };  
}
