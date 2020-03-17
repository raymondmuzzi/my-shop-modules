package com.raymondmuzzi.my.shop.web.admin.web.interceptor;

import com.raymondmuzzi.my.shop.common.constant.Constants;
import com.raymondmuzzi.my.shop.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Login interceptor
 *
 * @author raymondmuzzi
 * @date 2020-03-15 23:43:28
 */
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * Get user from HttpSession, send redirect to the login page while the user is null
     *
     * @param request
     * @param response
     * @param o
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        User user = (User) request.getSession().getAttribute(Constants.SESSION_USER);

        // Login failed
        if (null == user) {
            response.sendRedirect("/login");
        }
        // Login succeed, pass
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object o, ModelAndView modelAndView) throws Exception {
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) throws Exception {

    }
}
