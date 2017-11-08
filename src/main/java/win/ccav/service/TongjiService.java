package win.ccav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.ccav.dao.TongjiDao;
import win.ccav.model.Page;

import win.ccav.model.TongjiPage;
import win.ccav.model.save.StudentEntity;
import win.ccav.model.tongji.TongJiModel;

import java.util.List;

/**
 * Created by paul on 2017/4/25.
 */
@Service
public class TongjiService {
    @Autowired
    private TongjiDao tongjiDao;
    public TongjiPage queryForPage(int currentPage, int pageSize) {
        // TODO Auto-generated method stub
        TongjiPage page = new TongjiPage();
        //总记录数
        long allRow = tongjiDao.getAllRowCount();
        //当前页开始记录
        int offset = page.countOffset(currentPage,pageSize);
        //分页查询结果集
        List<StudentEntity> list = tongjiDao.getStudentByPageNum(offset, pageSize);

        page.setPageNo(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);
        return page;
    }
    public TongjiPage queryForTongjiPage(int currentPage, int pageSize) {
        // TODO Auto-generated method stub
        TongjiPage page = new TongjiPage();
        //总记录数
        long allRow = tongjiDao.getAllTongjiRowCount();
        //当前页开始记录
        int offset = page.countOffset(currentPage,pageSize);
        //分页查询结果集
        List<TongJiModel> list = tongjiDao.geTongji(offset, pageSize);
        page.setPageNo(currentPage);
        page.setPageSize(pageSize);
        page.setTotalRecords(allRow);
        page.setList(list);
        return page;
    }
}
