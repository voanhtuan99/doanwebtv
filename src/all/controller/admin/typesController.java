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

import all.entity.Type;

@Transactional
@Entity
@Controller
public class typesController {
	@Autowired
	SessionFactory factory;
	int idtype = 0;

	@RequestMapping("admin/types")
	public String admin(ModelMap model) {
		Session ses = factory.getCurrentSession();
		String sql = " FROM Type";
		Query q = ses.createQuery(sql);
		List<Type> list = q.list();
		model.addAttribute("typesList", list);
		return "admin/types";
	}
	
	@RequestMapping(value="admin/insertTypes", method=RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("type", new Type());
		return "admin/types";
	}
	
	@RequestMapping(value="admin/insertTypes", method=RequestMethod.POST)
	public String acceptInsert(ModelMap model, @ModelAttribute("type")Type type, BindingResult errors) {
		if(type.getIdtype() <= 0) {
			errors.rejectValue("idtype", "type", "nhap idtype");
		}
		if(type.getNametype().trim().length() == 0) {
			errors.rejectValue("nametype", "type", "nhap name");
		}
		
		if(errors.hasErrors()) {
		}
		else {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(type);
				t.commit();
				model.addAttribute("message", "Insert successful");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Failed");
			} finally {
				session.close();
			}
			return "redirect:/admin/types.htm";
		}	
		Session ses = factory.getCurrentSession();
		String sql = " FROM Type";
		Query q = ses.createQuery(sql);
		List<Type> list = q.list();
		model.addAttribute("typesList", list);
		return "admin/types";
	}
	
	@RequestMapping(value="admin/deleteTypes/{idtype}")
	public String delete(ModelMap model, @PathVariable("idtype")int idtype) {
		Session ses = factory.openSession();
		String sql = "DELETE FROM Type WHERE IDTYPE=:idtype";
		Query q = ses.createQuery(sql);
		q.setParameter("idtype", idtype);
		q.executeUpdate();
		return "redirect:/admin/types.htm";
	}
	
	@RequestMapping(value="admin/updateTypes/{idtype}", method=RequestMethod.GET)
	public String showU(ModelMap model, @PathVariable("idtype")int idtype) {
		Session ses = factory.getCurrentSession();
		this.idtype = idtype;
		String sql = "FROM Type WHERE IDTYPE=:idtype";
		Query q = ses.createQuery(sql);
		q.setParameter("idtype", idtype);
		List<Type> s = q.list();
		model.addAttribute("list", s);
		model.addAttribute("type", new Type());
		return "admin/updateTypes";
	}
	
	@RequestMapping(value="admin/updateTypes/fillAgain/{idtype}", method=RequestMethod.GET)
	public String again(ModelMap model, @PathVariable("idtype") int idtype) {
		Session ses = factory.getCurrentSession();
		String sql = "FROM Type WHERE IDTYPE=:idtype";
		Query q = ses.createQuery(sql);
		q.setParameter("idtype", idtype);
		List<Type> s = q.list();
		model.addAttribute("list", s);
		model.addAttribute("type", new Type());
		return "admin/updateTypes";
	}
	
	@RequestMapping(params="acceptTypes", method=RequestMethod.GET)
	public String updateU(ModelMap model) {
		model.addAttribute("type", new Type());
		return "admin/updateTypes";
	}
	
	@RequestMapping(params="acceptTypes", method=RequestMethod.POST)
	public String updateU(ModelMap model, @ModelAttribute("type")Type s, BindingResult errors) {
		if(s.getNametype().trim().length() == 0) {
			errors.rejectValue("nametype", "type", "nhap nametype");
		}
		
		if(errors.hasErrors()) {
			model.addAttribute("message", "vui long sua cac loi sau");
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
			return "redirect:/admin/types.htm";
		}
		Session ses = factory.getCurrentSession();
		String sql = "FROM User WHERE IDTYPE=:idtype";
		Query q = ses.createQuery(sql);
		q.setParameter("idtype", idtype);
		List<Type> list = q.list();
		model.addAttribute("list", list);
		return "admin/updateTypes";
	}
	

	@RequestMapping(params="exitTypes")
	public String exit() {
		return "redirect:/admin/types.htm";
	}
}
