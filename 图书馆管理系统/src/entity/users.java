package entity;

/**
 * ʵ��
 * �û�
 * 
 * ������id
 * @author lenovo
 *
 */

public class users {
	
	/**
	 * id,uname�ǿ�
	 */
	private int id;//�û�ID
	private String uname;//�û�������
	private String upass;//����
	private int age;//����
	private String sex;//�Ա�
	private int level;//�ȼ���1����Ա��0��ͨ
	private int status;//
	private int vip;//1�ǣ�0����
	public users() {
		super();
		// TODO �Զ����ɵĹ��캯�����
	}
	
	
	public users(String uname, String upass, int age, String sex, int level, int status, int vip) {
		super();
		this.uname = uname;
		this.upass = upass;
		this.age = age;
		this.sex = sex;
		this.level = level;
		this.status = status;
		this.vip = vip;
	}


	public users(int id, String uname, String upass, int age, String sex, int level, int status, int vip) {
		super();
		this.id = id;
		this.uname = uname;
		this.upass = upass;
		this.age = age;
		this.sex = sex;
		this.level = level;
		this.status = status;
		this.vip = vip;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUname() {
		return uname;
	}
	public void setUname(String uname) {
		this.uname = uname;
	}
	public String getUpass() {
		return upass;
	}
	public void setUpass(String upass) {
		this.upass = upass;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getVip() {
		return vip;
	}
	public void setVip(int vip) {
		this.vip = vip;
	}
	@Override
	public String toString() {
		return "users [id=" + id + ", uname=" + uname + ", upass=" + upass + ", age=" + age + ", sex=" + sex
				+ ", level=" + level + ", status=" + status + ", vip=" + vip + "]";
	}

	
}
