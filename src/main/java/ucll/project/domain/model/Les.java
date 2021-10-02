package ucll.project.domain.model;

import java.sql.Timestamp;

public class Les {
    private int id;
    private String lector;
    private String vak;



    private String reeks;
    private Timestamp van, tot;
    private String datum, vanTijd, totTijd, plaats;
    private boolean joinable;


    public Les(int id, String lector, String vak, Timestamp van, Timestamp tot, String plaats, boolean joinable, String reeks) {
        this.id = id;
        this.lector = lector;
        this.vak = vak;
        this.van = van;
        this.tot = tot;
        this.datum = van.toLocalDateTime().toLocalDate().toString();
        this.vanTijd = van.toLocalDateTime().getHour()+":"+van.toLocalDateTime().getMinute();
        this.totTijd = tot.toLocalDateTime().getHour()+":"+tot.toLocalDateTime().getMinute();
        this.plaats = plaats;
        this.joinable = joinable;
        this.reeks = reeks;
    }

    public Les() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVak() {
        return vak;
    }

    public void setTot(Timestamp tot) {
        this.tot = tot;
    }

    public String getDatum() {
        return datum;
    }

    public String getTotTijd() {
        return totTijd;
    }

    public String getVanTijd() {
        return vanTijd;
    }

    public void setDatum(String datum) {
        this.datum = datum;
    }

    public void setTotTijd(String totTijd) {
        this.totTijd = totTijd;
    }

    public void setVanTijd(String vanTijd) {
        this.vanTijd = vanTijd;
    }

    public String getLector() {
        return lector;
    }

    public Timestamp getTot() {
        return tot;
    }

    public Timestamp getVan() {
        return van;
    }

    public void setLector(String lector) {
        this.lector = lector;
    }

    public void setVak(String vak) {
        this.vak = vak;
    }

    public void setVan(Timestamp van) {
        this.van = van;
    }

    public String getPlaats() {
        return plaats;
    }

    public void setPlaats(String plaats) {
        this.plaats = plaats;
    }

    public boolean getJoinable() { return joinable; }

    public void setJoinable(boolean joinable) { this.joinable = joinable; }

    public String getReeks() {
        return reeks;
    }

    public void setReeks(String reeks) {
        this.reeks = reeks;
    }
}
