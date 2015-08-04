package org.frontgoo.scrap.ali.dto;

import java.util.List;
import java.util.Map;

public class SkuDto {

	private String price;
	private String retailPrice;
	private String canBookCount;
	private String saleCount;
	private List<List<Double>> priceRange;
	private List<List<Double>> priceRangeOriginal;
	private List<SkuProp> skuProps;
	private Map<String, ColorSizeDto> skuMap;
	private String end;
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the retailPrice
	 */
	public String getRetailPrice() {
		return retailPrice;
	}
	/**
	 * @param retailPrice the retailPrice to set
	 */
	public void setRetailPrice(String retailPrice) {
		this.retailPrice = retailPrice;
	}
	/**
	 * @return the canBookCount
	 */
	public String getCanBookCount() {
		return canBookCount;
	}
	/**
	 * @param canBookCount the canBookCount to set
	 */
	public void setCanBookCount(String canBookCount) {
		this.canBookCount = canBookCount;
	}
	/**
	 * @return the saleCount
	 */
	public String getSaleCount() {
		return saleCount;
	}
	/**
	 * @param saleCount the saleCount to set
	 */
	public void setSaleCount(String saleCount) {
		this.saleCount = saleCount;
	}
	/**
	 * @return the priceRange
	 */
	public List<List<Double>> getPriceRange() {
		return priceRange;
	}
	/**
	 * @param priceRange the priceRange to set
	 */
	public void setPriceRange(List<List<Double>> priceRange) {
		this.priceRange = priceRange;
	}
	/**
	 * @return the priceRangeOriginal
	 */
	public List<List<Double>> getPriceRangeOriginal() {
		return priceRangeOriginal;
	}
	/**
	 * @param priceRangeOriginal the priceRangeOriginal to set
	 */
	public void setPriceRangeOriginal(List<List<Double>> priceRangeOriginal) {
		this.priceRangeOriginal = priceRangeOriginal;
	}
	/**
	 * @return the skuProps
	 */
	public List<SkuProp> getSkuProps() {
		return skuProps;
	}
	/**
	 * @param skuProps the skuProps to set
	 */
	public void setSkuProps(List<SkuProp> skuProps) {
		this.skuProps = skuProps;
	}
	/**
	 * @return the skuMap
	 */
	public Map<String, ColorSizeDto> getSkuMap() {
		return skuMap;
	}
	/**
	 * @param skuMap the skuMap to set
	 */
	public void setSkuMap(Map<String, ColorSizeDto> skuMap) {
		this.skuMap = skuMap;
	}
	/**
	 * @return the end
	 */
	public String getEnd() {
		return end;
	}
	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
	}
	
}
