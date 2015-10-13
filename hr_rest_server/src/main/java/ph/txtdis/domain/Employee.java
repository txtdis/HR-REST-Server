package ph.txtdis.domain;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Type;

import lombok.AccessLevel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.CivilStatus;
import ph.txtdis.type.FamilyType;

@Data
@Entity
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Employee extends AuditedNumbered {

	private static final long serialVersionUID = 6502834099130082276L;

	@Column(nullable = false)
	private String surname;

	@Column(nullable = false)
	private String name;

	@Column(length = 1)
	private String middleInitial;

	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] photo;

	private LocalDate birthDate;

	private String birthplace;

	private CivilStatus civilStatus;

	private String emergencyContact;

	private FamilyType emergencyRelation;

	private String emergencyPhone;

	@JoinColumn(name = "employee_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<EmployeeAddress> addresses;

	@JoinColumn(name = "employee_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<EmployeeContactInfo> contactInfo;

	@JoinColumn(name = "employee_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<GovtId> govtIds;

	@JoinColumn(name = "employee_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Family> relatives;

	@JoinColumn(name = "employee_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Education> studies;

	@JoinColumn(name = "employee_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<PastWork> pastJobs;

	@JoinColumn(name = "employee_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Assignment> assignments;

	@JoinColumn(name = "employee_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Compensation> dailyRates;

	@JoinColumn(name = "employee_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Leave> leaves;

	@JoinColumn(name = "employee_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Loan> loans;

	@JoinColumn(name = "employee_id")
	@OneToMany(cascade = CascadeType.ALL)
	private List<Discipline> disciplinaryActions;

	public Employee(String surname, String name) {
		this.surname = surname;
		this.name = name;
	}

	@Override
	public String toString() {
		return surname + ", " + name;
	}
}
