package inventory_management;

public class ItemsCategoryLimitation {

    private static ItemsCategoryLimitation essentialTotal = null;
    static int essentialCount=3;
    static int luxurySum=4;
    static int miscSum=6;
    public static int getEssentialCount() {
        return essentialCount;
    }
    public static int luxurySum() {
        return luxurySum;
    }
    public static int getMiscSum() {
        return miscSum;
    }
    public static void setEssentialCount(int essentialCount) {
        ItemsCategoryLimitation.essentialCount = essentialCount;
    }
    public static void setLuxurySum(int luxurySum) {
        ItemsCategoryLimitation.luxurySum = luxurySum;
    }
    public static void setMiscSum(int miscSum) {
        ItemsCategoryLimitation.miscSum = miscSum;
    }
}
