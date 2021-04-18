package all.controller.user;

import java.util.List;

import javax.persistence.Entity;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import all.entity.Category;

@Transactional
@Entity
@Controller
public class samsungController {	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("samsung")
	public String index(ModelMap model, HttpSession http) {
		model.addAttribute("samsung", "active");
		
		Session ses = factory.getCurrentSession();
		String sql = "FROM Category WHERE NEWITEMS='True' AND IDTYPE=2";	
		Query q = ses.createQuery(sql);
		List<Category> list = q.list();
		model.addAttribute("newProducts", list);
		
		sql = "FROM Category WHERE IDTYPE=2 AND DISCOUNT > 0";
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("topSelling", list);
		
		sql = "FROM Category WHERE IDTYPE=2 ORDER BY NEWID()";
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
}
