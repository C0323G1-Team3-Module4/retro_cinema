package com.example.retro_cinema.login;

import com.example.retro_cinema.customer.dto.CustomerDto;
import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.customer.service.ICustomerService;
import com.example.retro_cinema.user.dto.AccountUserDto;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

    @GetMapping("/")
    public String showHomePage(Model model) {
        return "home";
    }



    @GetMapping("/login")
    public String formLogin(@RequestParam(value = "error", required = false)
                            boolean error, Principal principal, Model model, HttpServletRequest request) {
        String authentication = SecurityContextHolder.getContext().getAuthentication().getName();
        if (!"anonymousUser".equals(authentication)) {
            AccountUser accountUser = iAccountService.findByEmail(principal.getName());
            System.out.println(accountUser.getEmail());
            HttpSession session = request.getSession(); //Make session
            session.setAttribute("userLogin", accountUser);
            session.setMaxInactiveInterval(600); //login max in 30minutes
            if (!iCustomerService.findByEmail(accountUser.getEmail()).isEnabled()) {
                model.addAttribute("accountDto", new AccountUserDto());
                model.addAttribute("customerDto", new CustomerDto());
//                model.addAttribute("fail", "Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.");
                return "/loginPage";
            } else {
                model.addAttribute("info", iCustomerService.findByIdAccount(accountUser.getId()));
            }
            model.addAttribute("userLogin",accountUser);
            return "home";
        }
        if (error) {
            model.addAttribute("fail", "Email or Password error");
        }
        model.addAttribute("accountDto", new AccountUserDto());
        model.addAttribute("customerDto", new CustomerDto());
        return "/loginPage";
    }

    @GetMapping("/logoutSuccessful")
    public String logout(HttpServletRequest request, RedirectAttributes redirectAttributes) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (authentication != null){
//            SecurityContextHolder.clearContext();
//            redirectAttributes.addFlashAttribute("message","successful logout");
//        }
//        HttpSession session = request.getSession();
//        session.removeAttribute("userLogin");
//        return "home";

        HttpSession session = request.getSession();
        session.removeAttribute("userLogin");

        // Add a flash attribute for displaying a success message on the redirected page
//        redirectAttributes.addFlashAttribute("message", "Logout successful");

        // Redirect the user to the home page or any other page after logout
        return "home"; // Replace "home" with the appropriate URL mapping for your home page
    }

    @GetMapping(value = "/userInfo")
    public String userInfo(Model model, Principal principal, RedirectAttributes redirectAttributes) {
        String userName = principal.getName();
        AccountUser accountUser = iAccountService.findByEmail(principal.getName());
        model.addAttribute("acc", accountUser);
        if (accountUser.getRoles().getRoleName().equals("ROLE_USER")) {
            if (!iCustomerService.findByEmail(accountUser.getEmail()).isEnabled()) {
                redirectAttributes.addFlashAttribute("fail", "Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.");
                return "redirect:/login";
            } else {
                model.addAttribute("info", iCustomerService.findByIdAccount(accountUser.getId()));
                return "/home";
            }
        } else if (accountUser.getRoles().getRoleName().equals("ROLE_ADMIN")) {
            System.out.println("userName: " + userName);
            model.addAttribute("info", iCustomerService.findByIdAccount(accountUser.getId()));
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
    public String signup(@Valid @ModelAttribute CustomerDto customerDto, BindingResult bindingResult
            , RedirectAttributes redirectAttributes, HttpServletRequest request, Model model) throws UnsupportedEncodingException, MessagingException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("accountDto", new AccountUserDto());
            return "/loginPage";
        }
        if (iCustomerService.findByEmail(customerDto.getAccountUserDto().getEmail()) != null) {
            redirectAttributes.addFlashAttribute("fail", "This email already exists!");
        } else {
            customerDto.setExpiryDate(calculateExpiryDate());
            Roles roles = iAccountService.findRoleById(2);
            AccountUser accountUser = new AccountUser();
            BeanUtils.copyProperties(customerDto.getAccountUserDto(), accountUser);
            accountUser.setRoles(roles);
            iAccountService.createAccount(accountUser);
            Customer customer = new Customer();
            BeanUtils.copyProperties(customerDto, customer);
            customer.setAccountUser(accountUser);
            iCustomerService.create(customer);
            String siteURL = getSiteURL(request);
            iCustomerService.sendVerificationEmail(customer, siteURL);
            redirectAttributes.addFlashAttribute("success", "You have signed up successfully! Please check your email to verify your account.");
        }
        return "redirect:/login";
    }

    @GetMapping("/verify")
    public String verifyUser(@RequestParam("code") String code, RedirectAttributes redirectAttributes) {
        if (iCustomerService.verify(code)) {
            redirectAttributes.addFlashAttribute("success", "Congratulations, your account has been verified.");
        } else {
            redirectAttributes.addFlashAttribute("fail", "Sorry, we could not verify account. It maybe already verified, or verification code is incorrect.");
        }
        return "redirect:/login";
    }

    @GetMapping("/email")
    public String email() {
        return "/email_reset_pw";
    }

    @PostMapping("/confirm_email")
    public String confirm_email(@RequestParam("email") String email, HttpServletRequest request, RedirectAttributes redirectAttributes) throws UnsupportedEncodingException, MessagingException {
        Customer customer = iCustomerService.findByEmail(email);
        customer.setExpiryDate(calculateExpiryDate());
        iCustomerService.reset(customer);
        String siteURL = getSiteURL(request);
        iCustomerService.sendVerificationReset(customer, siteURL);
        redirectAttributes.addFlashAttribute("success", "Please check your email to verify your account.");
        return "redirect:/login";
    }

    @GetMapping("/reset_pw")
    public String reset_pw(@ModelAttribute Customer customer, Model model) {
        return "reset_pw";
    }

    @PostMapping("/new_pw")
    public String new_pw(@RequestParam("new_pw") String new_pw,
                         @ModelAttribute Customer customer,
                         RedirectAttributes redirectAttributes) {
        iCustomerService.reset_pw(customer, new_pw);
        redirectAttributes.addFlashAttribute("success", "Password change successful.");
        return "redirect:/login";
    }

    @GetMapping("/verify_reset")
    public String verify_reset(@RequestParam("code") String code, Model model,
                               RedirectAttributes redirectAttributes) {
        String email = null;
        if (iCustomerService.findByCode(code) != null) {
            Customer customer = iCustomerService.findByCode(code);
            email = customer.getAccountUser().getEmail();
        }
        if (iCustomerService.verifyReset(code)) {
            Customer customer = iCustomerService.findByEmail(email);
            model.addAttribute("customers", customer);
            return "/reset_pw";
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

