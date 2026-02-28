package io;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/*

HTML formatter - concrete product for HTML reports.
 
 */

public class HtmlReportFormatter implements ReportFormatter {
    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter monthFormatter;

    public HtmlReportFormatter() {
        this.dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.monthFormatter = DateTimeFormatter.ofPattern("yyyy-MM");
    }

    @Override
    public String formatDate(LocalDate date) {
        return date.format(dateFormatter);
    }

    @Override
    public String formatMonth(YearMonth month) {
        return month.format(monthFormatter);
    }

    @Override
    public String formatAmount(double amount) {
        return String.format("%.2f", amount);
    }

    @Override
    public String formatHeader() {
        return "<!DOCTYPE html>\n" +
               "<html>\n<head>\n" +
               "<title>BudgetBuddy Expense Report</title>\n" +
               "<style>\n" +
               "body { font-family: Arial, sans-serif; margin: 20px; }\n" +
               "h1 { color: #333; }\n" +
               "table { border-collapse: collapse; width: 100%; margin-bottom: 20px; }\n" +
               "th, td { border: 1px solid #ddd; padding: 8px; text-align: left; }\n" +
               "th { background-color: #4CAF50; color: white; }\n" +
               ".bar { background-color: #4CAF50; height: 20px; display: inline-block; }\n" +
               ".total { font-weight: bold; font-size: 1.2em; color: #4CAF50; }\n" +
               "</style>\n" +
               "</head>\n<body>\n" +
               "<h1>BudgetBuddy Expense Report</h1>\n";
    }

    @Override
    public String formatMonthlySummaryHeader() {
        return "<h2>Monthly Summary</h2>\n<table>\n" +
               "<tr><th>Month</th><th>Total Amount</th></tr>\n";
    }

    @Override
    public String formatMonthlySummaryRow(YearMonth month, double amount) {
        return String.format("<tr><td>%s</td><td>%s</td></tr>\n",
                formatMonth(month), formatAmount(amount));
    }

    @Override
    public String formatMonthlySummaryFooter() {
        return "</table>\n";
    }

    @Override
    public String formatCategoryBreakdownHeader() {
        return "<h2>Category Breakdown (All Time)</h2>\n<table>\n" +
               "<tr><th>Category</th><th>Total Amount</th><th>Visual</th></tr>\n";
    }

    @Override
    public String formatCategoryRow(String category, double amount, double maxAmount) {
        int barWidth = (int) Math.round((amount * 200) / maxAmount);
        String barHtml = String.format("<div class=\"bar\" style=\"width: %dpx;\"></div>", barWidth);
        return String.format("<tr><td>%s</td><td>%s</td><td>%s</td></tr>\n",
                category, formatAmount(amount), barHtml);
    }

    @Override
    public String formatCategoryBreakdownFooter() {
        return "</table>\n";
    }

    @Override
    public String formatGrandTotal(double total) {
        return String.format("<p class=\"total\">Grand Total: %s</p>\n", formatAmount(total));
    }

    @Override
    public String formatRecentEntriesHeader() {
        return "<h2>Recent Entries (Last 10)</h2>\n<table>\n" +
               "<tr><th>Date</th><th>Category</th><th>Amount</th><th>Notes</th></tr>\n";
    }

    @Override
    public String formatRecentEntry(LocalDate date, String category, double amount, String notes) {
        return String.format("<tr><td>%s</td><td>%s</td><td>%s</td><td>%s</td></tr>\n",
                formatDate(date), category, formatAmount(amount), notes);
    }

    @Override
    public String formatRecentEntriesFooter() {
        return "</table>\n";
    }

    @Override
    public String formatFooter() {
        return "</body>\n</html>\n";
    }
}