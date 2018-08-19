package day12okhttpandmvp.bwei.com.day12_okhttpandmvp.view;

import day12okhttpandmvp.bwei.com.day12_okhttpandmvp.bean.UserBean;

/**
 * View: 视图层，对应xml文件与Activity/Fragment；
 * Created by 房国伟 on 2018/8/7.
 */
public interface IRegView {

    public void mobileVerify();//验证手机号合法
    public void mobileEmpty();
    public void pwdVerify();//校验密码
    public void pwdpd();//判断密码长度
    public void success(UserBean userBean);//请求成功
    public void failure(String msg);//请求失败

}
