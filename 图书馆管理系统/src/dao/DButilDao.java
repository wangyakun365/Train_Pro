package dao;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.ArrayListHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import entity.books;
import entity.collect_record;
import entity.order_record;
import entity.remarks;
import entity.reserve_record;
import entity.users;
import utils.c3p0Utils;

//�����ݱ����crud����
public class DButilDao {

	// ��������users
	public boolean insert(users user) throws Exception {
		// �������ݿ�
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "insert into users(uname,upass,age,sex,level,status,vip) values (?,?,?,?,?,?,?)";

		// �ܸı��������ÿһ�����Զ��Ǹ�ʽ����һ������
		int num = qr.update(sql, new Object[] { user.getUname(), user.getUpass(), user.getAge(), user.getSex(),
				user.getLevel(), user.getStatus(), user.getVip() });
		if (num > 0) {
			return true;
		}
		return false;
	}

	// ��������books
	public boolean insertBooks(books book) throws Exception {
		// �������ݿ�
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "insert into books(bname,type, author, stocks, status, bcount) values (?,?,?,?,?,?)";

		// �ܸı��������ÿһ�����Զ��Ǹ�ʽ����һ������
		int num = qr.update(sql, new Object[] { book.getBname(), book.getType(), book.getauthor(), book.getStocks(),
				book.getStatus(), book.getBcount() });
		if (num > 0) {
			return true;
		}
		return false;
	}

	// ��������order_record
	public boolean insertOrder_record(users user, int bid) throws Exception {
		// �������ݿ�
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		// int uid=user.getId();
		String sql = "insert into order_record(uid, bid, sum,lendTime) values (?,?,?,?)";

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lendTime = sdf.format(date);
		// ϵͳ��ǰʱ�䣺lendTime
		// �ܸı��������ÿһ�����Զ��Ǹ�ʽ����һ������
		int num1 = qr.update(sql, new Object[] { user.getId(), bid, 1, lendTime });

		// ��books��Ŀ��-1
		String sql2 = "update books set stocks=stocks-1 where id=?";
		int num2 = qr.update(sql2, new Object[] { bid });
		if (num1 > 0 && num2 > 0) {
			System.out.println("����ok");
			return true;
		}
		return false;
	}

	// ���������
	// Ȼ�����û����û��Ľ��������ӻ���ʱ��;�ڻ�����в���һ����¼(���ֱ��л��ּ�1)��
	private void returnBooks(users user) throws Exception {
		// TODO �Զ����ɵķ������
		// ��ʾ���û��������û�����飺
		analysisByUid(user.getId());

		System.out.println("��������Ҫ���ġ����id�š���");
		int bid = utils.Util.input.nextInt();

		// �������ݿ�
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		// ���뻹��ʱ��
		String sql = "update order_record set returnTime=? where uid =? and bid=?";

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String returnTime = sdf.format(date);
		// �ܸı��������ÿһ�����Զ��Ǹ�ʽ����һ������
		int num1 = qr.update(sql, new Object[] { returnTime, user.getId(), bid });

		// �ڻ�����в���һ����¼(���ֱ��л��ּ�1)��
		String sql2 = "insert into collect_record(uid, bid, hid) values (?,?,?)";
		int num2 = qr.update(sql2, new Object[] { user.getId(), bid, 1 });

		// �������ۣ����������۱����������
		System.out.println("���������Ա�������ۣ�");// ��ֹ�ң�����Ӣ��
		String bookRemarks = utils.Util.input.next();
		String sql3 = "insert into remarks(uid, bid, remark,time,hid) values (?,?,?,?,?)";
		int num3 = qr.update(sql3, new Object[] { user.getId(), bid, bookRemarks, returnTime, 0 });

		// ��books��Ŀ��+1
		String sql4 = "update books set stocks=stocks+1 where id=?";
		int num4 = qr.update(sql4, new Object[] { bid });

		if (num1 > 0 && num2 > 0 && num3 > 0 && num4 > 0) {
			System.out.println("����ɹ�!���Գɹ���");
		} else {
			System.out.println("����ʧ�ܣ�");
		}
	}

	// ԤԼ�鼮����ԤԼ���в���һ��ԤԼ��Ϣ
	private void resverBooks(users user, int bid) throws Exception {
		// TODO �Զ����ɵķ������
		// �������ݿ�
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "insert into reserve_record(uid,bid, bookNum, hid) values (?,?,?,?)";

		// �ܸı��������ÿһ�����Զ��Ǹ�ʽ����һ������
		int num = qr.update(sql, new Object[] { user.getId(), bid, 1, 0 });
		if (num > 0) {
			System.out.println("��Ϊ��ԤԼ�ɹ���");
		} else {
			System.out.println("ԤԼʧ�ܣ�");
		}

	}

	// ��������
	public boolean update(users user) throws Exception {
		// �������ݿ�
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "update users set uname=?,sex=? where id=?";

		// �ܸı������
		int num = qr.update(sql, new Object[] { user.getUname(), user.getSex(), user.getId() });
		if (num > 0) {
			return true;
		}
		return false;
	}

	// ɾ������users
	public boolean deleteUser(int id) throws Exception {
		// �������ݿ�
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "delete from users where id=?";

		// �ܸı������
		int num = qr.update(sql, id);
		if (num > 0) {
			// System.out.println("ɾ��ok!");
			return true;
		}
		return false;
	}

	// ɾ������books
	public boolean deleteBooks(int id) throws Exception {
		// �������ݿ�
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "delete from books where id=?";

		// �ܸı������
		int num = qr.update(sql, id);
		if (num > 0) {
			// System.out.println("ɾ��ok!");
			return true;
		}
		return false;
	}

	// ����id��ѯ�û�
	public users findUserById(int id) throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select *  from users where id=?";
		users user = (users) qr.query(sql, new BeanHandler(users.class), new Object[] { id });// ÿ����¼����¼
		return user;
	}

	// �����û����������ѯ�û�
	public users findUserByUnamePass(String uname, String upass) throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select *  from users where uname=? and upass=?";
		users user = (users) qr.query(sql, new BeanHandler(users.class), new Object[] { uname, upass });// ÿ����¼����¼
		return user;
	}

	// ����id��ѯ��
	public books findBooksById(int id) throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select *  from books where id=?";
		books book = (books) qr.query(sql, new BeanHandler(books.class), new Object[] { id });// ÿ����¼����¼
		return book;
	}

	/**
	 * �����Ƽ�
	 * 
	 * @throws Exception
	 */
	public static void showTop3() throws Exception {
		BaseDao baseDao = new BaseDao();

		String sql = "SELECT bid,SUM(sum) from order_record GROUP BY bid ORDER BY SUM(sum) DESC LIMIT 3";
		List list = (List) baseDao.query(sql, new ArrayListHandler());// �������������������ʺ����Ӧ
		// ��sql��䣬ResultSetHandler������󣬶�������{����Ӧ����}�����Ž����б�

		/**
		 * �ֽ���б����ÿһ�� �����б����ÿһ�ÿһ��Ƕ������飬����id��nama,password��������
		 */
		Object[] arr1 = (Object[]) list.get(0);// �ֽ���б����ÿһ��
		Object[] arr2 = (Object[]) list.get(1);
		Object[] arr3 = (Object[]) list.get(2);
		// ��������������
		// ��1�����idΪarr1[0];������Ϊarr1[1]
		// ��2�����idΪarr2[0];������Ϊarr2[1]
		// ��3�����idΪarr3[0];������Ϊarr3[1]
		int bid1 = (int) arr1[0];
		int bid2 = (int) arr2[0];
		int bid3 = (int) arr3[0];
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql1 = "select * from books where id=?";
		books book1 = (books) qr.query(sql1, new BeanHandler(books.class), new Object[] { bid1 });// ÿ����¼����¼
		// return book;
		String sql2 = "select * from books where id=?";
		books book2 = (books) qr.query(sql2, new BeanHandler(books.class), new Object[] { bid2 });// ÿ����¼����¼
		String sql3 = "select * from books where id=?";
		books book3 = (books) qr.query(sql3, new BeanHandler(books.class), new Object[] { bid3 });// ÿ����¼����¼
		System.out.println("�鱾id\t����\t����\t����");
		System.out.println("------------------------------");
		System.out.println(book1.getId() + "\t" + book1.getBname() + "\t" + book1.getType() + "\t" + book1.getauthor());
		System.out.println(book2.getId() + "\t" + book2.getBname() + "\t" + book2.getType() + "\t" + book2.getauthor());
		System.out.println(book3.getId() + "\t" + book3.getBname() + "\t" + book3.getType() + "\t" + book3.getauthor());
	}

	public static void showRemarks() throws Exception {
		/*
		 * ��ʾ��������
		 */
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select uid,bid,remark from remarks";
		List list = (List) qr.query(sql, new BeanListHandler(remarks.class));// �������ÿ����¼�ŵ��б���
		// return list;
		remarks remark = null;
		System.out.println("�û�id\t�鱾id\t����\t");
		System.out.println("-------------------------------");
		for (int i = 0; i < list.size(); i++) {
			remark = (remarks) list.get(i);
			System.out.println(remark.getUid() + "\t" + remark.getBid() + "\t" + remark.getRemark());
		}
		System.out.println("ok!");
	}

	// ��ѯ�����û�
	public List choice_down() throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select *  from users";
		List list = (List) qr.query(sql, new BeanListHandler(users.class));// �������ÿ����¼�ŵ��б���
		return list;
	}

	// ���ر�
	public void downTable(int choice_down) throws SQLException, IOException {
		String[] tableList = { "users", "books", "order_record", "reserve_record", "collect_record","remarks" };
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		//int choiceD = choice_down - 1;
		String sql = "select *  from " + tableList[choice_down-1];
		List list = null;
		
		switch (choice_down) {
		case 1:
			list = (List) qr.query(sql, new BeanListHandler(users.class));// �������ÿ����¼�ŵ��б���
			break;
		case 2:
			list = (List) qr.query(sql, new BeanListHandler(books.class));// �������ÿ����¼�ŵ��б���
			break;
		case 3:
			list = (List) qr.query(sql, new BeanListHandler(order_record.class));// �������ÿ����¼�ŵ��б���
			break;
		case 4:
			list = (List) qr.query(sql, new BeanListHandler(reserve_record.class));// �������ÿ����¼�ŵ��б���
			break;
		case 5:
			list = (List) qr.query(sql, new BeanListHandler(collect_record.class));// �������ÿ����¼�ŵ��б���
			break;
		case 6:
			list = (List) qr.query(sql, new BeanListHandler(remarks.class));// �������ÿ����¼�ŵ��б���
			break;
		default:
			System.out.println("ѡ�����");
			break;
		}
		utils.Util.input.nextLine();
		
//		if (choice_down == 1) {
//			list = (List) qr.query(sql, new BeanListHandler(users.class));// �������ÿ����¼�ŵ��б���
//		}
//		if (choice_down == 2) {
//			list = (List) qr.query(sql, new BeanListHandler(books.class));// �������ÿ����¼�ŵ��б���
//		}
//		if (choice_down == 3) {
//			list = (List) qr.query(sql, new BeanListHandler(order_record.class));// �������ÿ����¼�ŵ��б���
//		}
//		if (choice_down == 4) {
//			list = (List) qr.query(sql, new BeanListHandler(reserve_record.class));// �������ÿ����¼�ŵ��б���
//		}
//		if (choice_down == 5) {
//			list = (List) qr.query(sql, new BeanListHandler(collect_record.class));// �������ÿ����¼�ŵ��б���
//		}
//		if (choice_down == 6) {
//			list = (List) qr.query(sql, new BeanListHandler(remarks.class));// �������ÿ����¼�ŵ��б���
//		}
//		else {
//			System.out.println("ѡ�����");
//		}

		// System.out.println(list.get(i).toString());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		FileOutputStream fos = new FileOutputStream(tableList[choice_down-1].substring(0, 1).toUpperCase()
				+ tableList[choice_down-1].substring(1, tableList[choice_down-1].length()) + ".txt", true);// ����ļ������ڣ����½���׷��
		byte[] byteArray = bos.toByteArray();// ��bosת���ֽ�����

		String string = "";

		for (int i = 0; i < list.size(); i++) {
			string = string + list.get(i).toString() + "\r\n";
		}
		bos.write(string.getBytes());
		bos.writeTo(fos);// ��ByteArrayOutputStream�ڲ�������������д����Ӧ���ļ��������
		fos.close();
		System.out.println("������ϣ�\n");

	}

	// �����û���id��ʾ�����еĽ����¼
	public void analysisByUid(int uid) throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select * from order_record where uid=?";
		List list = (List) qr.query(sql, new BeanListHandler(order_record.class), new Object[] { uid });// �������ÿ����¼�ŵ��б���
		// return list;
		for (Object object : list) {
			System.out.println(object);
		}
	}

	// �����鱾��id��ʾ�����еĽ����¼
	public void analysisByBid(int bid) throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select * from order_record where bid=?";
		List list = (List) qr.query(sql, new BeanListHandler(order_record.class), new Object[] { bid });// �������ÿ����¼�ŵ��б���
		// return list;
		for (Object object : list) {
			System.out.println(object);
		}
	}

	// ��ѯ������
	public void findAllBooks() throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select *  from books";
		List list = (List) qr.query(sql, new BeanListHandler(books.class));// �������ÿ����¼�ŵ��б���
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		// return list;
	}

	/**
	 * ����Աͳ��
	 * �鱾��id��ͳ�ƣ�����λ�û����,�ܼƶ���
	 * 
	 * @throws Exception
	 */
	private void bookAnalysisByBid() throws Exception {
		// TODO �Զ����ɵķ������
		System.out.println("����������id��");
		int bid = utils.Util.input.nextInt();
		// ����û�Ϊuid�����н�����¼
		analysisByBid(bid);
		String sql1 = "select COUNT(*), SUM(sum) from order_record where bid=?";
		Object[] arr1 = (Object[]) test.Test.baseDao.query(sql1, new ArrayHandler(), new Object[] { bid });// �������������������ʺ����Ӧ
		System.out.println("*********************************");
		System.out.println("idΪ" + bid + "���� �� " + arr1[0] + " λ�û������");
		System.out.println("�ܹ������� " + arr1[1] + " ����");
	}
	
	//�����û�id��ͳ��
	private void bookAnalysisByUid() throws Exception {
		// TODO �Զ����ɵķ������
		System.out.println("�������û���id��");
		int uid = utils.Util.input.nextInt();
		// ����û�Ϊuid�����н�����¼
		analysisByUid(uid);

		// �����û�Ϊuid�Ľ������
		String sql1 = "select COUNT(*), SUM(sum) from order_record where uid=?";
		Object[] arr1 = (Object[]) test.Test.baseDao.query(sql1, new ArrayHandler(), new Object[] { uid });// �������������������ʺ����Ӧ
		System.out.println("*******************************");
		System.out.println("idΪ" + uid + "���û������� " + arr1[0] + " �Σ�");
		System.out.println("������ " + arr1[1] + " ���飡");
	}

	/***
	 * ͼ����� String bname//���� String type;//�鱾����� int author;//�鱾������ int
	 * stocks;//�鱾�Ŀ���� int status; int bcount;//���ȥ������
	 * 
	 * @throws Exception
	 */
	public void addBooks() throws Exception {
		System.out.println("������");
		String bname = utils.Util.input.next();
		System.out.println("���");
		String type = utils.Util.input.next();
		System.out.println("���ߣ�");
		String author = utils.Util.input.next();
		System.out.println("�������");
		int stocks = utils.Util.input.nextInt();
		System.out.println("status��");
		int status = utils.Util.input.nextInt();
		int bcount = 0;

		books book = null;
		book = new books(bname, type, author, stocks, status, bcount);
		boolean b = insertBooks(book);
		System.out.println(b);

	}

	// ɾ����
	public void deleteBooks() throws Exception {
		findAllBooks();
		System.out.println("��������Ҫɾ�����id��");
		int id = utils.Util.input.nextInt();
		boolean b = deleteBooks(id);
		System.out.println(b);
	}

	// ������Ϣ
	public void update() throws Exception {
		System.out.println("��������Ҫ�޸����id:");
		int id = utils.Util.input.nextInt();
		books book = null;
		book = findBooksById(id);
		if (book != null) {
			System.out.println("�������Ϣ���¡������������Ϊ���޸ġ���" + book);
			System.out.println("�������޸ĺ��������");
			String bname = utils.Util.input.next();
			System.out.println("�������޸ĺ�����");
			String type = utils.Util.input.next();
			book.setBname(bname);
			book.setType(type);
			System.out.println("�޸���ϣ�������Ϣ���£�" + book);
		} else {
			System.out.println("���鲻���ڣ�");
		}
	}

	// ��ѯ��Ϣ
	public void select() throws Exception {
		findAllBooks();
		System.out.println("��������Ҫ��ѯ���id:");
		int id = utils.Util.input.nextInt();
		books book = null;
		book = findBooksById(id);
		if (book != null) {
			System.out.println("�������Ϣ���£�" + book);
		} else {
			System.out.println("���鲻���ڣ�");
		}
	}

	/*
	 * ����
	 * 
	 */

	public void orderResverBooks(users user) throws Exception {
		System.out.println("��������Ҫ���ĵ��鱾��id:");
		int bid = utils.Util.input.nextInt();
		books book = findBooksById(bid);
		int stock = book.getStocks();// ��ȡ���
		if (stock > 0) {
			insertOrder_record(user, bid);
		} else {
			System.out.println("������Ϊ0����ҪԤԼ��  �ǣ�Y/y�� ��N/n��");
			char yorn = utils.Util.input.next().charAt(0);
			switch (yorn) {
			case 'y':
			case 'Y':
				resverBooks(user, bid);
				// System.out.println("��Ϊ��ԤԼ�ɹ���");
				break;
			case 'n':
			case 'N':
				System.out.println("��ԤԼ��");
				break;
			default:
				System.out.println("ѡ������");
				break;
			}

		}

	}
	// ��¼ϵͳ

	public users login() throws Exception {
		System.out.println("����������û�����");
		String uname = utils.Util.input.next();
		System.out.println("������������룺");
		String upass = utils.Util.input.next();

		// ����uname��upass��ѯuser
		// users user = new users();
		users user = null;
		user = findUserByUnamePass(uname, upass);

		if (user != null) {
			//System.out.println(user.getId() + "," + user.getUname() + "," + user.getUpass());
			if (user.getLevel() == 1) {////// ����Ա
				System.out.println("��¼�ɹ�,����Ա���ã�");
				boolean t1 = true;
				while (t1) {
					char choice_bookscrud = view.view.bookscrud();
					switch (choice_bookscrud) {
					case '0':
						t1 = false;
						break;
					case '1':// ͼ�����
						addBooks();
						break;
					case '2':// ͼ���ɾ
						deleteBooks();
						break;
					case '3':// ͼ��ĸ�
						update();
						break;
					case '4':// ͼ��Ĳ�
						select();
						break;
					case '5':
						char choice_bookAnalysis = view.view.bookAnalysis();
						switch (choice_bookAnalysis) {
						case '1':
							bookAnalysisByUid();
							break;
						case '2':
							bookAnalysisByBid();
							break;
						default:
							System.out.println("�������");
							break;
						}
						break;
					case '6':// ���ر���Ϣ
						int choice_down = view.view.showTable();
						downTable(choice_down);
						break;

					case '7':// ��������
						showRemarks();

						break;
					default:
						System.out.println("ѡ�����");
						break;
					}

				}

			} else {////// ��ͨ�û�
				/**
				 * ���Բ鿴������Ϣ������鱾��Ϣ���������鱾���н��顢ԤԼ������Ȳ���������֮����Խ��л��顣
				 * ���鱾������Ӧ�𻵻���ʧ�����ʱ��Ӧ��������Ӧ�ĳͷ������û�����Ϊ0ʱ���û��˺Ŷ��ᡣ���⻹Ӧ��Ӧ�нⶳ���ܡ�--���Բ���
				 * ���鱾û�п���ʱ���û����Զ��鼮������Ӧ��ԤԼ������ԤԼ���鱾�д��ʱӦ��������Ӧ����ʾ��Ϣ�����鱾��ԤԼ�󣬶Դ����޷��������衣
				 */
				System.out.println("��¼�ɹ�,��ͨ�û����ã�");
				// System.out.println("��¼�ɹ�");
				// ��¼�ɹ���Ϳ��Խ��в���
				boolean t2 = true;
				while (t2) {
					char choice_userChoic = view.view.userChoic();
					// menuMent3(choice_userChoic,user);
					switch (choice_userChoic) {
					case '1':// 1:���ݲ鿴������Ϣ
						System.out.println("��ѯ��ϣ�������Ϣ���£�");
						System.out.println(user);
						break;
					case '2':// 2������鱾��Ϣ
						findAllBooks();
						break;
					case '3':// 3����ѯ�鱾
						books book = null;

						System.out.println("��������Ҫ��ѯ���id:");
						book = findBooksById(utils.Util.input.nextInt());
						if (book != null) {
							System.out.println("��ѯ��ϣ�idΪ" + book.getId() + "����Ϣ���£�");
							System.out.println(book);
						} else {
							System.out.println("���鲻���ڣ�");
						}

						break;
					case '4':// 4������;Ϊ0ʱѯ���Ƿ�ԤԼ�鱾
						orderResverBooks(user);

						break;
					case '5':// ͳ���Լ�������Ϣ
						analysisByUid(user.getId());
						String sql1 = "select COUNT(*), SUM(sum) from order_record where uid=?";
						Object[] arr1 = (Object[]) test.Test.baseDao.query(sql1, new ArrayHandler(),
								new Object[] { user.getId() });// �������������������ʺ����Ӧ
						System.out.println("��һ��� " + arr1[0] + " ���飬�ܼ� " + arr1[1] + " ����");
						System.out.println("*******************************************");
						break;
					case '6':// ����
						returnBooks(user);

						break;
					case '7':
						showRemarks();
						break;
					case '0':
						t2 = false;
						break;

					default:
						System.out.println("ѡ�����󣡣�������ѡ��");
						break;
					}

				}

			}

		} else {
			System.out.println("�˺Ż��������");
		}
		return user;
	}
}
