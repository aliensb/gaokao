package win.ccav.config.socket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import win.ccav.websocket.EndTimeSockert;

/**
 * Created by john on 2017/10/21.
 */
@EnableWebSocket
@Configuration
public class WebSockConfig implements WebSocketConfigurer {
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(endTimeSockert(),"/endtime");
    }
    @Bean
    public EndTimeSockert endTimeSockert(){
        return new EndTimeSockert();
    }
}
