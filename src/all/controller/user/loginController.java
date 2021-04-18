package all.controller.user;

import javax.persistence.Entity;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import all.entity.User;

@Transactional
@Entity
@Controller
public class loginController {
	@Autowired
	SessionFactory factory;
	
	@RequestMapping("login")
	public String login(ModelMap model) {
		return "user/login";
	}
	
	@RequestMapping("infoLogin") 
	public String showUser( ModelMap model, @ModelAttribute("user") User user,@RequestParam("username") String username,
			@RequestParam("password")String password, BindingResult result, HttpSession http) {
		if(username.length() == 0) {
			result.rejectValue("username", "user", "tai khoan khong duoc de trong");
		}
		if(password.length() == 0) {
			result.rejectValue("password", "user", "password khong the de trong");
		}
		if(result.hasErrors()) {
			
		}
		else {
			Session ses = factory.openSession();
			SP sp = new SP();
			int id = sp.checkLogin(ses, username, password);
			if(id == 0) {
				model.addAttribute("message", "DANG NHAP KHONG DUNG");
				return "user/login";
			}
			else {
				http.setAttribute("user", user);
				System.out.println("dang nhap dung");
				System.out.println(username + " " + password);
				if(username.equals("admin") && password.equals("admin")) {
					return "redirect:/admin/users.htm";
				}
			}
			
			return "redirect:/index2.htm";
		}
		return "user/login";
	}
}
