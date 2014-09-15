package ph.txtdis.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ph.txtdis.model.SystemUser;
import ph.txtdis.service.EmployeeService;
import ph.txtdis.service.StyleService;
import ph.txtdis.service.UserService;

@Component
public class EmployeeSetupImpl implements Setup {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    UserService userService;

    @Autowired
    StyleService styleService;

    public EmployeeSetupImpl() {
    }

    @Override
    public void start() {

        userService.save(new SystemUser("JACKIE", "robbie", true));
        
        /**
        byte[] female = getImagebytes("female");
        byte[] wolf = getImagebytes("wolf");

        Employee maria = employeeService.save(new Employee("DE LA CRUZ", "MARIA"));
        maria.setMiddleInitial("K");
        maria.setPhoto(female);
        maria.setBirthdate(DIS.parseDate("1960-01-01"));
        maria.setBirthplace("MARIKINA, METRO MANILA");
        maria.setCivilStatus(CivilStatus.MARRIED);
        maria.setEmergencyContact("JUAN");
        maria.setEmergencyRelation(FamilyType.SPOUSE);
        maria.setEmergencyPhone("0918-888-8888");

        Employee mozart = employeeService.save(new Employee("MOZART", "WOLFGANG"));
        mozart.setMiddleInitial("A");
        mozart.setPhoto(wolf);
        mozart.setBirthdate(DIS.parseDate("1756-01-27"));
        mozart.setBirthplace("SALZBURG, AUSTRIA");
        mozart.setCivilStatus(CivilStatus.MARRIED);
        mozart.setEmergencyContact("CONSTANZE");
        mozart.setEmergencyRelation(FamilyType.SPOUSE);
        mozart.setEmergencyPhone("+43-662-123-4567");

        List<EmployeeAddress> addys = new ArrayList<>();
        addys.add(new EmployeeAddress(maria, AddressType.HOME, "123 ILANG-ILANG ST., LA COLINA SUBD., ANTIPOLO CITY"));
        maria.setAddresses(addys);

        List<EmployeeContactInfo> info = new ArrayList<>();
        info.add(new EmployeeContactInfo(maria, ContactInfoType.HOME_PHONE, "123-4567"));
        info.add(new EmployeeContactInfo(maria, ContactInfoType.CELLPHONE, "0917-777-7777"));
        info.add(new EmployeeContactInfo(maria, ContactInfoType.CELLPHONE, "0922-222-2222"));
        info.add(new EmployeeContactInfo(maria, ContactInfoType.E$MAIL, "maria.dela.cruz@gmail.com"));
        info.add(new EmployeeContactInfo(maria, ContactInfoType.TWITTER, "@maria"));
        maria.setContactInfo(info);

        Byte[] ltoId = getImageBytes("LTO");
        Byte[] sssId = getImageBytes("SSS");
        Byte[] tinId = getImageBytes("TIN");
        Byte[] philHealthId = getImageBytes("PhilHealth");
        Byte[] pagIbigId = getImageBytes("PagIbig");

        Date issuance = DIS.parseDate("2013-01-01");
        Date expiry = DIS.parseDate("2015-12-31");

        List<GovtId> govtIds = new ArrayList<>();
        govtIds.add(new GovtId(maria, ltoId, GovtIdType.LTO, issuance, expiry, "A12-345-6789"));
        govtIds.add(new GovtId(maria, sssId, GovtIdType.SSS, issuance, expiry, "01-2345678-9"));
        govtIds.add(new GovtId(maria, tinId, GovtIdType.TIN, issuance, expiry, "A12-345-6789"));
        govtIds.add(new GovtId(maria, philHealthId, GovtIdType.PHILHEALTH, issuance, expiry, "12-345678901-2"));
        govtIds.add(new GovtId(maria, pagIbigId, GovtIdType.PAG_IBIG, issuance, expiry, "1234-5678-9012"));
        maria.setGovtIds(govtIds);

        List<Family> relatives = new ArrayList<>();
        relatives.add(new Family(maria, FamilyType.SPOUSE, "DE LA CRUZ", "JUAN", "B", DIS.parseDate("1962-12-31"),
                "MAGNUM GROWTH", "DRIVER"));
        relatives.add(new Family(maria, FamilyType.CHILD, "DE LA CRUZ", "NINO", "B", DIS.parseDate("1990-02-02"),
                "U.P.", "STUDENT"));
        relatives.add(new Family(maria, FamilyType.CHILD, "DE LA CRUZ", "NINA", "B", DIS.parseDate("1996-03-03"),
                "U.P.I.S.", "STUDENT"));
        relatives.add(new Family(maria, FamilyType.FATHER, "DE LA CRUZ", "PAPA", "A", DIS.parseDate("1930-04-04"),
                "DECEASED", "N/A"));
        relatives.add(new Family(maria, FamilyType.MOTHER, "DE LA CRUZ", "MAMA", "E", DIS.parseDate("1936-05-05"),
                "NONE", "HOUSEWIFE"));
        relatives.add(new Family(maria, FamilyType.SIBLING, "DE LA CRUZ", "ANDRES", "K", DIS.parseDate("1964-06-06"),
                "MLA. LOCAL GOV'T", "BRGY. 201 KAGAWAD"));
        relatives.add(new Family(maria, FamilyType.SIBLING, "SILANG", "GABRIELA", "D", DIS.parseDate("1968-07-07"),
                "B.P.I.", "BRANCH MANAGER"));
        maria.setRelatives(relatives);

        List<Education> studies = new ArrayList<>();
        studies.add(new Education(maria, DIS.parseDate("1970-06-01"), DIS.parseDate("1977-03-31"), "TONDO ELEM. SCH.",
                "ELEMENTARY", "GRADUATED"));
        studies.add(new Education(maria, DIS.parseDate("1977-06-01"), DIS.parseDate("1981-03-31"), "TONDO HIGH SCH.",
                "HIGH SCHOOL", "GRADUATED"));
        studies.add(new Education(maria, DIS.parseDate("1981-06-01"), DIS.parseDate("1986-03-31"), "P.L.M", "B.S.I.E",
                "GRADUATED"));
        studies.add(new Education(maria, DIS.parseDate("1988-06-01"), DIS.parseDate("1988-03-31"), "A.I.M", "M.B.A",
                "6 UNITS"));
        maria.setStudies(studies);

        List<PastWork> pastJobs = new ArrayList<>();
        pastJobs.add(new PastWork(maria, DIS.parseDate("1981-01-01"), DIS.parseDate("1984-12-31"), "C.C.B.P.I",
                "SALES SPECIALIST", new BigDecimal("12345.00"), "PROMOTION", "NONE", "N/A", "N/A"));
        pastJobs.add(new PastWork(maria, DIS.parseDate("1985-01-01"), DIS.parseDate("1988-12-31"), "C.C.B.P.I",
                "SALES SUPERVISOR", new BigDecimal("67890.00"), "PROMOTION", "NONE", "N/A", "N/A"));
        pastJobs.add(new PastWork(maria, DIS.parseDate("1988-01-01"), DIS.parseDate("2012-12-31"), "C.C.B.P.I",
                "SALES MANAGER", new BigDecimal("123456.00"), "RETIREMENT", "MANUEL QUEZON", "DIRECTOR",
                "0922-222-2222"));
        maria.setPastJobs(pastJobs);

        List<Assignment> assignments = new ArrayList<>();
        assignments.add(new Assignment(maria, DIS.parseDate("2013-01-01"), "SMIS, STA. MARIA", "OPS. MGR."));
        Assignment assignment = (new Assignment(maria, DIS.parseDate("2013-01-01"), "COKE, HAVANA", "OPS. MGR."));
        assignment.setEndDate(DIS.parseDate("2013-12-31"));
        assignments.add(assignment);
        assignments.add(new Assignment(maria, DIS.parseDate("2014-01-01"), "GSM1, EDSA", "OPS. MGR."));
        maria.setAssignments(assignments);

        List<Compensation> dailyRates = new ArrayList<>();
        dailyRates.add(new Compensation(maria, DIS.parseDate("2013-01-01"), new BigDecimal("1234.00")));
        dailyRates.add(new Compensation(maria, DIS.parseDate("2014-01-01"), new BigDecimal("2345.00")));
        maria.setDailyRates(dailyRates);

        List<Leave> leaves = new ArrayList<>();
        leaves.add(new Leave(maria, LeaveType.VLOP, DIS.parseDate("2014-01-15"), 14));
        maria.setLeaves(leaves);

        List<Loan> loans = new ArrayList<>();
        Loan oldLoan = new Loan(maria, LoanType.PAG_IBIG, DIS.parseDate("2013-06-01"), new BigDecimal("123.00"));
        loans.add(oldLoan);
        Loan newLoan = new Loan(maria, LoanType.PENALTY, DIS.parseDate("2014-01-01"), new BigDecimal("777777.00"));
        loans.add(newLoan);
        Loan testLoan = new Loan(maria, LoanType.SSS, DIS.parseDate("2012-12-31"), new BigDecimal("456.78"));
        loans.add(testLoan);

        List<Payment> newPayments = new ArrayList<>();
        newPayments.add(new Payment(newLoan, DIS.parseDate("2014-01-31"), new BigDecimal("111111.00")));
        newPayments.add(new Payment(newLoan, DIS.parseDate("2014-02-28"), new BigDecimal("111111.00")));
        newPayments.add(new Payment(newLoan, DIS.parseDate("2014-03-31"), new BigDecimal("111111.00")));
        newPayments.add(new Payment(newLoan, DIS.parseDate("2014-04-30"), new BigDecimal("111111.00")));
        newPayments.add(new Payment(newLoan, DIS.parseDate("2014-05-31"), new BigDecimal("111111.00")));
        Payment payment = new Payment(newLoan, DIS.parseDate("2014-06-30"), new BigDecimal("111111.00"));
        newPayments.add(payment);
        newLoan.setPayments(newPayments);
        saveToList(loans, newLoan);

        List<Payment> oldPayments = new ArrayList<>();
        oldPayments.add(new Payment(oldLoan, DIS.parseDate("2013-06-30"), new BigDecimal("111111.00")));
        oldLoan.setPayments(oldPayments);
        saveToList(loans, oldLoan);

        List<Payment> testPayments = new ArrayList<>();
        testPayments.add(new Payment(testLoan, DIS.parseDate("2013-06-30"), new BigDecimal("9999.00")));
        testLoan.setPayments(oldPayments);
        saveToList(loans, testLoan);

        payment.setAmount(new BigDecimal("222222.00"));
        saveToList(newPayments, payment);
        newLoan.setPayments(newPayments);
        saveToList(loans, newLoan);
        maria.setLoans(loans);

        List<Discipline> das = new ArrayList<>();
        Discipline da = new Discipline(maria, DIS.parseDate("2014-01-15"), "AWOL");
        da.setDecision("NOT GUILTY");
        da.setNoticeGiven(true);
        das.add(da);
        maria.setDisciplinaryActions(das);

        Discipline da2 = new Discipline(maria, DIS.parseDate("2014-12-31"), "LOWA");
        das.add(da2);
        maria.setDisciplinaryActions(das);
        maria = employeeService.save(maria);

        int id = maria.getId();
        maria.setDisciplinaryActions(employeeService.getDisciplinaryActions(id));
        das = maria.getDisciplinaryActions();
        da2 = das.get(1);
        maria.setDisciplinaryActions(das);
        maria = employeeService.save(maria);

        maria.setLoans(employeeService.getLoans(maria.getId()));
        loans = maria.getLoans();
        maria.setLoans(loans);
        employeeService.save(maria);
        **/
    }
    
    /*

    private Byte[] getImageBytes(String name) {
        try {
            InputStream photoInput = this.getClass().getResourceAsStream("/demo/" + name + ".jpg");
            return ArrayUtils.toObject(IOUtils.toByteArray(photoInput));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private byte[] getImagebytes(String name) {
        try {
            InputStream photoInput = this.getClass().getResourceAsStream("/demo/" + name + ".jpg");
            return IOUtils.toByteArray(photoInput);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private <E> void saveToList(List<E> items, E item) {
        int index = items.indexOf(item);
        if (index < 0)
            items.add(item);
        else
            items.set(index, item);
    }
    
    */
}
