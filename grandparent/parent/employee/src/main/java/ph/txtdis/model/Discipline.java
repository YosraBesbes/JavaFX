package ph.txtdis.model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = { "employee_id", "incidence", "allegation" }))
public class Discipline extends AbstractAudited {

    private static final long serialVersionUID = -2655032643722025128L;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Employee employee;

    @Column(nullable = false)
    private Date incidence;

    @Column(nullable = false)
    private String allegation;

    private String decision;

    private Date effectivity;

    private int dayCount;

    private boolean noticeGiven;

    private boolean replyReceived;

    private boolean decisionGiven;

    protected Discipline() {
    }

    public Discipline(Employee employee, Date incidence, String allegation) {
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

    public Date getIncidence() {
        return incidence;
    }

    public void setIncidence(Date incidence) {
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

    public Date getEffectivity() {
        return effectivity;
    }

    public void setEffectivity(Date effectivity) {
        this.effectivity = effectivity;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
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
