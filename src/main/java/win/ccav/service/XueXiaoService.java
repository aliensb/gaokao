package win.ccav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.ccav.dao.XueXiaoDao;
import win.ccav.model.Page;
import win.ccav.model.XuexiaoEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by paul on 2017/4/18.
 */
@Service
public class XueXiaoService {
    @Autowired
    private XueXiaoDao xueXiaoDao;
    public List<XuexiaoEntity> getXueXiaos(){
        return xueXiaoDao.getXueXiaos();
    }
    /**
     * 分页查询
     * @param currentPage 当前页号：现在显示的页数
     * @param pageSize 每页显示的记录条数
     * @return 封闭了分页信息(包括记录集list)的Bean
     * */
    @SuppressWarnings("unchecked")
    public Page queryForPage(int currentPage, int pageSize) {
        // TODO Auto-generated method stub
        Page page = new Page();
        //总记录数
        long allRow = xueXiaoDao.getAllRowCount();
        //当前页开始记录
        int offset = page.countOffset(currentPage,pageSize);
        //分页查询结果集
        List<XuexiaoEntity> list = xueXiaoDao.queryForPage(offset, pageSize);
        page.setDiqu(xueXiaoDao.getDiqu());
        page.setBanxuexingzhi(xueXiaoDao.getBanxueXingzhi());
        page.setPageNo(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);
        return page;
    }
    public List<String> getChengshi(String diqu){
        //return xueXiaoDao.getCheng(diqu);
        return xueXiaoDao.getCheng(diqu);
    }
}
