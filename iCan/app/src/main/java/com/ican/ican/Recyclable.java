package com.ican.ican;

public enum Recyclable {
    // TODO make these resource strings?
    ALUMINUM_CAN("Aluminum Cans", .26),
    GLASS_BOTTLE("Glass Bottles", .1),
    PLASTIC_BOTTLE("Plastic Bottles", .13),
    SHEET_OF_PAPER("Sheets of Paper", .05),
    PLASTIC_GROCERY_BAG("Plastic Grocery Bags", .02);

    private static final int LAPTOP_RATIO = 20;
    private static final int HAIR_AND_AIR_RATIO = 1;
    public final String name;
    public final double nrg;
    public int daily;
    public int alltime;

    Recyclable(String name, double nrg) {
        this.name = name;
        this.nrg = nrg;
        daily = 0;
        alltime = 0;
    }

    public String getFacts() {
        int laptop = (int) Math.floor(nrg * LAPTOP_RATIO * 60 * alltime);
        int hairAndAir = (int) Math.floor(nrg * HAIR_AND_AIR_RATIO * 60 * alltime);
        return "By recycling, you have conserved enough energy to charge your laptop for " + laptop + " minutes or " +
                "use your hairdryer or air conditioner for "
                + hairAndAir + " minutes!";
    }
}
