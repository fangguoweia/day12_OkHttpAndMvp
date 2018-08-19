package day12okhttpandmvp.bwei.com.day12_okhttpandmvp.model;

import com.google.gson.Gson;

import java.io.IOException;
import android.os.Handler;
import android.os.Message;

import java.util.logging.LogRecord;

import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.bean.UserBean;
import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.common.Api;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Model: 实体层，负责获取实体数据。
 * Created by 房国伟 on 2018/8/7.
 */
public class RegisterModel {

    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
        }
    };

    /**
     * 请求网络数据
     */
    public void register(String mobile, String pwd, final RegCallback regCallback){

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();

        FormBody formBody = new FormBody.Builder()
                .add("mobile",mobile)
                .add("password",pwd)
                .build();

        final Request request = new Request.Builder()
                .url(Api.REG_URL).post(formBody)
                .build();


        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                //请求失败
                if (regCallback != null){
                    regCallback.failure("请求失败---》"+e);
                }
            }

            //请求成功
            @Override
            public void onResponse(Call call, Response response) throws IOException {

                //1.得到响应体（json）,并解析成bean类
                //2.切换线程，把bean类回调给presenter层
                if (response.code() == 200){
                    String result = response.body().string();
                    parseJsonResult(result,regCallback);
                }
            }
        });
    }

    /**
     * 解析json为bean对象
     * @param result
     * @param regCallback
     */
    private void parseJsonResult(String result, final RegCallback regCallback) {
        if (result != null){
            final UserBean userBean = new Gson().fromJson(result, UserBean.class);

            //切换线程
            handler.post(new Runnable() {
                @Override
                public void run() {
                    //运行在主线程
                    if (regCallback !=null){
                        regCallback.success(userBean);
                    }
                }
            });
        }
    }


    /**
     * 创建接口
     */
    public interface RegCallback{
        //请求失败
        public void failure(String errorMsg);
        //请求成功
        public void success(UserBean userBean);
    }

}
