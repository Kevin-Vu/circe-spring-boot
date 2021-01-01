package com.circe.invoice.entity.referentiel;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Auditable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "r_user")
public class UserEntity implements Auditable<String, Integer, LocalDateTime> {

    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @Column(name = "usr_id")
    private Long id;

    @Column(name = "usr_firstname", nullable = false)
    private String firstname;

    @Column(name = "usr_lastname", nullable = false)
    private String lastname;

    @Column(name = "usr_client_code", updatable = false, unique = true, nullable = false)
    private String clientCode;

    @Column(name = "usr_email", nullable = false)
    private String email;

    @Column(name = "usr_password", nullable = false)
    private String password;

    @ManyToOne
    @JoinColumn(name = "usr_ptr_ath_id", nullable = false)
    private AuthorityEntity authority;

    @Column(name = "usr_pwd_expirity_date")
    private LocalDateTime pwdExpirityDate;

    @Column(name = "usr_pwd_access_start")
    private LocalDateTime pwdAccessStart;

    @Column(name = "usr_pwd_access_end")
    private LocalDateTime pwdAccessEnd;

    @Column(name = "usr_pwd_last_change_date")
    private LocalDateTime pwdLastChangeDate;

    @Column(name = "usr_created_by")
    @CreatedBy
    private String createdBy;

    @Column(name = "usr_created_date")
    @CreatedDate
    private LocalDateTime createDate;

    @Column(name = "usr_last_modified_by")
    @LastModifiedBy
    private String lastModifiedBy;

    @Column(name = "usr_last_modified_date")
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
