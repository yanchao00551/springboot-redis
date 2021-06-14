package com.power.modules.app.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;


import java.io.Serializable;
import java.util.Date;

/**
 * 前台用户表
 * 
 * @author yanchao
 * @email 7631990@qq.com
 * @date 2018-06-26 09:40:09
 */
@TableName("tb_user")
public class UserEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Long userId;
	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 手机号
	 */
	private String mobile;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 用户状态  用户状态默认为1
	 */
	private Integer userStatus;
	/**
	 * 用户头像
	 */
	private String userHeadimg;
	/**
	 * 是否是系统后台用户 0 不是 1 是
	 */
	private Integer isSystem;
	/**
	 * 是否是前台会员
	 */
	private Integer isMember;
	/**
	 * 手机号
	 */
	private String userTel;
	/**
	 * 手机号是否绑定 0 未绑定 1 绑定 
	 */
	private Integer userTelBind;
	/**
	 * qq号
	 */
	private String userQq;
	/**
	 * qq互联id
	 */
	private String qqOpenid;
	/**
	 * qq账号相关信息
	 */
	private String qqInfo;
	/**
	 * 邮箱
	 */
	private String userEmail;
	/**
	 * 是否邮箱绑定
	 */
	private Integer userEmailBind;
	/**
	 * 微信用户openid
	 */
	private String wxOpenid;
	/**
	 * 微信用户是否关注
	 */
	private Integer wxIsSub;
	/**
	 * 微信用户信息
	 */
	private String wxInfo;
	/**
	 * 附加信息
	 */
	private String otherInfo;
	/**
	 * 当前登录ip
	 */
	private String currentLoginIp;
	/**
	 * 当前登录的操作终端类型
	 */
	private Integer currentLoginType;
	/**
	 * 上次登录ip
	 */
	private String lastLoginIp;
	/**
	 * 上次登录的操作终端类型
	 */
	private Integer lastLoginType;
	/**
	 * 登录次数
	 */
	private Integer loginNum;
	/**
	 * 真实姓名
	 */
	private String realName;
	/**
	 * 性别 0保密 1男 2女
	 */
	private Integer sex;
	/**
	 * 所在地
	 */
	private String location;
	/**
	 * 用户昵称
	 */
	private String nickName;
	/**
	 * 微信unionid
	 */
	private String wxUnionid;
	/**
	 * 模板id
	 */
	private Integer qrcodeTemplateId;
	/**
	 * 微信用户关注时间
	 */
	private Date wxSubTime;
	/**
	 * 微信用户取消关注时间
	 */
	private Date wxNotsubTime;
	/**
	 * 注册时间
	 */
	private Date regTime;
	/**
	 * 当前登录时间
	 */
	private Date currentLoginTime;
	/**
	 * 上次登录时间
	 */
	private Date lastLoginTime;
	/**
	 * 
	 */
	private Integer birthday;
    /**
     * 支付密码
     */
	private String paymentPassword;
	
	/**
	 * 是否设置支付密码
	 */
	private Integer isSetPaymentPassword;
	



	public Integer getIsSetPaymentPassword() {
		return isSetPaymentPassword;
	}
	public void setIsSetPaymentPassword(Integer isSetPaymentPassword) {
		this.isSetPaymentPassword = isSetPaymentPassword;
	}
	public String getPaymentPassword() {
		return paymentPassword;
	}
	public void setPaymentPassword(String paymentPassword) {
		this.paymentPassword = paymentPassword;
	}
	/**
	 * 设置：
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Long getUserId() {
		return userId;
	}
	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取：手机号
	 */
	public String getMobile() {
		return mobile;
	}
	/**
	 * 设置：密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取：密码
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * 设置：用户状态  用户状态默认为1
	 */
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	/**
	 * 获取：用户状态  用户状态默认为1
	 */
	public Integer getUserStatus() {
		return userStatus;
	}
	/**
	 * 设置：用户头像
	 */
	public void setUserHeadimg(String userHeadimg) {
		this.userHeadimg = userHeadimg;
	}
	/**
	 * 获取：用户头像
	 */
	public String getUserHeadimg() {
		return userHeadimg;
	}
	/**
	 * 设置：是否是系统后台用户 0 不是 1 是
	 */
	public void setIsSystem(Integer isSystem) {
		this.isSystem = isSystem;
	}
	/**
	 * 获取：是否是系统后台用户 0 不是 1 是
	 */
	public Integer getIsSystem() {
		return isSystem;
	}
	/**
	 * 设置：是否是前台会员
	 */
	public void setIsMember(Integer isMember) {
		this.isMember = isMember;
	}
	/**
	 * 获取：是否是前台会员
	 */
	public Integer getIsMember() {
		return isMember;
	}
	/**
	 * 设置：手机号
	 */
	public void setUserTel(String userTel) {
		this.userTel = userTel;
	}
	/**
	 * 获取：手机号
	 */
	public String getUserTel() {
		return userTel;
	}
	/**
	 * 设置：手机号是否绑定 0 未绑定 1 绑定 
	 */
	public void setUserTelBind(Integer userTelBind) {
		this.userTelBind = userTelBind;
	}
	/**
	 * 获取：手机号是否绑定 0 未绑定 1 绑定 
	 */
	public Integer getUserTelBind() {
		return userTelBind;
	}
	/**
	 * 设置：qq号
	 */
	public void setUserQq(String userQq) {
		this.userQq = userQq;
	}
	/**
	 * 获取：qq号
	 */
	public String getUserQq() {
		return userQq;
	}
	/**
	 * 设置：qq互联id
	 */
	public void setQqOpenid(String qqOpenid) {
		this.qqOpenid = qqOpenid;
	}
	/**
	 * 获取：qq互联id
	 */
	public String getQqOpenid() {
		return qqOpenid;
	}
	/**
	 * 设置：qq账号相关信息
	 */
	public void setQqInfo(String qqInfo) {
		this.qqInfo = qqInfo;
	}
	/**
	 * 获取：qq账号相关信息
	 */
	public String getQqInfo() {
		return qqInfo;
	}
	/**
	 * 设置：邮箱
	 */
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	/**
	 * 获取：邮箱
	 */
	public String getUserEmail() {
		return userEmail;
	}
	/**
	 * 设置：是否邮箱绑定
	 */
	public void setUserEmailBind(Integer userEmailBind) {
		this.userEmailBind = userEmailBind;
	}
	/**
	 * 获取：是否邮箱绑定
	 */
	public Integer getUserEmailBind() {
		return userEmailBind;
	}
	/**
	 * 设置：微信用户openid
	 */
	public void setWxOpenid(String wxOpenid) {
		this.wxOpenid = wxOpenid;
	}
	/**
	 * 获取：微信用户openid
	 */
	public String getWxOpenid() {
		return wxOpenid;
	}
	/**
	 * 设置：微信用户是否关注
	 */
	public void setWxIsSub(Integer wxIsSub) {
		this.wxIsSub = wxIsSub;
	}
	/**
	 * 获取：微信用户是否关注
	 */
	public Integer getWxIsSub() {
		return wxIsSub;
	}
	/**
	 * 设置：微信用户信息
	 */
	public void setWxInfo(String wxInfo) {
		this.wxInfo = wxInfo;
	}
	/**
	 * 获取：微信用户信息
	 */
	public String getWxInfo() {
		return wxInfo;
	}
	/**
	 * 设置：附加信息
	 */
	public void setOtherInfo(String otherInfo) {
		this.otherInfo = otherInfo;
	}
	/**
	 * 获取：附加信息
	 */
	public String getOtherInfo() {
		return otherInfo;
	}
	/**
	 * 设置：当前登录ip
	 */
	public void setCurrentLoginIp(String currentLoginIp) {
		this.currentLoginIp = currentLoginIp;
	}
	/**
	 * 获取：当前登录ip
	 */
	public String getCurrentLoginIp() {
		return currentLoginIp;
	}
	/**
	 * 设置：当前登录的操作终端类型
	 */
	public void setCurrentLoginType(Integer currentLoginType) {
		this.currentLoginType = currentLoginType;
	}
	/**
	 * 获取：当前登录的操作终端类型
	 */
	public Integer getCurrentLoginType() {
		return currentLoginType;
	}
	/**
	 * 设置：上次登录ip
	 */
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	/**
	 * 获取：上次登录ip
	 */
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	/**
	 * 设置：上次登录的操作终端类型
	 */
	public void setLastLoginType(Integer lastLoginType) {
		this.lastLoginType = lastLoginType;
	}
	/**
	 * 获取：上次登录的操作终端类型
	 */
	public Integer getLastLoginType() {
		return lastLoginType;
	}
	/**
	 * 设置：登录次数
	 */
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}
	/**
	 * 获取：登录次数
	 */
	public Integer getLoginNum() {
		return loginNum;
	}
	/**
	 * 设置：真实姓名
	 */
	public void setRealName(String realName) {
		this.realName = realName;
	}
	/**
	 * 获取：真实姓名
	 */
	public String getRealName() {
		return realName;
	}
	/**
	 * 设置：性别 0保密 1男 2女
	 */
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	/**
	 * 获取：性别 0保密 1男 2女
	 */
	public Integer getSex() {
		return sex;
	}
	/**
	 * 设置：所在地
	 */
	public void setLocation(String location) {
		this.location = location;
	}
	/**
	 * 获取：所在地
	 */
	public String getLocation() {
		return location;
	}
	/**
	 * 设置：用户昵称
	 */
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	/**
	 * 获取：用户昵称
	 */
	public String getNickName() {
		return nickName;
	}
	/**
	 * 设置：微信unionid
	 */
	public void setWxUnionid(String wxUnionid) {
		this.wxUnionid = wxUnionid;
	}
	/**
	 * 获取：微信unionid
	 */
	public String getWxUnionid() {
		return wxUnionid;
	}
	/**
	 * 设置：模板id
	 */
	public void setQrcodeTemplateId(Integer qrcodeTemplateId) {
		this.qrcodeTemplateId = qrcodeTemplateId;
	}
	/**
	 * 获取：模板id
	 */
	public Integer getQrcodeTemplateId() {
		return qrcodeTemplateId;
	}
	/**
	 * 设置：微信用户关注时间
	 */
	public void setWxSubTime(Date wxSubTime) {
		this.wxSubTime = wxSubTime;
	}
	/**
	 * 获取：微信用户关注时间
	 */
	public Date getWxSubTime() {
		return wxSubTime;
	}
	/**
	 * 设置：微信用户取消关注时间
	 */
	public void setWxNotsubTime(Date wxNotsubTime) {
		this.wxNotsubTime = wxNotsubTime;
	}
	/**
	 * 获取：微信用户取消关注时间
	 */
	public Date getWxNotsubTime() {
		return wxNotsubTime;
	}
	/**
	 * 设置：注册时间
	 */
	public void setRegTime(Date regTime) {
		this.regTime = regTime;
	}
	/**
	 * 获取：注册时间
	 */
	public Date getRegTime() {
		return regTime;
	}
	/**
	 * 设置：当前登录时间
	 */
	public void setCurrentLoginTime(Date currentLoginTime) {
		this.currentLoginTime = currentLoginTime;
	}
	/**
	 * 获取：当前登录时间
	 */
	public Date getCurrentLoginTime() {
		return currentLoginTime;
	}
	/**
	 * 设置：上次登录时间
	 */
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 * 获取：上次登录时间
	 */
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	/**
	 * 设置：
	 */
	public void setBirthday(Integer birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取：
	 */
	public Integer getBirthday() {
		return birthday;
	}

	@Override
	public String toString() {
		return "UserEntity{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", mobile='" + mobile + '\'' +
				", password='" + password + '\'' +
				", userStatus=" + userStatus +
				", userHeadimg='" + userHeadimg + '\'' +
				", isSystem=" + isSystem +
				", isMember=" + isMember +
				", userTel='" + userTel + '\'' +
				", userTelBind=" + userTelBind +
				", userQq='" + userQq + '\'' +
				", qqOpenid='" + qqOpenid + '\'' +
				", qqInfo='" + qqInfo + '\'' +
				", userEmail='" + userEmail + '\'' +
				", userEmailBind=" + userEmailBind +
				", wxOpenid='" + wxOpenid + '\'' +
				", wxIsSub=" + wxIsSub +
				", wxInfo='" + wxInfo + '\'' +
				", otherInfo='" + otherInfo + '\'' +
				", currentLoginIp='" + currentLoginIp + '\'' +
				", currentLoginType=" + currentLoginType +
				", lastLoginIp='" + lastLoginIp + '\'' +
				", lastLoginType=" + lastLoginType +
				", loginNum=" + loginNum +
				", realName='" + realName + '\'' +
				", sex=" + sex +
				", location='" + location + '\'' +
				", nickName='" + nickName + '\'' +
				", wxUnionid='" + wxUnionid + '\'' +
				", qrcodeTemplateId=" + qrcodeTemplateId +
				", wxSubTime=" + wxSubTime +
				", wxNotsubTime=" + wxNotsubTime +
				", regTime=" + regTime +
				", currentLoginTime=" + currentLoginTime +
				", lastLoginTime=" + lastLoginTime +
				", birthday=" + birthday +
				", paymentPassword='" + paymentPassword + '\'' +
				", isSetPaymentPassword=" + isSetPaymentPassword +
				'}';
	}
}
