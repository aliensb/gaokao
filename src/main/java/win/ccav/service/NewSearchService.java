package win.ccav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.ccav.dao.NewSearchDao;
import win.ccav.model.NewSearchForm;
import win.ccav.model.NewSearchResult;

import java.util.List;

/**
 * Created by paul on 2017/6/22.
 */
@Service
public class NewSearchService {
    @Autowired
    private NewSearchDao dao;
    public String formatHql(NewSearchForm searchForm){
        StringBuffer hql=new StringBuffer("select new win.ccav.model.NewSearchResult(xue.id, " +
                "xue.zhaoshengdaima, xue.xuexiaomingcheng, xue.diqu, xue.chengshi, " +
                "xue.banxuexingzhi, xue.jibie, xue.leixing, xue.wenli, xue.pici, xue.zhaoshengrenshu2017," +
                " xue.zhaoshenrenshu2016, xue.zhaoshenrenshu2015, xue.zhaoshenrenshu2014, " +
                "xue.zhaoshenrenshu2013, xue.yuceweici2017, xue.weici2016, xue.weici2015, xue.weici2014," +
                " xue.weici2013, xue.fenshu2013, xue.fenshu2014, xue.fenshu2015, xue.fenshu2016, xue.yucefenshu2017, " +
                "xue.zhaoshenguize, zhuan.zhuanyemingcheng) from " +
                "win.ccav.model.XuexiaoEntity xue,win.ccav.model.ZhuanyeEntity zhuan " +
                "where xue.id=zhuan.xuexiaoid ");
        if(searchForm.getChengshi()==null){
            searchForm.setChengshi("不限");
        }
        if(!searchForm.getShenfen().equals("不限")){
            hql.append(" and xue.diqu = '"+searchForm.getShenfen()+"' ");
            if(!searchForm.getChengshi().equals("不限")){
                hql.append(" and xue.chengshi = '"+searchForm.getChengshi()+"' ");
            }
        }
        if(!searchForm.getBanxuexingzhi().equals("不限")){
            hql.append(" and xue.banxuexingzhi = '"+searchForm.getBanxuexingzhi()+"' ");
        }
        hql.append(" and xue.wenli = '"+searchForm.getWenli()+"' ");
        hql.append(" and xue.pici = '"+searchForm.getPici()+"' ");
        if(!(searchForm.getxName().equals("")||searchForm.getxName().trim().equals(""))){
            hql.append(" and xue.xuexiaomingcheng like '%"+searchForm.getxName()+"%'");
        }
        if(!(searchForm.getzName().equals("")||searchForm.getzName().trim().equals(""))){
            hql.append(" and zhuan.zhuanyemingcheng like '%"+searchForm.getzName()+"%'");
        }

//        if((searchForm.getStuScore()==null&&searchForm.getScoreBodong()==null)){
//            System.out.println("kkongkkkkk");
//        }
//        else {
//            System.out.println("pass    ");
//        }
        if(searchForm.getStuScore()!=null){
            if(searchForm.getScoreBodong()!=null){
                double minScore=searchForm.getStuScore()-searchForm.getScoreBodong();
                double maxScore=searchForm.getStuScore()+searchForm.getScoreBodong();
                hql.append(" and xue.yucefenshu2017 between "+minScore+" and "+maxScore);
            }
        }
        if(searchForm.getStuWeici()!=null){
            if(searchForm.getWeiciBodong()!=null){
                int minWeici=searchForm.getStuWeici()-searchForm.getWeiciBodong();
                int maxWeici=searchForm.getStuWeici()+searchForm.getWeiciBodong();
                hql.append(" and xue.yuceweici2017 between "+minWeici+" and "+maxWeici);
            }
        }
        if(!searchForm.getLeixing().equals("buxian")){
            hql.append(" and xue.leixing='"+searchForm.getLeixing()+"' ");
        }
        if(searchForm.getSort().equals("fendesc")){
            hql.append(" order by xue.yucefenshu2017 desc");
        }else if(searchForm.getSort().equals("fenasc")){
            hql.append(" order by xue.yucefenshu2017 asc");
        }
        else if(searchForm.getSort().equals("bodesc")){
            hql.append(" order by xue.yuceweici2017 desc");
        }
        else if(searchForm.getSort().equals("boasc")){
            hql.append(" order by xue.yuceweici2017 asc");
        }

        System.out.println(hql.toString());
        System.out.println(searchForm.getxName());
        System.out.println(searchForm.getzName());
        return hql.toString();
    }
    public String Test(NewSearchForm searchForm){
        String hql=formatHql(searchForm);
        dao.Test(hql);
        return hql;
    }
    public List<NewSearchResult> queryForPage(NewSearchForm searchForm){
        String hql=formatHql(searchForm);
        System.out.println(hql);
        List<NewSearchResult> res=null;
        res=dao.queryForPage(hql);
        return res;
    }
}
