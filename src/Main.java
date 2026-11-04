// =====================================================================================
// PROVIDED FILE - DO NOT MODIFY. (The autograder uses its own copy of this file.)
// =====================================================================================
import java.io.IOException;

/**
 * Program entry point (PROVIDED - do not modify). Loads the network and the test images, shows a few
 * digits as ASCII art with the network's answer, and prints the accuracy and the confusion matrix.
 *
 * <p>Run it (after finishing all tasks) with no arguments, or give the data directory and, optionally, the
 * index of ONE test image to show: {@code java Main}, {@code java Main data}, {@code java Main data 17}.</p>
 */
public class Main {

    /** Characters used to draw a pixel, from dark to bright. */
    private static final String SHADES = " .:-=+*#%@";

    /**
     * Draws a 28 x 28 image (values 0..1) with characters.
     *
     * @param image the scaled image
     * @return a multi-line string
     */
    private static String ascii(Matrix image) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < image.getRows(); i++) {
            for (int j = 0; j < image.getCols(); j++) {
                int level = (int) Math.round(image.get(i, j) * (SHADES.length() - 1));
                sb.append(SHADES.charAt(level)).append(SHADES.charAt(level));
            }
            sb.append('\n');
        }
        return sb.toString();
    }

    /**
     * Entry point.
     *
     * @param args optional: the data directory (default "data") and the index of one image to show
     * @throws IOException if a data file cannot be read
     */
    public static void main(String[] args) throws IOException {
        String dir = args.length > 0 ? args[0] : "data";

        CNNModel model = new CNNModel(dir + "/weights");
        Matrix images = Matrix.load(dir + "/test_images.txt"); // N x 784
        Matrix labels = Matrix.load(dir + "/test_labels.txt"); // N x 1
        int n = images.getRows();
        System.out.println("Loaded " + n + " test images.\n");

        // 1) a few digits (or just the one you ask for), drawn and classified
        int from = 0;
        int to = Math.min(3, n);
        if (args.length > 1) {
            from = Integer.parseInt(args[1]);
            to = from + 1;
        }
        for (int i = from; i < to; i++) {
            Matrix image = Classifier.preprocess(images.getRow(i));
            Matrix probabilities = model.forward(new Matrix[] {image})[0];
            int predicted = Classifier.predict(model, images.getRow(i));
            System.out.print(ascii(image));
            System.out.printf("test image %d   true digit: %d   predicted: %d   probability: %.4f%n%n",
                    i, (int) labels.get(i, 0), predicted, probabilities.get(0, predicted));
        }

        // 2) accuracy over the whole file
        double accuracy = Classifier.accuracy(model, images, labels);
        System.out.printf("Accuracy: %.2f%% (%d of %d images)%n%n",
                accuracy * 100, Math.round(accuracy * n), n);

        // 3) where the mistakes are: the table is drawn by YOUR Classifier.printConfusionMatrix
        Matrix confusion = Classifier.confusionMatrix(model, images, labels);
        System.out.println("Confusion matrix (row = true digit, column = predicted digit):");
        Classifier.printConfusionMatrix(confusion);
    }
}
