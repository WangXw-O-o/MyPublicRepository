package test;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class Test implements Parent {

    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException {
//        List
    }

    @Override
    public void test() {
        System.out.println("子类");
    }

    @Override
    public void test1() {

    }

}