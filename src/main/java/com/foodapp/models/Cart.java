package com.foodapp.models;

import java.util.HashMap;

public class Cart {
	private HashMap<Integer, CartItem> items;
	
	public Cart() {
		this.items = new HashMap<>();
	}
	
	public HashMap<Integer, CartItem> getItems() {
		return items;
	}

	public void setItems(HashMap<Integer, CartItem> items) {
		this.items = items;
	}

    public double getGrandTotal() {
        double total = 0;
        for (CartItem item : items.values()) {
            total += item.getTotalprice();
        }
        return total;
    }
//	public void addItemToCart(CartItem item) {
//		int itemId = item.getItemId();
//		
//		if(items.containsKey(itemId)) {
//			CartItem existingItem = items.get(itemId);
//			existingItem.setQuantity(existingItem.getQuantity() + item.getQuantity());
//		}
//	}
//	
//	public void updateItem(int itemId, int quantity) {
//		if(items.containsKey(itemId)) {
//			if(quantity<=0) {
//				items.remove(itemId);
//			}
//			else {
//				CartItem existingItem = items.get(itemId);
//				existingItem.setQuantity(quantity);
//			}
//		}
//	}
//	
//	public void removeItem(CartItem item) {
//		int itemId = item.getItemId();
//		
//		items.remove(itemId);
//	}
}