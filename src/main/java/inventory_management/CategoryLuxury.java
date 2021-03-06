package inventory_management;

import java.util.HashMap;
import java.util.Set;

public class CategoryLuxury implements Cart {
    private  Cart setCartCategory;
    @Override
    public void setCartCategory(Cart nextCart) {
        setCartCategory = nextCart;
    }
    @Override
    public double calculateTotal(HashMap<String, Instances> cart , String item , int quantity, Set<String> wrongQuantities,
                                 Set<String> readEssentials, Set<String> readLuxury, Set<String> readMisc)
    {
        Instances itemInstance = cart.get(item);
        if(itemInstance.Category.equals("Luxury")){
            readLuxury.add(item);
            if( quantity<=itemInstance.getQuantity()){
                ItemsCategoryLimitation.setLuxurySum(  ItemsCategoryLimitation.luxurySum() -quantity);
                itemInstance.setQuantity(quantity);
                return quantity* itemInstance.getPrice();
            }
            else{
                wrongQuantities.add(item);
                return 0;
            }
        }
        else {
            return setCartCategory.calculateTotal(cart,item , quantity,wrongQuantities,readEssentials,readLuxury,readMisc);
        }
    }
}
