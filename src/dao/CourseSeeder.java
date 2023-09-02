package dao;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import entity.Course;

/**
 *
 * @author Goh Chun Yen
 */
public class CourseSeeder {

    ListInterface<Course> courseList = new CircularDoublyLinkedList<>();

    public CourseSeeder() {
        courseList.add(new Course("BBMF3183", "Strategic Financial Management", 3, "FAFB", 500.00));
        courseList.add(new Course("BBFT3024", "Advanced Taxation", 4, "FAFB", 700.00));
        courseList.add(new Course("BTCM3014", "Contract Administration", 4, "FOBE", 700.00));
        courseList.add(new Course("BTAR3073", "Principles Of Architectural Practice", 3, "FOBE", 500.00));
        courseList.add(new Course("BAFS3353", "Food Fermentation", 3, "FOAS", 500.00));
        courseList.add(new Course("BASE3643", "Health, Fitness and Disease", 3, "FOAS", 500.00));
        courseList.add(new Course("BHMC2004", "Communication Theories", 4, "FCCI", 700.00));
        courseList.add(new Course("BHMS3114", "Politics And The Media", 4, "FCCI", 700.00));
        courseList.add(new Course("BACS2063", "Data Structures And Algorithms", 3, "FOCS", 500.00));
        courseList.add(new Course("BAIT1013", "Introduction To Computer Networks", 3, "FOCS", 500.00));
        courseList.add(new Course("BGMC3263", "Heat Transfer", 3, "FOET", 500.00));
        courseList.add(new Course("BTEE4643", "Power Quality", 3, "FOET", 500.00));
        courseList.add(new Course("BHTM2244", "Inbound Tour Business", 4, "FSSH", 700.00));
        courseList.add(new Course("BHPY2023", "Organisational Behaviour", 3, "FSSH", 500.00));
        courseList.add(new Course("FPCH1024", "Physical And Inorganic Chemistry", 4, "CPUS", 700.00));
        courseList.add(new Course("FPMA1024", "Quantitative Methods", 4, "CPUS", 700.00));

    }

    public ListInterface<Course> getCourseList() {
        return courseList;
    }

}
