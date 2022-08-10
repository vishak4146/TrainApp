package in.co.train.reservation.sys.exception;

/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 */

public class DatabaseException extends Exception {
	public DatabaseException(String msg) {
		super(msg);
	}
}
