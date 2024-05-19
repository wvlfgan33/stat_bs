public class Special {

    //I only care about nine decimal digits.

    public static double factorial(double n) {
        if (n<0 || n%1!=0) {
            throw new ArithmeticException("Not defined! What the fuck??"); 
        }
        if (n==0 || n==1) {
            return 1;
        }
        return n*factorial(n-1);
    }

    public static int alternate(int i) {
        if (i%2==0) {
            return 1;
        } else {
            return -1;
        }
    }

    public static double erf(double x) { 
        if (x > 4) {
            return 0.9999999990;
        } 
        if (x < -4) {
            return -0.9999999990;
        }

        double result = 0;
        for (int i=0; i<=100; i++) {
            result += alternate(i)*Math.pow(x,2*i+1)/(factorial(i)*(2*i+1));
        }

        return 2*result/Math.sqrt(Math.PI);
    }

    public static double gamma(double z) {
        if (z%1==0 && z>=1) {
            return factorial(z-1);
        }

        double g=8;
        double n=12;
        double[] p = {
            0.9999999999999999298,
            1975.3739023578852322,
            -4397.3823927922428918,
            3462.6328459862717019,
            -1156.9851431631167820,
            154.53815050252775060,
            -6.2536716123689161798,
            0.034642762454736807441,
            -7.4776171974442977377e-7,
            6.3041253821852264261e-8,
            -2.7405717035683877489e-8,
            4.0486948817567609101e-9
        };
    
        double y;

        if (z < 0.5) {
            y = Math.PI/(gamma(1-z)*Math.sin(Math.PI*z));
        } else {
            z-=1;
            double x=p[0];
            for (int i=1; i<p.length;i++) {
                x+=p[i]/(z+i);
            }
            double t=z+g+0.5;
            y = x*Math.exp(-t)*Math.pow(t,z+0.5)*Math.sqrt(2*Math.PI);
        }

        return y;
    }
}