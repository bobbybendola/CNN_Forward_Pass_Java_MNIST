// =====================================================================================
// PROVIDED FILE - DO NOT MODIFY. (The autograder uses its own copy of this file.)
// =====================================================================================
/**
 * Your personal number for the report (PROVIDED - do not modify).
 *
 * <p>Run it with the digits of your UCI student ID as the program argument, for example
 * {@code java -cp out ReportHelper 12345678} (IntelliJ: Run > Edit Configurations > Program arguments).
 * It prints the index of the test image you must use in report question 1. Everybody gets a different
 * number; the staff can recompute yours.</p>
 */
public class ReportHelper {
    private static long state;

    /** A tiny deterministic generator (same formula as the staff's Python script). */
    private static int next(int bound) {
        state = (state * 1103515245L + 12345L) & 0x7fffffffL;
        return (int) ((state >> 16) % bound);
    }

    /**
     * Entry point.
     *
     * @param args the digits of your UCI student ID
     */
    public static void main(String[] args) {
        String digits = args[0].replaceAll("[^0-9]", "");
        if (digits.length() > 9) {
            digits = digits.substring(digits.length() - 9);
        }
        state = Long.parseLong(digits) % 2147483648L;
        for (int i = 0; i < 5; i++) {
            next(1); // warm-up
        }
        System.out.println("Report numbers for ID " + digits);
        System.out.println("Question 1: use test image index " + next(200));
    }
}
