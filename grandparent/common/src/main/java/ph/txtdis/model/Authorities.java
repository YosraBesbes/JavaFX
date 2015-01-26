package ph.txtdis.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Authorities implements Serializable {

    private static final long serialVersionUID = 6822457250729674321L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(optional = false, cascade = CascadeType.REFRESH)
    private Users username;

    @Column(nullable = false)
    private String authority;

    public Authorities(Users username, String authority) {
        this.username = username;
        this.authority = authority;
    }

    @Override
    public String toString() {
        return getUsername().toString().toUpperCase() + ": " + getAuthority();
    }
}
