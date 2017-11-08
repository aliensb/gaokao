package win.ccav.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * Created by john on 2017/10/21.
 */
public class EndTimeSockert extends TextWebSocketHandler {
    @Autowired
    private HttpSession hSession;
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("连接成功");
        System.out.println(hSession==null);
        Enumeration<String> em =hSession.getAttributeNames();
        while (em.hasMoreElements()){
            System.out.println(em.nextElement());
        }
        System.out.println();
        System.out.println("!@!DWQWDQWDQWDDQWDQWD");

        //User user= (User) hSession.getAttribute("user");
//        System.out.println(user.getUsername()+user.getPassword()+"@@@@@@@@@@");
        for(int i=0;i<10;i++){
            session.sendMessage(new TextMessage(i+"bibi"));
            Thread.sleep(1000);
        }
    }

}
