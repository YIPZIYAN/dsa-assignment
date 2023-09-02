/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

/**
 *
 * @author Goh Chun Yen
 */
public class Programme implements Serializable{
    private String programmeCode;
    private String programmeName;
    private String programmeType;
    private static int totalProgramme = 0;

    public Programme() {
    }

    public Programme(String programmeCode, String programmeName, String programmeType) {
        this.programmeCode = programmeCode;
        this.programmeName = programmeName;
        this.programmeType = programmeType;
    }

    public String getProgrammeCode() {
        return programmeCode;
    }

    public String getProgrammeName() {
        return programmeName;
    }

    public String getProgrammeType() {
        return programmeType;
    }

    public static int getTotalProgramme() {
        return totalProgramme;
    }

    public void setProgrammeCode(String programmeCode) {
        this.programmeCode = programmeCode;
    }

    public void setProgrammeName(String programmeName) {
        this.programmeName = programmeName;
    }

    public void setProgrammeType(String programmeType) {
        this.programmeType = programmeType;
    }

    public static void setTotalProgramme(int totalProgramme) {
        Programme.totalProgramme = totalProgramme;
    }

    @Override
    public String toString() {
        return String.format("%-15s%-50s%-20s", programmeCode, programmeName, programmeType);
    }
    
    
    
}
