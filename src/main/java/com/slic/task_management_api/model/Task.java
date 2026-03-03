package com.slic.task_management_api.model;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
		name = "tasks",
		indexes = {
				@Index(name = "idx_userid", columnList = "user_id"),
				@Index(name = "idx_title", columnList = "title"),
		}
)
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor // Needed by Hibernate to instantiate class through reflection
public class Task {
	@Id
  @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	@NotBlank
  @Size(max = 256)
  private String title;

	@Column(name = "completed")
	@Default
	private Boolean completed = false;

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private Date createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private Date updatedAt;

	@ManyToOne(fetch = FetchType.EAGER) // Always fetch associated user details
	@JoinColumn(name = "user_id")
	private User user;
}
