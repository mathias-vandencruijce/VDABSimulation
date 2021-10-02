package ucll.project.domain.db;


import ucll.project.domain.model.*;
import ucll.project.util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PersonDBSQL implements PersonDB {
    private Connection connection;
    private String schema;

    public PersonDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSearchPath();
    }


    @Override
    public List<Student> getAll() {
        List<Student> people = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.student ORDER BY reeks, userid", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                Student student = getStudent(result);
                people.add(student);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return people;
    }

    public Person get(String userid) {
        String rol = getRole(userid);
        String sql = String.format("SELECT * FROM %s.%s WHERE userid = ?", this.schema, rol);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);

            ResultSet result = statementSql.executeQuery();
            result.next();
            Person person = null;

            switch (rol) {
                case "student":
                    person = getStudent(result);
                    break;
                case "lector":
                    person = getLector(result);
                    break;
                case "studentendienst":
                    person = getStudentenDienst(result);
                    break;
            }

            if (person == null) {
                throw new DbException("Persoon niet gevonden");
            }

            return person;

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override       //imported want anders error, may need to be removed
    public void delete(String userid) {

    }

    private String getRole(String userid) {
        try {
            String sql1 = "";
            for (Rol rol : Rol.values()) {
                sql1 += String.format("select userid, password, rol from %s." + rol.toString() + " union ", this.schema);
            }

            if (!sql1.trim().isEmpty()) {
                sql1 = sql1.substring(0, sql1.length() - 6);
            } else {
                throw new DbException("Er zijn geen rollen toegevoegd!");
            }

            String sql = String.format("select * from (%s) as l where l.userid = ?", sql1);
            PreparedStatement statementSql = connection.prepareStatement(sql);

            statementSql.setString(1, userid);
            ResultSet set = statementSql.executeQuery();
            set.next();

            String rol = set.getString("rol");

            if (rol == null || rol.trim().isEmpty()) {
                throw new DbException("Persoon niet gevonden");
            }

            return rol;

        } catch (Exception e) {
            throw new DbException(e.getMessage());
        }
    }

    private Student getStudent(ResultSet result) throws SQLException {
        try {

            String id = result.getString("userid");
            String password = result.getString("password");
            String omsOpleiding = result.getString("oms_opleiding");
            int fase = result.getInt("fase");
            String role = result.getString("rol");
            String reeks = result.getString("reeks");
            boolean vdab = result.getBoolean("vdab");
            String studentencoach = result.getString("studentencoach");
            String voornaam = result.getString("voornaam");
            String achternaam = result.getString("achternaam");

            return new Student(id, password, reeks, omsOpleiding, fase, role, vdab, studentencoach, voornaam, achternaam);
        } catch (Exception exception) {
            throw new SQLException(exception.getMessage());
        }
    }

    private Lector getLector(ResultSet result) throws SQLException {
        try {
            String id = result.getString("userid");
            String password = result.getString("password");
            String omsOpleiding = result.getString("naam");
            String role = result.getString("rol");
            boolean is_studentencoach = result.getBoolean("is_studentencoach");

            return new Lector(id, password, omsOpleiding, role, is_studentencoach);
        } catch (Exception exception) {
            throw new SQLException(exception.getMessage());
        }
    }

    private Person getStudentenDienst(ResultSet result) throws SQLException {
        try {
            String id = result.getString("userid");
            String password = result.getString("password");
            String role = result.getString("rol");

            return new Studentendienst(id, password, role);
        } catch (Exception exception) {
            throw new SQLException(exception.getMessage());
        }
    }

    @Override
    public List<Student> getAllPeopleFromLes(int id) {
        List<Student> people = new ArrayList<>();
        String sql = String.format("select * from %s.student inner join %s.partisipants using(userid) where id = ?", this.schema, this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, String.valueOf(id));
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {
                Student student = getStudent(result);
                String commentaar = result.getString("commentaar");
                student.setCommentaar(commentaar);
                people.add(student);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return people;
    }

    @Override
    public void updateVDAB(String[] checkedIds) {
        setVDABFalse();

        String sql = String.format("UPDATE %s.student SET vdab = ? WHERE userid = ?", this.schema);

        try {
            for (String id : checkedIds) {
                PreparedStatement statementSQL = connection.prepareStatement(sql);
                statementSQL.setBoolean(1, true);
                statementSQL.setString(2, id);
                statementSQL.execute();
            }
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private void setVDABFalse() {
        String sql = String.format("UPDATE %s.student SET vdab = ? WHERE 1 = 1", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setBoolean(1, false);
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    public void removeParticipantFromClass(String participantId, int lesId) {
        String sql = String.format("DELETE FROM %s.partisipants WHERE id=? AND userid=?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);

            statementSql.setString(1, String.valueOf(lesId));

            statementSql.setString(2, participantId);
            statementSql.execute();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public void confirmParticipants(int lesId) {
        String sql = String.format("UPDATE %s.partisipants SET confirmation=? WHERE id=?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setBoolean(1, true);
            statementSql.setInt(2, lesId);
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override

    public List<VdabStudent> getStudentReportsBetweenDates(Date from, Date until) {
        String sql = String.format("select DISTINCT l.userid, l.reeks, case\n" +
                "                                \t\twhen aanwezig is not null then aanwezig\n" +
                "                                \t\telse '00:00'\n" +
                "                            \t\tend as aanwezig, totaal\n" +
                "from (\n" +
                "\tselect userid, reeks, sum(tot - van) as totaal\n" +
                "\tfrom %s.student inner join %s.les using(reeks)\n" +
                "\twhere vdab = TRUE and van >= ? and tot < ?\n" +
                "\tgroup by userid\n" +
                "\torder by userid)    \n" +
                "   \t\t as l left outer join   \n" +
                "    \t\t\t\t\t(select student.userid, SUM(tot - van) AS aanwezig\n" +
                "\t\t\t\t\t\tfrom %s.student inner join %s.les using(reeks) inner join %s.partisipants on (student.userid = partisipants.userid and les.id = partisipants.id)\n" +
                "\t\t\t\t\t\twhere vdab = TRUE and van >= ? and tot < ?\n" +
                "\t\t\t\t\t\tgroup by student.userid, les.id\n" +
                "\t\t\t\t\t\torder by userid) as m using(userid)\n" +
                "\t\t\t\t\t\torder by l.userid", this.schema, this.schema, this.schema, this.schema, this.schema);
        List<VdabStudent> students = new ArrayList<>();
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setDate(1, from);
            statementSql.setDate(2, until);
            statementSql.setDate(3, from);
            statementSql.setDate(4, until);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                String reeks = result.getString("reeks");
                String aanwezig = result.getString("aanwezig");
                String totaal = result.getString("totaal");
                students.add(new VdabStudent(userid, reeks, aanwezig, totaal));
            }

        } catch (SQLException e) {
            System.out.println("Fout in query");
            throw new DbException(e.getMessage());
        }
        return students;
    }
    
    public List<VdabStudent> getStudentReports() {
        String sql = String.format("select DISTINCT l.userid, l.reeks, case\n" +
                "                                when aanwezig is not null then aanwezig\n" +
                "                                else '00:00'\n" +
                "                            end as aanwezig, totaal\n" +
                "from (select userid, reeks, sum(tot - van) as totaal\n" +
                "    from %s.student inner join %s.les using(reeks)\n" +
                "    where vdab = TRUE\n" +
                "    group by userid\n" +
                "    order by userid)    \n" +
                "    as l left outer join   \n" +
                "    (select userid, SUM(tot - van) AS aanwezig\n" +
                "from %s.student inner join %s.les using(reeks) inner join %s.partisipants using(userid)\n" +
                "where vdab = TRUE AND EXTRACT (DAY FROM (van-current_timestamp)) < 1 AND EXTRACT (DAY FROM (van-current_timestamp)) > -8\n" +
                "group by userid, van\n" +
                "order by userid) as m using(userid)", this.schema, this.schema, this.schema, this.schema, this.schema);
        List<VdabStudent> students = new ArrayList<>();
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                String userid = result.getString("userid");
                String reeks = result.getString("reeks");
                String aanwezig = result.getString("aanwezig");
                String totaal = result.getString("totaal");
                students.add(new VdabStudent(userid, reeks, aanwezig, totaal));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return students;
    }

    public void changeCommentaar(String userid, String id, String commentaar) {
        String sql = String.format("UPDATE %s.partisipants SET commentaar = ?  where id = ? and userid = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, commentaar);
            statementSql.setString(2, id);
            statementSql.setString(3, userid);
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    public List<Student> getAllvdabStudents() {
        List<Student> people = new ArrayList<>();
        String sql = String.format("select * from %s.student where vdab = ? order by reeks,userid", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setBoolean(1, true);

            ResultSet result = statementSql.executeQuery();

            while (result.next()) {
                Student student = getStudent(result);
                people.add(student);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return people;
    }

    @Override
    public List<Lector> getAllstudentencoachen() {
        List<Lector> people = new ArrayList<>();
        String sql = String.format("select * from %s.lector where is_studentencoach = ? ", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setBoolean(1, true);

            ResultSet result = statementSql.executeQuery();

            while (result.next()) {
                Lector lector = getLector(result);
                people.add(lector);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return people;
    }

    @Override
    public void setstudentencoach(String userid, String coachid) {
        String sql = String.format("update %s.student set studentencoach = ? where userid = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, coachid);
            statementSql.setString(2, userid);
            statementSql.execute();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }
    @Override
    public List<Student> getAfwezigeStudenten(String reeks, String lesId) {
        List<Student> students = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.student WHERE reeks = ? AND userid NOT IN(SELECT userid FROM %s.partisipants WHERE id = ? )", this.schema, this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, reeks);
            statementSql.setString(2, lesId);
            ResultSet result = statementSql.executeQuery();
            while (result.next()) {
                students.add(getStudent(result));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return students;
    }
    public String getCommentaarFromUser(String userid, String id) {
        String sql = String.format("select commentaar from %s.partisipants where id = ? and userid = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, id);
            statementSql.setString(2,userid);
            statementSql.executeQuery();
            ResultSet set = statementSql.getResultSet();
            set.next();
            return set.getString("commentaar");
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<VdabStudent> getAantalUrenVanVdabStudentenTussenDates(Timestamp van, Timestamp tot) {
        String sql = String.format("select DISTINCT l.userid, l.reeks, case\n" +
                "                                \t\twhen aanwezig is not null then aanwezig\n" +
                "                                \t\telse '00:00'\n" +
                "                            \t\tend as aanwezig, totaal\n" +
                "from (\n" +
                "\tselect userid, reeks, sum(tot - van) as totaal\n" +
                "\tfrom %s.student inner join %s.les using(reeks)\n" +
                "\twhere vdab = TRUE and van >= ? and tot < ?\n" +
                "\tgroup by userid\n" +
                "\torder by userid)    \n" +
                "   \t\t as l left outer join   \n" +
                "    \t\t\t\t\t(select student.userid, SUM(tot - van) AS aanwezig\n" +
                "\t\t\t\t\t\tfrom %s.student inner join %s.les using(reeks) inner join %s.partisipants on (student.userid = partisipants.userid and les.id = partisipants.id)\n" +
                "\t\t\t\t\t\twhere vdab = TRUE and van >= ? and tot < ?\n" +
                "\t\t\t\t\t\tgroup by student.userid, les.id\n" +
                "\t\t\t\t\t\torder by userid) as m using(userid)\n" +
                "\t\t\t\t\t\torder by l.userid", this.schema, this.schema, this.schema, this.schema, this.schema);
        List<VdabStudent> students = new ArrayList<>();
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setTimestamp(1, van);
            statementSql.setTimestamp(2, tot);
            statementSql.setTimestamp(3,van);
            statementSql.setTimestamp(4, tot);

            ResultSet result = statementSql.executeQuery();

            while (result.next()) {
                String userid = result.getString("userid");
                String reeks = result.getString("reeks");
                String aanwezig = result.getString("aanwezig");
                String totaal = result.getString("totaal");
                students.add(new VdabStudent(userid, reeks, aanwezig, totaal));
            }

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return students;
    }
}


