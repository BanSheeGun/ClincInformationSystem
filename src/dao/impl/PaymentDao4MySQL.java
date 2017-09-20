package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import dao.JDBCUtils;
import dao.PaymentDao;
import entity.*;

public class PaymentDao4MySQL implements PaymentDao {
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rs;
	@Override
	public Payment queryPayment(int pid) {
		connection = JDBCUtils.getConnection();
		Payment p = null;
		String s = " SELECT * FROM Payment WHERE PaymentId = " + pid + " ";
		try {
			pst = connection.prepareStatement(s);
			rs = pst.executeQuery();
			if (rs.next()) {
				p = new Payment();
				p.setInvoiceId(rs.getInt("InvoiceId"));
				p.setNumber(rs.getDouble("Number"));
				p.setPatientId(rs.getInt("PatientId"));
				p.setPaymentId(rs.getInt("PaymentId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return p;
	}

	@Override
	public Payment createPayment(Payment pay) {
		connection = JDBCUtils.getConnection();
		Payment p = null;
		String s = " INSERT INTO Payment(Number, PatientId) VALUES ("
				+ pay.getNumber() + ", "
				+ pay.getPatientId() + ") ";
		String s1 = " SELECT * FROM Payment WHERE "
				+ " Number = " + pay.getNumber() + " and "
				+ " PatientId = " + pay.getPatientId() + " ";
		try {
			pst = connection.prepareStatement(s);
			pst.executeUpdate();
			pst = connection.prepareStatement(s1);
			rs = pst.executeQuery();
			if (rs.next()) {
				p = new Payment();
				p.setInvoiceId(rs.getInt("InvoiceId"));
				p.setNumber(rs.getDouble("Number"));
				p.setPatientId(rs.getInt("PatientId"));
				p.setPaymentId(rs.getInt("PaymentId"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return p;
	}

	@Override
	public Payment updatePayment(int pid, int iid) {
		connection = JDBCUtils.getConnection();
		String s = " UPDATE Payment SET InvoiceId = " + iid + " WHERE PaymentId = " + pid + " ";
		if (connection != null)
			try {
				pst = connection.prepareStatement(s);
				pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return queryPayment(pid);
	}

}
