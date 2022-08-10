package in.co.train.reservation.sys.bean;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrainBean extends BaseBean {

	private String name;
	private String trainNo;
	private String rate;
	private Date date;
	private String from;
	private String to;
	private String maxSeat;
	
	
	@Override
	public String getKey() {
		return String.valueOf(id);
	}

	@Override
	public String getValue() {
		return name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the trainNo
	 */
	public String getTrainNo() {
		return trainNo;
	}

	/**
	 * @param trainNo the trainNo to set
	 */
	public void setTrainNo(String trainNo) {
		this.trainNo = trainNo;
	}

	/**
	 * @return the rate
	 */
	public String getRate() {
		return rate;
	}

	/**
	 * @param rate the rate to set
	 */
	public void setRate(String rate) {
		this.rate = rate;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the from
	 */
	public String getFrom() {
		return from;
	}

	/**
	 * @param from the from to set
	 */
	public void setFrom(String from) {
		this.from = from;
	}

	/**
	 * @return the to
	 */
	public String getTo() {
		return to;
	}

	/**
	 * @param to the to to set
	 */
	public void setTo(String to) {
		this.to = to;
	}

	/**
	 * @return the maxSeat
	 */
	public String getMaxSeat() {
		return maxSeat;
	}

	/**
	 * @param maxSeat the maxSeat to set
	 */
	public void setMaxSeat(String maxSeat) {
		this.maxSeat = maxSeat;
	}

	
}
