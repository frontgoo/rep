package org.frontgoo.scrap.ali.dto;

import java.util.List;

public class SkuProp {
	
	private String prop;
	private List<SkuPropValue> value;
	
	/**
	 * @return the prop
	 */
	public String getProp() {
		return prop;
	}
	/**
	 * @param prop the prop to set
	 */
	public void setProp(String prop) {
		this.prop = prop;
	}
	/**
	 * @return the value
	 */
	public List<SkuPropValue> getValue() {
		return value;
	}
	/**
	 * @param value the value to set
	 */
	public void setValue(List<SkuPropValue> value) {
		this.value = value;
	}
}
