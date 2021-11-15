package com.example.demo.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.model.DichVu;
import com.example.demo.model.KhachHang;
import com.example.demo.model.KhungGio;
import com.example.demo.model.LichHen;
import com.example.demo.model.NhanVien;
import com.example.demo.model.Slot;
import com.example.demo.model.SlotKhungGio;

@Controller
@RequestMapping()
public class NhanVienController {
	
	
	private KhungGioDAO khunggioDAO = new KhungGioDAO();
	private DichVuDAO dichVuDAO = new DichVuDAO();
	private SlotDAO slotDAO = new SlotDAO();
	private KhachHangDAO khDAO = new KhachHangDAO();
	private LichHenDAO lichhenDAO = new LichHenDAO();
	private SlotKhungGioDAO skgDAO = new SlotKhungGioDAO();
	@GetMapping(value = { "/", "login" })
	public String login() {
		return "gdDangNhap";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("USER");
		return "gdDangNhap";
	}

	@GetMapping("/home")
	public String home(ModelMap modelMap, @RequestParam(name = "username", required = false) String username,
			@RequestParam(name = "password", required = false) String password, HttpSession session) {
		// Employee user = userRepository.findOneByUsername(username);
		NhanVien nv = new NhanVien();
		nv.setUsername(username);
		nv.setPassword(password);
//		if(nv == null) {
//			modelMap.addAttribute("checkLogin","Tài khoản không tồn tại!!!");
//			return "login";
//		}
		if (password.equals(nv.getPassword())) {
			session.setAttribute("NhanVien", nv);
			return "gdChinh";
		} else {
			modelMap.addAttribute("checkLogin", "Tài khoản hoặc mật khẩu sai !!!");
			return "login";
		}
	}

	@GetMapping("/chondichvu")
	public String chondichvu(HttpSession session) {
		// lấy danh sách khung giờ
		ArrayList<KhungGio> listgio = khunggioDAO.getAll();
		
		// lấy danh sach dịch vụ
		ArrayList<DichVu> listDV = dichVuDAO.getAll();


		session.setAttribute("listGio", listgio);
		session.setAttribute("listDV", listDV);

		return "gdChonDichVu";
	}

	@GetMapping("/chonslot")
	public String chonslot(HttpSession session, @RequestParam(name = "gio") int gioid,
			@RequestParam(name = "dichvu") int dichvuid, @RequestParam(name = "ngay") String ngaychon)
			throws ParseException {
		// lấy danh sách khung giờ vừa chọn
		KhungGio gio = ((List<KhungGio>) session.getAttribute("listGio")).get(gioid);

		// lấy danh sach dịch vụ vừa chọn
		DichVu dv = ((List<DichVu>) session.getAttribute("listDV")).get(dichvuid);

		// lấy ngày vừa chọn
		java.util.Date ngay = new SimpleDateFormat("yyyy-MM-dd").parse(ngaychon);
		java.sql.Date ngaySql = new java.sql.Date(ngay.getTime());

		// set tạm 1 số cái đã chọn
		SlotKhungGio slotKhungGio = new SlotKhungGio();
		slotKhungGio.setGio(gio);
		slotKhungGio.setNgay(ngaySql);
		LichHen lichhen = new LichHen();
		lichhen.setDichvu(dv);
		lichhen.setThoigian(slotKhungGio);
		session.setAttribute("lichhen", lichhen);

		ArrayList<Slot> listSlot = slotDAO.findSlotTrong(ngaySql,gio.getId());

		session.setAttribute("listSlot", listSlot);
		return "gdChonSlot";
	}

	@GetMapping("/timkhachhang")
	public String timkhachhang(HttpSession session, @RequestParam(name = "idSlot", required = false) Integer idSlot,
			@RequestParam(name = "data", required = false) String data) {

		// gọi từ gd tìm slot
		if (idSlot != null) {
			//xóa tt tìm kiếm từ trước
			session.removeAttribute("listKH");
			// lấy slot vừa chọn
			Slot slot = ((ArrayList<Slot>) session.getAttribute("listSlot")).get(idSlot);

			// cập nhật slot vào lịch hẹn
			LichHen lichhen = (LichHen) session.getAttribute("lichhen");
			SlotKhungGio slotKhungGio = lichhen.getThoigian();
			slotKhungGio.setSlot(slot);
			lichhen.setThoigian(slotKhungGio);
			session.setAttribute("lichhen",lichhen);
		}

		// gọi từ gd tìm khách hàng
		if (data != null) {
			ArrayList<KhachHang> listKH = khDAO.findByData(data);

			session.setAttribute("listKH", listKH);
		}
		return "gdTimKhachHang";
	}

	@GetMapping("/xacnhanlich")
	public String xacnhanlich(HttpSession session, @RequestParam(name = "idKH", required = false) Integer idKH) {
		// lấy khach hàng vừa chọn
		KhachHang kh = ((ArrayList<KhachHang>) session.getAttribute("listKH")).get(idKH);

		// cập nhật slot vào lịch hẹn
		LichHen lichhen = (LichHen) session.getAttribute("lichhen");
		lichhen.setKhachhang(kh);
		session.setAttribute("lichhen", lichhen);
		return "gdXacNhanLich";
	}
	
	@GetMapping("/luu")
	public String luu(HttpSession session) {
		LichHen lichhen = (LichHen) session.getAttribute("lichhen");
		SlotKhungGio skg = lichhen.getThoigian();
		
		Integer id = skgDAO.luuSlotKhungGio(skg);
		skg.setId(id);
		lichhen.setThoigian(skg);
		
		lichhenDAO.luuLichHen(lichhen);
		
		return "gdChinh";
	}
}
