package com.ican.ican;

public enum Recyclable {
    // TODO make these resource strings?
    ALUMINUM_CAN("Aluminum Cans", .26),
    GLASS_BOTTLE("Glass Bottles", .1),
    PLASTIC_BOTTLE("Plastic Bottles", .13),
    SHEET_OF_PAPER("Sheets of Paper", .05),
    PLASTIC_GROCERY_BAG("Plastic Grocery Bags", .02);

    public String name;
    public int daily;
    public int alltime;
    public double nrg;

    Recyclable(String name, double nrg) {
        this.name = name;
        this.nrg = nrg;
        daily = 0;
        alltime = 0;
    }
}
