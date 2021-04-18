package all.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import all.bean.Mailer;

@Controller
public class subMemberController {
	@Autowired
	Mailer mailer;
	
	@RequestMapping("sub-member")
	public String subMember(ModelMap model,@RequestParam("subEmail")String to) {
		String from = "truongquynh2525@gmail.com";
		String subject = "TheGioiDiDong Subscribe Member";
		String body = "Cam on ban da dang ky dich vu 'khach hang than quen'";
		mailer.send(from, to, subject, body);
		model.addAttribute("message", "dang ky dich vu thang cong");
		return "redirect:/index.htm";
	}
}
