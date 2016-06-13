package examples.interfaces;

import com.google.common.collect.Lists;

public class InterfaceDefaultMethods {

    public static void main(String[] args) {
        Lists.newArrayList(new Duck(), new Penguin(), new Ostrich(), new Dolphin(), new Orangutan(), new Tiger())
                .forEach(Animal::move);
    }

    public interface Walk {
        default void walk() {
            System.out.println("Walking");
        }
    }

    public interface Swim {
        default void swim() {
            System.out.println("Swimming");
        }
    }

    public interface Fly {
        default void fly() {
            System.out.println("Flying");
        }
    }

    public interface Climb {
        default void climb() {
            System.out.println("Climbing");
        }
    }


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
    public static class Duck extends Animal implements Fly, Swim, Walk {
        void doMove() {
            fly();
            swim();
            walk();
        }
    }

    /**
     * Penguins can swim, walk
     */
    public static class Penguin extends Animal implements Swim, Walk {
        void doMove() {
            swim();
            walk();
        }
    }

    /**
     * Ostriches can walk
     */
    public static class Ostrich extends Animal implements Walk {
        void doMove() {
            walk();
        }
    }

    /**
     * Dolphins can swim
     */
    public static class Dolphin extends Animal implements Swim {
        void doMove() {
            swim();
        }
    }

    /**
     * Orangutan can climb, walk
     */
    public static class Orangutan extends Animal implements Climb, Walk{
        void doMove() {
            climb();
            walk();
        }
    }

    /**
     * Tigers can swim, walk, climb
     */
    public static class Tiger extends Animal implements Swim, Walk, Climb {
        void doMove() {
            swim();
            walk();
            climb();
        }
    }

}
