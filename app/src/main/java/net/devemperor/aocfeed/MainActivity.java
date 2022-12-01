package net.devemperor.aocfeed;

import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.koushikdutta.ion.Ion;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import java.util.stream.Collectors;

public class MainActivity extends Activity {


    String url = "";
    String sessionCookie = "";
    String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ProgressBar pb = findViewById(R.id.progressBar);

        Ion.with(this)
                .load(url)
                .addHeader("Cookie", "session=" + sessionCookie)
                .addHeader("User-Agent", "https://github.com/DevEmperor/AoCFeed")
                .asJsonObject()
                .setCallback((exception, result) -> {

                    Set<String> ids = result.getAsJsonObject("members").keySet();
                    List<Integer> scores = new ArrayList<>();
                    int myScore = -1;
                    for (String id : ids) {
                        int score = result.getAsJsonObject("members").getAsJsonObject(id).get("local_score").getAsInt();
                        scores.add(score);
                        if (id.equals(userId)) {
                            myScore = score;
                        }
                    }
                    List<Integer> sortedScores = new ArrayList<>(scores).stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

                    int myIndex = sortedScores.indexOf(myScore) + 1;
                    int players = ids.size();
                    int percent = Math.round(myIndex / (float) ids.size() * 100);

                    runOnUiThread(() -> {
                        TextView tvPlace = findViewById(R.id.tvPlace);
                        TextView tvPercentage = findViewById(R.id.tvPercentage);

                        pb.setVisibility(ProgressBar.INVISIBLE);
                        tvPlace.setText(Html.fromHtml("<font color=#cccccc>Place:<br></font><font color=#faed5d>" + myIndex + " </font> <font color=#cccccc>/</font> <font color=#078700>" + players + "</font>", Html.FROM_HTML_MODE_LEGACY));
                        tvPercentage.setText(String.format(Locale.GERMANY, "Top %d %%", percent));
                        tvPercentage.setText(Html.fromHtml("<font color=#cccccc>Top</font> <font color=#faed5d>" + percent + " </font> <font color=#cccccc>%</font>", Html.FROM_HTML_MODE_LEGACY));
                    });
                });

    }

}