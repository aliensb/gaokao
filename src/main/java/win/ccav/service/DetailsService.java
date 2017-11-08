package win.ccav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.ccav.dao.DetailsDao;
import win.ccav.model.XuexiaoEntityEager;

/**
 * Created by paul on 2017/4/20.
 */
@Service
public class DetailsService {
    @Autowired
    private DetailsDao detailsDao;
    public XuexiaoEntityEager getDetails(int id) {
        return detailsDao.getXueXiaoById(id);
    }
}
