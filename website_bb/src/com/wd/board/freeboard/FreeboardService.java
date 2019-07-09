package com.wd.board.freeboard;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wd.action.ActionForward;
import com.wd.board.BoardDTO;
import com.wd.board.BoardService;

public class FreeboardService implements BoardService{
	
	ActionForward actionForward;
	FreeboardDAO dao;
	public FreeboardService(){
		actionForward = new ActionForward();
		dao = new FreeboardDAO();
	}

	@Override
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//ActionForward actionForward = new ActionForward();
		//FreeboardDAO dao = new FreeboardDAO();
		BoardDTO dto = new FreeboardDTO();
		request.setAttribute("board", "freeboard");
		
		if(request.getMethod().equals("POST")) {
			dto.setTitle((String)request.getParameter("title")); //오늘의 메모: form태그에서 가져올때는 get parameter
			dto.setWriter((String)request.getParameter("writer"));
			dto.setContents((String)request.getParameter("contents"));
			
			
			int result = dao.insert(dto);
			if(result>0) {	//DB에 입력 성공
				actionForward.setForward(false);
				actionForward.setPath("boardList.freeboard");
				
			}else {			//입력 실패
				actionForward.setForward(true);
				actionForward.setPath("../common/result.jsp");
				request.setAttribute("path", "./boardWrite.freeboard");//??.jsp? .freeboard?
				request.setAttribute("message", "작성 실패");
				
			}
		}else {
			actionForward.setForward(true);
			actionForward.setPath("./boardWrite.jsp");
		}
		
		return actionForward;
	}

	@Override
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) throws Exception{
		int num = Integer.parseInt(request.getParameter("num"));
		BoardDTO dto = new FreeboardDTO();
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
				request.setAttribute("path", "./boardSelectOne.freeboard?num="+num);
			}else{
				request.setAttribute("message", "수정 실패");
				request.setAttribute("path", "./boardSelectOne.freeboard?num="+num);
			}
		}
		else {
			dto = dao.selectOne(num);
			
			request.setAttribute("dto", dto);
			request.setAttribute("board", "freeboard");
			
			actionForward.setForward(true);
			actionForward.setPath("../board/boardUpdate.jsp");
		}
		return actionForward;
	}

	@Override
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) throws Exception{
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		int result = dao.delete(num);
		
		actionForward.setForward(true);
		actionForward.setPath("../common/result.jsp");
		
		if(result>0) {
			request.setAttribute("message", "삭제 완료");
			request.setAttribute("path", "./boardList.freeboard");
		}else {
			request.setAttribute("message", "삭제 실패");
			request.setAttribute("path", "./boardSelectOne.freeboard?num="+num);
		}
		
		return actionForward;
	}

	@Override
	public ActionForward selectList(HttpServletRequest request, HttpServletResponse response) throws Exception{
		/*for(int i = 0; i < 100; i++) {
			BoardDTO d = new FreeboardDTO();
			d.setTitle("test"+i);
			d.setWriter("tester"+i);
			d.setContents(i+i+i+i+i+"\nthis is test code for a lot of contents\ndshfldksfhldksflsdjkghejfjds fsdhfjh fdsk dfhksd fdsf dk");
			dao.insert(d);
		}*/
		//ActionForward actionForward = new ActionForward();
		//FreeboardDAO dao = new FreeboardDAO();
		List<BoardDTO> freeboardList = new ArrayList<BoardDTO>();
		
		String searchBy = (String)request.getParameter("searchBy");
		String search = (String)request.getParameter("search");
		if(searchBy == null||searchBy=="") searchBy = "title";
		if(search == null) search = "";
		
		int curPage = (request.getParameter("curPage") == null)?1:Integer.parseInt((request.getParameter("curPage")));
		
		int contPerPage = 10; //한 페이지당 게시물 수 10으로 설정.
		
		int startRow = (curPage-1)*contPerPage+1;
		int endRow = contPerPage*curPage;
		
		request.setAttribute("board", "freeboard");
		
		try {
					
			int totalPage = (int) Math.ceil(dao.getTotal(searchBy, search)/(contPerPage));
			int pagePerBlock = 5; //한 블럭당 페이지 인덱스 5개로 제한 , 테스트 위해 작은 수로 제한함
			//int totalBlock = totalPage%(pagePerBlock+1);
			int startPage = pagePerBlock*((curPage-1)/pagePerBlock)+1;
			int endPage = startPage+pagePerBlock-1;
			if (endPage>totalPage) endPage = totalPage;
			//System.out.println(" to page : "+ totalPage +", startPage: "+ startPage);
			freeboardList = dao.selectList(startRow, endRow, searchBy, search);
			request.setAttribute("dtoList", freeboardList);
			
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
	public ActionForward selectOne(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//ActionForward actionForward = new ActionForward();
		BoardDTO dto = new BoardDTO();
		int num = Integer.parseInt(request.getParameter("num"));
		dto = dao.selectOne(num);
		
		request.setAttribute("dto", dto);
		request.setAttribute("board", "freeboard");
		actionForward.setForward(true);
		actionForward.setPath("../board/boardSelectOne.jsp");//이거 경로 모르겠음...
		
		return actionForward;
	}


}
