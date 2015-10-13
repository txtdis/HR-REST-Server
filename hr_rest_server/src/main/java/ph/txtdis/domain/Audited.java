package ph.txtdis.domain;

import java.time.ZonedDateTime;

public interface Audited {

    String getCreatedBy();

    ZonedDateTime getCreatedOn();
}
