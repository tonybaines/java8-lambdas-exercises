package examples.completablefuture;

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

import com.google.code.tempusfugit.temporal.Duration;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.google.code.tempusfugit.concurrency.ThreadUtils.sleep;

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
    final List<CafeService> cafes = Lists.newArrayList(new CafeRoma(), new CakesRUs(), new EvilBeanCafe());


    CompletableFuture first = CompletableFuture.supplyAsync(() -> "First result");

    CompletableFuture second = first.thenApply(prev -> prev+".  SecondResult");

    // ... time passes

    System.out.println(second.get());

  }


  public abstract static class CafeService {
    protected abstract double coffeePrice();
    protected abstract double cakePrice();

    private static Random random = new Random();
    private static int nextRandom() {return random.nextInt(3);}
    public double bestPriceForCoffee() {
      sleep(Duration.seconds(nextRandom()));
      return coffeePrice();
    }
    public double bestPriceForCake() {
      sleep(Duration.seconds(nextRandom()));
      return cakePrice();
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
