package com.ctre.phoenix.motorcontrol;

/**
 * Stator-side current limiting.  This is typically used to limit acceleration/torque and heat generation.
 */
public class StatorCurrentLimitConfiguration{
    /**
     * True/False to enable/disable limit feature.
     */
    public boolean enable = false;
    /**
     * The "holding" current (amperes) to limit to when feature is activated.
     */
    public double currentLimit = 0;

    /**
     * Current must exceed this threshold (amperes) before limiting occurs.
     * If this value is less than currentLimit, then currentLimit is used as the threshold.
     */
    public double triggerThresholdCurrent = 0;
    /**
     * How long current must exceed threshold (seconds) before limiting occurs.
     */
    public double triggerThresholdTime = 0;

    public StatorCurrentLimitConfiguration(){}

    public StatorCurrentLimitConfiguration(boolean enable, double currentLimit, double triggerThresholdCurrent, double triggerThresholdTime){
        this.enable = enable;
        this.currentLimit = currentLimit;
        this.triggerThresholdCurrent = triggerThresholdCurrent;
        this.triggerThresholdTime = triggerThresholdTime;
    }

    public StatorCurrentLimitConfiguration(double[] doubleArray){
        deserialize(doubleArray);
    }

    /**
     * @return string representation of current faults tripped
     */
    public String toString(){
        String retstr = "";

        if(enable == false){
            retstr = "Limiting is disabled.";
        }
        else{
            /* If current limit is greater than triggerThresholdCurrent,
            * the device will use current-limit as the threshold.
            */
            double effectiveThresholdCurr = Math.max(currentLimit, triggerThresholdCurrent);
            retstr = "Current Limiting will activate if STATOR current exceeds " + effectiveThresholdCurr + " amps for " + triggerThresholdTime + " seconds." + "  Then current will hold at " + currentLimit + " amps";
        }
        return retstr;
    }

    public double[] toArray(){
        double[] retval = new double[4];
        retval[0] = ((double)(enable ? 1 : 0));
        retval[1] = (currentLimit);
        retval[2] = (triggerThresholdCurrent);
        retval[3] = (triggerThresholdTime);
        return retval;
    }

    public void deserialize(double[] doubles){
        int doubleCnt = doubles.length;

        if (doubleCnt <= 0) { return; }
        int i = 0;

        if (i < doubleCnt) {
            enable = (doubles[i++] > 0);
        }
        if (i < doubleCnt) {
            currentLimit = doubles[i++];
        }
        if (i < doubleCnt) {
            triggerThresholdCurrent = doubles[i++];
        }
        if (i < doubleCnt) {
            triggerThresholdTime = doubles[i++];
        }
    }
    public boolean equals(StatorCurrentLimitConfiguration rhs){
        boolean retval = true;
        retval &= (enable == rhs.enable);
        retval &= (currentLimit == rhs.currentLimit);
        retval &= (triggerThresholdCurrent == rhs.triggerThresholdCurrent);
        retval &= (triggerThresholdTime == rhs.triggerThresholdTime);
        return retval;
    }
}