package dao;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import entity.Programme;

/**
 *
 * @author Goh Chun Yen
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

    }

    public ListInterface<Programme> getProgrammeList() {
        return programmeList;
    }

}
