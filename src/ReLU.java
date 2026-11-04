// =====================================================================================
// STUDENT FILE. Implement what is asked; write code ONLY between the comments
//   // Your code starts here   and   // Your code ends here
// Do NOT change the class name, the method signatures or the imports. Helper methods are
// allowed (private ones, in the helper region). Add a // comment to explain your algorithm.
// =====================================================================================
/**
 * ReLU activation: replaces every negative number by 0 and keeps the others, i.e.
 * {@code out = max(0, x)} for every element of every channel. The shape does not change.
 */
public class ReLU implements Layer {

    /**
     * Task 4.2: applies ReLU to every channel.
     *
     * @param input any number of channels of any (equal or different) shape
     * @return new channels with the same shapes; the input matrices stay untouched
     */
    @Override
    public Matrix[] forward(Matrix[] input) {
        // Your code starts here
        // copy every channel, replacing negative values with 0
        Matrix[] out = new Matrix[input.length];
        for (int c = 0; c < input.length; c++) {
            Matrix x = input[c];
            out[c] = new Matrix(x.getRows(), x.getCols());
            for (int i = 0; i < x.getRows(); i++) {
                for (int j = 0; j < x.getCols(); j++) {
                    out[c].set(i, j, Math.max(0.0, x.get(i, j)));
                }
            }
        }
        return out;
        // Your code ends here
    }
}
