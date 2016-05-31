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

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class CompletableFutures {
  public static void main(String[] args) throws ExecutionException, InterruptedException {
    CompletableFuture first = CompletableFuture.supplyAsync(() -> "First result");

    CompletableFuture second = first.thenApply(prev -> prev+".  SecondResult");

    // ... time passes

    System.out.println(second.get());

  }
}
