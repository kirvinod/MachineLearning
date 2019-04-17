package SimpleRegression;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SimpleRegression {

    private List<Variable> variables = new ArrayList<Variable>();

    private double x;
    /**
     * Linear equation of line: y = mx + b;
     *  m =  Y-Intercept
     *  b =  Slope of line
     */
    public double predict(double x){
        this.x = x;
        double b = this.getSlope();
        double m = this.getYIntercept();
        return (m * x) + b;
    }

    /**
     *  Slope formula: b = r(Sy/Sx)
     *  b =  Slope of line
     *  r =  Pearson's correlation coefficient
     *  Sy = Standard deviation of y
     *  Sx = Standard deviation of x
     */
    private double getSlope() {

        double r = this.getCorrelationCoefficient();

        double Sx = 0;
        double Sy = 0;




        System.out.println(r);


        return 0;
    }

    private double getCorrelationCoefficient() {

        double n = this.variables.size();
        double xSum = 0;
        double ySum = 0;
        double xxSum = 0;
        double yySum = 0;
        double xySum = 0;

        for (Variable variable: this.variables) {
            xSum += variable.getX();
            ySum += variable.getY();
            xxSum += variable.getX() * variable.getX();
            yySum += variable.getY() * variable.getY();
            xySum +=  variable.getX() * variable.getY();
        }



    }

//    private double getCorrelationCoefficientTest() {
//
//        double n = this.variables.size();
//
//        double xSum = 0;
//        double ySum = 0;
//        double xxSum = 0;
//        double yySum = 0;
//
//        double xySum = 0;
//
//        for (Variable variable: this.variables) {
//            xSum += variable.getX();
//            ySum += variable.getY();
//            xxSum += variable.getX() * variable.getX();
//            yySum += variable.getY() * variable.getY();
//            xySum +=  variable.getX() * variable.getY();
//        }
//
//        // co-variation
//        double cov = xySum / n - xSum * ySum / n / n;
//
//        // standard error of x
//        double sigmax = Math.sqrt(xxSum / n -  xSum * xSum / n / n);
//
//        // standard error of y
//        double sigmay = Math.sqrt(yySum / n -  ySum * ySum / n / n);
//
//        // correlation is just a normalized co-variation
//        double r = ( cov / sigmax / sigmay);
//
//        return r;
//
//    }

    private double getYIntercept() {
        return 0;
    }


    /**
    * Read data from CSV file
     * fileName: Full path of file
     * xColPos: In csv file position of x col
     * yColPos: In csv file position of y col
     *
    */
    public void read(String fileName, int xColPos, int yColPos) {

        CSVReader csvReader;
        String[] values;
        try {
            csvReader = new CSVReader(new FileReader(fileName), ',', '\'', 1);
            while ((values = csvReader.readNext()) != null) {
                Variable variable = new Variable( Double.parseDouble(values[xColPos]),  Double.parseDouble(values[yColPos]));
                this.variables.add(variable);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
