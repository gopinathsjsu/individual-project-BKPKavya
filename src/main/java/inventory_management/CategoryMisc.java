package inventory_management;

import java.util.HashMap;
import java.util.Set;

public class CategoryMisc implements Cart {
    private  Cart setCartCategory;
    @Override
    public void setCartCategory(Cart nextCart) {
        setCartCategory = nextCart;
    }
    @Override
    public double calculateTotal(HashMap<String, Instances> cart , String item , int quantity, Set<String> wrongQuantities,
                                 Set<String> readEssentials, Set<String> readLuxury, Set<String> readMisc)    {
        Instances items = cart.get(item);
        if(items.Category.equals("Misc")){
            readMisc.add(item);
            if(quantity<=items.getQuantity()){
                ItemsCategoryLimitation.setMiscSum(ItemsCategoryLimitation.getMiscSum()-quantity);
                items.setQuantity(quantity);
                return quantity* items.getPrice();
            }
            else{
                wrongQuantities.add(item);
                return 0;
            }
        }
        return 0;
    }
}
