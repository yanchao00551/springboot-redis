package com.power.modules.app.service;


import com.baomidou.mybatisplus.service.IService;
import com.power.modules.app.entity.UserEntity;
import com.power.modules.app.form.LoginForm;

/**
 * 用户

 */
public interface UserService extends IService<UserEntity> {

	UserEntity queryByMobile(String mobile);

	/**
	 * 新增一个用户数据
	 * @param userEntity
	 * @return
	 */
	Integer registerUser(UserEntity userEntity);

	/**
	 * 用户登录
	 * @param form
	 * @return
	 */
	long login(LoginForm form);

}
