package win.ccav.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import win.ccav.model.LogInfo;
import win.ccav.model.User;
import win.ccav.service.UserService;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;


/**
 * Created by paul on 2017/4/17.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService service;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }

    @RequestMapping("/logincheck")
    public @ResponseBody LogInfo loginCheck(User user,HttpSession session){
        LogInfo info=new LogInfo();
        info.setResult(false);
        info.setMsg("账号密码异常");
        if(!session.getAttribute("vilidate").equals(user.getVilidate().toUpperCase())){
            info.setMsg("验证码不对");
            return info;
        }
        User realUser=service.isUserExsist(user);
        if(realUser!=null){
            if(realUser.getValide()!=1){
                info.setResult(false);
                info.setMsg("账户未激活");
            }else {
                if(!service.setEndTime(realUser)){
                    info.setResult(false);
                    info.setMsg("账户信息不正常，请联系老师");
                }else{
                    session.setAttribute("user",realUser);
                    //session.setMaxInactiveInterval();
                    info.setResult(true);
                    info.setMsg("登录成功");
                }

            }
        }
        return info;
    }

    //生成验证码
    @RequestMapping("/code")
    public void generate(HttpServletResponse response, HttpSession session){
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        String code = drawImg(output);

        //Subject currentUser = SecurityUtils.getSubject();
        //Session session = currentUser.getSession();
        session.setAttribute("vilidate", code);

        try {
            ServletOutputStream out = response.getOutputStream();
            output.writeTo(out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 绘画验证码
     * @param output
     * @return
     */
    private String drawImg(ByteArrayOutputStream output){
        String code = "";
        //随机产生4个字符
        for(int i=0; i<4; i++){
            code += randomChar();
        }
        int width = 70;
        int height = 25;
        BufferedImage bi = new BufferedImage(width,height,BufferedImage.TYPE_3BYTE_BGR);
        Font font = new Font("Times New Roman",Font.PLAIN,20);
        //调用Graphics2D绘画验证码
        Graphics2D g = bi.createGraphics();
        g.setFont(font);
        Color color = new Color(66,2,82);
        g.setColor(color);
        g.setBackground(new Color(226,226,240));
        g.clearRect(0, 0, width, height);
        FontRenderContext context = g.getFontRenderContext();
        Rectangle2D bounds = font.getStringBounds(code, context);
        double x = (width - bounds.getWidth()) / 2;
        double y = (height - bounds.getHeight()) / 2;
        double ascent = bounds.getY();
        double baseY = y - ascent;
        g.drawString(code, (int)x, (int)baseY);
        g.dispose();
        try {
            ImageIO.write(bi, "jpg", output);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return code;
    }

    /**
     * 随机参数一个字符
     * @return
     */
    private char randomChar(){
        Random r = new Random();
        String s = "ABCDEFGHJKLMNPRSTUVWXYZ0123456789";
        return s.charAt(r.nextInt(s.length()));
    }
    @RequestMapping("/regist")
    public String registPage(){
        return "regist";
    }
    @RequestMapping("/registUser")
    public @ResponseBody String registUser(User user){
        return service.saveUser(user);
    }
}
