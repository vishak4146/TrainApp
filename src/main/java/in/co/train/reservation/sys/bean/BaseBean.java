package in.co.train.reservation.sys.bean;

import java.sql.Timestamp;

import lombok.Data;

/**
 * BaseBean JavaBean encapsulates Generic attributes
 */

@Data
public abstract class BaseBean implements DropdownListBean {
	
	
	protected long id;
	protected String createdBy;
	protected String modifiedBy;
	protected Timestamp createdDatetime;
	protected Timestamp modifiedDatetime;
	/**
	 * @return the id
	 */
	public long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(long id) {
		this.id = id;
	}
	/**
	 * @return the createdBy
	 */
	public String getCreatedBy() {
		return createdBy;
	}
	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	/**
	 * @return the modifiedBy
	 */
	public String getModifiedBy() {
		return modifiedBy;
	}
	/**
	 * @param modifiedBy the modifiedBy to set
	 */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	/**
	 * @return the createdDatetime
	 */
	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}
	/**
	 * @param createdDatetime the createdDatetime to set
	 */
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	/**
	 * @return the modifiedDatetime
	 */
	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}
	/**
	 * @param modifiedDatetime the modifiedDatetime to set
	 */
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}

	
	
	
}
