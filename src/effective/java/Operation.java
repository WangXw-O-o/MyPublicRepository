package effective.java;

public enum Operation {
    PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return 0;
        }
    };

    private final String symbol;

    Operation(String symbol) {
        this.symbol = symbol;
    }

    public abstract double apply(double x, double y);
}
