interface Printer {
    void print();
}


class LegacyPrinter {
    public void printDocument() {
        System.out.println("Legacy Printer is printing a document.");
    }
}


class PrinterAdapter implements Printer {
    private LegacyPrinter legacyPrinter;

    public PrinterAdapter() {
        this.legacyPrinter = new LegacyPrinter();
    }

    @Override
    public void print() {
        legacyPrinter.printDocument();
    }
}


public class adapter {

    public static void main(String[] args) {
        
        Printer adapter = new PrinterAdapter();
        adapter.print();
    }
}