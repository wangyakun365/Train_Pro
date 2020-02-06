package entity;
/**
 * 
 * 预约表
 * 主键;id
 * 
 * 外键：
 *      users:uid
 *      books:bid
 * 
 * @author lenovo
 *
 */

public class reserve_record {
	private int id;
	private int uid;
	private int bid;
	private int bookNum;
	private int hid;
	
	
	
	public reserve_record() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	
	public reserve_record(int id, int uid, int bid, int bookNum, int hid) {
		super();
		this.id = id;
		this.uid = uid;
		this.bid = bid;
		this.bookNum = bookNum;
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
	public int getBookNum() {
		return bookNum;
	}
	public void setBookNum(int bookNum) {
		this.bookNum = bookNum;
	}
	public int getHid() {
		return hid;
	}
	public void setHid(int hid) {
		this.hid = hid;
	}


	@Override
	public String toString() {
		return "reserve_record [id=" + id + ", uid=" + uid + ", bid=" + bid + ", bookNum=" + bookNum + ", hid=" + hid
				+ "]";
	}
	
	

}
