package ph.txtdis.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.FamilyType;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Family extends AbstractNumbered {

	private static final long serialVersionUID = -5822699684522028625L;

	@Column(nullable = false)
	private FamilyType type;

	@Column(length = 16, nullable = false)
	private String surname;

	@Column(length = 24, nullable = false)
	private String name;

	@Column(nullable = false)
	private LocalDate birthDate;

	@Column(length = 16, nullable = false)
	private String institution;

	@Column(length = 24, nullable = false)
	private String designation;
}
