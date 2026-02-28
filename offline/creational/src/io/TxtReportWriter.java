package io;

/*

Concrete factory for text reports.
 
*/

public class TxtReportWriter extends ReportWriter {
    
    @Override
    public ReportFormatter createFormatter() {
        return new TxtReportFormatter();
    }

    @Override
    public String getSuccessMessage(String filePath) {
        return "Text report written to: " + filePath;
    }
}