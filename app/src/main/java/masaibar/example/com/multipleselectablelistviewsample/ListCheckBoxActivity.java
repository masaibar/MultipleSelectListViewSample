package masaibar.example.com.multipleselectablelistviewsample;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ListCheckBoxActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent intent = new Intent(context, ListCheckBoxActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);
    }

    private ListView mListView;
    private PackageAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_check_box);

        setupListView();
        setupSwitch();
        setupGetButton();
    }

    private void setupListView() {
        mListView = (ListView) findViewById(R.id.list_packages);
        List<NotificationUnifierTargetApp> packages = getInstalledPackages(getApplicationContext());
        mAdapter = new PackageAdapter(getApplicationContext(), packages);
        mListView.setAdapter(mAdapter);
    }

    private void setupSwitch() {
        final TextView textMode = ((TextView) findViewById(R.id.text_mode));
        final Switch switchMode = ((Switch) findViewById(R.id.switch_select_mode));

        textMode.setText(switchMode.isChecked() ? "multiple" : "single");

        switchMode.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                textMode.setText(b ? "multiple" : "single");
            }
        });
    }

    private void setupGetButton() {
        findViewById(R.id.button_get).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<NotificationUnifierTargetApp> checked = mAdapter.getCheckedList();
                for (NotificationUnifierTargetApp app : checked) {
                    Log.d("!!!", String.format("package = %s", app.getPackageName()));
                }


            }
        });
    }

    private List<NotificationUnifierTargetApp> getInstalledPackages(Context context) {
        List<NotificationUnifierTargetApp> packages = new ArrayList<>();

        PackageManager manager = context.getPackageManager();
        final int flags = PackageManager.MATCH_UNINSTALLED_PACKAGES;
        List<ApplicationInfo> infos = manager.getInstalledApplications(flags);
        for (ApplicationInfo info : infos) {
            packages.add(new NotificationUnifierTargetApp(info.packageName));
        }

        return packages;
    }
}
