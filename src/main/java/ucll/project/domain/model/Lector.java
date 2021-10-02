package ucll.project.domain.model;

public class Lector extends Person {
    private String naam;
    private boolean is_studentencoach;


    public Lector(String userid, String password, String naam, String role,boolean is_studentencoach) {
        super(userid, password, role);
        setNaam(naam);
        setis_studentencoach(is_studentencoach);
    }


    private void setis_studentencoach(boolean is_studentencoach) {
        this.is_studentencoach=is_studentencoach;
    }

    public boolean isIs_studentencoach() {
        return is_studentencoach;
    }

    public Lector(){}

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }
}
