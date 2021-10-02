package ucll.project.domain.model;

public class VdabStudent {
    private String userId, reeks, aanwezig, totaal;

    public VdabStudent(String userId, String reeks, String aanwezig, String totaal) {
        this.userId = userId;
        this.reeks = reeks;
        this.aanwezig = aanwezig;
        this.totaal = totaal;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getReeks() {
        return reeks;
    }

    public void setReeks(String reeks) {
        this.reeks = reeks;
    }

    public String getAanwezig() {
        return aanwezig;
    }

    public void setAanwezig(String aanwezig) {
        this.aanwezig = aanwezig;
    }

    public String getTotaal() {
        return totaal;
    }

    public void setTotaal(String totaal) {
        this.totaal = totaal;
    }
}
