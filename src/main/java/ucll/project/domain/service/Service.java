package ucll.project.domain.service;

import ucll.project.domain.db.LesDB;
import ucll.project.domain.db.LessDBSQL;
import ucll.project.domain.db.PersonDB;
import ucll.project.domain.db.PersonDBSQL;
import ucll.project.domain.model.Lector;
import ucll.project.domain.model.Les;
import ucll.project.domain.model.Person;
import ucll.project.domain.model.Student;
import ucll.project.domain.model.VdabStudent;
import ucll.project.util.DbConnectionService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public class Service {
    private Connection connection;
    private String schema;

    public Service() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSearchPath();
    }

    PersonDB personDb = new PersonDBSQL();
    LesDB lesDB = new LessDBSQL();

    public Person getPerson(String userid) {
        return personDb.get(userid);
    }

    public boolean isCorrectPassword(String userid, String password) {
        Person person = personDb.get(userid);
        return person.isCorrectPassword(password);
    }

    public List<Student> getAllPeople() {
        return personDb.getAll();
    }
    public List<Lector> getAllstudentencoachen(){return  personDb.getAllstudentencoachen();}
    public  void setstudentencoach(String userid,String coachid){personDb.setstudentencoach(userid, coachid);}



    public List<Student> getAllPeopleFromLes(int id) {
        return personDb.getAllPeopleFromLes(id);
    }
    public List<Student> getAllvdabStudents() {
        return personDb.getAllvdabStudents();
    }


    public List<Les> getAllLessenFromLector(String userid) {
        return lesDB.getAllLessenFromLector(userid);
    }

    public void addPersonToLes(String userid, int id) {
        lesDB.addPersonToLes(userid, id);
    }

    public void addLes(Les les) {
        lesDB.addLes(les);
    }

    public Les getLes(int id) {
        return lesDB.getLes(id);
    }

    public List<Les> getAllLessen() {
        return lesDB.getAllLessen();
    }

    public List<Integer> getLesIds() {
        return lesDB.getAllIds();
    }

    public void changePlaats(String plaats, int id) {
        lesDB.changePlaats(plaats, id);
    }

    public Connection getConnection() {
        return this.connection;
    }

    public List<Les> getAllActiveLessenFromStudent(String userid) {
        return lesDB.getAllActiveLessenFromStudent(userid);
    }

    public void removeParticipantFromClass(String participantId, int lesId){  personDb.removeParticipantFromClass(participantId, lesId);}
    public void confirmParticipants(int id){ personDb.confirmParticipants(id);}

    public void reConnect() {
        DbConnectionService.disconnect();   // close connection with db properly
        DbConnectionService.connect();      // reconnect application to db server
        this.connection = DbConnectionService.getDbConnection();    // assign connection to DBSQL
    }

    public String getSchema() {
        return schema;
    }


    public void changeJoinableState(Boolean bool, String id){lesDB.changeJoinableState(bool, id);};

    public void updateVDAB(String[] checkedIds) {
        personDb.updateVDAB(checkedIds);
    }

    public List<VdabStudent> getStudentReports(){return personDb.getStudentReports();};

    public List<VdabStudent> getStudentReportsBetweenDates(Date from, Date until){return personDb.getStudentReportsBetweenDates(from, until);};

    public void changeCommentaar(String userid, String id, String commentaar) {
        personDb.changeCommentaar(userid, id, commentaar);
    }

    public List<Student> getAfwezigeStudenten(String reeks, String lesId){return personDb.getAfwezigeStudenten(reeks, lesId);};

    public String getCommentaarFromUser(String userid, String id) {
        return personDb.getCommentaarFromUser(userid, id);
    }

    public List<VdabStudent> getAantalUrenVanVdabStudentenTussenDates(Timestamp van, Timestamp tot) {return personDb.getAantalUrenVanVdabStudentenTussenDates(van, tot);}
}
