package org.gson.poem;

import java.util.ArrayList;
import java.util.List;

/**
 * 喜马拉雅山脉
 */
public class Himalaya {

    private List<Peak> peaks = new ArrayList<>();
    /**
     * 喜马拉雅山⛰️平均海拔在7000米
     */
    private int averageElevation = 7000;

    public int getAverageElevation() {
        return averageElevation;
    }

    /**
     * 给山脉增加诸多山峰
     * @param peak
     */
    public void addPeak(Peak peak){
        peaks.add(peak);
    }

    public List<Peak> getPeaks() {
        return peaks;
    }

    /**
     * 是否含有指定山峰
     * @param peak
     * @return
     */
    public boolean contains(Peak peak){
        return peaks.contains(peak);
    }
}
