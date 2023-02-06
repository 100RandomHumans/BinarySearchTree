import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BinaryPanel extends JPanel {
    public static int width = 1600;
    public static int height = 900;
    BinaryTree binaryTree;
    ArrayList<String> aa = new ArrayList<>();

    public BinaryPanel() {
        setLayout(null);
        setSize(1600, height);
        JTextField textField = new JTextField();
        textField.setBounds(0, 0, 500, 25);

        JButton button = new JButton("→");
        button.setBounds(499, 0, 60, 24);
        button.addActionListener(e -> {
            aa.add(0, textField.getText());
            if (aa.size() > 10) {
                aa.remove(10);
            }
            repaint();
            stringSplitter(textField.getText());
            System.out.println(textField.getText());
            textField.setText("");
        });

        binaryTree = new BinaryTree();
        add(textField);
        add(button);
    }



    public void stringSplitter(String enter) {
        String[] a = enter.split(" ");
        if (a[0].equalsIgnoreCase("print")) {
            for (BinaryNode[] pain : binaryTree.calculateTree()) {
                System.out.println(Arrays.toString(pain));
            }
            return;
        }
        int b;
        try {
            b = Integer.parseInt(a[1]);
        } catch (Exception e) {
            System.out.println("2nd part not a number");
            return;
        }
        if (a[0].equalsIgnoreCase("remove")) {
            System.out.println("tried to remove " + b);
            binaryTree.remove(b);
        } else if (a[0].equalsIgnoreCase("add")) {
            binaryTree.add(new BinaryNode(b));
        } else {
            System.out.println("nonsense in part 1");
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawString("add x - add tiles ", 0, 35);
        g.drawString("remove x - remove tiles", 0, 45);
        int yy = 60;
        for (String test : aa) {
            g.drawString(test, 0, yy);
            yy += 10;
        }
        if (binaryTree.root!= null) {
            ArrayList<BinaryNode[]> tree = binaryTree.calculateTree();
            int intervalY = 875 / tree.size();
            int intervalX;
            int count;
            int totalY = 50;
            for (BinaryNode[] holder : tree) {
                count = holder.length + 1;
                intervalX = width / count;
                int totalX = 0;

                for (BinaryNode node : holder) {
                    totalX += intervalX;
                    if (node != null) {

                        g.drawString(node.getValue() + "", totalX, totalY);
                        g.drawRect(totalX - 2, totalY - 12, g.getFontMetrics().stringWidth(node.getValue() + "") + 4, 12);
                        node.x = totalX;
                        node.y = totalY;
                    }
                }
                totalY += intervalY;

            }
            ArrayList<BinaryNode> list = binaryTree.levelOrder();
            for (BinaryNode node : list) {
                if (node != null) {
                    if (node.getLeft() != null) {
                        g.drawLine(node.x - 2, node.y, node.getLeft().x - 2, node.getLeft().y - 12);
                    }
                    if (node.getRight() != null) {
                        g.drawLine(node.x - 2, node.y, node.getRight().x - 2, node.getRight().y - 12);
                    }

                }
            }
        }
    }
}
