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
		connection = JDBCUtils.getConnection();
		Patient pa = null;
		String s = " SELECT * FROM Patient WHERE PatientId = ? ";
		try {
			pst = connection.prepareStatement(s);
			pst.setInt(1, patientId);
			rs = pst.executeQuery();
			while (rs.next()) {
				pa = new Patient();
				pa.setAddress(rs.getString("Address"));
				pa.setAge(rs.getInt("Age"));
				pa.setEmail(rs.getString("Email"));
				pa.setFamilyId(rs.getInt("FamilyId"));
				pa.setName(rs.getString("Name"));
				pa.setPatientId(rs.getInt("PatientId"));
				pa.setSex(rs.getString("Sex"));
				pa.setTel(rs.getString("Tel"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return pa;
	}

	@Override
	public Patient updatePatientInfo(Patient p) {
		connection = JDBCUtils.getConnection();
		if (connection != null)
			try {
				String work = " UPDATE Patient SET Name = ? , Sex = ? , Tel = ? , Age = ? , FamilyId = ? , Address = ? "
						+ ", Email = ? WHERE PatientId = ? ";
				pst = connection.prepareStatement(work);
				pst.setString(1, p.getName());
				pst.setString(2, p.getSex());
				pst.setString(3, p.getTel());
				pst.setInt(4, p.getAge());
				pst.setInt(5, p.getFamilyId());
				pst.setString(6, p.getAddress());
				pst.setString(7, p.getEmail());
				pst.setInt(8, p.getPatientId());
				pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return getPatientById(p.getPatientId());
	}

	@Override
	public Patient createPatientInfo(Patient p) {
		connection = JDBCUtils.getConnection();
		if (connection != null)
			try {
				String work = " Insert into Patient(Name , Sex , Tel  , Age  , FamilyId  , Address "
						+ ", Email ) Values ( ? , ? , ? , ? , ? , ? , ? ) ";
				pst = connection.prepareStatement(work);
				pst.setString(1, p.getName());
				pst.setString(2, p.getSex());
				pst.setString(3, p.getTel());
				pst.setInt(4, p.getAge());
				pst.setInt(5, p.getFamilyId());
				pst.setString(6, p.getAddress());
				pst.setString(7, p.getEmail());
				pst.executeUpdate();
				String work2 = " Select * from Patient WHERE Name = ? and Sex = ? and Tel = ? "
						+ " and Age = ? and FamilyId = ? and Address = ? "
						+ " and Email = ? ";
				pst = connection.prepareStatement(work2);
				pst.setString(1, p.getName());
				pst.setString(2, p.getSex());
				pst.setString(3, p.getTel());
				pst.setInt(4, p.getAge());
				pst.setInt(5, p.getFamilyId());
				pst.setString(6, p.getAddress());
				pst.setString(7, p.getEmail());
				p = null;
				rs = pst.executeQuery();
				if (rs.next()) {
					p = new Patient();
					p.setPatientId(rs.getInt("PatientId"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return getPatientById(p.getPatientId());
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
				pa.setName(rs.getString("Name"));
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
