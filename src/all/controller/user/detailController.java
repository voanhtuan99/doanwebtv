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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import all.entity.Category;
import all.entity.Detail;
import all.entity.Order;
import all.entity.Response;
import all.entity.User;

@Transactional
@Entity
@Controller
public class detailController {
	@Autowired
	SessionFactory factory;
	public List<Category> list;
	private int id;
	private int idorder;
	private int idcate;
	private int iddetail;
	private List<Integer> ID;
	
	@RequestMapping("detail/{idcate}")
	public String detail(ModelMap model, @PathVariable("idcate")int idcate, HttpSession http) {
		Session ses = factory.getCurrentSession();
		this.idcate = idcate;
		String sql = "FROM Category WHERE IDCATE=:idcate";	
		Query q = ses.createQuery(sql);
		q.setParameter("idcate", idcate);
		list = q.list();
		SP sp = new SP();
		int total = sp.soLuongSanPhamTrongGio(ses, http);
		model.addAttribute("total", total);
		return "redirect:/product.htm";
	}
	
	@RequestMapping("product")
	public String index(ModelMap model, HttpSession http) {
		SP sp = new SP();
		Session ses = factory.getCurrentSession();
		List<Response> listRes = sp.DanhSachReviews(ses, idcate);
		model.addAttribute("res", listRes);
		model.addAttribute("detail", list);
		int total = sp.soLuongSanPhamTrongGio(ses, http);
		model.addAttribute("total", total);
		return "user/product";
	}
	
	
	@RequestMapping("addToCart") 
	public String addCart(ModelMap model, @RequestParam("soluong") int soluong, HttpSession http) {
		SP sp = new SP();
		Session ses = factory.getCurrentSession();
	
		String sql;
		Query q;
		User u = new User();
		u = (User) http.getAttribute("user");
		int id = sp.SP_TIMIDUSER(ses, u.getUsername());
		int idorder = sp.SP_TIMIDORDER(ses, id);

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		if(idorder == 0) {
			sql = "EXEC SP_SINHIDORDER";	
			q = ses.createSQLQuery(sql);
			ID = q.list();
			idorder = ID.get(0) + 1;
			System.out.println("idorder " + idorder);
			Order or = new Order();
			or.setIdorder(idorder);
			User us = new User();
			us.setId(id);
			or.setUser(us);;
			try {
				session.save(or);			
				t.commit();
				System.out.println("ok");
			} catch (Exception e) {
				t.rollback();
				e.printStackTrace();
			} finally {
				session.close();
			}
		}
		
		idorder = sp.SP_TIMIDORDER(ses, id);
		sql = "EXEC SP_SINHIDDETAIL";
		q = ses.createSQLQuery(sql);
		ID = q.list();
		iddetail = ID.get(0) + 1;
		
		Detail dt = new Detail();
		dt.setIddetail(iddetail);
		
		Order or = new Order();
		or.setIdorder(idorder);
		dt.setOrder(or);
		Category cat = new Category();
		cat.setIdcate(idcate);
		dt.setCategory(cat);
		dt.setQuality(soluong);
		
		try {
			session.save(dt);			
			t.commit();
			System.out.println("ok");
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		int total = sp.soLuongSanPhamTrongGio(ses, http);
		model.addAttribute("total", total);
		model.addAttribute("detail", list);
		return "user/product";
	}
	
	@RequestMapping("reviews")
	public String reviews(ModelMap model, @RequestParam("rating") int stars, @RequestParam("cmt")String cmt, HttpSession http) {
		Session session = factory.getCurrentSession();
		String hql = "EXEC SP_SINHIDRES";
		Query q = session.createSQLQuery(hql);
		List<Integer> IDRES = q.list();
		int idres = IDRES.get(0) + 1;
		
		SP sp = new SP();
		User u = new User();
		u = (User) http.getAttribute("user");
		System.out.println("asdasd " + u.getUsername());
		User s = new User();
		s.setId(sp.SP_TIMIDUSER(session, u.getUsername()));
		
		Category c = new Category();
		c.setIdcate(idcate);
		
		Response res = new Response();
		res.setIdres(idres);
		res.setStars(stars);
		res.setComments(cmt);
		res.setCategory(c);
		res.setUser(s);
	
		Session ses = factory.openSession();
		Transaction t = ses.beginTransaction();
		try {
			ses.save(res);			
			t.commit();
		} catch (Exception e) {
			t.rollback();
			e.printStackTrace();
		} finally {
			ses.close();
		}
		int total = sp.soLuongSanPhamTrongGio(session, http);
		model.addAttribute("total", total);
		return "redirect:/product.htm";
	}
}
