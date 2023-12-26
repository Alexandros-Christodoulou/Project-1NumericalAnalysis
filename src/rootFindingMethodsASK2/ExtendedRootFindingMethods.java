package rootFindingMethodsASK2;
import rootFindingMethodsASK1.RootFindingMethods;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class ExtendedRootFindingMethods {
    private static int maxIterations=100;
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
    // Μέθοδος τροποποιημένης διχοτόμησης
    private static int newbisectionMethod(PrintWriter writer){
        double a=-2,b=2;//όπου a και b τα άκρα του διαστήματος που δίνονται
        writer.println("Για την τροποποιημένη μέθοδο της Διχοτόμησης στο διάστημα [-1,2]:    **όπου n οι αριθμοί των επαναλήψεων");
        double x=2;//επιλογή αρχικής τιμής με 2 γιατί η παρακάτω συνάρτηση έχει εύρος τιμών απο [-2,2)
        // δηλαδή εξαιρεί το 2 αλλά με αυτόν τον τρόπο ελέγχετε και η περίπτωση για χ=2
        int i = 0;
        while (Math.abs(function(x)) >= 1e-8 && i < maxIterations) {
            writer.print("Για n=" + (i + 1) + ":    x" + i + " = ");
            writer.printf("%.8f%n", x);
            Random r1 = new Random();
            r1.setSeed(System.currentTimeMillis()); // Ρύθμιση του seed με βάση το ρολόι
            // Λήψη τυχαίου πραγματικού αριθμού στο εύρος [-2, 2)
            double randomValue = -2 + (4 * r1.nextDouble());
            //r1.nextDouble(): Αυτό επιστρέφει έναν τυχαίο πραγματικό αριθμό από το διάστημα [0, 1).
            //4 * r1.nextDouble(): Αυτός ο όρος διευρύνει το διάστημα από [0, 1) σε [0, 4).
            // -2 + (4 * r1.nextDouble()): Προσθέτουμε το αποτέλεσμα του προηγούμενου βήματος
            // στον αριθμό -2. Έτσι, ο τελικός αριθμός που θα ληφθεί βρίσκεται στο διάστημα [-2, 2).
            x=randomValue;
            i++;
        }

        if (Math.abs(function(x)) < 1e-8) {
            writer.println("Μετά από " + i + " επαναλήψεις η εκτιμώμενη ρίζα είναι : ");
            System.out.printf("%.5f%n", x);
            return i;
        } else {
            writer.println("Η μέθοδος δεν βρήκε ρίζα σε επιθυμητή ακρίβεια μετά από " + maxIterations + " επαναλήψεις.");
            return 0;
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
            x=x-(function(x)/derivativeFunction1(x));//υπολογισμός ανάδρομης ακολουθίας Xn=Xn-1 -F(Xn-1)/F'(Xn-1)
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
            x=x-(function(x)/derivativeFunction1(x));//υπολογισμός ανάδρομης ακολουθίας Xn=Xn-1 -F(Xn-1)/F'(Xn-1)
            writer.print("Για n="+(i+1)+":    x"+i+" = ");
            writer.printf("%.8f%n", x);
        }
    }
    // Μέθοδος τροποποιημένη Newton-Raphson
    private static void newNewtonRaphsonMethod(PrintWriter writer){
        double initialGuess=-2;
        double x=initialGuess;
        writer.println("Για την τροποποιημένη μέθοδο Newton-Raphson με αρχική τιμή -2(άκρο διαστήματος):    **όπου n οι αριθμοί των επαναλήψεων");
        for (int i=0;i<maxIterations;i++){// Βρόγχος που εκτελεί τις επαναλήψεις για τη μέθοδο
            if (Math.abs(function(x)) < 1e-8) {//function(x)==0){//
                //Στην Java 1e−8 είναι ίσο με 1×10^(−8), που είναι το ίδιο με το 0.00000001 δηλαδή με την επιθυμητή ακρίβεια
                writer.println("Μετά από "+(i+1)+" επαναλήψεις η εκτιμώμενη ρίζα (με ακρίβεια 5ου δεκαδικού ψηφίου) είναι : ");
                writer.printf("%.5f%n", x);
                break;
            }
            double temp1=derivativeFunction1(x)/function(x);
            double temp2=derivativeFunction2(x)/derivativeFunction1(x);
            x=x-(1/temp1-temp2);//υπολογισμός ανάδρομης ακολουθίας Xn=Xn-1 -F(Xn-1)/F'(Xn-1)
            writer.print("Για n="+(i+1)+":    x"+i+" = ");
            writer.printf("%.8f%n", x);
        }
        initialGuess=2;
        x=initialGuess;
        writer.println("Για την τροποποιημένη μέθοδο Newton-Raphson με αρχική τιμή 2(άκρο διαστήματος):    **όπου n οι αριθμοί των επαναλήψεων");
        for (int i=0;i<maxIterations;i++){// Βρόγχος που εκτελεί τις επαναλήψεις για τη μέθοδο
            if (Math.abs(function(x)) < 1e-8) {//function(x)==0){//
                //Στην Java 1e−8 είναι ίσο με 1×10^(−8), που είναι το ίδιο με το 0.00000001 δηλαδή με την επιθυμητή ακρίβεια
                writer.println("Μετά από "+(i+1)+" επαναλήψεις η εκτιμώμενη ρίζα (με ακρίβεια 5ου δεκαδικού ψηφίου) είναι : ");
                writer.printf("%.5f%n", x);
                break;
            }
            double temp1=derivativeFunction1(x)/function(x);
            double temp2=derivativeFunction2(x)/derivativeFunction1(x);
            x=x-(1/temp1-temp2);//υπολογισμός ανάδρομης ακολουθίας Xn=Xn-1 -F(Xn-1)/F'(Xn-1)
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
            x0=x1;
            x1=x;
            x=x1-((function(x1)*(x1-x0))/(function(x1)-function(x0)));//υπολογισμός ανάδρομης ακολουθίας Xn=Xn-1 -F(Xn-1)/F'(Xn-1)
            writer.print("Για n="+(i+1)+":    x"+i+" = ");
            writer.printf("%.8f ", x0);
            writer.print("και x"+(i+1)+" = ");
            writer.printf("%.8f   ", x1);
            writer.printf("x=%.5f%n", x);

        }
    }
    // Τροποιημένη μέθοδος τέμνουσας
    private static void newSecantMethod(PrintWriter writer){
        double xn=0,xn1=3,xn2=3;//όπου xn, xn+1 και xn+2 τα άκρα του διαστήματος που δίνονται
        writer.println("Για την τροποποιημένη μέθοδο Τέμνουσας με αρχικές τιμές x0=0,x1=3 (άκρα διαστήματος):    **όπου n οι αριθμοί των επαναλήψεων");
        double q=function(xn)/function(xn1);
        double r=function(xn2)/function(xn1);
        double s=function(xn2)/function(xn);
        double xn3=xn2-((r*(r-q)*(xn2-xn1)+(1-r)*s*(xn2-xn))/((q-1)*(r-1)*(s-1)));
        writer.print("Για n="+(1)+":    x"+0+" = ");
        writer.printf("%.8f ", xn);
        writer.print("και x"+(1)+" = ");
        writer.printf("%.8f ", xn1);
        writer.print("και x"+(2)+" = ");
        writer.printf("%.8f    ", xn2);
        writer.printf("x=%.5f%n", xn3);
        for (int i=0;i<maxIterations;i++){// Βρόγχος που εκτελεί τις επαναλήψεις για τη μέθοδο
            if (Math.abs(function(xn3)) < 1e-8) {//function(x)==0){//
                //Στην Java 1e−8 είναι ίσο με 1×10^(−8), που είναι το ίδιο με το 0.00000001 δηλαδή με την επιθυμητή ακρίβεια
                writer.println("Μετά από "+(i+1)+" επαναλήψεις η εκτιμώμενη ρίζα (με ακρίβεια 5ου δεκαδικού ψηφίου) είναι : ");
                writer.printf("%.5f%n", xn3);
                break;
            }
            xn=xn1;
            xn1=xn2;
            xn2=xn3;
            //υπολογισμός ανάδρομης ακολουθίας
            q=function(xn)/function(xn1);
            r=function(xn2)/function(xn1);
            s=function(xn2)/function(xn);
            xn3=xn2-((r*(r-q)*(xn2-xn1)+(1-r)*s*(xn2-xn))/((q-1)*(r-1)*(s-1)));
            writer.print("Για n="+(i+1)+":    x"+i+" = ");
            writer.printf("%.8f ", xn);
            writer.print("και x"+(i+1)+" = ");
            writer.printf("%.8f ", xn1);
            writer.print("και x"+(i+2)+" = ");
            writer.printf("%.8f    ", xn2);
            writer.printf("x=%.5f%n", xn3);

        }
    }

    private static double function(double x) {
        // Υλοποιήστε τη συνάρτηση f(x) εδώ
        return 54 * Math.pow(x,6)+45 * Math.pow(x,5) - 102 * Math.pow(x,4) - 69 * Math.pow(x, 3) + 35 * Math.pow(x, 2) - 16 * x -4;
    }

    private static double derivativeFunction1(double x) {
        // Υλοποίηση της 1ης παραγώγου της συνάρτησης f(x) εδώ
        return 324 * Math.pow(x,5) + 225 * Math.pow(x,4) - 408 * Math.pow(x,3) - 207 * Math.pow(x, 2) + 70 * x - 16;
    }
    private static double derivativeFunction2(double x) {
        //Υλοποίηση της 2ης παραγώγου της συνάρτησης f(x) εδώ
        return 1620 * Math.pow(x,4) + 900 * Math.pow(x,3) - 1224 * Math.pow(x,2) - 414 * x + 70;
    }
    public static void runAll() {
        // Ανοίγει ένα αρχείο εξόδου για εγγραφή
        try (PrintWriter writer = new PrintWriter(new FileWriter("resultsAskisi2.txt"))) {
            // Εκτύπωση στο αρχείο αντί για την κονσόλα
            writer.println("Ισχύει για όλες τις υλοποιήσεις των μεθόδων");
            writer.printf("Θεωρούμε ως επιθυμητή ακρίβεια ρίζας το 1e-8 δηλαδή όριο για να καθορίσει%n" +
                    "ο αλγόριθμος πότε συγκλίνει σε μια αποδεκτή λύση. Όσο μικρότερη είναι η τιμή του 1e-x, τόσο%n" +
                    "υψηλότερη είναι η ακρίβεια, καθώς σημαίνει ότι ο αλγόριθμος θα θεωρήσει τη λύση ακριβή όταν η τιμή%n" +
                    "της συνάρτησης είναι πολύ κοντά στο μηδέν.%n");

            // Εκτύπωση στο αρχείο αντί για την κονσόλα
            long duration1,duration2 ,duration3,duration4,duration5 ,duration6;
            duration1=0;
            duration2=0;
            duration3=0;
            duration4=0;
            duration5=0;
            duration6=0;
            long t1;
            long t2;
            writer.println("Ερώτημα 1");
            t1 = System.nanoTime();
            newbisectionMethod(writer);
            t2 = System.nanoTime();
            duration1 = t2 - t1;
            t1 = System.nanoTime();
            newNewtonRaphsonMethod(writer);
            t2 = System.nanoTime();
            duration2 = t2 - t1;
            t1 = System.nanoTime();
            newSecantMethod(writer);
            t2 = System.nanoTime();
            duration3 = t2 - t1;
            writer.println("Ερώτημα 2");
            for (int i=0;i<20;i++){// Βρόγχος που εκτελεί τις επαναλήψεις για τη μέθοδο
                writer.print("Επανάληψη"+(i+1)+":");
                int temp=newbisectionMethod(writer);
                writer.println("Αριθμός επαναλήψεων μέχρι την εύρεση της ρίζας ήταν "+temp);
            }
            writer.println("Ερώτημα 3");

            writer.println("Διάρκεια εκτέλεσης για τροποποιημένη μέθοδο της Διχοτόμησης: " + duration1 + " (nanoseconds)");
            t1 = System.nanoTime();
            bisectionMethod(writer);
            t2 = System.nanoTime();
            duration4 = t2 - t1;
            writer.println("Διάρκεια εκτέλεσης για απλή μέθοδο της Διχοτόμησης: " + duration4 + " (nanoseconds)");

            writer.println("Διάρκεια εκτέλεσης για τροποποιημένη μέθοδο Newton-Raphson: " + duration2 + " (nanoseconds)");
            t1 = System.nanoTime();
            newtonRaphsonMethod(writer);
            t2 = System.nanoTime();
            duration5 = t2 - t1;
            writer.println("Διάρκεια εκτέλεσης για απλή μέθοδο Newton-Raphson: " + duration5 + " (nanoseconds)");

            writer.println("Διάρκεια εκτέλεσης για τροποποιημένη μέθοδο της Τέμνουσας: " + duration3 + " (nanoseconds)");
            t1 = System.nanoTime();
            bisectionMethod(writer);
            t2 = System.nanoTime();
            duration6 = t2 - t1;
            writer.println("Διάρκεια εκτέλεσης για απλή μέθοδο της Τέμνουσας: " + duration6 + " (nanoseconds)");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
