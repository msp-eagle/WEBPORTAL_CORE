package com.msp.webportal.businesslogic.commonUtil;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static Map<String, HttpSession> activeSessions = new HashMap<>();

    public static void invalidateExistingSession(String pcn) {
        if (activeSessions.containsKey(pcn)) {
            HttpSession oldSession = activeSessions.get(pcn);
            System.out.println("Invalidating existing session for PCN: " + pcn);
            oldSession.invalidate(); // Invalidate the old session
            activeSessions.remove(pcn);
        }
    }

    public static void registerNewSession(String pcn, HttpSession newSession) {
        invalidateExistingSession(pcn); // Remove old session if exists
        activeSessions.put(pcn, newSession); // Store new session
    }

    public static boolean isSessionActive(String pcn) {
        return activeSessions.containsKey(pcn);
    }
}
