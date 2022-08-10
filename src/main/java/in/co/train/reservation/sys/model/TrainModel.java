package in.co.train.reservation.sys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

import in.co.train.reservation.sys.bean.TrainBean;
import in.co.train.reservation.sys.exception.ApplicationException;
import in.co.train.reservation.sys.exception.DatabaseException;
import in.co.train.reservation.sys.exception.DuplicateRecordException;
import in.co.train.reservation.sys.util.JDBCDataSource;

/**
 * JDBC Implementation of TrainModel
 */

public class TrainModel {
	private static Logger log = Logger.getLogger(TrainModel.class.getName());
	

	public Integer nextPK() throws DatabaseException {
		log.info("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM Train");
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

	
	public long add(TrainBean bean) throws ApplicationException, DuplicateRecordException {
		
		Connection conn = null;
		int pk = 0;

		TrainBean existbean = findByName(bean.getName());

		if (existbean != null) {
			throw new DuplicateRecordException("Name is already exists");
		}

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false); 
			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO Train VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getTrainNo());
			pstmt.setString(4, bean.getRate());
			pstmt.setDate(5, new java.sql.Date(bean.getDate().getDate()));
			pstmt.setString(6, bean.getFrom());
			pstmt.setString(7, bean.getTo());
			pstmt.setString(8, bean.getMaxSeat());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());
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
			throw new ApplicationException("Exception : Exception in add Train");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
		return pk;
	}

	
	public void delete(TrainBean bean) throws ApplicationException {
		
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM Train WHERE ID=?");
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
			throw new ApplicationException("Exception : Exception in delete Train");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		
	}



	public TrainBean findByName(String name) throws ApplicationException {
		log.info("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Train WHERE NAME=?");
		TrainBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, name);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TrainBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setTrainNo(rs.getString(3));
				bean.setRate(rs.getString(4));
				bean.setDate(rs.getDate(5));
				bean.setFrom(rs.getString(6));
				bean.setTo(rs.getString(7));
				bean.setMaxSeat(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Train by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model findByLogin End");
		return bean;
	}


	public TrainBean findByPK(long pk) throws ApplicationException {
		log.info("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Train WHERE ID=?");
		TrainBean bean = null;
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TrainBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setTrainNo(rs.getString(3));
				bean.setRate(rs.getString(4));
				bean.setDate(rs.getDate(5));
				bean.setFrom(rs.getString(6));
				bean.setTo(rs.getString(7));
				bean.setMaxSeat(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in getting Train by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model findByPK End");
		return bean;
	}

	

	public void update(TrainBean bean) throws ApplicationException, DuplicateRecordException {
		log.info("Model update Started");
		Connection conn = null;

		TrainBean beanExist = findByName(bean.getName());
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("Name is already exist");
		}

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE Train SET NAME=?,trainNo=?,rate=?,date=?,fromcity=?,tocity=?,maxSeat=?,"
					+ "CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getTrainNo());
			pstmt.setString(3, bean.getRate());
			pstmt.setDate(4, new java.sql.Date(bean.getDate().getTime()));
			pstmt.setString(5, bean.getFrom());
			pstmt.setString(6, bean.getTo());
			pstmt.setString(7, bean.getMaxSeat());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Train ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model update End");
	}

	

	public List<TrainBean> search(TrainBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}


	public List<TrainBean> search(TrainBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.info("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM Train WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			
			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}
			
			if (bean.getTrainNo() != null && bean.getTrainNo().length() > 0) {
				sql.append(" AND TrainNo like '" + bean.getTrainNo() + "%'");
			}
			
			if (bean.getFrom() != null && bean.getFrom().length() > 0) {
				sql.append(" AND fromcity like '" + bean.getFrom() + "%'");
			}
			
			if (bean.getTo() != null && bean.getTo().length() > 0) {
				sql.append(" AND tocity like '" + bean.getTo() + "%'");
			}
			
			if (bean.getDate() != null && bean.getDate().getTime() > 0) {
				sql.append(" AND date = " + new java.sql.Date(bean.getDate().getTime()));
			}
						
		}

		if (pageSize > 0) {
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
		}

		ArrayList<TrainBean> list = new ArrayList<TrainBean>();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TrainBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setTrainNo(rs.getString(3));
				bean.setRate(rs.getString(4));
				bean.setDate(rs.getDate(5));
				bean.setFrom(rs.getString(6));
				bean.setTo(rs.getString(7));
				bean.setMaxSeat(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ApplicationException("Exception : Exception in search Train");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.info("Model search End");
		return list;
	}

	

	public List<TrainBean> list() throws ApplicationException {
		return list(0, 0);
	}



	public List<TrainBean> list(int pageNo, int pageSize) throws ApplicationException {
		log.info("Model list Started");
		ArrayList<TrainBean> list = new ArrayList<TrainBean>();
		StringBuffer sql = new StringBuffer("select * from Train");
	
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
				TrainBean bean = new TrainBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setTrainNo(rs.getString(3));
				bean.setRate(rs.getString(4));
				bean.setDate(rs.getDate(5));
				bean.setFrom(rs.getString(6));
				bean.setTo(rs.getString(7));
				bean.setMaxSeat(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			throw new ApplicationException("Exception : Exception in getting list of Trains");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.info("Model list End");
		return list;

	}

	



	
}
