package dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dao.AppointmentDao;
import dao.JDBCUtils;
import entity.*;

public class AppointmentDao4MySQL implements AppointmentDao {
	public static Date getNowDate() {
	    Date currentTime = new Date();
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
	    String dateString = formatter.format(currentTime);
	    ParsePosition pos = new ParsePosition(8);
	    Date currentTime_2 = formatter.parse(dateString, pos);
	    return currentTime_2;
	}
	private Connection connection;
	private PreparedStatement pst;
	private ResultSet rs;
	@Override
	public Appointment queryAppointment(int apid) {
		connection = JDBCUtils.getConnection();
		Appointment pr = null;
		String s = " SELECT * FROM Appointment WHERE AppointmentId = " + apid + " ";
		try {
			pst = connection.prepareStatement(s);
			rs = pst.executeQuery();
			if (rs.next()) {
				pr = new Appointment();
				pr.setApporintmentId(rs.getInt("AppointmentId"));
				pr.setClinicId(rs.getInt("ClinicId"));
				pr.setDate(rs.getString("Date"));
				pr.setDentistId(rs.getInt("DentistId"));
				pr.setPatientId(rs.getInt("patientId"));
				pr.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return pr;
	}

	@Override
	public Appointment updateAppointment(int apid, int st) {
		connection = JDBCUtils.getConnection();
		String s = " UPDATE Appointment SET Status = " + st + " WHERE AppointmentId = " + apid + " ";
		if (connection != null)
			try {
				pst = connection.prepareStatement(s);
				pst.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JDBCUtils.close(connection, pst, rs);
			}
		return queryAppointment(apid);
	}

	@Override
	public Appointment createPatientRecord(Appointment ap) {
		connection = JDBCUtils.getConnection();
		Appointment pr = null;
		String time = getNowDate().toString();
		String s = " INSERT INTO Appointment(PatientId, DentistId, Date, ClinicId, Status) VALUES ("
				+ ap.getPatientId() + ", "
				+ ap.getDentistId() + ", "
				+ time + " , "
				+ ap.getClinicId() + " , 0) ";
		String s1 = " SELECT * FROM PatientRecord WHERE "
				+ " PatientId = " + ap.getPatientId() + " and "
				+ " DentistId = " + ap.getDentistId() + " and "
				+ " Date = " + time + " and "
				+ " ClinicId = " + ap.getClinicId() + " and "
				+ " Status = " + 0 + " and ";
		try {
			pst = connection.prepareStatement(s);
			pst.executeUpdate();
			pst = connection.prepareStatement(s1);
			if (rs.next()) {
				pr = new Appointment ();
				pr.setApporintmentId(rs.getInt("AppointmentId"));
				pr.setClinicId(rs.getInt("ClinicId"));
				pr.setDate(rs.getString("Date"));
				pr.setDentistId(rs.getInt("DentistId"));
				pr.setPatientId(rs.getInt("patientId"));
				pr.setStatus(rs.getInt("status"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		return pr;
	}

	@Override
	public AppointmentPageModel queryAppointment(int start, String more) {
		connection = JDBCUtils.getConnection();
		AppointmentPageModel pgpm = new  AppointmentPageModel();
		pgpm.setApn(0);
		pgpm.setAptn(0);
		List<Appointment> lpr = new ArrayList<Appointment>();
		String s = " SELECT count(*) as am FROM Appointment WHERE " + more + " ";
		String s1 = " SELECT * FROM Appointment WHERE " + more + " LIMIT ? , 10  ";
		int tn = 0;
		try {
			pst = connection.prepareStatement(s);
			rs = pst.executeQuery();
			if (rs.next()){
				tn = rs.getInt("am");
			}
			pgpm.setAptn(tn);
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
			pgpm.setApn(n);
			pst = connection.prepareStatement(s1);
			pst.setInt(1, n-1);
			rs = pst.executeQuery();
			while (rs.next()) {
				Appointment pr = new Appointment();
				pr.setApporintmentId(rs.getInt("AppointmentId"));
				pr.setClinicId(rs.getInt("ClinicId"));
				pr.setDate(rs.getString("Date"));
				pr.setDentistId(rs.getInt("DentistId"));
				pr.setPatientId(rs.getInt("patientId"));
				pr.setStatus(rs.getInt("status"));
				lpr.add(pr);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtils.close(connection, pst, rs);
		}
		pgpm.setLap(lpr);
		return pgpm;
	}

}
