package win.ccav.config.schedule;

import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import win.ccav.Common.MVCConstant;
import win.ccav.dao.UserDao;
import win.ccav.model.User;
import win.ccav.model.UserLog;
import win.ccav.utils.MD5Utils;
import win.ccav.utils.jredis.JedisTemplate;
import win.ccav.utils.json.JsonUtil;

import java.util.*;
import java.util.jar.JarEntry;

/**
 * Created by john on 2017/10/27.
 */
@Component
@Configurable
@EnableScheduling
public class UserLimitTimeJob {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JedisTemplate jedisTemplate;
    //没分钟执行一次
    @Scheduled(cron = "0 */1 *  * * * ")
    public void reportCurrentByCron(){
        String json=jedisTemplate.get( MVCConstant.ALL_ONLINE_USERS);
        if(json==null)
            return;
        List<UserLog> allOnLineUsers=JsonUtil.fromJSONToListByType(json,new TypeToken<List<UserLog>>(){}.getType());
        for(int i=0;i<allOnLineUsers.size();i++){
            UserLog userLog=allOnLineUsers.get(i);
            this.processUserState(userLog,allOnLineUsers);
        }
        jedisTemplate.set(MVCConstant.ALL_ONLINE_USERS, JsonUtil.toJSON(allOnLineUsers));
    }
    //根据userlog来判断用户使用是不是超时了，如果超时了
    //禁号，清理redis
    private void processUserState(UserLog userLog,List<UserLog> userLogList){
            User user=userLog.getUser();
            Calendar endTime=userLog.getEndTime();
            if(Calendar.getInstance().after(endTime)){
                userLogList.remove(userLog);
                String key= MD5Utils.encrypt(user.getUsername()+user.getPassword());
                user.setValide(0);
                userDao.update(user);
                jedisTemplate.del("totalTime",key);
                jedisTemplate.del(MVCConstant.FIRST_LOGIN_DATE,key);
            }
    }
}
