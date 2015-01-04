package ph.txtdis.model;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "incidence", "allegation" }))
public class Discipline extends AbstractAudited {

    private static final long serialVersionUID = -2655032643722025128L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    @Column(nullable = false)
    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate incidence;

    @Column(nullable = false)
    private String allegation;

    private String decision;

    @Type(type = "org.jadira.usertype.dateandtime.threeten.PersistentLocalDate")
    private LocalDate effectivity;

    private int dayCount;

    private boolean noticeGiven;

    private boolean replyReceived;

    private boolean decisionGiven;

    protected Discipline() {
    }

    public Discipline(Employee employee, LocalDate incidence, String allegation) {
        this.employee = employee;
        this.incidence = incidence;
        this.allegation = allegation;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getIncidence() {
        return incidence;
    }

    public void setIncidence(LocalDate incidence) {
        this.incidence = incidence;
    }

    public String getAllegation() {
        return allegation;
    }

    public void setAllegation(String allegation) {
        this.allegation = allegation;
    }

    public String getDecision() {
        return decision;
    }

    public void setDecision(String decision) {
        this.decision = decision;
    }

    public LocalDate getEffectivity() {
        return effectivity;
    }

    public void setEffectivity(LocalDate effectivity) {
        this.effectivity = effectivity;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(Integer dayCount) {
        this.dayCount = dayCount;
    }

    public boolean isNoticeGiven() {
        return noticeGiven;
    }

    public void setNoticeGiven(boolean noticeGiven) {
        this.noticeGiven = noticeGiven;
    }

    public boolean isReplyReceived() {
        return replyReceived;
    }

    public void setReplyReceived(boolean replyReceived) {
        this.replyReceived = replyReceived;
    }

    public boolean isDecisionGiven() {
        return decisionGiven;
    }

    public void setDecisionGiven(boolean decisionGiven) {
        this.decisionGiven = decisionGiven;
    }
}
