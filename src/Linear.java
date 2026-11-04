// =====================================================================================
// STUDENT FILE. Implement what is asked; write code ONLY between the comments
//   // Your code starts here   and   // Your code ends here
// Do NOT change the class name, the method signatures or the imports. Helper methods are
// allowed (private ones, in the helper region). Add a // comment to explain your algorithm.
// =====================================================================================
/**
 * Fully connected (linear) layer: {@code y = x * W^T + b}.
 *
 * <p>The weight matrix has one ROW per output number: W is (outputs x inputs). The input is a single
 * 1 x inputs row, the bias is a 1 x outputs row, and the result is a 1 x outputs row
 * (here 676 inputs and 10 outputs: one score per digit 0..9). Use {@code transpose}, {@code mul}, {@code add}.</p>
 */
public class Linear implements Layer {

    // Self-defined attributes
    // Your code starts here
    private Matrix weightT;
    private Matrix bias;
    // Your code ends here

    /**
     * Task 5.2a: stores the parameters.
     *
     * @param weight the (outputs x inputs) weight matrix
     * @param bias the (1 x outputs) bias row
     */
    public Linear(Matrix weight, Matrix bias) {
        // Your code starts here
        // store W^T once so forward() does not transpose on every call
        this.weightT = weight.transpose();
        this.bias = bias;
        // Your code ends here
    }

    /**
     * Task 5.2b: computes x * W^T + b.
     *
     * @param input an array with ONE 1 x inputs matrix
     * @return an array with ONE 1 x outputs matrix
     */
    @Override
    public Matrix[] forward(Matrix[] input) {
        // Your code starts here
        return new Matrix[] {input[0].mul(weightT).add(bias)};
        // Your code ends here
    }
}
