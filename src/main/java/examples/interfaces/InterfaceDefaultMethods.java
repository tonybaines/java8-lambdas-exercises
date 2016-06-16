package examples.interfaces;

import com.google.common.collect.Lists;

public class InterfaceDefaultMethods {

    public static void main(String[] args) {
        Lists.newArrayList(new Duck(), new Penguin(), new Ostrich(), new Dolphin(), new Orangutan(), new Tiger())
                .forEach(Animal::move);
    }

    /*
     *  1. Create trivial (println) implementations of different movements
     *     Try to reuse as much as possible
     */

    /**
     * All animals can move
     */
    public static abstract class Animal {
        abstract void doMove();
        void move() {
            System.out.println("");
            System.out.println(this.getClass().getSimpleName());
            System.out.println("============");
            doMove();
        }
    }

    /**
     * Ducks can fly, swim, walk
     */
    public static class Duck extends Animal {
        void doMove() {
            /*???*/
        }
    }

    /**
     * Penguins can swim, walk
     */
    public static class Penguin extends Animal {
        void doMove() {
            /*???*/
        }
    }

    /**
     * Ostriches can walk
     */
    public static class Ostrich extends Animal {
        void doMove() {
            /*???*/
        }
    }

    /**
     * Dolphins can swim
     */
    public static class Dolphin extends Animal {
        void doMove() {
            /*???*/
        }
    }

    /**
     * Orangutan can climb, walk
     */
    public static class Orangutan extends Animal {
        void doMove() {
            /*???*/
        }
    }

    /**
     * Tigers can swim, walk, climb
     */
    public static class Tiger extends Animal {
        void doMove() {
            /*???*/
        }
    }



/*  Step 2. Use mixin interfaces with default methods

    public interface Walk {
        ???
    }

    public interface Swim {
        ???
    }

    public interface Fly {
        ???
    }

    public interface Climb {
        ???
    }
*/
}
