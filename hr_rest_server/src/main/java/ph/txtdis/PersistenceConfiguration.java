package ph.txtdis;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import ph.txtdis.domain.Authority;
import ph.txtdis.domain.Compensation;
import ph.txtdis.domain.Discipline;
import ph.txtdis.domain.Education;
import ph.txtdis.domain.Employee;
import ph.txtdis.domain.EmployeeAddress;
import ph.txtdis.domain.EmployeeContactInfo;
import ph.txtdis.domain.Family;
import ph.txtdis.domain.GovtId;
import ph.txtdis.domain.Leave;
import ph.txtdis.domain.Loan;
import ph.txtdis.domain.PastWork;
import ph.txtdis.domain.Payment;
import ph.txtdis.domain.User;
import ph.txtdis.repository.EmployeeRepository;
import ph.txtdis.repository.UserRepository;
import ph.txtdis.type.AddressType;
import ph.txtdis.type.CivilStatus;
import ph.txtdis.type.ContactInfoType;
import ph.txtdis.type.FamilyType;
import ph.txtdis.type.GovtIdType;
import ph.txtdis.type.LeaveType;
import ph.txtdis.type.LoanType;
import ph.txtdis.type.UserType;
import ph.txtdis.util.DIS;
import ph.txtdis.util.Spring;

@Configuration
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersistenceConfiguration {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void start() {
        if (userRepository.count() >= 1)
            return;

        List<Authority> roles = Arrays.asList(new Authority(UserType.MANAGER));
        User sysgen = new User("SYSGEN", encode("I'mSysGen4txtDIS@PostgreSQL"),
                true);
        sysgen.setRoles(roles);
        userRepository.save(sysgen);

        User jackie = new User("JACKIE", encode("robbie"), true);
        jackie.setEmail("manila12@gmail.com");
        jackie.setRoles(roles);
        userRepository.save(jackie);

        User ronald = new User("RONALD", encode("alphacowboy"), true);
        ronald.setEmail("ronaldallanso@yahoo.com");
        ronald.setRoles(roles);
        userRepository.save(ronald);

        byte[] female = getImagebytes("female");
        byte[] wolf = getImagebytes("male");

        Employee maria = employeeRepository.save(new Employee("DE LA CRUZ",
                "MARIA"));
        maria.setMiddleInitial("K");
        maria.setPhoto(female);
        maria.setBirthDate(LocalDate.parse("1960-01-01"));
        maria.setBirthplace("MARIKINA, METRO MANILA");
        maria.setCivilStatus(CivilStatus.MARRIED);
        maria.setEmergencyContact("JUAN");
        maria.setEmergencyRelation(FamilyType.SPOUSE);
        maria.setEmergencyPhone(DIS.persistPhone("09181234567"));

        Employee mozart = employeeRepository.save(new Employee("MOZART",
                "WOLFGANG"));
        mozart.setMiddleInitial("A");
        mozart.setPhoto(wolf);
        mozart.setBirthDate(LocalDate.parse("1756-01-27"));
        mozart.setBirthplace("SALZBURG, AUSTRIA");
        mozart.setCivilStatus(CivilStatus.MARRIED);
        mozart.setEmergencyContact("CONSTANZE");
        mozart.setEmergencyRelation(FamilyType.SPOUSE);
        mozart.setEmergencyPhone(DIS.persistPhone("09171234567"));

        List<EmployeeAddress> addys = new ArrayList<>();
        addys.add(new EmployeeAddress(AddressType.HOME,
                "123 ILANG-ILANG ST., LA COLINA SUBD., ANTIPOLO CITY"));
        maria.setAddresses(addys);

        List<EmployeeContactInfo> info = new ArrayList<>();

        info.add(new EmployeeContactInfo(ContactInfoType.HOME_PHONE, DIS
                .persistPhone("02 123 4567")));
        info.add(new EmployeeContactInfo(ContactInfoType.CELLPHONE, DIS
                .persistPhone("09055555555")));
        info.add(new EmployeeContactInfo(ContactInfoType.E$MAIL,
                "maria.dela.cruz@gmail.com"));
        info.add(new EmployeeContactInfo(ContactInfoType.TWITTER, "@maria"));
        maria.setContactInfo(info);

        byte[] ltoId = getImagebytes("LTO");
        byte[] sssId = getImagebytes("SSS");
        byte[] tinId = getImagebytes("TIN");
        byte[] philHealthId = getImagebytes("PhilHealth");
        byte[] pagIbigId = getImagebytes("PagIbig");

        LocalDate issuance = LocalDate.parse("2013-01-01");
        LocalDate expiry = LocalDate.parse("2015-12-31");

        List<GovtId> govtIds = new ArrayList<>();
        govtIds.add(new GovtId(ltoId, GovtIdType.LTO, issuance, expiry,
                "A12-345-6789"));
        govtIds.add(new GovtId(sssId, GovtIdType.SSS, issuance, expiry,
                "01-2345678-9"));
        govtIds.add(new GovtId(tinId, GovtIdType.TIN, issuance, expiry,
                "A12-345-6789"));
        govtIds.add(new GovtId(philHealthId, GovtIdType.PHILHEALTH, issuance,
                expiry, "12-345678901-2"));
        govtIds.add(new GovtId(pagIbigId, GovtIdType.PAG_IBIG, issuance, expiry,
                "1234-5678-9012"));
        maria.setGovtIds(govtIds);

        List<Family> relatives = new ArrayList<>();
        relatives.add(new Family(FamilyType.SPOUSE, "DE LA CRUZ", "JUAN",
                LocalDate.parse("1962-12-31"), "MAGNUM GROWTH", "DRIVER"));
        relatives.add(new Family(FamilyType.CHILD, "DE LA CRUZ", "NINO",
                LocalDate.parse("1990-02-02"), "U.P.", "STUDENT"));
        relatives.add(new Family(FamilyType.CHILD, "DE LA CRUZ", "NINA",
                LocalDate.parse("1996-03-03"), "U.P.I.S.", "STUDENT"));
        relatives.add(new Family(FamilyType.FATHER, "DE LA CRUZ", "PAPA",
                LocalDate.parse("1930-04-04"), "DECEASED", "N/A"));
        relatives.add(new Family(FamilyType.MOTHER, "DE LA CRUZ", "MAMA",
                LocalDate.parse("1936-05-05"), "NONE", "HOUSEWIFE"));
        relatives.add(new Family(FamilyType.SIBLING, "DE LA CRUZ", "ANDRES",
                LocalDate.parse("1964-06-06"), "MLA. LOCAL GOV'T",
                "BRGY. 201 KAGAWAD"));
        relatives.add(new Family(FamilyType.SIBLING, "SILANG", "GABRIELA",
                LocalDate.parse("1968-07-07"), "B.P.I.", "BRANCH MANAGER"));
        maria.setRelatives(relatives);

        List<Education> studies = new ArrayList<>();
        studies.add(new Education(LocalDate.parse("1970-06-01"), LocalDate
                .parse("1977-03-31"), "TONDO ELEM. SCH.", "ELEMENTARY",
                "GRADUATED"));
        studies.add(new Education(LocalDate.parse("1977-06-01"), LocalDate
                .parse("1981-03-31"), "TONDO HIGH SCH.", "HIGH SCHOOL",
                "GRADUATED"));
        studies.add(new Education(LocalDate.parse("1981-06-01"), LocalDate
                .parse("1986-03-31"), "P.L.M", "B.S.I.E", "GRADUATED"));
        studies.add(new Education(LocalDate.parse("1988-06-01"), LocalDate
                .parse("1988-03-31"), "A.I.M", "M.B.A", "6 UNITS"));
        maria.setStudies(studies);

        List<PastWork> pastJobs = new ArrayList<>();
        pastJobs.add(new PastWork(LocalDate.parse("1981-01-01"), LocalDate
                .parse("1984-12-31"), "C.C.B.P.I", "SALES SPECIALIST",
                new BigDecimal("12345.00"), "PROMOTION", "NONE", "N/A", DIS
                        .persistPhone("09051234567")));
        pastJobs.add(new PastWork(LocalDate.parse("1985-01-01"), LocalDate
                .parse("1988-12-31"), "C.C.B.P.I", "SALES SUPERVISOR",
                new BigDecimal("67890.00"), "PROMOTION", "NONE", "N/A", DIS
                        .persistPhone("09061234567")));
        pastJobs.add(new PastWork(LocalDate.parse("1988-01-01"), LocalDate
                .parse("2012-12-31"), "C.C.B.P.I", "SALES MANAGER",
                new BigDecimal("123456.00"), "RETIREMENT", "MANUEL QUEZON",
                "DIRECTOR", DIS.persistPhone("09071234567")));
        maria.setPastJobs(pastJobs);

        List<Compensation> dailyRates = new ArrayList<>();
        dailyRates.add(new Compensation(LocalDate.parse("2013-01-01"),
                new BigDecimal("1234.00")));
        dailyRates.add(new Compensation(LocalDate.parse("2014-01-01"),
                new BigDecimal("2345.00")));
        maria.setDailyRates(dailyRates);

        List<Leave> leaves = new ArrayList<>();
        leaves.add(new Leave(LeaveType.VLOP, LocalDate.parse("2014-01-15"),
                14));
        maria.setLeaves(leaves);

        List<Loan> loans = new ArrayList<>();
        Loan oldLoan = new Loan(LoanType.PAG_IBIG, LocalDate.parse(
                "2013-06-01"), new BigDecimal("123.00"));
        loans.add(oldLoan);
        Loan newLoan = new Loan(LoanType.PENALTY, LocalDate.parse("2014-01-01"),
                new BigDecimal("777777.00"));
        loans.add(newLoan);
        Loan testLoan = new Loan(LoanType.SSS, LocalDate.parse("2012-12-31"),
                new BigDecimal("456.78"));
        loans.add(testLoan);

        List<Payment> newPayments = new ArrayList<>();
        newPayments.add(new Payment(LocalDate.parse("2014-01-31"),
                new BigDecimal("111111.00")));
        newPayments.add(new Payment(LocalDate.parse("2014-02-28"),
                new BigDecimal("111111.00")));
        newPayments.add(new Payment(LocalDate.parse("2014-03-31"),
                new BigDecimal("111111.00")));
        newPayments.add(new Payment(LocalDate.parse("2014-04-30"),
                new BigDecimal("111111.00")));
        newPayments.add(new Payment(LocalDate.parse("2014-05-31"),
                new BigDecimal("111111.00")));
        Payment payment = new Payment(LocalDate.parse("2014-06-30"),
                new BigDecimal("111111.00"));
        newPayments.add(payment);
        newLoan.setPayments(newPayments);
        saveToList(loans, newLoan);

        List<Payment> oldPayments = new ArrayList<>();
        oldPayments.add(new Payment(LocalDate.parse("2013-06-30"),
                new BigDecimal("111111.00")));
        oldLoan.setPayments(oldPayments);
        saveToList(loans, oldLoan);

        List<Payment> testPayments = new ArrayList<>();
        testPayments.add(new Payment(LocalDate.parse("2013-06-30"),
                new BigDecimal("9999.00")));
        testLoan.setPayments(oldPayments);
        saveToList(loans, testLoan);

        payment.setPaymentValue(new BigDecimal("222222.00"));
        saveToList(newPayments, payment);
        newLoan.setPayments(newPayments);
        saveToList(loans, newLoan);
        maria.setLoans(loans);

        List<Discipline> das = new ArrayList<>();
        Discipline da = new Discipline(LocalDate.parse("2014-01-15"), "AWOL");
        da.setDecision("NOT GUILTY");
        da.setNoticeGiven(true);
        das.add(da);

        Discipline da2 = new Discipline(LocalDate.parse("2014-12-31"), "LOWA");
        das.add(da2);
        maria.setDisciplinaryActions(das);

        maria = employeeRepository.save(maria);
        mozart = employeeRepository.save(mozart);
    }

    private byte[] getImagebytes(String name) {
        InputStream photoInput = this.getClass().getResourceAsStream("/demo/"
                + name + ".jpg");
        return DIS.toBytes(photoInput);
    }

    private <E> void saveToList(List<E> items, E item) {
        if (items.contains(item))
            items.remove(item);
        items.add(item);
    }

    private String encode(String password) {
        return Spring.encode(password);
    }
}
