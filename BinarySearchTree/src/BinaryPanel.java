import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class BinaryPanel extends JPanel {
    public static int width = 1600;
    public static int height = 900;
    BinaryTree binaryTree = new BinaryTree();
    ArrayList<String> aa = new ArrayList<>();

    public BinaryPanel() {
        setLayout(null);
        setSize(1600, 900);
        JTextField textField = new JTextField();
        textField.setBounds(0, 0, 500, 25);

        JButton button = new JButton("→");
        button.setBounds(499, 0, 60, 24);
        button.addActionListener(e -> {
            aa.add(textField.getText());
            repaint();
            stringSplitter(textField.getText());
            System.out.println(textField.getText());
            textField.setText("");
        });
        JButton repainted = new JButton("repaint");
        repainted.setBounds(560, 0, 100, 24);
        repainted.addActionListener(e -> repaint());
        binaryTree = new BinaryTree("7 27 11 3 14 12 26 41 19 35 4 50");
        add(textField);
        add(button);
        add(repainted);
    }



    public void stringSplitter(String enter) {
        String[] a = enter.split(" ");
        if (a[0].equalsIgnoreCase("print")) {
            System.out.println("printed out");
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
        System.out.println("Binary Panel paint called");
        g.drawString("add x - add tiles ", 0, 35);
        g.drawString("remove x - remove tiles", 0, 45);
        int yy = 60;
        for (String test : aa) {
            g.drawString(test, 0, yy);
            yy += 10;
        }

        int startY = 50;
        int startX = 800;
        ArrayList<BinaryNode[]> tree = binaryTree.calculateTree();
        int intervalY = 875 / tree.size();
        int intervalX;
        int count = 0;
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
                    g.drawLine(node.x - 2, node.y, node.leftNode.x -2, node.leftNode.y - 12);
                }
                if (node.getRight() != null) {
                    g.drawLine(node.x - 2, node.y, node.rightNode.x -2, node.rightNode.y - 12);
                }

            }
        }

    }
}
