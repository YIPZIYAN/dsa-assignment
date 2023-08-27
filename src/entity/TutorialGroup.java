/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author Yip Zi Yan
 */
public class TutorialGroup {
    private String groupId;
    private int numOfStudent;

    public TutorialGroup() {
    }

    public TutorialGroup(String groupId) {
        this.groupId = groupId;
        this.numOfStudent = 0;
    }

    public TutorialGroup(String groupId, int numOfStudent) {
        this.groupId = groupId;
        this.numOfStudent = numOfStudent;
    }
    
    
}
