package ph.txtdis.model;

import java.time.LocalDate;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

import ph.txtdis.type.GovtIdType;

@Entity
@Table(name = "govt_id", uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "type", "issuance" }))
public class GovtId extends AbstractAudited {

    private static final long serialVersionUID = -5750732249679224119L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private Byte[] image;

    private GovtIdType type;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate issuance;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate expiry;

    @Column(nullable = false)
    private String detail;

    protected GovtId() {
    }

    public GovtId(Employee employee, Byte[] image, GovtIdType type, LocalDate issuance, LocalDate expiry, String detail) {
        this.employee = employee;
        this.image = image;
        this.type = type;
        this.issuance = issuance;
        this.expiry = expiry;
        this.detail = detail;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Byte[] getImage() {
        return image;
    }

    public void setImage(Byte[] image) {
        this.image = image;
    }

    public GovtIdType getType() {
        return type;
    }

    public void setType(GovtIdType type) {
        this.type = type;
    }

    public LocalDate getIssuance() {
        return issuance;
    }

    public void setIssuance(LocalDate issuance) {
        this.issuance = issuance;
    }

    public LocalDate getExpiry() {
        return expiry;
    }

    public void setExpiry(LocalDate expiry) {
        this.expiry = expiry;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    @Override
    public String toString() {
        return "[" + employee + "] " + type + ": " + detail;
    }
}
