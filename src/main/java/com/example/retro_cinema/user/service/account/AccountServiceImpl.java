package com.example.retro_cinema.user.service.account;

import com.example.retro_cinema.user.model.AccountUser;
import com.example.retro_cinema.user.model.Roles;
import com.example.retro_cinema.user.repository.accounts.IAccountRepository;
import com.example.retro_cinema.user.repository.roles.IRoleRepository;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
@Lazy
public class AccountServiceImpl implements IAccountService {
    @Autowired
    private IAccountRepository iAccountRepository;
    @Autowired
    private IRoleRepository iRoleRepository;
    @Autowired
    private JavaMailSender mailSender;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public AccountUser findByEmail(String email) {
        if (iAccountRepository.findAccountUserByEmail(email) == null) {
            return null;
        }
        return iAccountRepository.findAccountUserByEmail(email);
    }

    @Override
    public Roles findRoleById(int id) {
        return iRoleRepository.findById(id);
    }

    @Override
    public AccountUser findByIdAccount(Integer id) {
        if (iAccountRepository.findById(id).get() !=null) {
            return iAccountRepository.findById(id).get();
        } else {
            return null;
        }
    }

    @Override
    public void createAccount(AccountUser accountUser) {
        accountUser.setRoles(iRoleRepository.findById(2)); //Set ROLE_USER
        String encryptedPass = passwordEncoder.encode(accountUser.getPass());
        accountUser.setVerificationCode(UUID.randomUUID().toString());
        System.out.println(accountUser.getVerificationCode());
        accountUser.setPass(encryptedPass);
        accountUser.setEnabled(false);
        iAccountRepository.save(accountUser);
    }

    @Override
    public List<AccountUser> findAll() {
        return iAccountRepository.findAll();
    }

    @Override
    public void reset(AccountUser accountUser) {
        String randomCode = RandomString.make(64);
        accountUser.setVerificationCode(randomCode);
        iAccountRepository.save(accountUser);
    }


    @Override
    public void sendVerificationReset(AccountUser accountUser, String siteURL) throws MessagingException, UnsupportedEncodingException, MessagingException {
        String toAddress = accountUser.getEmail();
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
                "    <td bgcolor=\"#eaeaea\" style=\"padding: 30px 20px 40px 30px;background: url('email_background.jpg') no-repeat center center;background-size: cover;\">\n" +
                "\n" +
                "      <p>Dear<span style=\"color: #0db9e0;font-size: 14px;font-weight: bold;\"> \"" + accountUser.getUsername() + "\" </span></p>\n" +
                "      <p >\n" +
                "        We have received a request to reset the password associated with your account. In order to proceed with\n" +
                "        the password reset process, we need to confirm that the email address associated with your account is valid.<br>\n" +
                "        Please click on the following link to confirm your email address:<br>\n" +
                "      </p>";
        String verifyURL = siteURL + "/verify_reset?code=" + accountUser.getVerificationCode();
        content += "      <button style=\"background-color: #2093c7; \n" +
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
        content += "  <tr>\n" +
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
    public void sendVerificationEmail(AccountUser accountUser, String siteURL) throws MessagingException, UnsupportedEncodingException, MessagingException {
        String toAddress = accountUser.getEmail();
        String fromAddress = "phantaanhdao@gmail.com";
        String senderName = "cinema";
        String subject = "Confirm your email address";
        String content = "<body style=\"margin: 0; padding: 0\">\n" +
                "<table align=\"center\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\" style=\"border-collapse: collapse\">\n" +
                "  <tr>\n" +
                "    <td  style=\" background: #5cb1e7; \">\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  <tr>\n" +
                "    <td bgcolor=\"#eaeaea\" style=\"padding: 30px 20px 40px 30px;background: url('email_background.jpg') no-repeat center center;background-size: cover;\">\n" +
                "\n" +
                "      <p>Dear<span style=\"color: #0db9e0;font-size: 14px;font-weight: bold;\"> " + accountUser.getUsername() + "</span></p>\n" +
                "      <p >\n" +
                "        We have received a request to reset the password associated with your account. In order to proceed with\n" +
                "        the password reset process, we need to confirm that the email address associated with your account is valid.<br>\n" +
                "        Please click on the following link to confirm your email address:<br>\n" +
                "      </p>";
        String verifyURL = siteURL + "/verify?code=" + accountUser.getVerificationCode();
        content += "      <button style=\"background-color: #2093c7; \n" +
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
        content += "  <tr>\n" +
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
        helper.setText(content, true);
        mailSender.send(message);
    }

    @Override
    public boolean verify(String verificationCode) {
        AccountUser accountUser = iAccountRepository.findByVerificationCode(verificationCode);
        Calendar cal = Calendar.getInstance();
        if (accountUser == null || accountUser.isEnabled()) {
            return false;
        } else if ((accountUser.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            iAccountRepository.delete(accountUser);
            return false;
        } else {
            accountUser.setExpiryDate(null);
            accountUser.setVerificationCode(null);
            accountUser.setEnabled(true);
            iAccountRepository.save(accountUser);
            return true;
        }
    }

    @Override
    public AccountUser findByCode(String code) {
        return iAccountRepository.findByVerificationCode(code);
    }

    @Override
    public boolean verifyReset(String verificationCode) {
        AccountUser accountUser = iAccountRepository.findByVerificationCode(verificationCode);
        Calendar cal = Calendar.getInstance();
        if (accountUser == null || (accountUser.getExpiryDate().getTime() - cal.getTime().getTime()) <= 0) {
            return false;
        } else {
            accountUser.setVerificationCode(null);
            iAccountRepository.save(accountUser);
            return true;
        }
    }

    @Override
    public AccountUser findByUsername(String username) {
        return iAccountRepository.findAccountByUsername(username);
    }
    @Override
    public void reset_pw(AccountUser accountUser, String new_pw) {
        String encodedPassword = passwordEncoder.encode(new_pw);
        AccountUser oldUser = iAccountRepository.findAccountUserByEmail(accountUser.getEmail());
        oldUser.setPass(encodedPassword);
        iAccountRepository.save(oldUser);
    }

}
