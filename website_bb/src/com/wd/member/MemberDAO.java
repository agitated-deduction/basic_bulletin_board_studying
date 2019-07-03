package com.wd.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.wd.util.DBConnector;

public class MemberDAO {
	public int join(MemberDTO dto) throws Exception{
		Connection conn = DBConnector.getConnect();
		String sql = "insert into member(id, password, email, gender, name) "
				+ "values(?, ?, ?, ?, ?)";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, dto.getId());
		pstm.setString(2, dto.getPassword());
		pstm.setString(3, dto.getEmail());
		pstm.setString(4, dto.getGender());
		pstm.setString(5, dto.getName());
		
		int result = pstm.executeUpdate();
		
		DBConnector.disconnect(pstm, conn);
		
		return result;
	}
	public boolean memberCheck(MemberDTO dto) throws Exception{
		Connection conn = DBConnector.getConnect();
		String sql = "select * from member where id=? and password=?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, dto.getId());
		pstm.setString(2, dto.getPassword());
		
		ResultSet rs = pstm.executeQuery();
		boolean result = false;
		
		if(rs.next()) {
			result = true;
			dto.setEmail(rs.getString("email"));
			dto.setGender(rs.getString("gender"));
			dto.setName(rs.getString("name"));
		}
		DBConnector.disconnect(pstm, conn, rs);
		return result;
	}
	public int modify(MemberDTO dto) throws Exception{
		Connection conn = DBConnector.getConnect();
		String sql = "update member set gender = ?, email = ?, name = ? where id = ?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, dto.getGender());
		pstm.setString(2, dto.getEmail());
		pstm.setString(3, dto.getName());
		pstm.setString(4, dto.getId());
		
		int result = pstm.executeUpdate();
		
		DBConnector.disconnect(pstm, conn);
		
		return result;
	}
	public MemberDTO myPage(String id) throws Exception{
		Connection conn = DBConnector.getConnect();
		String sql = "select * from member where id=?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, id);
		
		ResultSet rs = pstm.executeQuery();
		
		MemberDTO dto = new MemberDTO();
		if(rs.next()) {
			dto.setId(id);
			dto.setPassword(rs.getString("password"));
			dto.setEmail(rs.getString("email"));
			dto.setGender(rs.getString("gender"));
			dto.setName(rs.getString("name"));
		}
		DBConnector.disconnect(pstm, conn, rs);
		return dto;
	}
	public int withdrawal(String id) throws Exception{
		Connection conn = DBConnector.getConnect();
		String sql = "delete from member where id=?";
		
		PreparedStatement pstm = conn.prepareStatement(sql);
		pstm.setString(1, id);
		
		int result = pstm.executeUpdate();
		
		DBConnector.disconnect(pstm, conn);
		return result;
	}
}
