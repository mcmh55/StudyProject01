package DTO;

import java.sql.Timestamp;

public class BoardDTO {

	private int seq;
	private String id;
	private String pw;
	private String title;
	private String content;
	private String filename;
	private int readcount;
	private Timestamp writedate;
	private int notice;
	private int del;
	
	public BoardDTO() {}
	
	public BoardDTO(int seq, String id, String pw, String title, String content, String filename, int readcount,
			Timestamp writedate, int notice, int del) {
		
		super();
		this.seq = seq;
		this.id = id;
		this.pw = pw;
		this.title = title;
		this.content = content;
		this.filename = filename;
		this.readcount = readcount;
		this.writedate = writedate;
		this.notice = notice;
		this.del = del;
		
	}
	
	public BoardDTO(String id, String pw, String title, String content, String filename) {
		
		this.id = id;
		this.pw = pw;
		this.title = title;
		this.content = content;
		this.filename = filename;
		
	}

	@Override
	public String toString() {
		return "BoardDTO [seq=" + seq + ", pw=" + pw + ", id=" + id + ", title=" + title + ", content=" + content
				+ ", filename=" + filename + ", readcount=" + readcount + ", writedate=" + writedate + ", notice="
				+ notice + ", del=" + del + "]";
	}

	public int getSeq() {
		return seq;
	}

	public void setSeq(int seq) {
		this.seq = seq;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public int getReadcount() {
		return readcount;
	}

	public void setReadcount(int readcount) {
		this.readcount = readcount;
	}

	public Timestamp getWritedate() {
		return writedate;
	}

	public void setWritedate(Timestamp writedate) {
		this.writedate = writedate;
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