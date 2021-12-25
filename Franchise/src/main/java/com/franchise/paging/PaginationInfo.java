package com.franchise.paging;

public class PaginationInfo {

	   private Criteria criteria;
	   private int totalRecordCount; // 전체 데이터 개수
	   private int totalPageCount; // 전체 페이지 개수
	   private int firstPage;
	   private int lastPage;
	   private int firstRecordIndex; // getStartPage 메서드를 대체해서 LIMIT 구문의 첫 번째 값에 사용되는 변수
	   private int lastRecordIndex; // 오라클과 같은 limit 구문이 존재하지 않을 때 사용 => 인라인 뷰(from 절 서브 쿼리)를 사용해야할 때
	   private boolean hasPreviousPage; // 이전 페이지가 존재하는 지를 판단하기 위한 변수
	   private boolean hasNextPage; // 다음 페이지가 존재하는 지를 구분하는 용도
	   
	   // 페이징 번호 계산 및 기본값 지정
	   public PaginationInfo(Criteria criteria) {
			if (criteria.getCurrentPageNo() < 1) {
				criteria.setCurrentPageNo(1);
			}
			if (criteria.getRecordsPerPage() < 1 || criteria.getRecordsPerPage() > 100) {
				criteria.setRecordsPerPage(10);
			}
			if (criteria.getPageSize() < 5 || criteria.getPageSize() > 20) {
				criteria.setPageSize(10);
			}
			
			this.criteria = criteria;
	   }
	   
	   
	   // 파라미터로 넘어온 전체 데이터 개수를 PaginationInfo 클래스의 전체 데이터 개수에 저장
	   // 전체 데이터 개수가 1 개 이상이면 페이지 번호를 계산하는 calculation 메서드 실행
	   public void setTotalRecordCount(int totalRecordCount) {
	      
	      this.totalRecordCount = totalRecordCount;
	  
	      if (totalRecordCount > 0) {
	         calculation();
	      }
	   }
	   
	   public void calculation() {
	      // 전체 페이지수
	      // 현재 페이지 번호가 전체 페이지 수보다 크면 현재 페이지 번호에 전체 페이지 수 저장
	      // ( 전체 데이터 개수 1) / 페이지당 출력할 데이터 개수 ) + 1 을 통해 전체 페이지 개수 산출
	      totalPageCount = ((totalRecordCount - 1) / criteria.getRecordsPerPage()) + 1;
	      if (criteria.getCurrentPageNo() > totalPageCount) {
	         criteria.setCurrentPageNo(totalPageCount);
	      }
	      
	      // 페이지 리스트의 첫 페이지 번호
	      // ( 현재 페이지 번호 1) / 화면 하단의 페이지 개수 ) * 화면 하단의 페이지 개수 + 1 을 통해 가장 좌측의 페이지 번호 산출
	      firstPage = ((criteria.getCurrentPageNo() -1) / criteria.getPageSize()) * criteria.getPageSize() +1;
	      
	      // 페이지 리스트의 마지막 페이지 번호
	      // 마지막 페이지가 전체 페이지 수보다 크면 마지막 페이지에 전체 페이지 수 저장
	      // (첫 페이지 번호 + 화면 하단의 페이지 개수 ) - 1 을 통해 마지막 페이지 번호 산출
	      lastPage = firstPage + criteria.getPageSize() -1;
	      if (lastPage > totalPageCount) {
	         lastPage = totalPageCount;
	      }
	      
	      // SQL의 조건절에 사용되는 첫 RNUM
	      firstRecordIndex = (criteria.getCurrentPageNo() -1) * criteria.getRecordsPerPage();
	      
	      // SQL의 조건절에 사용되는 마지막 RNUM
	      lastRecordIndex = criteria.getCurrentPageNo() * criteria.getRecordsPerPage();
	      
	      // 이전 페이지 존재 여부
	      hasPreviousPage = firstPage != 1;
	      
	      // 다음 페이지 존재 여부
	      // 마지막 페이지 번호 * 페이지당 출력할 데이터의 개수 가 전체 데이터 개수보다 크거나 같으면 false, 작으면 true
	      hasNextPage = (lastPage * criteria.getRecordsPerPage()) < totalRecordCount;
	   }

	   
	   // Getter Setter 생성자 생성
		public Criteria getCriteria() {
			return criteria;
		}
		
		public void setCriteria(Criteria criteria) {
			this.criteria = criteria;
		}
		
		public int getTotalPageCount() {
			return totalPageCount;
		}
		
		public void setTotalPageCount(int totalPageCount) {
			this.totalPageCount = totalPageCount;
		}
		
		public int getFirstPage() {
			return firstPage;
		}
		
		public void setFirstPage(int firstPage) {
			this.firstPage = firstPage;
		}
		
		public int getLastPage() {
			return lastPage;
		}
		
		public void setLastPage(int lastPage) {
			this.lastPage = lastPage;
		}
		
		public int getFirstRecordIndex() {
			return firstRecordIndex;
		}
		
		public void setFirstRecordIndex(int firstRecordIndex) {
			this.firstRecordIndex = firstRecordIndex;
		}
		
		public int getLastRecordIndex() {
			return lastRecordIndex;
		}
		
		public void setLastRecordIndex(int lastRecordIndex) {
			this.lastRecordIndex = lastRecordIndex;
		}
		
		public boolean isHasPreviousPage() {
			return hasPreviousPage;
		}
		
		public void setHasPreviousPage(boolean hasPreviousPage) {
			this.hasPreviousPage = hasPreviousPage;
		}
		
		public boolean isHasNextPage() {
			return hasNextPage;
		}
		
		public void setHasNextPage(boolean hasNextPage) {
			this.hasNextPage = hasNextPage;
		}
		
		public int getTotalRecordCount() {
			return totalRecordCount;
		}

		@Override
		public String toString() {
			return "PaginationInfo [criteria=" + criteria + ", totalRecordCount=" + totalRecordCount + ", totalPageCount="
					+ totalPageCount + ", firstPage=" + firstPage + ", lastPage=" + lastPage + ", firstRecordIndex="
					+ firstRecordIndex + ", lastRecordIndex=" + lastRecordIndex + ", hasPreviousPage=" + hasPreviousPage
					+ ", hasNextPage=" + hasNextPage + "]";
		}
	   
	}