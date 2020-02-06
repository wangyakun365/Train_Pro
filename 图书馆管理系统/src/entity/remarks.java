package entity;
/**
 * 实体
 *    评论，反馈，对书的留言表
 * 主键：id
 * 外键：
 *      uid:users的id
 *      bid:books的id
 * @author lenovo
 *
 */

import java.sql.Timestamp;

public class remarks {
	private int id;
	private int uid;
	private int bid;
	private String remark;
	private Timestamp time;
	private int hid;
	
	
	
	public remarks() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	
	public remarks(int id, int uid, int bid, String remark, Timestamp time, int hid) {
		super();
		this.id = id;
		this.uid = uid;
		this.bid = bid;
		this.remark = remark;
		this.time = time;
		this.hid = hid;
	}


	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Timestamp getTime() {
		return time;
	}
	public void setTime(Timestamp time) {
		this.time = time;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}


	@Override
	public String toString() {
		return "remarks [id=" + id + ", uid=" + uid + ", bid=" + bid + ", remark=" + remark + ", time=" + time
				+ ", hid=" + hid + "]";
	}
	
	
	
	

}
