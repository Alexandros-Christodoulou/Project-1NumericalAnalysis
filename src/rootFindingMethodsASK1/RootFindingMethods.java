package rootFindingMethodsASK1;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

//import java.util.function.Function;
public class RootFindingMethods {
    private static int maxIterations=1000;
//    public RootFindingMethods(){
//        runAll();
//    }
    // Μέθοδος διχοτόμησης
    private static void bisectionMethod(PrintWriter writer) {
        double a=0,b=3;//όπου a και b τα άκρα του διαστήματος που δίνονται
        writer.println("Για την μέθοδο της Διχοτόμησης στο διάστημα [0,3]:    **όπου n οι αριθμοί των επαναλήψεων");
        double m;
        m=(a+b)/2;
        writer.print("Ι0 = ("+a+","+b+") με m0 = ");
        writer.printf("%f%n", m);
        for (int i=0;i<maxIterations;i++){
            if(function(m)*function(a)>0){
                a=m;
            } else if (function(m)*function(a)<0) {
                b=m;
            } else if (Math.abs(function(m)) < 1e-8) {
                //Στη Java 1e−8 είναι ίσο με 1×10^(−8), που είναι το ίδιο με το 0.00000001 δηλαδή με την επιθυμητή ακρίβεια
                writer.println("Μετά από "+(i+1)+" επαναλήψεις η εκτιμώμενη ρίζα (με ακρίβεια 5ου δεκαδικού ψηφίου) είναι : ");
                writer.printf("%.5f%n", m);
                break;
            }

            m=(a+b)/2;
            writer.print("Για n="+(i+1)+":    ");
            writer.printf("Ι%d = (%f,%f) με m%d = %.8f%n", (i+1), a, b, (i+1), m);
        }
    }

    // Μέθοδος Newton-Raphson
    private static void newtonRaphsonMethod(PrintWriter writer) {
        double initialGuess=0;
        double x=initialGuess;
        writer.println("Για την μέθοδο Newton-Raphson με αρχική τιμή 0(άκρο διαστήματος):    **όπου n οι αριθμοί των επαναλήψεων");
        for (int i=0;i<maxIterations;i++){// Βρόγχος που εκτελεί τις επαναλήψεις για τη μέθοδο
            if (Math.abs(function(x)) < 1e-8) {//function(x)==0){//
                //Στην Java 1e−8 είναι ίσο με 1×10^(−8), που είναι το ίδιο με το 0.00000001 δηλαδή με την επιθυμητή ακρίβεια
                writer.println("Μετά από "+(i+1)+" επαναλήψεις η εκτιμώμενη ρίζα (με ακρίβεια 5ου δεκαδικού ψηφίου) είναι : ");
                writer.printf("%.5f%n", x);
                break;
            }
            x=x-(function(x)/derivativeFunction(x));//υπολογισμός ανάδρομης ακολουθίας Xn=Xn-1 -F(Xn-1)/F'(Xn-1)
            writer.print("Για n="+(i+1)+":    x"+i+" = ");
            writer.printf("%.8f%n", x);
        }
        initialGuess=3;
        x=initialGuess;
        writer.println("Για την μέθοδο Newton-Raphson με αρχική τιμή 3(άκρο διαστήματος):    **όπου n οι αριθμοί των επαναλήψεων");
        for (int i=0;i<maxIterations;i++){// Βρόγχος που εκτελεί τις επαναλήψεις για τη μέθοδο
            if (Math.abs(function(x)) < 1e-8) {//function(x)==0){//
                //Στην Java 1e−8 είναι ίσο με 1×10^(−8), που είναι το ίδιο με το 0.00000001 δηλαδή με την επιθυμητή ακρίβεια
                writer.println("Μετά από "+(i+1)+" επαναλήψεις η εκτιμώμενη ρίζα (με ακρίβεια 5ου δεκαδικού ψηφίου) είναι : ");
                writer.printf("%.5f%n", x);
                break;
            }
            x=x-(function(x)/derivativeFunction(x));//υπολογισμός ανάδρομης ακολουθίας Xn=Xn-1 -F(Xn-1)/F'(Xn-1)
            writer.print("Για n="+(i+1)+":    x"+i+" = ");
            writer.printf("%.8f%n", x);
        }
    }

    // Μέθοδος τέμνουσας
    private static void secantMethod(PrintWriter writer) {
        double x0=0,x1=3;//όπου x0 και x1 τα άκρα του διαστήματος που δίνονται
        double x=x1;
        writer.println("Για την μέθοδο Τέμνουσας με αρχικές τιμές x0=0,x1=3 (άκρα διαστήματος):    **όπου n οι αριθμοί των επαναλήψεων");
        x=x1-((function(x1)*(x1-x0))/(function(x1)-function(x0)));//υπολογισμός ανάδρομης ακολουθίας Xn=Xn-1 -F(Xn-1)/F'(Xn-1)
        writer.print("Για n="+(1)+":    x"+0+" = ");
        writer.printf("%.8f ", x0);
        writer.print("και x"+(1)+" = ");
        writer.printf("%.8f%n  ", x1);
        writer.printf("x=%.5f%n", x);
        for (int i=1;i<=maxIterations;i++){// Βρόγχος που εκτελεί τις επαναλήψεις για τη μέθοδο
            if (Math.abs(function(x)) < 1e-8) {//function(x)==0){//
                //Στην Java 1e−8 είναι ίσο με 1×10^(−8), που είναι το ίδιο με το 0.00000001 δηλαδή με την επιθυμητή ακρίβεια
                writer.println("Μετά από "+(i+1)+" επαναλήψεις η εκτιμώμενη ρίζα (με ακρίβεια 5ου δεκαδικού ψηφίου) είναι : ");
                writer.printf("%.5f%n", x);
                break;
            }
            writer.print("Για n="+(i+1)+":    x"+i+" = ");
            writer.printf("%.8f ", x0);
            writer.print("και x"+(i+1)+" = ");
            writer.printf("%.8f   ", x1);
            x0=x1;
            x1=x;
            x=x1-((function(x1)*(x1-x0))/(function(x1)-function(x0)));//υπολογισμός ανάδρομης ακολουθίας Xn=Xn-1 -F(Xn-1)/F'(Xn-1)
            writer.printf("x=%.5f%n", x);

        }
    }
    private static double function(double x) {
        // Υλοποιήστε τη συνάρτηση f(x) εδώ
        return 14 * x * Math.exp(x - 2) - 12 * Math.exp(x - 2) - 7 * Math.pow(x, 3) + 20 * Math.pow(x, 2) - 26 * x + 12;
    }

    private static double derivativeFunction(double x) {
        //Υλοποίηση της 1ης παραγώγου της συνάρτησης f(x) εδώ
        return 14 * Math.exp(x - 2) + 14 * x * Math.exp(x - 2) - 12 * Math.exp(x - 2) - 21 * Math.pow(x, 2) + 40 * x - 26;
    }
    public static void runAll() {
        // Ανοίγει ένα αρχείο εξόδου για εγγραφή
        try (PrintWriter writer = new PrintWriter(new FileWriter("resultsAskisi1.txt"))) {
            // Εκτύπωση στο αρχείο αντί για την κονσόλα
            writer.println("Ισχύει για όλες τις υλοποιήσεις των μεθόδων");
            writer.printf("Θεωρούμε ως επιθυμητή ακρίβεια ρίζας το 1e-8 δηλαδή όριο για να καθορίσει%n" +
                    "ο αλγόριθμος πότε συγκλίνει σε μια αποδεκτή λύση. Όσο μικρότερη είναι η τιμή του 1e-x, τόσο%n" +
                    "υψηλότερη είναι η ακρίβεια, καθώς σημαίνει ότι ο αλγόριθμος θα θεωρήσει τη λύση ακριβή όταν η τιμή%n" +
                    "της συνάρτησης είναι πολύ κοντά στο μηδέν.%n");

            // Εκτύπωση στο αρχείο αντί για την κονσόλα
            bisectionMethod(writer);
            newtonRaphsonMethod(writer);
            secantMethod(writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
//public static void main(String[] args) {
//
//    bisectionMethod();
//    newtonRaphsonMethod();
//    secantMethod();
//
//}
