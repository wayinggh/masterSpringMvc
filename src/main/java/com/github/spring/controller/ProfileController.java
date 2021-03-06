package com.github.spring.controller;

import com.github.spring.model.profile.ProfileForm;
import com.github.spring.date.LocalDateFormatter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * Created by heliming on 2015/12/17.
 */
@Controller
public class ProfileController {

    @RequestMapping("/profile")
    public String displayProfile(ProfileForm profileForm) {
        return "profile/profilePage";
    }

    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public String saveProfile(@Valid ProfileForm profileForm, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "profile/profilePage";
        }
        System.out.println("save ok " + profileForm);
        return "redirect:/profile";
    }

    @ModelAttribute("dateFormat")
    public String localeFormat(Locale locale) {
        return LocalDateFormatter.getPattern(locale);
    }


    @RequestMapping(path = "/profile", params = {"addTaste"})
    public String addRow(ProfileForm profileForm) {
        profileForm.getTastes().add(null);
        return "/profile/profilePage";
    }

    @RequestMapping(path = "profile", params = {"removeTaste"})
    public String removeRow(ProfileForm profileForm, HttpServletRequest request) {
        Integer rowId = Integer.valueOf(request.getParameter("removeTaste"));
        profileForm.getTastes().remove(rowId.intValue());
        return "/profile/profilePage";
    }



}
