package view;

import dao.DButilDao;
import entity.users;

public class view {
	
	/**
	 * �û���¼��
	 * �鿴������Ϣ������鱾��Ϣ���������鱾���н��顢ԤԼ��
	 * ����(ѡ��)
	 * ����֮����Խ��л��顣
	 * ���û�����Ϊ0ʱ���û��˺Ŷ��ᡣ���⻹Ӧ��Ӧ�нⶳ���ܡ�--���Բ���
	 * ���鱾û�п���ʱ���û����Զ��鼮������Ӧ��ԤԼ����

	 * 
	 * @return
	 * @throws Exception 
	 */
	public static char userChoic() throws Exception {
		System.out.println("1:�鿴������Ϣ\n2������鱾��Ϣ\n3����ѯ�鱾\n4������/ԤԼ�鱾\n5��ͳ�ƽ�����Ϣ\n6������\n7���鿴������Ϣ\n0������\n�����Ƽ�Top3��");
		DButilDao.showTop3();
		System.out.println("\n���������ѡ��");
		char choice_menuMent3=utils.Util.input.next().charAt(0);
		
		return choice_menuMent3;
	}
	
	
	/**
	 * ����Ա��
	 * ������һ���˵�
	 * ����ͼ���������ɾ���顢��
	 */
	public static char bookscrud() {
		System.out.println("1:���ͼ��\n2��ɾ��ͼ��\n3���޸��鱾��Ϣ\n4����ѯ�鱾��Ϣ\n5��ͳ����Ϣ\n6�����ر���Ϣ\n7���鿴������Ϣ\n0������");
		System.out.println("���������ѡ��");
		char choice_bookscrud=utils.Util.input.next().charAt(0);
		
		return choice_bookscrud;
	}
	
	public static char bookAnalysis() {
		System.out.println("1:�����û�idͳ��\n2�������鱾idͳ��");
		System.out.println("���������ѡ��");
		char choice_bookAnalysis=utils.Util.input.next().charAt(0);
		
		return choice_bookAnalysis;
	}
	
	
	public static int showTable() {
		System.out.println("1�������û���users");
		System.out.println("2�������鱾��books");
		System.out.println("3�����ؽ����order_record");
		System.out.println("4������ԤԼ��reserve_record");
		System.out.println("5�����ػ��ֱ�collect_record");
		System.out.println("6�������������۱�remarks");
		System.out.println("��������Ҫ���ص�ѡ�");
		int choice_down=utils.Util.input.nextInt();
		return choice_down;
	}
	

	/**
	 * ����һ��users����
	 * 
	 */
	//uname,upass,age,sex,level,status,vip
	public static users createUsers() {
		users user = null;
		System.out.println("�������û�����");
		String uname = utils.Util.input.next();
		System.out.println("���������룺");
		String upass = utils.Util.input.next();
		System.out.println("���������䣺");
		int age = utils.Util.input.nextInt();
		System.out.println("�������Ա�");
		String sex = utils.Util.input.next();
		System.out.println("������level(1/0)��");
		int level = utils.Util.input.nextInt();
		System.out.println("������status��");
		int status = utils.Util.input.nextInt();
		System.out.println("������vip(1/0)��");
		int vip = utils.Util.input.nextInt();
		user=new users(uname, upass, age, sex, level, status, vip);
		
		return user;
	}
	
	
	
	
	
	
	

}
