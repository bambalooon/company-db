package pl.bb.broker.company.companydb.pensjonat.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 31.05.14
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "user")
@javax.persistence.Table(name = "users", schema = "public", catalog = "pensjonat")
@Entity
public class UsersEntity {
    @NotNull
    @Size(min = 1, max = 40)
    private String username;
    @NotNull
    @Size(min = 1, max = 40)
    private String firstname;
    @NotNull
    @Size(min = 1, max = 40)
    private String surname;

    private Collection<ReservationsEntity> reservations;

    @XmlElement
    @javax.persistence.Column(name = "username", nullable = false, insertable = true, updatable = false, length = 40, precision = 0)
    @Id
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @XmlElement
    @javax.persistence.Column(name = "firstname", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }


    @XmlElement
    @javax.persistence.Column(name = "surname", nullable = false, insertable = true, updatable = true, length = 40, precision = 0)
    @Basic
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "user")
    public Collection<ReservationsEntity> getReservations() {
        return reservations;
    }

    public void setReservations(Collection<ReservationsEntity> reservations) {
        this.reservations = reservations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UsersEntity that = (UsersEntity) o;

        if (firstname != null ? !firstname.equals(that.firstname) : that.firstname != null) return false;
        if (surname != null ? !surname.equals(that.surname) : that.surname != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (firstname != null ? firstname.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }
}
