import java.util.List;

public class Checksum {

    private Converter converter = new Converter();

    public List<Integer> calculate(List<Integer> numbers) {

        Integer result = 0;

        for (int i = 0; i < numbers.size(); i++) {
            result = result + numbers.get(i);
        }

        return converter.castIntToList(result);
    }
    
}