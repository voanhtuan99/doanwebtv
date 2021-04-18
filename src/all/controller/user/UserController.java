package all.controller.user;

import java.util.List;

import javax.persistence.Entity;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import all.entity.Category;
import all.entity.User;
@Transactional
@Entity
@Controller	
@RequestMapping(value= {"", "/user/"})
public class UserController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping(value= {"index2"})
	public String home(ModelMap model, HttpSession http) {
		model.addAttribute("iphone", "active");
		model.addAttribute("trangchu", "active");
		
		Session ses = factory.getCurrentSession();
		String sql = "FROM Category WHERE NEWITEMS='True' AND IDTYPE=1";	
		Query q = ses.createQuery(sql);
		List<Category> list = q.list();
		model.addAttribute("newProducts", list);
		
		sql = "FROM Category WHERE IDTYPE=1 AND DISCOUNT > 0";
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("topSelling", list);
		
		sql = "FROM Category WHERE IDTYPE=1 ORDER BY NEWID()";
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("widget1", list);
		
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("widget2", list);
		
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("widget3", list);
		
		SP sp = new SP();
		int total = sp.soLuongSanPhamTrongGio(ses, http);
		model.addAttribute("total", total);
		
		return "user/index";
	}
	
	
	@RequestMapping("index") 
	public String index(ModelMap model,HttpSession http) {
		model.addAttribute("iphone", "active");
		model.addAttribute("trangchu", "active");
		
		Session ses = factory.getCurrentSession();
		String sql = "FROM Category WHERE NEWITEMS='True' AND IDTYPE=1";	
		Query q = ses.createQuery(sql);
		List<Category> list = q.list();
		model.addAttribute("newProducts", list);
		
		sql = "FROM Category WHERE IDTYPE=1 AND DISCOUNT > 0";
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("topSelling", list);
		
		sql = "FROM Category WHERE IDTYPE=1 ORDER BY NEWID()";
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("widget1", list);
		
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("widget2", list);
		
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("widget3", list);
		
		return "user/index2";
	}
	
	@RequestMapping("search")
	public String search(ModelMap model, @RequestParam("txtSearch")String txt) {
		model.addAttribute("trangchu", "active");
		Session ses = factory.getCurrentSession();
		String sql = "FROM Category c WHERE c.namecate LIKE '" +  txt + "%'";
		Query q = ses.createQuery(sql);
		List<Category> list = q.list();
		model.addAttribute("products", list);
		return "user/store";
	}
	
	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletRequest rq) {
		session = rq.getSession();
		User u = new User();
		u = (User) session.getAttribute("user");
		session.removeAttribute("user");
		return "redirect:/index.htm";
	}
	
	@RequestMapping("register")
	public String register() {
		return "user/register";
	}
	
	@RequestMapping("blank")
	public String blank() {
		return "user/blank";
	}
}
