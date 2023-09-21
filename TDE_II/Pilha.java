public class Pilha {

    private Node topo;
    
    public Node pop(){

        Node returnNode = topo;

        topo = topo.getProximo();

        return returnNode;

    }

    public void insert(int dado){

        topo = new Node(topo, dado);

    }

    public void insertNode(Node node){

        node.setProximo(topo);
        
        topo = node;

    }

    public boolean isEmpty(){

        return topo == null;

    }

    public boolean isCrescente(){

        if(isEmpty()) return false;

        return isCrescenteRecursivo(topo);

    }

    public boolean isDecrescente(){

        if(isEmpty()) return false;

        return isDecrescenteRecursivo(topo);

    }

    private boolean isCrescenteRecursivo(Node node){

        if(node.getProximo() == null) {

            return true;

        }

        if(node.getDado() < node.getProximo().getDado()){

            return false;

        }

        return isCrescenteRecursivo(node.getProximo());

    }

    private boolean isDecrescenteRecursivo(Node node){

        if(node.getProximo() == null) {

            return true;

        }

        if(node.getDado() > node.getProximo().getDado()){

            return false;

        }

        return isDecrescenteRecursivo(node.getProximo());

    }

    public Node getTopo() {
        return topo;
    }

    public String toString(){

        return "[" + topo.toString() + "]";

    }

}
