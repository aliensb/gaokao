package win.ccav.service;

import com.google.gson.reflect.TypeToken;
import com.sun.org.apache.bcel.internal.generic.ALOAD;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.ccav.Common.MVCConstant;
import win.ccav.dao.UserDao;
import win.ccav.model.User;
import win.ccav.model.UserLog;
import win.ccav.utils.MD5Utils;
import win.ccav.utils.jredis.JedisTemplate;
import win.ccav.utils.json.JsonUtil;

import java.util.*;

/**
 * Created by paul on 2017/4/18.
 */
@Service("userService")
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private JedisTemplate jedisTemplate;
    Log logger = LogFactory.getLog(UserService.class);
    public User isUserExsist(User user){
        user.setPassword(MD5Utils.encrypt(user.getPassword()));
        boolean res=false;
        User out=userDao.isUserExsist(user);
        return out;
    }
    public boolean setEndTime(User user){
        String key= MD5Utils.encrypt(user.getUsername()+user.getPassword());
        Integer totalMin= JsonUtil.fromJSONToObject(jedisTemplate.get("totalTime",key),Integer.class);
        if(totalMin==null){
            //说明用户账户有问题，情况是valid=1但是redis里面并没有使用时间，错误情况
            logger.info(user+"-用户的totalMin在redis里面没有，账号有问题-");
            return false;
        }
        Date firstLoginDate=JsonUtil.fromJSONToObject(
                jedisTemplate.get(MVCConstant.FIRST_LOGIN_DATE,key),Date.class);
        if(firstLoginDate==null){
            //第一次登录使用,将用户第一次登录系统的时间放进redis里面
            jedisTemplate.set(MVCConstant.FIRST_LOGIN_DATE, key,JsonUtil.toJSON(new Date()));
            Calendar endTime=Calendar.getInstance();
            endTime.add(Calendar.MINUTE,totalMin);//使用结束时间
            UserLog userLog=new UserLog();
            userLog.setUser(user);
            userLog.setEndTime(endTime);
            List<UserLog> allOnLineUsers=JsonUtil.fromJSONToListByType(jedisTemplate.get(MVCConstant.ALL_ONLINE_USERS),
                    new TypeToken<List<UserLog>>(){}.getType());
            if(allOnLineUsers==null){
                //系统中还没有用户登录进来，他是第一个用户，那就创建容器
                allOnLineUsers=new LinkedList<>();
                allOnLineUsers.add(userLog);
            }else{
                allOnLineUsers.add(userLog);
            }
            jedisTemplate.set(MVCConstant.ALL_ONLINE_USERS, JsonUtil.toJSON(allOnLineUsers));
            return true;
        }else{
            //之后登录了，需要判断这次登录的时候是不是已经超时了
            Calendar endCal=Calendar.getInstance();
            endCal.setTime(firstLoginDate);
            endCal.add(Calendar.MINUTE,totalMin);//使用到期时间
            Calendar nowCal=Calendar.getInstance();
            logger.info(endCal.getTime());
            logger.info(nowCal.getTime());
            if(Calendar.getInstance().after(endCal)){
                //已经超过使用的时间了，
                user.setValide(0);
                userDao.update(user);
                return false;
            }
            return true;
        }
    }


    public String saveUser(User user){
        user.setPassword(MD5Utils.encrypt(user.getPassword()));
        user.setValide(0);
        user.setRegistDate(new Date());
        return userDao.reigst(user);
    }

    public void setUserUnUse(User user){
        user.setValide(0);
        userDao.update(user);
    }
}
