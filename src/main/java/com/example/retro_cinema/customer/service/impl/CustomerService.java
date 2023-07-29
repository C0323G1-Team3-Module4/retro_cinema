package com.example.retro_cinema.customer.service.impl;

import com.example.retro_cinema.customer.model.Customer;
import com.example.retro_cinema.customer.repository.ICustomerRepository;
import com.example.retro_cinema.customer.service.ICustomerService;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;

@Service
@Component
@Lazy
public class CustomerService implements ICustomerService {
    @Autowired
    private ICustomerRepository iCustomerRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JavaMailSender mailSender;

    @Override
    public Page<Customer> findAll(String searchByName, Pageable pageable) {
        return iCustomerRepository.findAll(searchByName, pageable);
    }

    @Override
    public Customer findByIdCustomer(Integer id) {
        return iCustomerRepository.findById(id).get();
    }

    @Override
    public void create(Customer customer) {
        String encodedPassword = passwordEncoder.encode(customer.getAccountUser().getPass());
        customer.getAccountUser().setPass(encodedPassword);
        String randomCode = RandomString.make(64);
        customer.setVerificationCode(randomCode);
        customer.setEnabled(false);
        iCustomerRepository.save(customer);
    }

    @Override
    public void update(Customer customer) {
        iCustomerRepository.save(customer);
    }

    @Override
    public void delete(Integer id) {
        iCustomerRepository.deleteById(id);
    }

    @Override
    public Customer findByIdAccount(Integer id) {
        if (iCustomerRepository.findCustomerByAccountUser_Id(id) != null) {
            return iCustomerRepository.findCustomerByAccountUser_Id(id);
        } else {
            return null;
        }

    }

    @Override
    public Customer findByEmail(String email) {
        return iCustomerRepository.findByAccountUser_Email(email);
    }

    @Override
    public void sendVerificationEmail(Customer customer, String siteURL) throws MessagingException, UnsupportedEncodingException, MessagingException {
        String toAddress = customer.getAccountUser().getEmail();
        String fromAddress = "phantaanhdao@gmail.com";
        String senderName = "cinema";
        String subject = "Confirm your email address";
        String content = "<body style=\"margin: 0; padding: 0\">\n"+
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse\">\n" +
                "  <tr>\n" +
                "    <td  style=\" background: #5cb1e7; \">\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td bgcolor=\"#eaeaea\" style=\"padding: 30px 20px 40px 30px;background: url('https://cafebiz.cafebizcdn.vn/thumb_w/600/162123310254002176/2022/6/7/photo1654573349126-16545733492221043829261.jpg') no-repeat center center;background-size: cover;\">\n" +
                "\n" +
                "      <p>Dear<span style=\"color: #0db9e0;font-size: 14px;font-weight: bold;\"> "+customer.getFullName() +"</span></p>\n" +
                "      <p >\n" +
                "        We have received a request to reset the password associated with your account. In order to proceed with\n" +
                "        the password reset process, we need to confirm that the email address associated with your account is valid.<br>\n" +
                "        Please click on the following link to confirm your email address:<br>\n" +
                "      </p>";
        String verifyURL= siteURL+ "/verify?code="+customer.getVerificationCode();
        content+= "      <button style=\"background-color: #2093c7; \n" +
                "   border: none;\n" +
                "  color: #ffffff;\n" +
                "  padding: 16px 32px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\n" +
                "  font-size: 16px;\n" +
                "  margin: 4px 2px;\n" +
                "  justify-content: center;\n" +
                "  transition-duration: 0.4s;\n" +
                "  cursor: pointer; border-radius: 20px\"><span style='color: #ffffff'><a href=\"" + verifyURL + "\">Confirm Your Email Address</a></span></button>";
        content+=  "  <tr>\n" +
                "    <td style=\"padding: 10px 20px; color: #FFFFFF;background: #5cb1e7\">\n" +
                "      <p>If you did not create an account with us, please ignore this email.\n" +
                "        <br>\n" +
                "        Thank you for choosing Cinema. We look forward to serving you!<br>\n" +
                "        Best regards!<br></div></p>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "</body>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        try {
            helper.setFrom(fromAddress, senderName);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content,true);
        mailSender.send(message);
    }

    @Override
    public Customer findByCode(String code) {
        return iCustomerRepository.findByVerificationCode(code);
    }

    @Override
    public boolean verify(String verificationCode) {
        Customer customer = iCustomerRepository.findByVerificationCode(verificationCode);
        Calendar cal = Calendar.getInstance();
        if (customer == null || customer.isEnabled()){
            return false;
        }else if ((customer.getExpiryDate().getTime()- cal.getTime().getTime()) <= 0){
            iCustomerRepository.delete(customer);
            return false;
        }else {
            customer.setVerificationCode(null);
            customer.setEnabled(true);
            iCustomerRepository.save(customer);
            return true;
        }
    }

    @Override
    public void reset(Customer customer) {
        String randomCode = RandomString.make(64);
        customer.setVerificationCode(randomCode);
        iCustomerRepository.save(customer);
    }

    @Override
    public void sendVerificationReset(Customer customer, String siteURL) throws MessagingException, UnsupportedEncodingException, MessagingException {
        String toAddress = customer.getAccountUser().getEmail();
        String fromAddress = "phantaanhdao@gmail.com";
        String senderName = "Cinema";
        String subject = "Confirm your email address";
        String content = "<body  style=\"margin: 0; padding: 0\">\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse\">\n" +
                "  <tr>\n" +
                "    <td  style=\" background: #5cb1e7; \">\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td bgcolor=\"#eaeaea\" style=\"padding: 30px 20px 40px 30px;background: url('https://cafebiz.cafebizcdn.vn/thumb_w/600/162123310254002176/2022/6/7/photo1654573349126-16545733492221043829261.jpg') no-repeat center center;background-size: cover;\">\n" +
                "\n" +
                "      <p>Dear<span style=\"color: #0db9e0;font-size: 14px;font-weight: bold;\"> \""+customer.getFullName() +"\" </span></p>\n" +
                "      <p >\n" +
                "        We have received a request to reset the password associated with your account. In order to proceed with\n" +
                "        the password reset process, we need to confirm that the email address associated with your account is valid.<br>\n" +
                "        Please click on the following link to confirm your email address:<br>\n" +
                "      </p>";
        String verifyURL= siteURL+ "/verify_reset?code="+customer.getVerificationCode();
        content+= "      <button style=\"background-color: #2093c7; \n" +
                "   border: none;\n" +
                "  color: #ffffff;\n" +
                "  padding: 16px 32px;\n" +
                "  text-align: center;\n" +
                "  text-decoration: none;\n" +
                "  display: inline-block;\n" +
                "  font-size: 16px;\n" +
                "  margin: 4px 2px;\n" +
                "  justify-content: center;\n" +
                "  transition-duration: 0.4s;\n" +
                "  cursor: pointer; border-radius: 20px\"><span style='color: #ffffff'><a href=\"" + verifyURL + "\">Confirm Your Email Address</a><span></button>";
        content+= "  <tr>\n" +
                "    <td style=\"padding: 10px 20px; color: #FFFFFF;background: #5cb1e7\">\n" +
                "      <p>If you did not request a password reset, please disregard this message.\n" +
                "        <br>\n" +
                "        If you believe that your account has been compromised, please contact our customer support team immediately.<br>\n" +
                "        Thank you,<br></div></p>\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "</table>\n" +
                "</body>";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);

        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Override
    public boolean verifyReset(String verificationCode) {
        Customer customer = iCustomerRepository.findByVerificationCode(verificationCode);
        Calendar cal = Calendar.getInstance();
        if (customer==null||(customer.getExpiryDate().getTime()- cal.getTime().getTime()) <= 0){
            return false;
        }else {
            customer.setVerificationCode(null);
            iCustomerRepository.save(customer);
            return true;
        }
    }

    @Override
    public void reset_pw(Customer customer, String new_pw) {
        String encodedPassword = passwordEncoder.encode(new_pw);
        Customer customers = iCustomerRepository.findByAccountUser_Email(customer.getAccountUser().getEmail());
        customers.getAccountUser().setPass(encodedPassword);
        iCustomerRepository.save(customers);
    }

    @Override
    public Customer findById(int id) {
        return iCustomerRepository.findById(id).get();
    }
}
