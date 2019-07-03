package com.wd.member;

import java.sql.SQLIntegrityConstraintViolationException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wd.action.ActionForward;

public class MemberService {
	
	private ActionForward actionForward;
	private MemberDAO dao;
	
	public MemberService() {
		actionForward = new ActionForward();
		dao = new MemberDAO();
	}
	
	public ActionForward join(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		if(request.getMethod().equals("POST")) {
			MemberDTO dto = new MemberDTO();
			dto.setId(request.getParameter("id"));
			dto.setPassword(request.getParameter("password"));
			dto.setEmail(request.getParameter("email"));
			dto.setGender(request.getParameter("gender"));
			dto.setName(request.getParameter("name"));
			int result = 0;
			
			actionForward.setForward(true);
			actionForward.setPath("../common/result.jsp");

			request.setAttribute("path", "./memberJoin.member");
			
			try {
				result = dao.join(dto);
			}catch(SQLIntegrityConstraintViolationException e){
				//2019-06-12나중에 수정 필요 ... 아이디 중복확인 버튼 추가 
				request.setAttribute("message", "아이디 중복");
				return actionForward;
			}
			
			
			if(result>0) {//회원가입 성공메세지 띄우고, 로그인 화면으로 이동
				request.setAttribute("message", "가입 완료");
				request.setAttribute("path", "./memberLogin.member");
			}else {//회원가입 실패 메세지 띄우고, 폼으로 이동
				request.setAttribute("message", "가입 실패");
			}
		}else {
			actionForward.setForward(true);
			actionForward.setPath("./memberJoin.jsp");
		}
		
		return actionForward;
	}
	public ActionForward login(HttpServletRequest request, HttpServletResponse response) throws Exception{
		if(request.getMethod().equals("POST")) {
			MemberDTO dto = new MemberDTO();
			dto.setId(request.getParameter("id"));
			dto.setPassword(request.getParameter("password"));
			 //boolean flag = dao.memberCheck(dto);
			String remember = request.getParameter("remember");
			Cookie cookie = null;
			
			if(remember==null) {
				cookie = new Cookie("id", null);
				cookie.setMaxAge(0);
				response.addCookie(cookie);
			}else {
				cookie = new Cookie("id", dto.getId());
				cookie.setMaxAge(60*60*24*365);
				response.addCookie(cookie);
			}
			
			if(dao.memberCheck(dto)) {
				
				HttpSession session = request.getSession();
				session.setAttribute("memberDTO", dto);
				actionForward.setForward(false);
				actionForward.setPath("../index.jsp");
				
			}else {
				actionForward.setForward(true);
				actionForward.setPath("../common/result.jsp");
				request.setAttribute("message", "비밀번호가 일치하지 않거나, 아이디가 존재하지 않습니다.");
				request.setAttribute("path", "./memberLogin.jsp");
			}
			//레절트 0보다 크면 메인화면
			//아니면 로그인 실패 메세지, 로그인 폼
		}
		else {
			request.setAttribute("cookieID", null);
			Cookie[] cookies = request.getCookies();
			for(Cookie cookie :cookies) {
				if(cookie.getName().equals("id")) {
					request.setAttribute("cookieID", cookie.getValue());
					break;
				}
			}
			actionForward.setForward(true);
			actionForward.setPath("./memberLogin.jsp");
		}
		return actionForward;
	}
	public ActionForward logout(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		request.getSession().invalidate();
		//session.invalidate();
		actionForward.setForward(false);
		actionForward.setPath("../index.jsp");
		return actionForward;
	}
	public ActionForward withdrawal(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//멤버딜리트 
		HttpSession session = request.getSession();
		MemberDTO dto = (MemberDTO)session.getAttribute("memberDTO");
		int result = dao.withdrawal(dto.getId());
		
		actionForward.setForward(true);
		actionForward.setPath("../common/result.jsp");
		//성공하면 탈퇴 완료메세지, 인덱스 페이지로 이동
		if(result>0) {
			session.invalidate();
			//session.invalidate();
			request.setAttribute("message", "회원 탈퇴 완료");
			request.setAttribute("path", "../index.jsp");
		}else {
			request.setAttribute("message", "탈퇴 오류. 다시 시도해주세요.");
			request.setAttribute("path", "./memberMyPage.jsp");
		}
		//실패하면 탈퇴 실패 메세지 마이페이지로 .
		return actionForward;
	}
	public ActionForward modify(HttpServletRequest request, HttpServletResponse response) throws Exception{
		HttpSession session = request.getSession();
		MemberDTO dto = new MemberDTO();
		dto = (MemberDTO)session.getAttribute("memberDTO");
		
		if(request.getMethod().equals("POST")) {
			dto.setEmail(request.getParameter("email"));
			dto.setGender(request.getParameter("gender"));
			dto.setName(request.getParameter("name"));
			int result = dao.modify(dto);
			
			if(result>0) {
				session.setAttribute("memberDTO", dto);
				actionForward.setForward(false);
				actionForward.setPath("memberMyPage.member");
			}else {
				actionForward.setForward(true);
				actionForward.setPath("./common/result.jsp");
				
				request.setAttribute("message", "정보수정 실패. 다시 시도하세요.");
				request.setAttribute("path","memberMyPage.member");
			}
		}else {
			
			request.setAttribute("memberDTO", dto);
			actionForward.setForward(true);
			actionForward.setPath("./memberModify.jsp");
		}
		
		return actionForward;
	}
	
	public ActionForward myPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
		ActionForward actionForward = new ActionForward();
		
		HttpSession session = request.getSession();
		
		MemberDTO dto = (MemberDTO)session.getAttribute("memberDTO");
		
		request.setAttribute("memberDTO", dto);
		
		actionForward.setForward(true);
		actionForward.setPath("./memberMyPage.jsp");
				
		return actionForward;
	}
}
