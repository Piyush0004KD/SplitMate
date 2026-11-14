package com.example.splitmate.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.splitmate.R;

public abstract class BaseScreenActivity extends AppCompatActivity {
    protected abstract int layoutId();
    protected abstract String screenTitle();
    protected abstract String sampleContent();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutId());
        ((TextView) findViewById(R.id.screenTitle)).setText(screenTitle());
        ((TextView) findViewById(R.id.sampleContent)).setText(sampleContent());
        LinearLayout actions = findViewById(R.id.screenActions);
        Class<?>[] destinations = {DashboardActivity.class, CreateGroupActivity.class, GroupDetailActivity.class,
                AddExpenseActivity.class, SettlementActivity.class, HistoryActivity.class,
                AnalyticsActivity.class, ProfileActivity.class};
        String[] labels = {"Dashboard", "Create Group", "Group Details", "Add Expense", "Settlement", "Expense History", "Analytics", "Profile"};
        for (int index = 0; index < destinations.length; index++) {
            if (destinations[index] == getClass()) continue;
            final Class<?> destination = destinations[index];
            Button button = new Button(this);
            button.setText(labels[index]);
            button.setAllCaps(false);
            button.setOnClickListener(view -> startActivity(new Intent(this, destination)));
            actions.addView(button);
        }
        Button back = new Button(this);
        back.setText("← Back to login");
        back.setAllCaps(false);
        back.setOnClickListener(view -> startActivity(new Intent(this, com.example.splitmate.MainActivity.class)));
        actions.addView(back, 0);
    }
}
