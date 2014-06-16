package pl.bb.broker.company.companydb.pensjonat.entities;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created with IntelliJ IDEA.
 * User: BamBalooon
 * Date: 31.05.14
 * Time: 21:42
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "room")
@javax.persistence.Table(name = "rooms", schema = "public", catalog = "pensjonat")
@Entity
public class RoomsEntity {
    @Embeddable
    public static class RoomPK implements Serializable {
        @NotNull
        @javax.persistence.Column(name = "rtype", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
        protected String rtype;

        @NotNull
        @ManyToOne(fetch = FetchType.EAGER) //no cascade
        @JoinColumn(name = "facility")
        protected FacilitiesEntity facility;

        public RoomPK() {}

        public RoomPK(String rtype, FacilitiesEntity facility) {
            this.rtype = rtype;
            this.facility = facility;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            RoomPK that = (RoomPK) o;

            if (rtype != null ?
                    !(rtype.equals(that.rtype)&&facility.getName().equals(that.facility.getName()))
                    : that.rtype != null) return false;

            return true;
        }

        @Override
        public int hashCode() {
            int result = rtype != null ? rtype.hashCode() : 0;
            result = 31 * result + (facility != null && facility.getName() != null ? facility.getName().hashCode() : 0);
            return result;
        }

    }

    @EmbeddedId
    private RoomPK roomPK = new RoomPK();

    @NotNull
    @Size(min = 1, max = 20)
    @javax.persistence.Column(name = "rname", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    private String rname;
    @NotNull
    @DecimalMin(value = "0.00")
    @javax.persistence.Column(name = "price", nullable = false, insertable = true, updatable = true, length = 7, precision = 2)
    private BigDecimal price;
    @NotNull
    @Min(value = 1)
    @javax.persistence.Column(name = "count", nullable = false, insertable = true, updatable = true, length = 10, precision = 0)
    private int count;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "room")
    private Collection<ReservationsEntity> reservations;


    public RoomPK getRoomPK() {
        return roomPK;
    }

    public void setRoomPK(RoomPK roomPK) {
        this.roomPK = roomPK;
    }

    @XmlElement
    @NotNull
    public FacilitiesEntity getFacility() {
        return roomPK.facility;
    }

    public void setFacility(FacilitiesEntity facility) {
        roomPK.facility = facility;
    }

    @XmlElement
    @NotNull
    @Size(min = 1, max = 20)
    public String getRtype() {
        return roomPK.rtype;
    }

    public void setRtype(String rtype) {
        this.roomPK.rtype = rtype;
    }

    @XmlElement
    @Basic
    public String getRname() {
        return rname;
    }

    public void setRname(String rname) {
        this.rname = rname;
    }

    @XmlElement
    @Basic
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @XmlElement
    @Basic
    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @XmlTransient
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

        RoomsEntity that = (RoomsEntity) o;

        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (rname != null ? !rname.equals(that.rname) : that.rname != null) return false;
        if (roomPK != null ? !roomPK.equals(that.roomPK) : that.roomPK != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = roomPK != null ? roomPK.hashCode() : 0;
        result = 31 * result + (rname != null ? rname.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }
}
