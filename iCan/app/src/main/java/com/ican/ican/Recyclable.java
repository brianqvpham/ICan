package com.ican.ican;

public enum Recyclable {
    // TODO make these resource strings?
    ALUMINUM_CAN("Aluminum Cans"),
    GLASS_BOTTLE("Glass Bottles"),
    PLASTIC_BOTTLE("Plastic Bottles"),
    SHEET_OF_PAPER("Sheets of Paper"),
    PLASTIC_GROCERY_BAG("Plastic Grocery Bags");

    public String name;
    public int daily;
    public int alltime;

    Recyclable(String name) {
        this.name = name;
        daily = 0;
        alltime = 0;
    }
}
