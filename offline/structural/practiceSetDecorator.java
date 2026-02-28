public class practiceSetDecorator extends componentDecorator {
    private static final double PRACTICE_SET_PRICE = 10.0;

    public practiceSetDecorator(component component) {
        super(component);
    }

    @Override
    public double getPrice() {
        return wrappedComponent.getPrice() + PRACTICE_SET_PRICE;
    }

    @Override
    public void display(String indent) {
        wrappedComponent.display(indent);
        System.out.printf("%s+ Practice Question Set: $%.2f%n", indent, PRACTICE_SET_PRICE);
    }
}