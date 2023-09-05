package entity;

import adt.*;
import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Goh Chun Yen
 */
public class Course implements Serializable {

    private String courseCode;
    private String courseName;
    private int courseCreditHours;
    private String courseDepartment;
    private double courseFees;
    private ListInterface<Programme> programmes = new CircularDoublyLinkedList<>();
    private static int totalCourse = 0;

    public Course() {
    }

    public Course(String courseCode, String courseName, int courseCreditHours, String courseDepartment, double courseFees) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.courseCreditHours = courseCreditHours;
        this.courseDepartment = courseDepartment;
        this.courseFees = courseFees;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCourseCreditHours() {
        return courseCreditHours;
    }

    public String getCourseDepartment() {
        return courseDepartment;
    }

    public double getCourseFees() {
        return courseFees;
    }

    public ListInterface<Programme> getProgrammes() {
        return programmes;
    }

    public static int getTotalCourse() {
        return totalCourse;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void setCourseCreditHours(int courseCreditHours) {
        this.courseCreditHours = courseCreditHours;
    }

    public void setCourseDepartment(String courseDepartment) {
        this.courseDepartment = courseDepartment;
    }

    public void setCourseFees(double courseFees) {
        this.courseFees = courseFees;
    }

    public void setProgrammes(ListInterface<Programme> programmes) {
        this.programmes = programmes;
    }

    public static void setTotalCourse(int totalCourse) {
        Course.totalCourse = totalCourse;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.courseCode);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Course other = (Course) obj;
        if (!Objects.equals(this.courseCode, other.courseCode)) {
            return false;
        }
        return true;
    }

    public String toProgrammeReportString() {
        String outputStr = String.format("%-12s%-45s", courseCode, courseName);

        return outputStr;

    }

    @Override
    public String toString() {
        String outputStr = String.format("%-12s%-45s%-13d%-11s%-8.2f", courseCode, courseName, courseCreditHours, courseDepartment, courseFees);
        if (programmes.isEmpty()) {
            return outputStr;
        }

        outputStr += "\n    Programme List: ";
        for (int i = 0; i < programmes.getNumberOfEntries(); i++) {
            outputStr += String.format("\n\t%-4s%-50s", programmes.getEntry(i).getProgrammeCode(), programmes.getEntry(i).getProgrammeName());
        }
        return outputStr;

    }

}
