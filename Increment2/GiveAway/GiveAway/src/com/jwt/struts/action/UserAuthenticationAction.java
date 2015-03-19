package com.jwt.struts.action;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.jwt.struts.dao.UserRegisterDAO;
import com.jwt.struts.form.UserLoginForm;

public class UserAuthenticationAction {
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		UserLoginForm loginForm = (UserLoginForm) form;
		String userName = loginForm.getUserName();
		String password = loginForm.getPassword();
		System.out.println("in action class");
		UserRegisterDAO userDAO = new UserRegisterDAO();
		int status = userDAO.authenticateUser(userName, password);
		if(status == 1){
			return mapping.findForward("success");
		}
		else{
			return mapping.findForward("error");
		}
	}
		
}
