package tech.xiying.template.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @ClassName BaseEntity
 * @Description
 * @Author shanghao5
 * @Date 2020/11/27 20:54
 **/
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;

    @Column(name = "is_deleted", columnDefinition = "Bit default '0'")
    protected boolean isDeleted = false;

    @Column(name = "created_time", nullable = false)
    private Date createdTime;

    @Column(name = "last_modified_time")
    private Date lastModifiedTime;

    @PrePersist
    protected void prePersist() {
        if (this.createdTime == null) {
            createdTime = new Date();
        }
        if (this.lastModifiedTime == null) {
            lastModifiedTime = new Date();
        }
    }

    @PreUpdate
    protected void preUpdate() {
        this.lastModifiedTime = new Date();
    }

    @PreRemove
    protected void preRemove() {
        this.lastModifiedTime = new Date();
    }

}
