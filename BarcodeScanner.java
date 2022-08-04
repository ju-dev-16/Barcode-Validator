import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class BarcodeScanner {

    private final static Scanner scanner = new Scanner(System.in);

    private final static Converter converter = new Converter();
    private final static Checksum checksumCalculator = new Checksum();

    private static boolean loop = true;

    public static void main(String[] args) {

        while (loop) {

            String input = barcodeQuery();

            int checkDigit = Integer.parseInt(input.substring(input.length() - 1)); 

            String barcode = input.substring(0, input.length() - 1);

            if (barcode.length() == 12) {

                List<Integer> multipliedBarcode = multiplyList(barcode); // 1. Jede Ziffer mal 3 multiplizieren

                List<Integer> checksum = checksumCalculator.calculate(multipliedBarcode); // 2. Quersumme berechnen
    
                validateBarcode(checksum, checkDigit); // 3. Durch die nächste durch 10 teilbare Zahl ergänzen

            } else {

                System.out.println("\nDer Barcode ist ungültig.\n");

            }

            loop = repeatProcessQuery();
            
        }

        System.out.println("\nBeende Programm...\n");

        scanner.close();
    }


    private static void validateBarcode(List<Integer> checksum, int checkDigit) {

        StringBuilder checksumAsString = new StringBuilder();

        for (Integer i : checksum) {
            checksumAsString.append(String.valueOf(i));
        }

        if ((Integer.parseInt(checksumAsString.toString()) + checkDigit) % 10 == 0) {

            System.out.println("\nDer Barcode ist gültig.\n");
    
        } else {
    
            System.out.println("\nDer Barcode ist ungültig.\n");
    
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

}