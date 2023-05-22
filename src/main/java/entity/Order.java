package entity;

public class Order {
	private int oid;
	private double Totalprice;
	private String time;
	private String state;
	private String operate;
	public int getOid() {
		return oid;
	}
	public void setOid(int oid) {
		this.oid = oid;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public double getTotalprice() {
		return Totalprice;
	}
	public void setTotalprice(double totalprice) {
		Totalprice = totalprice;
	}
	public String getOperate() {
		return operate;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	
}
