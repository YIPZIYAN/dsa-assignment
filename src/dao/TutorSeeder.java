package dao;

import adt.CircularDoublyLinkedList;
import adt.ListInterface;
import entity.Tutor;
import java.time.LocalDateTime;

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
        tutorList.add(new Tutor("Yip Zi Yan",'M','P',10000,LocalDateTime.of(2022, 12, 14, 12, 14)));
        tutorList.add(new Tutor("Goh Chun Yen",'M','F',8800,LocalDateTime.of(2022, 12, 15, 3, 4)));
        tutorList.add(new Tutor("Chew Lip Sin",'M','F',8800,LocalDateTime.of(2022, 12, 15, 5, 3)));
        tutorList.add(new Tutor("Lim Yi Leong",'M','F',8800,LocalDateTime.of(2022, 12, 16, 1, 1)));
        tutorList.add(new Tutor("David Low",'M','F',8500));
        tutorList.add(new Tutor("Shoong Wai Kin",'M','F',8200));
        tutorList.add(new Tutor("Kok Wai Keong",'M','F',8550));
        tutorList.add(new Tutor("Pong Suk Fun",'F','F',8400));
        tutorList.add(new Tutor("Loo Bee Wah",'F','F',8600));
        tutorList.add(new Tutor("Ong Jia Hui",'M','F',8000));
        tutorList.add(new Tutor("Yong Shang Qian",'M','P',6600));
        tutorList.add(new Tutor("Chaw Thim Vai",'F','F',8750));
        tutorList.add(new Tutor("Calvin Lim Seng Wah",'M','P',3000));
        tutorList.add(new Tutor("Loh Kiean Nyak",'M','P',7880));
        tutorList.add(new Tutor("Tan Li Peng",'F','F',8900));
        tutorList.add(new Tutor("Yap Saw Teng",'F','F',8400));
        tutorList.add(new Tutor("Gan Lay Kee",'F','F',8600));
        tutorList.add(new Tutor("Ding Ying Hong",'M','F',8800));
        tutorList.add(new Tutor("Lee Soo Lin",'F','F',7650));
        tutorList.add(new Tutor("Too Wei Chin",'F','F',8500));
        tutorList.add(new Tutor("Loh Chuang Li",'M','P',2200));
        tutorList.add(new Tutor("Tan Zi Xuan",'M','P',3300));
        tutorList.add(new Tutor("Kong Yih Hern",'M','P',4750));
        tutorList.add(new Tutor("Cho Wee Hing",'M','P',4320));
        tutorList.add(new Tutor("Lim Tong Ming",'M','F',20200));
        tutorList.add(new Tutor("Ho Chee Yuen",'M','F',17580));
    }

    public ListInterface<Tutor> getTutorList() {
        return tutorList;
    }
    
    

}
