package core.java.java8stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java8stream {

    public static void main(String[] args) {
        //集合转换为流
        listToStream();
        //数组使用 Stream.of() 转换为流
        arrayToStream();
        //map方法按照指定的函数转换流中的元素
        map();
        //limit()返回包含当前流中前n个的新流，skip()丢弃前n个
        limitAndSkip();
        //takeWhile条件为真时的所有值 dropWhile条件为假时的所有值
        takeAndDropWhile();
    }

    //集合转换为流
    private static void listToStream() {
        System.out.println("----listToStream----");
        List<String> list = getList();
        var count = list.stream()
                .filter(s -> s.length() > 3)
                .count();
        System.out.println(count);
    }

    //数组使用 Stream.of() 转换为流
    //需要使用基本类型的包装类型
    private static void arrayToStream() {
        System.out.println("----arrayToStream----");
        Integer[] array = getArray(10);
        var count = Stream.of(array)
                .filter(i -> i < 5)
                .count();
        System.out.println(count);
    }

    private static void map() {
        System.out.println("----map----");
        List<String> list = getList();
        Stream<String> stringStream = list.stream()
                .filter(s -> s.length() > 3)
                .map(String::toUpperCase);//转换为大写
        System.out.println(Arrays.toString(stringStream.toArray()));
    }

    private static void limitAndSkip() {
        System.out.println("----limitAndSkip----");
        Stream<Double> limit = Stream.generate(Math::random) //通过反复调用 random 产生无限流，即产生随机数
                .limit(10);// 返回一个新的流，然后在返回 n 个后结束，这里返回10个。
        System.out.println(Arrays.toString(limit.toArray()));
        Integer[] array = getArray(10);
        var skip = Stream.of(array).skip(5);// 返回一个新的流，丢弃前n个。
        System.out.println(Arrays.toString(skip.toArray()));
    }

    private static void takeAndDropWhile() {
        System.out.println("----takeAndDropWhile----");
        List<String> list = getList();
        var takeWhile = list.stream()
                .takeWhile(e -> e.startsWith("c")).collect(Collectors.toList());
        var dropWhile = list.stream()
                .dropWhile(e -> e.charAt(0) == 'b').collect(Collectors.toList());
        //TODO 为什么不起作用
        System.out.println(takeWhile);
        System.out.println(dropWhile);
    }


    private static List<String> getList() {
        List<String> list = new ArrayList<>();
        list.add("aaaa");
        list.add("bbbbbbbb");
        list.add("ccc");
        list.add("dd");
        list.add("dd");
        list.add("fffffff");
        list.add("gg");
        list.add("HHHHHH");
        return list;
    }

    private static Integer[] getArray(int n) {
        Integer[] array = new Integer[n];
        for (int i = 0; i < array.length; i++) {
            array[i] = i;
        }
        return array;
    }

}
