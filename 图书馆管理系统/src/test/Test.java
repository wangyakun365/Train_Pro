package test;

import dao.BaseDao;
import dao.DButilDao;
import entity.users;

public class Test {
	private static DButilDao dao = new DButilDao();
	public static BaseDao baseDao = new BaseDao();

	public static void main(String[] args) throws Exception {
		//uname,upass,age,sex,level,status,vip
		System.out.println("��ӭ����ͼ��ݹ���ϵͳ");
		while (true) {
			System.out.println("1����¼��2��ע�᣻3���˳�");
			System.out.println("���������ѡ��;");
			char choiceLoginExit=utils.Util.input.next().charAt(0);
			switch (choiceLoginExit) {
			case '1':
				users user=dao.login();
				
				break;
			case '2':
				users users=view.view.createUsers();
				Boolean b=dao.insert(users);
				if (b) {
					System.out.println("ע��ɹ���");
				} else {
					System.out.println("ע��ʧ�ܣ�");
				}
				break;
			case '3':
				System.out.println("��л����ʹ�ã��ټ�");
				System.exit(0);
				break;
			default:
				System.out.println("����������������룡");
				
			}
		
		}
	}
}
