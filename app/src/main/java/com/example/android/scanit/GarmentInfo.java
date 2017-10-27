package com.example.android.scanit;

import android.annotation.SuppressLint;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Arrays;

public class GarmentInfo extends AppCompatActivity {

    private final String GOOD_WORKCONDITIONS = "Good";
    private final String[] GOOD_MATERIALS = {"Modal", "Hemp", "Bamboo", "Cotton"};
    private final int GOOD_EMISSION_THRESHOLD = 3;
    private final int GOOD_SUSTAINOMETER_THRESHOLD = 50;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_garment_info);

        Bundle extras = getIntent().getExtras();
        int selection = extras.getInt("selection");
        renderGarmentInfo(selection);
    }

    /**
     * Renders info about garment in view
     * @param garmentid id of the garment to be rendered
     */
    private void renderGarmentInfo(int garmentid) {
        TextView tv_madein = (TextView) findViewById(R.id.result_madein),
            tv_conditions = (TextView) findViewById(R.id.result_workconditions),
            tv_co2 = (TextView) findViewById(R.id.result_CO2),
            tv_sustainometer = (TextView) findViewById(R.id.sustainometer);
        LinearLayout materialContainer = (LinearLayout) findViewById(R.id.materialsContainer);

        GarmentXMLInfo info = GarmentXMLInfo.getGarmentXMLInfo(garmentid, this);

        String madein = info.getMadein(),
                conditions = info.getWorkconditions(),
                materials = info.getMaterials(),
                co2 = info.getCo2(),
                sustainometer = info.getSustainometer();

        setConditionsColor(conditions);
        addMaterials(materials, materialContainer);
        setCO2Color(Integer.parseInt(co2));
        setSusmeterColor(Integer.parseInt(sustainometer));

        tv_madein.setText(madein);
        tv_conditions.setText(conditions);
        tv_co2.setText(co2);
        tv_sustainometer.setText(sustainometer + "%");
    }

    /**
     * checks if the color of the conditions result should be changed, and changes if necessary
     * @param conditions result of conditions from xml file
     */
    private void setConditionsColor(String conditions) {
        TextView tv_conditions = (TextView) findViewById(R.id.result_workconditions);
        if (conditions.equals(GOOD_WORKCONDITIONS)) {
            setGreen(tv_conditions);
        } else {
            setRed(tv_conditions);
        }
    }

    /**
     * Adds materials-info with proper formatting to given layout
     * @param materialString String of materials to be added, separated by ", "
     * @param targetLayout The target layout to add the materials to
     */
    private void addMaterials(String materialString, LinearLayout targetLayout) {
        String[] materials = materialString.split(", ");

        for (String material : materials) {
            TextView tv_material = createMaterialsTextView(material);
            targetLayout.addView(tv_material);
        }
    }

    /**
     * Creates textview containing a single material with proper formatting
     * @param material name of material to be added
     * @return textview with properly formatted material
     */
    @NonNull
    private TextView createMaterialsTextView(String material) {
        TextView tv_material = new TextView(this);
        tv_material.setText(material);
        tv_material.setTextSize(TypedValue.COMPLEX_UNIT_PX,
                getResources().getDimension(R.dimen.infoItemTextSize));

        if (Arrays.asList(GOOD_MATERIALS).contains(material)) {
            setGreen(tv_material);
        } else {
            setRed(tv_material);
        }

        return tv_material;
    }

    /**
     * checks if the color of the emissions result should be changed, and changes if necessary
     * @param emissionScore result of emissions from xml file
     */
    private void setCO2Color(int emissionScore) {
        TextView tv_emissions = (TextView) findViewById(R.id.result_CO2);
        if (emissionScore <= GOOD_EMISSION_THRESHOLD) {
            setGreen(tv_emissions);
        } else {
            setRed(tv_emissions);
        }
    }

    /**
     * checks if the color of the sustainometer should be changed, and changes if necessary
     * @param sustainoscore the score on the sustainometer for garment being checked
     */
    private void setSusmeterColor(int sustainoscore) {
        if (sustainoscore < GOOD_SUSTAINOMETER_THRESHOLD) {
            View susmterCircleRed = findViewById(R.id.sustainometerCircleRed);
            susmterCircleRed.setVisibility(View.VISIBLE);
        }
    }

    /**
     * Set textcolor of textview green
     * @param tv textview to set to green
     */
    private void setGreen(TextView tv) {
        tv.setTextColor(getResources().getColor(R.color.scanit_green));
    }

    /**
     * Set textcolor of textview red
     * @param tv textview to set to red
     */
    private void setRed(TextView tv) {
        tv.setTextColor(getResources().getColor(R.color.scanit_red));
    }

}
