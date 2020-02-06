package view;

import dao.DButilDao;
import entity.users;

public class view {
	
	/**
	 * 用户登录后：
	 * 查看个人信息、浏览书本信息，并查找书本进行借书、预约、
	 * 续借(选作)
	 * 借书之后可以进行还书。
	 * 当用户积分为0时，用户账号冻结。此外还应对应有解冻功能。--可以不做
	 * 当书本没有库存的时候，用户可以对书籍进行相应的预约操作

	 * 
	 * @return
	 * @throws Exception 
	 */
	public static char userChoic() throws Exception {
		System.out.println("1:查看个人信息\n2：浏览书本信息\n3：查询书本\n4：借阅/预约书本\n5：统计借阅信息\n6：还书\n7：查看评价信息\n0：返回\n今日推荐Top3：");
		DButilDao.showTop3();
		System.out.println("\n请输入你的选择：");
		char choice_menuMent3=utils.Util.input.next().charAt(0);
		
		return choice_menuMent3;
	}
	
	
	/**
	 * 管理员：
	 * 二级第一个菜单
	 * 对于图书进行增、删、查、改
	 */
	public static char bookscrud() {
		System.out.println("1:添加图书\n2：删除图书\n3：修改书本信息\n4：查询书本信息\n5：统计信息\n6：下载表单信息\n7：查看评价信息\n0：返回");
		System.out.println("请输入你的选择：");
		char choice_bookscrud=utils.Util.input.next().charAt(0);
		
		return choice_bookscrud;
	}
	
	public static char bookAnalysis() {
		System.out.println("1:根据用户id统计\n2：根据书本id统计");
		System.out.println("请输入你的选择：");
		char choice_bookAnalysis=utils.Util.input.next().charAt(0);
		
		return choice_bookAnalysis;
	}
	
	
	public static int showTable() {
		System.out.println("1：下载用户表users");
		System.out.println("2：下载书本表books");
		System.out.println("3：下载借书表order_record");
		System.out.println("4：下载预约表reserve_record");
		System.out.println("5：下载积分表collect_record");
		System.out.println("6：下载留言评价表remarks");
		System.out.println("请输入您要下载的选项：");
		int choice_down=utils.Util.input.nextInt();
		return choice_down;
	}
	

	/**
	 * 创建一个users对象
	 * 
	 */
	//uname,upass,age,sex,level,status,vip
	public static users createUsers() {
		users user = null;
		System.out.println("请输入用户名：");
		String uname = utils.Util.input.next();
		System.out.println("请输入密码：");
		String upass = utils.Util.input.next();
		System.out.println("请输入年龄：");
		int age = utils.Util.input.nextInt();
		System.out.println("请输入性别：");
		String sex = utils.Util.input.next();
		System.out.println("请输入level(1/0)：");
		int level = utils.Util.input.nextInt();
		System.out.println("请输入status：");
		int status = utils.Util.input.nextInt();
		System.out.println("请输入vip(1/0)：");
		int vip = utils.Util.input.nextInt();
		user=new users(uname, upass, age, sex, level, status, vip);
		
		return user;
	}
	
	
	
	
	
	
	

}
