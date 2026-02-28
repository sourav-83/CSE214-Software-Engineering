package io;

import model.Expense;
import service.ExpenseRepository;
import service.Summarizer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

/*

Abstract creator (parent factory) that defines common report writing logic.
Subclasses implement the factory method to create specific formatters.
 
*/

public abstract class ReportWriter {
    
    
    public abstract ReportFormatter createFormatter();

    
    public void writeReport(String filePath, ExpenseRepository repository) throws IOException {
        List<Expense> allExpenses = repository.findAll();
        Summarizer summarizer = new Summarizer(allExpenses);
        
        ReportFormatter formatter = createFormatter();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            writer.write(formatter.formatHeader());
            
            writeMonthlySummary(writer, summarizer, formatter);
            
            writeCategoryBreakdown(writer, summarizer, formatter);
            
            writer.write(formatter.formatGrandTotal(summarizer.grandTotal()));
            
            writeRecentEntries(writer, allExpenses, formatter);
            
            writer.write(formatter.formatFooter());
        }

        System.out.println(getSuccessMessage(filePath));
    }

    
    private void writeMonthlySummary(BufferedWriter writer, Summarizer summarizer, 
                                     ReportFormatter formatter) throws IOException {
        writer.write(formatter.formatMonthlySummaryHeader());

        Map<YearMonth, Double> monthlyTotals = summarizer.monthlyTotals();
        for (Map.Entry<YearMonth, Double> entry : monthlyTotals.entrySet()) {
            writer.write(formatter.formatMonthlySummaryRow(entry.getKey(), entry.getValue()));
        }
        
        writer.write(formatter.formatMonthlySummaryFooter());
    }

    
    private void writeCategoryBreakdown(BufferedWriter writer, Summarizer summarizer,
                                        ReportFormatter formatter) throws IOException {
        writer.write(formatter.formatCategoryBreakdownHeader());

        Map<String, Double> categoryTotals = summarizer.categoryTotals(null);
        double maxAmount = categoryTotals.values().stream()
                .max(Double::compareTo)
                .orElse(1.0);

        for (Map.Entry<String, Double> entry : categoryTotals.entrySet()) {
            writer.write(formatter.formatCategoryRow(
                    entry.getKey(), entry.getValue(), maxAmount));
        }
        
        writer.write(formatter.formatCategoryBreakdownFooter());
    }

    
    private void writeRecentEntries(BufferedWriter writer, List<Expense> expenses,
                                     ReportFormatter formatter) throws IOException {
        writer.write(formatter.formatRecentEntriesHeader());

        int count = 0;
        for (int i = expenses.size() - 1; i >= 0 && count < 10; i--, count++) {
            Expense exp = expenses.get(i);
            writer.write(formatter.formatRecentEntry(
                    exp.getDate(), exp.getCategory(), exp.getAmount(), exp.getNotes()));
        }
        
        writer.write(formatter.formatRecentEntriesFooter());
    }

    
    public abstract String getSuccessMessage(String filePath);
}