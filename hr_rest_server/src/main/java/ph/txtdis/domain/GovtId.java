package ph.txtdis.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;

import org.hibernate.annotations.Type;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.GovtIdType;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class GovtId extends AbstractNumbered {

	private static final long serialVersionUID = -5750732249679224119L;

	@Column(nullable = false)
	@Type(type = "org.hibernate.type.BinaryType")
	private byte[] image;

	@Column(nullable = false)
	private GovtIdType type;

	@Column(nullable = false)
	private LocalDate issuanceDate;

	@Column(nullable = false)
	private LocalDate expiryDate;

	@Column(nullable = false)
	private String detail;
}
