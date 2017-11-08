package win.ccav.model;

/**
 * Created by paul on 2017/4/22.
 */
public class ZhuanYeKey {
    private String xuexiaomingcheng;
    private String  wenli;
    private String  pici;

    @Override
    public String toString() {
        return "ZhuanYeKey{" +
                "xuexiaomingcheng='" + xuexiaomingcheng + '\'' +
                ", wenli='" + wenli + '\'' +
                ", pici='" + pici + '\'' +
                '}';
    }

    public String getXuexiaomingcheng() {
        return xuexiaomingcheng;
    }

    public void setXuexiaomingcheng(String xuexiaomingcheng) {
        this.xuexiaomingcheng = xuexiaomingcheng;
    }

    public String getWenli() {
        return wenli;
    }

    public void setWenli(String wenli) {
        this.wenli = wenli;
    }

    public String getPici() {
        return pici;
    }

    public void setPici(String pici) {
        this.pici = pici;
    }
}
