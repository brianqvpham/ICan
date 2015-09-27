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
    public static final LAPTOP_RATIO = 20;
    public static final HAIR_AND_AIR_RATIO = 1;

    Recyclable(String name, double nrg) {
        this.name = name;
        this.nrg = nrg;
        daily = 0;
        alltime = 0;
    }

    public int getNrg() {
        return nrg;
    }

    public void getFacts() {
        int laptop = nrg * LAPTOP_RATIO;
        int hairAndAir = nrg * HAIR_AND_AIR_RATIO;
        String fact = ("By recycling, you could charge your laptop for" + laptop + "hours \nOr" +
                    "use your hairdryer for " + hairAndAir + "hours \nOr use your air conditioner for "
                    + hairAndAir + " hours.");
    }
}
