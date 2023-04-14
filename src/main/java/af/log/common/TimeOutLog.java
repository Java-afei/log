package af.log.common;

import lombok.Data;

@Data
public class TimeOutLog {

    private long id;

    private String methodName;

    private String date;

    private long time;

    private long fixTime;
}
