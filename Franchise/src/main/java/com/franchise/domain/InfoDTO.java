package com.franchise.domain;

public class InfoDTO extends CommonDTO {

	private Long id; // 번호
	private String upjong; //업종
	private String brandName;//브랜드명
	private String businessName;//상호명
	private String openDate;//개시일
	private String openPeriod;//현재까지 개시 기간
	private String shopNum;//가게 개수
	private String employeeNum;//종업원 수
	private String newShopNum;//신규개점
	private String endShopNum;//계약종료
	private String cancelShopNum;//계약해지
	private String changeShopNum; // 명의변경
	private String avgSales; // 가맹점 평균매출액
	private String areaAvgSales; // 가맹점 면적당 평균매출액
	private String initiationFee; // 가입비
	private String educationFee; // 교육비
	private String deposit; // 보증금
	private String etcFee; // 기타비용(인테리어 등)
	private String feeSum; // 합계
	private String areaFee; // 면적당 비용
	private String standardArea; // 기준면적
	
	public Long getId() {
		return id;
	}
	public void setIdx(Long id) {
		this.id = id;
	}
	public String getUpjong() {
		return upjong;
	}
	public void setUpjong(String upjong) {
		this.upjong = upjong;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getBusinessName() {
		return businessName;
	}
	public void setBusinessName(String businessName) {
		this.businessName = businessName;
	}
	public String getOpenDate() {
		return openDate;
	}
	public void setOpenDate(String openDate) {
		this.openDate = openDate;
	}
	public String getShopNum() {
		return shopNum;
	}
	public void setShopNum(String shopNum) {
		this.shopNum = shopNum;
	}
	public String getEmployeeNum() {
		return employeeNum;
	}
	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}
	public String getNewShopNum() {
		return newShopNum;
	}
	public void setNewShopNum(String newShopNum) {
		this.newShopNum = newShopNum;
	}
	public String getEndShopNum() {
		return endShopNum;
	}
	public void setEndShopNum(String endShopNum) {
		this.endShopNum = endShopNum;
	}
	public String getCancelShopNum() {
		return cancelShopNum;
	}
	public void setCancelShopNum(String cancelShopNum) {
		this.cancelShopNum = cancelShopNum;
	}
	public String getChangeShopNum() {
		return changeShopNum;
	}
	public void setChangeShopNum(String changeShopNum) {
		this.changeShopNum = changeShopNum;
	}
	public String getAvgSales() {
		return avgSales;
	}
	public void setAvgSales(String avgSales) {
		this.avgSales = avgSales;
	}
	public String getAreaAvgSales() {
		return areaAvgSales;
	}
	public void setAreaAvgSales(String areaAvgSales) {
		this.areaAvgSales = areaAvgSales;
	}
	public String getInitiationFee() {
		return initiationFee;
	}
	public void setInitiationFee(String initiationFee) {
		this.initiationFee = initiationFee;
	}
	public String getEducationFee() {
		return educationFee;
	}
	public void setEducationFee(String educationFee) {
		this.educationFee = educationFee;
	}
	public String getDeposit() {
		return deposit;
	}
	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}
	public String getEtcFee() {
		return etcFee;
	}
	public void setEtcFee(String etcFee) {
		this.etcFee = etcFee;
	}
	public String getFeeSum() {
		return feeSum;
	}
	public void setFeeSum(String feeSum) {
		this.feeSum = feeSum;
	}
	public String getAreaFee() {
		return areaFee;
	}
	public void setAreaFee(String areaFee) {
		this.areaFee = areaFee;
	}
	public String getStandardArea() {
		return standardArea;
	}
	public void setStandardArea(String standardArea) {
		this.standardArea = standardArea;
	}
	public String getOpenPeriod() {
		return openPeriod;
	}
	public void setOpenPeriod(String openPeriod) {
		this.openPeriod = openPeriod;
	}
	@Override
	public String toString() {
		return "InfoDTO [idx=" + id + ", upjong=" + upjong + ", brandName=" + brandName + ", businessName="
				+ businessName + ", openDate=" + openDate + ", openPeriod=" + openPeriod + ", shopNum=" + shopNum
				+ ", employeeNum=" + employeeNum + ", newShopNum=" + newShopNum + ", endShopNum=" + endShopNum
				+ ", cancelShopNum=" + cancelShopNum + ", changeShopNum=" + changeShopNum + ", avgSales=" + avgSales
				+ ", areaAvgSales=" + areaAvgSales + ", initiationFee=" + initiationFee + ", educationFee="
				+ educationFee + ", deposit=" + deposit + ", etcFee=" + etcFee + ", feeSum=" + feeSum + ", areaFee="
				+ areaFee + ", standardArea=" + standardArea + "]";
	}
	
}
