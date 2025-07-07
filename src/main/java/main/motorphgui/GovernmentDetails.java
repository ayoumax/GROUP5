/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.motorphgui;

/**
 *
 * @author WINDOWS 10
 */
public class GovernmentDetails {
    private String sssNumber;
    private String philHealthNumber;
    private String tin;
    private String pagIbigNumber;
    private String govDetails;
    private String witholdingTax;
    private double governmentDeduction;

    //Constructor
    public GovernmentDetails(String sssNumber, String philHealthNumber,String tin, String pagIbigNumber) {
        this.sssNumber = sssNumber;
        this.philHealthNumber = philHealthNumber;
        this.tin = tin;
        this.pagIbigNumber = pagIbigNumber;
    }
    
    //Constructor
    public double calculateGovernmentDeducatiteGovermentDeductionon (double calculatePagibig, double calculatePhilhealth, double calculateSSS) {
        this.governmentDeduction = governmentDeduction;
        governmentDeduction = calculatePagibig + calculatePhilhealth + calculateSSS;
        return governmentDeduction;
    }
    
    //Methods
    public static double calculateWithholdingTax(double taxableIncome) {
        if (taxableIncome <= 20832) {
            return 0;
        } else if (taxableIncome <= 33333) {
            return (taxableIncome - 20833) * 0.20;
        } else if (taxableIncome <= 666667) {
            return 2500 + (taxableIncome - 33333) * 0.25;
        } else if (taxableIncome <= 166667) {
            return 10833 + (taxableIncome - 666667) * 0.30;
        } else if (taxableIncome <= 666667) {
            return 40833.33 + (taxableIncome - 166667) * 0.32;
        } else {
            return 200833.33 + (taxableIncome - 666667) * 0.35;
        }
    }

    public static double calculatePagibig(double grossPay) {
        double rate = (grossPay > 1500) ? 0.02 : 0.01;
        return Math.min(grossPay * rate, 100); // Maximum of 100
    }

    public static double calculatePhilhealth(double grossPay) {
        double rate = 0.03;
        double contribution = grossPay * rate;
        return Math.min(contribution / 2, 900); // Employee pays half, max of 900
    }

    public static double calculateSSS(double grossPay) {
        if (grossPay < 3250) {
            return 135.0;
        } else if (grossPay <= 3749.99) {
            return 157.5;
        } else if (grossPay <= 4249.99) {
            return 180.0;
        } else if (grossPay <= 4749.99) {
            return 202.5;
        } else if (grossPay <= 5249.99) {
            return 225.0;
        } else if (grossPay <= 5749.99) {
            return 247.5;
        } else if (grossPay <= 6249.99) {
            return 270.0;
        } else if (grossPay <= 6749.99) {
            return 292.5;
        } else if (grossPay <= 7249.99) {
            return 315.0;
        } else if (grossPay <= 7749.99) {
            return 337.5;
        } else if (grossPay <= 8249.99) {
            return 360.0;
        } else if (grossPay <= 8749.99) {
            return 382.5;
        } else if (grossPay <= 9249.99) {
            return 405.0;
        } else if (grossPay <= 9749.99) {
            return 427.5;
        } else if (grossPay <= 10249.99) {
            return 450.0;
        } else if (grossPay <= 10749.99) {
            return 472.5;
        } else if (grossPay <= 11249.99) {
            return 495.0;
        } else if (grossPay <= 11749.99) {
            return 517.5;
        } else if (grossPay <= 12249.99) {
            return 540.0;
        } else if (grossPay <= 12749.99) {
            return 562.5;
        } else if (grossPay <= 13249.99) {
            return 585.0;
        } else if (grossPay <= 13749.99) {
            return 607.5;
        } else if (grossPay <= 14249.99) {
            return 630.0;
        } else if (grossPay <= 14749.99) {
            return 652.5;
        } else if (grossPay <= 15249.99) {
            return 675.0;
        } else if (grossPay <= 15749.99) {
            return 697.5;
        } else if (grossPay <= 16249.99) {
            return 720.0;
        } else if (grossPay <= 16749.99) {
            return 742.5;
        } else if (grossPay <= 17249.99) {
            return 765.0;
        } else if (grossPay <= 17749.99) {
            return 787.5;
        } else if (grossPay <= 18249.99) {
            return 810.0;
        } else if (grossPay <= 18749.99) {
            return 832.5;
        } else if (grossPay <= 19249.99) {
            return 855.0;
        } else if (grossPay <= 19749.99) {
            return 877.5;
        } else if (grossPay <= 20249.99) {
            return 900.0;
        } else if (grossPay <= 20749.99) {
            return 922.5;
        } else if (grossPay <= 21249.99) {
            return 945.0;
        } else if (grossPay <= 21749.99) {
            return 967.5;
        } else if (grossPay <= 22249.99) {
            return 990.0;
        } else if (grossPay <= 22749.99) {
            return 1012.5;
        } else if (grossPay <= 23249.99) {
            return 1035.0;
        } else if (grossPay <= 23749.99) {
            return 1057.5;
        } else if (grossPay <= 24249.99) {
            return 1080.0;
        } else if (grossPay <= 24749.99) {
            return 1102.5;
        } else {
            return 1125.0; // For gross pay of 24750 and above
        }
    }
    
    //Getters
    public String getSssNumber() {
        return sssNumber;
    }

    public String getPhilHealthNumber() {
        return philHealthNumber;
    }

    public String getTin() {
        return tin;
    }

    public String getPagIbigNumber() {
        return pagIbigNumber;
    }

    // Optional setters if you want to allow editing
    public void setSssNumber(String sssNumber) {
        this.sssNumber = sssNumber;
    }

    public void setPhilHealthNumber(String philHealthNumber) {
        this.philHealthNumber = philHealthNumber;
    }

    public void setTin(String tin) {
        this.tin = tin;
    }

    public void setPagIbigNumber(String pagIbigNumber) {
        this.pagIbigNumber = pagIbigNumber;
    }
}

    
