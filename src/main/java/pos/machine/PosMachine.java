package pos.machine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {

        List<String> items = getBarcodeValues(barcodes);
        int totalPrice = getTotalPrice(barcodes);
        String finalReciept = getFinalReciept(items, totalPrice);
        System.out.println(finalReciept);
        return finalReciept;
    }

    private String getFinalReciept(List<String> items, int totalPrice) {
        int i=0;
        StringBuilder sb = new StringBuilder();
        while(i<items.size()){
            sb.append(items.get(i));
        }
        String finalOutput = sb.toString();
        return finalOutput;
    }

    private int getTotalPrice(List<String> barcodes) {
        int total=0;
        String barcodeee = barcodes.get(1);
        List<ItemInfo> allInfo = ItemDataLoader.loadAllItemInfos();
        for(ItemInfo infos : allInfo) {
            if (infos.getBarcode() == barcodeee) {
                total += infos.getPrice();
            }
        }
        return total;
    }

    private List<String> getBarcodeValues(List<String> barcodes) {
        List<String> itemsInfo = getItemsInfo(barcodes);

        return itemsInfo;
    }

    private List<String> getItemsInfo(List<String> barcodes) {
        List<String> itemDetails = getItemDetails(barcodes);
        List<String> itemWithSubtotal = getSubtotal(itemDetails);

        return itemWithSubtotal;
    }

    private List<String> getSubtotal(List<String> itemDetails) {

        //int count = Collections.frequency(itemDetails,)

        return itemDetails;
    }

    private List<String> getItemDetails(List<String> barcodes) {
        String barcodeee = barcodes.get(1);
        List<ItemInfo> allInfo = ItemDataLoader.loadAllItemInfos();
        ArrayList<String> recipt = new ArrayList<String>();

        for(ItemInfo infos : allInfo) {
            if (infos.getBarcode() == barcodeee) {
                recipt.add(infos.getBarcode());
                recipt.add(infos.getName());
                recipt.add(Integer.toString(infos.getPrice()));
            }
        }
        return recipt;
    }
}
