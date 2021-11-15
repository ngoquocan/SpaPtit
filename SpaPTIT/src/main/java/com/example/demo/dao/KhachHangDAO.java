package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.demo.model.KhachHang;

public class KhachHangDAO extends DAO{
	public KhachHangDAO() {
		
	}
	
	public ArrayList<KhachHang> findByData(String data){
		ArrayList<KhachHang> kq = new ArrayList<>();
		String sql = "SELECT * FROM khach_hang WHERE ten LIKE '%" + data + "%' OR sdt LIKE '%" + data + "%'";
		ResultSet rs = null;	
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				KhachHang kh = new KhachHang();
				kh.setId(rs.getInt("id"));
				kh.setTen(rs.getString("ten"));
				kh.setNgaysinh(rs.getDate("ngaysinh"));
				kh.setSdt(rs.getString("sdt"));
				kh.setEmail(rs.getString("email"));
				
				kq.add(kh);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kq;
	}
}
