public abstract class componentDecorator implements component {
    protected component wrappedComponent;

    public componentDecorator(component component) {
        this.wrappedComponent = component;
    }

    @Override
    public String getName() {
        return wrappedComponent.getName();
    }

    @Override
    public double getPrice() {
        return wrappedComponent.getPrice();
    }

    @Override
    public double getDuration() {
        return wrappedComponent.getDuration();
    }

    @Override
    public void display(String indent) {
        wrappedComponent.display(indent);
    }
}