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

        JTextField textField = new JTextField();
        textField.setBounds(0,0,500,25);

        JButton button = new JButton("→");
        button.setBounds(499, 0, 60, 24);
        button.addActionListener(e -> {
            binaryPanel.stringSplitter(textField.getText());
            System.out.println(textField.getText());
            textField.setText("");
        });

        binaryTree = new BinaryTree();
        add(textField);
        add(button);
        add(new BinaryPanel());

        setVisible(true);
    }
}
