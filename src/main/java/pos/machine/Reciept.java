package pos.machine;
import java.util.List;
public class Reciept {
    private List<Details> itemDetail;
    private int receiptTotal;

    public Reciept(List<Details> itemDetail, int receiptTotal) {
        this.itemDetail = itemDetail;
        this.receiptTotal = receiptTotal;
    }

    public List<Details> getItemDetail() {
        return itemDetail;
    }

    public int getReceiptTotal() {
        return receiptTotal;
    }
}
