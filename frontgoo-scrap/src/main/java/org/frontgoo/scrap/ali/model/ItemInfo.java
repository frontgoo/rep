package org.frontgoo.scrap.ali.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang.RandomStringUtils;

/**
 * 商品信息
 * @author honglei
 *
 */
@Entity
@Table(name = "item_info")
public class ItemInfo {

	@Id
	@Column(name = "ID", length = 32, nullable = false)
	private String id;
	
	@Column
	private String url;
	
	@Column
	private String title;
	
	@Column
	private String imgUrl;
	
	@Transient
	private List<ColorSizeEntity> colorSizes;

	@Column
	private Date createDate;
	
	@Column
	private Date updateDate;
	
	@Column
	private String status;
	
	/**
	 * @return the id
	 */
	public String getId() {
		
		if (id != null)
            return id;

        id = RandomStringUtils.randomAlphanumeric(32);
        return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * @return the imgUrl
	 */
	public String getImgUrl() {
		return imgUrl;
	}
	/**
	 * @param imgUrl the imgUrl to set
	 */
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	/**
	 * @return the colorSizes
	 */
	public List<ColorSizeEntity> getColorSizes() {
		return colorSizes;
	}
	/**
	 * @param colorSizes the colorSizes to set
	 */
	public void setColorSizes(List<ColorSizeEntity> colorSizes) {
		this.colorSizes = colorSizes;
	}
	/**
	 * @return the createDate
	 */
	public Date getCreateDate() {
		return createDate;
	}
	/**
	 * @param createDate the createDate to set
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * @return the updateDate
	 */
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate the updateDate to set
	 */
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
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
	
}
