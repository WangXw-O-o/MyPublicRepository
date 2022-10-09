package demoTest;

import demoTest.demo.Person;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DemoMain {

    public static void main(String[] args) {
        var planets = new String[] {"a", "bbbbb", "dd", "cccc"};
        Arrays.sort(planets, (first, second) -> first.length() - second.length());
        System.out.println(Arrays.toString(planets));

//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("aa");
//        strings.add("bb");
//        strings.add(null);
//        strings.removeIf(Objects::isNull);
//        System.out.println(strings);

        ArrayList<String> names = new ArrayList<>();
        names.add("aa");
        names.add("bb");
        Stream<Person> stream = names.stream().map(Person::new);
        List<Person> people = stream.collect(Collectors.toList());
        System.out.println(people);

    }

}
