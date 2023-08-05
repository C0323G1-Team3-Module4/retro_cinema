package com.example.retro_cinema.login;

import com.example.retro_cinema.customer.dto.CustomerDto;
import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.customer.service.ICustomerService;
import com.example.retro_cinema.user.dto.AccountUserDto;
import com.example.retro_cinema.user.dto.UserDto;
import com.example.retro_cinema.user.model.AccountUser;
import com.example.retro_cinema.user.model.Roles;
import com.example.retro_cinema.user.service.account.IAccountService;
import com.example.retro_cinema.util.WebUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.util.Calendar;
import java.util.Date;

@Controller
@Lazy
public class Login {
    @Autowired
    private IAccountService iAccountService;
    @Autowired
    private ICustomerService iCustomerService;
    @Autowired
    private PasswordEncoder passwordEncoder;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginPage(Model model) {
        model.addAttribute("accountDto", new AccountUserDto());
        return "loginPage";
    }

    @GetMapping("/logoutSuccessful")
    public String logout(Model model, RedirectAttributes redirectAttributes) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            SecurityContextHolder.clearContext();
            redirectAttributes.addFlashAttribute("message", "successful logout");
        }
        redirectAttributes.addFlashAttribute("message", "Logout successful");
        model.addAttribute("info", null);
        return "home";
    }


    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal, RedirectAttributes redirectAttributes) {
        String userName = principal.getName();
        AccountUser accountUser = iAccountService.findByUsername(userName);
        if (accountUser.getRoles().getRoleName().equals("ROLE_USER")) {
            if (!accountUser.isEnabled()) {
                redirectAttributes.addFlashAttribute("fail", "Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.");
                return "redirect:/login";
            } else {
                model.addAttribute("info", accountUser);
                System.out.println("userName: " + userName);
                return "/home";
            }
        } else if (accountUser.getRoles().getRoleName().equals("ROLE_ADMIN")) {
            System.out.println("userName: " + userName);
            model.addAttribute("info", accountUser);
            return "/home";
        }
        return "/home";
    }

    @GetMapping("/400")
    public String accountDenied(Model model, Principal principal) {
        if (principal != null) {
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo = WebUtils.toString(loginedUser);
            AccountUser accountUser = iAccountService.findByEmail(principal.getName());
            model.addAttribute("userInfo", userInfo);
            String message = "Hi " + principal.getName()
                    + " You do not have permission to access this page!";
            model.addAttribute("info", iCustomerService.findByIdAccount(accountUser.getId()));
            model.addAttribute("message", message);
        }
        return "400Page";
    }

    @PostMapping("/signup")
    public String signup(@Valid @ModelAttribute AccountUserDto accountUserDto, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) throws UnsupportedEncodingException, MessagingException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accountDto", new AccountUserDto());
            return "loginPage";
        }
        if (iAccountService.findByEmail(accountUserDto.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("fail", "This email already exists!");
        } else {
            accountUserDto.setExpiryDate(calculateExpiryDate());
            Roles roles = iAccountService.findRoleById(2);
            AccountUser accountUser = new AccountUser();
            BeanUtils.copyProperties(accountUserDto, accountUser);
            accountUser.setRoles(roles);
            String cryptedPass = passwordEncoder.encode(accountUser.getPass());
            System.out.println(cryptedPass);
            accountUser.setPass(cryptedPass);
            iAccountService.createAccount(accountUser);
            Customer customer = new Customer(accountUser);
            iCustomerService.create(customer);
            String siteURL = getSiteURL(request);
            iAccountService.sendVerificationEmail(accountUser, siteURL);
            redirectAttributes.addFlashAttribute("success", "You have signed up successfully! Please check your email to verify your account.");
        }
        return "redirect:/login";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code, RedirectAttributes redirectAttributes) {
        if (iAccountService.verify(code)) {
            redirectAttributes.addFlashAttribute("success", "Congratulations, your account has been verified.");
        } else {
            redirectAttributes.addFlashAttribute("fail", "Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.");
        }
        return "redirect:/login";
    }

    @GetMapping("/email")
    public String email() {
        return "email_reset_pw";
    }

    @PostMapping("/confirm_email")
    public String confirm_email(@RequestParam("email") String email, HttpServletRequest request, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, MessagingException {
        AccountUser accountUser = iAccountService.findByEmail(email);
        accountUser.setExpiryDate(calculateExpiryDate());
        iAccountService.reset(accountUser);
        String siteURL = getSiteURL(request);
        iAccountService.sendVerificationReset(accountUser, siteURL);
        redirectAttributes.addFlashAttribute("success", "Please check your email to verify your account.");
        return "redirect:/login";
    }

    @GetMapping("/reset_pw")
    public String reset_pw(@ModelAttribute Customer customer, Model model) {
        return "reset_pw";
    }

    @PostMapping("/new_pw")
    public String new_pw(@RequestParam("new_pw") String new_pw,
                         @ModelAttribute AccountUser accountUser,
                         RedirectAttributes redirectAttributes) {
        iAccountService.reset_pw(accountUser, new_pw);
        redirectAttributes.addFlashAttribute("success", "Password change successful.");
        return "redirect:/login";
    }

    @GetMapping("/verify_reset")
    public String verify_reset(@RequestParam("code") String code, Model model,
                               RedirectAttributes redirectAttributes) {
        String email = null;
        if (iAccountService.findByCode(code) != null) {
            AccountUser accountUser = iAccountService.findByCode(code);
            email = accountUser.getEmail();
        }
        if (iAccountService.verifyReset(code)) {
            AccountUser accountUser = iAccountService.findByEmail(email);
            model.addAttribute("customers", accountUser);
            return "reset_pw";
        } else {
            redirectAttributes.addFlashAttribute("fail", "Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.");
            return "redirect:/login";
        }
    }

    private Date calculateExpiryDate() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MINUTE, 1);
        return new Date(cal.getTime().getTime());
    }

    private String getSiteURL(HttpServletRequest request) {
        String siteURL = request.getRequestURL().toString();
        return siteURL.replace(request.getServletPath(), "");
    }
}

