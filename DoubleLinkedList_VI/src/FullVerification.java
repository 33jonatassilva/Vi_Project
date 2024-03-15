import java.io.FileWriter;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class FullVerification {

    public void littleBox(String frase) {
        int ei, ef, t;
        t = 72 - frase.length();
        ei = ef = t / 2;
        if (t % 2 == 1) ef++;
        System.out.println("+" + "-".repeat(72) + "+");

        System.out.println("|" + " ".repeat(ei) + frase + " ".repeat(ef) + "|");
        System.out.println("+" + "-".repeat(72) + "+");
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
        if(begin > end || begin <= 0 || end > size) {
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
