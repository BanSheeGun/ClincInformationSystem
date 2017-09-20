package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
		String s = " SELECT * FROM PatientRecord WHERE PatientRecordId = " + prid + " ";
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
		String s = " INSERT INTO PatientRecord(PatientId, DentistId, Content ) VALUES ("
				+ ap.getPatientId() + ", "
				+ ap.getDentistId() + ", "
				+ "\"未填写\"" + ") ";
		String s1 = " SELECT * FROM PatientRecord WHERE "
				+ " PatientId = " + ap.getPatientId() + " and "
				+ " DentistId = " + ap.getDentistId() + " and "
				+ " Content = " + "\"未填写\""
				+ " ";
		System.out.println(s);
		System.out.println(s1);
		try {
			pst = connection.prepareStatement(s);
			pst.executeUpdate();
			pst = connection.prepareStatement(s1);
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

	@Override
	public PatientRecordPageModel queryPatientRecord(int start, String more) {
		connection = JDBCUtils.getConnection();
		PatientRecordPageModel pgpm = new  PatientRecordPageModel();
		pgpm.setPrn(0);
		pgpm.setPrtn(0);
		List<PatientRecord> lpr = new ArrayList<PatientRecord>();
		String s = " SELECT count(*) as am FROM PatientRecord WHERE " + more + " ";
		String s1 = " SELECT * FROM PatientRecord WHERE " + more + " LIMIT ? , 10  ";
		int tn = 0;
		try {
			pst = connection.prepareStatement(s);
			rs = pst.executeQuery();
			if (rs.next()){
				tn = rs.getInt("am");
			}
			pgpm.setPrtn(tn);
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		try {
			int n = start;
			tn = (tn + 9) / 10;
			if (n > tn)
				n = tn; 
			if (n < 1)
				n = 1;
			pgpm.setPrn(n);
			pgpm.setPrtn(tn);
			pst = connection.prepareStatement(s1);
			pst.setInt(1, n-1);
			rs = pst.executeQuery();
			while (rs.next()) {
				PatientRecord pr = new PatientRecord();
				pr.setContent(rs.getString("Content"));
				pr.setDentistId(rs.getInt("DentistId"));
				pr.setPatientId(rs.getInt("PatientId"));
				pr.setPatientRecordId(rs.getInt("patientRecordId"));
				pr.setPaymentId(rs.getInt("paymentId"));
				lpr.add(pr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		pgpm.setLpr(lpr);
		return pgpm;
	}

}
