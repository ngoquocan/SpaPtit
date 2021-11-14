package com.example.demo.dao;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.DichVu;
import com.example.demo.model.KhungGio;
import com.example.demo.model.LichHen;
import com.example.demo.model.NhanVien;
import com.example.demo.model.Slot;
import com.example.demo.model.SlotKhungGio;

import net.bytebuddy.agent.builder.AgentBuilder.FallbackStrategy.Simple;

@Controller
@RequestMapping()
public class NhanVienController {
	@GetMapping(value = {"/","login"})
	public String login() {
		return "gdDangNhap";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("USER");
		return "gdDangNhap";
	}
	@GetMapping("/home")
	public String home(ModelMap modelMap,@RequestParam(name = "username",required = false) String username,
			@RequestParam(name = "password",required = false) String password,
			HttpSession session) {	
		//Employee user = userRepository.findOneByUsername(username);
		NhanVien nv = new NhanVien();
		nv.setUsername(username);
		nv.setPassword(password);
//		if(nv == null) {
//			modelMap.addAttribute("checkLogin","Tài khoản không tồn tại!!!");
//			return "login";
//		}
		if(password.equals(nv.getPassword())) {
			session.setAttribute("NhanVien", nv);
			return "gdChinh";
		}
		else {
			modelMap.addAttribute("checkLogin","Tài khoản hoặc mật khẩu sai !!!");
			return "login";
		}
	}
	@GetMapping("/chondichvu")
	public String chondichvu(HttpSession session) {
		//lấy danh sách khung giờ
		ArrayList<KhungGio> listgio = new ArrayList<>();
		listgio.add(new KhungGio(1, Time.valueOf("07:00:00")));
		listgio.add(new KhungGio(1, Time.valueOf("08:00:00")));
		listgio.add(new KhungGio(1, Time.valueOf("09:00:00")));
		listgio.add(new KhungGio(1, Time.valueOf("10:00:00")));
		
		//lấy danh sach dịch vụ
		ArrayList<DichVu> listDV = new ArrayList<>();
		DichVu dv = new DichVu();
		dv.setTen("aaa");
		listDV.add(dv);
		listDV.add(dv);
		listDV.add(dv);
		
		session.setAttribute("listGio",listgio);
		session.setAttribute("listDV", listDV);
		
		return "gdChonDichVu";
	}
	
	@GetMapping("/chonslot")
	public String chonslot(HttpSession session,@RequestParam(name = "gio") int gioid,
			@RequestParam(name = "dichvu") int dichvuid, @RequestParam(name = "ngay") String ngaychon) throws ParseException {
		//lấy danh sách khung giờ vừa chọn
		KhungGio gio = ((List<KhungGio>) session.getAttribute("listGio")).get(gioid);
				
		//lấy danh sach dịch vụ	vừa chọn
		DichVu dv = ((List<DichVu>) session.getAttribute("listDV")).get(dichvuid);

		//lấy ngày vừa chọn
		java.util.Date ngay = new SimpleDateFormat("yyyy-MM-dd").parse(ngaychon);
		java.sql.Date ngaySql = new java.sql.Date(ngay.getTime());
		
		//set tạm 1 số cái đã chọn
		SlotKhungGio slotKhungGio = new SlotKhungGio();
		slotKhungGio.setGio(gio);
		slotKhungGio.setNgay(ngaySql);
		LichHen lichhen = new LichHen();
		lichhen.setDichVu(dv);
		lichhen.setThoigian(slotKhungGio);
		session.setAttribute("lichhen", lichhen);
		
		ArrayList<Slot> listSlot= new ArrayList<>();
		Slot slot = new Slot();
		slot.setId(1);
		slot.setTen("Slot1");
		listSlot.add(slot);
		listSlot.add(slot);
		listSlot.add(slot);
		listSlot.add(slot);
		listSlot.add(slot);
		
		session.setAttribute("listSlot", listSlot);
		return "gdChonSlot";
	}
	
	@GetMapping("/timkhachhang")
	public String timkhachhang(HttpSession session,@RequestParam(name = "idSlot",required = false) int idSlot) {
		//lấy slot vừa chọn
		Slot slot = ((ArrayList<Slot>) session.getAttribute("listSlot")).get(idSlot);
		
		//cập nhật slot vào lịch hẹn
		LichHen lichhen = (LichHen) session.getAttribute("lichhen");
		SlotKhungGio slotKhungGio = lichhen.getThoigian();
		slotKhungGio.setSlot(slot);
		
		return "gdTimKhachHang";
	}
}

