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

import all.entity.Response;

@Transactional
@Entity
@Controller
public class responseController {
	@Autowired
	SessionFactory factory;
	int idres = 0;
	
	@RequestMapping("admin/responses")
	public String admin(ModelMap model) {
		Session ses = factory.getCurrentSession();
		String sql = " FROM Response";
		Query q = ses.createQuery(sql);
		List<Response> list = q.list();
		model.addAttribute("responses", list);
		return "admin/responses";
	}
	
	@RequestMapping(value="admin/insertResponses", method=RequestMethod.GET)
	public String insert(ModelMap model) {
		model.addAttribute("response", new Response());
		return "admin/responses";
	}
	
	@RequestMapping(value="admin/insertResponses", method=RequestMethod.POST)
	public String acceptInsert(ModelMap model, @ModelAttribute("response")Response r, BindingResult errors) {
		if(r.getIdres() <= 0) {
			errors.rejectValue("idres", "response", "nhap idres");
		}
		if(r.getUser().getId() <= 0) {
			errors.rejectValue("user.id", "response", "nhap id user");
		}
		if(r.getComments().trim().length() == 0) {
			errors.rejectValue("comments", "response", "nhap binh luan");
		}
		if(r.getStars() <= 0) {
			errors.rejectValue("stars", "response", "nhap sao");
		}
		if(errors.hasErrors()) {
			
		}
		else {
			Session session = factory.openSession();
			Transaction t = session.beginTransaction();
			try {
				session.save(r);
				t.commit();
				model.addAttribute("message", "Insert successful");
			} catch (Exception e) {
				t.rollback();
				model.addAttribute("message", "Failed");
			} finally {
				session.close();
			}
			return "redirect:/admin/responses.htm";
		}
		Session ses = factory.getCurrentSession();
		String sql = " FROM Response";
		Query q = ses.createQuery(sql);
		List<Response> list = q.list();
		model.addAttribute("responses", list);
		return "admin/responses";
	}
	
	@RequestMapping(value="admin/deleteResponses/{idres}")
	public String delete(ModelMap model, @PathVariable("idres")int idres) {
		Session ses = factory.openSession();
		String sql = "DELETE FROM Response WHERE IDRES=:idres";
		Query q = ses.createQuery(sql);
		q.setParameter("idres", idres);
		q.executeUpdate();
		return "redirect:/admin/responses.htm";
	}
	
	@RequestMapping(value="admin/updateRes/{idres}", method=RequestMethod.GET)
	public String showU(ModelMap model, @PathVariable("idres")int idres) {
		Session ses = factory.getCurrentSession();
		this.idres = idres;
		String sql = "FROM Respnose WHERE IDRES=:idres";
		Query q = ses.createQuery(sql);
		q.setParameter("idres", idres);
		List<Response> s = q.list();
		model.addAttribute("list", s);
		model.addAttribute("res", new Response());
		return "admin/updateResponses";
	}
	
	@RequestMapping(value="admin/updateRes/fillAgain/{idres}", method=RequestMethod.GET)
	public String again(ModelMap model, @PathVariable("idres") int idres) {
		Session ses = factory.getCurrentSession();
		String sql = "FROM Response WHERE IDRES=:idres";
		Query q = ses.createQuery(sql);
		q.setParameter("idres", idres);
		List<Response> s = q.list();
		model.addAttribute("list", s);
		model.addAttribute("res", new Response());
		return "admin/updateResponses";
	}
	
	@RequestMapping(params="acceptResponses", method=RequestMethod.GET)
	public String updateU(ModelMap model) {
		model.addAttribute("res", new Response());
		return "admin/updateResponses";
	}
	
	@RequestMapping(params="acceptResponses", method=RequestMethod.POST)
	public String updateU(ModelMap model, @ModelAttribute("res")Response s, BindingResult errors) {
		if(s.getStars() <= 0) {
			errors.rejectValue("stars", "res", "nhap so sao");
		}
		if(s.getComments().trim().length() == 0) {
			errors.rejectValue("comments", "res", "nhap comments");
		}
		

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
			return "redirect:/admin/responses.htm";
		}
		Session ses = factory.getCurrentSession();
		String sql = "FROM Response WHERE IDRES=:idres";
		Query q = ses.createQuery(sql);
		q.setParameter("idres", idres);
		List<Response> list = q.list();
		model.addAttribute("list", list);
		return "admin/updateResponses";
	}
	

	@RequestMapping(params="exitResponses")
	public String exit() {
		return "redirect:/admin/responses.htm";
	}
}
