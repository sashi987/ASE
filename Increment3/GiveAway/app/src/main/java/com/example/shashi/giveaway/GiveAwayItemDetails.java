package com.example.shashi.giveaway;

public class GiveAwayItemDetails {
	
	public String getName() {
		return itemname;
	}
	public void setName(String name) {
		this.itemname = itemname;
	}
	public String getitemcategory() {
		return itemcategory;
	}
	public void setitemcategory(String itemcategory) {
		this.itemcategory = itemcategory;
	}
	public String getquantity() {
		return quantity;
	}
	public void setquantity(String quantity) {
		this.quantity = quantity;
	}
    public String getCreatedby(){return createdby;}
    public String getYearsused(){return yearsused;}
    public void setCreatedby(String createdby){this.createdby=createdby;}
    public void setYearsused(String yearsused){this.yearsused=yearsused;}
	public int getImageNumber() {
		return imageNumber;
	}
	public void setImageNumber(int imageNumber) {
		this.imageNumber = imageNumber;
	}
	
	private String itemname ;
	private String itemcategory;
	private String quantity;
    private String createdby;
    private String yearsused;
	private int imageNumber;

	
}
