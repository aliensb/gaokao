package win.ccav.model.tongji;

/**
 * Created by paul on 2017/4/26.
 */
public class TongJiModel {
    private String xuexiaomingcheng;
    private String wenli;
    private Double pingjun;
    private Integer count;

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

    public Double getPingjun() {
        return pingjun;
    }

    public void setPingjun(Double pingjun) {
        this.pingjun = pingjun;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "TongJiModel{" +
                "xuexiaomingcheng='" + xuexiaomingcheng + '\'' +
                ", wenli='" + wenli + '\'' +
                ", pingjun=" + pingjun +
                ", count=" + count +
                '}';
    }
}
