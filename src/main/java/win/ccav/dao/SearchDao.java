package win.ccav.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import win.ccav.model.XuexiaoEntity;

import java.util.List;

/**
 * Created by paul on 2017/4/19.
 */
@Repository
public class SearchDao {
    @Autowired
    private SessionFactory sessionFactory;
    public List<XuexiaoEntity> search(String hql,int offset,int length){
        List<XuexiaoEntity> res=null;
        Session session=sessionFactory.openSession();
        Query query=session.createQuery(hql);
        query.setFirstResult(offset);
        query.setMaxResults(length);
        res=query.list();
        session.close();
        return res;
    }
    public long getAllRowCount(String hql){
        Session session=sessionFactory.openSession();
        Query query=session.createQuery(hql);
        long allRowCount=(Long) query.uniqueResult();
        session.close();
        return allRowCount;
    }
    public List<XuexiaoEntity> getXuexiaoByName(String name,int offset,int pageSize){
        Session session=sessionFactory.openSession();
        String hql="from XuexiaoEntity where xuexiaomingcheng like :name";
        Query query=session.createQuery(hql).setString("name","%"+name+"%");
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
        List<XuexiaoEntity> entities=query.list();
        session.close();
        return entities;
    }
    public Long getAllRowCountByname(String name){
        Session session=sessionFactory.openSession();
        String hql="select count(id) from XuexiaoEntity where xuexiaomingcheng like :name";
        Query query=session.createQuery(hql).setString("name","%"+name+"%");
        long allRowCount=(Long) query.uniqueResult();
        session.close();
        return allRowCount;
    }
}
