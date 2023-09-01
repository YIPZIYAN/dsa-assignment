/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import entity.Tutor;

/**
 *
 * @author Yip Zi Yan
 *
 * Used to initialize fake data
 *
 */
public class TutorSeeder {

    ListInterface<Tutor> tutorList = new CircularDoublyLinkedList<>();

    public TutorSeeder() {
        tutorList.add(new Tutor("Yip Zi Yan",'M','P'));
        tutorList.add(new Tutor("Goh Chun Yen",'M','F'));
        tutorList.add(new Tutor("Chew Lip Sin",'M','F'));
        tutorList.add(new Tutor("Lim Yi Leong",'M','F'));
        tutorList.add(new Tutor("David Low",'M','F'));
        tutorList.add(new Tutor("Shoong Wai Kin",'M','F'));
        tutorList.add(new Tutor("Kok Wai Keong",'M','F'));
        tutorList.add(new Tutor("Pong Suk Fun",'F','F'));
        tutorList.add(new Tutor("Loo Bee Wah",'F','F'));
        tutorList.add(new Tutor("Ong Jia Hui",'M','F'));
        tutorList.add(new Tutor("Yong Shang Qian",'M','P'));
        tutorList.add(new Tutor("Chaw Thim Vai",'F','F'));
        tutorList.add(new Tutor("Calvin Lim Seng Wah",'M','P'));
        tutorList.add(new Tutor("Loh Kiean Nyak",'M','P'));
        tutorList.add(new Tutor("Tan Li Peng",'F','F'));
        tutorList.add(new Tutor("Yap Saw Teng",'F','F'));
        tutorList.add(new Tutor("Gan Lay Kee",'F','F'));
        tutorList.add(new Tutor("Ding Ying Hong",'M','F'));
        tutorList.add(new Tutor("Lee Soo Lin",'F','F'));
        tutorList.add(new Tutor("Too Wei Chin",'F','F'));
        tutorList.add(new Tutor("Loh Chuang Li",'M','P'));
        tutorList.add(new Tutor("Tan Zi Xuan",'M','P'));
        tutorList.add(new Tutor("Kong Yih Hern",'M','P'));
        tutorList.add(new Tutor("Cho Wee Hing",'M','P'));
        tutorList.add(new Tutor("Lim Tong Ming",'M','F'));
        tutorList.add(new Tutor("Ho Chee Yuen",'M','F'));
    }

    public ListInterface<Tutor> getTutorList() {
        return tutorList;
    }
    
    

}
