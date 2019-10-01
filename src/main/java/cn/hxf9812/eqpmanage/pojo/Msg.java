package cn.hxf9812.eqpmanage.pojo;

import java.util.HashMap;
import java.util.Map;

/**
 * 这是一个
 */
public class Msg {
    /**
     * 数据对象
     */
    private Map<String,Object> extend=new HashMap<String, Object>();
    /**
     * 成功失败
     */
    private boolean flag;
    /**
     * 提示信息
     */
    private String msg;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getExtend() {
        return extend;
    }

    public void setExtend(Map<String, Object> extend) {
        this.extend = extend;
    }

    public static Msg success(){
        Msg msg=new Msg();
        msg.setFlag(true);
        msg.setMsg("处理成功！");
        return msg;
    }
    public static Msg success(String success){
        Msg msg=new Msg();
        msg.setFlag(true);
        msg.setMsg(success);
        return msg;
    }
    public static Msg fail(){
        Msg msg=new Msg();
        msg.setFlag(false);
        msg.setMsg("处理失败！");
        return msg;
    }
    public static Msg fail(String exception){
        Msg msg=new Msg();
        msg.setFlag(false);
        msg.setMsg(exception);
        return msg;
    }
    public Msg add(String key,Object values){
        this.getExtend().put(key,values);
        return this;
    }
}
