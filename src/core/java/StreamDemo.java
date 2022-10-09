package core.java;

import java.util.ArrayList;
import java.util.List;

public class StreamDemo {

    public static void main(String[] args) {
        List<String> list = List.of("a b ca d fc".split(" "));
        long count = list.stream().filter(w -> w.length() == 1).count();
        System.out.println(count);
    }



}
