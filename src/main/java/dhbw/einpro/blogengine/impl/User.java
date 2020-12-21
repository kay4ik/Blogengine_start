package dhbw.einpro.blogengine.impl;

import java.util.Comparator;

import dhbw.einpro.blogengine.interfaces.IUser;

/**
 * Klasse enthält Informationen zu einem Benutzer des Blog-Systems
 *
 * @author rbimaz
 */
public class User implements Comparable<User>, IUser
{
    private String email;
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName, String email){
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public void setEmail(String p_email) {
        email = p_email;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String p_firstName) {
        firstName = p_firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String p_lastName) {
        lastName = p_lastName;
    }

    @Override
    public int compareTo(User o) {
        return Comparator
                .comparing(User::getFirstName)
                .thenComparing(User::getLastName)
                .thenComparing(User::getEmail)
                .compare(this, o);
    }
}
