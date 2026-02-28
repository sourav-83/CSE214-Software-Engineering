public class durationDiscountDecorator extends componentDecorator {
    private static final double DISCOUNT_AMOUNT = 12.0;
    private static final double DURATION_THRESHOLD = 5.0;

    public durationDiscountDecorator(component component) {
        super(component);
    }

    @Override
    public double getPrice() {
        double originalPrice = wrappedComponent.getPrice();
        if (wrappedComponent.getDuration() >= DURATION_THRESHOLD) {
            return Math.max(0, originalPrice - DISCOUNT_AMOUNT);
        }
        return originalPrice;
    }

    @Override
    public void display(String indent) {
        wrappedComponent.display(indent);
        if (wrappedComponent.getDuration() >= DURATION_THRESHOLD) {
            System.out.printf("%s- Special Duration Discount (5+ hrs): -$%.2f%n", indent, DISCOUNT_AMOUNT);
        }
    }
}