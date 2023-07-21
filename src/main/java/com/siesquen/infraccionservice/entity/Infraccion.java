package com.siesquen.infraccionservice.entity;

import java.util.Date;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Entity
@Table(name="infraccion")
@EntityListeners(AuditingEntityListener.class)
public class Infraccion {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(unique = true,nullable = false,length = 9)
	private String dni;
	@Column(nullable = false,length = 3)
	private String falta;
	@Column(name="created_at",nullable = false,updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@CreatedDate
	private Date createdAt;
	@Column(name="updated_at")
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updateAt;
	@Column(nullable = false,length = 200)
	private String infraccion;
	@Column(nullable = true,length = 200)
	private String descripcion;
	
}
