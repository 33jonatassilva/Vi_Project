import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class FullVerification {

    private void littleBox(String frase) {
        int ei, ef, t;
        t = 53 - frase.length();
        ei = ef = t / 2;
        if (t % 2 == 1) ef++;
        System.out.println("+" + "-".repeat(53) + "+");

        System.out.println("|" + " ".repeat(ei) + frase + " ".repeat(ef) + "|");
        System.out.println("+" + "-".repeat(53) + "+");
    }

    // Verifica se uma certa String só possuem caracteres numéricos
    public boolean isNumeric(String linha){
        return (linha != null && linha.matches("[0-9]+"));
    }

    // Verifica se duas Strings possuem somente caracteres numéricos
    public boolean isNumeric(String linha, String linha2){
        if(!(linha != null && linha.matches("[0-9]+"))){
            littleBox("Invalid Interval - Try Again");
            return false;
        }
        return true;
    }

    // Verifica se o intervalo fornecido é válido
    public boolean isValid(int begin, int end, int size){
        if(begin > end || begin < 0 || end > size) {
            littleBox("Invalid Interval - Try Again");
            return false;
        }
        return true;
    }

    // VERIFICAÇÃO USADA NA FUNÇAO :X position
    // QUE REMOVE UM ELEMENTO QUALQUER DA LISTA
    public boolean isValid(int position, int size){
        if(position > 0 && position <= size)
            return true;
        littleBox("Invalid Position - Try Again");
        return false;
    }

    // USADO NA FUNÇÃO :a PARA INSERIR, É DIFERENTE DO isValid() comum
    // POIS INCLUI O ZERO COMO NÚMERO VÁLIDO POR ISSO isValidZ()
    public boolean isValidZ(int position, int size){
        if(position >= 0 && position <= size)
            return true;
        littleBox("Invalid Position - Try Again");
        return false;
    }


    // FUNÇÃO USADA NOS PRINTS DO CÓDIGO E DO HELP
    public boolean askContinue(){
        Scanner asker = new Scanner(System.in);
        System.out.println("Do you wish to continue? (Y/N)");
        do{
            String answer = asker.nextLine();
            if(answer.equalsIgnoreCase("Y"))
                return true;
            else if (answer.equalsIgnoreCase("N"))
                return false;
            else
                System.out.println("Option invalid! Try again (Y/N)");
        } while (true);
    }


    public boolean helpExtended(String opcao){
        switch (opcao){
            case ":e":
                System.out.println("Abrir o arquivo de nome “nomeArq.ext”, ler o seu conteúdo e armazenar\n" +
                        "cada linha em um Node da lista encadeada circular (simplesmente ou\n" +
                        "duplamente). Informar mensagem adequada ao usuário do editor ou exibir\n" +
                        "todo o texto armazenado.");
                break;
            case ":w":
                System.out.println("Salvar o conteúdo da lista encadeada circular (simplesmente ou\n" +
                        "duplamente) em arquivo de nome “nomeArq.ext”. A seguir, exibir\n" +
                        "mensagem coerente ao usuário do editor.");
                break;
            case ":q!":
                System.out.println("Sair do editor sem salvar as modificações realizadas. Antes de sair, solicitar\n" +
                        "confirmação e informar mensagem ao usuário do editor.\n");
                break;
            case "v":
                System.out.println("Marcar um texto da lista (para cópia ou corte) da LinIni até LinFim. Deve\n" +
                        "ser verificado se o intervalo [LinIni, LinFim] é válido. Se não for válido,\n" +
                        "informar ao usuário do editor. Caso contrário, realizar a operação, exibir o\n" +
                        "conteúdo do código fonte marcado e mensagem adequada ao usuário.");
            case "y":
                System.out.println("Copiar o texto marcado para uma lista de Cópia e exibir mensagem\n" +
                        "adequada ao usuário.");
            case "c":
                System.out.println("Cortar o texto marcado e exibir mensagem adequada ao usuário");
            case ":p":
                System.out.println("Colar o texto marcado a partir da linha inicial (LinIniPaste). Deve ser\n" +
                        "verificado se LinIniPaste é válido. Se não for válido, informar ao usuário do\n" +
                        "editor. Caso contrário, exibir mensagem adequada ao usuário.");
            case "s":
                System.out.println("Exibir na tela o conteúdo do programa fonte completo de 10 em 10 linhas. ");
            case "s LinIni LinEnd":
                System.out.println("Exibir na tela o conteúdo do programa fonte que consta na lista da linha\n" +
                        "inicial “LimIni” até a linha final “LinFim” de 10 em 10 linhas. Deve ser\n" +
                        "exibido o número da linha de código ao lado de cada linha (linhas em\n" +
                        "branco, caso haja, contam como linhas válidas).\n");
            case ":x Lin":
                System.out.println("Apagar a linha de posição “Lin” da lista e exibir mensagem adequada");
            case ":xG Lin":
                System.out.println("Apagar a partir da linha “Lin” até o final da lista e exibir mensagem\n" +
                        "adequada.");
            case "XG Lin":
                System.out.println("Apagar da linha “Lin” até o início da lista e exibir mensagem adequada.");
            case ":/ element":
                System.out.println("Percorrer a lista, localizar a(s) linha(s) (com a correspondente numeração\n" +
                        "da linha) na(s) qual(is) o “elemento” encontra-se e exibi-las");
            case ":/ elem elemTroca":
                System.out.println("Percorrer a lista, localizar o “elem” e realizar a troca por “elemTroca” em\n" +
                        "todas as linhas do código fonte. Exibir mensagem adequada ao término");
            case ":a posLin":
                System.out.println("Permitir a edição de uma ou mais novas linhas e inserir na lista depois da\n" +
                        "posição posLin. O término da entrada é dada por um “:a” em uma linha\n" +
                        "vazia. Quando a lista está vazia, insere a partir do início da lista. Exibir\n" +
                        "mensagem adequada ao término.");
            case "i posLin":
                System.out.println("Permitir a inserção da linha “[conteudo da nova linha]” e inserir na lista\n" +
                        "antes da posição posLin. Quando a lista está vazia, insere no início da lista.\n" +
                        "Exibir mensagem adequada ao término.\n");

        }
        return true;
    }


    public boolean wVrf(String[] vetor, String nameArq, boolean pass){
        if(!(vetor.length == 2)){
            littleBox("Invalid Command");
            return false;}
        if(!pass){
            littleBox("An error has occurred. File not initialized");
            return  false;}
        if(!Objects.equals(vetor[1], nameArq)){
            littleBox("An error has occurred. File not found");
            return false;}
        return true;
    }


}
