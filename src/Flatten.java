// =====================================================================================
// STUDENT FILE. Implement what is asked; write code ONLY between the comments
//   // Your code starts here   and   // Your code ends here
// Do NOT change the class name, the method signatures or the imports. Helper methods are
// allowed (private ones, in the helper region). Add a // comment to explain your algorithm.
// =====================================================================================
/**
 * Flatten: turns all channels into ONE row vector so a fully connected layer can use it.
 *
 * <p>Each channel is unrolled in row-major order into a 1 x (H*W) row, and the rows of channel 0,
 * channel 1, ... are placed one after another (channel-major). Two 2 x 3 channels
 * A = (1,2,3 / 4,5,6) and B = (7,8,9 / 10,11,12) give the single 1 x 12 row
 * (1,2,3,4,5,6,7,8,9,10,11,12). Python: {@code np.concatenate([c.reshape(-1) for c in channels])}.</p>
 */
public class Flatten implements Layer {

    /**
     * Task 5.1: flattens and concatenates the channels.
     *
     * @param input one or more channels
     * @return an array holding ONE 1 x (sum of H*W over all channels) matrix
     */
    @Override
    public Matrix[] forward(Matrix[] input) {
        // Your code starts here
        // reshape each channel to one row and stack them side by side
        Matrix row = input[0].reshape(1, input[0].getRows() * input[0].getCols());
        for (int c = 1; c < input.length; c++) {
            row = row.hstack(input[c].reshape(1, input[c].getRows() * input[c].getCols()));
        }
        return new Matrix[] {row};
        // Your code ends here
    }
}
