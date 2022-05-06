package inventory_management;

import java.util.HashMap;
import java.util.Set;

public class CategoryEssentials implements Cart {
    private  Cart setCartCategory;
    public void setCartCategory(Cart nextCart) {

        setCartCategory = nextCart;
    }

    public double calculateTotal(HashMap<String, Instances> cart , String item , int quantity, Set<String> wrongQuantities,
                                 Set<String> readEssentials, Set<String> readLuxury, Set<String> readMisc)
    {
        Instances itemInstance = cart.get(item);

        if(itemInstance.Category.equals("Essentials")){
            readEssentials.add(item);
            if(quantity<=itemInstance.getQuantity()){
                ItemsCategoryLimitation.setEssentialCount(ItemsCategoryLimitation.getEssentialCount()- quantity);
                itemInstance.setQuantity(itemInstance.getQuantity()-quantity);
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
