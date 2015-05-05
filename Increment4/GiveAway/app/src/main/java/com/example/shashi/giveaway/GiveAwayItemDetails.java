package com.example.shashi.giveaway;

public class GiveAwayItemDetails {
	
	public String getItemid(){return itemid;}
    public void setItemid(String itemid){this.itemid=itemid;}

    public String getName() {
		return itemname;
	}
	public void setName(String itemname) {
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
    public void setSubscribedby(String subscribedby){this.subscribedby=subscribedby;}
    public String getSubscribedby(){return subscribedby;}
    public void setapprovedby(String approvedby){this.approvedby=approvedby;}
    public String getApprovedby(){return approvedby;}
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
    private String itemid;
    private String yearsused;
    private String approvedby;
	private int imageNumber;
    public String subscribedby;

	
}
