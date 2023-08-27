/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import adt.ArrayList;
import java.util.Objects;

/**
 *
 * @author Yip Zi Yan
 */
public class Tutor {

    private String tutorId;
    private String tutorName;
    private String gender;
    private String email;
    private static int totalTutor = 0;
    private ArrayList<TutorialGroup> handleGroup;
    private String status; // Part-time, Full-time, Retired

    public Tutor() {
    }

    public Tutor(String tutorName, String gender, String email, String status) {
        this.tutorId = String.format("%04s", ++totalTutor);
        this.tutorName = tutorName;
        this.gender = gender;
        this.email = email;
        this.status = status;
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

    public ArrayList<TutorialGroup> getHandleGroup() {
        return handleGroup;
    }

    public String getStatus() {
        return status;
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

    public void setHandleGroup(ArrayList<TutorialGroup> handleGroup) {
        this.handleGroup = handleGroup;
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

        return String.format("%s %s %s %s", tutorId, gender, tutorName, gender, email, status);
    }

}
