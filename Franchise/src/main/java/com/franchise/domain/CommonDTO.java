package com.franchise.domain;

import java.time.LocalDateTime;

import com.franchise.paging.Criteria;
import com.franchise.paging.PaginationInfo;

public class CommonDTO extends Criteria {

	private PaginationInfo paginationInfo; // 페이징 정보
	private String deleteYn; // 삭제 여부
	private LocalDateTime insertTime; // 등록일
	private LocalDateTime updateTime; // 수정일
	private LocalDateTime deleteTime; // 삭제일
	
	// Getter Setter 생성
	public PaginationInfo getPaginationInfo() {
		return paginationInfo;
	}
	public void setPaginationInfo(PaginationInfo paginationInfo) {
		this.paginationInfo = paginationInfo;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	public LocalDateTime getInsertTime() {
		return insertTime;
	}
	public void setInsertTime(LocalDateTime insertTime) {
		this.insertTime = insertTime;
	}
	public LocalDateTime getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(LocalDateTime updateTime) {
		this.updateTime = updateTime;
	}
	public LocalDateTime getDeleteTime() {
		return deleteTime;
	}
	public void setDeleteTime(LocalDateTime deleteTime) {
		this.deleteTime = deleteTime;
	}
	
	@Override
	public String toString() {
		return "CommonDTO [paginationInfo=" + paginationInfo + ", deleteYn=" + deleteYn + ", insertTime=" + insertTime
				+ ", updateTime=" + updateTime + ", deleteTime=" + deleteTime + "]";
	}
	
}