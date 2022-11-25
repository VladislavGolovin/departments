package com.task.departments.entities;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "divisions")
public class Division {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "dt_from")
    private LocalDateTime dtFrom;

    @Column(name = "dt_till")
    private LocalDateTime dtTill;

    @Column(name = "sort_priority")
    private Integer sortPriority;

    @Column(name = "is_system")
    private Integer isSystem;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "correction_date")
    private LocalDateTime correctionDate;
    
    public Division() {
      // Empty constructor for work with JPA
    }

    public Division(Long id, String name, 
                    Long parentId, LocalDateTime dtFrom, 
                    LocalDateTime dtTill, Integer sortPriority,
                    Integer isSystem, LocalDateTime creationDate, LocalDateTime correctionDate) {
        this.id = id;
        this.name = name;
        this.parentId = parentId;
        this.dtFrom = dtFrom;
        this.dtTill = dtTill;
        this.sortPriority = sortPriority;
        this.isSystem = isSystem;
        this.creationDate = creationDate;
        this.correctionDate = correctionDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public LocalDateTime getDtFrom() {
        return dtFrom;
    }

    public void setDtFrom(LocalDateTime dtFrom) {
        this.dtFrom = dtFrom;
    }

    public LocalDateTime getDtTill() {
        return dtTill;
    }

    public void setDtTill(LocalDateTime dtTill) {
        this.dtTill = dtTill;
    }

    public Integer getSortPriority() {
        return sortPriority;
    }

    public void setSortPriority(Integer sortPriority) {
        this.sortPriority = sortPriority;
    }

    public Integer getIsSystem() {
        return isSystem;
    }

    public void setIsSystem(Integer isSystem) {
        this.isSystem = isSystem;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getCorrectionDate() {
        return correctionDate;
    }

    public void setCorrectionDate(LocalDateTime correctionDate) {
        this.correctionDate = correctionDate;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((parentId == null) ? 0 : parentId.hashCode());
        result = prime * result + ((dtFrom == null) ? 0 : dtFrom.hashCode());
        result = prime * result + ((dtTill == null) ? 0 : dtTill.hashCode());
        result = prime * result + ((sortPriority == null) ? 0 : sortPriority.hashCode());
        result = prime * result + ((isSystem == null) ? 0 : isSystem.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((correctionDate == null) ? 0 : correctionDate.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Division other = (Division) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        if (parentId == null) {
            if (other.parentId != null)
                return false;
        } else if (!parentId.equals(other.parentId))
            return false;
        if (dtFrom == null) {
            if (other.dtFrom != null)
                return false;
        } else if (!dtFrom.equals(other.dtFrom))
            return false;
        if (dtTill == null) {
            if (other.dtTill != null)
                return false;
        } else if (!dtTill.equals(other.dtTill))
            return false;
        if (sortPriority == null) {
            if (other.sortPriority != null)
                return false;
        } else if (!sortPriority.equals(other.sortPriority))
            return false;
        if (isSystem == null) {
            if (other.isSystem != null)
                return false;
        } else if (!isSystem.equals(other.isSystem))
            return false;
        if (creationDate == null) {
            if (other.creationDate != null)
                return false;
        } else if (!creationDate.equals(other.creationDate))
            return false;
        if (correctionDate == null) {
            if (other.correctionDate != null)
                return false;
        } else if (!correctionDate.equals(other.correctionDate))
            return false;
        return true;
    }

    
}
