package com.wd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wd.action.ActionForward;
import com.wd.board.notice.NoticeService;

/**
 * Servlet implementation class NoticeControl
 */
@WebServlet("/NoticeControl")
public class NoticeControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionForward actionForward = new ActionForward();
		String cmd = request.getRequestURI();
		NoticeService service = new NoticeService();
		
		cmd = cmd.substring(cmd.lastIndexOf("/")+1);
		
		try {
			if(cmd.equals("boardList.notice")) {
				actionForward = service.selectList(request, response);
			}else if(cmd.equals("boardSelectOne.notice")) {
				actionForward = service.selectOne(request, response);
			}else if(cmd.equals("boardWrite.notice")) {
				actionForward = service.insert(request, response);
			}else if(cmd.equals("boardUpdate.notice")) {
				actionForward =service.update(request, response);
			}else if(cmd.equals("boardDelete.notice")) {
				actionForward = service.delete(request, response);
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
