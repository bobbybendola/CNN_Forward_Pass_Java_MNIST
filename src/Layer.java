// =====================================================================================
// PROVIDED FILE - DO NOT MODIFY. (The autograder uses its own copy of this file.)
// =====================================================================================
/**
 * One building block of the network (PROVIDED - do not modify).
 *
 * <p>A layer receives the "feature maps" produced by the previous layer and returns new feature
 * maps. A feature map is a {@link Matrix}; several channels travel together as a {@code Matrix[]}
 * (one Matrix per channel). The network is simply:
 * {@code x = layer1.forward(x); x = layer2.forward(x); ...}</p>
 *
 * <p>A layer must NEVER modify the matrices it receives: always build and return new ones.</p>
 */
public interface Layer {

    /**
     * Applies this layer.
     *
     * @param input the channels coming from the previous layer
     * @return the channels produced by this layer
     */
    Matrix[] forward(Matrix[] input);
}
