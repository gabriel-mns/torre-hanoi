import java.util.Random;

public class Jogo {
    
    private int tamanho;

    private Pilha[] pilhas = { new Pilha(), new Pilha(), new Pilha()};

    private boolean crescente;

    private int jogadas = 0;

    public Jogo(int tamanho, boolean crescente){

        this.tamanho = tamanho;

        this.crescente = crescente;

        if(tamanho == 0) {

            System.out.println("Tamanho inválido. Deve ser maior que 0.");
            return;

        }

        popularPilhas(tamanho);

    }

    private void popularPilhas(int tamanho){

        Random random = new Random();

        // Vai adicionar numeros aleatórios as pilhas até o tamanho que foi informado
        for (int i = 0; i < tamanho; i++) {

            // Gera números aleatórios de 0 a 99 e soma +1 para ficar de 1 até 100
            int numeroP2 = random.nextInt(100) + 1;

            pilhas[0].insert(numeroP2);

        }

    }

    public String viewTabelaPilhas(){

        // Formato (em colunas) que a tabela terá, o '15' é a quantidade de caracteres daquela coluna
        // '-' informa que o texto que estará dentro será alinhado à esquerda (padrão a direita)

        String formatoTabela = "%-15s %-15s %-15s\n";

        String tabela = String.format(formatoTabela, "Pilha 1", "Pilha 2", "Pilha 3");

        Node pilha1Atual = pilhas[0].getTopo();
        Node pilha2Atual = pilhas[1].getTopo();
        Node pilha3Atual = pilhas[2].getTopo();

        for ( int i = 0; i < tamanho; i++ ) {

            // Vai exibir o número caso o node atual n seja null ou vai exibir
            // um '-' caso o node seja null
            
            String dadoApresentacaoP1 = pilha1Atual == null ? "-" : "" + pilha1Atual.getDado();
            String dadoApresentacaoP2 = pilha2Atual == null ? "-" : "" + pilha2Atual.getDado();
            String dadoApresentacaoP3 = pilha3Atual == null ? "-" : "" + pilha3Atual.getDado();

            tabela += String.format(formatoTabela, dadoApresentacaoP1, dadoApresentacaoP2, dadoApresentacaoP3);

            pilha1Atual = pilha1Atual == null ? pilha1Atual : pilha1Atual.getProximo();
            pilha2Atual = pilha2Atual == null ? pilha2Atual : pilha2Atual.getProximo();
            pilha3Atual = pilha3Atual == null ? pilha3Atual : pilha3Atual.getProximo();
        }

        return tabela;


    }

    public void movimentar(int origem, int destino){

        try{
        

            if(verificarSeJogadaValida(origem, destino)){

                Node nodeRemovido = pilhas[origem].pop();
    
                pilhas[destino].insertNode(nodeRemovido); 
    
                System.out.println("Esta é foi jogada número: " + jogadas);
    
                jogadas++;

                return;
            }

        } catch(Exception ignore){ 
        } 
        finally{

            System.out.println(viewTabelaPilhas());

        }
        
        System.out.println("""



                Movimentação inválida! Você deve selecionar:
                 - Pilha de origem de 1, 2 ou 3.
                 - Pilha de destino de 1, 2 ou 3.
                 - Pilha de origem diferente de pilha de destino
                 - Se escolheu crescente, apenas números maiores em cima de menores
                 - Se escolheu decrescente, apenas números menores em cima de maiores



                """);
    }

    private boolean verificarSeJogadaValida(int origem, int destino) {

        if(origem != destino){

            try {

                if(pilhas[destino].getTopo() == null){

                    return true;

                }

                if(crescente){

                    return pilhas[origem].getTopo().getDado() >= pilhas[destino].getTopo().getDado();

                }

                return pilhas[origem].getTopo().getDado() <= pilhas[destino].getTopo().getDado();
                


            } catch (Exception ignore) {}

        }

        return false;
    }
    
    private boolean verificarSeDecrescente(){
        
        boolean pilha0Vazia = pilhas[0].isEmpty();
        boolean pilha1Vazia = pilhas[1].isEmpty();
        boolean pilha2Vazia = pilhas[2].isEmpty();

        if((!pilha0Vazia && !pilha1Vazia && !pilha2Vazia) ^ ( !pilha0Vazia ^ !pilha1Vazia ^ !pilha2Vazia)) {

            if(!pilha0Vazia) return pilhas[0].isDecrescente();

            if(!pilha1Vazia) return pilhas[1].isDecrescente();

            if(!pilha2Vazia) return pilhas[2].isDecrescente();

        }
        
        return false;
    }

    private boolean verificarSeCrescente(){
        
        boolean pilha0Vazia = pilhas[0].isEmpty();
        boolean pilha1Vazia = pilhas[1].isEmpty();
        boolean pilha2Vazia = pilhas[2].isEmpty();

        if((!pilha0Vazia && !pilha1Vazia && !pilha2Vazia) ^ ( !pilha0Vazia ^ !pilha1Vazia ^ !pilha2Vazia)) {

            if(!pilha0Vazia) return pilhas[0].isCrescente();

            if(!pilha1Vazia) return pilhas[1].isCrescente();

            if(!pilha2Vazia) return pilhas[2].isCrescente();

        }
        
        return false;
    }

    public boolean verificarSeFinalizado(){

        return crescente ? verificarSeCrescente() : verificarSeDecrescente();

    }

    public void resolverAutomaticamente() {

        try {

            Random random = new Random();

            int origem  = random.nextInt(3);
            int destino = random.nextInt(3);

            while(!verificarSeFinalizado()){

                if(verificarSeJogadaValida(origem, destino)){

                    movimentar(origem, destino);

                }

                origem  = random.nextInt(3);
                destino = random.nextInt(3);

            }

            
        } catch (Exception ignore) {}

    }
    
    public int getJogadas(){

        return jogadas;

    }
}
