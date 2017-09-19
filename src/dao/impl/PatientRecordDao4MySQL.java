package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.JDBCUtils;
import dao.PatientRecordDao;
import entity.*;

public class PatientRecordDao4MySQL implements PatientRecordDao {
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rs;
	@Override
	public PatientRecord queryPatientRecord(int prid) {
		connection = JDBCUtils.getConnection();
		PatientRecord pr = null;
		String s = " SELECT * FROM PatientRecord WHERE PatientRecord = " + prid + " ";
		try {
			pst = connection.prepareStatement(s);
			rs = pst.executeQuery();
			if (rs.next()) {
				pr = new PatientRecord ();
				pr.setContent(rs.getString("Content"));
				pr.setDentistId(rs.getInt("DentistId"));
				pr.setPatientId(rs.getInt("PatientId"));
				pr.setPatientRecordId(rs.getInt("PatientRecordId"));
				pr.setPaymentId(rs.getInt("PaymentId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return pr;
	}

	@Override
	public PatientRecord updatePatientRecord(int prid, String content) {
		connection = JDBCUtils.getConnection();
		String s = " UPDATE PatientRecord SET Content = \"" + content + "\" WHERE PatientRecordId = " + prid + " ";
		if (connection != null)
			try {
				pst = connection.prepareStatement(s);
				pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return queryPatientRecord(prid);
	}

	@Override
	public PatientRecord createPatientRecord(Appointment ap) {
		connection = JDBCUtils.getConnection();
		PatientRecord pr = null;
		String s = " INSERT INTO PatientRecord(PatientId, DentistId, Content, PaymentId) VALUES ("
				+ ap.getPatientId() + " "
				+ ap.getDentistId() + " "
				+ " \"  \" "
				+ 0 + ") ";
		String s1 = " SELECT * FROM PatientRecord WHERE "
				+ " PatientId = " + ap.getPatientId() + " "
				+ " DentistId = " + ap.getDentistId() + " "
				+ " Content = " + " \"  \" " + " "
				+ " PaymentId = " + 0 + " ";
		try {
			pst = connection.prepareStatement(s);
			pst.executeUpdate();
			pst = connection.prepareStatement(s1);
			if (rs.next()) {
				pr = new PatientRecord ();
				pr.setContent(rs.getString("Content"));
				pr.setDentistId(rs.getInt("DentistId"));
				pr.setPatientId(rs.getInt("PatientId"));
				pr.setPatientRecordId(rs.getInt("PatientRecordId"));
				pr.setPaymentId(rs.getInt("PaymentId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return pr;
	}

	@Override
	public PatientRecord updatePatientRecord(int prid, int payid) {
		connection = JDBCUtils.getConnection();
		String s = " UPDATE PatientRecord SET PaymentId = " + payid + " WHERE PatientRecordId = " + prid + " ";
		if (connection != null)
			try {
				pst = connection.prepareStatement(s);
				pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return queryPatientRecord(prid);
	}

}
