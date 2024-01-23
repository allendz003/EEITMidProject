package tw.chen.midproject.action;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import tw.chen.midproject.dao.CancerHospital;
import tw.chen.midproject.dao.CancerHospitalDaoFactory;
import tw.chen.midproject.dao.ICancerHospitalDao;

public class MPAction {

	public static void main(String[] args) throws Exception {

		ICancerHospitalDao chDao = CancerHospitalDaoFactory.creaCancerHospitalDaoFactory();

		try {
			chDao.createSqlConn();

			Scanner scan = new Scanner(System.in);

			while (true) {
				System.out.println("癌友導航醫院名冊系統");
				System.out.println("請選擇功能：");
				System.out.println("(0) 退出程式");
				System.out.println("(1) 根據ID查詢醫院");
				System.out.println("(2) 查詢所有醫院");
				System.out.println("(3) 透過url新增醫院");
				System.out.println("(4) 新增醫院");
				System.out.println("(5) 根據ID修改醫院資料");
				System.out.println("(6) 根據ID刪除醫院");
				System.out.println("(7) 儲存圖片");
				System.out.println("(8) 輸出圖片");

				int userInput = scan.nextInt();

				if (userInput == 0) {
					break;
				}
				if (userInput == 1) {
					System.out.println("請輸入ID：");
					int id = scan.nextInt();
					CancerHospital ch = chDao.findById(id);
					if (ch != null) {
						System.out.println(ch.getId() + " " + ch.getCity() + " " + ch.getHospitalLevel() + " "
								+ ch.getHospitalName() + " " + ch.getPostCode() + " " + ch.getHospitalAddress());
					} else {
						System.out.println("此ID為空號");
					}

				}
				if (userInput == 2) {
					List<CancerHospital> chList = chDao.findAll();
					for (CancerHospital ch : chList) {
						System.out.println(ch.getId() + " " + ch.getCity() + " " + ch.getHospitalLevel() + " "
								+ ch.getHospitalName() + " " + ch.getPostCode() + " " + ch.getHospitalAddress());
					}
				}

				if (userInput == 3) {
					try {
						System.out.println("請輸入URL：");
						String url = scan.next();
						chDao.inputFromUrl(url);
						System.out.println("成功輸入");
					} catch (Exception e) {
						System.out.println("網址輸入錯誤");
					}
				}

				if (userInput == 4) {
					try {
						System.out.println("請輸入縣市：");
						String city = scan.next();
						System.out.println("請輸入醫院等級：");
						String hLevel = scan.next();
						System.out.println("請輸入醫院名稱：");
						String hName = scan.next();
						System.out.println("請輸入郵遞區號：");
						int postCode = scan.nextInt();
						System.out.println("請輸入醫院地址：");
						String hAddress = scan.next();
						chDao.add(new CancerHospital(city, hLevel, hName, postCode, hAddress));
					} catch (Exception e) {
						System.out.println("郵遞區號輸入錯誤");
					}
				}

				if (userInput == 5) {
					try {
						System.out.println("請輸入ID：");
						int hId = scan.nextInt();
						System.out.println("請輸入縣市：");
						String city = scan.next();
						System.out.println("請輸入醫院等級：");
						String hLevel = scan.next();
						System.out.println("請輸入醫院名稱：");
						String hName = scan.next();
						System.out.println("請輸入郵遞區號：");
						int postCode = scan.nextInt();
						System.out.println("請輸入醫院地址：");
						String hAddress = scan.next();
						chDao.update(new CancerHospital(hId, city, hLevel, hName, postCode, hAddress));
					} catch (Exception e) {
						System.out.println("輸入錯誤");
					}
				}

				if (userInput == 6) {
					try {
						System.out.println("請輸入ID：");
						int hId = scan.nextInt();
						chDao.deleteById(hId);
					} catch (Exception e) {
						System.out.println("輸入錯誤");
					}
				}

				if (userInput == 7) {
					try {
						System.out.println("請輸入檔案位置：");
						String fileDir = scan.next();
						chDao.imageStoreProcess(fileDir);
					} catch (Exception e) {
						System.out.println("系統找不到指定的檔案");
					}
				}

				if (userInput == 8) {
					try {
						System.out.println("請輸入ID：");
						int id = scan.nextInt();
						chDao.imageRetrieveProcess(id);
						System.out.println("輸出成功");
					} catch (Exception e) {
						System.out.println("輸出失敗");
					}

				}

				for (int i = 1; i <= 3; i++) {
					System.out.println();
				}
			}

			scan.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("系統結束");
    chDao.closeSqlConn();
  }

}
