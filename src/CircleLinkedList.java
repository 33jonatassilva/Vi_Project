import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class CircleLinkedList {
    private FullVerification vrf = new FullVerification();
    private Node head;
    private Node tail;
    private int count;

    // Elementos usados para marcar texto
    private Node mi; // marcação inicial
    private Node mf; // marcação final
    private int countM; // contador de marcacao

    //funcao que formata o texto em caixa desejada
    private void littleBox(String frase, boolean n) {
        System.out.println("+" + "-".repeat(frase.length() + 2) + "+");
        System.out.println("| " + frase + " |");
        System.out.println("+" + "-".repeat(frase.length() + 2) + "+");

        if (n)
            System.out.println();
    }
    //fim da littleBox

    //funcao que formata o texto em caixa desejada com a insersao de \n no fim
    private void littleBox(String frase) {
        int ei, ef, t;
        t = 53 - frase.length();
        ei = ef = t / 2;
        if (t % 2 == 1) ef++;
        System.out.println("+" + "-".repeat(53) + "+");

        System.out.println("|" + " ".repeat(ei) + frase + " ".repeat(ef) + "|");
        System.out.println("+" + "-".repeat(53) + "+");
    }
    //fim da littleBox2.0


    public CircleLinkedList() {
        head = tail = mi = mf = null;

        count = 0;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean isFull() {
        Node aux = new Node();
        return aux == null;
    }

    public int getCount() {
        return count;
    }

    public Node getMi() {
        return mi;
    }

    public Node getMf() {
        return mf;
    }

    public Node getHead() {
        return head;
    }

    public String getHeadLine() {
        return head.getLinha();
    }

    public String getTailLine() {
        return tail.getLinha();
    }

    public Node getTail() {
        return tail;
    }

    public boolean insertTail(String linha) {
        Node aux;
        if (!isFull()) { // Não há espaço de memória
            aux = new Node(linha, null);
            if (isEmpty()) { // Lista está vazia insere no cabeça
                aux.setProx(head);
                head = tail = aux;
            } else { // Insere no final e atualiza os ponteiros
                tail.setProx(aux);
                aux.setProx(head);
                tail = aux;
            }
            count++;
            return true;
        } else return false;
    }

    ; // Final do método insertTail()

    public boolean insertHead(String linha) {
        Node aux;
        if (!isFull()) {
            aux = new Node(linha, null);
            if (isEmpty()) { // Lista está vazia
                aux.setProx(head);
                head = tail = aux;
            } else { // Insere no começo e atualiza os ponteiros
                aux.setProx(head);
                head = aux;
                tail.setProx(head);
            }
            count++;
            return true;
        } else return false;
    }

    ; // Final do método insertHead

    public boolean insertText(int indice) {
        if (!isFull()) {
            String linha;
            String[] vetor;
            Scanner input = new Scanner(System.in);
            //CircleLinkedList listaAuxiliar = new CircleLinkedList();

            if (isEmpty()) {
                do {
                    linha = input.nextLine();
                    if (linha.equals(":a"))
                        break;
                    insertTail(linha);
                } while (true);
                littleBox("Line(s) inserted successfully!");
                return true;

            }

            do {
                linha = input.nextLine();
                if (linha.equals(":a"))
                    break;
                insertPorIndice(indice, linha);
                indice++;
            } while (true);
            littleBox("Line(s) inserted successfully!");
            return true;
        }
        littleBox("Unable to insert line(s)");
        return false;
    } // Final insertText()


    // funcao que acrescenta um Node por indice
    public boolean insertPorIndice(int indice, String linha) {
        if (indice < 1 || indice > count + 1) {
            // Verifica se o índice é inválido.
            return false;
        }

        Node pAnda = new Node();
        pAnda.setLinha(linha);

        if (isEmpty()) {
            // Se a lista estiver vazia, o novo nó se torna a cabeça (head).
            //insertTail(linha);
            head = tail = pAnda;
            pAnda.setProx(head);

        } else if (indice == 1) {
            //insertHead(linha);
            pAnda.setProx(head);
            head = pAnda;
            tail.setProx(pAnda);
        } else if (indice == count + 1) {
            // Se o índice for igual ao tamanho da lista + 1, o novo nó se torna o novo final (tail).
            tail.setProx(pAnda);
            pAnda.setProx(head);
            tail = pAnda;
        } else {
            // Caso contrário, encontre o nó pAnt ao índice desejado e insira o novo nó entre eles.
            Node pAnt = head;
            for (int i = 1; i < indice - 1; i++) {
                pAnt = pAnt.getProx();
            }
            pAnda.setProx(pAnt.getProx());
            pAnt.setProx(pAnda);
        }
        count++;
        return true;
    } // Final insertPorIndice()

    // funcao que procura uma String na lista
    public Node search(String linha) {
        Node pAnda;

        if (isEmpty()) {
            return null; // Empty list
        } else {
            pAnda = head;
            // procura a posição do elemento na lista
            while ((pAnda.getProx() != head) && !(pAnda.getLinha().equals(linha)))
                pAnda = pAnda.getProx();
            if ((pAnda.getLinha().equals(linha)))
                return pAnda; // Retorna a referência para o No
            return null; // elemento não encontrado
        }
    } // Final  do método search()


    // funcao que mostra o numero de aparicoes da String desejada na lista
    public void showOcorrences(String elemento) {
        Node pAnda;
        int cont = 0;
        boolean encontrada = false;

        if (isEmpty()) {
            littleBox("Empty list");
        } else {
            pAnda = head;
            littleBox("Searching for occurrence(s)...");
            do {
                cont++;
                Pattern padrao = Pattern.compile(elemento);
                Matcher matcher = padrao.matcher(pAnda.getLinha());
                if (matcher.find())
                    encontrada = true;
                pAnda = pAnda.getProx();
            } while (pAnda != head);
        }
        if (!encontrada && !isEmpty())
            littleBox("Word not found");
        else if (encontrada)
            littleBox("Occurrence(s) shown successfully");
    } // Final do método showOcorrences


    // funcao que troca a String da lista por uma outra String desejada
    public void replaceOcorrences(String elemento, String elemSubstituto) {

        Node pAnda;
        boolean encontrada = false;

        if (isEmpty()) {
            littleBox("Empty list");
        } else {
            pAnda = head;
            do {
                Pattern padrao = Pattern.compile(elemento);
                Matcher matcher = padrao.matcher(pAnda.getLinha());
                if (matcher.find())
                    encontrada = true;
                pAnda.setLinha(pAnda.getLinha().replaceAll(elemento, elemSubstituto));
                pAnda = pAnda.getProx();
            } while (pAnda != head);
        }
        if (!encontrada && !isEmpty())
            littleBox("Word not found");
        else if (encontrada)
            littleBox("Exchange made successfully!");
    } // Final do método replaceOcorrences

    //funcao que remove a linha a partir do indice inserido pelo usuario
    public boolean removeLinePosition(int posicao) {
        Node pAnt = null, pAnda;
        if (isEmpty()) {
            littleBox("Empty list - There is nothing to remove");
            return false; // Empty list
        } else {
            pAnda = head;
            int contador = 1;

            // procura a posição do elemento na lista
            while ((contador != count) && contador != posicao) {
                pAnt = pAnda;
                pAnda = pAnda.getProx();
                contador++;
            }

            if ((contador == count) && contador != posicao)
                return false; // Se não encontrou o elemento
                // Se elemento encontrado remove da lista
            else {
                // se o elemento encontrado está na cabeça da lista
                // e tem somente um elemento
                if ((head == pAnda && count == 1)) {
                    head = null;
                    tail = null;
                    // está no cabeça e tem mais elementos
                } else if ((head == pAnda && count != 1)) {
                    head = head.getProx();
                    tail.setProx(head);
                } else {// remove elemento do meio/fim
                    // Se o elemento estiver no fim
                    if (pAnda == tail && contador == posicao)
                        tail = pAnt; // Atualiza o fim
                    pAnt.setProx(pAnda.getProx());
                }
                count--;
                littleBox("Element removed successfully!");
                return true;
            }
        }
    } // Final do método removeLinePosition

    //funcao que remove as linhas a partir da inserida pelo usuario ate o final da lista
    public boolean removeLineEnd(int posicao) {
        if (isEmpty()) {
            littleBox("Empty list - There is nothing to remove");
            return false; // Empty list
        } else {
            Node pAnt = null, pAnda;
            //System.out.println("A posicao eh " + posicao);
            if (posicao == 1) {
                head = null;
                tail = null;
                littleBox("Range deleted successfully");
                count = 0;
                return true;
            }

            pAnda = head;
            int contador = 1;

            // procura a posição do elemento na lista
            while (pAnda.getProx() != head && contador != posicao) {
                pAnt = pAnda;
                pAnda = pAnda.getProx();
                contador++;
            }
            pAnt.setProx(head);
            tail.setProx(null);
            tail = pAnt;
        }
        count = (posicao - 1);
        littleBox("Range deleted successfully");
        return true;
    } // Final do método removeLineEnd


    //funcao que remove as linhas a partir da inserida pelo usuario ate o inicio da lista
    public boolean removeLineBegin(int posicao) {
        if (isEmpty()) {
            System.out.println("Empty list - There is nothing to remove");
            return false; // Empty list
        } else {

            Node pAnda;
            //System.out.println("A posicao eh " + posicao);
            if (posicao == 1 && count > 1) {
                head = head.getProx();
                tail.setProx(head);
                littleBox("Range deleted successfully");
                return true;
            } else if (posicao == count) {
                head = null;
                tail = null;
                littleBox("Range deleted successfully");
                count = 0;
                return true;
            }

            pAnda = head;
            int contador = 1;

            // procura a posição do elemento na lista
            while (contador != count && contador != posicao && pAnda.getProx() != head) {
                //pAnt = pAnda;
                pAnda = pAnda.getProx();
                contador++;
            }
            head = pAnda.getProx();
            pAnda.setProx(null);
        }
        count -= posicao;
        littleBox("Range deleted successfully");
        return true;
    } // Final do método removeLineBegin


    // funcao que imprime o conteudo da lista
    public void print() {
        if (isEmpty()) {
            littleBox("Empty list - There is Nothing to be Printed");
        }
        Node pAnda;
        int contador = 1;
        Scanner barran = new Scanner(System.in);
        if (!isEmpty()) {
            pAnda = head;
            while (pAnda.getProx() != head && contador <= count) {
                System.out.println(contador + " " + pAnda.getLinha());
                pAnda = pAnda.getProx();
                contador++;
                if (contador % 11 == 0)
                    if (!vrf.askContinue())
                        break;
            }
            if (pAnda == tail)
                System.out.println(contador + " " + pAnda.getLinha());

            littleBox("COMPLETE PRINTING");
        }
    } // Final print()

    //funcao que imprime o intervalo da lista inserido pelo usuario
    public void printInterval(int linhaInicial, int linhaFinal) {
        Node pAnda = head;
        int contador = 1; // Começar a contar a partir de 1
        boolean first = false;

        Scanner barran = new Scanner(System.in);

        if (!isEmpty()) {
            do {
                if (contador >= linhaInicial && contador <= linhaFinal) {
                    System.out.println(contador + " " + pAnda.getLinha());
                    if (contador % 10 == 0 && first) {
                        barran.nextLine();
                    }
                    first = true;
                }
                pAnda = pAnda.getProx();
                contador++;
            } while (pAnda != head); // Permite percorrer a lista circular completamente
        }

        littleBox("RANGE DISPLAYED IN FULL");

    } // Final printaIntevalo()

    //funcao que limpa a lista desejada
    public void clear() {
        if (!isEmpty()) {
            Node pAnt, pAnda = head;
            int contadora = 1;
            while (contadora <= count && pAnda.getProx() != head) {
                pAnt = pAnda;  // Libera o nó
                pAnda = pAnda.getProx();
                pAnt.setProx(null);
                pAnt = null;
                contadora++;
            }
            count = 0;
            tail = head = null;
        }
    } // Final clear()

    @Override
    //funcao que devolve lista em formato de string
    public String toString() {

        StringBuilder sb = new StringBuilder();
        int qtde = 0;
        Node pAnda = head;
        while (qtde != count) {
            if (qtde == count - 1)
                sb.append(pAnda.getLinha());
            else
                sb.append(pAnda.getLinha() + "\n");
            qtde++;
            pAnda = pAnda.getProx();
        }
        //sb.append("\nQtde.: " + count);
        //sb.append("\n");
        return sb.toString();

    } // Final toString()

    // Marca o as linhas entre linIn linF (inclusive)
    public boolean highlighter(int linIn, int linF) {
        if (linIn > 0 && linF <= count && count > 0 && linIn <= linF) {
            countM = 1 + linF - linIn;
            Node pAnda = head;

            for (int i = 1; i <= linF; i++) {
                if (i == linIn) mi = pAnda;
                if (i >= linIn) {
                    System.out.println(i + " " + pAnda.getLinha());
                }
                if (i == linF) mf = pAnda;
                pAnda = pAnda.getProx();
            }
            return true;
        } else {
            littleBox("ERROR! Invalid values!");
            return false;
        }
    } // Final highlighter

    // funcao que copia o texto
    public boolean copyText(CircleLinkedList listaAuxiliar) {
        if (mi != null) {
            listaAuxiliar.clear();
            Node pAnda = mi;
            listaAuxiliar.insertTail(pAnda.getLinha());
            while (pAnda != mf) {
                pAnda = pAnda.getProx();
                listaAuxiliar.insertTail(pAnda.getLinha());
            }
            return true;
        } else {
            littleBox("ERROR! Text not selected.");
            return false;
        }
    } // Final copyText()

    // Corta o texto e copia na lista auxiliar
    public boolean cutText(CircleLinkedList listaAuxiliar) {
        if (mi != null) {
            copyText(listaAuxiliar);
            Node pAnda = head;
            Node ppAnt = pAnda;
            if (mf != tail) {
                if (mi != head) {
                    while (pAnda.getProx() != mi) {
                        ppAnt = pAnda;
                        pAnda = pAnda.getProx();
                    }

                    if (ppAnt != head) ppAnt.getProx().setProx(mf.getProx());
                    else {
                        tail.getProx().setProx(mf.getProx());
                        head = tail.getProx();
                    }
                } else {
                    tail.setProx(mf.getProx());
                    head = tail.getProx();

                }
            } else if (mi != head) {
                while (pAnda.getProx() != mi) {
                    ppAnt = pAnda;
                    pAnda = pAnda.getProx();
                }
                if (ppAnt != head) {
                    ppAnt.getProx().setProx(tail.getProx());
                    tail = ppAnt.getProx();
                } else {
                    tail.getProx().setProx(tail.getProx());
                    head = tail.getProx();
                    tail = head;
                }
            } else {
                clear();
                countM = 0;
                mi = null;
                mf = null;
                littleBox("Message cut successfully");
                return true;
            }

            count -= countM;
            countM = 0;
            mi = null;
            mf = null;
            littleBox("Message cut successfully");
            return true;
        } else {
            littleBox("ERROR! Text not selected.");
            return false;
        }
    } // Final cutText()

// funcao que cola o texto marcado a partir da linha indicada

    public boolean pasteText(int linIn, CircleLinkedList lista, CircleLinkedList listaAuxiliar) {

        if (listaAuxiliar.isEmpty()) {
            littleBox("ERROR! Nothing copied.");
            return false;
        }
        if (linIn > 0) {

            Node pAnda = listaAuxiliar.getHead();
            for (int i = linIn; i <= listaAuxiliar.getCount() + linIn - 1; i++) {
                lista.insertPorIndice(i, pAnda.getLinha());
                pAnda = pAnda.getProx();
            }
            littleBox("Message recorded successfully!");
            return true;
        }else {
            littleBox("ERROR! Invalid values!");
            return false;
        }
    } // Final pasteText()
}

