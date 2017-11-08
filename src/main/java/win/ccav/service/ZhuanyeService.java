package win.ccav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.ccav.dao.XueXiaoDao;
import win.ccav.dao.ZhuanyeSearchDao;
import win.ccav.model.Page;
import win.ccav.model.XuexiaoEntity;
import win.ccav.model.ZhuanyeEntity;

import java.util.List;

/**
 * Created by paul on 2017/6/22.
 */
@Service
public class ZhuanyeService {
    @Autowired
    private ZhuanyeSearchDao searchDao;
    @Autowired
    private XueXiaoDao xueXiaoDao;
    public Page searchForPage(String name, int currentPage, int pageSize) {
        Page page = new Page();
        //总记录数
        long allRow = searchDao.getAllRowCountByZhuanye(name);
        //当前页开始记录
        int offset = page.countOffset(currentPage,pageSize);
        //分页查询结果集
        List<ZhuanyeEntity> list = searchDao.getXuexiaoByName(name,offset,pageSize);
        page.setDiqu(xueXiaoDao.getDiqu());
        page.setBanxuexingzhi(xueXiaoDao.getBanxueXingzhi());
        page.setPageNo(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);
        return page;
    }
}
