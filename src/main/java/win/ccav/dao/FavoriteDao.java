package win.ccav.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import win.ccav.model.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 2017/4/22.
 */
@Repository
public class FavoriteDao {
    @Autowired
    private SessionFactory sessionFactory;
    public List<XuexiaoCompare> getFavoriteXuexiao(List<Integer> ids){
        List<XuexiaoCompare> list=new ArrayList<XuexiaoCompare>();
        Session session=sessionFactory.openSession();
        String hql="FROM XuexiaoCompare WHERE id IN (:ids)";
        Query query=session.createQuery(hql).setParameterList("ids",ids);
        list=query.list();
        session.close();
        return list;
    }
    public ZhuanyeEntity getZhuanyeById(Integer id){
        Session session=sessionFactory.openSession();
        ZhuanyeEntity zhuanyeEntity=session.get(ZhuanyeEntity.class,id);
        session.close();
        return zhuanyeEntity;
    }
    public List<ZhuanyeCompare> getZhuanyes(ZhuanYeKey key){
        Session session=sessionFactory.openSession();
        String hql="from ZhuanyeCompare where xuexiaomingcheng=:xuexiaomingcheng and wenli=:wenli" +
                " and pici=:pici";
        Query query=session.createQuery(hql).setString("xuexiaomingcheng",key.getXuexiaomingcheng())
                .setString("wenli",key.getWenli()).setString("pici",key.getPici());
        List<ZhuanyeCompare> list=query.list();
        session.close();
        return list;
    }

}
