package cn.hxf9812.eqpmanage.pojo;

import java.util.Date;

/**
 * 这是维修日志类
 */
public class EqpLog {
    /**
     * 日志编号
     */
    private int id;
    /**
     * 维修的设备id
     */
    private int eqpid;
    /**
     * 损坏时间
     */
    private Date damagedate;
    /**
     * 损坏原因
     */
    private String cause;
    /**
     *损坏次数
     */
    private int damagetime;
    /**
     * 是否被处理
     */
    private int isrepaired;

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

    public Date getDamagedate() {
        return damagedate;
    }

    public void setDamagedate(Date damagedate) {
        this.damagedate = damagedate;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getDamagetime() {
        return damagetime;
    }

    public void setDamagetime(int damagetime) {
        this.damagetime = damagetime;
    }

    public int getIsrepaired() {
        return isrepaired;
    }

    public void setIsrepaired(int isrepaired) {
        this.isrepaired = isrepaired;
    }

    @Override
    public String toString() {
        return "EqpLog{" +
                "id=" + id +
                ", eqpid=" + eqpid +
                ", damagedate=" + damagedate +
                ", cause='" + cause + '\'' +
                ", damagetime=" + damagetime +
                ", isrepaired=" + isrepaired +
                '}';
    }
}
