package win.ccav.model;

import org.springframework.context.annotation.Bean;

/**
 * Created by paul on 2017/4/17.
 */

public class LogInfo {
    private boolean result;
    private String msg;

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
