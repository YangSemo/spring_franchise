package com.franchise.domain;

public class CommentDTO {

	private Long idx;
	
	private Long boardIdx;
	private String content;
	private String writer;
	private String deleteYn;
	
	public Long getIdx() {
		return idx;
	}
	public void setIdx(Long idx) {
		this.idx = idx;
	}
	public Long getBoardIdx() {
		return boardIdx;
	}
	public void setBoardIdx(Long boardIdx) {
		this.boardIdx = boardIdx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getDeleteYn() {
		return deleteYn;
	}
	public void setDeleteYn(String deleteYn) {
		this.deleteYn = deleteYn;
	}
	@Override
	public String toString() {
		return "CommentDTO [idx=" + idx + ", boardIdx=" + boardIdx + ", content=" + content + ", writer=" + writer
				+ ", deleteYn=" + deleteYn + "]";
	}
	
	
	
}
