package com.example.retro_cinema.user.model;

import javax.persistence.*;
import java.util.Set;

@Entity

public class Roles {
    @Id
    private Integer id;
    @Column(name = "role_name")
    private String roleName;
    @Column(columnDefinition = "bit default 0")
    private boolean flagDelete;

    @OneToMany(mappedBy = "roles")
    private Set<AccountUser> accountUserSet;
    public Roles() {
    }

    public Roles(Integer id, String roleName, boolean flagDelete, Set<AccountUser> accountUserSet) {
        this.id = id;
        this.roleName = roleName;
        this.flagDelete = flagDelete;
        this.accountUserSet = accountUserSet;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public boolean isFlagDelete() {
        return flagDelete;
    }

    public void setFlagDelete(boolean flagDelete) {
        this.flagDelete = flagDelete;
    }

    public Set<AccountUser> getAccountUserSet() {
        return accountUserSet;
    }

    public void setAccountUserSet(Set<AccountUser> accountUserSet) {
        this.accountUserSet = accountUserSet;
    }
}
