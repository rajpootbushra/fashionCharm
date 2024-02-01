// CartItem.java
package com.example.fashioncharm;

public class CartItem {
    private String itemName;
    private String itemType;
    private String itemPrice;
    public CartItem() {
        // Default constructor required for Firebase
    }

    public CartItem(String itemName, String itemType,String itemPrice) {
        this.itemName = itemName;
        this.itemType = itemType;
        this.itemPrice=itemPrice;
    }

    public String getItemName() {
        return itemName;
    }

    public String getItemType() {
        return itemType;
    }
    public String getItemPrice(){return itemPrice;}
}
