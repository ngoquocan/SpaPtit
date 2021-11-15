package com.example.demo.dao;

import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import com.example.demo.model.KhungGio;

public class KhungGioDAO extends DAO{
	
	public KhungGioDAO() {
		super();
	}
	
	public ArrayList<KhungGio> getAll(){
		ArrayList<KhungGio> kq = new ArrayList<>();
		String sql = "SELECT * FROM khung_gio";
		ResultSet rs = null;	
		try {
			CallableStatement cs = con.prepareCall(sql);
			rs = cs.executeQuery();
			while(rs.next()) {
				KhungGio kg = new KhungGio();
				kg.setId(rs.getInt("id"));
				kg.setGio(Time.valueOf(rs.getString("gio")));
				
				kq.add(kg);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kq;
	}
}
