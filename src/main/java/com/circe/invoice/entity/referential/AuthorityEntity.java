package com.circe.invoice.entity.referential;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "r_authority")
public class AuthorityEntity implements Serializable, Auditable<String, Integer, LocalDateTime> {

    @Id
    @SequenceGenerator(name = "authority_generator", sequenceName = "authority_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_generator")
    @Column(name = "ath_id")
    private Integer id;

    @Column(name = "ath_name", nullable = false, unique = true, updatable = false)
    private String name;

    @OneToMany
    @JoinTable(name = "r_join_authority_right",
            joinColumns = @JoinColumn(name = "jar_ptr_auth_id"),
            inverseJoinColumns = @JoinColumn(name = "jar_ptr_right_id"))
    private List<RightEntity> rightEntities;

    @Column(name = "ath_created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "ath_created_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "ath_last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name = "ath_last_modified_date")
    @LastModifiedDate
    private LocalDateTime lastModifiedDate;

    @Override
    public Optional<String> getCreatedBy() {
        return Optional.ofNullable(this.createdBy);
    }

    @Override
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public Optional<LocalDateTime> getCreatedDate() {
        return Optional.ofNullable(this.createDate);
    }

    @Override
    public void setCreatedDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public Optional<String> getLastModifiedBy() {
        return Optional.ofNullable(this.lastModifiedBy);
    }

    @Override
    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    @Override
    public Optional<LocalDateTime> getLastModifiedDate() {
        return Optional.ofNullable(this.lastModifiedDate);
    }

    @Override
    public void setLastModifiedDate(LocalDateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @Override
    public boolean isNew() {
        return this.id == null;
    }
}
