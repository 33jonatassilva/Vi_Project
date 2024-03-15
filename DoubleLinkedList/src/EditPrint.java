import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class EditPrint {
    public void beginBanner(){
        try {
            File myObj = new File("Tittle.txt");
            Scanner myReader = new Scanner(myObj);
            int cont = 1;
            while (cont <= 15) {
                String data = myReader.nextLine();
                System.out.println(data);
                Thread.sleep(150);
                cont++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    public void endBanner(){
        try {
            File myObj = new File("Tittle.txt");
            Scanner myReader = new Scanner(myObj);
            int cont = 1;
            while (myReader.hasNextLine()) {

                    String data = myReader.nextLine();
                if(cont >= 16) {
                    System.out.println(data);
                    Thread.sleep(150);
                }
                cont++;
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
