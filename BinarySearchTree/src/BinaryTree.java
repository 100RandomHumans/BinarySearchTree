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

    public void remove(int i) {
        BinaryNode node = search(i, root);
        if (node.equals(root)) {
            if (node.getRight() != null) {
                BinaryNode holder = node.getRight();
                if (holder.getLeft() != null) {
                    while (holder.getLeft() != null) {
                        holder = holder.getLeft();
                    }
                    holder.parent.setLeft(holder.getRight());
                    root = holder;
                    holder.setLeft(node.getLeft());
                    holder.setRight(node.getRight());
                    node.parent.setRight(holder);
                } else {
                    holder.parent.setRight(holder.getRight());
                    root = holder;
                    holder.setLeft(node.getLeft());
                    holder.setRight(node.getRight());

                }
            } else if (node.getLeft() != null) {
                root = node.getLeft();
            } else {
                root = null;
            }
        } else {
            if (node.getRight() != null) {
                BinaryNode holder = node.getRight();
                if (holder.getLeft() != null) {
                    while (holder.getLeft() != null) {
                        holder = holder.getLeft();
                    }
                    holder.parent.setLeft(holder.getRight());

                    if (node.parent.getRight().equals(node)) {
                        node.parent.setRight(holder);
                    } else {
                        node.parent.setLeft(holder);
                    }
                    holder.setLeft(node.getLeft());
                    holder.setRight(node.getRight());
                } else {
                    if (node.parent.getRight().equals(node)) {
                        node.parent.setRight(holder);
                    } else {
                        node.parent.setLeft(holder);
                    }
                    holder.setLeft(node.getLeft());
                }
            } else if (node.getLeft() != null) {
                if (node.parent.getRight().equals(node)) {
                    node.parent.setRight(node.getLeft());
                } else {
                    node.parent.setLeft(node.getLeft());
                }
            } else {
                if (node.parent.getRight().equals(node)) {
                    node.parent.setRight(null);
                } else
                    node.parent.setLeft(null);
            }






        }

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
