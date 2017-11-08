package win.ccav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.ccav.dao.StudentDao;
import win.ccav.model.TianBaoForm;
import win.ccav.model.User;
import win.ccav.model.save.StudentEntity;
import win.ccav.model.save.ZiyuanXuexiaoEntity;
import win.ccav.model.save.ZiyuanZhuanyeEntity;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by paul on 2017/4/24.
 */
@Service
public class StudentService {
    @Autowired
    private StudentDao studentDao;

    public int saveOne(TianBaoForm form, HttpSession session) {
        int count=0;
        Set<ZiyuanZhuanyeEntity> zhuanyeEntities = new HashSet<ZiyuanZhuanyeEntity>();
        zhuanyeEntities.add(new ZiyuanZhuanyeEntity(form.getZhuanye1id(), form.getZhuanye1(), 1));
        zhuanyeEntities.add(new ZiyuanZhuanyeEntity(form.getZhuanye2id(), form.getZhuanye2(), 2));
        zhuanyeEntities.add(new ZiyuanZhuanyeEntity(form.getZhuanye3id(), form.getZhuanye3(), 3));
        zhuanyeEntities.add(new ZiyuanZhuanyeEntity(form.getZhuanye4id(), form.getZhuanye4(), 4));
        zhuanyeEntities.add(new ZiyuanZhuanyeEntity(form.getZhuanye5id(), form.getZhuanye5(), 5));
        zhuanyeEntities.add(new ZiyuanZhuanyeEntity(form.getZhuanye6id(), form.getZhuanye6(), 6));
        //zhuanyeEntities.add(new ZiyuanZhuanyeEntity(form.getZhuanye6id(), form.getZhuanye6(), 6));
        Map<String, ZiyuanXuexiaoEntity> ziyuanXuexiaoEntityMap = (Map<String, ZiyuanXuexiaoEntity>) session.getAttribute("xuexiaoMap");
        System.out.println(form);
        //先从session中读取有没有学校map
        if (ziyuanXuexiaoEntityMap == null) {
            //如果没有，就建立一个，然后将学校和志愿的序号放入这个map,在将map放入到session中
            ziyuanXuexiaoEntityMap = new HashMap<String, ZiyuanXuexiaoEntity>();
            ZiyuanXuexiaoEntity xuexiao = new ZiyuanXuexiaoEntity(form.getXuexiaoid(), form.getXuexiaomingcheng(), form.getXuexiaoorder(), zhuanyeEntities);
            ziyuanXuexiaoEntityMap.put(form.getZiyuanOrder(), xuexiao);
            session.setAttribute("xuexiaoMap", ziyuanXuexiaoEntityMap);
        } else {
            //如果有这个map。那就用志愿序号去读这个map,看有没有这个学校的记录
            ZiyuanXuexiaoEntity xuexiao = ziyuanXuexiaoEntityMap.get(form.getZiyuanOrder());
            if (xuexiao == null) {//这个if else判断实际上没意义
                //如果没有这个志愿。那就是说明这个志愿还没有填，那就创建这个志愿，将这个志愿放入到map中
                xuexiao = new ZiyuanXuexiaoEntity(form.getXuexiaoid(), form.getXuexiaomingcheng(), form.getXuexiaoorder(), zhuanyeEntities);
                ziyuanXuexiaoEntityMap.put(form.getZiyuanOrder(), xuexiao);
            } else {
                //如果有这个志愿，那就重新填这个志愿
                xuexiao = new ZiyuanXuexiaoEntity(form.getXuexiaoid(), form.getXuexiaomingcheng(), form.getXuexiaoorder(), zhuanyeEntities);
                ziyuanXuexiaoEntityMap.put(form.getZiyuanOrder(), xuexiao);
            }
            session.setAttribute("xuexiaoMap", ziyuanXuexiaoEntityMap);
        }
        count=ziyuanXuexiaoEntityMap.size();
        return count;
//        Set<ZiyuanXuexiaoEntity> xuexiaos = studentEntity.getZiyuanXuexiaoEntities();
//        if (xuexiaos == null || xuexiaos.size() == 0) {
//            Set<ZiyuanXuexiaoEntity> ziyuanXuexiaoEntities = new HashSet<ZiyuanXuexiaoEntity>();
//            ziyuanXuexiaoEntities.add(new ZiyuanXuexiaoEntity(form.getXuexiaoid(), form.getXuexiaomingcheng(), form.getXuexiaoorder(), zhuanyeEntities));
//            studentEntity.setZiyuanXuexiaoEntities(ziyuanXuexiaoEntities);
//        } else {
//            xuexiaos.add(new ZiyuanXuexiaoEntity(form.getXuexiaoid(), form.getXuexiaomingcheng(), form.getXuexiaoorder(), zhuanyeEntities));
//        }
//        StudentEntity studentEntity1 = (StudentEntity) session.getAttribute("stu");
    }

    public void saveStudent(StudentEntity studentEntity) {
        studentDao.save(studentEntity);
    }
    public void saveStudent(String name,Double score,String wenli,Map<String, ZiyuanXuexiaoEntity> ziyuanXuexiaoEntityMap,String userName){
        StudentEntity student=new StudentEntity();
        student.setSname(name);
        student.setScore(score);
        student.setWenli(wenli);
        student.setUserName(userName);
        Set<String> keys=ziyuanXuexiaoEntityMap.keySet();
        Iterator<String> iterator=keys.iterator();
        Set<ZiyuanXuexiaoEntity> ziyuanXuexiaoEntitySet=new HashSet<ZiyuanXuexiaoEntity>();
        while (iterator.hasNext()){
            ziyuanXuexiaoEntitySet.add(ziyuanXuexiaoEntityMap.get(iterator.next()));
        }
        student.setZiyuanXuexiaoEntities(ziyuanXuexiaoEntitySet);
        saveStudent(student);
    }

}
