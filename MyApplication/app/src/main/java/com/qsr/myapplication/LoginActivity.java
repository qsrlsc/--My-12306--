package com.qsr.myapplication;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.qsr.myapplication.Entity.User;
import com.qsr.myapplication.util.Code;
import com.qsr.myapplication.util.DBOpenHelper;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity {

    //声明控件
    private String realCode;
    private DBOpenHelper mDBOpenHelper;
    private Button mBtLoginactivityRegister;
    private EditText mEtLoginactivityUsername;
    private EditText mEtLoginactivityPassword;
    private Button mBtLoginactivityLogin;
    private EditText mEtloginactivityPhonecodes;
    private ImageView mIvloginactivityShowcode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initView();

        mDBOpenHelper = new DBOpenHelper(this);

        //将验证码用图片的形式显示出来
        mIvloginactivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
        realCode = Code.getInstance().getCode().toLowerCase();
    }
    private void initView() {
        // 初始化控件
        mBtLoginactivityLogin = findViewById(R.id.btn_login);
        mBtLoginactivityRegister = findViewById(R.id.btn_register);
        mEtLoginactivityUsername = findViewById(R.id.et_loginactivity_username);
        mEtLoginactivityPassword = findViewById(R.id.et_loginactivity_password);
        mEtloginactivityPhonecodes = findViewById(R.id.et_loginactivity_phoneCodes);
        mIvloginactivityShowcode = findViewById(R.id.iv_loginactivity_showCode);

        // 设置点击事件监听器
        mBtLoginactivityLogin.setOnClickListener(this::onClick);
        mIvloginactivityShowcode.setOnClickListener(this::onClick);
        mBtLoginactivityRegister.setOnClickListener(this::onClick);

    }

    public void onClick(View view) {
        Intent intent=null;
        switch (view.getId()) {
            // 跳转到注册界面
            case R.id.btn_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
            case R.id.iv_loginactivity_showCode:    //改变随机验证码的生成
                mIvloginactivityShowcode.setImageBitmap(Code.getInstance().createBitmap());
                realCode = Code.getInstance().getCode().toLowerCase();
                break;

            case R.id.btn_login:
                String username=mEtLoginactivityUsername.getText().toString();
                String password= mEtLoginactivityPassword.getText().toString();
                String phoneCode = mEtloginactivityPhonecodes.getText().toString();
                //弹出内容设置
                String ok="登陆成功！";
                String fail="账号或密码有误，请重新登陆！";
                String wrongcode="验证码有误！";

//                String name = mEtLoginactivityUsername.getText().toString().trim();
//                String password = mEtLoginactivityPassword.getText().toString().trim();
//                String phoneCode = mEtloginactivityPhonecodes.getText().toString().toLowerCase();
//
//                if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(password)&&!TextUtils.isEmpty(phoneCode)) {
//                    if (phoneCode.equals(realCode)) {
//                        ArrayList<User> data = mDBOpenHelper.getAllData();
//                        boolean match = false;
//                        for (int i = 0; i < data.size(); i++) {
//                            User user = data.get(i);
//                            if (name.equals(user.getName()) && password.equals(user.getPassword())/*&&phoneCode.equals(realCode)*/) {
//                                match = true;
//                                break;
//                            } else {
//                                match = false;
//                            }
//                        }
//
//                        if (match) {
//                            Toast.makeText(this, "登录成功", Toast.LENGTH_SHORT).show();
//                            Intent intent = new Intent(this, MainActivity.class);
//                            startActivity(intent);
//                            finish();//销毁此Activity
//                        } else {
//                            Toast.makeText(this, "用户名或密码不正确，请重新输入", Toast.LENGTH_SHORT).show();
//                        }
//                    }else {
//                        Toast.makeText(this, "验证码错误,注册失败", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(this, "请输入你的用户名或密码", Toast.LENGTH_SHORT).show();
//                }break;
             if(!phoneCode.equals(realCode)){
                 Toast.makeText(getApplicationContext(),wrongcode,Toast.LENGTH_SHORT).show();
                }
            else if(username.equals("qsr")&&password.equals("123")&&phoneCode.equals(realCode)){
                //正确进行跳转
                Toast.makeText(getApplicationContext(),ok,Toast.LENGTH_SHORT).show();
                intent=new Intent(this,MainActivity.class);
                startActivity(intent);
            }else {
                //不正确，弹出登陆失败toast
                Toast toastCenter =Toast.makeText(getApplicationContext(),fail,Toast.LENGTH_SHORT);
                toastCenter.setGravity(Gravity.CENTER,0,0);
                toastCenter.show();
            }
        }


    }
}