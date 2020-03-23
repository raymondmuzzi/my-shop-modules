package com.raymondmuzzi.my.shop.web.admin.web.controller;

import com.raymondmuzzi.my.shop.common.constant.StatusEnum;
import com.raymondmuzzi.my.shop.common.dto.BaseResult;
import com.raymondmuzzi.my.shop.domain.TbUser;
import com.raymondmuzzi.my.shop.web.admin.service.TbUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * User controller
 *
 * @author raymondmuzzi
 * @date 2020-03-21 21:12:38
 */
@Controller
@RequestMapping(value = "user")
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private TbUserService tbUserService;

    /**
     * The @ModelAttribute annotation annotated method will be invoked
     * before all the methods in UserController who annotated with @RequestMapping.
     * <p>
     * The returned result will be put into the Model object, we can use this annotation
     * help the form table echo the data inputted before
     *
     * @param id the tbUser's id
     */
    @ModelAttribute
    public TbUser getTbUser(Long id) {
        TbUser tbUser;
        // if id is null, return empty object
        if (null == id) {
            tbUser = new TbUser();
        }
        // if id is not null, return the result from db
        else {
            tbUser = tbUserService.getById(id);
        }
        return tbUser;
    }

    /**
     * Forward to the user_list.jsp, show all the users' info
     *
     * @return the view path
     */
    @RequestMapping(value = "list", method = RequestMethod.GET)
    public String list(Model model) {
        LOGGER.info("Invoke list()");
        List<TbUser> tbUsers = tbUserService.selectAll();
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }

    /**
     * Forward to user's form page
     *
     * @return form page view path
     */
    @RequestMapping(value = "form", method = RequestMethod.GET)
    public String form() {
        return "user_form";
    }

    /**
     * Save the user's info
     *
     * @param tbUser
     * @param redirectAttributes
     * @return
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(TbUser tbUser, Model model, RedirectAttributes redirectAttributes) {
        BaseResult baseResult = tbUserService.save(tbUser);
        // if success, redirect to the user list page
        if (StatusEnum.getByValue(StatusEnum.SUCCESS) == baseResult.getStatus()) {
            redirectAttributes.addFlashAttribute("baseResult", baseResult);
            return "redirect:/user/list";
        }

        // if failed, forward to user_form page
        else {
            model.addAttribute("baseResult", baseResult);
            model.addAttribute("tbUser", tbUser);
            return "user_form";
        }
    }

    /**
     * Search the user list by keyword from front-end
     *
     * @param keyword the search keyword
     * @param model   model object
     * @return the view path
     */
    @RequestMapping(value = "search", method = RequestMethod.POST)
    public String search(String keyword, Model model) {
        List<TbUser> tbUsers = tbUserService.search(keyword);
        model.addAttribute("tbUsers", tbUsers);
        return "user_list";
    }
}