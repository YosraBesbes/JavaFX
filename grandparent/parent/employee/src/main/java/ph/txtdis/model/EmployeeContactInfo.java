package ph.txtdis.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ph.txtdis.type.ContactInfoType;

@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "employee_id", "type", "detail" }) })
public class EmployeeContactInfo extends AbstractAudited {

    private static final long serialVersionUID = -3583165118929909989L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    private ContactInfoType type;

    @Column(nullable = false)
    private String detail;
}
