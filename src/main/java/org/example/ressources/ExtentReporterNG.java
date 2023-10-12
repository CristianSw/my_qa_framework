package org.example.ressources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReporterNG {
    public static ExtentReports getReportObject() {
        String path = System.getProperty("user.dir") + "//reports//index.html";
        //ExtentReports, ExtentSparkReports
        ExtentSparkReporter report = new ExtentSparkReporter(path);
        report.config().setReportName("Web Automation Results");
        report.config().setDocumentTitle("Test Results");
        ExtentReports extentReports = new ExtentReports();
        extentReports.attachReporter(report);
        extentReports.setSystemInfo("Tester", "CD");
        return extentReports;
    }
}
