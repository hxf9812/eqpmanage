package cn.hxf9812.eqpmanage.pojo;
/*设备表
master 设备负责人
indate购入时间
user使用者（用户）
status(当前状态)
belong从属实验室*/

import java.util.Date;
import java.util.List;

/**
 * 这是一个实验室设备实体类
 */
public class Eqp {
    /**
     * 编号
     */
    private int id;
    /**
     * 设备负责人用户账号
     */
    private String master;
    /**
     * 购入时间
     */
    private Date indate;
    /**
     * 当前使用者
     */
    private String user;
    /**
     * 当前使用状态
     * 0表示未被使用，1表示正在被使用
     */
    private int status;
    /**
     * 设备所属实验室
     */

    private String belong;
    /**
     * 负责人对象
     */
    private User masterUser;
    /**
     * 用户对象
     */
    private User userUser;

    public User getMasterUser() {
        return masterUser;
    }

    public void setMasterUser(User masterUser) {
        this.masterUser = masterUser;
    }

    public User getUserUser() {
        return userUser;
    }

    public void setUserUser(User userUser) {
        this.userUser = userUser;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public Date getIndate() {
        return indate;
    }

    public void setIndate(Date indate) {
        this.indate = indate;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getBelong() {
        return belong;
    }

    public void setBelong(String belong) {
        this.belong = belong;
    }

    @Override
    public String toString() {
        return "Eqp{" +
                "id=" + id +
                ", master='" + master + '\'' +
                ", indate=" + indate +
                ", user='" + user + '\'' +
                ", status=" + status +
                ", belong='" + belong + '\'' +
                ", masterUser=" + masterUser +
                ", userUser=" + userUser +
                '}';
    }
}
