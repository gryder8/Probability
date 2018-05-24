package pkg;




import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

//TODO: Double chec results
//TODO: Bug testing


public class Main {
    public static Scanner globalScan = new Scanner (System.in);
    public static double p;
    public static double q;
    public static double biProbResult = 0;
    private static double result;
    private static double n;
    private static double X;
    private static DecimalFormat df = new DecimalFormat("#.###");
    private static DecimalFormat df2 = new DecimalFormat("##.#");

    private static void choice(){
        int c;
        Scanner choice = new Scanner (System.in);
        System.out.println("Enter 1 for Simulation. Enter 2 for Binomial Probability");
        c = choice.nextInt();
        if(c == 1) {
            probability();
        } else if (c==2) {
            nCrExecution();
        } else {
            System.out.println("Invalid option.");
            choice();
        }
    }
    private static double nCr (double x, double y){
        double rfact=1;
        double nfact=1;
        double nrfact=1;
        double temp1 = x-y;
        double temp2 = y;
        if(y>x-y)
        {
            temp1 =y;
            temp2 =x-y;
        }
        for(int i=1;i<=x;i++)
        {
            if(i<=temp2)
            {
                rfact *= i;
                nrfact *= i;
            }
            else if(i<=temp1)
            {
                nrfact *= i;
            }
            nfact *= i;
        }
        return nfact/(int)(rfact*nrfact);
    }



    private static void probability() {
        Scanner in = new Scanner(System.in);
        double sampleSize;
        double repeat;
        int randInt;
        int YES = 0;
        int NO = 0;
        int MID = 0;

        System.out.println("Sample size:");
        sampleSize = in.nextInt();
        System.out.println("Number of iterations:");
        repeat = in.nextInt();
        long[][] nums = new long[(int)repeat][(int)sampleSize];
        for (int i = 0; i < repeat; i++) {
            for (int n = 0; n < sampleSize; n++) {
                randInt = ThreadLocalRandom.current().nextInt(0, 100);
                if (randInt > 50) {
                    YES++;
                } else if (randInt < 50) {
                    NO++;
                } else {
                    MID++;
                }
                nums[i][n] = (long)randInt;
            }
        }

        //System.out.println(nums[1][43]); //[iteration number][number picked]
        double totalSize = sampleSize*repeat;
        System.out.println("Yes: " + YES+"("+df2.format((YES/totalSize)*100)+"%)");
        System.out.println("Undecided: "+MID+"("+df2.format((MID/totalSize)*100)+"%)");
        System.out.println("No:" + NO+"("+df2.format((NO/totalSize)*100)+"%)");
        System.out.println("");
        choice();
    }

    private static void nCrExecution () {
        String localInput;
        Scanner sc = new Scanner(System.in);
        System.out.println("Using at least? y/n");
        localInput = sc.next();
        if (localInput.equals("y")) {
            atLeast();
        } else if (localInput.equalsIgnoreCase("n")) {
            System.out.println("Using no more than? y/n");
            localInput = sc.next();
                if (localInput.equalsIgnoreCase("y")) {
                    noMoreThan();
                } else if (localInput.equalsIgnoreCase("n")){
                    System.out.println("Input n:");
                    n = sc.nextDouble();
                    if (n*1!=n) {
                        nCrExecution();
                    }
                    System.out.println("Input X:");
                    X = sc.nextDouble();
                    if (X*1!=X) {
                        nCrExecution();
                    }
                    System.out.println("Input p (as a decimal):");
                    p = globalScan.nextDouble();
                    if (p*1!=p) {
                        nCrExecution();
                    }
                    q = 1-p;
                    result = nCr(n,X);
                    biProbFormEz();
                } else {
                    System.out.println("Invalid input");
                    nCrExecution();
                }
        } else {
            System.out.println("Invalid input");
            nCrExecution();
        }

        //System.out.println("Result of "+n+" C "+X+" is "+result);
    }

    private static double  biProbFormEz(){
        //Scanner scan = new Scanner(System.in);
        biProbResult = biProbResult + (result)*(Math.pow(p,X))*(Math.pow(q, n-X));
        //if(biProbResult<=1) {
            System.out.println("The result is: " + df.format(biProbResult));
        /*} else {
            System.out.println("Invalid inputs. Please try again");
            biProbForm();
        }*/

        System.out.println("");
        choice();
        return biProbResult;
    }
    private static void atLeast() {
        System.out.println("n is at least:");
        n = globalScan.nextDouble();
        if (n*1!=n){
            atLeast();
        }
        System.out.println("Input X:");
        X = globalScan.nextDouble();
        if (X*1!=X){
            atLeast();
        }
        System.out.println("Input p (as a decimal):");
        p = globalScan.nextDouble();
        if (p*1!=p) {
            atLeast();
        }
        q = 1-p;
        result = nCr(n,X);
        biProbResult = biProbResult + (result)*(Math.pow(p,X))*(Math.pow(q, n-X));
        for (int i = 1; i<Math.abs(X-n); i++) {
            biProbResult = biProbResult + (result)*(Math.pow(p,X))*(Math.pow(q, n+i-X));
        }
        System.out.println("The result is: " + df.format(biProbResult));
        System.out.println("");
        choice();
    }

    private static void noMoreThan() {
        System.out.println("n is no more than:");
        n = globalScan.nextDouble();
        if (n*1!=n){
            noMoreThan();
        }
        System.out.println("Input X:");
        X = globalScan.nextDouble();
        if (X*1!=X){
            noMoreThan();
        }
        System.out.println("Input p (as a decimal):");
        p = globalScan.nextDouble();
        if (p*1!=p) {
            noMoreThan();
        }
        q = 1-p;
        result = nCr(n,X);
        biProbResult = biProbResult + (result)*(Math.pow(p,X))*(Math.pow(q, n-X));
        for (int i = (int)n-1; i>0; i--) {
            biProbResult = biProbResult + (result)*(Math.pow(p,X))*(Math.pow(q, n+i-X));
        }
        System.out.println("The result is: " + df.format(biProbResult));
        System.out.println("");
        choice();
    }

    public static void main(String[] args) {
        choice();
    }
}