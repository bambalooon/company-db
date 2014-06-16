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
 * Date: 02.06.14
 * Time: 22:46
 * To change this template use File | Settings | File Templates.
 */
@XmlRootElement(name = "facility")
@javax.persistence.Table(name = "facilities", schema = "public", catalog = "pensjonat")
@Entity
public class FacilitiesEntity {
    @NotNull
    @Size(min = 1, max = 20)
    private String name;
    @Size(min = 0, max = 80)
    private String address;

    private Collection<RoomsEntity> rooms;
    private Collection<ReservationsEntity> reservations;

    @XmlElement
    @javax.persistence.Column(name = "name", nullable = false, insertable = true, updatable = true, length = 20, precision = 0)
    @Id
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlElement
    @javax.persistence.Column(name = "address", nullable = true, insertable = true, updatable = true, length = 80, precision = 0)
    @Basic
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @XmlTransient
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "roomPK.facility")
    public Collection<RoomsEntity> getRooms() {
        return rooms;
    }

    public void setRooms(Collection<RoomsEntity> rooms) {
        this.rooms = rooms;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FacilitiesEntity that = (FacilitiesEntity) o;

        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        return result;
    }
}
