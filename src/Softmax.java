// =====================================================================================
// STUDENT FILE. Implement what is asked; write code ONLY between the comments
//   // Your code starts here   and   // Your code ends here
// Do NOT change the class name, the method signatures or the imports. Helper methods are
// allowed (private ones, in the helper region). Add a // comment to explain your algorithm.
// =====================================================================================
/**
 * Softmax: turns the 10 scores (logits) into probabilities that are positive and sum to 1.
 *
 * <pre>
 *   p[j] = exp(x[j] - m) / sum over t of exp(x[t] - m)       where m = the largest x
 * </pre>
 *
 * <p>Subtracting m changes nothing mathematically but keeps exp() from overflowing: exp(1000) is
 * infinity, while exp(1000 - 1002) is fine. You may use {@code Math.exp}.</p>
 */
public class Softmax implements Layer {

    /**
     * Task 5.3: applies softmax to the single row of the input.
     *
     * @param input an array with ONE 1 x N matrix
     * @return an array with ONE 1 x N matrix of probabilities
     */
    @Override
    public Matrix[] forward(Matrix[] input) {
        // Your code starts here
        // subtract the max before exp() to avoid overflow, then normalize
        Matrix x = input[0];
        double m = x.max();
        Matrix out = new Matrix(1, x.getCols());
        double total = 0.0;
        for (int j = 0; j < x.getCols(); j++) {
            double e = Math.exp(x.get(0, j) - m);
            out.set(0, j, e);
            total += e;
        }
        return new Matrix[] {out.div(total)};
        // Your code ends here
    }
}
