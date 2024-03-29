package cn.hxf9812.eqpmanage.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 这是一个申请实体类
 */
@JsonIgnoreProperties(value = {"handler"})
public class Apply {
    /**
     * 请求编码
     */
    private int id;
    /**
     * 申请人账号
     */
    private String whoapply;
    /**
     * 处理人账号
     */
    private String whoapplyed;
    /**
     * 被申请的设备
     */
    private int applyeqp;
    /**
     * 处理结果
     */
    private int isdealed;
    /**
     * 申请用户
     */
    private User applyUser;
    /**
     * 被申请用户
     */
    private User applyedUser;

    //分页属性
    private int pageNum;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }
    /**
     *  被申请的设备
     */
    private Eqp applyedEqp;

    public Eqp getApplyedEqp() {
        return applyedEqp;
    }

    public void setApplyedEqp(Eqp applyedEqp) {
        this.applyedEqp = applyedEqp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getWhoapply() {
        return whoapply;
    }

    public void setWhoapply(String whoapply) {
        this.whoapply = whoapply;
    }

    public String getWhoapplyed() {
        return whoapplyed;
    }

    public void setWhoapplyed(String whoapplyed) {
        this.whoapplyed = whoapplyed;
    }

    public int getApplyeqp() {
        return applyeqp;
    }

    public void setApplyeqp(int applyeqp) {
        this.applyeqp = applyeqp;
    }

    public int getIsdealed() {
        return isdealed;
    }

    public void setIsdealed(int isdealed) {
        this.isdealed = isdealed;
    }

    public User getApplyUser() {
        return applyUser;
    }

    public void setApplyUser(User applyUser) {
        this.applyUser = applyUser;
    }

    public User getApplyedUser() {
        return applyedUser;
    }

    public void setApplyedUser(User applyedUser) {
        this.applyedUser = applyedUser;
    }

    @Override
    public String toString() {
        return "Apply{" +
                "id=" + id +
                ", whoapply='" + whoapply + '\'' +
                ", whoapplyed='" + whoapplyed + '\'' +
                ", applyeqp=" + applyeqp +
                ", isdealed=" + isdealed +
                ", applyUser=" + applyUser +
                ", applyedUser=" + applyedUser +
                ", applyedEqp=" + applyedEqp +
                '}';
    }
}
