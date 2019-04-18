package SimpleRegression;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class SimpleRegression {

    private List<Variable> variables = new ArrayList<Variable>();

    private double xMean;
    private double yMean;

    private double m = 1;
    private double b = 0;

    public void train() {


        double learningRate = 0.05;

        for (Variable variable: this.variables) {

            double x = variable.getX();
            double y = variable.getY();

            double guess = this.m * x + this.b;

            double error = y - guess;

            this.m = this.m + (error * x) * learningRate;
            this.b = this.b + (error * learningRate);


            System.out.println(this.m);

        }




    }



    /**
     * Linear equation of line: y = mx + b;
     *  m =  slope of line
     *  b =  Y-Intercept
     */
    public double predict(double x){
        double m = this.getSlope();
        double b = this.getYIntercept(m);
        return (m * x) + b;
    }

    /**
     *  Slope formula: m = r(Sy/Sx)
     *  m =  Slope of line
     *  r =  Pearson's correlation coefficient
     *  Sy = Standard deviation of y
     *  Sx = Standard deviation of x
     */
    private double getSlope() {

        double r = this.getCorrelationCoefficient();

        double xSumSqr = 0;
        double ySumSqr = 0;

        for (Variable variable: this.variables) {
            xSumSqr += (variable.getX() - this.xMean) * (variable.getX() - this.xMean);
            ySumSqr += (variable.getY() - this.yMean) * (variable.getY() - this.yMean);
        }

        double Sx = Math.sqrt(xSumSqr/9);
        double Sy = Math.sqrt(ySumSqr/9);

        return r*(Sy/Sx);
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

        //these vars we need later
        this.xMean = xSum/n;
        this.yMean = ySum/n;

        double m1 = (n*xySum) - (xSum*ySum);
        double m2 = Math.sqrt(((n*xxSum) - (xSum*xSum)) * ((n*yySum) - (ySum*ySum)));

        return (m1/m2);
    }

    /**
     * formula a = mean of y - (mean of x * slope) ie. a = Ym - Xm * b;
     */
    private double getYIntercept(double slope) {
        return yMean - (xMean * slope);
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
