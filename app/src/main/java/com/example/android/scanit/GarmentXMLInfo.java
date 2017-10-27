package com.example.android.scanit;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by Simen on 2017-10-25.
 */

public class GarmentXMLInfo {


    private String madein;
    private String workconditions;
    private String materials;
    private String co2;
    private String sustainometer;

    public GarmentXMLInfo(String madein, String workconditions, String materials, String co2, String sustainometer) {
        this.madein = madein;
        this.workconditions = workconditions;
        this.materials = materials;
        this.co2 = co2;
        this.sustainometer = sustainometer;
    }

    /**
     * Retrieves garment data from garments.xml
     * and creates a new GarmentXMLInfo object from values
     * @param garmentid id of the garment to retrieve from garments.xml
     * @param con context
     * @return GarmentXMLInfo object containing the fetched values
     */
    public static GarmentXMLInfo getGarmentXMLInfo(int garmentid, Context con) {
        Resources res = con.getResources();
        String garmentName = "garment" + Integer.toString(garmentid);
        int identifier = res.getIdentifier(garmentName, "array", "com.example.android.scanit");
        String[] garmentInfo = res.getStringArray(identifier);
        return new GarmentXMLInfo(garmentInfo[0], garmentInfo[1], garmentInfo[2], garmentInfo[3], garmentInfo[4]);
    }

    public String getMadein() {
        return madein;
    }

    public String getWorkconditions() {
        return workconditions;
    }

    public String getMaterials() {
        return materials;
    }

    public String getCo2() { return co2; }

    public String getSustainometer() { return sustainometer; }
}
