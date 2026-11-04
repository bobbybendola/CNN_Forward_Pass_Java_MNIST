// =====================================================================================
// PROVIDED FILE - DO NOT MODIFY. (The autograder uses its own copy of this file.)
// =====================================================================================
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Sample tests (PROVIDED - do not modify). One small, hand-checkable example per task, printed next to the expected
 * result, with a verdict. Run it as often as you like while you work: tasks you have not implemented yet simply
 * show up as "not implemented / crashed" and do not stop the other tests.
 *
 * <p>Passing everything here does NOT guarantee full credit: the autograder uses many more (and harder) cases.</p>
 */
public class SampleTest {
    private static int passed = 0;
    private static int total = 0;

    /** One test body. */
    private interface Body {
        String run() throws Exception;
    }

    /** Runs one test; prints the result, the reference and a verdict. */
    private static void check(String task, String expected, Body body) {
        total++;
        String got;
        try {
            got = body.run();
        } catch (Throwable t) {
            got = "!! crashed: " + t;
        }
        boolean ok = expected.equals(got);
        if (ok) {
            passed++;
        }
        System.out.println((ok ? "[ OK ] " : "[FAIL] ") + task);
        if (!ok) {
            System.out.println("         your result : " + got);
            System.out.println("         reference   : " + expected);
        }
    }


    /** Runs {@code r} and returns everything it printed on the console. */
    private static String capture(Runnable r) {
        PrintStream old = System.out;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bos, true));
        try {
            r.run();
        } finally {
            System.out.flush();
            System.setOut(old);
        }
        return bos.toString();
    }

    private static List<Double> numbersIn(String line) {
        List<Double> res = new ArrayList<>();
        Matcher m = Pattern.compile("-?\\d+(?:\\.\\d+)?").matcher(line);
        while (m.find()) {
            res.add(Double.parseDouble(m.group()));
        }
        return res;
    }

    /** "OK" if the text has one line per true digit (in order) holding that row's counts in order; else a message. */
    private static String tableProblem(String out, Matrix conf) {
        String[] lines = out.split("\\R");
        int li = 0;
        for (int t = 0; t < 10; t++) {
            boolean found = false;
            while (li < lines.length && !found) {
                List<Double> nums = numbersIn(lines[li++]);
                int k = 0;
                for (int q = 0; q < nums.size() && k < 10; q++) {
                    if (nums.get(q) == conf.get(t, k)) {
                        k++;
                    }
                }
                found = k == 10;
            }
            if (!found) {
                return "no line found for true digit " + t + ": its 10 counts must appear, in order, on ONE line (after the line of digit " + (t - 1) + ")";
            }
        }
        return "OK";
    }

    private static Matrix m(double[][] a) {
        return new Matrix(a);
    }

    /** Wraps a single matrix into the channel array used by the layers. */
    private static Matrix[] ch(Matrix... x) {
        return x;
    }

    public static void main(String[] args) throws Exception {
        final double[][] A = {{1, 2, 3}, {4, 5, 6}};

        System.out.println("===== Task 1: Matrix input and output");
        check("1.1  Matrix(2,3) is a 2x3 zero matrix", "2x3 [0.00000,0.00000,0.00000;0.00000,0.00000,0.00000]", () -> {
            Matrix z = new Matrix(2, 3);
            return z.getRows() + "x" + z.getCols() + " " + z.print();
        });
        check("1.1  shape() gives {rows, cols} and a NEW array each time", "2x3 true", () -> {
            Matrix x = new Matrix(2, 3);
            int[] first = x.shape();
            first[0] = 99; // must not change the matrix
            int[] second = x.shape();
            return second[0] + "x" + second[1] + " " + (first != second);
        });
        check("1.1  Matrix(double[][]), get, set", "2 2 9.5 3.0", () -> {
            Matrix x = m(new double[][] {{1, 2}, {3, 4}});
            x.set(0, 1, 9.5);
            return x.getRows() + " " + x.getCols() + " " + x.get(0, 1) + " " + x.get(1, 0);
        });
        check("1.2  print()", "[1.00000,2.50000,-3.00000;0.12500,4.00000,-0.50000]",
                () -> m(new double[][] {{1, 2.5, -3}, {0.125, 4, -0.5}}).print());
        check("1.3  load() a file with messy white space", "[1.50000,-2.00000,3.00000;0.25000,4.00000,-0.12500]",
                () -> Matrix.load("data/sample/matrix_messy.txt").print());

        System.out.println("===== Task 2: Arithmetic");
        check("2.1  add()", "[2.00000,4.00000,6.00000;8.00000,10.00000,12.00000]", () -> m(A).add(m(A)).print());
        check("2.1  sub()", "[0.00000,0.00000,0.00000;0.00000,0.00000,0.00000]", () -> m(A).sub(m(A)).print());
        check("2.2  mul(Matrix): 2x3 * 3x2", "[58.00000,64.00000;139.00000,154.00000]",
                () -> m(A).mul(m(new double[][] {{7, 8}, {9, 10}, {11, 12}})).print());
        check("2.2  mul(double)", "[2.50000,5.00000,7.50000;10.00000,12.50000,15.00000]", () -> m(A).mul(2.5).print());
        check("2.2  div(double)", "[0.50000,1.00000,1.50000;2.00000,2.50000,3.00000]", () -> m(A).div(2.0).print());
        check("2.3  hadamard()", "[1.00000,4.00000,9.00000;16.00000,25.00000,36.00000]", () -> m(A).hadamard(m(A)).print());
        check("2.3  transpose()", "[1.00000,4.00000;2.00000,5.00000;3.00000,6.00000]", () -> m(A).transpose().print());
        check("2.4  sum()", "21.0", () -> String.valueOf(m(A).sum()));
        check("2.4  max() of negative numbers", "-1.0", () -> String.valueOf(m(new double[][] {{-3, -1, -2}}).max()));
        check("2.4  argmax(): first maximum, row-major", "1", () -> String.valueOf(m(new double[][] {{3, 9, 9}, {1, 2, 3}}).argmax()));
        check("2.5  isEq()", "true false", () -> m(A).isEq(m(A), 1e-9) + " " + m(A).isEq(m(A).mul(1.1), 1e-9));

        System.out.println("===== Task 3: Indexing and reshaping");
        check("3.1  getRow(1)", "[4.00000,5.00000,6.00000]", () -> m(A).getRow(1).print());
        check("3.2  slice(0,2,1,3)", "[2.00000,3.00000;5.00000,6.00000]", () -> m(A).slice(0, 2, 1, 3).print());
        check("3.3  reshape(3,2)", "[1.00000,2.00000;3.00000,4.00000;5.00000,6.00000]", () -> m(A).reshape(3, 2).print());
        check("3.4  hstack()", "[1.00000,2.00000,3.00000,1.00000;4.00000,5.00000,6.00000,4.00000]",
                () -> m(A).hstack(m(new double[][] {{1}, {4}})).print());

        System.out.println("===== Task 4: Convolution, ReLU, max pooling");
        final Matrix img = m(new double[][] {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15, 16}});
        check("4.1  Conv2D: 4x4 image, kernel of ones", "[54.00000,63.00000;90.00000,99.00000]", () -> {
            Matrix ones = m(new double[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}});
            return new Conv2D(new Matrix[] {ones}, new double[] {0.0}).forward(ch(img))[0].print();
        });
        check("4.1  Conv2D: edge kernel + bias 0.5, two kernels", "2 [-5.50000,-5.50000;-5.50000,-5.50000] [1.50000,1.50000;1.50000,1.50000]", () -> {
            Matrix edge = m(new double[][] {{1, 0, -1}, {1, 0, -1}, {1, 0, -1}});
            Matrix zeros = m(new double[][] {{0, 0, 0}, {0, 0, 0}, {0, 0, 0}});
            Matrix[] out = new Conv2D(new Matrix[] {edge, zeros}, new double[] {0.5, 1.5}).forward(ch(img));
            return out.length + " " + out[0].print() + " " + out[1].print();
        });
        check("4.2  ReLU", "[0.00000,2.00000;3.00000,0.00000]",
                () -> new ReLU().forward(ch(m(new double[][] {{-1, 2}, {3, -4}})))[0].print());
        check("4.3  MaxPool2D(2): 4x4 -> 2x2", "[6.00000,8.00000;14.00000,16.00000]", () -> new MaxPool2D(2).forward(ch(img))[0].print());
        check("4.3  MaxPool2D(2): 5x5 -> 2x2 (ignores last row/column)", "2x2", () -> {
            Matrix five = new Matrix(5, 5);
            Matrix out = new MaxPool2D(2).forward(ch(five))[0];
            return out.getRows() + "x" + out.getCols();
        });

        System.out.println("===== Task 5: Flatten, Linear, Softmax");
        check("5.1  Flatten: two 2x3 channels -> one 1x12 row", "1 [1.00000,2.00000,3.00000,4.00000,5.00000,6.00000,7.00000,8.00000,9.00000,10.00000,11.00000,12.00000]", () -> {
            Matrix b = m(new double[][] {{7, 8, 9}, {10, 11, 12}});
            Matrix[] out = new Flatten().forward(ch(m(A), b));
            return out.length + " " + out[0].print();
        });
        check("5.2  Linear: x*W^T + b", "[1.50000,4.50000]", () -> {
            Matrix w = m(new double[][] {{1, 0, 0}, {0, 1, 1}});
            return new Linear(w, m(new double[][] {{0.5, -0.5}})).forward(ch(m(new double[][] {{1, 2, 3}})))[0].print();
        });
        check("5.3  Softmax(1, 2, 3)", "[0.09003,0.24473,0.66524]", () -> new Softmax().forward(ch(m(new double[][] {{1, 2, 3}})))[0].print());
        check("5.3  Softmax(1000, 1001, 1002) must not overflow", "[0.09003,0.24473,0.66524]",
                () -> new Softmax().forward(ch(m(new double[][] {{1000, 1001, 1002}})))[0].print());

        System.out.println("===== Task 6: Classifier (needs everything above)");
        Scanner ref = new Scanner(new File("data/reference.txt"));
        String firstTen = ref.nextLine().trim();
        double refAccuracy = Double.parseDouble(ref.nextLine().trim().split("\\s+")[1]);
        ref.close();
        // loading the network needs Matrix.load, getRow, reshape and the layer constructors: guard it too
        CNNModel loaded = null;
        Matrix loadedImages = null;
        Matrix loadedLabels = null;
        try {
            loaded = new CNNModel("data/weights");
            loadedImages = Matrix.load("data/test_images.txt");
            loadedLabels = Matrix.load("data/test_labels.txt");
        } catch (Throwable t) {
            System.out.println("[FAIL] could not load the network and the test images yet: " + t);
        }
        final CNNModel model = loaded;
        final Matrix images = loadedImages;
        final Matrix labels = loadedLabels;
        check("6.1  preprocess(): 1x784 -> 28x28 in [0,1]", "28x28 0.0 1.0", () -> {
            Matrix p = Classifier.preprocess(images.getRow(0));
            return p.getRows() + "x" + p.getCols() + " " + p.get(0, 0) + " " + p.max();
        });
        check("6.2  predict(): the first 10 test images", firstTen, () -> {
            StringBuilder sb = new StringBuilder("predictions_first10");
            for (int i = 0; i < 10; i++) {
                sb.append(' ').append(Classifier.predict(model, images.getRow(i)));
            }
            return sb.toString();
        });
        check("6.3  accuracy() on the 200 test images", String.format("%.4f", refAccuracy), () ->
                String.format(java.util.Locale.US, "%.4f", Classifier.accuracy(model, images, labels)));
        check("6.4  confusionMatrix(): all counts add up to 200", "200.0", () ->
                String.valueOf(Classifier.confusionMatrix(model, images, labels).sum()));

        Matrix sampleConf = new Matrix(10, 10);
        for (int d = 0; d < 10; d++) {
            sampleConf.set(d, d, 5 + d);
        }
        sampleConf.set(3, 5, 2);
        sampleConf.set(8, 1, 1);
        final String[] table = new String[1];
        check("6.5  printConfusionMatrix(): one line per true digit (your layout is shown below)", "OK", () -> {
            table[0] = capture(() -> Classifier.printConfusionMatrix(sampleConf));
            return tableProblem(table[0], sampleConf);
        });
        if (table[0] != null) {
            System.out.println("         what your method printed:");
            for (String line : table[0].split("\\R")) {
                System.out.println("         | " + line);
            }
        }

        System.out.println("\n" + passed + " of " + total + " sample tests passed.");
        if (passed == total) {
            System.out.println("Great! Now run Main to see your network read digits.");
        }
    }
}
