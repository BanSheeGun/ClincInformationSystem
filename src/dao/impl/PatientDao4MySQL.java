package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.*;
import entity.*;

public abstract class PatientDao4MySQL implements PatientDao {
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rs;
	
	private String Obation_Current_Page_Data_SQL = 
			" SELECT * FROM Patient LIMIT ? , ? ";
	private String Obation_Total_Record_Amount = 
			" SELECT COUNT(*) AS Amount FROM Patient ";
	private String Delete_Patient_By_Id_SQL =
			" DELETE FROM Patient WHERE PatientId = ?  ";
	private String Get_Patient_By_Id_SQL =
			" SELECT * FROM Patient WHERE PatientId = ?  ";
	
	@Override
	public boolean deletePatientById(int patientId) {
		connection = JDBCUtils.getConnection();
		boolean isDelete = false;
		if (connection != null)
			try {
				pst = connection.prepareStatement(Delete_Patient_By_Id_SQL);
				pst.setInt(1, patientId);
				isDelete = pst.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return isDelete;
	}


	@Override
	public Patient getPatientById(int patientId) {
		connection = JDBCUtils.getConnection();
		Patient p = null;
		if (connection != null)
			try {
				pst = connection.prepareStatement(Get_Patient_By_Id_SQL);
				pst.setInt(1, patientId);
				rs = pst.executeQuery();
				if (rs.next()) {
					p = new Patient();
					p.setPatientId(rs.getInt("PatientId"));
					p.setFamilyId(rs.getInt("FamilyId"));
					p.setAge(rs.getInt("Age"));
					p.setSex(rs.getString("Sex"));
					p.setName(rs.getString("Name"));
					p.setTel(rs.getString("Tel"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return p;
	}

	@Override
	public boolean updatePatientInfo(Patient p) {
		connection = JDBCUtils.getConnection();
		boolean isUpate = false;
		if (connection != null)
			try {
				String work = " UPDATE Patient SET Name = ? , Sex = ? , Tel = ? , Age = ? , FamilyId = ? WHERE PatientId = ? ";
				pst = connection.prepareStatement(work);
				pst.setString(1, p.getName());
				pst.setString(2, p.getSex());
				pst.setString(3, p.getTel());
				pst.setInt(4, p.getAge());
				pst.setInt(5, p.getFamilyId());
				pst.setInt(6, p.getPatientId());
				if (pst.executeUpdate() > 0)
					isUpate = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return isUpate;
	}
	@Override
	public boolean createPatientInfo(Patient patient) {
		connection = JDBCUtils.getConnection();
		return false;
	}

}
