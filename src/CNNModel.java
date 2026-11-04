// =====================================================================================
// PROVIDED FILE - DO NOT MODIFY. (The autograder uses its own copy of this file.)
// =====================================================================================
import java.io.IOException;

/**
 * The whole network (PROVIDED - do not modify). This class only wires the pieces together:
 *
 * <pre>
 *   image 28x28 -> Conv2D (4 kernels 3x3) -> ReLU -> MaxPool2D(2) -> Flatten -> Linear(676 -> 10) -> Softmax
 *   shapes:        1 x 28x28      4 x 26x26       4 x 26x26    4 x 13x13    1 x 676       1 x 10        1 x 10
 * </pre>
 *
 * <p>The numbers (weights) come from four text files in the weights directory. They were trained
 * in Python and are loaded with YOUR {@code Matrix.load}.</p>
 */
public class CNNModel {

    /** The layers, applied in this order. */
    private final Layer[] layers;

    /**
     * Loads the weights and builds the layers.
     *
     * @param weightDir directory with conv_w.txt (4 x 9), conv_b.txt (1 x 4), fc_w.txt (10 x 676)
     *                  and fc_b.txt (1 x 10)
     * @throws IOException if a weight file cannot be read
     */
    public CNNModel(String weightDir) throws IOException {
        Matrix convW = Matrix.load(weightDir + "/conv_w.txt"); // row k = kernel k, 9 numbers
        Matrix convB = Matrix.load(weightDir + "/conv_b.txt"); // 1 x 4
        Matrix fcW = Matrix.load(weightDir + "/fc_w.txt");     // 10 x 676
        Matrix fcB = Matrix.load(weightDir + "/fc_b.txt");     // 1 x 10

        int k = convW.getRows();
        Matrix[] kernels = new Matrix[k];
        double[] bias = new double[k];
        for (int i = 0; i < k; i++) {
            kernels[i] = convW.getRow(i).reshape(3, 3); // 1 x 9 row -> 3 x 3 kernel
            bias[i] = convB.get(0, i);
        }

        layers = new Layer[] {
            new Conv2D(kernels, bias),
            new ReLU(),
            new MaxPool2D(2),
            new Flatten(),
            new Linear(fcW, fcB),
            new Softmax()
        };
    }

    /**
     * Runs the image through all layers.
     *
     * @param x the channels of the input (one 28 x 28 matrix for an MNIST digit)
     * @return an array with ONE 1 x 10 matrix of class probabilities
     */
    public Matrix[] forward(Matrix[] x) {
        for (Layer layer : layers) {
            x = layer.forward(x);
        }
        return x;
    }
}
