package ucll.project.domain.db;

import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Person;
import ucll.project.domain.model.Student;
import ucll.project.domain.model.VdabStudent;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface PersonDB {
    List<Student> getAll();
    Person get(String userid);
    void delete(String userid);
    List<Student> getAllPeopleFromLes(int id);
    void updateVDAB(String[] checkedIds);
    void removeParticipantFromClass(String id, int lesid);
    void confirmParticipants(int lesId);
    List<VdabStudent> getStudentReports();
    void changeCommentaar(String userid, String id, String commentaar);
    List<Student> getAllvdabStudents();
    List<Lector> getAllstudentencoachen();
    void setstudentencoach(String userid,String coachid);
    List<VdabStudent> getStudentReportsBetweenDates(Date from, Date until);
    List<Student> getAfwezigeStudenten(String reeks, String lesId);
    String getCommentaarFromUser(String userid, String id);
    List<VdabStudent> getAantalUrenVanVdabStudentenTussenDates(Timestamp van, Timestamp tot);

}
