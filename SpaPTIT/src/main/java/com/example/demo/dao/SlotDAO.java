package com.example.demo.dao;

import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.example.demo.model.DichVu;
import com.example.demo.model.Slot;

public class SlotDAO extends DAO{
	public SlotDAO() {
		
	}
	
	public ArrayList<Slot> findSlotTrong(Date ngay, int gioId){
		ArrayList<Slot> kq = new ArrayList<>();
		String sql = "SELECT * FROM slot s "
				+ "WHERE s.id NOT IN  "
				+ "(SELECT skg.slot_id FROM slot_khung_gio skg WHERE skg.ngay = ? AND skg.gio_id = ?)";
		ResultSet rs = null;	
		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, ngay);
			ps.setInt(2, gioId);
			rs = ps.executeQuery();
			while(rs.next()) {
				Slot dv = new Slot();
				dv.setId(rs.getInt("id"));
				dv.setTen(rs.getString("ten"));
				kq.add(dv);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kq;
	}
}
