import java.util.List;

public class Checksum {

    private Converter converter = new Converter();

    public List<Integer> calculate(String strNumber) {

        List<Integer> castedNumber = converter.castStrListToIntList(converter.splittNumber(strNumber));

        Integer result = 0;

        for (int i = 0; i < castedNumber.size(); i++) {
            result = result + castedNumber.get(i);
        }

        return converter.castIntToList(result);
    }
    
}