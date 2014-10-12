package ph.txtdis.util;

public enum Type {
	// Modules
	BACKUP("Backup"),
	BANK_LIST("Bank List"), 
	BOM("Bill of Materials"),
	COUNT("Stock Take"),
	CASH_SETTLEMENT("Cash Settlement"),
	CONTACT_DETAIL("Contact"),
	COUNT_LIST("Stock Take List"), 
	COUNT_VARIANCE("Stock Take Variance"),
	CUSTOMER("Customer"),
	CUSTOMER_LIST("Customer List"),
	DELIVERY("Delivery/Credit/Debit Order"),
	DSR("Daily Sales Report"),
	FINANCE("Financial Reports"),
	INVENTORY("Inventory"), 
	INVOICE("Invoice"),
	INVOICE_BOOKLET("Issued Invoice Booklet", "Dialog"),
	INVOICE_BOOKLET_LIST("Issued Invoice Booklet List"),
	INVOICE_DELIVERY_LIST("Invoice/Delivery Report"),
	ITEM("Item Data"), 
	ITEM_LIST("Item List"),
	ITEM_LOG("Item Movement Log"),
	LOAD_SETTLEMENT("Load Settlement"),
	MAIN_MENU("Main Menu"),
	OUTLET_LIST("Outlet List"),
	OVERDUE("Overdue"),
	PRICE_LIST("Price List"),
	PURCHASE("Purchase/Return Order"),
	PURCHASE_TARGET("Purchase Target"),
	REMIT("Remittance"),
	REMIT_SETTLEMENT("Remittance Settlement"),
	RECEIVABLES("Aging Receivables"),
	RECEIVING("Receiving Report"), 
	RECEIVING_LIST("Receiving Report List"),
	RESTORE("Restore"),
	SETTLEMENT("Route Report"),
	SALES("Sales/Bad Order"),
	SALES_LIST("Sales Order List"),
	SALES_REPORT("Sales Report"),
	SALES_TARGET("Sales Target"), 
	SALES_TARGET_LIST("Sales Target List"), 
	TRANSMITTAL("Transmittal"), 
	VAT("Value-Added Tax"),

	// Items
	BUNDLED, DERIVED, MONETARY, PURCHASED, REPACKED, VIRTUAL,

	// Product Lines
	REF_MEAT,

	// User Group
	SUPER_FINANCE, SUPER_SUPPLY, SUPER_USER, USER_FINANCE, USER_SALES, USER_SUPPLY, 
	// Sales Report
	STT, CALL,

	// Pricing
	WHOLESALE, RETAIL, 

	// Labels
	ADDRESS("ADDRESS", "Address"),
	BANK("BANK", "Bank"),
	BARRIO("BARANGAY", "District"),
	CHANNEL("CHANNEL", "Channel"), 
	CHECKER("CHECKER", "Checker"),
	DATE("DATE", "Date"), 
	DESIGNATION("DESIGNATION", "Designation"),
	DISCOUNT1("0.00%", "Discount1Percent"),
	DISCOUNT2("0.00%", "Discount2Percent"),
	ENCODER("ENCODER", "Encoder"), 
	ENCODED_TIME("TIME", "EncodingTime"), 
	ENCODED_DATE("DATE", "EncodingDate"), 
	FIRST("NAME", "FirstName"),
	HQ("NAME", "hq"),
	HQ_ID("MAIN ID", "hqId"),
	ID("ID", "Id"),
	LOCATION("LOCATION", "Location"),
	NAME("NAME", "Name"), 
	NONE(""), 
	PARTNER("PARTNER", "Partner"), 
	PARTNER_ID("ID", "PartnerId"), 
	PARTNER_NAME("", "Partner"), 
	PHONE("PHONE", "Phone"),
	PROVINCE("PROVINCE/CITY", "Province"),
	RR("R/R #", "Id"),
	TAG("TAG #", "Id"),
	TAKER("TAKER", "Taker"),
	TOWN("TOWN/DISTRICT", "Town"),
	SMS("SMS", "Sms"), 
	SMS_ID("SMS ID", "SmsId"),
	SO_PO("S/O(P/O) #", "ReferenceId"),
	STREET("STREET", "Street"),
	SURNAME("SURNAME", "Surname"),
	TOTAL("TOTAL", "ComputedTotal"),
	VATABLE("VATABLE", "TotalVatable"), 
	VAT_TOTAL("VAT", "TotalVat"),

	// Buttons
	ADD("Add new entry", "add"),
	ADJUST("Adjust data", "adjust"),
	BACKWARD("Go to previous", "goPrevious"), 
	BOOKLET("List booklet", "listBooklet"), 
	CALENDAR("Select date/s", "selectDate"), 
	CANCEL("Delete an entry", "cancel"), 
	CLOSE("Tag as closed", "closeTransaction"),
	DUMP("Dump data to a .xls file", "dumpData"), 
	EDIT("Edit data", "edit"), 
	EXCEL("Write data to a .xls file", "saveAsExcel"), 
	EXTRACT("List undeposited\nremittances", "getUndeposited"), 
	IMPORT("Import template", "importTemplate"), 
	FORWARD("Go to next", "goNext"), 
	NEW("Create a new file", "restart"), 
	OPEN("Open a saved file", "open"), 
	PRINT("Print", "print"), 
	REPORT("Generate report", "generateReport"), 
	SAVE("Post data to server", "post"), 
	SEARCH("Search List", "searchList"), 
	SEARCH16("Search List"), 
	TARGET("Show target/s", "showTarget"),
	TRANSMIT("Transmit remittances", "transmit"),
	OPTION("Pick an option", "selectOption"), 
	VARIANCE("Show variances", "showVariance"),
	WARNING, 
	WIZARD("Generate data", "generateData"), 
		
	// Tables
	ACCOUNT, 
	
	// Quality
	BAD,
	GOOD,
	ONHOLD,
	
	// OUM
	PK,
	CS,
	KG,
	L,
	TE;

	private String name, method;

	Type() {
	}

	Type(String name) {
		this.name = name;
	}

	Type(String name, String method) {
		this.name = name;
		this.method = method;
	}

	public String getMethod() {
		return method;
	}

	public String getName() {
		return name;
	}
}
