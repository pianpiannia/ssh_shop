package com.jun.shop.adminuser.action;

import org.apache.struts2.ServletActionContext;

import com.jun.shop.adminuser.service.AdminUserService;
import com.jun.shop.adminuser.vo.AdminUser;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminUserAction extends ActionSupport implements ModelDriven<AdminUser>{

	private AdminUser adminUser = new AdminUser();
	@Override
	public AdminUser getModel() {
		// TODO Auto-generated method stub
		return adminUser;
	}
	
	private AdminUserService adminUserService;
	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
	}

	public String login(){
		//调用service完成登录
		AdminUser existAdminUser = adminUserService.login(adminUser);
		if(existAdminUser == null){
			//登录失败
			this.addActionError("用户名或者密码错误");
			return "loginFail";
		}else{
			//登录成功
			ServletActionContext.getRequest().getSession().setAttribute("existAdminUser", existAdminUser);
			return "loginSuccess";
		}
	}
}
