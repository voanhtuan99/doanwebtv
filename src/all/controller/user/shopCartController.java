package all.controller.user;

import java.util.List;

import javax.persistence.Entity;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import all.bean.Mailer;
import all.controller.user.entity.infoCheckout;
import all.entity.Category;
import all.entity.Detail;
import all.entity.Order;
import all.entity.User;

@Transactional
@Entity
@Controller
public class shopCartController {
	@Autowired
	SessionFactory factory;
	@Autowired
	Mailer mailer;
	int sl = 1;
	int idorder = 0;
	
	@RequestMapping("shopCart")
	public String index(ModelMap model, HttpSession http) {
		Session ses = factory.getCurrentSession(); 
		SP sp = new SP();
		User u = new User();
		u = (User) http.getAttribute("user");
		int id = sp.SP_TIMIDUSER(ses, u.getUsername());
		int idorder = sp.SP_TIMIDORDER(ses, id);
		String sql = "FROM Detail WHERE IDORDER=:idorder";
		Query q = ses.createQuery(sql);
		q.setParameter("idorder", idorder);
		List<Detail> list = q.list();
		model.addAttribute("total", list.size());
		model.addAttribute("list", list);
		return "user/cart";
	}
	
	@RequestMapping("deleteCate/{idcate}")
	public String delete(ModelMap model, @PathVariable("idcate")int idcate) {
		Session ses = factory.openSession();
		String sql = "DELETE FROM Detail WHERE IDCATE=:idcate";
		Query q = ses.createQuery(sql);
		q.setParameter("idcate", idcate);
		q.executeUpdate();
		return "redirect:/shopCart.htm";
	}
	
	@RequestMapping(value="edit/{idcate}", params="add")
	public String edit(ModelMap model, @PathVariable("idcate")int idcate, @RequestParam("quality")int qlt, HttpSession http) {
		qlt = qlt + 1;
		this.sl = qlt;
		User u = new User();
		u = (User) http.getAttribute("user");
		Session ses = factory.getCurrentSession();
		
		SP sp = new SP();
		int iddetail = sp.SP_TIMIDDETAIL(ses, idcate);
		int id = sp.SP_TIMIDUSER(ses, u.getUsername());
		int idorder = sp.SP_TIMIDORDER(ses, id);
			
		Order or = new Order();
		or.setIdorder(idorder);
		Category cat = new Category();
		cat.setIdcate(idcate);
		
		Detail dt = new Detail();
		dt.setIddetail(iddetail);
		dt.setOrder(or);
		dt.setCategory(cat);
		dt.setQuality(qlt);
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(dt);			
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return "redirect:/shopCart.htm";
	}
	
	@RequestMapping(value="edit/{idcate}", params="sub")
	public String editsub(ModelMap model, @PathVariable("idcate")int idcate, @RequestParam("quality")int qlt, HttpSession http) {
		if(qlt > 1) {
			qlt = qlt - 1;
			this.sl = qlt;
			User u = new User();
			u = (User) http.getAttribute("user");
			
			Session ses = factory.getCurrentSession();
			
			SP sp = new SP();

			int iddetail = sp.SP_TIMIDDETAIL(ses, idcate);
			int id = sp.SP_TIMIDUSER(ses, u.getUsername());
			int idorder = sp.SP_TIMIDORDER(ses, id);
				
			Order or = new Order();
			or.setIdorder(idorder);
			Category cat = new Category();
			cat.setIdcate(idcate);
			
			Detail dt = new Detail();
			dt.setIddetail(iddetail);
			dt.setOrder(or);
			dt.setCategory(cat);
			dt.setQuality(qlt);
			
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.update(dt);			
				t.commit();
			} catch (Exception e) {
				t.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		return "redirect:/shopCart.htm";
	}
	
	@RequestMapping("checkout")
	public String checkout(ModelMap model, HttpSession http) {
		SP sp = new SP();
		Session ses = factory.getCurrentSession(); 
		
		int total = sp.soLuongSanPhamTrongGio(ses, http);
		if(total == 0) {
			return "redirect:/shopCart.htm";
		}
		User u = new User();
		u = (User) http.getAttribute("user");
		
		String sql = "FROM Detail WHERE IDORDER=:idorder";
		Query q = ses.createQuery(sql);
		

		int id = sp.SP_TIMIDUSER(ses, u.getUsername());
		this.idorder = sp.SP_TIMIDORDER(ses, id);
		q.setParameter("idorder", idorder);
		List<Detail> list = q.list();
		sql = "EXEC SP_TONGTIEN " + idorder;
		q = ses.createSQLQuery(sql);
		List<Double> tt = q.list();
		double tong = tt.get(0);
		model.addAttribute("tongtien", tong);
		model.addAttribute("list", list);
		model.addAttribute("total", total);
		return "user/checkout";
	}
	
	@RequestMapping("buy")
	public String buy(ModelMap model, @ModelAttribute("infoCheckout")infoCheckout in, @RequestParam("hoten")String hoten, @RequestParam("email")String email, 
			@RequestParam("diachi")String diachi, @RequestParam("tel")String tel, BindingResult errors, HttpSession http) {
		if(hoten.length() < 3) {
			errors.rejectValue("hoten", "infoCheckout", "ten khong duoc trong");
		}
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		if(email.length() == 0) {
			errors.rejectValue("email", "infoCheckout", "email khong duoc trong");
		}
		else if(in.getEmail().trim().matches(regex) == false){
			errors.rejectValue("email", "user", "email khong dung dinh dang");
		}
		if(diachi.length() == 0) {
			errors.rejectValue("diachi", "infoCheckout", "dia chi khong duoc trong");
		}
		if(tel.length() == 0) {
			errors.rejectValue("tel", "infoCheckout", "so dien thoai khong duoc trong");
		}
		if(errors.hasErrors()) {
			
		}
		else {
			Session ses = factory.openSession();
			String sql = "DELETE FROM Detail WHERE IDORDER=:idorder";
			Query q = ses.createQuery(sql);
			q.setParameter("idorder", this.idorder);
			q.executeUpdate();
			
			sql = "DELETE FROM Order WHERE IDORDER=:idorder";
			q = ses.createQuery(sql);
			q.setParameter("idorder", this.idorder);
			q.executeUpdate();
			
			String from = "truongquynh2525@gmail.com";
			String subject = "TheGioiDiDong";
			String body = "Hang ban da dat se chuyen toi noi som nhat co the. Cam on da tin tuong va su dung dich vu chung toi";
			mailer.send(from, email, subject, body);
			SP sp = new SP();
			int total = sp.soLuongSanPhamTrongGio(ses, http);
			model.addAttribute("total", total);
			return "user/contact";
		}

		return checkout(model, http);
	}
}
