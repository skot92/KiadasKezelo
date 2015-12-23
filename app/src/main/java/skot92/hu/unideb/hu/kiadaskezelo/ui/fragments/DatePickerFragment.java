package skot92.hu.unideb.hu.kiadaskezelo.ui.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.widget.Button;
import android.widget.DatePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import skot92.hu.unideb.hu.kiadaskezelo.R;

/**
 * Created by skot9 on 2015. 12. 23..
 */
public class DatePickerFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    private String formattedDate;
    private int viewId;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
// Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

// Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        Calendar c = Calendar.getInstance();
        c.set(year, month, day);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        formattedDate = sdf.format(c.getTime());
        Button activityButton = (Button)getActivity().findViewById(viewId);
        activityButton.setText (formattedDate);
        Log.d("asd",formattedDate);
    }

    public void setViewId(int id){
        this.viewId = id;
    }




}