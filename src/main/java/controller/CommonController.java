package controller;

import lombok.extern.log4j.Log4j;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Log4j
public class CommonController {

    @GetMapping("/accessError")
    public void accessDenied(Authentication auth, Model model) {
        log.info("access Denied : " + auth);
        model.addAttribute("msg", "Access Denied~~~");
    }

    @GetMapping("/login")
    public String loginInput(String error, String logout, Model model) {

        log.info("error:"+error);
        log.info("logout:"+logout);

        if(error!= null) {
            model.addAttribute("error","Login Error Check Your Account");
        }
        if(logout != null) {
            model.addAttribute("logout","Logout!!");
        }
        return "customLogin";
    }

    @GetMapping("/logout")
    public String logoutGET() {

        log.info("custom logout");

        return "customLogout";
    }
}
