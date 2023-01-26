import java.util.ArrayList;
import java.util.Arrays;

public class tester {
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree("7 27 11 3 14 12 26 41 19 35 4 50");
        ArrayList<BinaryNode[]> a = tree.calculateTree();
        for (BinaryNode[] pain : a) {
                System.out.println(Arrays.toString(pain));

        }

    }
}
