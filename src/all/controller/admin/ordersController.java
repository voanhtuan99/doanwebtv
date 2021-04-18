package all.controller.admin;

import java.util.List;

import javax.persistence.Entity;
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
import org.springframework.web.bind.annotation.RequestMethod;

import all.entity.Order;
import all.entity.Type;
import all.entity.User;

@Transactional
@Entity
@Controller
public class ordersController {
	@Autowired
	SessionFactory factory;
	int idorder = 0;
	@RequestMapping("admin/orders")
	public String admin(ModelMap model) {
		Session ses = factory.getCurrentSession();
		String sql = " FROM Order";
		Query q = ses.createQuery(sql);
		List<User> list = q.list();
		model.addAttribute("orders", list);
		return "admin/orders";
	}
	
	@RequestMapping(value="admin/insertOrders", method=RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("order", new Order());
		return "admin/orders";
	}
	
	@RequestMapping(value="admin/insertOrders", method=RequestMethod.POST)
	public String acceptInsert(ModelMap model, @ModelAttribute("order")Order u, BindingResult errors) {
		if(u.getIdorder() <= 0) {
			errors.rejectValue("idorder", "order", "nhap idorder");
		}
		if(u.getUser().getId() <= 0) {
			errors.rejectValue("user.id", "order", "nhap id user");
		}
		if(errors.hasErrors()) {
		}
		else {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(u);
				t.commit();
				model.addAttribute("message", "Insert successful");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Failed");
			} finally {
				session.close();
			}
			return "redirect:/admin/orders.htm";
		}
		Session ses = factory.getCurrentSession();
		String sql = " FROM Order";
		Query q = ses.createQuery(sql);
		List<User> list = q.list();
		model.addAttribute("orders", list);
		return "admin/orders";
	}
	
	@RequestMapping(value="admin/deleteOrders/{idorder}")
	public String edit(ModelMap model, @PathVariable("idorder")int idorder) {
		Session ses = factory.openSession();
		String sql = "DELETE FROM Order WHERE IDORDER=:idorder";
		Query q = ses.createQuery(sql);
		q.setParameter("idorder", idorder);
		q.executeUpdate();
		return "redirect:/admin/orders.htm";
	}
	
	@RequestMapping(value="admin/updateOrders/{idorder}", method=RequestMethod.GET)
	public String showU(ModelMap model, @PathVariable("idorder")int idorder) {
		Session ses = factory.getCurrentSession();
		this.idorder = idorder;
		String sql = "FROM Order WHERE IDORDER=:idorder";
		Query q = ses.createQuery(sql);
		q.setParameter("idorder", idorder);
		List<Order> s = q.list();
		model.addAttribute("list", s);
		model.addAttribute("order", new Order());
		return "admin/updateOrders";
	}
	
	@RequestMapping(value="admin/updateOrders/fillAgain/{idorder}", method=RequestMethod.GET)
	public String again(ModelMap model, @PathVariable("idorder") int idorder) {
		Session ses = factory.getCurrentSession();
		String sql = "FROM Order WHERE IDORDER=:idorder";
		Query q = ses.createQuery(sql);
		q.setParameter("idorder", idorder);
		List<Order> s = q.list();
		model.addAttribute("list", s);
		model.addAttribute("order", new Order());
		return "admin/updateOrders";
	}
	
	@RequestMapping(params="acceptOrders", method=RequestMethod.GET)
	public String updateU(ModelMap model) {
		model.addAttribute("order", new Type());
		return "admin/updateOrders";
	}
	
	@RequestMapping(params="acceptOrders", method=RequestMethod.POST)
	public String updateU(ModelMap model, @ModelAttribute("order")Order s, BindingResult errors) {
		if(errors.hasErrors()) {
			model.addAttribute("message", "vui long sua cac loi");
		}
		else {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.update(s);
				t.commit();
				model.addAttribute("message", "Update successful");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Failed");
			} finally {
				session.close();
			}
			return "redirect:/admin/orders.htm";
		}
		Session ses = factory.getCurrentSession();
		String sql = "FROM Order WHERE IDORDER=:idorder";
		Query q = ses.createQuery(sql);
		q.setParameter("idorder", idorder);
		List<Order> list = q.list();
		model.addAttribute("list", list);
		return "admin/updateOrders";
	}
	

	@RequestMapping(params="exitOrders")
	public String exit() {
		return "redirect:/admin/orders.htm";
	}
}
