package com.circe.invoice.entity.referential;

import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "r_user")
public class UserEntity implements Serializable {

    @Id
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @Column(name = "usr_id")
    private Integer id;

    @Column(name = "usr_firstname", nullable = false)
    private String firstname;

    @Column(name = "usr_lastname", nullable = false)
    private String lastname;

    @Column(name = "usr_user_code", updatable = false, unique = true, nullable = false)
    private String userCode;

    @Column(name = "usr_email", nullable = false, unique = true)
    private String email;

    @Column(name = "usr_password", nullable = false)
    private String password;

    @Column(name = "usr_lang_code",nullable = false)
    private String langCode;

    @ManyToOne
    @JoinColumn(name = "usr_ptr_ath_id", nullable = false)
    private AuthorityEntity authority;

    @Column(name = "usr_pwd_expiration_date")
    private LocalDateTime pwdExpirationDate;

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

}
