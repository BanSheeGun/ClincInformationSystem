package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.JDBCUtils;
import dao.PatientDao;
import entity.Patient;

public class PatientDao4MySQL implements PatientDao {
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rs;
	
	private String Delete_Patient_By_Id_SQL =
			" DELETE FROM Patient WHERE PatientId = ?  ";
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updatePatientInfo(Patient patient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean createPatientInfo(Patient patient) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Patient> getPatient(int start) {
		connection = JDBCUtils.getConnection();
		List<Patient> p = new ArrayList<Patient>();
		String s = " SELECT * FROM Patient LIMIT ? , 10 ";
		try {
			pst = connection.prepareStatement(s);
			pst.setInt(1, start);
			rs = pst.executeQuery();
			while (rs.next()) {
				Patient pa = new Patient();
				pa.setAddress(rs.getString("Address"));
				pa.setAge(rs.getInt("Age"));
				pa.setEmail(rs.getString("Email"));
				pa.setFamilyId(rs.getInt("FamilyId"));
				pa.setName(rs.getString("Address"));
				pa.setPatientId(rs.getInt("PatientId"));
				pa.setSex(rs.getString("Sex"));
				pa.setTel(rs.getString("Tel"));
				p.add(pa);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return p;
	}

	@Override
	public int getPatientNumbers() {
		int ans = 0;
		connection = JDBCUtils.getConnection();
		String s = " SELECT COUNT(*) AS Amount FROM Patient ";
		try {
			pst = connection.prepareStatement(s);
			rs = pst.executeQuery();
			if (rs.next()) {
				ans = rs.getInt("Amount");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return ans;
	}

}
