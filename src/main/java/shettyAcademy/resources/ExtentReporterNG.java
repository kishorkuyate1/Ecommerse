package shettyAcademy.resources; 
// Package declaration for project structure

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
// Required imports for ExtentReports and the Spark HTML reporter

public class ExtentReporterNG {
    // Utility class to configure and provide a reusable ExtentReports object

    public static ExtentReports getReportObject() {
        // Static method so it can be accessed without creating an instance

        String path = System.getProperty("user.dir") + "//reports//index.html";
        // Defines the file path where the report will be generated under /reports folder

        ExtentSparkReporter reporter = new ExtentSparkReporter(path);
        // Creates a SparkReporter to generate the HTML report

        reporter.config().setReportName("Web Automation Report");
        // Sets the name displayed at the top of the report

        reporter.config().setDocumentTitle("Test Results");
        // Sets the title of the browser tab/report document

        ExtentReports extent = new ExtentReports();
        // Creates an ExtentReports object which manages all the tests and report generation

        extent.attachReporter(reporter);
        // Attaches the SparkReporter to ExtentReports

        extent.setSystemInfo("QA", "Kishor");
        // Adds custom info (like tester name, environment, etc.) to the report

        return extent;
        // Returns the fully configured ExtentReports object to be used in listeners or test classes
    }
}
