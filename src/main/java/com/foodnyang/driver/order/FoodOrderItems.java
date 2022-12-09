package com.foodnyang.driver.order;

public class FoodOrderItems {
    private int foodItemId, qty, subtotalPrice;
    private String name;

    public FoodOrderItems(int foodItemId, String name, int qty, int subtotalPrice) {
        this.foodItemId = foodItemId;
        this.name = name;
        this.qty = qty;
        this.subtotalPrice = subtotalPrice;
    }

    public int getFoodItemId() {
        return foodItemId;
    }

    public int getQty() {
        return qty;
    }

    public int getSubtotalPrice() {
        return subtotalPrice;
    }

    public String getName() {
        return name;
    }
}
