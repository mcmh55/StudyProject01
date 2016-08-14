package DTO;

import java.sql.Date;

public class BoardDTO {

	private int seq;
	private int group;
	private int depth;
	private int step;
	private int parentSeq;
	private int parentDel;
	private String id;
	private int pw;
	private String title;
	private String content;
	private String fileName;
	private int readCount;
	private Date writeDate;
	private int notice;
	private int del;
	
	public BoardDTO() {}
	
	public BoardDTO(int seq, int group, int depth, int step, int parentSeq, int parentDel, String id, int pw,
			String title, String content, String fileName, int readCount, Date writeDate, int notice, int del) {
		super();
		this.seq = seq;
		this.group = group;
		this.depth = depth;
		this.step = step;
		this.parentSeq = parentSeq;
		this.parentDel = parentDel;
		this.id = id;
		this.pw = pw;
		this.title = title;
		this.content = content;
		this.fileName = fileName;
		this.readCount = readCount;
		this.writeDate = writeDate;
		this.notice = notice;
		this.del = del;
	}



	public BoardDTO(String id, int pw, String title, String content, String fileName) {
		this.id = id;
		this.pw = pw;
		this.title = title;
		this.content = content;
		this.fileName = fileName;
	}
	
	public BoardDTO(int seq, String id, int pw, String title, String content, String fileName) {
		this.seq = seq;
		this.id = id;
		this.pw = pw;
		this.title = title;
		this.content = content;
		this.fileName = fileName;
	}
	
	public BoardDTO(int seq, int pw, String title, String content, String fileName) {
		this.seq = seq;
		this.pw = pw;
		this.title = title;
		this.content = content;
		this.fileName = fileName;
	}


	@Override
	public String toString() {
		return "BoardDTO [seq=" + seq + ", group=" + group + ", depth=" + depth + ", step=" + step + ", parentSeq="
				+ parentSeq + ", parentDel=" + parentDel + ", id=" + id + ", pw=" + pw + ", title=" + title
				+ ", content=" + content + ", fileName=" + fileName + ", readCount=" + readCount + ", writeDate="
				+ writeDate + ", notice=" + notice + ", del=" + del + "]";
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

	public int getParentDel() {
		return parentDel;
	}

	public void setParentDel(int parentDel) {
		this.parentDel = parentDel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPw() {
		return pw;
	}

	public void setPw(int pw) {
		this.pw = pw;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getReadCount() {
		return readCount;
	}

	public void setReadCount(int readCount) {
		this.readCount = readCount;
	}

	public Date getWriteDate() {
		return writeDate;
	}

	public void setWriteDate(Date writeDate) {
		this.writeDate = writeDate;
	}

	public int getNotice() {
		return notice;
	}

	public void setNotice(int notice) {
		this.notice = notice;
	}

	public int getDel() {
		return del;
	}

	public void setDel(int del) {
		this.del = del;
	}
}