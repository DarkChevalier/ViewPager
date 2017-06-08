package com.video.videoproject.register;

//import static cn.smssdk.framework.utils.R.getStringRes;
import static cn.smssdk.framework.utils.R.getStringRes;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.video.videoproject.LoginActivity;
import com.video.videoproject.R;
import com.video.videoproject.Data.Data;
import com.video.videoproject.util.ToastUtils;

public class RegisterActivity extends Activity implements OnClickListener{
	private ImageView iv_yanzheng,iv_check_xieyi,iv_back,iv_clear_phone,iv_clear_yanzheng,iv_clear_pwd,iv_clear_repwd;		//获取验证码
	private EditText et_phone,et_identity,et_password,et_repassword;		//手机号
	private TextView tv_identity,tv_tologin;
	private Button btn_zhuce;
	private String phString;
	private static String URL = "http://58.58.197.181:10029/api/v1/video/ConnectSSLAndroid";  //注册的方法
	
	public final static String APPKEY = "9da93e9f5e66";
	public final static String APPSECRET = "ca53c560cd104f11cd2f804f5168eefd";
	//ConnectSSLAndroid(string phone, string phoneCode, string password)
	private static boolean isCheck = false; 
	
	@SuppressLint("NewApi") 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main_regist);
		
		StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);
		
		initLayout();
		SMSSDK.initSDK(this, APPKEY, APPSECRET);
		EventHandler eh=new EventHandler(){

			@Override
			public void afterEvent(int event, int result, Object data) {
				
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handler.sendMessage(msg);
			}
			
		};
		SMSSDK.registerEventHandler(eh);
		if(isCheck){
			iv_check_xieyi.setImageResource(R.drawable.pic_regist_check_remberpwd);
		}
	}
	
	/**
	 * 初始化布局控件
	 */
	private void initLayout() {
		iv_yanzheng = (ImageView)findViewById(R.id.iv_yanzhengma_num);
		et_phone = (EditText)findViewById(R.id.et_write_phonenum);
		tv_identity = (TextView)findViewById(R.id.tv_unreceive_identifynum);	//验证码
		btn_zhuce = (Button)findViewById(R.id.btn_next);		//注册
		iv_check_xieyi = (ImageView)findViewById(R.id.iv_tocheck_xieyi);		//同意协议
		et_identity = (EditText)findViewById(R.id.et_identity);
		et_password = (EditText)findViewById(R.id.et_password);
		et_repassword = (EditText)findViewById(R.id.et_repassword);
		iv_back = (ImageView)findViewById(R.id.iv_title_back);
		tv_tologin = (TextView)findViewById(R.id.tv_video_tologin);
		
		iv_back.setOnClickListener(this);
		iv_check_xieyi.setOnClickListener(this);
		btn_zhuce.setOnClickListener(this);
		iv_yanzheng.setOnClickListener(this);
		tv_tologin.setOnClickListener(this);
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
		case R.id.iv_title_back:
			RegisterActivity.this.finish();
			break;
		case R.id.iv_yanzhengma_num:
			if(!TextUtils.isEmpty(et_phone.getText().toString())){
				if(isPhoneNumberValid(et_phone.getText().toString())){
					SMSSDK.getVerificationCode("86",et_phone.getText().toString());
					phString=et_phone.getText().toString();
					iv_yanzheng.setImageResource(R.drawable.pic_regist_getidentifynum);
					et_identity.setText("");
					et_password.setText("");
					et_repassword.setText("");
				}else{
					ToastUtils.ToastMessage(getApplicationContext(),"请输入正确的手机号码");
				}
			}else {
				ToastUtils.ToastMessage(getApplicationContext(),"电话不能为空");
			}
			break;
		case R.id.tv_video_tologin:
			Intent intent = new Intent(RegisterActivity.this,
					LoginActivity.class);
			startActivity(intent);
			break;
		case R.id.btn_next:
			//注册
			if(!TextUtils.isEmpty(et_phone.getText().toString())){
				if(!TextUtils.isEmpty(et_identity.getText().toString())){
					if(!TextUtils.isEmpty(et_password.getText().toString())){
						if(!TextUtils.isEmpty(et_repassword.getText().toString())){
							if(!(et_password.getText().toString()).equals(et_repassword.getText().toString())){
								ToastUtils.ToastMessage(getApplicationContext(), "两次输入的密码不相同");
							}else{
								if(isCheck){
									//注册部分代码
									SMSSDK.submitVerificationCode("86", phString, et_identity.getText().toString());
									String result = toRegist(et_phone.getText().toString(),et_identity.getText().toString(),et_password.getText().toString());
									if(result.equals("1")){		//成功
										ToastUtils.ToastMessage(getApplicationContext(), "注册成功");
										//跳转到登录页面
										Intent inte = new Intent(RegisterActivity.this,
												LoginActivity.class);
										startActivity(inte);
									}else if(result.equals("2")){	//已注册
										ToastUtils.ToastMessage(getApplicationContext(), "亲，手机号已被注册");
									}else{
										ToastUtils.ToastMessage(getApplicationContext(), "注册失败");
									}
								}else{
									ToastUtils.ToastMessage(getApplicationContext(), "请同意用户协议");
								}
							}
						}else{
							ToastUtils.ToastMessage(getApplicationContext(), "密码不能为空");
						}
					}else{
						ToastUtils.ToastMessage(getApplicationContext(), "请再次输入密码");
					}
				}else{
					ToastUtils.ToastMessage(getApplicationContext(), "验证码不能为空");
				}
			}else{
				ToastUtils.ToastMessage(getApplicationContext(), "请先输入手机号");
			}
			break;
		case R.id.iv_tocheck_xieyi:
			if(isCheck){
			//提示是否取消收藏
				isCheck = false;
				iv_check_xieyi.setImageResource(R.drawable.pic_regist_remeberpwd);
			}else {
				isCheck=true;
				//如果已经收藏，则显示收藏后的效果
				iv_check_xieyi.setImageResource(R.drawable.pic_regist_check_remberpwd);
			}
			break;
		default:
			break;
		}
	}
	/**
	 * 注册代码
	 * @param phone
	 * @param phoneCode
	 * @param password
	 */
	private String toRegist(String phone,String phoneCode,String password) {
		HttpURLConnection conn = null;
		PrintWriter printWriter = null;  
        BufferedReader bufferedReader = null;
        StringBuffer responseResult = new StringBuffer();  
		try {
	        String newUrl = URL +"?phone="+ phone + "&phoneCode=" + phoneCode + "&password=" + password;
			URL url = new URL(newUrl);
	        conn = (HttpURLConnection) url.openConnection();
	        conn.setRequestMethod("POST");// 提交模式
	        conn.setRequestProperty("Content-Type", "plain/text; charset=UTF-8");  
	        
	        // 设置通用的请求属性  
//            conn.setRequestProperty("accept", "*/*");  
//            conn.setRequestProperty("connection", "Keep-Alive");  
            // 发送POST请求必须设置如下两行  
            conn.setDoOutput(true);  
            conn.setDoInput(true);
            conn.connect();
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
	Handler handler=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			int event = msg.arg1;
			int result = msg.arg2;
			Object data = msg.obj;
			Log.e("event", "event="+event);
			if (result == SMSSDK.RESULT_COMPLETE) {
				//短信注册成功后，返回MainActivity,然后提示新好友
//				if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {//提交验证码成功
//					ToastUtils.ToastMessage(getApplicationContext(), "提交验证码成功");
//					tv_identity.setVisibility(View.VISIBLE);
//					tv_identity.setText("提交验证码成功");
//				} else 
					if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE){
						ToastUtils.ToastMessage(getApplicationContext(), "验证码已经发送");
						tv_identity.setVisibility(View.VISIBLE);
	//					iv_yanzheng.setImageResource(R.drawable.pic_regist_identifynum);
						tv_identity.setText("验证码已经发送");
//				}else if (event ==SMSSDK.EVENT_GET_SUPPORTED_COUNTRIES){//返回支持发送验证码的国家列表
//					Toast.makeText(getApplicationContext(), "获取国家列表成功", Toast.LENGTH_SHORT).show();
//					countryTextView.setText(data.toString());
//					
				}
			} else {
				((Throwable) data).printStackTrace();
				int resId = getStringRes(RegisterActivity.this, "smssdk_network_error");
				ToastUtils.ToastMessage(getApplicationContext(),"验证码错误");
				if (resId > 0) {
					Toast.makeText(RegisterActivity.this, resId, Toast.LENGTH_SHORT).show();
				}
			}
			
		}
	};
	private TextWatcher phoneWatcher = new TextWatcher() {  
        
        @Override 
        public void beforeTextChanged(CharSequence s, int start, int count,  
                int after) {  
            // TODO Auto-generated method stub  
        }  
 
         @Override    
        public void onTextChanged(CharSequence s, int start, int before,     
                int count) {     
//        	 if (s.length() > 0) {
//        		btn_login.setEnabled(true);
//     			iv_phone_clear.setVisibility(View.VISIBLE);
//     		} else {
//     			btn_login.setEnabled(false);
//     			iv_phone_clear.setVisibility(View.GONE);
//     		}
        }

		@Override
		public void afterTextChanged(Editable arg0) {
			
		}                    
    };  
}
