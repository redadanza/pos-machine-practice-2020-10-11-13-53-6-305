package pos.machine;

public class main {
    public static void main(String[] args){
        PosMachine posmach = new PosMachine();
        posmach.printReceipt(ItemDataLoader.loadBarcodes());


    }
}
