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

@NamePattern("%s|name")
@Table(name = "CEUW_CUSTOMER")
@Entity(name = "ceuw$Customer")
public class Customer extends StandardEntity {
    private static final long serialVersionUID = 4405837660265764134L;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "FIRST_NAME")
    protected String firstName;

    @NotNull
    @Column(name = "EMAIL", nullable = false)
    protected String email;

    @Composition
    @OnDelete(DeletePolicy.CASCADE)
    @OneToMany(mappedBy = "customer")
    protected List<Address> addresses;

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