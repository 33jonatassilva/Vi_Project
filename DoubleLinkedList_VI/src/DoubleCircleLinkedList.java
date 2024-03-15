import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoubleCircleLinkedList {
    private Node head;
    private int count;
    private Node lBegin;
    private Node lEnd;
    private int countHigh;
    public int getCount(){return count;}
    public Node getHead(){return head;}
    public Node getTail(){return  head.getLeft();}
    FullVerification vrf = new FullVerification();
    public DoubleCircleLinkedList(){head = lBegin = lEnd = null; count = countHigh = 0;}
    public boolean isFull() { Node aux = new Node(); return aux == null;}
    public boolean isEmpty() {
        return count == 0;
    }
    private void littleBox(String frase) {
        int ei, ef, t;
        t = 72 - frase.length();
        ei = ef = t / 2;
        if (t % 2 == 1) ef++;
        System.out.println("+" + "-".repeat(72) + "+");

        System.out.println("|" + " ".repeat(ei) + frase + " ".repeat(ef) + "|");
        System.out.println("+" + "-".repeat(72) + "+");
    }

    public boolean insertTail(String num) {
        if (!isFull()) {
            Node aux = new Node();
            aux.setLine(num);
            if (!isEmpty()) {
                if (count == 1) {
                    head.setRight(aux);
                    aux.setLeft(head);
                    head.setLeft(aux);
                    aux.setRight(head);
                    count++;
                    return true;
                }
                head.getLeft().setRight(aux);
                aux.setLeft(head.getLeft());
                aux.setRight(head);
                head.setLeft(aux);
                count++;
                return true;
            }
            head = aux;
            head.setLeft(head);
            head.setRight(head);
            count++;
        }
        return false;
    } // End of insertTail()


    public boolean insertIn(int indice, String linha){
        if(!isFull()){
            Node aux = new Node();
            aux.setLine(linha);

            if(indice == 1){
                if(isEmpty()){
                    head = aux;
                    head.setRight(head);
                    head.setLeft(head);
                    count++;
                    return true;
                }
                aux.setLeft(head.getLeft());
                aux.setRight(head);
                head.getLeft().setRight(aux);
                head.setLeft(aux);
                head = aux;
                count++;
                return true;
            }
            Node pAnda = head;
            int contador = 1;
            while (contador < indice){
                pAnda = pAnda.getRight();
                contador++;
            }
            pAnda.getLeft().setRight(aux);
            aux.setLeft(pAnda.getLeft());
            pAnda.setLeft(aux);
            aux.setRight(pAnda);
            count++;
            return true;
        }
        return false;
    } // End of insertIn()

    public boolean insertText(int index){
        if(!isFull()) {
            Scanner sc = new Scanner(System.in);
            while (true) {
                String line = sc.nextLine();
                if (line.equals(":a")) break;
                insertIn(index, line);
                index++;
            }
            return true;
        }
        return false;
    } // End of insertText()


    public boolean removeIn(int indice) {
        if (!isEmpty()) {
            Node aux = head;
            if (indice == 1) {
                if (count == 1) {
                    head = null;
                    count = 0;
                    return true;
                }
                head.getRight().setLeft(head.getLeft());
                head.getLeft().setRight(head.getRight());
                head = head.getRight();
                aux.setRight(null);
                aux.setLeft(null);
                count--;
                return true;
            }
            int contador = 1;
            while (contador != indice) {
                aux = aux.getRight();
                contador++;
            }
            aux.getRight().setLeft(aux.getLeft());
            aux.getLeft().setRight(aux.getRight());
            aux = null;
            count--;
            return true;
        }
        return false;
    } // End of removeIn()

    public boolean removeEnd(int indice) {
        if (!isEmpty()) {
            Node aux = head;
            if (indice == 1) {
                head = null;
                count = 0;
                return true;
            }
            int contador = 1;
            while (contador != indice) {
                aux = aux.getRight();
                contador++;
            }
            aux.getLeft().setRight(head);
            head.setLeft(aux.getLeft());
            count -= (count - indice) + 1;
            return true;
        }
        return false;
    } // End of removeEnd()


    public boolean removeBeg(int indice){
        if (!isEmpty()) {
            Node aux = head;
            if (indice == 1 && count == 1) {
                head = null;
                count = 0;
                return true;
            }
            int contador = 1;
            while (contador != indice) {
                aux = aux.getRight();
                contador++;
            }
            head.getLeft().setRight(aux.getRight());
            aux.getRight().setLeft(head.getLeft());
            head = aux.getRight();
            count -= indice;
            return true;
        }
        return false;
    } // End of removeBeg()


    public String toString() {

        StringBuilder sb = new StringBuilder();
        int qtde = 0;
        Node pAnda = head;
        while (qtde != count) {
            if (qtde == count - 1)
                sb.append(pAnda.getLine());
            else
                sb.append(pAnda.getLine() + "\n");
            qtde++;
            pAnda = pAnda.getRight();
        }
        return sb.toString();
    } // End of toString()

    public boolean showAll(){
        if(!isEmpty()) {
            Node aux = head;
            int contador = 1;
            //System.out.println("Count é: " + count);
            while (contador <= count) {
                if(contador == count)
                    System.out.println(contador + " " + aux.getLine());
                else
                    System.out.println(contador + " " + aux.getLine());
                aux = aux.getRight();
                contador++;
                if((contador - 1) % 10 == 0 && contador <= count) {
                    if(!vrf.askContinue())
                        return true;
                }
            }
            return true;
        }
        return false;
    } // End of showAll()

    public boolean showInterval(int begin, int end){
        if(!isEmpty()){
            int contador2 = 0;
            Node aux = head;
            String texto = "";
            int contador = 1;
            while (contador <= end){
                if(contador >= begin) {
                    System.out.println(contador + " " + aux.getLine());
                    contador2++;
                }
                if(contador2 != 0 && contador2  % 10 == 0 && count != contador && contador2 != end) {
                    if (!vrf.askContinue())
                        return true;
            }
            aux = aux.getRight();
            contador++;
        }
        return true;
    }
        return false;
} // End of showInterval()


    public boolean showOccurrences(String element){
        if(!isEmpty()){
            boolean searched = false;
            Node aux = head;
            int cont = 1;
            do {
                Pattern padrao = Pattern.compile(element);
                Matcher matcher = padrao.matcher(aux.getLine());
                if (matcher.find()) {
                    System.out.println(cont + " " + aux.getLine());
                    searched = true;
                }
                aux = aux.getRight();
                cont++;
            } while (aux != head);
            return searched;
        }
        return false;
    } // End of showOccurrences()
    

    public boolean replaceOccurrences(String element, String elemSubstituto) {
        if(!isEmpty()){
            boolean searched = false;
            Node aux = head;
            int cont = 1;
            do {
                Pattern padrao = Pattern.compile(element);
                Matcher matcher = padrao.matcher(aux.getLine());
                if (matcher.find())
                    searched = true;
                aux.setLine(aux.getLine().replaceAll(element, elemSubstituto));
                aux = aux.getRight();
            } while (aux != head);
            return searched;
        }
        return false;
    } // End of replaceOccurrences()


    public boolean highlighter(int lBegin, int lEnd){
        if(!isEmpty()){
            Node aux = head;
            int cont = 1;
            while (cont <= lEnd){
                if(cont >= lBegin){
                    System.out.println(cont + " " + aux.getLine());
                    if(cont == lBegin)
                        this.lBegin = aux;
                    if(cont == lEnd)
                        this.lEnd = aux;
                }
                aux = aux.getRight();
                cont++;
            }
            countHigh = 1 + lEnd - lBegin;
            return true;
        }
        return false;
    } // End of highlighter()

    public boolean copy(DoubleCircleLinkedList listAux){
        //if(!listAux.isEmpty()) {
            if (lBegin != null) {
                listAux.clear();
                Node aux = lBegin;
                while (aux != lEnd) {
                    listAux.insertTail(aux.getLine());
                    aux = aux.getRight();
                }
                listAux.insertTail(aux.getLine());
                return true;
            }
        //}
        littleBox("ERROR! Text not selected.");
        return false;
    } // End of copy()


    public boolean cut(DoubleCircleLinkedList listAux){
        if(lBegin != null){
            copy(listAux);
            if(lBegin != head){
                if(lEnd != head.getLeft()){
                    lBegin.getLeft().setRight(lEnd.getRight());
                    lEnd.getRight().setLeft(lBegin.getLeft());
                } else {
                  lBegin.getLeft().setRight(head);
                  head.setLeft(lBegin.getLeft());
                }
            } else {
                if(lEnd != head.getLeft()){
                    lEnd.getRight().setLeft(head.getLeft());
                    head.getLeft().setRight(lEnd.getRight());
                    head = lEnd.getRight();
                } else {
                    count = 0;
                    countHigh = 0;
                    lBegin = lEnd = null;
                    return true;
                }

            }
            count -= countHigh;
            countHigh = 0;
            lBegin = null;
            lEnd = null;
            return true;
        }
        littleBox("ERROR! Text not selected.");
        return false;
    } // End of cut()


    public boolean paste(int index, DoubleCircleLinkedList listAux){
        if(!listAux.isEmpty()){
            Node aux = listAux.getHead();
            for(int i = index; i <= listAux.getCount() + index - 1; i++){
                insertIn(i, aux.getLine());
                aux = aux.getRight();
            }
            return true;
        }
        return false;
    } // End of paste()


    public void clear() {
        if (!isEmpty()) {
            Node aux = head;
            int cont = 1;
            while (cont <= count && aux.getRight() != head) {
                aux = aux.getRight(); // Libera o nó
                aux.getLeft().setRight(null);
                aux.getLeft().setLeft(null);
                aux.getLeft().setLine("");
                cont++;
            }
            count = 0;
            head = null;
        }
    } // End of clear()
}
