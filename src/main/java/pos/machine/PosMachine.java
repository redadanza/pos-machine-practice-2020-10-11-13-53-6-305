package pos.machine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class PosMachine {
    public String printReceipt(List<String> barcodes) {

        List<String> items = getBarcodeValues(barcodes);
        int totalPrice = getTotalPrice(barcodes);
        String finalReciept = getFinalReciept(items, totalPrice);
        System.out.println(finalReciept);

        //System.out.println(items);
        //System.out.println("Total: " + totalPrice + " (yuan)");

        return finalReciept;
    }

    private String getFinalReciept(List<String> items, int totalPrice) {
        int i=0;
        String finalOutput = "";
        for(i=0;i<=16;i++)
        {
            finalOutput = finalOutput.concat(items.get(i));
        }

        finalOutput = finalOutput.concat("\nTotal: " + totalPrice + " (yuan)");

        return finalOutput;
    }

    private int getTotalPrice(List<String> barcodes) {
        int total=0;
        String barcodeee;
        List<ItemInfo> allInfo = ItemDataLoader.loadAllItemInfos();
        for(int i = 0; i<8; i++) {
            barcodeee= barcodes.get(i);
            for (ItemInfo infos : allInfo) {
                if (infos.getBarcode() == barcodeee) {
                    total += infos.getPrice();
                }
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

        return itemDetails;
    }

    private List<String> getSubtotal(List<String> itemDetails) {

        Map<String, Integer> map= new HashMap<String, Integer>();
        for(String s: itemDetails){
            map.put(s,Collections.frequency(itemDetails,s));
        }
        //System.out.println("map is: " + map);
        return itemDetails;
    }

    private List<String> getItemDetails(List<String> barcodes) {
        String barcodeee;
        List<ItemInfo> allInfo = ItemDataLoader.loadAllItemInfos();
        ArrayList<String> recipt = new ArrayList<String>();
        for(int i = 0; i<=8; i++) {
            barcodeee= barcodes.get(i);
            for (ItemInfo infos : allInfo) {
                if (infos.getBarcode() == barcodeee) {
                    //recipt.add(infos.getBarcode());
                    recipt.add("Name: " + infos.getName());
                    recipt.add("Unit Price: " + Integer.toString(infos.getPrice()));

                }
            }
            recipt.add("\n");
        }
        return recipt;
    }
}
