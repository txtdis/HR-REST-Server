package ph.txtdis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Style extends AbstractNumbered {

    private static final long serialVersionUID = -4479575580426911105L;

    @Column(nullable = false)
    private String base;

    @Column(nullable = false)
    private String background;

    @Column(nullable = false)
    private String accent;

    @Column(nullable = false)
    private String font;
}
