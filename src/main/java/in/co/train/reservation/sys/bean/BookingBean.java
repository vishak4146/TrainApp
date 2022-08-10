package in.co.train.reservation.sys.bean;

import lombok.Setter;

import java.util.Date;

import lombok.Getter;

@Getter
@Setter
public class BookingBean extends BaseBean{
	
	private String name;
	private long trainId;
	private String trainName;
	private Date bookingDate;
	private Date travelDate;
	private int adult;
	private int child;
	private int totalMember;
	private String ticketPrice;
	private String totalAmount;
	private String status;
	private long invoiceNo;
	
	private String from;
	private String to;
	
	
	
	

	@Override
	public String getKey() {
		return null;
	}

	@Override
	public String getValue() {
		return null;
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
	 * @return the trainId
	 */
	public long getTrainId() {
		return trainId;
	}

	/**
	 * @param trainId the trainId to set
	 */
	public void setTrainId(long trainId) {
		this.trainId = trainId;
	}

	/**
	 * @return the trainName
	 */
	public String getTrainName() {
		return trainName;
	}

	/**
	 * @param trainName the trainName to set
	 */
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	/**
	 * @return the bookingDate
	 */
	public Date getBookingDate() {
		return bookingDate;
	}

	/**
	 * @param bookingDate the bookingDate to set
	 */
	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	/**
	 * @return the travelDate
	 */
	public Date getTravelDate() {
		return travelDate;
	}

	/**
	 * @param travelDate the travelDate to set
	 */
	public void setTravelDate(Date travelDate) {
		this.travelDate = travelDate;
	}

	/**
	 * @return the adult
	 */
	public int getAdult() {
		return adult;
	}

	/**
	 * @param adult the adult to set
	 */
	public void setAdult(int adult) {
		this.adult = adult;
	}

	/**
	 * @return the child
	 */
	public int getChild() {
		return child;
	}

	/**
	 * @param child the child to set
	 */
	public void setChild(int child) {
		this.child = child;
	}

	/**
	 * @return the totalMember
	 */
	public int getTotalMember() {
		return totalMember;
	}

	/**
	 * @param totalMember the totalMember to set
	 */
	public void setTotalMember(int totalMember) {
		this.totalMember = totalMember;
	}

	/**
	 * @return the ticketPrice
	 */
	public String getTicketPrice() {
		return ticketPrice;
	}

	/**
	 * @param ticketPrice the ticketPrice to set
	 */
	public void setTicketPrice(String ticketPrice) {
		this.ticketPrice = ticketPrice;
	}

	/**
	 * @return the totalAmount
	 */
	public String getTotalAmount() {
		return totalAmount;
	}

	/**
	 * @param totalAmount the totalAmount to set
	 */
	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the invoiceNo
	 */
	public long getInvoiceNo() {
		return invoiceNo;
	}

	/**
	 * @param invoiceNo the invoiceNo to set
	 */
	public void setInvoiceNo(long invoiceNo) {
		this.invoiceNo = invoiceNo;
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

	
}
