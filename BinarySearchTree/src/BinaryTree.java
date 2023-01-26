import java.util.ArrayList;
import java.util.Scanner;

public class BinaryTree {
    BinaryNode root;

    public BinaryTree(String a) {
        Scanner scanner = new Scanner(a);
        while (scanner.hasNextInt()) {
            add(new BinaryNode(scanner.nextInt(), null, null));
        }
    }

    public BinaryTree() {

    }

    public void add(BinaryNode node) {
        if (root == null) {
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

    public ArrayList<BinaryNode> preOrder() {
        return preOrder(root);
    }

    public ArrayList<BinaryNode> preOrder(BinaryNode node) {
        ArrayList<BinaryNode> array = new ArrayList<>();
        if (node != null) {
            array.add(node);
            array.addAll(preOrder(node.getLeft()));
            array.addAll(preOrder(node.getRight()));
        }
        return array;
    }

    public ArrayList<BinaryNode> postOrder() {
        return postOrder(root);
    }

    public ArrayList<BinaryNode> postOrder(BinaryNode node) {
        if (node == null)
            return new ArrayList<BinaryNode>();
        ArrayList<BinaryNode> aa = new ArrayList<>();
        aa.addAll(postOrder(node.getLeft()));
        aa.addAll(postOrder(node.getRight()));
        aa.add(node);
        return aa;
    }

    public ArrayList<BinaryNode> inOrder() {
        return inOrder(root);
    }

    public ArrayList<BinaryNode> inOrder(BinaryNode node) {
        if (node == null) {
            return new ArrayList<>();
        }
        ArrayList<BinaryNode> holder = new ArrayList<>();
        holder.addAll(inOrder(node.getLeft()));
        holder.add(node);
        holder.addAll(inOrder(node.getRight()));
        return holder;
    }

    public ArrayList<BinaryNode> levelOrder() {
        ArrayList<BinaryNode> a = new ArrayList<>();
        a.add(root);
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != null) {
                a.add(a.get(i).getLeft());
                a.add(a.get(i).getRight());
                System.out.println(a.get(i).getLeft() + " : " + a.get(i).getRight());
            }
        }
        return a;

    }

    public ArrayList<BinaryNode[]> calculateTree() {
        ArrayList<BinaryNode[]> tree = new ArrayList<>();
        tree.add(new BinaryNode[]{root});
        while (true) {
            boolean flip = false;
            for (BinaryNode a : tree.get(tree.size() - 1)) {
                if (a != null) {
                    flip = true;
                    break;
                }
            }
            if (!flip) {
                tree.remove(tree.size() -1 );
                return tree;
            }
            tree.add(new BinaryNode[tree.get(tree.size() - 1).length * 2]);
            int holder = tree.get(tree.size() - 2).length;
            for (int i = 0; i < holder; i++) {
                if (tree.get(tree.size() - 2)[i] != null) {
                    tree.get(tree.size() - 1)[(i * 2)] = tree.get(tree.size() - 2)[i].getLeft();
                    tree.get(tree.size() - 1)[(i * 2) + 1] = tree.get(tree.size() - 2)[i].getRight();
                } else {
                    tree.get(tree.size() - 1)[(i * 2)] = null;
                    tree.get(tree.size() - 1)[(i * 2) + 1] = null;
                }

            }
        }
    }

}
