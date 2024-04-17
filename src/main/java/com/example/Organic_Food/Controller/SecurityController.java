package com.example.Organic_Food.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SecurityController
{
    @Autowired
    HttpSession session;
    @GetMapping("/default/")
    public String showDefaultPageAfterLogin(HttpServletRequest request)
    {
        System.out.println(request);
        if (request.isUserInRole("ROLE_ADMIN"))
        {
            return "redirect:/dashboard/";
        }
        else if (request.isUserInRole("ROLE_USER"))
        {
            return "redirect:/";
        }
        else if (request.isUserInRole("ROLE_FARMER"))
        {
            return "redirect:/dashboard/";
        }

        // similarly you can check for other designations like manager, analyst etc

        return "redirect:/";
    }
}