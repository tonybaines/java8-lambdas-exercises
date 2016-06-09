package examples;

/*============================================================================*
 * - COPYRIGHT NOTICE -
 *
 * Copyright (c) British Telecommunications plc, 2010, All Rights Reserved
 *
 * The information contained herein and which follows is proprietary
 * information and is the property of BT. This information is not to be
 * divulged, released, copied, reproduced, or conveyed to unauthorised
 * personnel,companies or other institutions without the direct and expressed
 * approval in writing of BT
 *
 *============================================================================*/

import support.Cafes;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

import static support.Cafes.Product.Cake;
import static support.Cafes.Product.Coffee;
import static support.Util.*;

/*
@startuml

note left of Client
  I want coffee and cake!
end note

== Coffee First ==
Client -> Service1: bestPriceForCoffee()
Client <-- Service1: £2.50
Client -> Service2: bestPriceForCoffee()
Client <-- Service2: £2.10
Client -> Service3: bestPriceForCoffee()
Client <-- Service3: OUT-OF-BEAN-ERROR!

== Then Cake ==
Client -> Service1: bestPriceForCake()
Client <-- Service1: £0.99
Client -> Service2: bestPriceForCake()
Client <-- Service2: £1.20
Client -> Service3: bestPriceForCake()
Client <-- Service3: £0.50

note left of Client
  Choose based
  on price
end note

@enduml
 */
public class CompletableFutures {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    log("Start");

    final List<CompletableFuture<String>> results = Cafes.allCafes().stream()
      .map(cafe -> {
        // For each cafe, start asynchronous jobs to lookup the price of coffee and cake (and handle errors)
        final CompletableFuture<Cafes.Price> coffeeFuture = CompletableFuture
          .supplyAsync(() -> cafe.bestPriceForCoffee())
          .exceptionally(ex -> Cafes.Price.unavailable(cafe, Coffee, ex.getCause().getMessage()));
        final CompletableFuture<Cafes.Price> cakeFuture = CompletableFuture
          .supplyAsync(() -> cafe.bestPriceForCake())
          .exceptionally(ex -> Cafes.Price.unavailable(cafe, Cake, ex.getCause().getMessage()));

        // Merge what comes back from the two
        return coffeeFuture.thenCombine(cakeFuture, (Cafes.Price coffee, Cafes.Price cake) ->
          String.format("Cafe '%s': %s %.2f, %s %.2f.  Total %.2f", coffee.cafeName, coffee.product, coffee.price, cake.product, cake.price, coffee.price + cake.price));
      })
      .collect(Collectors.toList());

    // As each cafe-result comes in, handle the message
    results
      .forEach(result -> result.thenAcceptAsync(msg -> log(msg)));

    // Wait for all to complete
    log("All requests submitted, waiting for the answers to come back");
    results.forEach(it -> it.join());

    log("Done");

  }

}
