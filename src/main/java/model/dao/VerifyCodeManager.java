package model.dao;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

public class VerifyCodeManager {
    private static final Map<String, String> codeMap = new HashMap<>();
    private static final Map<String, Timer> timerMap = new HashMap<>();
    private static final long CODE_EXPIRATION_TIME = 10 * 60 * 1000;
    
    public static void generateCode(String email, String code) {
        
        // 기존 타이머 제거
        if(timerMap.containsKey(email)) {
            timerMap.get(email).cancel();
            timerMap.remove(email);
        }
        
        // 인증코드 저장 및 
        codeMap.put(email, code);
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                codeMap.remove(email);
                timerMap.remove(email);
            }
        }, CODE_EXPIRATION_TIME);
        
        timerMap.put(email, timer);
    }
    
    public static boolean verifyCode(String email, String code) {
        return codeMap.containsKey(email) && codeMap.get(email).equals(code);
    }
    
    public static void invalidateCode(String email) {
        codeMap.remove(email);
        if(timerMap.containsKey(email)) {
            timerMap.get(email).cancel();
            timerMap.remove(email);
        }
    }
    
    public static String getStoredCode(String email) {
        return codeMap.get(email);
    }
}
