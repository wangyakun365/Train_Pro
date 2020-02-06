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

//对数据表进行crud操作
public class DButilDao {

	// 插入数据users
	public boolean insert(users user) throws Exception {
		// 连接数据库
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "insert into users(uname,upass,age,sex,level,status,vip) values (?,?,?,?,?,?,?)";

		// 受改变的行数，每一个属性都是格式化了一个对象
		int num = qr.update(sql, new Object[] { user.getUname(), user.getUpass(), user.getAge(), user.getSex(),
				user.getLevel(), user.getStatus(), user.getVip() });
		if (num > 0) {
			return true;
		}
		return false;
	}

	// 插入数据books
	public boolean insertBooks(books book) throws Exception {
		// 连接数据库
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "insert into books(bname,type, author, stocks, status, bcount) values (?,?,?,?,?,?)";

		// 受改变的行数，每一个属性都是格式化了一个对象
		int num = qr.update(sql, new Object[] { book.getBname(), book.getType(), book.getauthor(), book.getStocks(),
				book.getStatus(), book.getBcount() });
		if (num > 0) {
			return true;
		}
		return false;
	}

	// 插入数据order_record
	public boolean insertOrder_record(users user, int bid) throws Exception {
		// 连接数据库
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		// int uid=user.getId();
		String sql = "insert into order_record(uid, bid, sum,lendTime) values (?,?,?,?)";

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String lendTime = sdf.format(date);
		// 系统当前时间：lendTime
		// 受改变的行数，每一个属性都是格式化了一个对象
		int num1 = qr.update(sql, new Object[] { user.getId(), bid, 1, lendTime });

		// 在books表的库存-1
		String sql2 = "update books set stocks=stocks-1 where id=?";
		int num2 = qr.update(sql2, new Object[] { bid });
		if (num1 > 0 && num2 > 0) {
			System.out.println("借书ok");
			return true;
		}
		return false;
	}

	// 还书操作；
	// 然后在用户在用户的借书表中添加还书时间;在还书表中插入一条记录(积分表中积分加1)，
	private void returnBooks(users user) throws Exception {
		// TODO 自动生成的方法存根
		// 显示该用户借过但还没还的书：
		analysisByUid(user.getId());

		System.out.println("请输入您要还的【书的id号】：");
		int bid = utils.Util.input.nextInt();

		// 连接数据库
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		// 插入还书时间
		String sql = "update order_record set returnTime=? where uid =? and bid=?";

		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String returnTime = sdf.format(date);
		// 受改变的行数，每一个属性都是格式化了一个对象
		int num1 = qr.update(sql, new Object[] { returnTime, user.getId(), bid });

		// 在还书表中插入一条记录(积分表中积分加1)，
		String sql2 = "insert into collect_record(uid, bid, hid) values (?,?,?)";
		int num2 = qr.update(sql2, new Object[] { user.getId(), bid, 1 });

		// 输入评价，在留言评价表里添加数据
		System.out.println("请输入您对本书的评价：");// 防止乱，输入英文
		String bookRemarks = utils.Util.input.next();
		String sql3 = "insert into remarks(uid, bid, remark,time,hid) values (?,?,?,?,?)";
		int num3 = qr.update(sql3, new Object[] { user.getId(), bid, bookRemarks, returnTime, 0 });

		// 在books表的库存+1
		String sql4 = "update books set stocks=stocks+1 where id=?";
		int num4 = qr.update(sql4, new Object[] { bid });

		if (num1 > 0 && num2 > 0 && num3 > 0 && num4 > 0) {
			System.out.println("还书成功!留言成功！");
		} else {
			System.out.println("操作失败！");
		}
	}

	// 预约书籍，在预约表中插入一条预约信息
	private void resverBooks(users user, int bid) throws Exception {
		// TODO 自动生成的方法存根
		// 连接数据库
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "insert into reserve_record(uid,bid, bookNum, hid) values (?,?,?,?)";

		// 受改变的行数，每一个属性都是格式化了一个对象
		int num = qr.update(sql, new Object[] { user.getId(), bid, 1, 0 });
		if (num > 0) {
			System.out.println("已为您预约成功！");
		} else {
			System.out.println("预约失败！");
		}

	}

	// 更改数据
	public boolean update(users user) throws Exception {
		// 连接数据库
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "update users set uname=?,sex=? where id=?";

		// 受改变的行数
		int num = qr.update(sql, new Object[] { user.getUname(), user.getSex(), user.getId() });
		if (num > 0) {
			return true;
		}
		return false;
	}

	// 删除数据users
	public boolean deleteUser(int id) throws Exception {
		// 连接数据库
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "delete from users where id=?";

		// 受改变的行数
		int num = qr.update(sql, id);
		if (num > 0) {
			// System.out.println("删除ok!");
			return true;
		}
		return false;
	}

	// 删除数据books
	public boolean deleteBooks(int id) throws Exception {
		// 连接数据库
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "delete from books where id=?";

		// 受改变的行数
		int num = qr.update(sql, id);
		if (num > 0) {
			// System.out.println("删除ok!");
			return true;
		}
		return false;
	}

	// 根据id查询用户
	public users findUserById(int id) throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select *  from users where id=?";
		users user = (users) qr.query(sql, new BeanHandler(users.class), new Object[] { id });// 每条记录都记录
		return user;
	}

	// 根据用户名，密码查询用户
	public users findUserByUnamePass(String uname, String upass) throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select *  from users where uname=? and upass=?";
		users user = (users) qr.query(sql, new BeanHandler(users.class), new Object[] { uname, upass });// 每条记录都记录
		return user;
	}

	// 根据id查询书
	public books findBooksById(int id) throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select *  from books where id=?";
		books book = (books) qr.query(sql, new BeanHandler(books.class), new Object[] { id });// 每条记录都记录
		return book;
	}

	/**
	 * 今日推荐
	 * 
	 * @throws Exception
	 */
	public static void showTop3() throws Exception {
		BaseDao baseDao = new BaseDao();

		String sql = "SELECT bid,SUM(sum) from order_record GROUP BY bid ORDER BY SUM(sum) DESC LIMIT 3";
		List list = (List) baseDao.query(sql, new ArrayListHandler());// 对象数组的数与上面的问号相对应
		// （sql语句，ResultSetHandler子类对象，对象数组{、对应参数}），放进了列表

		/**
		 * 分解出列表里的每一项 对于列表里的每一项，每一项都是对象数组，包括id，nama,password三个对象，
		 */
		Object[] arr1 = (Object[]) list.get(0);// 分解出列表里的每一项
		Object[] arr2 = (Object[]) list.get(1);
		Object[] arr3 = (Object[]) list.get(2);
		// 三个借阅量最大的
		// 第1本书的id为arr1[0];借阅量为arr1[1]
		// 第2本书的id为arr2[0];借阅量为arr2[1]
		// 第3本书的id为arr3[0];借阅量为arr3[1]
		int bid1 = (int) arr1[0];
		int bid2 = (int) arr2[0];
		int bid3 = (int) arr3[0];
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql1 = "select * from books where id=?";
		books book1 = (books) qr.query(sql1, new BeanHandler(books.class), new Object[] { bid1 });// 每条记录都记录
		// return book;
		String sql2 = "select * from books where id=?";
		books book2 = (books) qr.query(sql2, new BeanHandler(books.class), new Object[] { bid2 });// 每条记录都记录
		String sql3 = "select * from books where id=?";
		books book3 = (books) qr.query(sql3, new BeanHandler(books.class), new Object[] { bid3 });// 每条记录都记录
		System.out.println("书本id\t书名\t类型\t作者");
		System.out.println("------------------------------");
		System.out.println(book1.getId() + "\t" + book1.getBname() + "\t" + book1.getType() + "\t" + book1.getauthor());
		System.out.println(book2.getId() + "\t" + book2.getBname() + "\t" + book2.getType() + "\t" + book2.getauthor());
		System.out.println(book3.getId() + "\t" + book3.getBname() + "\t" + book3.getType() + "\t" + book3.getauthor());
	}

	public static void showRemarks() throws Exception {
		/*
		 * 显示留言评价
		 */
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select uid,bid,remark from remarks";
		List list = (List) qr.query(sql, new BeanListHandler(remarks.class));// 将结果集每条记录放到列表里
		// return list;
		remarks remark = null;
		System.out.println("用户id\t书本id\t评价\t");
		System.out.println("-------------------------------");
		for (int i = 0; i < list.size(); i++) {
			remark = (remarks) list.get(i);
			System.out.println(remark.getUid() + "\t" + remark.getBid() + "\t" + remark.getRemark());
		}
		System.out.println("ok!");
	}

	// 查询所有用户
	public List choice_down() throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select *  from users";
		List list = (List) qr.query(sql, new BeanListHandler(users.class));// 将结果集每条记录放到列表里
		return list;
	}

	// 下载表
	public void downTable(int choice_down) throws SQLException, IOException {
		String[] tableList = { "users", "books", "order_record", "reserve_record", "collect_record","remarks" };
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		//int choiceD = choice_down - 1;
		String sql = "select *  from " + tableList[choice_down-1];
		List list = null;
		
		switch (choice_down) {
		case 1:
			list = (List) qr.query(sql, new BeanListHandler(users.class));// 将结果集每条记录放到列表里
			break;
		case 2:
			list = (List) qr.query(sql, new BeanListHandler(books.class));// 将结果集每条记录放到列表里
			break;
		case 3:
			list = (List) qr.query(sql, new BeanListHandler(order_record.class));// 将结果集每条记录放到列表里
			break;
		case 4:
			list = (List) qr.query(sql, new BeanListHandler(reserve_record.class));// 将结果集每条记录放到列表里
			break;
		case 5:
			list = (List) qr.query(sql, new BeanListHandler(collect_record.class));// 将结果集每条记录放到列表里
			break;
		case 6:
			list = (List) qr.query(sql, new BeanListHandler(remarks.class));// 将结果集每条记录放到列表里
			break;
		default:
			System.out.println("选择错误！");
			break;
		}
		utils.Util.input.nextLine();
		
//		if (choice_down == 1) {
//			list = (List) qr.query(sql, new BeanListHandler(users.class));// 将结果集每条记录放到列表里
//		}
//		if (choice_down == 2) {
//			list = (List) qr.query(sql, new BeanListHandler(books.class));// 将结果集每条记录放到列表里
//		}
//		if (choice_down == 3) {
//			list = (List) qr.query(sql, new BeanListHandler(order_record.class));// 将结果集每条记录放到列表里
//		}
//		if (choice_down == 4) {
//			list = (List) qr.query(sql, new BeanListHandler(reserve_record.class));// 将结果集每条记录放到列表里
//		}
//		if (choice_down == 5) {
//			list = (List) qr.query(sql, new BeanListHandler(collect_record.class));// 将结果集每条记录放到列表里
//		}
//		if (choice_down == 6) {
//			list = (List) qr.query(sql, new BeanListHandler(remarks.class));// 将结果集每条记录放到列表里
//		}
//		else {
//			System.out.println("选择错误！");
//		}

		// System.out.println(list.get(i).toString());
		ByteArrayOutputStream bos = new ByteArrayOutputStream();
		FileOutputStream fos = new FileOutputStream(tableList[choice_down-1].substring(0, 1).toUpperCase()
				+ tableList[choice_down-1].substring(1, tableList[choice_down-1].length()) + ".txt", true);// 如果文件不存在，就新建，追加
		byte[] byteArray = bos.toByteArray();// 讲bos转成字节数组

		String string = "";

		for (int i = 0; i < list.size(); i++) {
			string = string + list.get(i).toString() + "\r\n";
		}
		bos.write(string.getBytes());
		bos.writeTo(fos);// 把ByteArrayOutputStream内部缓冲区的数据写进对应的文件输出流中
		fos.close();
		System.out.println("下载完毕！\n");

	}

	// 根据用户的id显示出所有的借书记录
	public void analysisByUid(int uid) throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select * from order_record where uid=?";
		List list = (List) qr.query(sql, new BeanListHandler(order_record.class), new Object[] { uid });// 将结果集每条记录放到列表里
		// return list;
		for (Object object : list) {
			System.out.println(object);
		}
	}

	// 根据书本的id显示出所有的借书记录
	public void analysisByBid(int bid) throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select * from order_record where bid=?";
		List list = (List) qr.query(sql, new BeanListHandler(order_record.class), new Object[] { bid });// 将结果集每条记录放到列表里
		// return list;
		for (Object object : list) {
			System.out.println(object);
		}
	}

	// 查询所有书
	public void findAllBooks() throws Exception {
		QueryRunner qr = new QueryRunner(c3p0Utils.getDataSource());
		String sql = "select *  from books";
		List list = (List) qr.query(sql, new BeanListHandler(books.class));// 将结果集每条记录放到列表里
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
		// return list;
	}

	/**
	 * 管理员统计
	 * 书本的id来统计，被几位用户借过,总计多少
	 * 
	 * @throws Exception
	 */
	private void bookAnalysisByBid() throws Exception {
		// TODO 自动生成的方法存根
		System.out.println("请输入该书的id：");
		int bid = utils.Util.input.nextInt();
		// 输出用户为uid的所有结束记录
		analysisByBid(bid);
		String sql1 = "select COUNT(*), SUM(sum) from order_record where bid=?";
		Object[] arr1 = (Object[]) test.Test.baseDao.query(sql1, new ArrayHandler(), new Object[] { bid });// 对象数组的数与上面的问号相对应
		System.out.println("*********************************");
		System.out.println("id为" + bid + "的书 被 " + arr1[0] + " 位用户借过！");
		System.out.println("总共被借了 " + arr1[1] + " 本！");
	}
	
	//根据用户id来统计
	private void bookAnalysisByUid() throws Exception {
		// TODO 自动生成的方法存根
		System.out.println("请输入用户的id：");
		int uid = utils.Util.input.nextInt();
		// 输出用户为uid的所有结束记录
		analysisByUid(uid);

		// 计算用户为uid的借书次数
		String sql1 = "select COUNT(*), SUM(sum) from order_record where uid=?";
		Object[] arr1 = (Object[]) test.Test.baseDao.query(sql1, new ArrayHandler(), new Object[] { uid });// 对象数组的数与上面的问号相对应
		System.out.println("*******************************");
		System.out.println("id为" + uid + "的用户共借书 " + arr1[0] + " 次！");
		System.out.println("共借了 " + arr1[1] + " 本书！");
	}

	/***
	 * 图书的增 String bname//书名 String type;//书本的类别 int author;//书本的作者 int
	 * stocks;//书本的库存量 int status; int bcount;//借出去的数量
	 * 
	 * @throws Exception
	 */
	public void addBooks() throws Exception {
		System.out.println("书名：");
		String bname = utils.Util.input.next();
		System.out.println("类别：");
		String type = utils.Util.input.next();
		System.out.println("作者：");
		String author = utils.Util.input.next();
		System.out.println("库存量：");
		int stocks = utils.Util.input.nextInt();
		System.out.println("status：");
		int status = utils.Util.input.nextInt();
		int bcount = 0;

		books book = null;
		book = new books(bname, type, author, stocks, status, bcount);
		boolean b = insertBooks(book);
		System.out.println(b);

	}

	// 删除书
	public void deleteBooks() throws Exception {
		findAllBooks();
		System.out.println("请输入你要删除书的id：");
		int id = utils.Util.input.nextInt();
		boolean b = deleteBooks(id);
		System.out.println(b);
	}

	// 更改信息
	public void update() throws Exception {
		System.out.println("请输入您要修改书的id:");
		int id = utils.Util.input.nextInt();
		books book = null;
		book = findBooksById(id);
		if (book != null) {
			System.out.println("该书的信息如下【以书名，类别为例修改】：" + book);
			System.out.println("请输入修改后的书名：");
			String bname = utils.Util.input.next();
			System.out.println("请输入修改后的类别：");
			String type = utils.Util.input.next();
			book.setBname(bname);
			book.setType(type);
			System.out.println("修改完毕，该书信息如下：" + book);
		} else {
			System.out.println("该书不存在！");
		}
	}

	// 查询信息
	public void select() throws Exception {
		findAllBooks();
		System.out.println("请输入您要查询书的id:");
		int id = utils.Util.input.nextInt();
		books book = null;
		book = findBooksById(id);
		if (book != null) {
			System.out.println("该书的信息如下：" + book);
		} else {
			System.out.println("该书不存在！");
		}
	}

	/*
	 * 借阅
	 * 
	 */

	public void orderResverBooks(users user) throws Exception {
		System.out.println("请输入您要借阅的书本的id:");
		int bid = utils.Util.input.nextInt();
		books book = findBooksById(bid);
		int stock = book.getStocks();// 获取库存
		if (stock > 0) {
			insertOrder_record(user, bid);
		} else {
			System.out.println("该书库存为0，您要预约吗？  是（Y/y） 否（N/n）");
			char yorn = utils.Util.input.next().charAt(0);
			switch (yorn) {
			case 'y':
			case 'Y':
				resverBooks(user, bid);
				// System.out.println("已为您预约成功！");
				break;
			case 'n':
			case 'N':
				System.out.println("不预约！");
				break;
			default:
				System.out.println("选项有误！");
				break;
			}

		}

	}
	// 登录系统

	public users login() throws Exception {
		System.out.println("请输入你的用户名：");
		String uname = utils.Util.input.next();
		System.out.println("请输入你的密码：");
		String upass = utils.Util.input.next();

		// 根据uname，upass查询user
		// users user = new users();
		users user = null;
		user = findUserByUnamePass(uname, upass);

		if (user != null) {
			//System.out.println(user.getId() + "," + user.getUname() + "," + user.getUpass());
			if (user.getLevel() == 1) {////// 管理员
				System.out.println("登录成功,管理员您好！");
				boolean t1 = true;
				while (t1) {
					char choice_bookscrud = view.view.bookscrud();
					switch (choice_bookscrud) {
					case '0':
						t1 = false;
						break;
					case '1':// 图书的增
						addBooks();
						break;
					case '2':// 图书的删
						deleteBooks();
						break;
					case '3':// 图书的改
						update();
						break;
					case '4':// 图书的查
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
							System.out.println("输入错误！");
							break;
						}
						break;
					case '6':// 下载表单信息
						int choice_down = view.view.showTable();
						downTable(choice_down);
						break;

					case '7':// 产看评价
						showRemarks();

						break;
					default:
						System.out.println("选项错误！");
						break;
					}

				}

			} else {////// 普通用户
				/**
				 * 可以查看个人信息、浏览书本信息，并查找书本进行借书、预约、续借等操作，借书之后可以进行还书。
				 * 当书本出现相应损坏或遗失等情况时，应当做出相应的惩罚，当用户积分为0时，用户账号冻结。此外还应对应有解冻功能。--可以不做
				 * 当书本没有库存的时候，用户可以对书籍进行相应的预约操作，预约后当书本有存库时应当给出相应的提示信息、当书本被预约后，对此书无法进行续借。
				 */
				System.out.println("登录成功,普通用户您好！");
				// System.out.println("登录成功");
				// 登录成功后就可以进行操作
				boolean t2 = true;
				while (t2) {
					char choice_userChoic = view.view.userChoic();
					// menuMent3(choice_userChoic,user);
					switch (choice_userChoic) {
					case '1':// 1:根据查看个人信息
						System.out.println("查询完毕，您的信息如下：");
						System.out.println(user);
						break;
					case '2':// 2：浏览书本信息
						findAllBooks();
						break;
					case '3':// 3：查询书本
						books book = null;

						System.out.println("请输入你要查询书的id:");
						book = findBooksById(utils.Util.input.nextInt());
						if (book != null) {
							System.out.println("查询完毕，id为" + book.getId() + "的信息如下：");
							System.out.println(book);
						} else {
							System.out.println("该书不存在！");
						}

						break;
					case '4':// 4：借阅;为0时询问是否预约书本
						orderResverBooks(user);

						break;
					case '5':// 统计自己借书信息
						analysisByUid(user.getId());
						String sql1 = "select COUNT(*), SUM(sum) from order_record where uid=?";
						Object[] arr1 = (Object[]) test.Test.baseDao.query(sql1, new ArrayHandler(),
								new Object[] { user.getId() });// 对象数组的数与上面的问号相对应
						System.out.println("您一借过 " + arr1[0] + " 次书，总计 " + arr1[1] + " 本。");
						System.out.println("*******************************************");
						break;
					case '6':// 还书
						returnBooks(user);

						break;
					case '7':
						showRemarks();
						break;
					case '0':
						t2 = false;
						break;

					default:
						System.out.println("选择有误！，请重新选择");
						break;
					}

				}

			}

		} else {
			System.out.println("账号或密码错误！");
		}
		return user;
	}
}
