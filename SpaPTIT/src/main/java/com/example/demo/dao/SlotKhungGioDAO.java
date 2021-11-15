package com.example.demo.dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;

import com.example.demo.model.KhungGio;
import com.example.demo.model.SlotKhungGio;

public class SlotKhungGioDAO extends DAO {

	public SlotKhungGioDAO() {
		super();
	}

	public Integer luuSlotKhungGio(SlotKhungGio slotKhungGio) {
		//luu
		String sql = "INSERT INTO slot_khung_gio VALUE(NULL,?,?,?)";
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, slotKhungGio.getNgay());
			ps.setInt(2, slotKhungGio.getGio().getId());
			ps.setInt(3, slotKhungGio.getSlot().getId());

			ps.execute();
		} catch (SQLException ex) {
			ex.printStackTrace();	
		}
		
		//lay ra ban ghi vua luu
		String sql1 = "SELECT * FROM slot_khung_gio ORDER BY id DESC LIMIT 1";
		ResultSet rs = null;
		Integer kq = null;
		try {
			CallableStatement cs = con.prepareCall(sql1);
			rs = cs.executeQuery();
			while(rs.next()) {
				kq = rs.getInt("id");				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kq;
	}
}
