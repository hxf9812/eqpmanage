package cn.hxf9812.eqpmanage.pojo;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

/**
 * 维修申报请求对象
 */
@JsonIgnoreProperties(value = {"handler"})
public class Declare {
    /**
     * 编号
     */
    private int id;
    /**
     * 需要维修设备的id
     */
    private int eqpid;
    /**
     * 设备申请人账号
     */
    private String declarant;
    /**
     * 设备负责人账号
     */
    private String master;
    /**
     * 申报的日期
     */
    private Date decalredate;
    /**
     * 申报的原因
     */
    private String content;
    /**
     * 处理状态 0未被处理  1故障认证  2已处理
     */
    private int dealstatus;

    /**
     * 申请人用户信息
     */
    private User declarantUser;
    /**
     * 管理员用户信息
     */
    private User masterUser;
    /**
     * 设备信息
     */
    private Eqp eqp;

    public User getDeclarantUser() {
        return declarantUser;
    }

    public void setDeclarantUser(User declarantUser) {
        this.declarantUser = declarantUser;
    }

    public User getMasterUser() {
        return masterUser;
    }

    public void setMasterUser(User masterUser) {
        this.masterUser = masterUser;
    }

    public Eqp getEqp() {
        return eqp;
    }

    public void setEqp(Eqp eqp) {
        this.eqp = eqp;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEqpid() {
        return eqpid;
    }

    public void setEqpid(int eqpid) {
        this.eqpid = eqpid;
    }

    public String getDeclarant() {
        return declarant;
    }

    public void setDeclarant(String declarant) {
        this.declarant = declarant;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Date getDecalredate() {
        return decalredate;
    }

    public void setDecalredate(Date decalredate) {
        this.decalredate = decalredate;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getDealstatus() {
        return dealstatus;
    }

    public void setDealstatus(int dealstatus) {
        this.dealstatus = dealstatus;
    }

    @Override
    public String toString() {
        return "Declare{" +
                "id=" + id +
                ", eqpid=" + eqpid +
                ", declarant='" + declarant + '\'' +
                ", master='" + master + '\'' +
                ", decalredate=" + decalredate +
                ", content='" + content + '\'' +
                ", dealstatus=" + dealstatus +
                '}';
    }
}
