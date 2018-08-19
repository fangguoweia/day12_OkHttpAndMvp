package day12okhttpandmvp.bwei.com.day12_okhttpandmvp.presenter;

import android.text.TextUtils;

import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.bean.UserBean;
import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.mainactivity.MainActivity;
import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.model.RegisterModel;
import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.utils.RegexValidateUtil;
import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.view.IRegView;

/**
 * Presenter: 逻辑控制层，同时持有View和Model对象；
 * Created by 房国伟 on 2018/8/7.
 */
public class RegisterPresenter {

    private RegisterModel registerModel;
    private IRegView iRegView;

    
    public RegisterPresenter(IRegView iRegView) {
        this.iRegView = iRegView;
        this.registerModel = new RegisterModel();// TODO: 2018/8/7  
    }

    /**
     * 注册逻辑
     * @param mobile
     * @param pwd
     */
    public void register(String mobile,String pwd){

        if (TextUtils.isEmpty(mobile)){
            iRegView.mobileEmpty();
            return;
        }

        if (!RegexValidateUtil.checkCellphone(mobile)){
            iRegView.mobileVerify();
            return;
        }

        //判断为空
        if (TextUtils.isEmpty(pwd)){
            iRegView.pwdVerify();
            return;
        }
        //判断密码是否大于6
        if (pwd.length()<6) {
            iRegView.pwdpd();
            return;
        }

        /**
         * 调用接口
         */
        registerModel.register(mobile, pwd, new RegisterModel.RegCallback() {

            //失败
            @Override
            public void failure(String errorMsg) {
                iRegView.failure(errorMsg);
            }
            //成功
            @Override
            public void success(UserBean userBean) {
                iRegView.success(userBean);
            }
        });
    }
}
