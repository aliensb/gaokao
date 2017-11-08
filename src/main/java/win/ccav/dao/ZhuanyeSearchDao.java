package win.ccav.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import win.ccav.model.XuexiaoEntity;
import win.ccav.model.ZhuanyeEntity;

import java.util.List;

/**
 * Created by paul on 2017/6/22.
 */
@Repository
public class ZhuanyeSearchDao {
    @Autowired
    private SessionFactory sessionFactory;
    public Long getAllRowCountByZhuanye(String zhuanye){
        Session session=sessionFactory.openSession();
        String hql="select count(id) from ZhuanyeEntity zy where zhuanyemingcheng like :name )";
        Query query=session.createQuery(hql).setString("name","%"+zhuanye+"%");
        long allRowCount=(Long) query.uniqueResult();
        session.close();
        return allRowCount;
    }
    public List<ZhuanyeEntity> getXuexiaoByName(String name, int offset, int pageSize){
        Session session=sessionFactory.openSession();
        String hql="from ZhuanyeEntity zy where zhuanyemingcheng like :name )";
        Query query=session.createQuery(hql).setString("name","%"+name+"%");
        query.setFirstResult(offset);
        query.setMaxResults(pageSize);
        List<ZhuanyeEntity> entities=query.list();
        session.close();
        return entities;
    }
}
