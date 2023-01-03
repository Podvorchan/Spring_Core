package ua.ithillel;

import static java.lang.System.out;

import java.util.Map;
import java.util.Scanner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * @author Podvorchan Ruslan 03.01.2023
 */
public class Main {

  public static void main(String[] args) {
    String configPath = "src/main/resources/ApplicationContext.xml";
    ApplicationContext context = new FileSystemXmlApplicationContext(configPath);

    ProductRepository products = context.getBean("productRepository", ProductRepository.class);

    Cart cart = context.getBean("cart", Cart.class);
    boolean isWorking = true;
    int correspondence;

    do {
      out.println("Make a choice:\n" +
          "0 - exit\n" +
          "1 - Show products in cart\n" +
          "2 - Product list\n" +
          "3 - Add product to cart\n" +
          "4 - Delete product from cart\n");

      correspondence = inputInt();

      switch (correspondence) {
        case 0:
          isWorking = false;
          break;
        case 1:
          out.println(cart);

          break;
        case 2:
          out.println(products.getAll());

          break;
        case 3:
          addToCart(cart, products);

          break;
        case 4:
          deleteFromCart(cart, products);

          break;
        default:
          out.println("Invalid input, please try again!");

      }
    } while (isWorking);
  }

  public static void addToCart(Cart cart, ProductRepository products) {
    boolean isExit;
    int id;

    do {
      out.println("Enter product id");
      id = inputInt();

      isExit = checkProductList(id, products);
      if (!isExit) {
        out.println("Invalid input, please try again! ");
      }
    } while (!isExit);
    cart.addProduct(products.getById(id));
    out.println("Product " + products.getById(id) + " added to cart");
  }

  public static void deleteFromCart(Cart cart, ProductRepository products) {
    if (cart.getProducts().size() > 0) {
      boolean isExitProducts;
      boolean isExitCart;
      int id;

      do {
        out.println("Enter the product id to uninstall ");

        isExitCart = false;

        id = inputInt();

        isExitProducts = checkProductList(id, products);
        for (Map.Entry<Integer, Product> pr : cart.getProducts().entrySet()) {
          if (id == pr.getKey()) {
            isExitCart = true;
            break;
          }
        }
        if (!isExitProducts) {
          out.println("This product is not available, please try again ");
        } else if (!isExitCart) {
          out.println("This item is not available in your cart, please try again ");
        }
      } while (!isExitProducts || !isExitCart);
      cart.deleteProduct(products.getById(id));
      out.println("Product " + products.getById(id) + " removed from cart");

    } else {
      out.println("Сart is empty");
    }
  }

  public static boolean checkProductList(int id, ProductRepository products) {
    boolean isExit = false;
    for (Product pr : products.getAll()) {
      if (id == pr.getId()) {
        isExit = true;
        break;
      }
    }
    return isExit;
  }

  public static int inputInt() {
    boolean isInt;
    Scanner scanner = new Scanner(System.in);
    String res;
    do {
      res = scanner.nextLine();
      isInt = true;
      try {
        Integer.parseInt(res);
      } catch (NumberFormatException e) {
        out.println("Not a number");
        isInt = false;
      }
    } while (!isInt);
    return Integer.parseInt(res);
  }

}


