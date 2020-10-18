package pos.machine;

public class Details {
    private String barcode;
    private String name;
    private int quantity;
    private int unitPrice;
    private int subtotal;

    public Details(String barcode, String name, int quantity, int unitPrice, int subtotal) {
        this.barcode = barcode;
        this.name = name;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.subtotal = subtotal;
    }

    public void setSubtotal(int subtotal) {
        this.subtotal = subtotal;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getBarcode() {
        return barcode;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public int getSubtotal() {
        return subtotal;
    }
}
