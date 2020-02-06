package test;

import dao.BaseDao;
import dao.DButilDao;
import entity.users;

public class Test {
	private static DButilDao dao = new DButilDao();
	public static BaseDao baseDao = new BaseDao();

	public static void main(String[] args) throws Exception {
		//uname,upass,age,sex,level,status,vip
		System.out.println("欢迎来到图书馆管理系统");
		while (true) {
			System.out.println("1：登录；2：注册；3：退出");
			System.out.println("请输入你的选择;");
			char choiceLoginExit=utils.Util.input.next().charAt(0);
			switch (choiceLoginExit) {
			case '1':
				users user=dao.login();
				
				break;
			case '2':
				users users=view.view.createUsers();
				Boolean b=dao.insert(users);
				if (b) {
					System.out.println("注册成功！");
				} else {
					System.out.println("注册失败！");
				}
				break;
			case '3':
				System.out.println("感谢本次使用，再见");
				System.exit(0);
				break;
			default:
				System.out.println("输入错误，请重新输入！");
				
			}
		
		}
	}
}
