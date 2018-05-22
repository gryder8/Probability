package pkg;




import java.text.DecimalFormat;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;



public class Main {
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
            biProbForm();
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
        Scanner sc = new Scanner(System.in);
        System.out.println("Input n:");
        n = sc.nextDouble();
        System.out.println("Input X:");
        X = sc.nextDouble();
        result = nCr(n,X);
        //System.out.println("Result of "+n+" C "+X+" is "+result);
    }

    private static double  biProbForm(){
        //System.out.println("The Binomial Probability Formula");
        nCrExecution();
        Scanner scan = new Scanner(System.in);
        System.out.println("Input p (as a decimal):");
        double p = scan.nextDouble();
        //System.out.println("Input q (as a decimal)");
        //double q = scan.nextDouble();
        //System.out.println(df.format(p));
        double q = 1-p;
        double biProbResult = (result)*(Math.pow(p,X))*(Math.pow(q, n-X));
        if(biProbResult<=1) {
            System.out.println("The result is: " + df.format(biProbResult));
        } else {
            System.out.println("Invalid inputs. Please try again");
            biProbForm();
        }
        System.out.println("");
        choice();
        return biProbResult;
    }
    public static void main(String[] args) {
        choice();
    }
}
