package com.msp.webportal.businesslogic.constant;

public enum AuditModule {
    /** The otp auth. */
    CARD_TRACK_STATUS("PWP-MAPP-1001","Track Status Request", "Track Status"),

    /** The auth transaction history. */
    AUTH_TRANSACTION_HISTORY("PWP-RH-100","Auth Transaction History Request", "Auth Transaction History"),
    BIO_TRANSACTION_HISTORY("PWP-RH-101","Bio/Demo/Otp Transaction History Request", "Bio/Demo/Otp Transaction History"),
    RETRIVE_PCN_OTP("PWP-MAPP-1005","Retrive PCN otp request","Retrieve pcn otp"),
    KNOWN_CARD_DETAILS("PWP-MAPP-1002","Known PCN Request", "Known Your PCN"),

    DEMO_DETAILS("PWP-MAPP-1003","Demographic Details Request", "Demographic Details"), //not applicable for release v1

    DISPLAY_VID("PWP-MAPP-1004","Display VID Request", "Display VID"),

    /** The vid generation request. */
    VID_GENERATION_REQUEST("PWP-RS-300","VID Generation requested", "VID Generation"),//not applicable for release v1
    AUTH_LOCK_UNLOCK_REQUSET("PWP-RS-301","Auth Lock/Unlock Request", "Auth Lock/Unlock "),

    VID_RUD_REQUEST("PWP-RS-302","VID De-acticate/Delete Request", "VID De-acticate/Delete "),
    DEMO_UPDATE_REQUEST("PWP-RS-303","Demo Update Request", "Demo Update"),
    LOST_STOLEN_REQUEST("PWP-RS-304","Lost Stolen Card Request", "Lost Stolen Card "),
    OTP_REQUEST("PWP-RS-305","OTP Generation Request", "OTP Generation"),
    OTP_MANAGER_REQUEST("PWP-OTP-200","OTP Generation Request", "OTP Generation"),
    OTP_MANAGER_VALIDATE("PWP-OTP-201","OTP Validation Request", "OTP Validation"),

    FEEDBACK("PWP-10000","Feedback Request", "Feedback and Greviences"),
    PAYMENT_GATEWAY("PWP-10001","Payment Gateway Request", "Payment Gateway"),

    USER_MANAGEMENT("PWP-10002","User Login/Register/Forget pwd Request", "User Login/Register/Forget pwd");

//not applicable for release v1
//not applicable for release v1

    /** The module id. */
    /**  The Module id. */
    private final String moduleId;

    /**  The Description. */
    private  String desc;

    /** The module name. */
    private String moduleName;



    /**
     *  Instantiates a new audit contants.
     *
     *
     * @param moduleId the module id
     * @param desc the desc
     * @param moduleName the module name
     */
    private AuditModule(String moduleId, String desc, String moduleName) {
        this.moduleId=moduleId;
        this.desc=desc;
        this.moduleName=moduleName;
    }


    /**
     * Gets the desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Gets the module id.
     *
     * @return the module id
     */
    public String getModuleId() {
        return moduleId;
    }

    /**
     * Gets the module name.
     *
     * @return the module name
     */
    public String getModuleName() {
        return this.moduleName;
    }
}

