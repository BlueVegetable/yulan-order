package com.yulan.pojo;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Objects;

public class CommodityOrder {

	private String id;
	private BigDecimal price;
	private String activityId;
	private BigDecimal activityPrice;
	private Item item;
	private String orderItemId;
	private BigDecimal quantity;
	private BigDecimal width;
	private BigDecimal height;
	private String note;
	private String unit;
	private Integer splitShipment;
	private Integer status;
	private String curtainItemName;
	private String curtainPartName;
	private BigDecimal dosage;
	private String manufacturingInstructions;
	private Integer certainHeightWidth;
	private BigDecimal specification;
	private String illustrate;
	private Timestamp saveTime;
	private String orderNo;
	private Integer orderItemNumber;
	private String suggestion;
	private Integer lineNo;
	private String itemId;
	private String deleteFlag;
	private String modifyFlag;
	private String changeFlag;

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public BigDecimal getActivityPrice() {
		return activityPrice;
	}

	public void setActivityPrice(BigDecimal activityPrice) {
		this.activityPrice = activityPrice;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public String getOrderItemId() {
		return orderItemId;
	}

	public void setOrderItemId(String orderItemId) {
		this.orderItemId = orderItemId;
	}

	public BigDecimal getQuantity() {
		return quantity;
	}

	public void setQuantity(BigDecimal quantity) {
		this.quantity = quantity;
	}

	public BigDecimal getWidth() {
		return width;
	}

	public void setWidth(BigDecimal width) {
		this.width = width;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public Integer getSplitShipment() {
		return splitShipment;
	}

	public void setSplitShipment(Integer splitShipment) {
		this.splitShipment = splitShipment;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getCurtainItemName() {
		return curtainItemName;
	}

	public void setCurtainItemName(String curtainItemName) {
		this.curtainItemName = curtainItemName;
	}

	public String getCurtainPartName() {
		return curtainPartName;
	}

	public void setCurtainPartName(String curtainPartName) {
		this.curtainPartName = curtainPartName;
	}

	public BigDecimal getDosage() {
		return dosage;
	}

	public void setDosage(BigDecimal dosage) {
		this.dosage = dosage;
	}

	public String getManufacturingInstructions() {
		return manufacturingInstructions;
	}

	public void setManufacturingInstructions(String manufacturingInstructions) {
		this.manufacturingInstructions = manufacturingInstructions;
	}

	public Integer getCertainHeightWidth() {
		return certainHeightWidth;
	}

	public void setCertainHeightWidth(Integer certainHeightWidth) {
		this.certainHeightWidth = certainHeightWidth;
	}

	public BigDecimal getSpecification() {
		return specification;
	}

	public void setSpecification(BigDecimal specification) {
		this.specification = specification;
	}

	public String getIllustrate() {
		return illustrate;
	}

	public void setIllustrate(String illustrate) {
		this.illustrate = illustrate;
	}

	public Timestamp getSaveTime() {
		return saveTime;
	}

	public void setSaveTime(Timestamp saveTime) {
		this.saveTime = saveTime;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public Integer getOrderItemNumber() {
		return orderItemNumber;
	}

	public void setOrderItemNumber(Integer orderItemNumber) {
		this.orderItemNumber = orderItemNumber;
	}

	public String getSuggestion() {
		return suggestion;
	}

	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}

	public Integer getLineNo() {
		return lineNo;
	}

	public void setLineNo(Integer lineNo) {
		this.lineNo = lineNo;
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	public String getModifyFlag() {
		return modifyFlag;
	}

	public void setModifyFlag(String modifyFlag) {
		this.modifyFlag = modifyFlag;
	}

	public String getChangeFlag() {
		return changeFlag;
	}

	public void setChangeFlag(String changeFlag) {
		this.changeFlag = changeFlag;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof CommodityOrder)) return false;
		CommodityOrder that = (CommodityOrder) o;
		return Objects.equals(getId(), that.getId()) &&
				Objects.equals(getPrice(), that.getPrice()) &&
				Objects.equals(getActivityId(), that.getActivityId()) &&
				Objects.equals(getActivityPrice(), that.getActivityPrice()) &&
				Objects.equals(getItem(), that.getItem()) &&
				Objects.equals(getOrderItemId(), that.getOrderItemId()) &&
				Objects.equals(getQuantity(), that.getQuantity()) &&
				Objects.equals(getWidth(), that.getWidth()) &&
				Objects.equals(getHeight(), that.getHeight()) &&
				Objects.equals(getNote(), that.getNote()) &&
				Objects.equals(getUnit(), that.getUnit()) &&
				Objects.equals(getSplitShipment(), that.getSplitShipment()) &&
				Objects.equals(getStatus(), that.getStatus()) &&
				Objects.equals(getCurtainItemName(), that.getCurtainItemName()) &&
				Objects.equals(getCurtainPartName(), that.getCurtainPartName()) &&
				Objects.equals(getDosage(), that.getDosage()) &&
				Objects.equals(getManufacturingInstructions(), that.getManufacturingInstructions()) &&
				Objects.equals(getCertainHeightWidth(), that.getCertainHeightWidth()) &&
				Objects.equals(getSpecification(), that.getSpecification()) &&
				Objects.equals(getIllustrate(), that.getIllustrate()) &&
				Objects.equals(getSaveTime(), that.getSaveTime()) &&
				Objects.equals(getOrderNo(), that.getOrderNo()) &&
				Objects.equals(getOrderItemNumber(), that.getOrderItemNumber()) &&
				Objects.equals(getSuggestion(), that.getSuggestion());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getPrice(), getActivityId(), getActivityPrice(), getItem(), getOrderItemId(), getQuantity(), getWidth(), getHeight(), getNote(), getUnit(), getSplitShipment(), getStatus(), getCurtainItemName(), getCurtainPartName(), getDosage(), getManufacturingInstructions(), getCertainHeightWidth(), getSpecification(), getIllustrate(), getSaveTime(), getOrderNo(), getOrderItemNumber(), getSuggestion());
	}

	@Override
	public String toString() {
		return "CommodityOrder{" +
				"id='" + id + '\'' +
				", price=" + price +
				", activityId='" + activityId + '\'' +
				", activityPrice=" + activityPrice +
				", item=" + item +
				", orderItemId='" + orderItemId + '\'' +
				", quantity=" + quantity +
				", width=" + width +
				", height=" + height +
				", note='" + note + '\'' +
				", unit='" + unit + '\'' +
				", splitShipment=" + splitShipment +
				", status=" + status +
				", curtainItemName='" + curtainItemName + '\'' +
				", curtainPartName='" + curtainPartName + '\'' +
				", dosage=" + dosage +
				", manufacturingInstructions='" + manufacturingInstructions + '\'' +
				", certainHeightWidth=" + certainHeightWidth +
				", specification=" + specification +
				", illustrate='" + illustrate + '\'' +
				", saveTime=" + saveTime +
				", orderNo='" + orderNo + '\'' +
				", orderItemNumber=" + orderItemNumber +
				", suggestion='" + suggestion + '\'' +
				'}';
	}
}