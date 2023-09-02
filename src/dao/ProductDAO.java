package dao;

import adt.*;
import entity.Course;
import java.io.*;

/**
 *
 * @author Kat Tan
 */
public class ProductDAO {

    private String fileName = "courses.dat"; // For security and maintainability, should not have filename hardcoded here.
/*
    public void saveToFile(ListInterface<Product> productList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(productList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println("\nCannot save to file");
        }
    }

    public ListInterface<Product> retrieveFromFile() {
        File file = new File(fileName);
        ListInterface<Product> productList = new ArrayList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            productList = (ArrayList<Product>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return productList;
        }
    }
*/
    public void saveToFileCourse(ListInterface<Course> courseList) {
        File file = new File(fileName);
        try {
            ObjectOutputStream ooStream = new ObjectOutputStream(new FileOutputStream(file));
            ooStream.writeObject(courseList);
            ooStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nFile not found");
        } catch (IOException ex) {
            System.out.println(ex);
            System.out.println("\nCannot save to file");
        }
    }

    public ListInterface<Course> retrieveFromFileCourse() {
        File file = new File(fileName);
        ListInterface<Course> courseList = new CircularDoublyLinkedList<>();
        try {
            ObjectInputStream oiStream = new ObjectInputStream(new FileInputStream(file));
            courseList = (CircularDoublyLinkedList<Course>) (oiStream.readObject());
            oiStream.close();
        } catch (FileNotFoundException ex) {
            System.out.println("\nNo such file.");
        } catch (IOException ex) {
            System.out.println(ex);
            System.out.println("\nCannot read from file.");
        } catch (ClassNotFoundException ex) {
            System.out.println("\nClass not found.");
        } finally {
            return courseList;
        }

    }
}
