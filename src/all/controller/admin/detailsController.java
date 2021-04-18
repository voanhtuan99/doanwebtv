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

import all.entity.Detail;

@Transactional
@Entity
@Controller
public class detailsController {
	@Autowired
	SessionFactory factory;
	int iddetail = 0;
	
	@RequestMapping("admin/details")
	public String admin(ModelMap model) {
		Session ses = factory.getCurrentSession();
		String sql = " FROM Detail";
		Query q = ses.createQuery(sql);
		List<Detail> list = q.list();
		model.addAttribute("details", list);
		return "admin/details";
	}
	
	@RequestMapping(value="admin/insertDetails", method=RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("detail", new Detail());
		return "admin/details";
	}
	
	@RequestMapping(value="admin/insertDetails", method=RequestMethod.POST)
	public String acceptInsert(ModelMap model, @ModelAttribute("detail")Detail u, BindingResult errors) {
		if(u.getIddetail() <= 0) {
			errors.rejectValue("iddetail", "detail", "nhap iddetail");
		}
		if(u.getOrder().getIdorder() <= 0) {
			errors.rejectValue("order.idorder", "detail", "nhap id order");
		}
		if(u.getCategory().getIdcate() <= 0) {
			errors.rejectValue("category.idcate", "detail", "nhap id cate");
		}
		if(u.getQuality() <= 0) {
			errors.rejectValue("quality", "detail", "nhap quality");
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
			return "redirect:/admin/details.htm";
		}
		
		Session ses = factory.getCurrentSession();
		String sql = " FROM Detail";
		Query q = ses.createQuery(sql);
		List<Detail> list = q.list();
		model.addAttribute("details", list);
		return "admin/details";
	}
	
	@RequestMapping(value="admin/deleteDetails/{iddetail}")
	public String edit(ModelMap model, @PathVariable("iddetail")int iddetail) {
		Session ses = factory.openSession();
		String sql = "DELETE FROM Detail WHERE IDDETAIL=:iddetail";
		Query q = ses.createQuery(sql);
		q.setParameter("iddetail", iddetail);
		q.executeUpdate();
		return "redirect:/admin/details.htm";
	}
	
	@RequestMapping(value="admin/updateDetails/{iddetail}", method=RequestMethod.GET)
	public String showU(ModelMap model, @PathVariable("iddetail")int iddetail) {
		Session ses = factory.getCurrentSession();
		this.iddetail = iddetail;
		String sql = "FROM Detail WHERE IDDETAIL=:iddetail";
		Query q = ses.createQuery(sql);
		q.setParameter("iddetail", iddetail);
		List<Detail> s = q.list();
		model.addAttribute("list", s);
		model.addAttribute("detail", new Detail());
		return "admin/updateDetails";
	}
	
	@RequestMapping(value="admin/updateDetails/fillAgain/{id}", method=RequestMethod.GET)
	public String again(ModelMap model, @PathVariable("iddetail") int iddetail) {
		Session ses = factory.getCurrentSession();
		String sql = "FROM Detail WHERE IDDETAIL=:iddetail";
		Query q = ses.createQuery(sql);
		q.setParameter("iddetail", iddetail);
		List<Detail> s = q.list();
		model.addAttribute("list", s);
		model.addAttribute("detail", new Detail());
		return "admin/updateDetails";
	}
	
	@RequestMapping(params="acceptDetails", method=RequestMethod.GET)
	public String updateU(ModelMap model) {
		model.addAttribute("detail", new Detail());
		return "admin/updateDetails";
	}
	
	@RequestMapping(params="acceptDetails", method=RequestMethod.POST)
	public String updateU(ModelMap model, @ModelAttribute("detail")Detail s, BindingResult errors) {
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
			return "redirect:/admin/details.htm";
		}
		Session ses = factory.getCurrentSession();
		String sql = "FROM Detail WHERE IDDETAIL=:iddetail";
		Query q = ses.createQuery(sql);
		q.setParameter("iddetail", iddetail);
		List<Detail> list = q.list();
		model.addAttribute("list", list);
		return "admin/updateDetails";
	}
	

	@RequestMapping(params="exitDetails")
	public String exit() {
		return "redirect:/admin/details.htm";
	}
}
