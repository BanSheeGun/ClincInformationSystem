package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

import dao.*;
import entity.*;

public class FamilyDao4MySQL implements FamilyDao {
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rs;
	@Override
	public List<Patient> queryFamilyMembers(int fid) {
		connection = JDBCUtils.getConnection();
		List<Patient> p = new ArrayList<Patient>();
		String s = " SELECT * FROM Patient where FamilyId = ? ";
		try {
			pst = connection.prepareStatement(s);
			pst.setInt(1, fid);
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
	public Family queryFamily(int fid) {
		connection = JDBCUtils.getConnection();
		Family f = null;
		String s = " SELECT * FROM Family where FamilyId = ? ";
		try {
			pst = connection.prepareStatement(s);
			pst.setInt(1, fid);
			rs = pst.executeQuery();
			if (rs.next()) {
				f = new Family();
				f.setAddress(rs.getString("Address"));
				f.setFamilyId(rs.getInt("FamilyId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return f;
	}

	@Override
	public Family createFamily(String add) {
		connection = JDBCUtils.getConnection();
		Family f = null;
		String s = " SELECT * FROM Family where Address = \"" + add +  "\"";
		String s1 = "INSERT INTO Family (Address) VALUES (\"" + add +  "\") ";
		try {
			pst = connection.prepareStatement(s1);
			pst.execute();
			pst = connection.prepareStatement(s);
			rs = pst.executeQuery();
			if (rs.next()) {
				f = new Family();
				f.setAddress(rs.getString("Address"));
				f.setFamilyId(rs.getInt("FamilyId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return f;
	}

	@Override
	public Family queryFamily(String add) {
		connection = JDBCUtils.getConnection();
		Family f = null;
		String s = " SELECT * FROM Family where FamilyId = ? ";
		try {
			pst = connection.prepareStatement(s);
			pst.setString(1, add);
			rs = pst.executeQuery();
			if (rs.next()) {
				f = new Family();
				f.setAddress(rs.getString("Address"));
				f.setFamilyId(rs.getInt("FamilyId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return f;
	}

	@Override
	public List<Family> queryFamilyList(int start) {
		List<Family> l = new ArrayList<>();
		connection = JDBCUtils.getConnection();
		Family f = null; 
		String s = " SELECT * FROM Family LIMIT ? , 10 ";
		try {
			pst = connection.prepareStatement(s);
			pst.setInt(1, start);
			rs = pst.executeQuery();
			while (rs.next()) {
				f = new Family();
				f.setAddress(rs.getString("Address"));
				f.setFamilyId(rs.getInt("FamilyId"));
				l.add(f);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return l;
	}

	@Override
	public int queryFamilyNumbers() {
		int ans = 0;
		connection = JDBCUtils.getConnection();
		String s = " SELECT COUNT(*) AS Amount FROM Family ";
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

	@Override
	public boolean deleteFamily(int fid) {
		connection = JDBCUtils.getConnection();
		boolean isDelete = false;
		String s = " DELETE FROM Family WHERE FamilyId = " + fid + " ";
		System.out.println(s);
		if (connection != null)
			try {
				pst = connection.prepareStatement(s);
				isDelete = pst.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return isDelete;
	}

	@Override
	public boolean updateFamily(Family f) {
		connection = JDBCUtils.getConnection();
		boolean isUpate = false;
		String s = " UPDATE Family SET Address = \"" + f.getAddress() + "\" WHERE FamilyId = " + f.getFamilyId() + " ";
		if (connection != null)
			try {
				pst = connection.prepareStatement(s);
				if (pst.executeUpdate() > 0)
					isUpate = true;
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return isUpate;
	}

}
