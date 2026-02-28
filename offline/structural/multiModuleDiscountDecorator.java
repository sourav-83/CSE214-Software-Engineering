public class multiModuleDiscountDecorator extends componentDecorator {
    private static final double DISCOUNT_AMOUNT = 15.0;
    private int moduleCount;

    public multiModuleDiscountDecorator(component component, int moduleCount) {
        super(component);
        this.moduleCount = moduleCount;
    }

    @Override
    public double getPrice() {
        double originalPrice = wrappedComponent.getPrice();
        if (moduleCount >= 2) {
            return Math.max(0, originalPrice - DISCOUNT_AMOUNT);
        }
        return originalPrice;
    }

    @Override
    public void display(String indent) {
        wrappedComponent.display(indent);
        if (moduleCount >= 2) {
            System.out.printf("%s- Multi-Module Discount (2+ modules): -$%.2f%n", indent, DISCOUNT_AMOUNT);
        }
    }
}