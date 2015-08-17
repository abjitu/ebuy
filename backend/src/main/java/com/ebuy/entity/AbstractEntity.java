package com.ebuy.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 * The Class AbstractEntity.
 */
@MappedSuperclass
public class AbstractEntity {
    
    /** The id. */
    private Integer id;
    
    /** The created date. */
    private Date createdDate;
    
    /** The modified date. */
    private Date modifiedDate;
    
    /** The version. */
    private Integer version;

    /**
     * Gets the id.
     *
     * @return the id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, unique = true, updatable = false)
    public Integer getId() {
        return id;
    }

    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public void setId(final Integer id) {
        this.id = id;
    }

    /**
     * Gets the created date.
     *
     * @return the created date
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", nullable = false, length = 19)
    public Date getCreatedDate() {
        return this.createdDate;
    }

    /**
     * Sets the created date.
     *
     * @param createdDate the new created date
     */
    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Gets the modified date.
     *
     * @return the modified date
     */
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_at", nullable = false, length = 19)
    public Date getModifiedDate() {
        return this.modifiedDate;
    }

    /**
     * Sets the modified date.
     *
     * @param modifiedDate the new modified date
     */
    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    /**
     * Gets the version.
     *
     * @return the version
     */
    @Version
    public Integer getVersion() {
        return version;
    }

    /**
     * Sets the version.
     *
     * @param version the new version
     */
    public void setVersion(Integer version) {
        this.version = version;
    }

    /**
     * On create.
     */
    @PrePersist
    public void onCreate() {
        final Date time = new Date();
        setCreatedDate(time);
        setModifiedDate(time);
    }

    /**
     * On update.
     */
    @PreUpdate
    public void onUpdate() {
        final Date time = new Date();
        setModifiedDate(time);
    }
}
