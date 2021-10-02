package ucll.project.domain.model;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Student extends Person{
    private String reeks, omsOpleiding, role, commentaar, studentencoach, voornaam, achternaam;
    private int fase;
    private boolean vdab;

    public Student(String userid, String password, String reeks, String omsOpleiding, int fase, String role, boolean vdab,String studentencoach, String voornaam, String achternaam) {
        super(userid, password, role);
       setReeks(reeks);
       setOmsOpleiding(omsOpleiding);
       setFase(fase);
       setRole(role);
       setVDAB(vdab);
       setstudentencoach(studentencoach);
       this.voornaam = voornaam;
       this.achternaam = achternaam;
    }

    private void setstudentencoach(String studentencoach) {
        this.studentencoach=studentencoach;
    }

    public String getStudentencoach() {
        return studentencoach;
    }

    public String getCommentaar() {
        return commentaar;
    }

    public void setCommentaar(String commentaar) {
        this.commentaar = commentaar;
    }

    public Student() {}

    public String getRole() {
        return role;
    }

    public String getReeks() {
        return this.reeks;
    }

    public int getFase() {
        return fase;
    }

    public String getOmsOpleiding() {
        return omsOpleiding;
    }

    public boolean isVdab() {
        return vdab;
    }

    public void setRole(String role) {
        if (!isValidString(role)) throw new IllegalArgumentException("Give valid role.");
        this.role = role;
    }

    public void setOmsOpleiding(String omsOpleiding) {
        if (!isValidString(omsOpleiding)) throw new IllegalArgumentException("Give valid Oms Opleiding.");
        this.omsOpleiding = omsOpleiding;
    }

    public void setFase(int fase) {
        this.fase = fase;
    }

    public void setReeks(String reeks) {
        if (!isValidString(reeks)) throw new IllegalArgumentException("Give valid reeks.");
        this.reeks = reeks;
    }

    private void setVDAB(boolean vdab) {
        this.vdab = vdab;
    }

    public String getAchternaam() {
        return achternaam;
    }

    public String getVoornaam() {
        return voornaam;
    }
}
