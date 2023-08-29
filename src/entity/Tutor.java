/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.exampleAdt.ArrayList;
import java.util.Objects;

/**
 *
 * @author Yip Zi Yan
 */
public class Tutor {

    private String tutorId; // auto generate
    private String tutorName;
    private char gender; // M or F
    private String email; // auto generate
    private String status; // PT, FT, RT
    private static int totalTutor = 0;

    public Tutor() {
    }

    public Tutor(String tutorName, char gender, String status) {

        this.tutorId = String.format("T%04d", ++totalTutor);
        this.tutorName = tutorName;
        this.gender = Character.toUpperCase(gender);
        this.status = status.toUpperCase();

        //auto create email
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

    public void setGender(char gender) {
        this.gender = gender;
    }

    public void setTutorId(String tutorId) {
        this.tutorId = tutorId;
    }

    public void setTutorName(String tutorName) {
        this.tutorName = tutorName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTotalTutor(int totalTutor) {
        this.totalTutor = totalTutor;
    }

    public void setStatus(String status) {
        this.status = status;
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

        return String.format("%5s %12s %8c %18s %12s", tutorId, tutorName, gender, email, status);
    }

}
