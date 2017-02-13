package masaibar.example.com.multipleselectablelistviewsample;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class PackageAdapter extends ArrayAdapter<NotificationUnifierTargetApp> {

    private LayoutInflater mInflater;

    public PackageAdapter(Context context, List<NotificationUnifierTargetApp> apps) {
        super(context, 0, apps);
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ItemSelectablePackageLayout view;

        if (convertView == null) {
            view = (ItemSelectablePackageLayout) mInflater.inflate(R.layout.list_item_selectable_package, null);
        } else {
            view = (ItemSelectablePackageLayout) convertView;
        }

        view.bindView(getContext(), getItem(position));
        return view;
    }

    public List<NotificationUnifierTargetApp> getCheckedList() {
        List<NotificationUnifierTargetApp> result = new ArrayList<>();
        for (int i = 0; i < getCount(); i++) {
            NotificationUnifierTargetApp item = getItem(i);
            if (item.isChecked()) {
                result.add(item);
            }
        }

        return result;
    }
}

