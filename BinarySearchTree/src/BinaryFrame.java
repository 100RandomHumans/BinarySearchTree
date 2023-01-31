import javax.swing.*;

public class BinaryFrame extends JFrame {
    public static int width = 1600;
    public static int height = 900;
    BinaryTree binaryTree;
    public BinaryFrame() {
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(width, height);


        BinaryPanel binaryPanel = new BinaryPanel();

        add(binaryPanel);
        setVisible(true);
    }
}
