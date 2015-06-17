package vo;

public class ReaderInfo {
	private int readerid;
	private int rtypeid;
	private String readername;
	private String  idcard;
	private int borrownumber;
	private int number;
	private String rtypename;
	
	public int getReaderid() {
		return readerid;
	}
	public void setReaderid(int readerid) {
		this.readerid = readerid;
	}
	
	public int getRtypeid() {
		return rtypeid;
	}
	public void setRtypeid(int rtypeid) {
		this.rtypeid = rtypeid;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getRtypename() {
		return rtypename;
	}
	public void setRtypename(String rtypename) {
		this.rtypename = rtypename;
	}
	public String getReadername() {
		return readername;
	}
	public void setReadername(String readername) {
		this.readername = readername;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public int getBorrownumber() {
		return borrownumber;
	}
	public void setBorrownumber(int borrownumber) {
		this.borrownumber = borrownumber;
	}
	
	
}
