package entity;
/**
 * 
 * 主键;id
 *      借阅表
 * 外键：
 *      users:uid
 *      books:bid
 * 
 * @author lenovo
 *
 */

public class order_record {

	private int id;
	private int uid;
	private int bid;
	private int sum;
	private int renews;
	private String lendTime;
	private String returnTime;
	
	
	
	
	public order_record() {
		super();
		// TODO 自动生成的构造函数存根
	}
	
	
	public order_record(int id, int uid, int bid, int sum, int renews, String lendTime, String returnTime) {
		super();
		this.id = id;
		this.uid = uid;
		this.bid = bid;
		this.sum = sum;
		this.renews = renews;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
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
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	public int getRenews() {
		return renews;
	}
	public void setRenews(int renews) {
		this.renews = renews;
	}
	public String getLendTime() {
		return lendTime;
	}
	public void setLendTime(String lendTime) {
		this.lendTime = lendTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}


	@Override
	public String toString() {
		return "order_record [id=" + id + ", uid=" + uid + ", bid=" + bid + ", sum=" + sum + ", renews=" + renews
				+ ", lendTime=" + lendTime + ", returnTime=" + returnTime + "]";
	}
	
	
	
}
