package com.wd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wd.action.ActionForward;
import com.wd.member.MemberService;

/**
 * Servlet implementation class MemberControl
 */
@WebServlet("/MemberControl")
public class MemberControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionForward actionForward = new ActionForward();
		String cmd = request.getRequestURI();
		MemberService service = new MemberService();
		
		cmd = cmd.substring(cmd.lastIndexOf("/")+1);
		
		try {
			if(cmd.equals("memberJoin.member")) {
				actionForward = service.join(request, response);
			}else if(cmd.equals("memberLogin.member")) {
				actionForward = service.login(request, response);
			}else if(cmd.equals("memberLogout.member")) {
				actionForward = service.logout(request, response);
			}else if(cmd.equals("memberWithdrawal.member")) {
				actionForward =service.withdrawal(request, response);
			}else if(cmd.equals("memberModify.member")){  
				actionForward = service.modify(request, response);
			}else if(cmd.equals("memberMyPage.member")){
				actionForward = service.myPage(request, response);
			}else {
				actionForward = new ActionForward();
				actionForward.setForward(false);
				actionForward.setPath(request.getContextPath()+"/index.jsp");
			}
		}catch(Exception e) {
		e.printStackTrace();}//액션포워드 설정 안되어있음!!!!
		
		if(actionForward.isForward()) {
			RequestDispatcher view = request.getRequestDispatcher(actionForward.getPath());
			view.forward(request, response);
		}
		else {
			response.sendRedirect(actionForward.getPath());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
