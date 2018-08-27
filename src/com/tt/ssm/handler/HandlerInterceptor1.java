package com.tt.ssm.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class HandlerInterceptor1 implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// ��request�л�ȡsession
		HttpSession session = request.getSession();
		// ��session�л�ȡusername
		Object username = session.getAttribute("user");
		// �ж�username�Ƿ�Ϊnull
		if (username != null) {
			// �����Ϊ�������
			return true;
		} else {
			// ���Ϊ������ת����¼ҳ��
			response.sendRedirect(request.getContextPath() + "/user/gologin.action");
		}

		return false;
	}

}
