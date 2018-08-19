package day12okhttpandmvp.bwei.com.day12_okhttpandmvp.mainactivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.R;
import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.bean.UserBean;
import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.presenter.RegisterPresenter;
import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.view.IRegView;
import okhttp3.OkHttpClient;

public class MainActivity extends AppCompatActivity implements IRegView{

    private EditText mobileEt;
    private EditText pwdEt;
    private RegisterPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();

    }

    private void initData() {
        presenter = new RegisterPresenter(this);
    }

    private void initView() {
        mobileEt = findViewById(R.id.mobile_et);
        pwdEt = findViewById(R.id.pwd_et);
    }


    /**
     * 点击注册
     */
    public void register(View view){
        presenter.register(mobileEt.getText().toString(),pwdEt.getText().toString());
    }

    @Override
    public void mobileVerify() {
        Toast.makeText(this,"手机号必须11位数的合法手机号",Toast.LENGTH_LONG).show();
    }

    @Override
    public void mobileEmpty() {
        Toast.makeText(this,"手机号不能为空",Toast.LENGTH_LONG).show();
    }

    @Override
    public void pwdVerify() {
        Toast.makeText(this,"密码不合法",Toast.LENGTH_LONG).show();
    }

    @Override
    public void pwdpd() {
        Toast.makeText(this,"密码必须大于等于6位",Toast.LENGTH_LONG).show();
    }

    @Override
    public void success(UserBean userBean) {
        Toast.makeText(this,userBean.msg,Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,MainActivity2.class));
    }

    @Override
    public void failure(String msg) {
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }
}
