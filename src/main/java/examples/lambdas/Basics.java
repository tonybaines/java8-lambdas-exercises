package examples.lambdas;

import java.util.function.Function;

public class Basics {
    public static void main(String[] args) {
        // Anonymous inner class
        final Function<String, Integer> functionFromStringToInteger = new Function<String, Integer>() {
            @Override
            public Integer apply(String s) {
                return s.length();
            }
        };

        // Rewrite as a lambda (verbose)
        final Function<String, Integer> verboseLambdaStringToInteger = (String s) -> {
            return s.length();
        };

        // Rewrite as lambda (concise syntax, inferred types)
        final Function<String, Integer> inferredTypesLambdaStringToInteger = (s) -> s.length();

        // Rewrite as lambda (method reference)
        final Function<String, Integer> methodReferenceLambdaStringToInteger = String::length;
    }
}
