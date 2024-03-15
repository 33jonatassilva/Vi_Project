a
public class Node {
    private String linha;
    private Node prox;

    public Node() {
        this("", null);
    }

    public Node(String linha, Node prox) {
        this.linha = linha;
        this.prox = prox;
    }

    public Node getProx() { return prox; };
    public String getLinha(){ return linha; };

    public void setProx(Node prox) { this.prox = prox; };
    public void setLinha(String linha) { this.linha = linha;	};

}
a
public class Node {
    private String linha;
    private Node prox;

    public Node() {
        this("", null);
    }

    public Node(String linha, Node prox) {
        this.linha = linha;
        this.prox = prox;
    }

    public Node getProx() { return prox; };
    public String getLinha(){ return linha; };

    public void setProx(Node prox) { this.prox = prox; };
    public void setLinha(String linha) { this.linha = linha;	};

}