package io;

import java.time.LocalDate;
import java.time.YearMonth;

/*

 Product interface that defines all operations for formatting and creating report content.
 
 */

public interface ReportFormatter {
    
    String formatDate(LocalDate date);
    String formatMonth(YearMonth month);
    String formatAmount(double amount);
    
    
    String formatHeader();
    String formatMonthlySummaryHeader();
    String formatMonthlySummaryRow(YearMonth month, double amount);
    String formatMonthlySummaryFooter();
    
    String formatCategoryBreakdownHeader();
    String formatCategoryRow(String category, double amount, double maxAmount);
    String formatCategoryBreakdownFooter();
    
    String formatGrandTotal(double total);
    
    String formatRecentEntriesHeader();
    String formatRecentEntry(LocalDate date, String category, double amount, String notes);
    String formatRecentEntriesFooter();
    
    String formatFooter();
}