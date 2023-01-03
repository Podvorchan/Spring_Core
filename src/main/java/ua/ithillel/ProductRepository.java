package ua.ithillel;

import java.util.List;
import lombok.Data;

/**
 * @author Podvorchan Ruslan 03.01.2023
 */
@Data
public class ProductRepository {

  List<Product> products;

  public ProductRepository(List<Product> products) {
    this.products = products;
  }

  public List<Product> getAll() {
    return products;
  }

  public Product getById(int id) {
    return products.stream().filter(product -> product.getId() == id).findFirst().orElse(null);
  }

}
