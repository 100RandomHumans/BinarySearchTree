import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class BinaryPanel extends JPanel{
    BinaryTree binaryTree = new BinaryTree();
    public BinaryPanel() {
        setBounds(0,0,1600,900);
        repaint();
    }

    public void paint(Graphics g) {
        g.drawString("add x - add tiles ", 0, 35);
        g.drawString("remove x - remove tiles", 0, 45);

    }

    public void addTile(int num) {
        binaryTree.add(new BinaryNode(num));
    }

    public void stringSplitter(String enter) {
        String[] a = enter.split(" ");
        if (a[0].equalsIgnoreCase("print")) {
            System.out.println("called");
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
                // do something
            } else if (a[0].equalsIgnoreCase("add")) {
                addTile(b);
            } else {
                System.out.println("nonsense in part 1");
            }
        }
    }
