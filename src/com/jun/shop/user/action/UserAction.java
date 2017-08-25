package com.jun.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jun.shop.user.service.UserService;
import com.jun.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	//模型驱动使用的对象
	private User user = new User();
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	//接受验证码
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	//注入UserService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 跳转到注册界面
	 */
	public String registPage(){
		return "registPage";
	}
	
	/**
	 * ajax进行异步校验用户名的执行方法
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		//调用service查询
		User existUser = userService.findByUsername(user.getUsername());
		//获得response对象，向页面输出
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		//判断
		if(existUser != null){
			//用户名以存在
			response.getWriter().println("<font color='red'>用户名已存在</font>");
		}else{
			//用户名可以使用
			response.getWriter().println("<font color='green'>用户名可以使用</font>");
		}
		return NONE;
	}

	/**
	 * 用户注册的方法
	 */
	public String regist(){
		//判断验证码程序：
		//从session中获得随机验证码
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("验证码输入错误");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("注册成功,请去邮箱激活");
		return "msg";
	}
	
	/**
	 * 跳转到登录页面
	 */
	public String loginPage(){	
		return "loginPage";
	}
	
	/**
	 * 登录的方法
	 */
	public String login(){
		User existUser = userService.login(user);
		if(existUser == null){
			//登录失败
			this.addActionError("用户名或密码错误或用户未激活");
			return LOGIN;
		}else{
			//登录成功
			//将用户信息存入session中
			ServletActionContext.getRequest().getSession()
			.setAttribute("existUser", existUser);
		}
		return "loginSuccess";
	}
	
	/**
	 * 用户退出的方法
	 */
	public String quit(){
		//销毁session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
