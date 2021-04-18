package all.controller.user;

import java.util.List;

import javax.persistence.Entity;
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

public class vsmartController {	
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("vsmart")
	public String index(ModelMap model) {
		model.addAttribute("vsmart", "active");
		
		Session ses = factory.getCurrentSession();
		String sql = "FROM Category WHERE NEWITEMS='True' AND IDTYPE=3";	
		Query q = ses.createQuery(sql);
		List<Category> list = q.list();
		model.addAttribute("newProducts", list);
		
		sql = "FROM Category WHERE IDTYPE=3 AND DISCOUNT > 0";
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("topSelling", list);
		
		sql = "FROM Category WHERE IDTYPE=3 ORDER BY NEWID()";
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("widget1", list);
		
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("widget2", list);
		
		q = ses.createQuery(sql);
		list = q.list();
		model.addAttribute("widget3", list);
		return "user/index";
	}
}