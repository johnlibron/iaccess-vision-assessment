package com.iaccess.vision.data;

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
public class Whitelist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "client_name")
    private String clientName;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "app_name")
    private String appName;

    @Column(name = "env_name")
    private String envName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Whitelist whitelist = (Whitelist) o;
        return id != null && Objects.equals(id, whitelist.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
