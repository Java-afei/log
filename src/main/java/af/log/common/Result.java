package af.log.common;

import lombok.Data;

@Data
public class Result {

    private int code;

    private String msg;

    private Object data;

    public Result() {
    }

    public Result(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Result success(String m, Object data){
        return new Result(200,m,data);
    }

    public static Result error(int c,String m){
        return new Result(c,m,null);
    }
}
