package com.example.curly_api.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "tb_roles")
public class Role {

    @Id
    @Getter
    @Setter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long roleId;

    @Getter
    @Setter
    private String name;

    public enum Values {

        CLIENT(1L),
        PROFESSIONAL(2L),
        ADMIN(3L);

        Long roleId;

        Values(long roleId) {
            this.roleId = roleId;
        }

        public long getRoleId() {
            return roleId;
        }
    }
}