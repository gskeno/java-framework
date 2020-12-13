package com.gson.logback.filter;

import org.slf4j.*;

public class DuplicateMessageUse {
    private static final Logger logger = LoggerFactory.getLogger(DuplicateMessageUse.class);

    //java -Dlogback.configurationFile=duplicateMessage.xml DuplicateMessageUse
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            if (i == 3) {
                MDC.put("username", "sebastien");
                logger.debug("logging statement {}", i);
                MDC.remove("username");
            } else if (i == 6) {
                Marker billing = MarkerFactory.getMarker("billing");
                logger.error(billing, "billing statement {}", i);
            } else {
                logger.info("logging statement {}", i);
            }
        }
    }
}
