// =====================================================================================
// STUDENT FILE. Implement what is asked; write code ONLY between the comments
//   // Your code starts here   and   // Your code ends here
// Do NOT change the class name, the method signatures or the imports. Helper methods are
// allowed (private ones, in the helper region). Add a // comment to explain your algorithm.
// =====================================================================================
import java.io.File;
import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

/**
 * A two-dimensional matrix of doubles. Everything the CNN does (convolution windows, the fully
 * connected layer, the images themselves) is built on top of this one class.
 *
 * <p>Indices are 0-based: {@code get(0, 0)} is the top-left element. All matrices are immutable
 * from the outside EXCEPT through {@link #set(int, int, double)}: every other method must return
 * a NEW Matrix and leave {@code this} (and its arguments) unchanged.</p>
 *
 * <p>All arguments are valid: you never need to check shapes or indices.</p>
 */
public class Matrix {

    // ------------------------------------------------------------------------------------
    // Self-defined attributes: decide how you store the data (hint: a double[][] and the sizes).
    // ------------------------------------------------------------------------------------
    // Your code starts here
    private double [][] matrix; 
    // Your code ends here

    // ====================================================================================
    // Task 1: Construction, accessors, printing and loading from a file
    // ====================================================================================

    /**
     * Task 1.1a: creates a rows x cols matrix whose elements are all 0.0.
     *
     * @param rows number of rows (positive)
     * @param cols number of columns (positive)
     */
    public Matrix(int rows, int cols) {
        // Your code starts here
        double [][] zeroMatrix= new double [rows][cols]; 
        for(int i=0; i<rows;i++){
            for (int j=0; j<cols; j++){
                    zeroMatrix[i][j]=0.0;        
            }
        }
        // Your code ends here
    }

    /**
     * Task 1.1b: creates a matrix holding a COPY of the given values. Changing {@code values}
     * afterwards must not change the matrix (and vice versa).
     *
     * @param values a rectangular array: values[i][j] is the element in row i, column j
     */
    public Matrix(double[][] values) {
        // Your code starts here
        double [][] copyMatrix = new double [values.length][values[0].length]; 

        for (int i=0; i<values.length; i++){
            for (int j=0; j<values[0].length; j++){
                    copyMatrix[i][j] = values[i][j];
            }
        }
            // Your code ends here
    }

    /**
     * Task 1.1c: number of rows.
     *
     * @return the number of rows
     */
    public int getRows() {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return matrix.length; 
        // Your code ends here
    }

    /**
     * Task 1.1c: number of columns.
     *
     * @return the number of columns
     */
    public int getCols() {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return matrix[0].length; 
        // Your code ends here
    }

    /**
     * Task 1.1e: the shape of the matrix, like {@code a.shape} in numpy.
     *
     * @return a NEW array {rows, cols}; changing the returned array must not change the matrix
     */
    public int[] shape() {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        int [] shape = new int [2];
        shape[0]=matrix.length; 
        shape[1]=matrix[0].length; 
        return shape;
        // Your code ends here 
    }

    /**
     * Task 1.1d: reads one element.
     *
     * @param i row index (0-based)
     * @param j column index (0-based)
     * @return the element at row i, column j
     */
    public double get(int i, int j) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return matrix[i][j];
        // Your code ends here
    }

    /**
     * Task 1.1d: overwrites one element (the only method allowed to modify {@code this}).
     *
     * @param i row index (0-based)
     * @param j column index (0-based)
     * @param value the new value
     */
    public void set(int i, int j, double value) {
        // Your code starts here
        matrix[i][j]= value; 
        
        // Your code ends here
    }

    /**
     * Task 1.2: turns the matrix into text. Rows are separated by ';', elements by ',', the whole
     * matrix is wrapped in '[' and ']', and there are NO spaces or line breaks. Every element is
     * written with exactly 5 digits after the decimal point.
     *
     * <p>Example: the 2x2 matrix (1, 2.5 / -3, 0) prints as
     * {@code [1.00000,2.50000;-3.00000,0.00000]}.</p>
     *
     * @return the text form of the matrix
     */
    public String print() {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    /**
     * Task 1.3: loads a matrix from a text file.
     *
     * <p>File format: the first two numbers are the row count and the column count; they are
     * followed by rows*cols numbers in row-major order (row 0 first). Numbers are separated by
     * ANY amount of whitespace (spaces, tabs, '\n', '\r\n', blank lines). Numbers may look like
     * {@code 3}, {@code -0.25}, {@code 1.5e-3}.</p>
     *
     * @param path path of the text file
     * @return the matrix stored in the file
     * @throws IOException if the file cannot be read
     */
    public static Matrix load(String path) throws IOException {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    // ====================================================================================
    // Task 2: Arithmetic
    // ====================================================================================

    /**
     * Task 2.1a: element-wise sum.
     *
     * @param other a matrix of the same shape
     * @return a new matrix with this[i][j] + other[i][j]
     */
    public Matrix add(Matrix other) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    /**
     * Task 2.1b: element-wise difference.
     *
     * @param other a matrix of the same shape
     * @return a new matrix with this[i][j] - other[i][j]
     */
    public Matrix sub(Matrix other) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    /**
     * Task 2.2a: matrix product (NOT element-wise). If this is m x k and other is k x n, the
     * result is m x n with res[i][j] = sum over t of this[i][t] * other[t][j].
     *
     * @param other a matrix with as many rows as this has columns
     * @return the m x n product
     */
    public Matrix mul(Matrix other) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    /**
     * Task 2.2b: multiplies every element by a scalar.
     *
     * @param scalar the factor
     * @return a new matrix with this[i][j] * scalar
     */
    public Matrix mul(double scalar) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    /**
     * Task 2.2c: divides every element by a (non-zero) scalar.
     *
     * @param scalar the divisor
     * @return a new matrix with this[i][j] / scalar
     */
    public Matrix div(double scalar) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    /**
     * Task 2.3a: element-wise (Hadamard) product. This is what a convolution multiplies a window
     * with a kernel by, before summing.
     *
     * @param other a matrix of the same shape
     * @return a new matrix with this[i][j] * other[i][j]
     */
    public Matrix hadamard(Matrix other) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    /**
     * Task 2.3b: transpose (rows become columns). An m x n matrix becomes n x m.
     *
     * @return a new matrix t with t[j][i] = this[i][j]
     */
    public Matrix transpose() {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    /**
     * Task 2.4a: sum of all elements. Add them in row-major order (row 0 left to right, then
     * row 1, ...), starting from 0.0.
     *
     * @return the sum of all elements
     */
    public double sum() {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return 0.0;
        // Your code ends here
    }

    /**
     * Task 2.4b: the largest element. Careful: the matrix may contain only negative numbers.
     *
     * @return the maximum element
     */
    public double max() {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return 0.0;
        // Your code ends here
    }

    /**
     * Task 2.4c: position of the largest element, counted in row-major order: the element at
     * (i, j) has position i * cols + j. For a 1 x N row this is simply the column index. If
     * several elements are equal to the maximum, return the FIRST (smallest position).
     *
     * @return the row-major position of the largest element
     */
    public int argmax() {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return 0;
        // Your code ends here
    }

    /**
     * Task 2.5: approximate equality (floating point numbers should not be compared with ==).
     *
     * @param other the matrix to compare with
     * @param tol the largest allowed difference per element
     * @return true if both matrices have the same shape and |this[i][j] - other[i][j]| <= tol
     *         for every element; false otherwise
     */
    public boolean isEq(Matrix other, double tol) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return false;
        // Your code ends here
    }

    // ====================================================================================
    // Task 3: Indexing and reshaping ("slicing")
    // ====================================================================================

    /**
     * Task 3.1: one row of the matrix, as a 1 x cols matrix (a copy).
     *
     * @param i row index (0-based)
     * @return a new 1 x cols matrix holding row i
     */
    public Matrix getRow(int i) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    /**
     * Task 3.2: a rectangular part of the matrix (a COPY: changing the slice must not change the
     * original). Row range is [r0, r1) and column range is [c0, c1), i.e. the start is included
     * and the end is excluded, exactly like Python's {@code a[r0:r1, c0:c1]}.
     *
     * @param r0 first row (included)
     * @param r1 last row (excluded)
     * @param c0 first column (included)
     * @param c1 last column (excluded)
     * @return a new (r1 - r0) x (c1 - c0) matrix
     */
    public Matrix slice(int r0, int r1, int c0, int c1) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    /**
     * Task 3.3: same elements in a new shape. Elements are read in row-major order and written in
     * row-major order, like Python's {@code a.reshape(newRows, newCols)}. For example the 1 x 4 row
     * (1, 2, 3, 4) reshaped to 2 x 2 is (1, 2 / 3, 4).
     *
     * @param newRows rows of the result
     * @param newCols columns of the result (newRows * newCols equals rows * cols)
     * @return a new newRows x newCols matrix
     */
    public Matrix reshape(int newRows, int newCols) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    /**
     * Task 3.4: places {@code other} to the right of this matrix (horizontal stack). The
     * 1 x 3 row (1,2,3) hstack the 1 x 2 row (4,5) is the 1 x 5 row (1,2,3,4,5).
     *
     * @param other a matrix with the same number of rows
     * @return a new matrix with cols + other.cols columns
     */
    public Matrix hstack(Matrix other) {
        // Your code starts here
        // TODO: delete the placeholder line below and write your own code
        return null;
        // Your code ends here
    }

    // ------------------------------------------------------------------------------------
    // Self-defined helper methods: add your own (private) helpers here if you want.
    // ------------------------------------------------------------------------------------
    // Your code starts here
    // Your code ends here
}
