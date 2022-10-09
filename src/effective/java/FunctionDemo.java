package effective.java;

import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class FunctionDemo {

    public static void main(String[] args) {
        Instant instant = Instant.now();
        System.out.println(instant);
        Date date = new Date();
        System.out.println(date.toString());
        try {

        } catch (Exception e) {

        } finally {
            return;
        }
    }

    private void test1(Objects objects) {
        assert objects != null;
        Objects.requireNonNull(objects);
    }
}
