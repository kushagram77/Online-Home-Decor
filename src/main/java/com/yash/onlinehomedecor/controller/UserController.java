package com.yash.onlinehomedecor.controller;

import com.yash.onlinehomedecor.command.LoginCommand;
import com.yash.onlinehomedecor.command.UserCommand;
import com.yash.onlinehomedecor.domain.User;
import com.yash.onlinehomedecor.exception.UserBlockedException;
import com.yash.onlinehomedecor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = {"/", "/index"})
    public String index(Model model) {
        model.addAttribute("command", new LoginCommand());
        return "index"; // JSP - /WEB-INF/view/index.jsp
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String handleLogin(@ModelAttribute("command") LoginCommand cmd, Model model, HttpSession session) {
        try {
            User loggedInUser  = userService.login(cmd.getLoginName(), cmd.getPassword());
            addUserInSession(loggedInUser , session);
            return "redirect:user/dashboard"; // Adjust according to your user dashboard URL
        } catch (UserBlockedException ex) {
            model.addAttribute("err", ex.getMessage());
            return "index"; // JSP - Login FORM
        }
    }

    @RequestMapping(value = "/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:index?act=lo";
    }

    @RequestMapping(value = "/user/dashboard")
    public String userDashboard() {
        return "dashboard_user"; // JSP for user dashboard
    }

    @RequestMapping(value = "/admin/dashboard")
    public String adminDashboard() {
        return "dashboard_admin"; // JSP for admin dashboard
    }

    @RequestMapping(value = "/admin/users")
    public String getUserList(Model model) {
        List<User> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "users"; // JSP for displaying user list
    }

    @RequestMapping(value = "/reg_form")
    public String registrationForm(Model model) {
        model.addAttribute("command", new UserCommand());
        return "reg_form"; // JSP for registration form
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String registerUser (@ModelAttribute("command") UserCommand cmd, Model model) {
        try {
            User user = cmd.getUser ();
            userService.register(user);
            return "redirect:index?act=reg"; // Redirect to login page after successful registration
        } catch (DuplicateKeyException e) {
            model.addAttribute("err", "Email is already registered. Please select another email.");
            return "reg_form"; // JSP for registration form
        }
    }

    private void addUserInSession(User user, HttpSession session) {
        session.setAttribute("user", user);
        session.setAttribute("userId", user.getId());
        session.setAttribute("role", user.getRole());
    }
}