package test;

public interface Parent extends TestInterface {

    default void test() {
        System.out.println("这是默认方法！");
    }

}
