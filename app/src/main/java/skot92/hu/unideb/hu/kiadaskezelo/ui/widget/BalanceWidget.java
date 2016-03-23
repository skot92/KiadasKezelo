package skot92.hu.unideb.hu.kiadaskezelo.ui.widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import skot92.hu.unideb.hu.kiadaskezelo.R;
import skot92.hu.unideb.hu.kiadaskezelo.service.BalanceService;

/**
 * Created by skot9 on 2015. 11. 22..
 */


public class BalanceWidget extends AppWidgetProvider {

    private BalanceService balanceService;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager,
                         int[] appWidgetIds) {
        // ID-k lekérdezése
        ComponentName thisWidget = new ComponentName(context,
                BalanceWidget.class);
        int[] allWidgetIds = appWidgetManager.getAppWidgetIds(thisWidget);
        for (int widgetId : allWidgetIds) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(),
                    R.layout.balance_widget);
            // Szöveg beállítása
            balanceService = new BalanceService(context);
            String balance = String.valueOf(balanceService.findBalance());
            remoteViews.setTextViewText(R.id.tvStatus, balance );

            // Kattintás esemény kezelése, hatására frissül ismét a balance_widget
            Intent intent = new Intent(context, BalanceWidget.class);

            intent.setAction(AppWidgetManager.ACTION_APPWIDGET_UPDATE);
            intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS, appWidgetIds);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(context,
                    0, intent, PendingIntent.FLAG_UPDATE_CURRENT);

            remoteViews.setOnClickPendingIntent(R.id.tvStatus, pendingIntent);
            appWidgetManager.updateAppWidget(widgetId, remoteViews);
        }
    }

}
