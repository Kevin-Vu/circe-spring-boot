package com.circe.invoice.entity.referential;

import jakarta.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "r_right")
public class RightEntity implements Serializable {

  @Id
  @SequenceGenerator(name = "right_generator", sequenceName = "right_sequence", allocationSize = 1)
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "right_generator")
  @Column(name = "rgt_id")
  private Integer id;

  @Column(name = "rgt_name")
  private String name;

  @Column(name = "rgt_created_by")
  @CreatedBy
  private String createdBy;

  @Column(name = "rgt_created_date")
  @CreatedDate
  private LocalDateTime createDate;

  @Column(name = "rgt_last_modified_by")
  @LastModifiedBy
  private String lastModifiedBy;

  @Column(name = "rgt_last_modified_date")
  @LastModifiedDate
  private LocalDateTime lastModifiedDate;
}
