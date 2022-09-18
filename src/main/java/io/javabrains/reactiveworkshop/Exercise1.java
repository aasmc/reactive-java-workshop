package io.javabrains.reactiveworkshop;

import java.util.stream.Stream;

public class Exercise1 {

    public static void main(String[] args) {

        // Use StreamSources.intNumbersStream() and StreamSources.userStream()

        // Print all numbers in the intNumbersStream stream
        StreamSources.intNumbersStream()
                .forEach(System.out::println);

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        // Print numbers from intNumbersStream that are less than 5
        StreamSources.intNumbersStream()
                .filter(num -> num < 5)
                .forEach(System.out::println);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        // Print the second and third numbers in intNumbersStream that's greater than 5
        StreamSources
                .intNumbersStream()
                .filter(num -> num > 5)
                .skip(1)
                .limit(2)
                .forEach(System.out::println);
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        //  Print the first number in intNumbersStream that's greater than 5.
        //  If nothing is found, print -1
        StreamSources.intNumbersStream()
                .filter(num -> num > 5)
                .findFirst()
                .ifPresentOrElse(System.out::println, () -> System.out.println(-1));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        // Print first names of all users in userStream
        StreamSources.userStream()
                .forEach(user -> System.out.println(user.getFirstName()));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        // Print first names in userStream for users that have IDs from number stream
        StreamSources.intNumbersStream()
                .flatMap(id -> StreamSources.userStream().filter(user -> user.getId() == id))
                .forEach(user -> System.out.println(user.getFirstName()));
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");

        StreamSources.userStream()
                .filter(user -> StreamSources.intNumbersStream().anyMatch(id -> user.getId() == id))
                .forEach(user -> System.out.println(user.getFirstName()));

    }

}
