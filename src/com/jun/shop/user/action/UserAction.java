package com.jun.shop.user.action;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.jun.shop.user.service.UserService;
import com.jun.shop.user.vo.User;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User>{
	//ģ������ʹ�õĶ���
	private User user = new User();
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}
	//������֤��
	private String checkcode;
	
	public void setCheckcode(String checkcode) {
		this.checkcode = checkcode;
	}
	//ע��UserService
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * ��ת��ע�����
	 */
	public String registPage(){
		return "registPage";
	}
	
	/**
	 * ajax�����첽У���û�����ִ�з���
	 * @throws IOException 
	 */
	public String findByName() throws IOException{
		//����service��ѯ
		User existUser = userService.findByUsername(user.getUsername());
		//���response������ҳ�����
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		//�ж�
		if(existUser != null){
			//�û����Դ���
			response.getWriter().println("<font color='red'>�û����Ѵ���</font>");
		}else{
			//�û�������ʹ��
			response.getWriter().println("<font color='green'>�û�������ʹ��</font>");
		}
		return NONE;
	}

	/**
	 * �û�ע��ķ���
	 */
	public String regist(){
		//�ж���֤�����
		//��session�л�������֤��
		String checkcode1 = (String) ServletActionContext.getRequest().getSession().getAttribute("checkcode");
		if(!checkcode.equalsIgnoreCase(checkcode1)){
			this.addActionError("��֤���������");
			return "checkcodeFail";
		}
		userService.save(user);
		this.addActionMessage("ע��ɹ�,��ȥ���伤��");
		return "msg";
	}
	
	/**
	 * ��ת����¼ҳ��
	 */
	public String loginPage(){	
		return "loginPage";
	}
	
	/**
	 * ��¼�ķ���
	 */
	public String login(){
		User existUser = userService.login(user);
		if(existUser == null){
			//��¼ʧ��
			this.addActionError("�û��������������û�δ����");
			return LOGIN;
		}else{
			//��¼�ɹ�
			//���û���Ϣ����session��
			ServletActionContext.getRequest().getSession()
			.setAttribute("existUser", existUser);
		}
		return "loginSuccess";
	}
	
	/**
	 * �û��˳��ķ���
	 */
	public String quit(){
		//����session
		ServletActionContext.getRequest().getSession().invalidate();
		return "quit";
	}
}
