package DTO;

import java.sql.Date;

public class BoardCommentDTO {

	private int seq;
	private int group;
	private int depth;
	private int step;
	private int parentSeq;
	private int boardSeq;
	private String id;
	private String content;
	private Date writeDate;
	private int del;
	
	public BoardCommentDTO() {}
	
	public BoardCommentDTO(int seq, int group, int depth, int step, int parentSeq, int boardSeq, String id,
			String content, Date writeDate, int del) {
		super();
		this.seq = seq;
		this.group = group;
		this.depth = depth;
		this.step = step;
		this.parentSeq = parentSeq;
		this.boardSeq = boardSeq;
		this.id = id;
		this.content = content;
		this.writeDate = writeDate;
		this.del = del;
	}


	public BoardCommentDTO(String id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	
	@Override
	public String toString() {
		return "BoardCommentDTO [seq=" + seq + ", group=" + group + ", depth=" + depth + ", step=" + step
				+ ", parentSeq=" + parentSeq + ", boardSeq=" + boardSeq + ", id=" + id + ", content=" + content
				+ ", writeDate=" + writeDate + ", del=" + del + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public int getGroup() {
		return group;
	}

	public void setGroup(int group) {
		this.group = group;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public int getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(int parentSeq) {
		this.parentSeq = parentSeq;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}

	public int getBoardSeq() {
		return boardSeq;
	}

	public void setBoardSeq(int boardSeq) {
		this.boardSeq = boardSeq;
	}
}