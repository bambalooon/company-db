package pl.bb.broker.company.companydb.pensjonat.entities;

import org.hibernate.annotations.*;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 31.05.14
 * Time: 21:56
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "reservation")
@javax.persistence.Table(name = "reservations", schema = "public", catalog = "pensjonat")
@Entity
public class ReservationsEntity {
    private int id;
    @NotNull
    private Date arrival;
    @NotNull
    private Date departure;
    private UsersEntity user;
    private RoomsEntity room;

    @XmlAttribute
    @javax.persistence.Column(name = "id", nullable = false, insertable = true, updatable = false, length = 10, precision = 0)
    @Id
    @SequenceGenerator(name = "reservations_id_seq", sequenceName = "reservations_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservations_id_seq")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    @javax.persistence.Column(name = "arrival", nullable = false, insertable = true, updatable = true, length = 13, precision = 0)
    @Basic
    public Date getArrival() {
        return arrival;
    }

    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }

    @XmlElement
    @javax.persistence.Column(name = "departure", nullable = false, insertable = true, updatable = true, length = 13, precision = 0)
    @Basic
    public Date getDeparture() {
        return departure;
    }

    public void setDeparture(Date departure) {
        this.departure = departure;
    }

    @XmlElement
    @ManyToOne(fetch = FetchType.EAGER)
    @Cascade({CascadeType.SAVE_UPDATE, CascadeType.MERGE}) //only save&update - we want to save new user to db or to update if some info changed..
    @JoinColumn(name = "username")
    public UsersEntity getUser() {
        return user;
    }

    public void setUser(UsersEntity user) {
        this.user = user;
    }

    @XmlElement
    @ManyToOne(targetEntity = RoomsEntity.class, fetch = FetchType.EAGER) //no cascade - i don't won't update or delete of this!
    @JoinColumns({
            @JoinColumn(name = "facility", referencedColumnName = "facility"),
            @JoinColumn(name = "rtype", referencedColumnName = "rtype")
    })
    public RoomsEntity getRoom() {
        return room;
    }

    public void setRoom(RoomsEntity room) {
        this.room = room;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ReservationsEntity that = (ReservationsEntity) o;

        if (id != that.id) return false;
        if (arrival != null ? !arrival.equals(that.arrival) : that.arrival != null) return false;
        if (departure != null ? !departure.equals(that.departure) : that.departure != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (arrival != null ? arrival.hashCode() : 0);
        result = 31 * result + (departure != null ? departure.hashCode() : 0);
        return result;
    }
}
