package tw.chen.midproject.dao;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CancerHospitalDaoJdbcImpl implements ICancerHospitalDao {

	private Connection conn;

	@Override
	public void createSqlConn() throws Exception {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String urlstr = "jdbc:sqlserver://localhost:1433;" + "databaseName=MidProject;" + "user=watcher;"
				+ "password=P@ssw0rd;" + "encrypt=true;" + "trustServerCertificate=true";
		conn = DriverManager.getConnection(urlstr);
		// System.out.println("連線狀態:" + !conn.isClosed());
	}

	@Override
	public void closeSqlConn() throws SQLException {
		if (conn != null) {
			conn.close();
		}
		System.out.println("關閉連線");
	}

	@Override
	public void inputFromUrl(String fileDir) throws Exception {
		URL url = new URL(fileDir);
		InputStream is = url.openStream();
		InputStreamReader isr = new InputStreamReader(is, "big5");
		BufferedReader br = new BufferedReader(isr);
		String line = null;
		br.readLine();
		while ((line = br.readLine()) != null) {
			String item[] = line.split(",");
			add(new CancerHospital(item[0].trim(), item[1].trim(), item[2].trim(), Integer.valueOf(item[3].trim()),
					item[4].trim()));
		}
		br.close();
		isr.close();
		is.close();
	}

	@Override
	public void add(CancerHospital ch) throws SQLException {
		String sqlstr = "Insert into CancerHospital(city, hospitalLevel, hospitalName, postCode, hospitalAddress) Values(?, ?, ?, ?, ?)";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setString(1, ch.getCity());
		state.setString(2, ch.getHospitalLevel());
		state.setString(3, ch.getHospitalName());
		state.setInt(4, ch.getPostCode());
		state.setString(5, ch.getHospitalAddress());
		state.execute();
		state.close();
	}

	@Override
	public void update(CancerHospital ch) throws SQLException {
		String sqlstr = "Update CancerHospital set city=?, hospitalLevel=?, hospitalName=?, postCode=?, hospitalAddress=? where id=?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setString(1, ch.getCity());
		state.setString(2, ch.getHospitalLevel());
		state.setString(3, ch.getHospitalName());
		state.setInt(4, ch.getPostCode());
		state.setString(5, ch.getHospitalAddress());
		state.setInt(6, ch.getId());
		state.execute();
		state.close();
	}

	@Override
	public void deleteById(int id) throws SQLException {
		String sqlstr = "Delete from CancerHospital where id=?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setInt(1, id);
		state.execute();
		state.close();
	}

	@Override
	public CancerHospital findById(int id) throws SQLException {
		final String sql = "SELECT * FROM CancerHospital WHERE id = ?";

		PreparedStatement preState = conn.prepareStatement(sql);
		preState.setInt(1, id);
		ResultSet rs = preState.executeQuery();
		if (rs.next()) {
			CancerHospital ch = new CancerHospital(rs.getInt("id"), rs.getString("city"), rs.getString("hospitalLevel"),
					rs.getString("hospitalName"), rs.getInt("postCode"), rs.getString("hospitalAddress"));
			return ch;
		}
		return null;
	}

	@Override
	public List<CancerHospital> findAll() throws SQLException {
		final String sql = "SELECT * FROM CancerHospital";

		PreparedStatement preState = conn.prepareStatement(sql);
		ResultSet rs = preState.executeQuery();

		List<CancerHospital> chList = new ArrayList<CancerHospital>();
		while (rs.next()) {
			CancerHospital ch = new CancerHospital(rs.getInt("id"), rs.getString("city"), rs.getString("hospitalLevel"),
					rs.getString("hospitalName"), rs.getInt("postCode"), rs.getString("hospitalAddress"));
			chList.add(ch);
		}
		rs.close();
		preState.close();
		return chList;
	}

	@Override
	public void imageRetrieveProcess(int id) throws SQLException, IOException {
		String sqlstr = "select*from ImgGallery where id=?";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setInt(1, id);
		ResultSet rs = state.executeQuery();
		if (rs.next()) {

			Blob blob = rs.getBlob(3);
			int length = (int) blob.length();
			//System.out.println("blob.length():" + length);
			FileOutputStream fos = new FileOutputStream("C:/Users/Student/Desktop/myImage.png");

			BufferedOutputStream bos = new BufferedOutputStream(fos);
			bos.write(blob.getBytes(1, length));
			bos.flush();
			bos.close();

			rs.close();
			state.close();
		}
	}

	@Override
	public void imageStoreProcess(String fileDir) throws SQLException, FileNotFoundException {
		FileInputStream fis = new FileInputStream(fileDir);
		String sqlstr = "insert into ImgGallery(imageName, imageFile) values(?,?)";
		PreparedStatement state = conn.prepareStatement(sqlstr);
		state.setString(1, "image");
		state.setBinaryStream(2, fis);
		state.executeUpdate();
		state.close();
		System.out.println("File Stored");
	}

}
