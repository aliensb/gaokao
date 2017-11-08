package win.ccav.model;

/**
 * Created by paul on 2017/4/18.
 */
import java.util.List;

public class Page<E> {
    // 结果集
    private List<E> list;

    // 查询记录总数
    private long totalRecords;

    // 每页多少条记录
    private int pageSize;

    // 第几页
    private int pageNo;

    private List<E> diqu;
    private List<String> banxuexingzhi;

    public List<String> getBanxuexingzhi() {
        return banxuexingzhi;
    }

    public void setBanxuexingzhi(List<String> banxuexingzhi) {
        this.banxuexingzhi = banxuexingzhi;
    }

    public List<E> getDiqu() {
        return diqu;
    }

    public void setDiqu(List<E> diqu) {
        this.diqu = diqu;
    }

    /**
     * @return 总页数
     * */
    public long getTotalPages(){
        return (totalRecords+pageSize-1)/pageSize;
    }

    /**
     * 计算当前页开始记录
     * @param pageSize 每页记录数
     * @param currentPage 当前第几页
     * @return 当前页开始记录号
     */
    public int countOffset(int currentPage,int pageSize){
        int offset = pageSize*(currentPage-1);
        return offset;
    }

    /**
     * @return 首页
     * */
    public int getTopPageNo(){
        return 1;
    }

    /**
     * @return 上一页
     * */
    public int getPreviousPageNo(){
        if(pageNo<=1){
            return 1;
        }
        return pageNo-1;
    }

    /**
     * @return 下一页
     * */
    public long getNextPageNo(){
        if(pageNo>=getBottomPageNo()){
            return getBottomPageNo();
        }
        return pageNo+1;
    }

    /**
     * @return 尾页
     * */
    public long getBottomPageNo(){
        return getTotalPages();
    }


    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

}