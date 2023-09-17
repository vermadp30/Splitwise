package com.scaler.splitwise.models;

import jakarta.persistence.*;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Setter
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long Id;

  @CreatedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date createdOn;

  @LastModifiedDate
  @Temporal(TemporalType.TIMESTAMP)
  private Date updatedOn;
}
