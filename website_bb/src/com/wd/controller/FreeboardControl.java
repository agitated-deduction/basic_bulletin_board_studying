package com.wd.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wd.action.ActionForward;
import com.wd.board.freeboard.FreeboardService;

/**
 * Servlet implementation class BoardControl
 */
@WebServlet("/BoardControl")
public class FreeboardControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FreeboardControl() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ActionForward actionForward = new ActionForward();
		String cmd = request.getRequestURI();
		FreeboardService service = new FreeboardService();
		
		cmd = cmd.substring(cmd.lastIndexOf("/")+1);
		
		try {
			if(cmd.equals("boardList.freeboard")) {
				actionForward = service.selectList(request, response);
			}else if(cmd.equals("boardSelectOne.freeboard")) {
				actionForward = service.selectOne(request, response);
			}else if(cmd.equals("boardWrite.freeboard")) {
				actionForward = service.insert(request, response);
			}else if(cmd.equals("boardUpdate.freeboard")) {
				actionForward =service.update(request, response);
			}else if(cmd.equals("boardDelete.freeboard")) {
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
