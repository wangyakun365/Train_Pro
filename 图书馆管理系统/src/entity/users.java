package entity;

/**
 * 实体
 * 用户
 * 
 * 主键：id
 * @author lenovo
 *
 */

public class users {
	
	/**
	 * id,uname非空
	 */
	private int id;//用户ID
	private String uname;//用户的姓名
	private String upass;//密码
	private int age;//年龄
	private String sex;//性别
	private int level;//等级，1管理员；0普通
	private int status;//
	private int vip;//1是；0不是
	public users() {
		super();
		// TODO 自动生成的构造函数存根
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
