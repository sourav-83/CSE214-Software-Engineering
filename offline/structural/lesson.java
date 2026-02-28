public class lesson implements component {
    private String name;
    private double duration;
    private double price;

    public lesson(String name, double duration, double price) {
        this.name = name;
        this.duration = duration;
        this.price = price;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public double getDuration() {
        return duration;
    }

    @Override
    public void display(String indent) {
        System.out.printf("%sLesson: %s | Duration: %.2f hrs | Price: $%.2f%n", indent, name, duration, price);
    }
}