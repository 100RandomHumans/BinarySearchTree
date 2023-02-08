public class RedBlackNode extends BinaryNode{
    int color;
    public RedBlackNode(int value) {
        super(value);
        color = 0;
    }

    public RedBlackNode(int value, BinaryNode left, BinaryNode right, int color) {
        super(value, left, right);
        this.color = color;
    }


}
