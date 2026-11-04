// =====================================================================================
// STUDENT FILE. Implement what is asked; write code ONLY between the comments
//   // Your code starts here   and   // Your code ends here
// Do NOT change the class name, the method signatures or the imports. Helper methods are
// allowed (private ones, in the helper region). Add a // comment to explain your algorithm.
// =====================================================================================
/**
 * Max pooling: shrinks every channel by keeping only the largest value of each square block.
 *
 * <p>The blocks are size x size, do not overlap (stride = size) and start at the top-left corner.
 * If the height (or width) is not a multiple of size, the last incomplete rows (columns) are
 * ignored: an H x W channel becomes (H / size) x (W / size) with integer division. For size 2,
 * 26 x 26 becomes 13 x 13 and 5 x 5 becomes 2 x 2. Python: {@code x[i*s:(i+1)*s, j*s:(j+1)*s].max()}.</p>
 */
public class MaxPool2D implements Layer {

    // Self-defined attributes
    // Your code starts here
    private int size;
    // Your code ends here

    /**
     * Task 4.3a: stores the block size.
     *
     * @param size side length of the pooling block (2 in this homework)
     */
    public MaxPool2D(int size) {
        // Your code starts here
        this.size = size;
        // Your code ends here
    }

    /**
     * Task 4.3b: pools every channel.
     *
     * @param input any number of channels
     * @return new channels, each (H / size) x (W / size)
     */
    @Override
    public Matrix[] forward(Matrix[] input) {
        // Your code starts here
        // each output cell is the max of a non-overlapping size x size block
        Matrix[] out = new Matrix[input.length];
        for (int c = 0; c < input.length; c++) {
            int outRows = input[c].getRows() / size;
            int outCols = input[c].getCols() / size;
            out[c] = new Matrix(outRows, outCols);
            for (int i = 0; i < outRows; i++) {
                for (int j = 0; j < outCols; j++) {
                    Matrix block = input[c].slice(i * size, (i + 1) * size, j * size, (j + 1) * size);
                    out[c].set(i, j, block.max());
                }
            }
        }
        return out;
        // Your code ends here
    }
}
