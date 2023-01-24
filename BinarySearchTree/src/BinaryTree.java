import java.util.Scanner;

public class BinaryTree {
    BinaryNode root;
    public BinaryTree(String a) {
        Scanner scanner = new Scanner(a);
        while (scanner.hasNextInt()) {
            add(new BinaryNode(scanner.nextInt(), null, null));
        }
    }
    public BinaryTree(){

    }
    public void add(BinaryNode node) {
        if(root == null) {
            root = node;
        } else {
            place(root, node);
        }
    }
    public void place(BinaryNode parent, BinaryNode child) {
        if (parent.getValue() > child.getValue()) {
            if (parent.getLeft() == null) {
                parent.setLeft(child);
            } else {
                place(parent.getLeft(), child);
            }
        } else {
            if (parent.getRight() == null) {
                parent.setRight(child);
            } else {
                place(parent.getRight(), child);
            }
        }
    }
    public void preOrder() {
        System.out.println("Preorder: " + preOrder(root));
    }
    public String preOrder(BinaryNode node) {
        String value = "";
        if (node != null) {
            value += node.getValue() + " ";
            value += preOrder(node.getLeft());
            value += preOrder(node.getRight());
        }
        return value;
    }
    public void postOrder() {
        System.out.println("Postorder: " + postOrder(root));
    }
    public String postOrder(BinaryNode node) {
        if (node == null)
            return "";
        String a = postOrder(node.getLeft());
        String b = postOrder(node.getRight());
        return a + b + node.getValue() + " ";
    }
    public void inOrder() {
        System.out.println("Inorder: " + inOrder(root));
    }
    public String inOrder(BinaryNode node) {
        return "future task";
    }
}
