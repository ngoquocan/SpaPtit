package com.example.demo.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.example.demo.model.LichHen;

public class LichHenDAO extends DAO{

	public LichHenDAO() {
		super();
	}
	
	public boolean luuLichHen(LichHen lichHen) {
		String sql = "INSERT INTO lich_hen VALUE(NULL,?,?,?)";
		boolean kq = false;
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setInt(1, lichHen.getKhachhang().getId());
			ps.setInt(2, lichHen.getThoigian().getId());
			ps.setInt(3, lichHen.getDichvu().getId());

			kq = ps.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();	
		}
		return kq;
	}
}
