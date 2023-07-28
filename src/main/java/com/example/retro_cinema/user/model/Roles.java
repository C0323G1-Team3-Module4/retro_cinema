package com.example.retro_cinema.user.model;

import javax.persistence.*;

@Entity

public class Roles {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "role_name")
    private String roleName;
    @Column(columnDefinition = "bit default 0")
    private boolean flagDelete;

    public Roles() {
    }

    public Roles(Integer id, String roleName, boolean flagDelete) {
        this.id = id;
        this.roleName = roleName;
        this.flagDelete = flagDelete;
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
}
