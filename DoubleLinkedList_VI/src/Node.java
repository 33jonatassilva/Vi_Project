public class Node {
    private Node right;
    private Node left;
    private  String line;
    public Node(){ right = left = null; this.line = "";}
    public String getLine() {return this.line;}
    public Node getLeft() {return left;}
    public Node getRight() {return right;}
    public void setLine(String line) {this.line = line;}
    public void setLeft(Node left) {this.left = left;}
    public void setRight(Node right) {this.right = right;}
}
