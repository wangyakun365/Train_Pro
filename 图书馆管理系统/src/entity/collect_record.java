package entity;

/**
 * 积分表
 * 主键：id
 * 外键：
 * 
 *     uid:users
 *     bid：books
 * @author lenovo
 *
 */

public class collect_record {
	private int id;
	private int uid;
	private int bid;
	private int hid;
	
	
	
	public collect_record() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	
	public collect_record(int id, int uid, int bid, int hid) {
		super();
		this.id = id;
		this.uid = uid;
		this.bid = bid;
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
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}


	@Override
	public String toString() {
		return "collect_record [id=" + id + ", uid=" + uid + ", bid=" + bid + ", hid=" + hid + "]";
	}
	
	
	
	

}
