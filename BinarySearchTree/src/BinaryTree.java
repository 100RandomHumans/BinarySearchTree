import java.util.ArrayList;
import java.util.Scanner;

public class BinaryTree { // FIX ROOT REPLACEMENT THING :D
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
            root.parent = new BinaryNode(-1);
            root.parent.setLeft(root);
            root.parent.setRight(new BinaryNode(-2));
        } else {
            place(root, node);
        }
    }

    public void place(BinaryNode parent, BinaryNode child) {
        if (parent.getValue() > child.getValue()) {
            if (parent.getLeft() == null) {
                parent.setLeft(child);
                child.parent = parent;
            } else {
                place(parent.getLeft(), child);
            }
        } else {
            if (parent.getRight() == null) {
                parent.setRight(child);
                child.parent = parent;
            } else {
                place(parent.getRight(), child);
            }
        }
    }

//    public ArrayList<BinaryNode> preOrder() {
//        return preOrder(root);
//    }
//
//    public ArrayList<BinaryNode> preOrder(BinaryNode node) {
//        ArrayList<BinaryNode> array = new ArrayList<>();
//        if (node != null) {
//            array.add(node);
//            array.addAll(preOrder(node.getLeft()));
//            array.addAll(preOrder(node.getRight()));
//        }
//        return array;
//    }

//    public ArrayList<BinaryNode> postOrder() {
//        return postOrder(root);
//    }
//
//    public ArrayList<BinaryNode> postOrder(BinaryNode node) {
//        if (node == null)
//            return new ArrayList<BinaryNode>();
//        ArrayList<BinaryNode> aa = new ArrayList<>();
//        aa.addAll(postOrder(node.getLeft()));
//        aa.addAll(postOrder(node.getRight()));
//        aa.add(node);
//        return aa;
//    }

//    public ArrayList<BinaryNode> inOrder() {
//        return inOrder(root);
//    }
//
//    public ArrayList<BinaryNode> inOrder(BinaryNode node) {
//        if (node == null) {
//            return new ArrayList<>();
//        }
//        ArrayList<BinaryNode> holder = new ArrayList<>();
//        holder.addAll(inOrder(node.getLeft()));
//        holder.add(node);
//        holder.addAll(inOrder(node.getRight()));
//        return holder;
//    }

    public ArrayList<BinaryNode> levelOrder() {
        ArrayList<BinaryNode> a = new ArrayList<>();
        a.add(root);
        for (int i = 0; i < a.size(); i++) {
            if (a.get(i) != null) {
                a.add(a.get(i).getLeft());
                a.add(a.get(i).getRight());
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
                tree.remove(tree.size() - 1);
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

    public void remove(int value) {
        BinaryNode node = search(value, root);
        System.out.println(node + " removed");
        if (node.getLeft() == null && node.getRight() == null) { // degree 0
            if (node == root) {
                root = null;
            return;
        }
            if (node.parent.getRight() != null && node.parent.getRight().equals(node)) {
                node.parent.setRight(null);
            } else {
                node.parent.setLeft(null);
            }
            return;
        }
        if (node.getLeft() != null && node.getRight() == null) { // degree 1 with left child
            if (node.parent.getRight().equals(node)) {
                node.parent.setRight(node.getLeft());
            } else {
                node.parent.setLeft(node.getLeft());
            }
            node.getLeft().parent = node.parent;
            if (node == root)
                root = node.getLeft();
            return;
        }
        if (node.getRight() != null) {
            if (node.getRight().getLeft() == null) {
                node = node.getRight();
                node = node.parent;
                node.getRight().setLeft(node.getLeft());
                if (node.parent.getRight().equals(node)) {
                    node.parent.setRight(node.getRight());
                } else {
                    node.parent.setLeft(node.getRight());
                }
                if (node == root)
                    root = node.getRight();
                return;
            }

            BinaryNode sucNode = successor(node);

            sucNode.parent.setLeft(sucNode.getRight());
            sucNode.setRight(node.getRight());
            sucNode.setLeft(node.getLeft());
            sucNode.parent = node.parent;
            if (node.parent.getRight().equals(node)) {
                node.parent.setRight(sucNode);
            } else {
                node.parent.setLeft(sucNode);
            }
            if (node == root)
                root = sucNode;
            return;
        }
        if (node == root)
            root = new BinaryNode(231823);
    }

    private BinaryNode successor(BinaryNode k) //
    {
        BinaryNode temp = k;
        temp = temp.getRight();
        while (temp.getLeft() != null)
            temp = temp.getLeft();
        return temp;
    }

    public BinaryNode search(int value, BinaryNode startNode) {
        if (startNode == null) {
            return null;
        }
        if (startNode.getValue() == value) {
            return startNode;
        } else if (value < startNode.getValue()) {
            return search(value, startNode.getLeft());
        } else {
            return search(value, startNode.getRight());
        }
    }

}
