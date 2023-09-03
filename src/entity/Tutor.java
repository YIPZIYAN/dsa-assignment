/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 *
 * @author Yip Zi Yan
 */
public class Tutor implements Serializable {

    private String tutorId; // auto generate
    private String tutorName;
    private char gender; // M or F
    private String email; // auto generate
    private String status; // [P]art[T]ime, [F]ull[T]ime, [R]e[T]ired, [R]e[S]ign
    private double salary;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
    private static int totalTutor = 0;

    public Tutor() {
    }

    public Tutor(String tutorName, char gender, char status, double salary) {

        this.tutorId = String.format("T%04d", ++totalTutor);
        this.tutorName = tutorName;
        this.gender = Character.toUpperCase(gender);
        this.status = Character.toUpperCase(status)
                == 'P' ? "PT" : "FT";
        this.salary = salary;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();

        generateEmail();

    }

    public Tutor(String tutorName, char gender, char status, double salary, int currentNumber) {

        this.tutorId = String.format("T%04d", currentNumber);
        this.tutorName = tutorName;
        this.gender = Character.toUpperCase(gender);
        this.status = Character.toUpperCase(status)
                == 'P' ? "PT" : "FT";
        this.salary = salary;
        this.created_at = LocalDateTime.now();
        this.updated_at = LocalDateTime.now();

        generateEmail();

    }

    private void generateEmail() {
        if (this.status == "PT") {
            this.email = String.format("p%s@tarumt.edu.my",
                    this.tutorId.substring(1));
        } else {
            this.email = String.format("%s@tarumt.edu.my",
                    this.tutorName
                            .replaceAll("\\s", "")
                            .toLowerCase());
        }
    }

    public double getSalary() {
        return salary;
    }

    public LocalDateTime getCreated_at() {
        return created_at;
    }

    public LocalDateTime getUpdated_at() {
        return updated_at;
    }

    public char getGender() {
        return gender;
    }

    public String getTutorId() {
        return tutorId;
    }

    public String getTutorName() {
        return tutorName;
    }

    public String getEmail() {
        return email;
    }

    public static int getTotalTutor() {
        return totalTutor;
    }

    public String getStatus() {
        return status;
    }

    public void setSalary(double salary) {
        this.salary = salary;
        this.updated_at = LocalDateTime.now();
    }

    public void setGender(char gender) {
        this.gender = gender;
        this.updated_at = LocalDateTime.now();
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
        generateEmail();
        this.updated_at = LocalDateTime.now();
    }

    public void setEmail(String email) {
        this.email = email;
        this.updated_at = LocalDateTime.now();
    }

    public static void setTotalTutor(int totalTutor) {
        Tutor.totalTutor = totalTutor;
    }

    public void setStatus(String status) {
        this.status = status;
        if (isWorking()) {
            generateEmail();
        }
        this.updated_at = LocalDateTime.now();
    }

    private boolean isWorking() {
        return this.status == "PT" || this.status == "FT";
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.tutorId);
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
        final Tutor other = (Tutor) obj;
        return Objects.equals(this.tutorId, other.tutorId);
    }

    @Override
    public String toString() {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return String.format("%-8s %-26s %-10s %-32s %8.2f %12s %22s %22s",
                tutorId, tutorName, getGenderStr(),
                email, salary, getStatusStr(),
                created_at.format(formatter), updated_at.format(formatter));
    }

    public String getStatusStr() {
        String status;
        switch (this.status) {
            case "FT":
                status = "Full-Time";
                break;
            case "PT":
                status = "Part-Time";
                break;
            case "RS":
                status = "Resigned";
                break;
            case "RT":
                status = "Retired";
                break;
            default:
                status = "Unknown";
        }
        return status;
    }

    public String getGenderStr() {
        return gender == 'M' ? "Male" : "Female";
    }

    public boolean isFemale() {
        return gender == 'F' ? true : false;
    }

}
