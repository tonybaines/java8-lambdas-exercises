package examples.futures;

import support.Cafes;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static support.Cafes.Product.Cake;
import static support.Cafes.Product.Coffee;
import static support.Util.log;

public class CompletableFutures {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    log("Start");

    final List<CompletableFuture<String>> results = Cafes.allCafes()
      .map(cafe -> {
        // For each cafe, start asynchronous jobs to lookup the price of coffee and cake (and handle errors)
        final CompletableFuture<Cafes.Price> coffeeFuture = ???;
        final CompletableFuture<Cafes.Price> cakeFuture = ???;

        // Merge what comes back from the two separate calls
        return coffeeFuture.thenCombine(cakeFuture, (Cafes.Price coffee, Cafes.Price cake) ->
          String.format("Cafe '%s': %s %.2f, %s %.2f.  Total %.2f",
            coffee.cafeName, coffee.product, coffee.price, cake.product, cake.price, coffee.price + cake.price));
      })
      .collect(Collectors.toList());

    // As each cafe-result comes in, handle the message as soon as it's available
    results
      .forEach(result -> ???);


    log("All requests submitted, waiting for the answers to come back");
    // CompletableFuture.allOf(...) looks useful here, but accepts a vararg not a Collection
    results.forEach(it -> it.join());

    log("Done");

  }

}
