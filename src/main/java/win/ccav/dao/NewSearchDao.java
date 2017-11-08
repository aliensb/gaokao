package win.ccav.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import win.ccav.model.NewSearchResult;
import win.ccav.service.NewSearchService;

import java.util.List;


/**
 * Created by paul on 2017/6/22.
 */
@Repository
public class NewSearchDao {
    @Autowired
    private NewSearchService searchService;
    @Autowired
    private SessionFactory sessionFactory;
    public void Test(String hql){
        Session session =sessionFactory.openSession();
        Query query=session.createQuery(hql);
        List<NewSearchResult> list=query.list();
        for(NewSearchResult result:list){
            System.out.println("@@@@@@@@@@@@@@");
            System.out.println(result);
        }

        session.close();
    }
    public int getTotalPage(String hql){
        return 0;
    }
    public List<NewSearchResult> queryForPage(String hql){
        List<NewSearchResult> res=null;
        Session session=sessionFactory.openSession();
        Query query=session.createQuery(hql);
        res=query.list();
        session.close();
        return res;
    }
}
