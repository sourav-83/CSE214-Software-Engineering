package io;

/*

Concrete factory for HTML reports.
 
*/

public class HtmlReportWriter extends ReportWriter {
    
    @Override
    public ReportFormatter createFormatter() {
        return new HtmlReportFormatter();
    }

    @Override
    public String getSuccessMessage(String filePath) {
        return "HTML report written to: " + filePath;
    }
}