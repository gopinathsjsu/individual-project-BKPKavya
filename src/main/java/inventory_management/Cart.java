package inventory_management;

import java.util.HashMap;
import java.util.Set;

public interface Cart {
    public void setCartCategory(Cart cart);
    public double calculateTotal(HashMap<String, Instances> cart , String item , int quantity, Set<String> wrongQuantities,
                                 Set<String> readEssentials, Set<String> readLuxury, Set<String> readMisc);
}
