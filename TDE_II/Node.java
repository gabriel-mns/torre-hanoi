public class Node {

    private Node proximo;

    private int dado;

    public Node(Node proximo, int dado) {

        this.proximo = proximo;
        this.dado = dado;

    }

    public Node(int dado) {

        this.dado = dado;

    }

    public Node getProximo() {

        return proximo;
        
    }

    public void setProximo(Node proximo) {

        this.proximo = proximo;
        
    }

    public int getDado() {

        return dado;
        
    }

    public void setDado(int dado) {

        this.dado = dado;
        
    }

    public String toString(){

        String strNormal = dado + " => " + proximo;
        String strUltimo = dado + " => Fim da pilha";

        return proximo == null ? strUltimo : strNormal;

    }

}
