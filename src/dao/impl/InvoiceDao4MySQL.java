package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.*;
import dao.InvoiceDao;
import dao.JDBCUtils;
import entity.Invoice;
import entity.Payment;

public class InvoiceDao4MySQL implements InvoiceDao {
	
	public static String getNowDate() {
        Date ss = new Date();  
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy年MM月dd日");  
        String time = format1.format(ss.getTime());
        return time;
	}
	
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rs;
	@Override
	public Invoice queryInvoice(int iid) {
		connection = JDBCUtils.getConnection();
		Invoice i = null;
		String s = " SELECT * FROM Invoice WHERE InvoiceId = " + iid + " ";
		try {
			pst = connection.prepareStatement(s);
			rs = pst.executeQuery();
			if (rs.next()) {
				i = new Invoice();
				i.setInvoiceId(rs.getInt("InvoiceId"));
				i.setNumber(rs.getDouble("Number"));
				i.setPatientId(rs.getInt("PatientId"));
				i.setDate(rs.getString("Date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return i;
	}

	@Override
	public Invoice createInvoice(Payment p) {
		connection = JDBCUtils.getConnection();
		String date = getNowDate();
		Invoice i = null;
		String s = " INSERT INTO Invoice(Date, Number, PatientId) VALUES (\""
				+ date + "\" , "
				+ p.getNumber() + " , "
				+ p.getPatientId() + ") ";
		String s1 = " SELECT * FROM Invoice WHERE "
				+ " Date = \"" + date + "\" and "
				+ " Number = " + p.getNumber() + " and "
				+ " PatientId = " + p.getPatientId() + " ";
		try {
			pst = connection.prepareStatement(s);
			pst.executeUpdate();
			pst = connection.prepareStatement(s1);
			rs = pst.executeQuery();
			if (rs.next()) {
				i = new Invoice();
				i.setInvoiceId(rs.getInt("InvoiceId"));
				i.setNumber(rs.getDouble("Number"));
				i.setPatientId(rs.getInt("PatientId"));
				i.setDate(rs.getString("Date"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return i;
	}

}
