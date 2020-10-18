package pos.machine;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

import static pos.machine.ItemDataLoader.loadAllItemInfos;

public class PosMachine {
    int itemsTotal;
    int total;
    public String printReceipt(List<String> barcodes) {

        List<Details> itemsList = getItemDetails(barcodes);
        getQuantity(barcodes, itemsList);
        Reciept reciept = getItemsInfo(itemsList);
        return getFinalRecipt(reciept);

    }
    private String getFinalRecipt(Reciept reciept){
        String finalOutput = "***<store earning no money>Receipt***\n";
        for (Details details : reciept.getItemDetail()) {
            finalOutput = finalOutput.concat(String.format("Name: %s, ", details.getName()));
            finalOutput = finalOutput.concat(String.format("Quantity: %s, ",details.getQuantity()));
            finalOutput =  finalOutput.concat(String.format("Unit price: %s (yuan), ", details.getUnitPrice()));
            finalOutput =  finalOutput.concat(String.format("Subtotal: %s (yuan)", details.getSubtotal()));
            finalOutput =  finalOutput.concat("\n");
        }
        finalOutput = finalOutput.concat("----------------------\n");
        finalOutput = finalOutput.concat("Total: " + reciept.getReceiptTotal());
        finalOutput = finalOutput.concat(" (yuan)\n**********************");


        return finalOutput;
    }
    private Reciept getItemsInfo(List<Details> itemsList) {
        getSubtotal(itemsList);
        itemsTotal = getTotalPrice(itemsList);
        return new Reciept(itemsList,itemsTotal);
    }

    private int getTotalPrice(List<Details> itemsList) {
        total = 0;
        for (Details details : itemsList) {
            total += details.getSubtotal();
        }
        return total;
    }

    private void getSubtotal(List<Details> itemsList) {
        for (Details details : itemsList) {
            details.setSubtotal(details.getQuantity() * details.getUnitPrice());
        }
    }

    private void getQuantity(List<String> barcodes, List<Details> itemsList) {
        int quantity = 0;
        for (Details details: itemsList) {
            for (String barcode : barcodes) {
                quantity = details.getBarcode().equals(barcode) ? quantity+1
                         : quantity;
            }
            details.setQuantity(quantity);
            quantity = 0;
        }
    }

    private List<Details> getItemDetails(List<String> barcodes) {
        List<String> barcodeList = new ArrayList<>();
        List<Details> recipt = new ArrayList<>();
        List<ItemInfo> itemInfos = loadAllItemInfos();

        for (String data : barcodes) {
            if(barcodeList.contains(data)){
                //System.out.println(data);
            }
            else{
                barcodeList.add(data);
            }
        }
        for (String barcode : barcodeList) {
            for (ItemInfo infos : itemInfos) {
                if (infos.getBarcode().equals(barcode)) {
                    Details items = new Details(infos.getBarcode(),infos.getName(),0,infos.getPrice(),0);
                    recipt.add(items);
                }
            }
        }
        return recipt;
    }
}
