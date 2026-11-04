// =====================================================================================
// STUDENT FILE. Implement what is asked; write code ONLY between the comments
//   // Your code starts here   and   // Your code ends here
// Do NOT change the class name, the method signatures or the imports. Helper methods are
// allowed (private ones, in the helper region). Add a // comment to explain your algorithm.
// =====================================================================================
/**
 * From pixels to a digit: the last step of the project. It uses the {@link CNNModel} (already
 * built from your Matrix and your layers) to classify MNIST images and to measure how good it is.
 *
 * <p>Images are stored one per ROW of an N x 784 matrix (28*28 = 784 raw pixel values 0..255, row
 * by row), labels in an N x 1 matrix (the digit 0..9 as a number).</p>
 */
public class Classifier {

    /**
     * Task 6.1: prepares one raw image for the network: scales the pixels from 0..255 to 0..1
     * (divide by 255.0) and reshapes the 1 x 784 row into a 28 x 28 matrix.
     *
     * @param flatRow a 1 x 784 matrix of raw pixels
     * @return the 28 x 28 matrix of scaled pixels
     */
    public static Matrix preprocess(Matrix flatRow) {
        // Your code starts here
        return flatRow.div(255.0).reshape(28, 28);
        // Your code ends here
    }

    /**
     * Task 6.2: classifies one image: preprocess it, run the model, and return the digit with the
     * highest probability.
     *
     * @param model the network
     * @param flatRow a 1 x 784 matrix of raw pixels
     * @return the predicted digit 0..9
     */
    public static int predict(CNNModel model, Matrix flatRow) {
        // Your code starts here
        Matrix probabilities = model.forward(new Matrix[] {preprocess(flatRow)})[0];
        return probabilities.argmax();
        // Your code ends here
    }

    /**
     * Task 6.3: the fraction of images that are classified correctly.
     *
     * @param model the network
     * @param images an N x 784 matrix, one raw image per row
     * @param labels an N x 1 matrix with the true digit of every image
     * @return correct / N, a number between 0.0 and 1.0
     */
    public static double accuracy(CNNModel model, Matrix images, Matrix labels) {
        // Your code starts here
        int correct = 0;
        for (int i = 0; i < images.getRows(); i++) {
            if (predict(model, images.getRow(i)) == (int) labels.get(i, 0)) {
                correct++;
            }
        }
        return (double) correct / images.getRows();
        // Your code ends here
    }

    /**
     * Task 6.4: the confusion matrix: a 10 x 10 matrix whose element (t, p) counts the images whose
     * TRUE digit is t and whose PREDICTED digit is p. A perfect classifier has numbers only on the
     * diagonal.
     *
     * @param model the network
     * @param images an N x 784 matrix, one raw image per row
     * @param labels an N x 1 matrix with the true digit of every image
     * @return the 10 x 10 matrix of counts (stored as doubles)
     */
    public static Matrix confusionMatrix(CNNModel model, Matrix images, Matrix labels) {
        // Your code starts here
        // row = true digit, column = predicted digit
        Matrix confusion = new Matrix(10, 10);
        for (int i = 0; i < images.getRows(); i++) {
            int t = (int) labels.get(i, 0);
            int p = predict(model, images.getRow(i));
            confusion.set(t, p, confusion.get(t, p) + 1);
        }
        return confusion;
        // Your code ends here
    }

    /**
     * Task 6.5: prints a confusion matrix on the console as a table that a human can read. YOU design
     * the layout; {@code Main} only prints a title line and then calls this method.
     *
     * <p>Requirements: (1) print ONE LINE for every TRUE digit, in the order 0, 1, ..., 9; (2) that line
     * contains the ten counts of the row, from predicted digit 0 to predicted digit 9, as whole numbers
     * (cast the doubles to int); (3) everything else is up to you: a header line with the predicted digits,
     * row labels, separators, alignment, even totals. Tools: {@code System.out.print}, {@code println},
     * {@code printf}, {@code String.format}. Do not change the matrix.</p>
     *
     * @param confusion a 10 x 10 confusion matrix as returned by {@link #confusionMatrix}
     */
    public static void printConfusionMatrix(Matrix confusion) {
        // Your code starts here
        System.out.print(" true\\pred |");
        for (int p = 0; p < 10; p++) {
            System.out.printf("%5d", p);
        }
        System.out.println(" | total");
        System.out.println("-----------+" + "-".repeat(50) + "-+------");
        for (int t = 0; t < 10; t++) {
            System.out.printf("%10d |", t);
            int total = 0;
            for (int p = 0; p < 10; p++) {
                int count = (int) confusion.get(t, p);
                System.out.printf("%5d", count);
                total += count;
            }
            System.out.printf(" | %5d%n", total);
        }
        // Your code ends here
    }
}
