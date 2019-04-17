package SimpleRegression;

public class App {

    public static void main(String[] args) {

        SimpleRegression simpleRegression = new SimpleRegression();

        simpleRegression.read("Datasets/Linear_Regression/Basic.csv", 0 , 1);

        //System.out.println("Prediction where X = 15, Y is: ");
        simpleRegression.predict(15);
    }



}
