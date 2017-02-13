package masaibar.example.com.multipleselectablelistviewsample;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class ItemSelectablePackageLayout extends LinearLayout{

    private View mRootView;
    private ImageView mIcon;
    private TextView mLabel;
    private CheckBox mCheckBox;

    public ItemSelectablePackageLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mRootView = findViewById(R.id.item_root);
        mIcon = (ImageView) findViewById(R.id.image_app_icon);
        mLabel = (TextView) findViewById(R.id.text_app_label);
        mCheckBox = (CheckBox) findViewById(R.id.checkbox_app);
    }

    public void bindView(Context context, final NotificationUnifierTargetApp app) {
        mRootView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                mCheckBox.setChecked(!mCheckBox.isChecked());
            }
        });
        mIcon.setImageDrawable(getIcon(context, app.getPackageName()));
        mLabel.setText(getLabel(context, app.getPackageName()));
        mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                app.setChecked(b);
            }
        });
        mCheckBox.setChecked(app.isChecked());
    }

    @Nullable
    private String getLabel(Context context, String packageName) {
        try {
            PackageManager pm = getPackageManager(context);
            ApplicationInfo info = pm.getApplicationInfo(packageName, 0);

            CharSequence c = info.loadLabel(pm);

            if (c == null) {
                return null;
            }

            String label = c.toString();
            return TextUtils.isEmpty(label) ? null : label;

        } catch (PackageManager.NameNotFoundException e) {
            return null;
        }
    }

    @Nullable
    private Drawable getIcon(Context context, String packageName) {
        Drawable icon = null;
        try {
            icon = getPackageManager(context).getApplicationIcon(packageName);
        } catch (PackageManager.NameNotFoundException | OutOfMemoryError e) {
        }

        return icon;
    }

    private static PackageManager getPackageManager(Context context) {
        return context.getPackageManager();
    }
}
