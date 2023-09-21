import java.util.Scanner;

public class Main {
    
    public static void menu(Jogo jogo){

        Scanner scanner = new Scanner(System.in);

        System.out.print("""
            ============
                MENU
            ============

            [1] Movimentar
            [2] Solução automática
            [3] Sair

            Digite o número da opção desejada:  
            """);

            int opcaoSelecionada = scanner.nextInt();

            if(opcaoSelecionada > 0 && opcaoSelecionada < 4){

                System.out.println(jogo.viewTabelaPilhas());

                switch(opcaoSelecionada){
                    
                    case 1:
                        movimentar(jogo);
                        break;
                    case 2:
                        jogo.resolverAutomaticamente();
                        break;

                    case 3:
                        System.out.println("\n\n\nPROGRAMA FINALIZADO!\n\n\n");
                        System.exit(0);
                        break;

                }

                return;

            }

            System.out.println("\nOPÇÃO INVÁLIDA!\n");

    }

    public static void movimentar(Jogo jogo){

        Scanner scanner = new Scanner(System.in);

        System.out.print("""
            =============
                PILHAS
            =============

            [1] Pilha 1
            [2] Pilha 2
            [3] Pilha 3

            Digite o número da pilha de origem: """);

            int pilhaOrigem = scanner.nextInt();

            System.out.print("Digite o número da pilha de destino: ");

            int pilhaDestino = scanner.nextInt();

            System.out.println("Tentando movimentar... ");

            jogo.movimentar(pilhaOrigem-1, pilhaDestino-1);
            
    }

    public static Jogo inciarJogo(){

        Scanner scanner = new Scanner(System.in);

        System.out.print("\nDigite o tamanho desejado das pilhas: ");
        int tamanhoPilha = scanner.nextInt();

        System.out.println("""
                Digite como deseja ordernar as pilhas:

                [1] Crescente
                [2] Decrescente

                """);
        boolean ordenacao = scanner.nextInt() == 1;

        return new Jogo(tamanhoPilha, ordenacao);

    }

    public static void main(String[] args) {
        
        Jogo novoJogo = inciarJogo();

        System.out.println(novoJogo.viewTabelaPilhas());

        while(true){

            menu(novoJogo);

            if(novoJogo.verificarSeFinalizado()) {

                System.out.printf("""


                        PARABÉNS!

                        Você terminou o jogo em %s jogadas

                        
                        """
                        , novoJogo.getJogadas());

                System.out.println(novoJogo.viewTabelaPilhas());
                break;

            }

            
        }

    }
}
