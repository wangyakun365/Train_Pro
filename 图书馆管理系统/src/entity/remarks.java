package entity;
/**
 * ʵ��
 *    ���ۣ���������������Ա�
 * ������id
 * �����
 *      uid:users��id
 *      bid:books��id
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
		// TODO �Զ����ɵĹ��캯�����
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
