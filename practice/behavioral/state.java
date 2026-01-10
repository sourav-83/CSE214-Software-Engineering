interface VendingMachineState {
    void handleRequest();
    
}

class ReadyState implements VendingMachineState {

    public void handleRequest() {
       System.out.println("Ready state: Please select a product.");
    }
}

class ProductSelectedState implements VendingMachineState {
    VendingMachineContext vendingMachine;
    public ProductSelectedState(VendingMachineContext context)
    {
        vendingMachine = context;
    }
    public void handleRequest() {
        System.out.println("Product selected state: Processing payment.");
        vendingMachine.setState(new PaymentPendingState());
        vendingMachine.request();
    }
}

class PaymentPendingState implements VendingMachineState {

    
    public void handleRequest() {
        System.out.println("Payment pending state: Dispensing product.");
        
    }
}

class OutOfStockState implements VendingMachineState {
    public void handleRequest() {
        System.out.println("Out of stock state: Product unavailable. Please select another product.");
    }
}

class VendingMachineContext {

    private VendingMachineState state;
    public VendingMachineContext() {
        this.state = null;
    }

    public void setState(VendingMachineState state) {
        this.state = state;
    }

    public void request() {
        this.state.handleRequest();
    }
}

public class state
{
    public static void main(String[] args)
    {
    VendingMachineContext vendingMachine = new VendingMachineContext();

    vendingMachine.setState(new ReadyState());

    vendingMachine.request();

    vendingMachine.setState(new ProductSelectedState(vendingMachine));

    vendingMachine.request();

    vendingMachine.setState(new PaymentPendingState());

    vendingMachine.request();

    vendingMachine.setState(new OutOfStockState());

    vendingMachine.request();

    }
}
