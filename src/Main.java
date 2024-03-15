import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException; // Import the IOexception class to handle errors


public class Main {
    public static void littleBox(String frase) {
        int ei, ef, t;
        t = 53 - frase.length();
        ei = ef = t / 2;
        if (t % 2 == 1) ef++;
        System.out.println("+" + "-".repeat(53) + "+");
        System.out.println("|" + " ".repeat(ei) + frase + " ".repeat(ef) + "|");
        System.out.println("+" + "-".repeat(53) + "+");
    }

    public static void main(String[] args) throws InterruptedException {

        FullVerification vrf = new FullVerification();
        CircleLinkedList list = new CircleLinkedList();
        CircleLinkedList listAux = new CircleLinkedList();
        int position = -1;
        boolean pass = false;
        String nomeArq = "";
        boolean exit = false;
        int begin = -1;
        int end = -1;
        int time = 150;


        System.out.println("╔=====================================================╗");
        Thread.sleep(time);
        System.out.println("║                                                     ║");
        Thread.sleep(time);
        System.out.println("║  ███╗   ██╗ ███████╗ ██╗  ██╗ ███╗   ███╗           ║");
        Thread.sleep(time);
        System.out.println("║  ████╗  ██║ ██╔════╝ ██║  ██║ ████╗ ████║           ║");
        Thread.sleep(time);
        System.out.println("║  ██╔██╗ ██║ █████╗   ███████║ ██╔████╔██            ║");
        Thread.sleep(time);
        System.out.println("║  ██║╚██╗██║ ██╔══╝   ██╔══██║ ██║╚██╔╝██║           ║");
        Thread.sleep(time);
        System.out.println("║  ██║ ╚████║ ███████╗ ██║  ██║ ██║ ╚═╝ ██║           ║");
        Thread.sleep(time);
        System.out.println("║  ╚═╝  ╚═══╝ ╚══════╝ ╚═╝  ╚═╝ ╚═╝     ╚═╝ Editor    ║");
        Thread.sleep(time);
        System.out.println("╚=====================================================╝");
        Thread.sleep(time);
        System.out.println("    by Jônatas Brito, Felipe Koike and Bruno Viana     \n\n\n");





        while(true){
            Scanner scStr = new Scanner(System.in);
            System.out.print("Nehm -> ");
            String comando = scStr.nextLine();
            String [] vetor = comando.split(" ", 3); // Tô recebendo o comando, mas tem alguns que são iguais eu preciso distinguir se ele tem

            // :e codigo.java
            switch (vetor[0]){
                // lê o arquivo de acordo com o nome inserido pelo usuário
                case ":e":
                    if(vetor.length == 2) {
                        try {
                            list.clear();
                            nomeArq = vetor[1];
                            File myObj = new File(nomeArq);
                            Scanner myReader = new Scanner(myObj);
                            while (myReader.hasNextLine()) {
                                String data = myReader.nextLine();
                                list.insertTail(data);
                            }
                            littleBox("File read successfully!");
                            pass = true;
                            myReader.close();
                        } catch (FileNotFoundException e) {
                            littleBox("An error has occurred. File not found");
                            //e.printStackTrace();
                        }
                    } else
                        littleBox("Invalid Command");
                    break;


                // Função salva o texto editado no arquivo
                case ":w":
                    if(vrf.wVrf(vetor, nomeArq, pass)) {
                        try {
                            FileWriter writer = new FileWriter(nomeArq);
                            writer.write("");
                            writer.write(list.toString());
                            writer.close();
                            littleBox("Changes saved successfully.");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    break;

                // Encerra o programa
                case ":q!":
                    if(vetor.length == 1){
                        littleBox("Program closed.");
                        exit = true;
                    } else
                        littleBox("Invalid command");
                    break;

                // Marca um intervalo de texto
                case  ":v":
                    if(vetor.length == 3 && vrf.isNumeric(vetor[1], vetor[2])) {
                        if(list.isEmpty()) {littleBox("Empty list - There is nothing to mark"); break; };
                        try {
                            while (vetor[2].endsWith(" ")) vetor[2] = vetor[2].substring(0, vetor[2].length() - 1);
                            begin = Integer.parseInt(vetor[1]);
                            end = Integer.parseInt(vetor[2]);
                            if (vrf.isValid(begin, end, list.getCount())) {
                                list.highlighter(begin, end);
                                break;
                            }
                            else
                                break;
                        } catch (NumberFormatException e) {
                            littleBox("Invalid interval - Try Again");
                            break;
                        }
                    }
                    littleBox("Invalid Command");
                    break;

                // Copia o intervalo de texto marcado
                case ":y":
                    if (vetor.length == 1) {
                        if(list.copyText(listAux)) littleBox("Message copied successfully!");
                        //System.out.println(listAux.toString());
                    } else
                        littleBox("Invalid number of element(s)");

                    break;

                // Corta o intervalo de texto marcado
                case ":c":

                    if(vetor.length == 1) {
                        list.cutText(listAux);
                        //System.out.println(list.toString());
                    }
                    else
                        littleBox("Invalid Command");
                    break;

                // Cola o intervalo de texto copiado ou recortado
                case ":p":
                        if(vetor.length == 2 && vrf.isNumeric(vetor[1])) {
                        int homeLine = Integer.parseInt(vetor[1]);
                        if(vrf.isValidZ(homeLine, list.getCount() + 1)) {
                            list.pasteText(homeLine, list, listAux);
                            //System.out.println(list.toString();
                        }
                    }
                    break;

                // Mostra o texto editado que está na listAux de 10 em 10
                case ":s":
                    try {
                        if (vetor.length == 1) {
                            if(!list.isEmpty()) {
                                littleBox("printing file...");
                            }
                            list.print();
                        } else if (vetor.length == 3 && vrf.isNumeric(vetor[1], vetor[2])) {
                            if(list.isEmpty()) {littleBox("List Empty - There Nothing to be Printed"); break;}
                            begin = Integer.parseInt(vetor[1]);
                            end = Integer.parseInt(vetor[2]);
                            if (vrf.isValid(begin, end, list.getCount())) {
                                littleBox("printing file...");
                                list.printInterval(begin, end);
                            }
                        } else
                            littleBox("Invalid number of element(s)");
                    } catch (NumberFormatException e){
                        littleBox("Invalid Command");
                    }
                    break;

                // Remove um elemento pela posição
                case ":x":
                    if (!(vetor.length == 2) || !(vrf.isNumeric(vetor[1]))){
                        littleBox("Invalid Command");
                        break;}
                    position = Integer.parseInt(vetor[1]);
                    if(vrf.isValid(position, list.getCount()))
                        list.removeLinePosition(position);
                    break;

                // Remove elemento(s) a da posição p até o final da list
                case ":xG":
                    if ((vetor.length == 2) && vrf.isNumeric(vetor[1])) {
                        position = Integer.parseInt(vetor[1]);
                        if(vrf.isValid(position, list.getCount()))
                            list.removeLineEnd(position);
                    } else
                        littleBox("Invalid number of element(s)");
                    break;

                // Remove elemento(s) da posição p até o início
                case ":XG":
                    if ((vetor.length == 2) && vrf.isNumeric(vetor[1])) {
                        position = Integer.parseInt(vetor[1]);
                        if(vrf.isValid(position, list.getCount()))
                            list.removeLineBegin(position);
                    } else
                        littleBox("Invalid number of element(s)");
                    break;

                // Encontra ocorrencias ou troca as mesmas por outra palavra
                case ":/":
                    if (vetor.length == 2)
                        // Mostra as linhas em que uma certa palavra ou letra ocorre
                        list.showOcorrences(vetor[1]);
                    else if (vetor.length == 3)
                        // Troca as ocorrências em que uma certa palavra ou letra ocorre
                        list.replaceOcorrences(vetor[1], vetor[2]);
                    else
                        littleBox("Invalid number of element(s)");
                    break;

                // Insere uma ou mais linhas numa posição qualquer
                case ":a":
                    if (vetor.length == 2 && vrf.isNumeric(vetor[1])) {
                        position = Integer.parseInt(vetor[1]);
                        if(vrf.isValidZ(position, list.getCount()))
                            list.insertText(position + 1);
                    } else
                        littleBox("Invalid Command");
                    break;

                case ":i":
                    if ((vetor.length == 3) && vrf.isNumeric(vetor[1])) {
                        position = Integer.parseInt(vetor[1]);
                        if(vrf.isValidZ(position - 1, list.getCount() + 1)) {
                            System.out.println("Vetor[2] eh: " + vetor[2]);
                            list.insertPorIndice(position, vetor[2]);
                        }
                    } else {
                        littleBox("Invalid Command");
                    }
                    break;

                case ":help":
                    Scanner sc = new Scanner(System.in);

                    System.out.println(":e nameArq.ext\n" +
                            "Abre o arquivo de nome “nomeArq.ext”, ler o seu conteúdo e armazenar\n" +
                            "cada linha em um Node da lista encadeada circular (simplesmente ou\n" +
                            "duplamente)");
                    System.out.println(":w nameArq.ext\n" +
                            "Salva o conteúdo da lista encadeada circular (simplesmente ou\n" +
                            "duplamente) em arquivo de nome “nomeArq.ext”");
                    System.out.println(":q!" +
                            "Sai do editor sem salvar as modificações realizadas. Antes de sair, solicita\n" +
                            "confirmação e informa mensagem ao usuário do editor");
                    System.out.println(":v\n" +
                            "Marca um texto da lista (para cópia ou corte) da LinIni até LinFim. Deve\n" +
                            "ser verificado se o intervalo [LinIni, LinFim] é válido. Se não for válido,\n" +
                            "informa ao usuário do editor. Caso contrário, realiza a operação, exibir o\n" +
                            "conteúdo do código fonte marcado e mensagem adequada ao usuário.");
                    System.out.println(":y\n" +
                            "Copia o texto marcado para uma lista de Cópia e exibe mensagem\n" +
                            "adequada ao usuário. ");
                    System.out.println(":c\n" +
                            "Corta o texto marcado e exibe mensagem adequada ao usuário");
                    if(!vrf.askContinue()) {
                        littleBox("help finished");
                        break;
                    }

                    System.out.println(":p LinIniPaste\n" +
                            "Cola o texto marcado a partir da linha inicial (LinIniPaste). Deve ser\n" +
                            "verificado se LinIniPaste é válido. Se não for válido, informa ao usuário do\n" +
                            "editor. Caso contrário, exibe mensagem adequada ao usuário.");
                    System.out.println(":s\n" +
                            "Exibe na tela o conteúdo do programa fonte completo de 10 em 10 linhas.");
                    System.out.println(":s LinBeg LinEndzn\n" +
                            "Exibir na tela o conteúdo do programa fonte que consta na lista da linha\n" +
                            "inicial “LimIni” até a linha final “LinFim” de 10 em 10 linhas. Deve ser\n" +
                            "exibido o número da linha de código ao lado de cada linha (linhas em\n" +
                            "branco, caso haja, contam como linhas válidas).");
                    System.out.println(":x Lin\n" +
                            "Apagar a linha de posição “Lin” da lista e exibir mensagem adequada.");
                    System.out.println(":xG Lin\n" +
                            "Apagar a partir da linha “Lin” até o final da lista e exibir mensagem\n" +
                            "adequada.");
                    if(!vrf.askContinue()) {
                        littleBox("help finished");
                        break;
                    }

                    System.out.println(":XG Lin");
                    System.out.println(":/ element");
                    System.out.println(":/ elem elemRepl");
                    System.out.println(":a posLin ");
                    System.out.println(":i posLin [new line content]\n");
                    littleBox("help finished");
                    break;

                default:
                    littleBox("Invalid Command.");
                    break;

            }
            if(exit) break;
        }
    }
}
