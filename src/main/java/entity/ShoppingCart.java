package entity;

public class ShoppingCart {
	private int uid;
	private int bid;
	private String bname;
	private String image;
	private double price;
	private int countbook;
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getCountbook() {
		return countbook;
	}
	public void setCountbook(int countbook) {
		this.countbook = countbook;
	}
	
}
