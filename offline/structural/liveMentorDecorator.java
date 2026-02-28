public class liveMentorDecorator extends componentDecorator {
    private static final double MENTOR_PRICE = 20.0;

    public liveMentorDecorator(component component) {
        super(component);
    }

    @Override
    public double getPrice() {
        return wrappedComponent.getPrice() + MENTOR_PRICE;
    }

    @Override
    public void display(String indent) {
        wrappedComponent.display(indent);
        System.out.printf("%s+ Live Mentor Support: $%.2f%n", indent, MENTOR_PRICE);
    }
}
