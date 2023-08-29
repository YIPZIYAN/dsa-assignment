
package entity;

import java.util.Objects;

/**
 *
 * @author Goh Chun Yen
 */
public class Course {
    
    private String courseCode;
    private String courseName;
    private int courseCreditHours;
    private String courseDepartment;
    private double courseFees;
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

    @Override
    public String toString() {
        return String.format("%5s %12s %8s %18s %12s", courseCode, courseName, courseCreditHours, courseDepartment, courseFees);
    }
    
    
    
}
