package dao;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import java.io.*;

/**
 *
 * @author Yip Zi Yan, Goh Chun Yen
 */
public class DAO<T>{

    private String fileName;

    public DAO() {
    }

    public DAO(String fileName) {
        this.fileName = fileName;
    }

    public void saveToFile(ListInterface<T> dataList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = 
                    new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(dataList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println(ex);
            System.out.println("\nCannot save to file");
        }
    }

    public ListInterface<T> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<T> dataList = new CircularDoublyLinkedList<>();
        try {
            ObjectInputStream oiStream = 
                    new ObjectInputStream(new FileInputStream(file));
            dataList = (CircularDoublyLinkedList<T>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
                        System.out.println(ex);

            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return dataList;
        }
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
