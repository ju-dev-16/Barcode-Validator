import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BarcodeScanner {

    private static Scanner scanner = new Scanner(System.in);

    private static Converter converter = new Converter();

    public static void main(String[] args) {

        // Beispiel Barcode: 4012345123432

        boolean loop = true;

        while(loop) {

            String barcode = barcodeQuery();

            if (!checkBarcodeLength(barcode)) {

                System.out.println("\nDieser Barcode ist ungültig.\n");

            } else {

                List<Integer> multipliedBarcode = multiplyList(barcode);

                StringBuilder barcodeAsString = new StringBuilder();

                for (Integer i : multipliedBarcode) {
                    barcodeAsString.append(String.valueOf(i));
                }

                List<Integer> checksum = new Checksum().calculate(barcodeAsString.toString());

                switch (checksum.get(checksum.size() - 1)) {
                    case 1: {
                        validateBarcode(barcodeAsString.toString(), 9);
                        break;
                    }
                    case 2: {
                        validateBarcode(barcodeAsString.toString(), 8);
                        break;   
                    }
                    case 3: {
                        validateBarcode(barcodeAsString.toString(), 7);
                        break;                       
                    }
                    case 4: {
                        validateBarcode(barcodeAsString.toString(), 6);
                        break;                       
                    }
                    case 5: {
                        validateBarcode(barcodeAsString.toString(), 5);
                        break;                      
                    }
                    case 6: {
                        validateBarcode(barcodeAsString.toString(), 4);
                        break;
                    }
                    case 7: {
                        validateBarcode(barcodeAsString.toString(), 3);
                        break;    
                    }
                    case 8: {
                        validateBarcode(barcodeAsString.toString(), 2);
                        break;                       
                    }
                    case 9: {
                        validateBarcode(barcodeAsString.toString(), 1);
                        break; 
                    }
                    default: {
                        System.out.println("\nIhr Barcode ist ungültig!\n");
                        break;
                    }
                }
            }

            loop = repeatProcessQuery();
            
        }

        scanner.close();

        System.out.println("\nBeende Programm...\n");
    }

    private static void validateBarcode(String barcodeAsString, int checkDigit) {

        String barcodeWithoutCheckDigit = barcodeAsString.substring(0, barcodeAsString.length() - 1);

        try {

            String barcode = barcodeWithoutCheckDigit + String.valueOf(checkDigit);

            System.out.println(Long.parseLong(barcode));
            
            if (Long.parseLong(barcode) % 10L == 0L) {

                System.out.println("\nDer Barcode ist gültig.\n");
    
            } else {
    
                System.out.println("Der Barcode ist ungültig.");
    
            }   

        } catch (NumberFormatException nfe) {

            nfe.printStackTrace();

        }
    }

    private static String barcodeQuery() {

        System.out.print("\nBitte geben Sie den Barcode ein: \n\n -> ");

        return scanner.nextLine();
    }

    private static boolean repeatProcessQuery() {

        System.out.print("\nMöchten Sie noch einen Barcode überprüfen lassen? (j/n): \n\n -> ");

        switch (scanner.nextLine()) {
            case "j": {
                return true;
            }
            case "n": {
                return false;
            }
            default: {
                return true;
            }
        }
    }

    // Multipliziert jedes Element mit geradem Index mal 3
    private static List<Integer> multiplyList(String barcode) {      

        List<Integer> currentList = converter.castStrListToIntList(converter.splittNumber(barcode));

        List<Integer> multipliedList = new ArrayList<>();

        for (int i = 0; i < currentList.size(); i++) {

            if (i % 2 == 1) {

                multipliedList.add(currentList.get(i));

            } else {

                multipliedList.add(currentList.get(i) * 3);

            }

        }
        
        return multipliedList;
    }

    private static boolean checkBarcodeLength(String barcode) {

        if (barcode.length() == 13) {
            return true;
        }

        return false;
    }
}