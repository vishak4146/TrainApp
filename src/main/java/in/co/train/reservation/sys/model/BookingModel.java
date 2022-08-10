package in.co.train.reservation.sys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import in.co.train.reservation.sys.bean.BookingBean;
import in.co.train.reservation.sys.exception.ApplicationException;
import in.co.train.reservation.sys.exception.DatabaseException;
import in.co.train.reservation.sys.exception.DuplicateRecordException;
import in.co.train.reservation.sys.util.DataUtility;
import in.co.train.reservation.sys.util.JDBCDataSource;


/**
 * JDBC Implementation of BookingModel
 */

public class BookingModel {
	private static Logger log = Logger.getLogger(BookingModel.class.getName());
	

	public Integer nextPK() throws DatabaseException {
		log.info("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM Booking");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model nextPK End");
		return pk + 1;
	}
	
	public Integer countMember(long trainId) throws DatabaseException {
		log.info("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT Sum(totalMember) FROM Booking where trainId=?");
			pstmt.setLong(1,trainId);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model nextPK End");
		return pk;
	}
	
	public Long nextInvoiceNo() throws DatabaseException {
		log.info("Model nextPK Started");
		Connection conn = null;
		long pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(InvoiceNo) FROM Booking");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getLong(1);
			}
			rs.close();
		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model nextPK End");
		if(pk==0)
		return 1000001L;
		else
			return pk+1;
	}

	
	public long add(BookingBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); 
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Booking VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2,nextInvoiceNo());
			pstmt.setString(3, bean.getName());
			pstmt.setLong(4, bean.getTrainId());
			pstmt.setString(5, bean.getTrainName());
			pstmt.setDate(6, new java.sql.Date(bean.getBookingDate().getTime()));
			pstmt.setDate(7, new java.sql.Date(bean.getTravelDate().getTime()));
			pstmt.setInt(8, bean.getAdult());
			pstmt.setInt(9, bean.getChild());
			pstmt.setInt(10, bean.getTotalMember());
			pstmt.setString(11, bean.getTicketPrice());
			pstmt.setString(12, bean.getTotalAmount());
			pstmt.setString(13, bean.getStatus());
			pstmt.setString(14, bean.getCreatedBy());
			pstmt.setString(15, bean.getModifiedBy());
			pstmt.setTimestamp(16, DataUtility.getCurrentTimestamp());
			pstmt.setTimestamp(17, DataUtility.getCurrentTimestamp());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Booking");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

	
	public void delete(BookingBean bean) throws ApplicationException {
		
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Booking WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
		
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete Booking");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}



	public BookingBean findByName(String name) throws ApplicationException {
		log.info("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Booking WHERE NAME=?");
		BookingBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookingBean();
				bean.setId(rs.getLong(1));
				bean.setInvoiceNo(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setTrainId(rs.getLong(4));
				bean.setTrainName(rs.getString(5));
				bean.setBookingDate(rs.getDate(6));
				bean.setTravelDate(rs.getDate(7));
				bean.setAdult(rs.getInt(8));
				bean.setChild(rs.getInt(9));
				bean.setTotalMember(rs.getInt(10));
				bean.setTicketPrice(rs.getString(11));
				bean.setTotalAmount(rs.getString(12));
				bean.setStatus(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Booking by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model findByLogin End");
		return bean;
	}


	public BookingBean findByPK(long pk) throws ApplicationException {
		log.info("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Booking WHERE ID=?");
		BookingBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookingBean();
				bean.setId(rs.getLong(1));
				bean.setInvoiceNo(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setTrainId(rs.getLong(4));
				bean.setTrainName(rs.getString(5));
				bean.setBookingDate(rs.getDate(6));
				bean.setTravelDate(rs.getDate(7));
				bean.setAdult(rs.getInt(8));
				bean.setChild(rs.getInt(9));
				bean.setTotalMember(rs.getInt(10));
				bean.setTicketPrice(rs.getString(11));
				bean.setTotalAmount(rs.getString(12));
				bean.setStatus(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Booking by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model findByPK End");
		return bean;
	}


	public List<BookingBean> search(BookingBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}


	public List<BookingBean> search(BookingBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.info("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Booking WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			
			if (bean.getInvoiceNo()> 0) {
				sql.append(" AND InvoiceNo =" + bean.getInvoiceNo());
			}
			
			if (bean.getTrainName() != null && bean.getTrainName().length() > 0) {
				sql.append(" AND TrainName like '" + bean.getTrainName() + "%'");
			}
						
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
		}

		ArrayList<BookingBean> list = new ArrayList<BookingBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new BookingBean();
				bean.setId(rs.getLong(1));
				bean.setInvoiceNo(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setTrainId(rs.getLong(4));
				bean.setTrainName(rs.getString(5));
				bean.setBookingDate(rs.getDate(6));
				bean.setTravelDate(rs.getDate(7));
				bean.setAdult(rs.getInt(8));
				bean.setChild(rs.getInt(9));
				bean.setTotalMember(rs.getInt(10));
				bean.setTicketPrice(rs.getString(11));
				bean.setTotalAmount(rs.getString(12));
				bean.setStatus(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in search Booking");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model search End");
		return list;
	}

	

	public List<BookingBean> list() throws ApplicationException {
		return list(0, 0);
	}



	public List<BookingBean> list(int pageNo, int pageSize) throws ApplicationException {
		log.info("Model list Started");
		ArrayList<BookingBean> list = new ArrayList<BookingBean>();
		StringBuffer sql = new StringBuffer("select * from Booking");
	
		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				BookingBean bean = new BookingBean();
				bean.setId(rs.getLong(1));
				bean.setInvoiceNo(rs.getLong(2));
				bean.setName(rs.getString(3));
				bean.setTrainId(rs.getLong(4));
				bean.setTrainName(rs.getString(5));
				bean.setBookingDate(rs.getDate(6));
				bean.setTravelDate(rs.getDate(7));
				bean.setAdult(rs.getInt(8));
				bean.setChild(rs.getInt(9));
				bean.setTotalMember(rs.getInt(10));
				bean.setTicketPrice(rs.getString(11));
				bean.setTotalAmount(rs.getString(12));
				bean.setStatus(rs.getString(13));
				bean.setCreatedBy(rs.getString(14));
				bean.setModifiedBy(rs.getString(15));
				bean.setCreatedDatetime(rs.getTimestamp(16));
				bean.setModifiedDatetime(rs.getTimestamp(17));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting list of Bookings");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.info("Model list End");
		return list;

	}

	



	
}
