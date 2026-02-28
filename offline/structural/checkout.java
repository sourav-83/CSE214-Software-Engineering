
public class checkout {
     
    public double calculatePrice(component component) {
        return component.getPrice();
    }
    
    
    public void displayPurchaseDetails(component component) {
        System.out.println("---------- PURCHASE DETAILS ----------");
        component.display("");
        System.out.println("------------------------------");
        System.out.printf("TOTAL PRICE: $%.2f%n", calculatePrice(component));
        System.out.printf("TOTAL DURATION: %.2f hours%n", component.getDuration());
        System.out.println("------------------------------\n");
    }
    
   
    public void processCheckout(component component) {
        displayPurchaseDetails(component);
        System.out.println("Payment processed successfully!");
        System.out.println();
    }
}