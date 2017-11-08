package win.ccav.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import win.ccav.model.User;

import java.util.List;

/**
 * Created by paul on 2017/4/17.
 */
@Repository
public class UserDao {
    @Autowired
    private SessionFactory sessionFactory;
    public User isUserExsist(User user){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        String hql="from User u where u.username=? and u.password=?";
        Query query=session.createQuery(hql).setString(0,user.getUsername()).setString(1,user.getPassword());
        List<User> users=query.list();
        session.close();
        if(users.size()>0){
            return users.get(0);
        }
        return null;
    }
    public String reigst(User user){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        try{
            session.save(user);
        }catch (ConstraintViolationException e){
            session.getTransaction().rollback();
            return "用户名已存在！";
        }
        session.getTransaction().commit();
        session.close();
        return "ok";
    }
    public void update(User user){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        try{
            session.update(user);
        }catch (ConstraintViolationException e){
            session.getTransaction().rollback();
        }
        session.getTransaction().commit();
        session.close();
    }
}
