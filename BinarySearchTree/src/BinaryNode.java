public class BinaryNode {
    public BinaryNode leftNode;
    public BinaryNode rightNode;
    public int value;
    public BinaryNode(int value) {
        this.value = value;
        leftNode = null;
        rightNode = null;
    }
    public BinaryNode(int value, BinaryNode left, BinaryNode right) {
        this.value = value;
        leftNode = left;
        rightNode = right;
    }

    public void setLeft(BinaryNode leftNode) {
        this.leftNode = leftNode;
    }
    public void setRight(BinaryNode rightNode){
        this.rightNode = rightNode;
    }

    public BinaryNode getLeft() {
        return leftNode;
    }

    public BinaryNode getRight() {
        return rightNode;
    }
    public int getValue() {
        return value;
    }
    public String toString()
    {
        return value + "";
//        return "Value:"+value; //+
//                ", Left:"+(leftNode==null?null:leftNode.getValue())+
//                ", Right:"+(rightNode==null?null:rightNode.getValue());
    }
}
