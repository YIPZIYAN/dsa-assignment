/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import entity.Programme;

/**
 *
 * @author Cy
 */
public class ProgrammeSeeder {

    ListInterface<Programme> programmeList = new CircularDoublyLinkedList<>();

    public ProgrammeSeeder() {
        programmeList.add(new Programme("RSD", "Bachelor of Computer Science in Data Science", "Bachelor Degree"));
        programmeList.add(new Programme("DCS", "Diploma in Computer Science", "Diploma"));
        programmeList.add(new Programme("RAC", "Bachelor of Accounting", "Bachelor Degree"));
        programmeList.add(new Programme("REE", "Bachelor of Electronics Engineering Technology", "Bachelor Degree"));
        programmeList.add(new Programme("RFS", "Bachelor of Science in Food Science", "Bachelor Degree"));
        programmeList.add(new Programme("RRE", "Bachelor of Real Estate Management", "Bachelor Degree"));
        programmeList.add(new Programme("RMS", "Bachelor of Communication in Media Studies", "Bachelor Degree"));
        programmeList.add(new Programme("FIS", "Foundation in Science", "Foundation"));
        programmeList.add(new Programme("DCS", "Diploma in Computer Science", "Diploma"));

    }

    public ListInterface<Programme> getProgrammeList() {
        return programmeList;
    }

}
