import java.util.ArrayList;
import java.util.List;

public class Converter {

    // Gibt die Nummer als Liste zurück
    public List<Integer> castIntToList(int number) {
        return castStrListToIntList(splittNumber(String.valueOf(number)));
    }
    
    // Zerteilt den String in eine Liste
    public List<String> splittNumber(String strNumber) {
    
        List<String> splittedNumber = new ArrayList<>();
    
        for (int i = 0; i < strNumber.length(); i++) {
            splittedNumber.add(String.valueOf(strNumber.charAt(i)));
        }
    
        return splittedNumber;
    } 
    
    // Gibt die Liste als Integer zurück
    public List<Integer> castStrListToIntList(List<String> list) {
            
        List<Integer> intList = new ArrayList<>();
    
        for (String s : list) {
            intList.add(Integer.parseInt(s));
        }
    
        return intList;
    }

}