package ph.txtdis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.AddressType;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeAddress extends AbstractNumbered {

    private static final long serialVersionUID = -3587005294480579590L;

    @Column(nullable = false)
    private AddressType type;

    @Column(length = 64, nullable = false)
    private String location;
}
