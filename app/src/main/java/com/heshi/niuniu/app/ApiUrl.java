package com.heshi.niuniu.app;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class ApiUrl {

    public static final String Login = "api/token";

    //验证码获取
    public static final String getCode = "api/sms";

    //注册
    public static final String Register = "api/user";

    //获取验证码 --忘记密码
    public static final String getVerCode = "api/user/forget_password";

    //校验 验证码
    public static final String commitVerCode = "api/user/forget_password_2";

    //修改密码
    public static final String verPass = "api/user/forget_password_3";


    //获取im信息
    public static final String getImPass = "redfive/im/user.do";

    //获取基本信息
    public static final String getBaseInfo = "red/user/findfriend.do";

    //获取im信息 user_name
    public static final String getWeiBo = "redfive/blog/count_circle.do";

    //获取im信息 user_name
    public static final String getDynamic = "redfive/face/count_circle.do";

    //获取粉丝 user_id
    public static final String getFan = "red/user/count_friend.do";

    //获取红包  user_id
    public static final String getRedPacket = "red/payred/pay_count.do";


    //上传  user_id
    public static final String updataImg = "red/use/hard_pic.do";


    //获取红包(所有好友)  user_id
    public static final String getAllFriend = "red/user/allfriend.do";


    //查询好友 name Post
    public static final String searchFriend = "red/user/find_id.do";

    //查询好友  friend_id
    public static final String findFriend = "red/user/find_id.do";

    //添加好友  friend_id
    public static final String addFriend = "red/user/addfriend.do";

    //设置备注  friend_id
    public static final String setInfoMark = "red/user/set_friend.do";

    //验证信息
    public static final String getNewFiend = "red/user/find_people.do";

    //获取已发送验证
    public static final String getOldFiend = "red/user/no_friend.do";

    //获取待验证
    public static final String agreeOperation = "red/user/add_me_friend.do";

    //获取朋友圈列表
    public static final String getDynamicList = "redfive/face/getcricle.do";

    //获取朋友圈列表
    public static final String getDynamicInfo = "redfive/face/me.do";

    //朋友圈获取点赞
    public static final String getZanList = "redfive/face/get_give.do";

    //获取一级评论
    public static final String getOneStepComment = "redfive/face/get_comment1.do";

    //发表评论
    public static final String publishDynamic = "redfive/face/circle.do";

}
