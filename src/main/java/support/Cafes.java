package support;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import static support.Cafes.Product.Cake;
import static support.Cafes.Product.Coffee;
import static support.Util.*;

public class Cafes {
  public static Stream<CafeService> allCafes() {
    return list(new CafeRoma(), new CakesRUs(), new EvilBeanCafe()).stream();
  }

  public enum Product {Coffee, Cake}

  public static final class Price {
    public String cafeName;
    public String product;
    public double price;

    private Price(String cafeName, String product, double price) {
      this.cafeName = cafeName;
      this.product = product;
      this.price = price;
    }

    public static Price available(CafeService cafeService, Product product, double price) {
      return new Price(cafeService.toString(), product.name(), price);
    }

    public static Price unavailable(CafeService cafe, Product product, String message) {
      return new Price(cafe.toString(), String.format("%s unavailable (%s)", product, message), 0d);
    }
  }

  public abstract static class CafeService {
    protected abstract double coffeePrice();
    protected abstract double cakePrice();

    public Price bestPriceForCoffee() {
      sleepFor(between(2,6), TimeUnit.SECONDS);
      log("Coffee costs %.2f at %s", coffeePrice(), this);
      return Price.available(this, Coffee, coffeePrice());
    }
    public Price bestPriceForCake() {
      sleepFor(between
        (1,4), TimeUnit.SECONDS);
      log("Cake costs %.2f at %s", cakePrice(), this);
      return Price.available(this, Cake, cakePrice());
    }
  }

  public static class CakesRUs extends CafeService {
    protected double coffeePrice() {
      throw new IllegalStateException("Out of Coffee!");
    }

    protected double cakePrice() {
      return 0.5;
    }

    public String toString() {
      return "Cakes`R Us";
    }
  }

  public static class EvilBeanCafe extends CafeService {
    protected double coffeePrice() {
      return 2.50;
    }

    protected double cakePrice() {
      return 0.99;
    }

    public String toString() {
      return "Evil Bean Cafe";
    }
  }

  public static class CafeRoma extends CafeService {
    protected double coffeePrice() {
      return 2.00;
    }

    protected double cakePrice() {
      return 1.20;
    }

    public String toString() {
      return "Cafe Roma";
    }
  }
}
