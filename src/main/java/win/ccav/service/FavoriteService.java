package win.ccav.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import win.ccav.dao.FavoriteDao;
import win.ccav.model.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by paul on 2017/4/21.
 */
@Service
public class FavoriteService {
    @Autowired
    private FavoriteDao favoriteDao;
    public List<XuexiaoCompare> getCompare(List<Favorite> allFavorite){
        List<Integer> ids=new ArrayList<Integer>();
        for(Favorite favorite:allFavorite){
           ids.add(favorite.getId());
        }
        List<XuexiaoCompare> xuexiaoCompares=favoriteDao.getFavoriteXuexiao(ids);
        Collections.sort(xuexiaoCompares);
        Collections.reverse(xuexiaoCompares);
        return xuexiaoCompares;
    }
    public ZhuanyeEntity getZhuanyeById(Integer id){
        return favoriteDao.getZhuanyeById(id);
    }
    public List<ZhuanyeCompare> getzhuanyes(ZhuanYeKey key){
        if(key==null||key.getPici()==null||key.getWenli()==null||key.getXuexiaomingcheng()==null){
            return null;
        }
        return favoriteDao.getZhuanyes(key);
    }
}
