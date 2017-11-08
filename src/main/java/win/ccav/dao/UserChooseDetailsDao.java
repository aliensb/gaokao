package win.ccav.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import win.ccav.model.UserChooseDetails;

/**
 * Created by john on 2017/10/30.
 */
@Repository
public class UserChooseDetailsDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void add(UserChooseDetails userChooseDetails){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        try{
            session.save(userChooseDetails);
        }catch (ConstraintViolationException e){
            session.getTransaction().rollback();
            return ;
        }
        session.getTransaction().commit();
        session.close();
        return ;
    }
}
