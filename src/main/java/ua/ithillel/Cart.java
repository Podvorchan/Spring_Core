package ua.ithillel;

import java.util.HashMap;
import java.util.Map;
import lombok.Data;

/**
 * @author Podvorchan Ruslan 03.01.2023
 */
@Data
public class Cart {

  Map<Integer, Product> products = new HashMap<>();

  public boolean addProduct(Product product) {
    if (product == null) {
      return false;
    }
    products.put(product.getId(), product);
    return true;
  }

  public boolean deleteProduct(Product product) {
    if (product == null) {
      return false;
    }
    if (products.containsKey(product.getId())) {
      products.remove(product.getId());
      return true;
    } else {
      return false;
    }
  }

  @Override
  public String toString() {
    return "Cart contains: " + products;
  }

}
