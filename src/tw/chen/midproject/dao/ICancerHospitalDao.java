package tw.chen.midproject.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ICancerHospitalDao {

	public void inputFromUrl(String fileDir) throws Exception;

	public void add(CancerHospital dd) throws SQLException;

	public void update(CancerHospital dd) throws SQLException;

	public void deleteById(int id) throws SQLException;

	public CancerHospital findById(int id) throws SQLException;

	public List<CancerHospital> findAll() throws SQLException;

	public void createSqlConn() throws Exception;

	public void closeSqlConn() throws SQLException;

	public void imageRetrieveProcess(int id) throws SQLException, IOException;

	public void imageStoreProcess(String fileDir) throws SQLException, FileNotFoundException;

}
