// =====================================================================================
// STUDENT FILE. Implement what is asked; write code ONLY between the comments
//   // Your code starts here   and   // Your code ends here
// Do NOT change the class name, the method signatures or the imports. Helper methods are
// allowed (private ones, in the helper region). Add a // comment to explain your algorithm.
// =====================================================================================
/**
 * 2-D convolution layer for a ONE-channel input (an MNIST digit is a grey-scale image).
 *
 * <p>The layer owns K small "kernels" (filters), here 3 x 3 each, and K biases. Output channel k
 * is obtained by sliding kernel k over the image (stride 1, no padding). For the output element
 * at (i, j) multiply the kernel element-wise with the window of the image whose top-left corner
 * is (i, j), add all the products, then add bias k:</p>
 *
 * <pre>
 *   out_k[i][j] = sum over (a, b) of  image[i + a][j + b] * kernel_k[a][b]  +  bias[k]
 * </pre>
 *
 * <p>A kh x kw kernel on an H x W image therefore gives a (H-kh+1) x (W-kw+1) output
 * (28 x 28 with a 3 x 3 kernel gives 26 x 26). In Python/numpy the same thing is
 * {@code (image[i:i+3, j:j+3] * kernel).sum() + bias}. Use {@code slice}, {@code hadamard}, {@code sum}.</p>
 */
public class Conv2D implements Layer {

    // Self-defined attributes: remember the kernels and the biases.
    // Your code starts here
    private Matrix[] kernels;
    private double[] bias;
    // Your code ends here

    /**
     * Task 4.1a: stores the parameters.
     *
     * @param kernels the K kernels, all of the same shape (for example K matrices of 3 x 3)
     * @param bias the K biases; bias[k] belongs to kernels[k]
     */
    public Conv2D(Matrix[] kernels, double[] bias) {
        // Your code starts here
        this.kernels = kernels;
        this.bias = bias;
        // Your code ends here
    }

    /**
     * Task 4.1b: convolves the image with every kernel.
     *
     * @param input an array with exactly ONE matrix: the H x W image
     * @return an array with K matrices (one per kernel), each (H-kh+1) x (W-kw+1)
     */
    @Override
    public Matrix[] forward(Matrix[] input) {
        // Your code starts here
        // slide each kernel over the image: window (hadamard) kernel, summed, plus bias
        Matrix image = input[0];
        Matrix[] out = new Matrix[kernels.length];
        for (int k = 0; k < kernels.length; k++) {
            int kh = kernels[k].getRows();
            int kw = kernels[k].getCols();
            int outRows = image.getRows() - kh + 1;
            int outCols = image.getCols() - kw + 1;
            out[k] = new Matrix(outRows, outCols);
            for (int i = 0; i < outRows; i++) {
                for (int j = 0; j < outCols; j++) {
                    Matrix window = image.slice(i, i + kh, j, j + kw);
                    out[k].set(i, j, window.hadamard(kernels[k]).sum() + bias[k]);
                }
            }
        }
        return out;
        // Your code ends here
    }
}
