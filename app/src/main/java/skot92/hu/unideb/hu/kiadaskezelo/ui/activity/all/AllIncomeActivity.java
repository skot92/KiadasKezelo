package skot92.hu.unideb.hu.kiadaskezelo.ui.activity.all;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.core.helper.InComeTable;
import skot92.hu.unideb.hu.kiadaskezelo.service.InComeService;
import skot92.hu.unideb.hu.kiadaskezelo.ui.Adapter.AllIncomeAdapter;
import skot92.hu.unideb.hu.kiadaskezelo.ui.dialogs.AllIncomesSearchDialogAmount;
import skot92.hu.unideb.hu.kiadaskezelo.ui.dialogs.AllIncomesSearchDialogDate;
import skot92.hu.unideb.hu.kiadaskezelo.ui.dialogs.AllIncomesSearchDialogName;

public class AllIncomeActivity extends AppCompatActivity {

    public static ListView lv;
    private InComeService inComeService;


    public static ListView getLv() {
        return lv;
    }

    public static void setLv(ListView lv) {
        AllIncomeActivity.lv = lv;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_income_list_view);

        inComeService = new InComeService(getApplicationContext());

        lv = (ListView) findViewById(R.id.list_view);

        final AllIncomeAdapter adapter = new AllIncomeAdapter(this, inComeService.findInComes(""));
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(), "" + i, Toast.LENGTH_SHORT).show();
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.all_incomes, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        AlertDialog.Builder builder = null;
        switch (item.getItemId()) {
            case R.id.income_order_by:
                builder = new AlertDialog.Builder(AllIncomeActivity.this);
                builder.setTitle(getString(R.string.order_by));
                builder.setItems(new CharSequence[]
                                {getString(R.string.amount), getString(R.string.date), getString(R.string.name)},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                AllIncomeAdapter adapter;
                                switch (which) {
                                    case 0:
                                        adapter = new AllIncomeAdapter(AllIncomeActivity.this,
                                                inComeService.findInComes(InComeTable.IN_COME_AMOUNT));
                                        adapter.notifyDataSetChanged();
                                        lv.setAdapter(adapter);
                                        break;
                                    case 1:
                                        adapter = new AllIncomeAdapter(AllIncomeActivity.this,
                                                inComeService.findInComes(InComeTable.IN_COME_DATE));
                                        adapter.notifyDataSetChanged();
                                        lv.setAdapter(adapter);
                                        break;
                                    case 2:
                                        adapter = new AllIncomeAdapter(AllIncomeActivity.this,
                                                inComeService.findInComes(InComeTable.IN_COME_NAME));
                                        adapter.notifyDataSetChanged();
                                        lv.setAdapter(adapter);
                                        break;
                                }
                            }
                        });
                builder.create().show();
                return true;
            case R.id.income_where:
                builder = new AlertDialog.Builder(AllIncomeActivity.this);
                builder.setTitle(getString(R.string.search));
                builder.setItems(new CharSequence[]
                                {getString(R.string.amount), getString(R.string.date), getString(R.string.name)},
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which) {
                                    case 0:
                                        AllIncomesSearchDialogAmount cdd = new AllIncomesSearchDialogAmount(AllIncomeActivity.this);
                                        cdd.show();
                                        Log.d("asd","asd");
                                        break;
                                    case 1:
                                        AllIncomesSearchDialogDate cdd1 = new AllIncomesSearchDialogDate(AllIncomeActivity.this);
                                        cdd1.show();
                                        Log.d("asd","asd");
                                        break;
                                    case 2:
                                        AllIncomesSearchDialogName cdd2 = new AllIncomesSearchDialogName(AllIncomeActivity.this);
                                        cdd2.show();
                                        Log.d("asd","asd");
                                        break;
                                }
                            }
                        });
                builder.create().show();

                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
