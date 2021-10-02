package ucll.project.domain.db;

import ucll.project.domain.model.Les;
import ucll.project.util.DbConnectionService;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessDBSQL implements LesDB {
    private Connection connection;
    private String schema;

    public LessDBSQL() {
        this.connection = DbConnectionService.getDbConnection();
        this.schema = DbConnectionService.getSearchPath();
    }


    @Override
    public void addLes(Les les) {
        if (les == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("INSERT INTO %s.les (id, lector, vak, van, tot, plaats) VALUES (?, ?, ?, ?, ?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, les.getId());
            statementSQL.setString(2, les.getLector());
            statementSQL.setString(3, les.getVak());
            statementSQL.setTimestamp(4, les.getVan());
            statementSQL.setTimestamp(5,les.getTot());
            statementSQL.setString(6,les.getPlaats());
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public Les getLes(int id) {
        String sql = String.format("SELECT les.id, vak.opleidingsonderdeel as vak, lector, van, tot, plaats, joinable, reeks FROM %s.les inner join %s.vak on (les.vak = vak.id) where les.id = ?", this.schema, this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, String.valueOf(id));

            ResultSet result = statementSql.executeQuery();
            result.next();

            return get(result);

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }


    @Override
    public List<Les> getAllLessen() {
        List<Les> lessen = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.class", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {
                Les les = get(result);
                lessen.add(les);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lessen;
    }

    @Override
    public List<Integer> getAllIds() {
        List<Integer> list = new ArrayList<>();
        String sql = String.format("SELECT id FROM %s.les", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {
                list.add(result.getInt("id"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }

        return list;
    }

    @Override
    public void addPersonToLes(String userid, int id) {
        if (userid == null) {
            throw new DbException("Nothing to add.");
        }
        String sql = String.format("INSERT INTO %s.partisipants (id, userid) VALUES (?, ?)", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setInt(1, id);
            statementSQL.setString(2, userid);
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    private Les get(ResultSet result) throws SQLException {
        try {
            int id = result.getInt("id");
            String vak = result.getString("vak");
            String lector = result.getString("lector");
            Timestamp van = result.getTimestamp("van");
            Timestamp tot = result.getTimestamp("tot");
            String plaats = result.getString("plaats");
            boolean joinable = result.getBoolean("joinable");
            String reeks = result.getString("reeks");

            return new Les(id, lector, vak, van, tot, plaats, joinable, reeks);
        } catch (Exception exception) {
            throw new SQLException(exception.getMessage());
        }
    }

    @Override
    public List<Les> getAllLessenFromLector(String userid) {
        List<Les> lessen = new ArrayList<>();
        String sql = String.format("SELECT * FROM %s.les WHERE lector = ?", this.schema);
        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {
                Les les = get(result);
                lessen.add(les);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lessen;
    }

    @Override
    public void changePlaats(String plaats, int id) {
        String sql = String.format("UPDATE %s.les SET plaats = ? where id = ? ", this.schema);

        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setString(1, plaats);
            statementSQL.setString(2, String.valueOf(id));
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public void changeJoinableState(Boolean bool, String id){
        String sql = String.format("UPDATE %s.les SET joinable=? WHERE id=?", this.schema);
        try {
            PreparedStatement statementSQL = connection.prepareStatement(sql);
            statementSQL.setBoolean(1, bool);
            statementSQL.setString(2, id);
            statementSQL.execute();
        } catch (SQLException e) {
            throw new DbException(e);
        }
    }

    @Override
    public List<Les> getAllActiveLessenFromStudent(String userid) {
        List<Les> lessen = new ArrayList<>();
        String sql = String.format("select * from %s.les inner join %s.partisipants using(id) where joinable = true and userid = ?", this.schema, this.schema);

        try {
            PreparedStatement statementSql = connection.prepareStatement(sql);
            statementSql.setString(1, userid);
            ResultSet result = statementSql.executeQuery();

            while (result.next()) {
                Les les = get(result);
                lessen.add(les);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lessen;
    }

}
