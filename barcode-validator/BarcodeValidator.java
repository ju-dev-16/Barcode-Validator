import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import helper.Converter;

class BarcodeValidator {

    private final static Scanner scanner = new Scanner(System.in);
    private final static Converter converter = new Converter();
    private static boolean loop = true;

    public static void main(String[] args) {

        while (loop) {

            String input = barcodeQuery();

            try {
                
                // Parsing check digit from barcode
                int checkDigit = Integer.parseInt(input.substring(input.length() - 1)); 

                String barcode = input.substring(0, input.length() - 1);
    
                if (barcode.length() == 12) {
    
                    // Multiply the barcode as list n times
                    List<Integer> multipliedBarcode = multiplyList(barcode, 3);

                    // Calculate the checksum
                    List<Integer> checksum = calculateChecksum(multipliedBarcode);

                    // Check digit procedure
                    validateBarcode(checksum, checkDigit);
    
                } else {
    
                    System.out.println("\nDer Barcode ist ungültig.\n");
    
                }

            } catch (NumberFormatException nfe) {

                System.out.println("\nDer Barcode ist ungültig.\n");

            }

            loop = repeatProcessQuery();
            
        }

        System.out.println("\nBeende Programm...\n");

        scanner.close();
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

    private static List<Integer> multiplyList(String barcode, int faktor) {      

        List<Integer> currentList = converter.strListToIntList(converter.splittStrNumber(barcode));

        List<Integer> multipliedList = new ArrayList<>();

        for (int i = 0; i < currentList.size(); i++) {

            if (i % 2 == 1) {

                multipliedList.add(currentList.get(i));

            } else {

                multipliedList.add(currentList.get(i) * faktor);

            }

        }
        
        return multipliedList;
    }

    public static List<Integer> calculateChecksum(List<Integer> numbers) {

        Integer result = 0;

        for (int i = 0; i < numbers.size(); i++) {
            result = result + numbers.get(i);
        }

        return converter.castIntToList(result);
    }

    private static void validateBarcode(List<Integer> checksum, int checkDigit) {

        if ((Integer.parseInt(converter.intListToStr(checksum)) + checkDigit) % 10 == 0) {

            System.out.println("\nDer Barcode ist gültig.\n");
    
        } else {
    
            System.out.println("\nDer Barcode ist ungültig.\n");
    
        }   
    }
}