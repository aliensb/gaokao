package win.ccav.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Repository;
import win.ccav.model.XuexiaoEntity;
import win.ccav.model.save.StudentEntity;
import win.ccav.model.tongji.TongJiModel;

import java.sql.*;
import java.util.List;

/**
 * Created by paul on 2017/4/25.
 */
@Repository
@PropertySource("classpath:/application.properties")
public class TongjiDao {
    @Value("${spring.datasource.driverClassName}")
    private String drive;
    @Value("${spring.datasource.url}")
    private String jdbcUrl;
    @Value("${spring.datasource.username}")
    private String user;
    @Value("${spring.datasource.password}")
    private String password;
    @Autowired
    private SessionFactory sessionFactory;
//    public List<StudentEntity> getStudentByPageNum(int pageNum, int pageSize){
//        Session session=sessionFactory.openSession();
//        Query query=session.createQuery("from StudentEntity ");
//        query.setFirstResult(pageNum);
//        query.setMaxResults(pageSize);
//        List<StudentEntity> studentEntities=query.list();
//        session.close();
//        return studentEntities;
//    }
    public List<StudentEntity> getStudentByPageNum(int offset, int length) {
        Session session=sessionFactory.openSession();
        List<StudentEntity> entitylist=null;
        try{
            Query query = session.createQuery("from StudentEntity ");
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
    public long getAllRowCount(){
        Session session=sessionFactory.openSession();
        String hql="select count(id) from StudentEntity";
        Query query=session.createQuery(hql);
        long allRowCount=(Long) query.uniqueResult();
        session.close();
        return allRowCount;
    }
    //统计情况
    public List<TongJiModel> geTongji(int offset, int length) {
        Session session = sessionFactory.openSession();
        String sql = "SELECT xuexiaomingcheng as xuexiaomingcheng,wenli as wenli,avg(score) as pingjun,count(xuexiaomingcheng) as count\n" +
                "FROM student st,ziyuan_xuexiao zy\n" +
                "WHERE st.id=zy.stuid  GROUP BY xuexiaomingcheng,wenli\n" +
                "ORDER BY count DESC";
        Query query=session.createSQLQuery(sql).setResultTransformer(Transformers.aliasToBean(TongJiModel.class));
        //Query query=session.createSQLQuery(sql);
        query.setFirstResult(offset);
        query.setMaxResults(length);
        List<TongJiModel> models=query.list();
        session.close();
        return models;
    }
//    public long getAllTongjiRowCount(){
//        Session session=sessionFactory.openSession();
//        String sql = "SELECT count(*) from (SELECT xuexiaomingcheng,wenli FROM student st,ziyuan_xuexiao zy WHERE st.id=zy.stuid  GROUP BY xuexiaomingcheng,wenli) as tem";
//        Query query=session.createQuery(sql);
//        long allRowCount=(Long) query.uniqueResult();
//        session.close();
//        return allRowCount;
//    }
    public long getAllTongjiRowCount(){
        Connection conn = null;
        PreparedStatement psmt = null;
        ResultSet rs = null;
        long rowCount=0;
        try {
            Class.forName(drive);
            String url = jdbcUrl;
            String userName = user;
            String passwd = password;
            conn = DriverManager.getConnection(url, userName, passwd);
            String sql = "SELECT count(*) as rowCountnum from (SELECT xuexiaomingcheng,wenli FROM student st,ziyuan_xuexiao zy WHERE st.id=zy.stuid  GROUP BY xuexiaomingcheng,wenli) as tem";
            psmt=conn.prepareStatement(sql);
            rs = psmt.executeQuery();
            while (rs.next()) {
                rowCount=rs.getLong("rowCountnum");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (rs != null) psmt.close();
                if (rs != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return rowCount;
    }
}
