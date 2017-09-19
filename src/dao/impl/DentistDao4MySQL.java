package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import dao.DentistDao;
import dao.JDBCUtils;
import entity.*;

public class DentistDao4MySQL implements DentistDao {
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rs;

	@Override
	public boolean deleteDentistById(int dentistId) {
		connection = JDBCUtils.getConnection();
		boolean isDelete = false;
		String s = " DELETE FROM Dentist WHERE DentistId = ?  ";
		if (connection != null)
			try {
				pst = connection.prepareStatement(s);
				pst.setInt(1, dentistId);
				isDelete = pst.execute();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return isDelete;
	}

	@Override
	public Dentist getDentistById(int dentistId) {
		connection = JDBCUtils.getConnection();
		Dentist da = null;
		String s = " SELECT * FROM Dentist WHERE DentistId = ? ";
		try {
			pst = connection.prepareStatement(s);
			pst.setInt(1, dentistId);
			rs = pst.executeQuery();
			while (rs.next()) {
				da = new Dentist();
				da.setAddress(rs.getString("Address"));
				da.setAge(rs.getInt("Age"));
				da.setEmail(rs.getString("Email"));
				da.setName(rs.getString("Name"));
				da.setDentistId(rs.getInt("DentistId"));
				da.setSex(rs.getString("Sex"));
				da.setTel(rs.getString("Tel"));
				da.setClinicId(rs.getInt("ClinicId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return da;
	}

	@Override
	public Dentist updateDentistInfo(Dentist p) {
		connection = JDBCUtils.getConnection();
		if (connection != null)
			try {
				String work = " UPDATE Dentist SET Name = ? , Sex = ? , Tel = ? , Age = ? , DinicId = ? , Address = ? "
						+ ", Email = ? WHERE  DentistId = ? ";
				pst = connection.prepareStatement(work);
				pst.setString(1, p.getName());
				pst.setString(2, p.getSex());
				pst.setString(3, p.getTel());
				pst.setInt(4, p.getAge());
				pst.setInt(5, p.getClinicId());
				pst.setString(6, p.getAddress());
				pst.setString(7, p.getEmail());
				pst.setInt(8, p.getDentistId());
				pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return getDentistById(p.getDentistId());
	}

	@Override
	public Dentist createDentistInfo(Dentist p) {
		connection = JDBCUtils.getConnection();
		if (connection != null)
			try {
				String work = " Insert into Dentist(Name , Sex , Tel  , Age  , ClincId  , Address "
						+ ", Email ) Values ( ? , ? , ? , ? , ? , ? , ? ) ";
				pst = connection.prepareStatement(work);
				pst.setString(1, p.getName());
				pst.setString(2, p.getSex());
				pst.setString(3, p.getTel());
				pst.setInt(4, p.getAge());
				pst.setInt(5, p.getClinicId());
				pst.setString(6, p.getAddress());
				pst.setString(7, p.getEmail());
				pst.executeUpdate();
				String work2 = " Select * from Dentist WHERE Name = ? and Sex = ? and Tel = ? "
						+ " and Age = ? and ClinicId = ? and Address = ? "
						+ " and Email = ? ";
				pst = connection.prepareStatement(work2);
				pst.setString(1, p.getName());
				pst.setString(2, p.getSex());
				pst.setString(3, p.getTel());
				pst.setInt(4, p.getAge());
				pst.setInt(5, p.getClinicId());
				pst.setString(6, p.getAddress());
				pst.setString(7, p.getEmail());
				p = null;
				rs = pst.executeQuery();
				if (rs.next()) {
					p = new Dentist();
					p.setDentistId(rs.getInt("DentistId"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return getDentistById(p.getDentistId());
	}

	@Override
	public List<Dentist> getDentist(int start) {
		connection = JDBCUtils.getConnection();
		List<Dentist> p = new ArrayList<Dentist>();
		String s = " SELECT * FROM Dentist LIMIT ? , 10 ";
		try {
			pst = connection.prepareStatement(s);
			pst.setInt(1, start);
			rs = pst.executeQuery();
			while (rs.next()) {
				Dentist da = new Dentist();
				da.setAddress(rs.getString("Address"));
				da.setAge(rs.getInt("Age"));
				da.setEmail(rs.getString("Email"));
				da.setName(rs.getString("Name"));
				da.setDentistId(rs.getInt("DentistId"));
				da.setSex(rs.getString("Sex"));
				da.setTel(rs.getString("Tel"));
				da.setClinicId(rs.getInt("ClinicId"));
				p.add(da);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return p;
	}

	@Override
	public int getDentistNumbers() {
		int ans = 0;
		connection = JDBCUtils.getConnection();
		String s = " SELECT COUNT(*) AS Amount FROM Dentist ";
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
