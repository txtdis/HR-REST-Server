package ph.txtdis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import ph.txtdis.type.ContactInfoType;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmployeeContactInfo extends AbstractNumbered {

    private static final long serialVersionUID = -3583165118929909989L;

    @Column(nullable = false)
    private ContactInfoType type;

    @Column(nullable = false)
    private String detail;
}
