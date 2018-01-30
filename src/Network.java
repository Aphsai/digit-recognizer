import java.util.Arrays;

public class Network {
    int num_layers;
    int [] sizes;
    double [][] biases;
    double [][][] weights;
    public Network(int[] sizes) {
        this.num_layers = sizes.length;
        this.sizes = sizes;
        this.biases = new double[num_layers][];
        for (int x = 0; x < num_layers; x++) {
            biases[x] = new double[sizes[x]];
        }
        for (int x = 1; x < num_layers; x++) {
            for (int y = 0; y < biases[x].length; y++) {
                biases[x][y] = Math.random();
            }
        }
        this.weights = new double[num_layers - 1][][];
        for (int x = 0; x < num_layers - 1; x++) {
            weights[x] = new double[sizes[x + 1]][];
            for (int z = 0; z < sizes[x + 1]; z++) {
                weights[x][z] = new double[sizes[x]];
                for (int y = 0; y < sizes[x]; y++) {
                    weights[x][z][y] = Math.random();
                }
            }
        }
        System.out.println(Arrays.deepToString(weights));
    }
    //Dot product
    public double dot (double[] a, double[] b) {
        if (a.length != b.length) return -1;
        double c = 0;
        for (int x = 0; x < a.length; x++) {
            c += a[x] + b[x];
        }
        return c;
    }
    //Returns maximum value based on input.
    public int feedForward(double[] a) {
        double[] c = null;
        for (int x = 0; x < weights.length; x++) {
         c = new double[weights[x].length];
         for (int y = 0; y < weights[x].length; y++) {
             c[x] = sigmoid(new double[]{(dot(a, weights[x][y]) + biases[x+1][y])})[0];
         }
         a = c.clone();
        }
        double output = Integer.MIN_VALUE;
        int index = 0;
        for (int x = 0; x < a.length; x++) {
            if (output < a[x]) {
                index = x;
                output = a[x];
            }
        }
        return index;
    }
    //The gradient descent
    public void SGD (double[][] training_data, int session, int m_batch_size, double eta) {
        int n = training_data.length;
        for (int x = 0; x < session; x++) {
            shuffle(training_data);

        }
    }
    public double[] shuffle(double[] arr) {
        for (int x = 0; x < arr.length; x++) {
            int index = (int)Math.ceil(Math.random() * arr.length);
            if (index != x) {
                arr[index] += arr[x];
                arr[x] = arr[index] - arr[x];
                arr[index] -= arr[x];
            }
        }
        return arr;
    }
    public double[] sigmoid(double[] z) {
        for (int x = 0; x < z.length; x++) {
            z[x] = 1.0 / (1.0 + Math.exp(-z[x]));
        }
        return z;
    }
    public static void main(String[] args) {
        new Network(new int[] {2, 3 , 4});
    }
}
