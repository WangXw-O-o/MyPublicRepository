package effective.java;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

import static java.util.Comparator.comparingInt;

public class AbstractDemo {

    private static String param = "param";

    public String getParam() {
        return param;
    }

    public static void setParam(String param) {
        AbstractDemo.param = param;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1,2,3};
        List<Integer> integers = intArrayAsList(a);
        System.out.println(integers.set(0, 9));
        System.out.println(integers.get(0));
        System.out.println(integers.size());

        StaticMemberClass staticMemberClass = new StaticMemberClass(1);
        System.out.println(staticMemberClass.getOutParam());

        List<String> list = new ArrayList<>();
        list.add("12345");
        list.add("2321");
        Collections.sort(list, comparingInt(String::length));
    }

    private Object setList(List<?> a) {
        return a.get(0);
    }

    /**
     * 骨架实现类
     * @param a
     * @return
     */
    private static List<Integer> intArrayAsList(int[] a) {
        Objects.requireNonNull(a);

        return new AbstractList<>() {
            @Override
            public Integer get(int i) {
                return a[i];
            }

            @Override
            public Integer set(int index, Integer element) {
                int oldVal = a[index];
                a[index] = element;
                return oldVal;
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }

    /**
     * 静态成员类
     */
    static class StaticMemberClass {

        private int id;

        public StaticMemberClass(int id) {
            this.id = id;
        }

        public String getOutParam() {
            return this.id + AbstractDemo.param;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
