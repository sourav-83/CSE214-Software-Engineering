
public class developingCountryDiscountDecorator extends componentDecorator {
    private static final double DISCOUNT_AMOUNT = 10.0;

    public developingCountryDiscountDecorator(component component) {
        super(component);
    }

    @Override
    public double getPrice() {
        double originalPrice = wrappedComponent.getPrice();
        return Math.max(0, originalPrice - DISCOUNT_AMOUNT);
    }

    @Override
    public void display(String indent) {
        wrappedComponent.display(indent);
        System.out.printf("%s- Developing Country Student Discount: -$%.2f%n", indent, DISCOUNT_AMOUNT);
    }
}