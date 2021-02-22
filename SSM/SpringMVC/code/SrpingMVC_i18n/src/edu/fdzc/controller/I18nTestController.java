package edu.fdzc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * @author CAI
 * @time 2021/2/22
 */
@Controller
public class I18nTestController {

    @Autowired
    private MessageSource messageSource;

    @RequestMapping("/tologinpage")
    public String tologinPage(String localeInfo, Locale l, HttpSession session) {
//        Locale locale;
//
//        if (localeInfo != null && !localeInfo.isEmpty()) {
//            locale = new Locale(localeInfo.split("_")[0], localeInfo.split("_")[1]);
//
//        } else {
//            locale = l;
//        }
//
//        session.setAttribute(SessionLocaleResolver.class.getName() + ".LOCALE", locale);
        return "login";
    }
}
