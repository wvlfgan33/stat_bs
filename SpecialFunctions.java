import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpecialFunctions {

    private static final int N = 6;

    private static final BigDecimal EULER_MASCHERONI = BigDecimal.valueOf(0.5772156649015328606065120900824024310421593359399235988057672348848677267776646709369470632917467495146314472498070824809605040144865428362241739976449235362535003337429373377376739427925952582470949160087352039481656708532331517766115286211995015079847937450857057400299213547861466940296043254215190587755352673313992540129674205137541395491116851028079842348775872050384310939973613725530608893312676001724795378367592713515772261027349291394079843010341777177808815495706610750101619166334015227893586796549725203621287922655595366962817638879272680132431010476505963703947394957638906572967929601009015125195950922243501409349871228247949747195646976318506676129063811051824197444867836380861749455169892792301877391072945781554316005002182844096053772434203285478367015177394398700302370339518328690001558193988042707411542227819716523011073565833967348717650491941812300040654693142999297779569303100503086303418569803231083691640025892970890985486825777364288253954925873629596133298574739302);
    /**
     * Calculates the error function erf(x)
     * @param x a number
     * @return erf(x)
     */
    public static BigDecimal erf(BigDecimal x) {
        final int p = 100; //precision--the bigger the better.
        final BigDecimal s = BigDecimal.valueOf(2/Math.sqrt(Math.PI)); //a scaling factor
        BigDecimal result = BigDecimal.valueOf(0);

        for (int i=0; i<=p; i++) {
            BigDecimal numerator = (BigDecimal.valueOf(-1).pow(i)).multiply(x.pow(2*i+1));
            BigDecimal denominator = ((BigDecimal.valueOf(2).multiply(BigDecimal.valueOf(i))).add(BigDecimal.ONE)).multiply(factorial(i));
            result = result.add(numerator.divide(denominator, N, RoundingMode.HALF_UP));
        }
        return result.multiply(s);
    }

    public static BigDecimal exp(BigDecimal x) {
        int p = 75;
        BigDecimal result = BigDecimal.ZERO;

        for (int i=0; i<=p; i++) {
            result = result.add(x.pow(i).divide(factorial(i), N, RoundingMode.HALF_UP));
        }

        return result;
    }
    
    /**
     * Calculates the gamma function evaluated at x using the 
     * @param x A real number... hopefully within the domain of the gamma function. 
     * @return The gamma function evaluated at x.
     */
    
    
    /**
     * Calculates the factorial of n.
     * @param n A nonnegative integer.
     * @return The number n!, n factorial. In BigDecimal.
     */
    public static BigDecimal factorial(int n) {
        if (n==0) {
            return BigDecimal.ONE;
        }
        if (n <= 2) {
            return BigDecimal.valueOf(n);
        }
        return (BigDecimal.valueOf(n)).multiply(factorial(n-1));
    }

}