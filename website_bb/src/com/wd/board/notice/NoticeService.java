package com.wd.board.notice;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wd.action.ActionForward;
import com.wd.board.BoardDTO;
import com.wd.board.BoardService;

public class NoticeService implements BoardService{
	
	ActionForward actionForward;
	NoticeDAO dao;
	public NoticeService(){
		actionForward = new ActionForward();
		dao = new NoticeDAO();
	}
	
	@Override
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) throws Exception {
		BoardDTO dto = new NoticeDTO();
		request.setAttribute("board", "notice");
		
		if(request.getMethod().equals("POST")) {
			dto.setTitle((String)request.getParameter("title")); //오늘의 메모: form태그에서 가져올때는 get parameter
			dto.setWriter((String)request.getParameter("writer"));
			dto.setContents((String)request.getParameter("contents"));
			
			
			int result = dao.insert(dto);
			if(result>0) {	//DB에 입력 성공
				actionForward.setForward(false);
				actionForward.setPath("boardList.notice");
				
			}else {			//입력 실패
				actionForward.setForward(true);
				actionForward.setPath("../common/result.jsp");
				request.setAttribute("path", "./boardWrite.notice");
				request.setAttribute("message", "작성 실패");
				
			}
		}else {
			actionForward.setForward(true);
			actionForward.setPath("./boardWrite.jsp");
		}
		
		return actionForward;
	}

	@Override
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) throws Exception {
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDTO dto = new NoticeDTO();
		if(request.getMethod().equals("POST")) {
			actionForward.setForward(true);
			actionForward.setPath("../common/result.jsp");
			
			dto.setNum(num);
			dto.setTitle(request.getParameter("title"));
			dto.setWriter(request.getParameter("writer"));
			dto.setContents(request.getParameter("contents"));
			
			int result = dao.update(dto);
			
			if(result>0) {
				request.setAttribute("message", "수정 완료");
				request.setAttribute("path", "./boardSelectOne.notice?num="+num);
			}else{
				request.setAttribute("message", "수정 실패");
				request.setAttribute("path", "./boardSelectOne.notice?num="+num);
			}
		}
		else {
			dto = dao.selectOne(num);
			
			request.setAttribute("dto", dto);
			request.setAttribute("board", "notice");
			
			actionForward.setForward(true);
			actionForward.setPath("../board/boardUpdate.jsp");
		}
		return actionForward;
	}

	@Override
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) throws Exception {
int num = Integer.parseInt(request.getParameter("num"));
		
		int result = dao.delete(num);
		
		actionForward.setForward(true);
		actionForward.setPath("../common/result.jsp");
		
		if(result>0) {
			request.setAttribute("message", "삭제 완료");
			request.setAttribute("path", "./boardList.notice");
		}else {
			request.setAttribute("message", "삭제 실패");
			request.setAttribute("path", "./boardSelectOne.notice?num="+num);
		}
		
		return actionForward;
	}

	@Override
	public ActionForward selectList(HttpServletRequest request, HttpServletResponse response) throws Exception {
		List<BoardDTO> noticeList = new ArrayList<BoardDTO>();
		
		String searchBy = (String)request.getParameter("searchBy");
		String search = (String)request.getParameter("search");
		if(searchBy == null||searchBy=="") searchBy = "title";
		if(search == null) search = "";
		
		int curPage = (request.getParameter("curPage") == null)?1:Integer.parseInt((request.getParameter("curPage")));
		
		int contPerPage = 10; //한 페이지당 게시물 수 10으로 설정.
		
		int startRow = (curPage-1)*contPerPage+1;
		int endRow = contPerPage*curPage;
		
		request.setAttribute("board", "notice");
		
		try {
					
			int totalPage = dao.getTotal(searchBy, search)/(contPerPage+1)+1;
			int pagePerBlock = 5; //한 블럭당 페이지 인덱스 5개로 제한 , 테스트 위해 작은 수로 제한함
			//int totalBlock = totalPage%(pagePerBlock+1);
			int startPage = curPage/pagePerBlock+1;
			int endPage = startPage+pagePerBlock-1;
			if (endPage>totalPage) endPage = totalPage;
			
			noticeList = dao.selectList(startRow, endRow, searchBy, search);
			request.setAttribute("dtoList", noticeList);
			
			request.setAttribute("curPage", curPage);
			request.setAttribute("searchBy", searchBy);
			request.setAttribute("search", search);
			request.setAttribute("startPage", startPage);
			request.setAttribute("endPage", endPage);
			request.setAttribute("totalPage", totalPage);
			
			//String test = (String)request.getAttribute("board");
			
		}
		catch(Exception e) {
			
			e.printStackTrace();
			request.setAttribute("curPage", 1);
			request.setAttribute("searchBy", "title");
			request.setAttribute("search", "");
			request.setAttribute("startPage", 1);
			request.setAttribute("endPage", 1);
			request.setAttribute("totalPage", 1);
		}
		
		actionForward.setForward(true);
		actionForward.setPath("../board/boardList.jsp");
		return actionForward;
	}

	@Override
	public ActionForward selectOne(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//ActionForward actionForward = new ActionForward();
				BoardDTO dto = new BoardDTO();
				int num = Integer.parseInt(request.getParameter("num"));
				dto = dao.selectOne(num);
				
				request.setAttribute("dto", dto);
				request.setAttribute("board", "notice");
				actionForward.setForward(true);
				actionForward.setPath("../board/boardSelectOne.jsp");//이거 경로 모르겠음...
				
				return actionForward;
	}

}
