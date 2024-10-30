import org.json.JSONObject;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class SecretSharing {
    public static double lagrangeInterpolation(List<double[]> points) {
        double constantTerm = 0;
        int numPoints = points.size();
        for (int i = 0; i < numPoints; i++) {
            double xi = points.get(i)[0];
            double yi = points.get(i)[1];
            double li = 1;

            for (int j = 0; j < numPoints; j++) {
                if (i != j) {
                    double xj = points.get(j)[0];
                    li *= (0 - xj) / (xi - xj);
                }
            }

            constantTerm += yi * li;
        }

        return Math.round(constantTerm);
    }

    public static void main(String[] args) {
     
        String[] inputFiles = {"testcase.json", "testcase1.json"};

        for (String fileName : inputFiles) {
            try {
            
                String content = new String(Files.readAllBytes(Paths.get(fileName)));
                JSONObject data = new JSONObject(content);

                JSONObject keys = data.getJSONObject("keys");
                int n = keys.getInt("n");
                int k = keys.getInt("k");

               
                List<double[]> points = new ArrayList<>();
                for (String key : data.keySet()) {
                    if (key.equals("keys")) continue; 
                    JSONObject pointData = data.getJSONObject(key);
                    int base = pointData.getInt("base");
                    String value = pointData.getString("value");

                    BigInteger bigY = new BigInteger(value, base);
                    double y = bigY.doubleValue(); 
                    points.add(new double[]{Double.parseDouble(key), y});
                }

                double secret = lagrangeInterpolation(points);
                System.out.println("The secret constant term (c) for " + fileName + " is: " + secret);

            } catch (IOException e) {
                System.err.println("Error reading file " + fileName + ": " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Error processing file " + fileName + ": " + e.getMessage());
            }
        }
    }
}
