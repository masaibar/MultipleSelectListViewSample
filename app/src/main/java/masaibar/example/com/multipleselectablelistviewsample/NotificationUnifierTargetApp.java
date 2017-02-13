package masaibar.example.com.multipleselectablelistviewsample;

/**
 * Created by masaibar on 2017/02/13.
 */

public class NotificationUnifierTargetApp {
    private String mPackageName;
    private boolean mIsChecked;

    public NotificationUnifierTargetApp(String packageName) {
        mPackageName = packageName;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public boolean isChecked() {
        return mIsChecked;
    }

    public void setChecked(boolean checked) {
        mIsChecked = checked;
    }
}
