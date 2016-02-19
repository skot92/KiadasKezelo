package skot92.hu.unideb.hu.kiadaskezelo.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.entity.InComeEntity;

/**
 * Created by skot9 on 2016. 02. 19..
 */
public class AllIncomeAdapter extends ArrayAdapter<InComeEntity> {

    public AllIncomeAdapter(Context context, int resource) {
        super(context, resource);
    }

    public AllIncomeAdapter(Context context, List<InComeEntity> incomes) {
        super(context, 0, incomes);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        InComeEntity user = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_all_income, parent, false);
        }
        // Lookup view for data population
        TextView amount = (TextView) convertView.findViewById(R.id.incomeAmount);
        TextView date = (TextView) convertView.findViewById(R.id.incomeDate);
        TextView name = (TextView) convertView.findViewById(R.id.incomeName);
        // Populate the data into the template view using the data object
        amount.setText(String.valueOf(user.getAmount()));
        date.setText(user.getDate());
        name.setText(user.getName());
        // Return the completed view to render on screen
        return convertView;
    }
}
