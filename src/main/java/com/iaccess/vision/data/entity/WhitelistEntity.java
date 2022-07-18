package com.iaccess.vision.data.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "tb_whitelist")
@Getter
@Setter
@ToString
public class WhitelistEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client_name", nullable = false)
    private String clientName;

    @Column(name = "ip_address", nullable = false)
    private String ipAddress;

    @Column(name = "application_name", nullable = false)
    private String applicationName;

    @Column(name = "environment_name", nullable = false)
    private String environmentName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        WhitelistEntity whitelist = (WhitelistEntity) o;
        return id != null && Objects.equals(id, whitelist.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
