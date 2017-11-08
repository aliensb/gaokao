package win.ccav.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import win.ccav.model.XuexiaoEntity;
import win.ccav.model.XuexiaoEntityEager;

import java.util.List;

/**
 * Created by paul on 2017/4/20.
 */
@Repository
public class DetailsDao {
    @Autowired
    private SessionFactory sessionFactory;
    public XuexiaoEntityEager getXueXiaoById(int id){
        Session session =sessionFactory.openSession();
        XuexiaoEntityEager xuexiaoEntity=session.get(XuexiaoEntityEager.class,id);
        session.close();
        return xuexiaoEntity;
    }

    public List<String> getWenli(int xuexiaoid){
        Session session=sessionFactory.openSession();
        String hql="select distinct wenli from ZhuanyeEntity where xuexiaoid=?";
        Query query=session.createQuery(hql).setInteger(0,xuexiaoid);
        List<String> list=query.list();
        session.close();
        return list;
    }
}
