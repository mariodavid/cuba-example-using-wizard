package de.diedavids.cuba.ceuw.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.chile.core.annotations.Composition;
import com.haulmont.cuba.core.entity.annotation.OnDelete;
import com.haulmont.cuba.core.global.DeletePolicy;
import java.util.List;
import javax.persistence.OneToMany;
import java.util.Date;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@NamePattern("%s|name")
@Table(name = "CEUW_CUSTOMER")
@Entity(name = "ceuw$Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = 4405837660265764134L;

    @Column(name = "SALUTATION")
    protected String salutation;

    @NotNull
    @Column(name = "NAME", nullable = false)
    protected String name;

    @NotNull
    @Column(name = "FIRST_NAME", nullable = false)
    protected String firstName;

    @Temporal(TemporalType.DATE)
    @Column(name = "BIRTHDAY")
    protected Date birthday;

    @NotNull
    @Column(name = "EMAIL", nullable = false)
    protected String email;

    @Column(name = "PHONE")
    protected String phone;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "customer")
    protected List<Address> addresses;

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getBirthday() {
        return birthday;
    }


    public void setSalutation(Salutation salutation) {
        this.salutation = salutation == null ? null : salutation.getId();
    }

    public Salutation getSalutation() {
        return salutation == null ? null : Salutation.fromId(salutation);
    }


    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhone() {
        return phone;
    }


    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

    public List<Address> getAddresses() {
        return addresses;
    }


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }


}