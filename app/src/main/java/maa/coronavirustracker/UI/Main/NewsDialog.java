package maa.coronavirustracker.UI.Main;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;

import maa.coronavirustracker.Adapter.NewsAdapter;
import maa.coronavirustracker.Models.NewsData;
import maa.coronavirustracker.R;

public class NewsDialog {
    private Activity activity;
    private AlertDialog dialog;
    TextView todaysdate;
    public static RecyclerView newscycles;
    TextView closedialog;
    public static RelativeLayout NewsNoData;
    public static ProgressBar NewBar;

    public NewsDialog(Activity myActivity) {
        activity = myActivity;

    }

    public void getNews() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.news, null));
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();


        NewsNoData = dialog.findViewById(R.id.animationHelp);
        NewBar = dialog.findViewById(R.id.NewsLoading);
        todaysdate = dialog.findViewById(R.id.dateToday);
        closedialog = dialog.findViewById(R.id.Close);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        String TodayDate = dateFormat.format(System.currentTimeMillis());
        String month = TodayDate.substring(3);
        TodayDate = TodayDate.substring(0, 2);
        if (month.equals("01")) {
            todaysdate.setText(TodayDate + " January");
        }
        if (month.equals("02")) {
            todaysdate.setText(TodayDate + " February");
        }
        if (month.equals("03")) {
            todaysdate.setText(TodayDate + " March");
        }
        if (month.equals("04")) {
            todaysdate.setText(TodayDate + " April");
        }
        if (month.equals("05")) {
            todaysdate.setText(TodayDate + " May");
        }
        if (month.equals("06")) {
            todaysdate.setText(TodayDate + " June");
        }
        if (month.equals("07")) {
            todaysdate.setText(TodayDate + " July");
        }
        if (month.equals("08")) {
            todaysdate.setText(TodayDate + " August");
        }
        if (month.equals("09")) {
            todaysdate.setText(TodayDate + " September");
        }
        if (month.equals("10")) {
            todaysdate.setText(TodayDate + " October");
        }
        if (month.equals("11")) {
            todaysdate.setText(TodayDate + " November");
        }
        if (month.equals("12")) {
            todaysdate.setText(TodayDate + " December");
        }


        newscycles = dialog.findViewById(R.id.NewsCycle);
        NewsData data = new NewsData(activity);
        data.execute();

        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissTheDialog();
            }
        });
    }

    public void dismissTheDialog() {
        dialog.dismiss();
    }

    public void getWorldNews() {

        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        builder.setView(inflater.inflate(R.layout.news, null));
        builder.setCancelable(false);
        dialog = builder.create();
        dialog.show();

        NewsNoData = dialog.findViewById(R.id.animationHelp);
        NewBar = dialog.findViewById(R.id.NewsLoading);
        todaysdate = dialog.findViewById(R.id.dateToday);
        closedialog = dialog.findViewById(R.id.Close);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM");
        String TodayDate = dateFormat.format(System.currentTimeMillis());
        String month = TodayDate.substring(3);
        TodayDate = TodayDate.substring(0, 2);
        if (month.equals("01")) {
            todaysdate.setText(TodayDate + " January");
        }
        if (month.equals("02")) {
            todaysdate.setText(TodayDate + " February");
        }
        if (month.equals("03")) {
            todaysdate.setText(TodayDate + " March");
        }
        if (month.equals("04")) {
            todaysdate.setText(TodayDate + " April");
        }
        if (month.equals("05")) {
            todaysdate.setText(TodayDate + " May");
        }
        if (month.equals("06")) {
            todaysdate.setText(TodayDate + " June");
        }
        if (month.equals("07")) {
            todaysdate.setText(TodayDate + " July");
        }
        if (month.equals("08")) {
            todaysdate.setText(TodayDate + " August");
        }
        if (month.equals("09")) {
            todaysdate.setText(TodayDate + " September");
        }
        if (month.equals("10")) {
            todaysdate.setText(TodayDate + " October");
        }
        if (month.equals("11")) {
            todaysdate.setText(TodayDate + " November");
        }
        if (month.equals("12")) {
            todaysdate.setText(TodayDate + " December");
        }


        newscycles = dialog.findViewById(R.id.NewsCycle);

        closedialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismissTheDialog();
            }
        });

        NewBar.setVisibility(View.GONE);
        NewsNoData.setVisibility(View.VISIBLE);

    }


}



