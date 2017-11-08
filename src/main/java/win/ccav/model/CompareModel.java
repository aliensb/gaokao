package win.ccav.model;

import java.util.List;

/**
 * Created by paul on 2017/4/22.
 */
public class CompareModel {
    private XuexiaoEntity xuexiaoEntity;
    private List<ZhuanyeEntity> zhuanye;

    public XuexiaoEntity getXuexiaoEntity() {
        return xuexiaoEntity;
    }

    public void setXuexiaoEntity(XuexiaoEntity xuexiaoEntity) {
        this.xuexiaoEntity = xuexiaoEntity;
    }

    public List<ZhuanyeEntity> getZhuanye() {
        return zhuanye;
    }

    public void setZhuanye(List<ZhuanyeEntity> zhuanye) {
        this.zhuanye = zhuanye;
    }
}
