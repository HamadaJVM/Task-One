import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Scanner;

public class storing {

    public static void add(int id, String name, String stage)  {
        try {
            File file = new File("table.txt");
            File tempFile = new File("temp.txt");
            Scanner scanner = new Scanner(file);
            PrintWriter tempWriter = new PrintWriter(tempFile);
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                tempWriter.println(line);
            }
            tempWriter.println(id+":"+name+":"+stage);//adding the new data
            tempWriter.close();
            PrintWriter writer = new PrintWriter(file);
            Scanner tempScanner = new Scanner(tempFile);
            while (tempScanner.hasNextLine()){
                String line = tempScanner.nextLine();
                writer.println(line);
            }
            writer.close();
            System.out.println("تم الاضافة بنجاح.");

        }catch (Exception e){
            System.out.println("ERROR :" + e.getMessage());
        }
    }}
