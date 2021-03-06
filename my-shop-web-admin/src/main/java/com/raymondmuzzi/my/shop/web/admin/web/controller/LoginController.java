package com.raymondmuzzi.my.shop.web.admin.web.controller;

import com.raymondmuzzi.my.shop.common.constant.Constants;
import com.raymondmuzzi.my.shop.common.utils.CookieUtils;
import com.raymondmuzzi.my.shop.domain.TbUser;
import com.raymondmuzzi.my.shop.web.admin.service.TbUserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author raymondmuzzi
 * @date 2020-03-15 22:00:07
 */
@Controller
public class LoginController {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginController.class);
    private static final String COOKIE_NAME_USER_INFO = "userInfo";

    @Autowired
    private TbUserService userService;

    /**
     * Forward login request to achieve remember me function
     *
     * @param request the http servlet request
     * @return the view path [login.jsp]
     */
    @RequestMapping(value = {"login", ""}, method = RequestMethod.GET)
    public String login(HttpServletRequest request) {
        String cookieValue = CookieUtils.getCookieValue(request, COOKIE_NAME_USER_INFO);
        if (StringUtils.isNotBlank(cookieValue)) {
            String[] split = cookieValue.split(":");
            String username = split[0];
            String password = split[1];
            request.setAttribute("username", username);
            request.setAttribute("password", password);
            request.setAttribute("isRemembered", true);
        }
        return "login";
    }

    /**
     * User login controller by POST method
     *
     * @param username user's username from font-end
     * @param password user's password from front-end
     * @param request  the http servlet request object
     * @param response the http servlet response object
     * @return the view path [login.jsp]
     * @throws IOException
     * @throws ServletException
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(@RequestParam(required = true, value = "username") String username,
                        @RequestParam(required = true, value = "password") String password,
                        HttpServletRequest request,
                        HttpServletResponse response) throws IOException, ServletException {
        boolean isRemembered = request.getParameter("isRemembered") == null ? false : true;

        // User not select remember me
        if (!isRemembered) {
            CookieUtils.deleteCookie(request, response, COOKIE_NAME_USER_INFO);
        }

        TbUser user = userService.login(username, password);
        // Login succeed
        if (user != null) {
            LOGGER.info("User {} login succeed", user.getUsername());

            // put the login info into the session
            request.getSession().setAttribute(Constants.SESSION_USER, user);


            // If select the remember me checkbox, enable the remember me function
            if (isRemembered) {
                CookieUtils.setCookie(request, response, COOKIE_NAME_USER_INFO, String.format("%s:%s", username, password), 7 * 24 * 60 * 60);
            }
            // redirect
            return "redirect:/main";
        }
        // Login failed
        else {
            LOGGER.info("User {} login failed", username);
            request.setAttribute("message", "ERROR Incorrect username or password");
            return login(request);
        }
    }

    /**
     * Logout
     *
     * @param request logout request
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "login";
    }
}
