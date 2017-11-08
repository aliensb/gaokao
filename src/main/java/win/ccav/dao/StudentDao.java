package win.ccav.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import win.ccav.model.save.StudentEntity;

/**
 * Created by paul on 2017/4/24.
 */
@Repository
public class StudentDao {
    @Autowired
    private SessionFactory sessionFactory;
    public boolean save(StudentEntity studentEntity){
        Session session=sessionFactory.openSession();
        session.beginTransaction();
        session.save(studentEntity);
        session.getTransaction().commit();
        session.close();
        return true;
    }
}
