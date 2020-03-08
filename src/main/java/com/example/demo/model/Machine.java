package com.example.demo.model;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "machine")
@EntityListeners(AuditingEntityListener.class)
public class Machine {
    private Long id;
    private String uid;
    private String status;
    private int createdBy;
    private Timestamp createdAt;
    private byte active;
    private User userByCreatedBy;

    @Id
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "uid", nullable = false, length = 45)
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Basic
    @Column(name = "status", nullable = false, length = 45)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "createdBy", nullable = false)
    public int getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(int createdBy) {
        this.createdBy = createdBy;
    }

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdAt", nullable = false)
    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    @Basic
    @Column(name = "active", nullable = false)
    public byte getActive() {
        return active;
    }

    public void setActive(byte active) {
        this.active = active;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Machine machine = (Machine) o;
        return id == machine.id &&
                createdBy == machine.createdBy &&
                active == machine.active &&
                Objects.equals(uid, machine.uid) &&
                Objects.equals(status, machine.status) &&
                Objects.equals(createdAt, machine.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, uid, status, createdBy, createdAt, active);
    }

    @ManyToOne
    @JoinColumn(name = "createdBy", referencedColumnName = "id", nullable = false)
    public User getUserByCreatedBy() {
        return userByCreatedBy;
    }

    public void setUserByCreatedBy(User userByCreatedBy) {
        this.userByCreatedBy = userByCreatedBy;
    }
}
