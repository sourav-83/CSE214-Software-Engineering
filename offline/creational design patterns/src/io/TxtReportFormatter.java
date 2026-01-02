package io;

import util.TextUtils;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

/*

Plain-text formatter - concrete product for text reports.
 
*/

public class TxtReportFormatter implements ReportFormatter {
    private final DateTimeFormatter dateFormatter;
    private final DateTimeFormatter monthFormatter;

    public TxtReportFormatter() {
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
        return "=====================================\n" +
               "       BUDGETBUDDY EXPENSE REPORT    \n" +
               "=====================================\n\n";
    }

    @Override
    public String formatMonthlySummaryHeader() {
        return "MONTHLY SUMMARY\n" + TextUtils.separator(60) + "\n";
    }

    @Override
    public String formatMonthlySummaryRow(YearMonth month, double amount) {
        return String.format("%-10s : %12s\n", formatMonth(month), formatAmount(amount));
    }

    @Override
    public String formatMonthlySummaryFooter() {
        return "\n";
    }

    @Override
    public String formatCategoryBreakdownHeader() {
        return "CATEGORY BREAKDOWN (All Time)\n" + TextUtils.separator(60) + "\n";
    }

    @Override
    public String formatCategoryRow(String category, double amount, double maxAmount) {
        String bar = TextUtils.createBar(amount, maxAmount, 30);
        return String.format("%-15s %12s  %s\n", category, formatAmount(amount), bar);
    }

    @Override
    public String formatCategoryBreakdownFooter() {
        return "\n";
    }

    @Override
    public String formatGrandTotal(double total) {
        return TextUtils.separator(60) + "\n" +
               String.format("GRAND TOTAL: %s\n", formatAmount(total)) +
               TextUtils.separator(60) + "\n";
    }

    @Override
    public String formatRecentEntriesHeader() {
        return "\nRECENT ENTRIES (Last 10)\n" + TextUtils.separator(60) + "\n";
    }

    @Override
    public String formatRecentEntry(LocalDate date, String category, double amount, String notes) {
        return String.format("%s  %-12s %10s  %s\n",
                formatDate(date), category, formatAmount(amount), notes);
    }

    @Override
    public String formatRecentEntriesFooter() {
        return "";
    }

    @Override
    public String formatFooter() {
        return "";
    }
}
