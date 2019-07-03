package com.wd.board;

import java.util.List;

public interface BoardDAO {
	public int insert(BoardDTO dto) throws Exception;
	public int update(BoardDTO dto) throws Exception;
	public int delete(int num) throws Exception;
	public List<BoardDTO> selectList(int s, int e, String SearchBy, String search) throws Exception;
	public BoardDTO selectOne(int num) throws Exception;
	public int getTotal(String searchBy, String search)throws Exception;
}
