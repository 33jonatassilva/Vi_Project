import java.io.File;  // Import the File class
import java.io.FileWriter;   // Import the FileWriter class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.io.IOException; // Import the IOexception class to handle errors


public class Main {
    public static void littleBox(String frase) {
        int ei, ef, t;
        t = 72 - frase.length();
        ei = ef = t / 2;
        if (t % 2 == 1) ef++;
        System.out.println("+" + "-".repeat(72) + "+");
        System.out.println("|" + " ".repeat(ei) + frase + " ".repeat(ef) + "|");
        System.out.println("+" + "-".repeat(72) + "+");
    }

    public static void main(String[] args) throws InterruptedException {

        DoubleCircleLinkedList listAux = new DoubleCircleLinkedList();
        DoubleCircleLinkedList list = new DoubleCircleLinkedList();
        FullVerification vrf = new FullVerification();
        EditPrint p = new EditPrint();
        boolean exit = false;
        boolean pass = false;
        String nomeArq = "";
        int position = -1;
        int begin = -1;
        int time = 150;
        int end = -1;

        p.beginBanner();

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
                        p.endBanner();
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
                                if(list.highlighter(begin, end))
                                    littleBox("Successfully marked text!");
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
                        if(list.copy(listAux)) littleBox("Text copied successfully!");
                        //System.out.println(listAux.toString());
                    } else
                        littleBox("Invalid command");

                    break;

                // Corta o intervalo de texto marcado
                case ":c":
                    if(vetor.length == 1) {
                        if(list.cut(listAux))
                            littleBox("Text recorded successfully!");
                        //System.out.println(list.toString());
                    }
                    else
                        littleBox("Invalid Command");
                    break;

                // Cola o intervalo de texto copiado ou recortado
                case ":p":
                    if(vetor.length == 2 && vrf.isNumeric(vetor[1])) {
                        if(listAux.isEmpty()){ littleBox("Error! Text not recorded or selected"); break;};
                        int homeLine = Integer.parseInt(vetor[1]);
                        if(vrf.isValid(homeLine, list.getCount() + 1)) {
                            if(list.paste(homeLine, listAux))
                                littleBox("Text pasted successfully!");
                        }
                    } else
                        littleBox("Invalid command");
                    break;

                // Mostra o texto editado que está na listAux de 10 em 10
                case ":s":
                    try {
                        if (vetor.length == 1) {
                            if(list.isEmpty()) {littleBox("List Empty - There Nothing to be Printed"); break;}
                            if(!list.isEmpty()) {
                                littleBox("printing file...");
                            }
                            if(list.showAll())
                                littleBox("Complete Printing");
                        } else if (vetor.length == 3 && vrf.isNumeric(vetor[1], vetor[2])) {
                            if(list.isEmpty()) {littleBox("List Empty - There Nothing to be Printed"); break;}
                            begin = Integer.parseInt(vetor[1]);
                            end = Integer.parseInt(vetor[2]);
                            if (vrf.isValid(begin, end, list.getCount())) {
                                littleBox("printing file...");
                                if(list.showInterval(begin, end))
                                    littleBox("Complete Printing");
                            }
                        } else {
                            littleBox("Invalid number of element(s)");
                            break;
                        }
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
                    if(vrf.isValid(position, list.getCount())) {
                        list.removeIn(position);
                        littleBox("Line removed successfully");
                    }
                    break;

                // Remove elemento(s) a da posição p até o final da list
                case ":xG":
                    if ((vetor.length == 2) && vrf.isNumeric(vetor[1])) {
                        position = Integer.parseInt(vetor[1]);
                        if(vrf.isValid(position, list.getCount())) {
                            list.removeEnd(position);
                            littleBox("Range deleted successfully");
                        }
                    } else
                        littleBox("Invalid command");
                    break;

                // Remove elemento(s) da posição p até o início
                case ":XG":
                    if ((vetor.length == 2) && vrf.isNumeric(vetor[1])) {
                        position = Integer.parseInt(vetor[1]);
                        if(vrf.isValid(position, list.getCount()))
                            if(list.removeBeg(position))
                                littleBox("Range deleted successfully");
                    } else
                        littleBox("Invalid command");
                    break;

                // Encontra ocorrencias ou troca as mesmas por outra palavra
                case ":/":
                    if (vetor.length == 2) {
                        // Mostra as linhas em que uma certa palavra ou letra ocorre
                        littleBox("Searching for occurrence(s)...");
                        Thread.sleep(1000);
                        if(list.showOccurrences(vetor[1])) {
                            littleBox("Occurrences printed successfully");
                        } else {
                            littleBox("Word not found");
                        }

                    }
                    else if (vetor.length == 3) {
                        // Troca as ocorrências em que uma certa palavra ou letra ocorre
                        if(list.replaceOccurrences(vetor[1], vetor[2])) {
                            littleBox("Occurrences replaced successfully");
                        } else {
                            littleBox("Word not found");
                        }
                    }
                    else
                        littleBox("Invalid command");
                    break;

                // Insere uma ou mais linhas numa posição qualquer
                case ":a":
                    if (vetor.length == 2 && vrf.isNumeric(vetor[1])) {
                        position = Integer.parseInt(vetor[1]);
                        if(vrf.isValidZ(position, list.getCount())) {
                            if (list.insertText(position + 1))
                                littleBox("Text entered successfully");
                            else
                                littleBox("Could not insert sentence");
                        }
                    } else
                        littleBox("Invalid Command");
                    break;

                case ":i":
                    if ((vetor.length == 3) && vrf.isNumeric(vetor[1])) {
                        position = Integer.parseInt(vetor[1]);
                        if(vrf.isValid(position, list.getCount() + 1)) {
                            //System.out.println("Vetor[2] eh: " + vetor[2]);
                            if(list.insertIn(position, vetor[2]))
                                littleBox("Sentence entered successfully");
                            else
                                littleBox("Could not insert sentence");
                        }
                    } else {
                        littleBox("Invalid Command");
                    }
                    break;

                case ":help":
                    Scanner sc = new Scanner(System.in);

                    System.out.println(":e nameArq.ext" +
                            "\tAbre o arquivo de nome “nomeArq.ext”, lê o seu conteúdo e armazena\n" +
                            "\t\t\t\tcada linha em um Node da lista encadeada circular (simplesmente ou\n" +
                            "\t\t\t\tduplamente)\n");
                    System.out.println(":w nameArq.ext" +
                            "\tSalva o conteúdo da lista encadeada circular (simplesmente ou\n" +
                            "\t\t\t\tduplamente) em arquivo de nome “nomeArq.ext”\n");
                    System.out.println(":q!" +
                            "\t\t\t\tSai do editor sem salvar as modificações realizadas. Antes de sair, solicita\n" +
                            "\t\t\t\tconfirmação e informa mensagem ao usuário do editor\n");
                    System.out.println(":v LnBg LnEnd" +
                            "\tMarca um texto da lista (para cópia ou corte) da LnBg até LnEnd. Deve\n" +
                            "\t\t\t\tser verificado se o intervalo [LnBg, LnEnd] é válido. Se não for válido,\n" +
                            "\t\t\t\tinforma ao usuário do editor. Caso contrário, realiza a operação, exibir o\n" +
                            "\t\t\t\tconteúdo do código fonte marcado e mensagem adequada ao usuário.\n");
                    System.out.println(":y" +
                            "\t\t\t\tCopia o texto marcado para uma lista de Cópia e exibe mensagem\n" +
                            "\t\t\t\tadequada ao usuário.\n");
                    if(!vrf.askContinue()) {
                        littleBox("help finished");
                        break;
                    }

                    System.out.println(":c" +
                            "\t\t\t\tCorta o texto marcado e exibe mensagem adequada ao usuário\n");


                    System.out.println(":p LinIniPaste" +
                            "\tCola o texto marcado a partir da linha inicial (LinIniPaste). Deve ser\n" +
                            "\t\t\t\tverificado se LinIniPaste é válido. Se não for válido, informa ao usuário do\n" +
                            "\t\t\t\teditor. Caso contrário, exibe mensagem adequada ao usuário.\n");
                    System.out.println(":s" +
                            "\t\t\t\tExibe na tela o conteúdo do programa fonte completo de 10 em 10 linhas.\n");
                    System.out.println(":s LnBg LnEnd" +
                            "\tExibe na tela o conteúdo do programa fonte que consta na lista da linha\n" +
                            "\t\t\t\tinicial “LnBg” até a linha final “LnEnd” de 10 em 10 linhas. Deve ser\n" +
                            "\t\t\t\texibido o número da linha de código ao lado de cada linha (linhas em\n" +
                            "\t\t\t\tbranco, caso haja, contam como linhas válidas).\n");
                    System.out.println(":x Lin" +
                            "\t\t\tApaga a linha de posição “Lin” da lista e exibe mensagem adequada.\n");

                    if(!vrf.askContinue()) {
                        littleBox("help finished");
                        break;
                    }

                    System.out.println(":xG Lin" +
                            "\t\t\tApaga a partir da linha “Lin” até o final da lista e exibe mensagem\n" +
                            "\t\t\t\tadequada.\n");


                    System.out.println(":XG Lin" +
                            "\t\t\tApaga da linha “Lin” até o início da lista e exibe mensagem adequada\n");
                    System.out.println(":/ element" +
                            "\t\tPercorre a lista, localiza a(s) linha(s) (com a correspondente numeração\n" +
                            "\t\t\t\tda linha) na(s) qual(is) o “elemento” encontra-se e exibi-las. \n");
                    System.out.println(":/ elm elmRpl" +
                            "\tPercorre a lista, localiza o “elm” e realiza a troca por “elmRpl” em\n" +
                            "\t\t\t\ttodas as linhas do código fonte. Exibe mensagem adequada ao término.\n");
                    System.out.println(":a posLin" +
                            "\t\tPermite a edição de uma ou mais novas linhas e insere na lista depois da\n" +
                            "\t\t\t\tposição posLin. O término da entrada é dada por um “:a” em uma linha vazia. \n" +
                            "\t\t\t\tQuando a lista está vazia, insere a partir do início da lista. Exibir\n" +
                            "\t\t\t\tmensagem adequada ao término.");

                    if(!vrf.askContinue()) {
                        littleBox("help finished");
                        break;
                    }

                    System.out.println(":i posLin [new line content]\n" +
                            "\t\t\t\tPermite a inserção da linha “[conteudo da nova linha]” e insere na lista\n" +
                            "\t\t\t\tantes da posição posLin. Quando a lista está vazia, insere no início da lista.\n" +
                            "\t\t\t\tExibir mensagem adequada ao término.\n");

                    System.out.println(":help" +
                            "\t\t\tApresenta na tela todas as operações permitidas no editor de 5 em 5\n" +
                            "\t\t\t\tcomandos.\n");

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
