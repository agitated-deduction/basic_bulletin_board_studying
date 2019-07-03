package com.wd.board.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wd.board.BoardDAO;
import com.wd.board.BoardDTO;
import com.wd.util.DBConnector;

public class NoticeDAO implements BoardDAO{

	@Override
	public int insert(BoardDTO dto) throws Exception {
		Connection conn = DBConnector.getConnect();
		String sql = "insert into notice(num, title, writer, contents, reg_date, hit) "
				+ "values(seq_board.nextval, ?, ?, ?, sysdate, 0)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, dto.getTitle());
		pstm.setString(2, dto.getWriter());
		pstm.setString(3, dto.getContents());
		
		int result = pstm.executeUpdate();
		
		DBConnector.disconnect(pstm, conn);
		
		return result;
	}

	@Override
	public int update(BoardDTO dto) throws Exception {
		Connection conn = DBConnector.getConnect();
		String sql = "update notice set title=?,writer=?,contents=? where num=?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, dto.getTitle());
		pstm.setString(2, dto.getWriter());
		pstm.setString(3, dto.getContents());
		pstm.setInt(4, dto.getNum());
		
		int result = pstm.executeUpdate();
		
		DBConnector.disconnect(pstm, conn);
		return result;
	}

	@Override
	public int delete(int num) throws Exception {
		Connection conn = DBConnector.getConnect();
		String sql = "delete from notice where num=?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setInt(1, num);
		
		int result = pstm.executeUpdate();
		
		DBConnector.disconnect(pstm, conn);
		return result;
	}

	@Override
	public List<BoardDTO> selectList(int s, int e, String searchBy, String search) throws Exception {

		List<BoardDTO> list = new ArrayList<BoardDTO>();
		
		Connection conn = DBConnector.getConnect();
		
		//한 페이지에 게시물 30개씩 보여주기. 
		String sql = "select * from "
				+ "(select rownum R, N.* from "
				+ "(select * from notice where "+searchBy+" like ? order by num desc)N) "
				+ "where R between ? and ?";
		PreparedStatement pstm = conn.prepareStatement(sql);
		
		pstm.setString(1, "%"+search+"%");
		pstm.setInt(2, s);
		pstm.setInt(3, e);
		
		ResultSet rs = pstm.executeQuery();
		while(rs.next()) {
			NoticeDTO fbdto = new NoticeDTO();
			
			fbdto.setNum(rs.getInt("num"));
			fbdto.setTitle(rs.getString("title"));
			fbdto.setWriter(rs.getString("writer"));
			fbdto.setContents(rs.getString("contents"));
			fbdto.setReg_date(rs.getDate("reg_date"));
			fbdto.setHit(rs.getInt("hit"));
			
			list.add(fbdto);
		}
		
		DBConnector.disconnect(pstm, conn, rs);
		
		return list;
	}

	@Override
	public BoardDTO selectOne(int num) throws Exception {
		Connection conn = DBConnector.getConnect();
		String sql = "select * from notice where num=?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setInt(1, num);
		
		ResultSet rs = pstm.executeQuery();
		BoardDTO dto = new NoticeDTO();
		if(rs.next()) {
			dto.setTitle(rs.getString("title"));
			dto.setWriter(rs.getString("writer"));
			dto.setContents(rs.getString("contents"));
			dto.setReg_date(rs.getDate("reg_date"));
			dto.setHit(rs.getInt("hit"));
			dto.setNum(rs.getInt("num"));
		}
			
		DBConnector.disconnect(pstm, conn, rs);
		return dto;
	}

	@Override
	public int getTotal(String searchBy, String search) throws Exception {
		int total = 0;
		
		Connection conn = DBConnector.getConnect();
		String sql = "select count(num) from notice "
				+ "where "+ searchBy+" like ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, "%"+search+"%");
		
		ResultSet rs = pstm.executeQuery();
		
		rs.next();
		total = rs.getInt(1);
		DBConnector.disconnect(pstm, conn, rs);
		
		return total;
	}

}
