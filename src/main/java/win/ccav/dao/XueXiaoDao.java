package win.ccav.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import win.ccav.model.XuexiaoEntity;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.Arrays;
import java.util.List;

/**
 * Created by paul on 2017/4/18.
 */
@Repository
public class XueXiaoDao {
    @Autowired
    private SessionFactory sessionFactory;
    public List<XuexiaoEntity> getXueXiaos(){
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from XuexiaoEntity");
        query.setFirstResult(0);
        query.setMaxResults(20);
        List<XuexiaoEntity> xuexiaoEntities=query.list();
        session.close();
        return xuexiaoEntities;
    }
    public List<XuexiaoEntity> getXueXiaoByPageNum(int pageNum,int pageSize){
        int firstResult=(pageNum-1)*pageSize;
        Session session=sessionFactory.openSession();
        Query query=session.createQuery("from XuexiaoEntity ");
        query.setFirstResult(firstResult);
        query.setMaxResults(pageSize);
        List<XuexiaoEntity> xuexiaoEntities=query.list();
        session.close();
        return xuexiaoEntities;
    }
    public List<XuexiaoEntity> queryForPage(int offset, int length) {
        Session session=sessionFactory.openSession();
        List<XuexiaoEntity> entitylist=null;
        try{
            Query query = session.createQuery("from XuexiaoEntity order by yucefenshu2017 desc");
            query.setFirstResult(offset);
            query.setMaxResults(length);
            entitylist = query.list();

        }catch(RuntimeException re){
            session.close();
            throw re;
        }
        session.close();
        return entitylist;
    }

    // TODO: 2017/4/19 将 uniqueResult
    public long getAllRowCount(){
        Session session=sessionFactory.openSession();
        String hql="select count(id) from XuexiaoEntity";
        Query query=session.createQuery(hql);
        long allRowCount=(Long) query.uniqueResult();
        session.close();
        return allRowCount;
    }
    public List<XuexiaoEntity> getDiqu(){
        Session session=sessionFactory.openSession();
        String hql="select distinct new XuexiaoEntity(diqu) from XuexiaoEntity";
        Query query=session.createQuery(hql);
        List<XuexiaoEntity> xuexiaoEntities=query.list();
        session.close();
        return xuexiaoEntities;
    }
    public List<String> getCheng(String diqu){
        Session session=sessionFactory.openSession();
        //language=HQL
        String hql="SELECT distinct chengshi FROM XuexiaoEntity where diqu=:mydiqu";
        Query query=session.createQuery(hql).setString("mydiqu",diqu);
        List<String> list=query.list();
        session.close();
        return list;
    }
    public List<String> getBanxueXingzhi(){
        Session session=sessionFactory.openSession();
        String hql="select distinct banxuexingzhi from XuexiaoEntity";
        Query query=session.createQuery(hql);
        List<String> list=query.list();
        session.close();
        return list;
    }
    public List<String> getlisu(){
        Session session=sessionFactory.openSession();
        String hql="select distinct lisu from XuexiaoEntity";
        Query query=session.createQuery(hql);
        List<String> list=query.list();
        session.close();
        return list;
    }

}
