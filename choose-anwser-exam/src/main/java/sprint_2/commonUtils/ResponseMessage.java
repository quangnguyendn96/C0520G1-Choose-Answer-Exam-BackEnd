package sprint_2.commonUtils;

/**
 * ResponseMessage
 *
 * Version 1.0
 *
 * Date: 08-12-2020
 *
 * Copyright
 *
 * Modification Logs:
 * DATE                 AUTHOR          DESCRIPTION
 * -----------------------------------------------------------------------
 * 08-12-2020         TungTS            Response Message
 */

public class ResponseMessage {
    private String message;

    public ResponseMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
