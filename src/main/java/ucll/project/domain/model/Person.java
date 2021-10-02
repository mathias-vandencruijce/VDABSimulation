package ucll.project.domain.model;

import java.math.BigInteger;
import java.security.MessageDigest;

public abstract class Person {
    private String userid, password, role;

    public Person(String userid, String password, String role) {
        this.userid = userid;
        this.password = password;
        this.role = role;
    }

    public Person(){}

    public String getUserid() {
        return userid;
    }

    public String getPassword() {
        return password;
    }

    public String hashPassword(String password) {
        try {
            MessageDigest crypt = MessageDigest.getInstance("SHA-512");

            crypt.reset();

            byte[] passwordBytes = password.getBytes("UTF-8");
            crypt.update(passwordBytes);

            byte[] digest = crypt.digest();
            BigInteger digestAsBigInteger = new BigInteger(1, digest);
            return digestAsBigInteger.toString(16);
        } catch (Exception exc) {
            throw new DomainException(exc.getMessage());
        }
    }

    public void setHashedPassword(String password) {
        if(password.isEmpty()){
            throw new IllegalArgumentException("No password given");
        }
        this.password = hashPassword(password);
    }

    public boolean isCorrectPassword(String password) {
        if(password.isEmpty()){
            throw new IllegalArgumentException("No password given");
        }

        return (this.password.equals(hashPassword(password)));
    }

    public void setUserid(String userid) {
        if (!isValidString(userid)) throw new IllegalArgumentException("Give valid userid.");
        this.userid = userid;
    }

    public void setPassword(String password) {
        if (!isValidString(password)) throw new IllegalArgumentException("Give valid password.");
        this.password = password;
    }

    public boolean isValidString(String string) {
        return string != null && !string.trim().isEmpty();
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
