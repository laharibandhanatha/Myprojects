import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class PolynomialConstantFinder {
    public static void main(String[] args) {
        String filePath = "C:\\Users\\bandh\\OneDrive\\Documents\\Desktop\\input.json"; // JSON file path
        JSONObject obj = null;
        try {
            JSONParser parser = new JSONParser();
            obj = (JSONObject) parser.parse(new FileReader(filePath));
        } catch (IOException | ParseException e) {
            System.out.println("Error reading/parsing file: " + e.getMessage());
            return;
        }

        JSONObject keysObj = (JSONObject) obj.get("keys");
        int n = ((Long) keysObj.get("n")).intValue();
        int k = ((Long) keysObj.get("k")).intValue();

        List<BigInteger> xValues = new ArrayList<>();
        List<BigInteger> yValues = new ArrayList<>();
        for (Object keyObj : obj.keySet()) {
            String key = (String) keyObj;
            if (!key.equals("keys")) {
                JSONObject pointObj = (JSONObject) obj.get(key);
                int base = Integer.parseInt((String) pointObj.get("base"));
                String value = (String) pointObj.get("value");
                BigInteger y = new BigInteger(value, base);
                xValues.add(BigInteger.valueOf(Integer.parseInt(key)));
                yValues.add(y);
            }
        }

        
        BigInteger constantC = lagrangeInterpolation(xValues, yValues, BigInteger.ZERO);

        System.out.println("Constant term c = " + constantC);
    }

    public static BigInteger lagrangeInterpolation(List<BigInteger> xValues, List<BigInteger> yValues, BigInteger x) {
        BigInteger result = BigInteger.ZERO;
        int n = xValues.size();
        for (int i = 0; i < n; i++) {
            BigInteger numerator = BigInteger.ONE;
            BigInteger denominator = BigInteger.ONE;
            for (int j = 0; j < n; j++) {
                if (i != j) {
                    numerator = numerator.multiply(x.subtract(xValues.get(j)));
                    denominator = denominator.multiply(xValues.get(i).subtract(xValues.get(j)));
                }
            }
            
            result = result.add(yValues.get(i).multiply(numerator).divide(denominator));
        }
        return result;
    }
}
