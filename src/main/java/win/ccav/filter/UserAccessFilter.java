package win.ccav.filter;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import win.ccav.Common.MVCConstant;
import win.ccav.dao.UserDao;
import win.ccav.model.User;
import win.ccav.utils.MD5Utils;
import win.ccav.utils.jredis.JedisTemplate;
import win.ccav.utils.json.JsonUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by john on 2017/10/26.
 */

public class UserAccessFilter implements Filter {
    Log logger = LogFactory.getLog(UserAccessFilter.class);

    private JedisTemplate jedisTemplate;
    private UserDao userDao;
    private ApplicationContext context;

    private UserDao getUserDao() {
        if (userDao != null) {
            return userDao;
        } else {
            userDao = (UserDao) context.getBean("userDao");
            return userDao;
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("filter init");
        ServletContext servletContext = filterConfig.getServletContext();
        context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        jedisTemplate = (JedisTemplate) context.getBean("jedisTemplate");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("dofilter");
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            //用户不为空，说明用户已经登录
            String key = MD5Utils.encrypt(user.getUsername() + user.getPassword());
            Integer totalMin = JsonUtil.fromJSONToObject(jedisTemplate.get("totalTime", key), Integer.class);
            if (totalMin == null) {
                logger.error(user + "用户已经登录了，但是并没有使用时间，账号有问题");
                //TODO非法操作，弄走
                servletResponse.setCharacterEncoding("utf-8");
                servletResponse.getWriter().print("<h1>使用时间到期，请联系老师充值！</h1>");
            }
            Date firstLoinDate = JsonUtil.fromJSONToObject(jedisTemplate.get(MVCConstant.FIRST_LOGIN_DATE, key), Date.class);
            Calendar endCal = Calendar.getInstance();
            endCal.setTime(firstLoinDate);
            endCal.add(Calendar.MINUTE, totalMin);
            Calendar nowCal = Calendar.getInstance();
            logger.info(nowCal.getTime() + "\n" + endCal.getTime());
            logger.info(nowCal + "\n" + endCal);
            if (nowCal.after(endCal)) {
                //已经超出使用时间，踢出用户，valid置为0
                session.removeAttribute("user");
                user.setValide(0);
                this.getUserDao().update(user);
                jedisTemplate.del(MVCConstant.FIRST_LOGIN_DATE, key);
                servletResponse.setCharacterEncoding("utf-8");
                servletResponse.getWriter().print("<h1>使用时间到期，请联系老师充值！</h1>");
            } else {
                filterChain.doFilter(servletRequest, servletResponse);
                return;
            }
        } else {//用户没有登录，用没有问题
            filterChain.doFilter(servletRequest, servletResponse);
        }

    }

    @Override
    public void destroy() {
        logger.info("dofilter");
    }
}
