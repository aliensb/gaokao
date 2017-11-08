package win.ccav.model;

import java.util.Calendar;

/**
 * Created by john on 2017/10/28.
 */
public class UserLog {
    private User user;
    private Calendar endTime;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }
}
