import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
public class Main {
    static File file = new File("table.txt");
    static Scanner cin = new Scanner(System.in);
    static ArrayList<String[]> rows;
    static {
        try {
            rows = readRowsFromFile(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static void add() {
        try {
             String[] data = inPutData();
            if (check(Integer.parseInt(data[0]), rows)){
                System.out.println("Try to add number Higher than :" + getMaxId(rows));
                throw new MyException();}
            rows.add(rows.size(),data);
            writeRowsToFile();
        } catch (Exception e) {
            System.out.println("ERROR :" + e.getMessage() );
        }
    }
    public static void edite() throws IOException {
        String[] data = inPutData();
        if (check(Integer.parseInt(data[0]), rows)){
            for (int i = 0; i < rows.size(); i++){
                if ( Integer.parseInt(data[0]) == Integer.parseInt(rows.get(i)[0]) ){
                    rows.set(i,data);
                    break;}
            }
            writeRowsToFile();
        }else System.out.println("There is no such ID");
    }
    public static void search() throws IOException {
        System.out.println("Enter the ID of the Person that you wanna get its info");
        int id = cin.nextInt();
        if (check(id, rows)){
            for (String[] row : rows) {
                if (id == Integer.parseInt(row[0])) {
                    System.out.println(Arrays.toString(row));
                    break;
                }
            }
            writeRowsToFile();
            System.out.println("DONE!");
        }else System.out.println("There is no such ID");
    }
    public static void remove() throws IOException {
        System.out.println("Enter the ID of the Person that you wanna remove it");
        int id = cin.nextInt();
        if (check(id, rows)){
            for (int i = 0; i < rows.size(); i++){
                if ( id == Integer.parseInt(rows.get(i)[0]) ){
                    rows.remove(i);
                    break;}
                System.out.println("DONE !");
            }
            writeRowsToFile();
        }else System.out.println("There is no such ID");
    }


    public static void main(String[] args) {
        try {
            int choice ;
            Scanner cin = new Scanner(System.in);
            do {
                System.out.print("""
                        
                        1. Display
                        2. Add
                        3. Remove
                        4. Edit
                        5. Search
                        6. Exit
                        Enter your choice:\s""");
                choice = cin.nextInt();
                cin.nextLine(); // Consume newline character
                switch (choice) {
                    case 1 -> printData(rows);
                    case 2 -> add();
                    case 3 -> remove();
                    case 4 -> edite();
                    case 5 -> search();
                    case 6 -> System.out.println("Goodbye!");
                    default -> System.out.println("Invalid choice, try again.");
                }


            }while (choice != 6);

        } catch (Exception e) {
            System.out.println("There is an Error: " + e.getMessage());
        }
    }
    public static ArrayList<String[]> readRowsFromFile(File file) throws FileNotFoundException {
        ArrayList<String[]> rows = new ArrayList<>();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] row = scanner.nextLine().split(":");
                rows.add(row);
            }
        }
        return rows;
    }
    public static void writeRowsToFile() throws IOException {
        FileOutputStream fos = new FileOutputStream(file);
        for (String[] arr : rows) {
            fos.write((arr[0]+":"+arr[1]+":"+arr[2]+"\n").getBytes());
        }
        fos.close();
    }
    public static boolean check(int number,ArrayList<String[]> rows) {
        for (String[] r : rows) {
            if (Integer.parseInt(r[0]) == number)
                return true;
        }
        return false;
    }
    public static int getMaxId(ArrayList<String[]> rows)  {
        int max = 0;
        for (String[] r : rows) {
            if(max < Integer.parseInt(r[0]))
                max = Integer.parseInt(r[0]);
        }
        return max;
    }
    public static void printData(ArrayList<String[]> rows) {
        System.out.println("Printing rows...");
        for (String[] row : rows) {
            System.out.println(row[0] + ":\t" + row[1] + ":\t" + row[2]);
        }
        System.out.println();
    }
    public static String[] inPutData(){
        String name,stage, id;
        do {
            System.out.println("Enter the ID ");
            id = cin.nextLine();
        }while (!id.matches("\\d+"));
        System.out.println("Enter the Name ");
        name = cin.nextLine();
        System.out.println("Enter the Stage ");
        stage = cin.nextLine();
        return new String[]{id, name, stage};
    }
}