package com.msp.webportal.businesslogic.constant;

public enum AuditEvents {
    LOGIN_REQUEST("PWP-EVT-001","SYSTEM", "User Login"),//not applicable for release v1
    CREATE_USER_REQUEST("PWP-EVT-002","SYSTEM", "User Registration"),//not applicable for release v1
    FORGET_PWD_REQUEST("PWP-EVT-003","SYSTEM", "Forget Password User"),//not applicable for release v1
    TRACK_STATUS_REQUEST("PWP-EVT-004","SYSTEM", "Track Status"),//not applicable for release v1

    KNOW_PCN_OTP_REQUEST("PWP-EVT-021","SYSTEM", "Known PCN otp "),//not applicable for release v1
    KNOW_PCN_REQUEST("PWP-EVT-005","SYSTEM", "Known PCN"),//not applicable for release v1
    LOCK_PCN("PWP-EVT-006","SYSTEM", "Lock Your PCN"),//not applicable for release v1
    UNLOCK_PCN("PWP-EVT-007","SYSTEM", "Un-Lock Your PCN"),//not applicable for release v1
    RESIDENT_OTP("PWP-EVT-008","SYSTEM", "Resident OTP Requset"),//not applicable for release v1
    AUTH_STATUS("PWP-EVT-009","SYSTEM", "Auth Status Request"),//not applicable for release v1
    BIO_DEMO_UPDATE_HISTORY("PWP-EVT-010","SYSTEM", "BIO/DEMO/UPDATE History"),//not applicable for release v1
    AUTH_LOCK_UNLOCK_HISTORY("PWP-EVT-011","SYSTEM", "Auth Lock/Unlock History"),//not applicable for release v1
    CREATE_VID_GENERATION("PWP-EVT-012","SYSTEM", "VID Generation Request"),//not applicable for release v1
    VIEW_VID_REQUEST("PWP-EVT-013","SYSTEM", "VID View Request"),//not applicable for release v1
    DAU_VID_REQUEST("PWP-EVT-013","SYSTEM", "Delete/Deactivated/Activated VID Request"),//not applicable for release v1

    VIEW_DEMO_REQUEST("PWP-EVT-014","SYSTEM", "View Demo Requset"),//not applicable for release v1
    OTP_MANAGER_REQUEST("PWP-EVT-015","SYSTEM", "OTP Manager Generation Request"),//not applicable for release v1
    OTP_MANAGER_VALIDATE("PWP-EVT-020","SYSTEM", "OTP Manager Validation Request"),//not applicable for release v1

    UPDATE_DEMO_REQUSET("PWP-EVT-016","SYSTEM", "Update Demographic details Request"),//not applicable for release v1
    REPORT_STOLEN_CARD("PWP-EVT-017","SYSTEM", "Report Stolen Card Requset"),//not applicable for release v1
    EMAIL_NOTIFIER("PWP-EVT-018","SYSTEM", "Email notifier Requset"),//not applicable for release v1
    SMS_NOTIFIER("PWP-EVT-019","SYSTEM", "SMS notifier Requset");//not applicable for release v1

    /** The event id. */
    private final String eventId;

    /** The event type. */
    private final String eventType;

    /** The event name. */
    private final String eventName;

    /**
     * Instantiates a new audit events.
     *
     * @param eventId the event id
     * @param eventType the event type
     * @param eventName the event name
     */
    private AuditEvents(String eventId, String eventType, String eventName) {
        this.eventId = eventId;
        this.eventType = eventType;
        this.eventName=eventName;
    }

    /**
     * Gets the event id.
     *
     * @return the event id
     */
    public String getEventId() {
        return eventId;
    }

    /**
     * Gets the event type.
     *
     * @return the event type
     */
    public String getEventType() {
        return eventType;
    }

    /**
     * Gets the event name.
     *
     * @return the event name
     */
    public String getEventName() {
        return this.eventName;
    }
}
