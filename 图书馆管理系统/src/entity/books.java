package entity;
/**
 * ʵ��
 * �鱾
 * 
 * ������id
 * @author lenovo
 *
 */

public class books {
	/**
	 * id,bname�ǿ�
	 */
	private int id;
	private String bname;
	private String type;//�鱾�����
	private String author;//�鱾������
	private int stocks;//�鱾�Ŀ����
	private int status;
	private int bcount;//���ȥ������
	
	public books() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}
	
	
	public books(String bname, String type, String author, int stocks, int status, int bcount) {
		super();
		this.bname = bname;
		this.type = type;
		this.author = author;
		this.stocks = stocks;
		this.status = status;
		this.bcount = bcount;
	}


	public books(int id, String bname, String type, String author, int stocks, int status, int bcount) {
		super();
		this.id = id;
		this.bname = bname;
		this.type = type;
		this.author = author;
		this.stocks = stocks;
		this.status = status;
		this.bcount = bcount;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getauthor() {
		return author;
	}
	public void setauthor(String author) {
		this.author = author;
	}
	public int getStocks() {
		return stocks;
	}
	public void setStocks(int stocks) {
		this.stocks = stocks;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getBcount() {
		return bcount;
	}
	public void setBcount(int bcount) {
		this.bcount = bcount;
	}
	@Override
	public String toString() {
		return "books [id=" + id + ", bname=" + bname + ", type=" + type + ", author=" + author + ", stocks=" + stocks
				+ ", status=" + status + ", bcount=" + bcount + "]";
	}
	
	
	
	

}
