package com.wd.board;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wd.action.ActionForward;

public interface BoardService {
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ActionForward update(HttpServletRequest request, HttpServletResponse response)throws Exception;
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ActionForward selectList(HttpServletRequest request, HttpServletResponse response) throws Exception;
	public ActionForward selectOne(HttpServletRequest request, HttpServletResponse response)throws Exception;
}
