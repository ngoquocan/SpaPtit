package com.example.demo.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.demo.model.DichVu;

public class DichVuDAO extends DAO{
	public DichVuDAO() {
		
	}
	
	public ArrayList<DichVu> getAll(){
		ArrayList<DichVu> kq = new ArrayList<>();
		String sql = "SELECT * FROM dich_vu";
		ResultSet rs = null;	
		try {
			CallableStatement cs = con.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				DichVu dv = new DichVu();
				dv.setId(rs.getInt("id"));
				dv.setTen(rs.getString("ten"));
				dv.setGia(rs.getFloat("gia"));
				
				kq.add(dv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kq;
	}
}
