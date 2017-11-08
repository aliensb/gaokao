package win.ccav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import win.ccav.Common.MVCConstant;
import win.ccav.model.User;
import win.ccav.utils.MD5Utils;
import win.ccav.utils.jredis.JedisTemplate;
import win.ccav.utils.json.JsonUtil;

import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by john on 2017/10/23.
 */
@Controller
public class TimeController {
    @Autowired
    private JedisTemplate jedisTemplate;
    @RequestMapping("/getTime")//
    public String index(HttpSession session, Model model){//   /getTime这个是请求的地址，也就是你再页面上输入的url
        User user= (User) session.getAttribute("user");
        String key= MD5Utils.encrypt(user.getUsername()+user.getPassword());
        Integer totalMin= JsonUtil.fromJSONToObject(jedisTemplate.get("totalTime",key),Integer.class);
        Date firstLoginDate=JsonUtil.fromJSONToObject(
                jedisTemplate.get(MVCConstant.FIRST_LOGIN_DATE,key),Date.class);
        Calendar cal=Calendar.getInstance();
        cal.setTime(firstLoginDate);
        cal.add(Calendar.MINUTE,totalMin);
        model.addAttribute("endTime",cal.getTimeInMillis());//数据endTime第一个参数是key，有就是再页面上去的时候的key
        long timeStamp=System.currentTimeMillis();
        Timestamp timestamp=new Timestamp(timeStamp);
        model.addAttribute("nowTime",timestamp.getTime());//数据，
        return "time";//这个方法的return是time，那他就对应的会再浏览器展示time.html，但是展示之前，会做一些数据渲染，现在我们去看页面
    }
    @RequestMapping("/getTimeStampe")
    public @ResponseBody String  timeNow(){
        long timeStamp=System.currentTimeMillis();
        Timestamp times=new Timestamp(timeStamp);
        return times.toString();
    }
}
