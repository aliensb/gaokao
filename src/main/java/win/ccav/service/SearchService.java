package win.ccav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.ccav.dao.SearchDao;
import win.ccav.dao.XueXiaoDao;
import win.ccav.model.Page;
import win.ccav.model.SearchForm;
import win.ccav.model.WeiciSearchForm;
import win.ccav.model.XuexiaoEntity;

import java.util.List;

/**
 * Created by paul on 2017/4/19.
 */
@Service
public class SearchService {
    @Autowired
    private SearchDao searchDao;
    @Autowired
    private XueXiaoDao xueXiaoDao;
    //根本表单来构造一个查询的hql语句
    private String formatHql(SearchForm searchForm){
        StringBuffer stringBuffer=new StringBuffer("erro");
        if(searchForm.getStuScore()==null||searchForm.getStuScore()==0||searchForm.getBodongzhi()==null
                ||searchForm.getBodongzhi()==0){
            return "erro";
        }
        else {
            double minSore=searchForm.getStuScore()-searchForm.getBodongzhi();
            double maxSore=searchForm.getStuScore()+searchForm.getBodongzhi();
            stringBuffer=new StringBuffer("from XuexiaoEntity where yucefenshu2017 BETWEEN ");
            stringBuffer.append(String.valueOf(minSore)+" and "+String.valueOf(maxSore));
            if(!searchForm.getShenfen().equals("不限")){
                stringBuffer.append(" and diqu = '"+searchForm.getShenfen()+"' ");
                if(!searchForm.getChengshi().equals("不限")){
                    stringBuffer.append(" and chengshi = '"+searchForm.getChengshi()+"' ");
                }
            }
            if(!searchForm.getBanxuexingzhi().equals("不限")){
                stringBuffer.append(" and banxuexingzhi = '"+searchForm.getBanxuexingzhi()+"' ");
            }
            stringBuffer.append(" and wenli = '"+searchForm.getWenli()+"' ");
            stringBuffer.append(" and pici = '"+searchForm.getPici()+"' ");
            stringBuffer.append(" order by yucefenshu2017 "+searchForm.getSort());
        }
        return stringBuffer.toString();
    }
    private String weiciFormatHql(WeiciSearchForm searchForm){
        StringBuffer stringBuffer=new StringBuffer("erro");
        if(searchForm.getStuWeici()==null||searchForm.getStuWeici()==0||searchForm.getBodongzhi()==null
                ||searchForm.getBodongzhi()==0){
            return "erro";
        }
        else {
            double minSore=searchForm.getStuWeici()-searchForm.getBodongzhi();
            double maxSore=searchForm.getStuWeici()+searchForm.getBodongzhi();
            stringBuffer=new StringBuffer("from XuexiaoEntity where yuceweici2017 BETWEEN ");
            stringBuffer.append(String.valueOf(minSore)+" and "+String.valueOf(maxSore));
            if(!searchForm.getShenfen().equals("不限")){
                stringBuffer.append(" and diqu = '"+searchForm.getShenfen()+"' ");
                if(!searchForm.getChengshi().equals("不限")){
                    stringBuffer.append(" and chengshi = '"+searchForm.getChengshi()+"' ");
                }
            }
            if(!searchForm.getBanxuexingzhi().equals("不限")){
                stringBuffer.append(" and banxuexingzhi = '"+searchForm.getBanxuexingzhi()+"' ");
            }
            stringBuffer.append(" and wenli = '"+searchForm.getWenli()+"' ");
            stringBuffer.append(" and pici = '"+searchForm.getPici()+"' ");
            stringBuffer.append(" order by yuceweici2017 "+searchForm.getSort());
        }
        return stringBuffer.toString();
    }
    
    //返回一个查询查询条条件查询后的结果的数量
    private String formatCountHql(SearchForm searchForm){
        StringBuffer stringBuffer=new StringBuffer("select count(id) ");
        stringBuffer.append(formatHql(searchForm));
        String res=stringBuffer.toString().split("order by")[0];
        return res;
    }
    private String weiciFormatCountHql(WeiciSearchForm searchForm){
        StringBuffer stringBuffer=new StringBuffer("select count(id) ");
        stringBuffer.append(weiciFormatHql(searchForm));
        String res=stringBuffer.toString().split("order by")[0];
        return res;
    }


    //放弃的方法
    public String getSearchHql(SearchForm searchForm){
        StringBuffer stringBuffer=null;
        if(searchForm.getStuScore()==null||searchForm.getStuScore()==0||searchForm.getBodongzhi()==null
                ||searchForm.getBodongzhi()==0){
            return "erro";
        }
        else {
            double minSore=searchForm.getStuScore()-searchForm.getBodongzhi();
            double maxSore=searchForm.getStuScore()+searchForm.getBodongzhi();
            stringBuffer=new StringBuffer("from XuexiaoEntity where yucefenshu2017 BETWEEN ");
            stringBuffer.append(String.valueOf(minSore)+" and "+String.valueOf(maxSore));
            if(!searchForm.getShenfen().equals("不限")){
                if(!searchForm.getChengshi().equals("不限")){
                    stringBuffer.append(" and diqu = '"+searchForm.getShenfen()+"' and chengshi = '"+searchForm.getChengshi()+"'");
                }else {
                    stringBuffer.append(" and diqu = '"+searchForm.getShenfen()+"'");
                }
                stringBuffer.append(" and pici = '"+searchForm.getPici()+"' and wenli = '"+ searchForm.getWenli()+"'"
                +" and banxuexingzhi = '"+searchForm.getBanxuexingzhi()+"' order by yucefenshu2017 "+searchForm.getSort()
                );
            }else {
                if(!searchForm.getBanxuexingzhi().equals("不限")){
                    stringBuffer.append(" and pici = '"+searchForm.getPici()+"' and wenli = '"+ searchForm.getWenli()+"'"
                            +" and banxuexingzhi = '"+searchForm.getBanxuexingzhi()+"' order by yucefenshu2017 "+searchForm.getSort()
                    );
                }
                stringBuffer.append(" and pici = '"+searchForm.getPici()+"' and wenli = '"+ searchForm.getWenli()+"'"
                        +" and banxuexingzhi = '"+searchForm.getBanxuexingzhi()+"' order by yucefenshu2017 "+searchForm.getSort()
                );
            }
        }
        return stringBuffer.toString();
    }
    private List<XuexiaoEntity> search(SearchForm searchForm,int offset,int length){
        List<XuexiaoEntity> res=null;
        String hql=formatHql(searchForm);
        if(!hql.equals("erro")){
            res=searchDao.search(hql,offset,length);
        }
        return res;
    }
    private List<XuexiaoEntity> search(WeiciSearchForm searchForm,int offset,int length){
        List<XuexiaoEntity> res=null;
        String hql=weiciFormatHql(searchForm);
        if(!hql.equals("erro")){

            res=searchDao.search(hql,offset,length);
        }
        return res;
    }
    //分页的查询结果
    public Page queryForPage(SearchForm searchForm,int currentPage, int pageSize) {

        Page page = new Page();
        //总记录数
        long allRow = searchDao.getAllRowCount(formatCountHql(searchForm));
        //当前页开始记录
        int offset = page.countOffset(currentPage,pageSize);
        String hql=formatHql(searchForm);
        //分页查询结果集
        List<XuexiaoEntity> list = search(searchForm,offset, pageSize);
        page.setDiqu(xueXiaoDao.getDiqu());
        page.setBanxuexingzhi(xueXiaoDao.getBanxueXingzhi());
        page.setPageNo(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);

        return page;
    }
    public Page weiciQueryForPage(WeiciSearchForm searchForm, int currentPage, int pageSize) {
        // TODO Auto-generated method stub

        Page page = new Page();
        //总记录数
        long allRow = searchDao.getAllRowCount(weiciFormatCountHql(searchForm));
        //当前页开始记录
        int offset = page.countOffset(currentPage,pageSize);
        String hql=weiciFormatHql((searchForm));
        //分页查询结果集
        List<XuexiaoEntity> list = search(searchForm,offset, pageSize);
        page.setDiqu(xueXiaoDao.getDiqu());
        page.setBanxuexingzhi(xueXiaoDao.getBanxueXingzhi());
        page.setPageNo(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);

        return page;
    }
    public Page searchForPage(String name,int currentPage, int pageSize) {

        Page page = new Page();
        //总记录数
        long allRow = searchDao.getAllRowCountByname(name);
        //当前页开始记录
        int offset = page.countOffset(currentPage,pageSize);
        //分页查询结果集
        List<XuexiaoEntity> list = searchDao.getXuexiaoByName(name,offset,pageSize);
        page.setDiqu(xueXiaoDao.getDiqu());
        page.setBanxuexingzhi(xueXiaoDao.getBanxueXingzhi());
        page.setPageNo(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);
        return page;
    }
}
