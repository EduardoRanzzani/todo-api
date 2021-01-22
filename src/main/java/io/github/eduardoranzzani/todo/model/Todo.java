package io.github.eduardoranzzani.todo.model;

import java.time.LocalDateTime;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "todo")
public class Todo {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "description")
	private String description;
	@Column(name = "done")
	private Boolean done;
	@Column(name = "register_date")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime registerDate;
	@Column(name = "done_date")
	@JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private LocalDateTime doneDate;

	@PrePersist
	public void beforeSave() {
		setRegisterDate(LocalDateTime.now());
	}

}
