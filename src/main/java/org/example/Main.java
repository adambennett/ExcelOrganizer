package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        TestData testData = dataCompareSummaryTest();
        List<List<LoanDataValuesDTO>> basicMultiRowData = groupSortMultiRowReportData(testData.basicReportData());
        List<List<LoanDataValuesDTO>> forcedMultiLoanFormatResult = addForcedMultiLoanColumnDataToRows(basicMultiRowData, testData.forcedMultiLoanReportData());
        for (var header : testData.headers()) {
            System.out.print(header.value() + " | ");
        }
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        int rowNum = 0;
        for (var row : forcedMultiLoanFormatResult) {
            for (var cell : row) {
                System.out.print(cell + " | ");
            }
            rowNum++;
            System.out.println();
        }
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println("\nRows: " + rowNum);
    }

    // Tests

    private static TestData originalTest() {
        List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets = new ArrayList<>();
        List<List<LoanDataValuesDTO>> rows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData = new ArrayList<>();
        rowData.add(new LoanDataValuesDTO("SFR: Finding Name", "1-4 Family Rider is Missing", "finding", "1", 0));
        List<LoanDataValuesDTO> rowData2 = new ArrayList<>();
        rowData2.add(new LoanDataValuesDTO("SFR: Finding Name", "RMC: Max LTV/CLTV/HCLTV 90%", "finding", "2", 0));
        List<LoanDataValuesDTO> rowData3 = new ArrayList<>();
        rowData3.add(new LoanDataValuesDTO("SFR: Finding Name", "RMC: Product Type does not meet overlay requirements", "finding", "3", 0));

        rows.add(rowData);
        rows.add(rowData2);
        rows.add(rowData3);

        List<List<LoanDataValuesDTO>> rows2 = new ArrayList<>();

        List<LoanDataValuesDTO> rowData4 = new ArrayList<>();
        rowData4.add(new LoanDataValuesDTO("Radian Overlays: Finding Name", "RMC: Product Type does not meet overlay requirements", "finding", "3", 1));
        List<LoanDataValuesDTO> rowData5 = new ArrayList<>();
        rowData5.add(new LoanDataValuesDTO("Radian Overlays: Finding Name", "RMC: Max LTV/CLTV/HCLTV 90%", "finding", "2", 1));
        rows2.add(rowData4);
        rows2.add(rowData5);

        List<List<LoanDataValuesDTO>> rowsF = new ArrayList<>();

        List<LoanDataValuesDTO> rowDataA = new ArrayList<>();
        rowDataA.add(new LoanDataValuesDTO("FHR: Finding Name", "RMC: Product Type does not meet overlay requirements", "finding", "3", 2));
        rowDataA.add(new LoanDataValuesDTO("FHR: Section", "1.1 Section", "finding", "3", 3));
        //List<LoanDataValuesDTO> rowDataB = new ArrayList<>();
        //rowDataB.add(new LoanDataValuesDTO("", "RMC: Max LTV/CLTV/HCLTV 90%", "finding", "2"));
        rowsF.add(rowDataA);
        //rowsF.add(rowDataB);

        List<List<LoanDataValuesDTO>> rows3 = new ArrayList<>();

        List<LoanDataValuesDTO> rowData6 = new ArrayList<>();
        rowData6.add(new LoanDataValuesDTO("Data Compare: Field Name", "Example", "dataCompare", "1", 4));
        rowData6.add(new LoanDataValuesDTO("Data Compare: Discrepancy", "yes", "dataCompare", "1", 5));
        List<LoanDataValuesDTO> rowData7 = new ArrayList<>();
        rowData7.add(new LoanDataValuesDTO("Data Compare: Field Name", "Example 2", "dataCompare", "2", 4));
        rowData7.add(new LoanDataValuesDTO("Data Compare: Field Name", "no", "dataCompare", "2", 5));
        rows3.add(rowData6);
        rows3.add(rowData7);

        //List<List<LoanDataValuesDTO>> rows4 = new ArrayList<>();

        /*List<LoanDataValuesDTO> rowData8 = new ArrayList<>();
        rowData8.add(new LoanDataValuesDTO("Data Compare: Discrepancy", "Example 3", "dataCompare", "1"));
        List<LoanDataValuesDTO> rowData9 = new ArrayList<>();
        rowData9.add(new LoanDataValuesDTO("Data Compare: Discrepancy", "Example 4", "dataCompare", "2"));
        rows4.add(rowData8);
        rows4.add(rowData9);*/

        /*List<List<LoanDataValuesDTO>> rows5 = new ArrayList<>();
        List<LoanDataValuesDTO> rowData10 = new ArrayList<>();
        rowData10.add(new LoanDataValuesDTO("", "Other", "someOtherKey", "1"));
        rows5.add(rowData10);

        List<List<LoanDataValuesDTO>> rows6 = new ArrayList<>();
        List<LoanDataValuesDTO> rowData11 = new ArrayList<>();
        rowData11.add(new LoanDataValuesDTO("", "Other1", "someOtherKey", "2"));
        rows6.add(rowData11);*/


        multiRowCustomReportDataSets.add(rows);
        multiRowCustomReportDataSets.add(rows2);
        multiRowCustomReportDataSets.add(rowsF);
        multiRowCustomReportDataSets.add(rows3);
        //multiRowCustomReportDataSets.add(rows4);

        //multiRowCustomReportDataSets.add(rows5);
        //multiRowCustomReportDataSets.add(rows6);
        return autoOutput(multiRowCustomReportDataSets, new ArrayList<>());
    }
    private static TestData oneGroupTest() {
        List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets = new ArrayList<>();

        int firstReportRows = 15;
        List<List<LoanDataValuesDTO>> rows = new ArrayList<>();
        for (int i = 1; i < firstReportRows; i++) {
            List<LoanDataValuesDTO> rowData = new ArrayList<>();
            rowData.add(r1(i));
            rows.add(rowData);
        }
        Collections.shuffle(rows);

        List<List<LoanDataValuesDTO>> radianOverlaysReport = new ArrayList<>();
        List<LoanDataValuesDTO> rowData15 = new ArrayList<>();
        rowData15.add(new LoanDataValuesDTO("Radian Overlay", "Test", "finding", firstReportRows+"", 1));
        radianOverlaysReport.add(rowData15);

        multiRowCustomReportDataSets.add(rows);
        multiRowCustomReportDataSets.add(radianOverlaysReport);
        return autoOutput(multiRowCustomReportDataSets, new ArrayList<>());
    }
    private static TestData twoGroupTest() {
        List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets = new ArrayList<>();

        List<List<LoanDataValuesDTO>> loanIdReport = new ArrayList<>();
        List<LoanDataValuesDTO> lrRd = new ArrayList<>();
        lrRd.add(new LoanDataValuesDTO("Loan ID Report", "Loan ID", "1", "finding", "1", 1, 1));
        List<LoanDataValuesDTO> lrRdA = new ArrayList<>();
        lrRdA.add(new LoanDataValuesDTO("Loan ID Report", "Loan ID", "1", "finding", "2", 1, 1));
        List<LoanDataValuesDTO> lrRdB = new ArrayList<>();
        lrRdB.add(new LoanDataValuesDTO("Loan ID Report", "Loan ID", "1", "finding", "3", 1, 1));
        List<LoanDataValuesDTO> lrRd2 = new ArrayList<>();
        lrRd2.add(new LoanDataValuesDTO("Loan ID Report", "Loan ID", "2", "finding", "1", 1, 2));
        List<LoanDataValuesDTO> lrRd2A = new ArrayList<>();
        lrRd2A.add(new LoanDataValuesDTO("Loan ID Report", "Loan ID", "2", "finding", "2", 1, 2));
        List<LoanDataValuesDTO> lrRd2B = new ArrayList<>();
        lrRd2B.add(new LoanDataValuesDTO("Loan ID Report", "Loan ID", "2", "finding", "3", 1, 2));
        loanIdReport.add(lrRd);
        loanIdReport.add(lrRdA);
        loanIdReport.add(lrRdB);
        loanIdReport.add(lrRd2);
        loanIdReport.add(lrRd2A);
        loanIdReport.add(lrRd2B);

        List<List<LoanDataValuesDTO>> standardFindingRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData = new ArrayList<>();
        rowData.add(new LoanDataValuesDTO("Standard Findings Report", "SFR: Finding Name", "1-4", "finding", "1", 2, 1));
        List<LoanDataValuesDTO> rowData2 = new ArrayList<>();
        rowData2.add(new LoanDataValuesDTO("Standard Findings Report", "SFR: Finding Name", "RMCa", "finding", "2", 2, 1));
        List<LoanDataValuesDTO> rowData3 = new ArrayList<>();
        rowData3.add(new LoanDataValuesDTO("Standard Findings Report", "SFR: Finding Name", "RMCb", "finding", "3", 2, 1));
        List<LoanDataValuesDTO> rowDataX = new ArrayList<>();
        rowDataX.add(new LoanDataValuesDTO("Standard Findings Report", "SFR: Finding Name", "1-4", "finding", "5", 2, 2));
        List<LoanDataValuesDTO> rowDataY = new ArrayList<>();
        rowDataY.add(new LoanDataValuesDTO("Standard Findings Report", "SFR: Finding Name", "Missing Doc", "finding", "6", 2, 2));
        List<LoanDataValuesDTO> rowDataZ = new ArrayList<>();
        rowDataZ.add(new LoanDataValuesDTO("Standard Findings Report", "SFR: Finding Name", "DC Finfing", "finding", "7", 2, 2));
        List<LoanDataValuesDTO> rowDataZA = new ArrayList<>();
        rowDataZA.add(new LoanDataValuesDTO("Standard Findings Report", "SFR: Finding Name", "Note Date is Missing", "finding", "8", 2, 2));
        standardFindingRows.add(rowData);
        standardFindingRows.add(rowData2);
        standardFindingRows.add(rowData3);
        standardFindingRows.add(rowDataX);
        standardFindingRows.add(rowDataY);
        standardFindingRows.add(rowDataZ);
        standardFindingRows.add(rowDataZA);

        List<List<LoanDataValuesDTO>> findingHistoryRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData4 = new ArrayList<>();
        rowData4.add(new LoanDataValuesDTO("Findings History Report", "FHR: Finding Name", "RMCb", "finding", "3", 3, 1));
        List<LoanDataValuesDTO> rowData5 = new ArrayList<>();
        rowData5.add(new LoanDataValuesDTO("Findings History Report","FHR: Finding Name", "RMCa", "finding", "2", 3, 1));
        List<LoanDataValuesDTO> rowData55 = new ArrayList<>();
        rowData55.add(new LoanDataValuesDTO("Findings History Report","FHR: Finding Name", "Note Date is Missing", "finding", "8", 3, 2));
        findingHistoryRows.add(rowData4);
        findingHistoryRows.add(rowData5);
        findingHistoryRows.add(rowData55);

        List<List<LoanDataValuesDTO>> radianRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowDataA = new ArrayList<>();
        rowDataA.add(new LoanDataValuesDTO("Radian Overlays Report","Radian: Finding Name", "RMCb", "finding", "3", 4, 1));
        rowDataA.add(new LoanDataValuesDTO("Radian Overlays Report", "Radian: Section", "1.1 Section", "finding", "3", 5, 1));
        radianRows.add(rowDataA);

        List<List<LoanDataValuesDTO>> dataCompareRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData6 = new ArrayList<>();
        rowData6.add(new LoanDataValuesDTO("Data Compare Report","DC: Field Name", "Number of Borrowers", "field", "1", 6, 1));
        rowData6.add(new LoanDataValuesDTO("Data Compare Report","DC: Source", "the1008Page", "field", "1", 8, 1));
        rowData6.add(new LoanDataValuesDTO("Data Compare Report","DC: Comment", "Rescinded", "field", "1", 9, 1));
        List<LoanDataValuesDTO> rowData7 = new ArrayList<>();
        rowData7.add(new LoanDataValuesDTO("Data Compare Report","DC: Field Name", "Qualifying HLTV", "field", "2", 6, 1));
        rowData7.add(new LoanDataValuesDTO("Data Compare Report","DC: Source", "Property", "field", "2", 8, 1));
        rowData7.add(new LoanDataValuesDTO("Data Compare Report","DC: Comment", "Need explanation", "field", "2", 9, 1));
        dataCompareRows.add(rowData6);
        dataCompareRows.add(rowData7);

        List<List<LoanDataValuesDTO>> dataCompareSummaryRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData8 = new ArrayList<>();
        rowData8.add(new LoanDataValuesDTO("Data Compare Summary", "DCS: Field Label", "NumberofBorrowers", "field", "1", 7, 1));
        rowData8.add(new LoanDataValuesDTO("Data Compare Summary", "DCS: % Variance", "25%", "field", "1", 10, 1));
        List<LoanDataValuesDTO> rowData9 = new ArrayList<>();
        rowData9.add(new LoanDataValuesDTO("Data Compare Summary", "DCS: Field Label", "QualifyingHLTV", "field", "2", 7, 1));
        rowData9.add(new LoanDataValuesDTO("Data Compare Summary", "DCS: % Variance", "33%", "field", "2", 10, 1));
        dataCompareSummaryRows.add(rowData8);
        dataCompareSummaryRows.add(rowData9);

        multiRowCustomReportDataSets.add(loanIdReport);
        multiRowCustomReportDataSets.add(standardFindingRows);
        multiRowCustomReportDataSets.add(findingHistoryRows);
        multiRowCustomReportDataSets.add(radianRows);
        multiRowCustomReportDataSets.add(dataCompareRows);
        multiRowCustomReportDataSets.add(dataCompareSummaryRows);
        return autoOutput(multiRowCustomReportDataSets, new ArrayList<>());
    }
    private static TestData threeGroupTest() {
        List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets = new ArrayList<>();
        List<List<LoanDataValuesDTO>> standardFindingRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData = new ArrayList<>();
        rowData.add(new LoanDataValuesDTO("SFR: Finding Name", "1-4", "finding", "1", 1));
        List<LoanDataValuesDTO> rowData2 = new ArrayList<>();
        rowData2.add(new LoanDataValuesDTO("SFR: Finding Name", "RMCa", "finding", "2", 1));
        List<LoanDataValuesDTO> rowData3 = new ArrayList<>();
        rowData3.add(new LoanDataValuesDTO("SFR: Finding Name", "RMCb", "finding", "3", 1));
        standardFindingRows.add(rowData);
        standardFindingRows.add(rowData2);
        standardFindingRows.add(rowData3);

        List<List<LoanDataValuesDTO>> findingHistoryRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData4 = new ArrayList<>();
        rowData4.add(new LoanDataValuesDTO("FHR: Finding Name", "RMCb", "finding", "3", 2));
        List<LoanDataValuesDTO> rowData5 = new ArrayList<>();
        rowData5.add(new LoanDataValuesDTO("FHR: Finding Name", "RMCa", "finding", "2", 2));
        findingHistoryRows.add(rowData4);
        findingHistoryRows.add(rowData5);

        List<List<LoanDataValuesDTO>> radianRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowDataA = new ArrayList<>();
        rowDataA.add(new LoanDataValuesDTO("Radian: Finding Name", "RMCb", "finding", "3", 3));
        rowDataA.add(new LoanDataValuesDTO("Radian: Section", "1.1 Section", "finding", "3", 4));
        radianRows.add(rowDataA);

        List<List<LoanDataValuesDTO>> dataCompareRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData6 = new ArrayList<>();
        rowData6.add(new LoanDataValuesDTO("DC: Field Name", "Number of Borrowers", "field", "1", 5));
        rowData6.add(new LoanDataValuesDTO("DC: Source", "the1008Page", "field", "1", 7));
        rowData6.add(new LoanDataValuesDTO("DC: Comment", "Rescinded", "field", "1", 8));
        List<LoanDataValuesDTO> rowData7 = new ArrayList<>();
        rowData7.add(new LoanDataValuesDTO("DC: Field Name", "Qualifying HLTV", "field", "2", 5));
        rowData7.add(new LoanDataValuesDTO("DC: Source", "Property", "field", "2", 7));
        rowData7.add(new LoanDataValuesDTO("DC: Comment", "Need explanation", "field", "2", 8));
        dataCompareRows.add(rowData6);
        dataCompareRows.add(rowData7);

        List<List<LoanDataValuesDTO>> dataCompareSummaryRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData8 = new ArrayList<>();
        rowData8.add(new LoanDataValuesDTO("DCS: Field Label", "NumberofBorrowers", "field", "1", 6));
        rowData8.add(new LoanDataValuesDTO("DCS: % Variance", "25%", "field", "1", 9));
        List<LoanDataValuesDTO> rowData9 = new ArrayList<>();
        rowData9.add(new LoanDataValuesDTO("DCS: Field Label", "QualifyingHLTV", "field", "2", 6));
        rowData9.add(new LoanDataValuesDTO("DCS: % Variance", "33%", "field", "2", 9));
        dataCompareSummaryRows.add(rowData8);
        dataCompareSummaryRows.add(rowData9);

        List<List<LoanDataValuesDTO>> multiPropertyRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowDataC = new ArrayList<>();
        rowDataC.add(new LoanDataValuesDTO("MP: Property ID", "1", "property", "1", 10));
        rowDataC.add(new LoanDataValuesDTO("MP: Loan Amount", "$500,000", "property", "1", 11));
        List<LoanDataValuesDTO> rowDataD = new ArrayList<>();
        rowDataD.add(new LoanDataValuesDTO("MP: Property ID", "2", "property", "2", 10));
        rowDataD.add(new LoanDataValuesDTO("MP: Loan Amount", "$440,000", "property", "2", 11));
        List<LoanDataValuesDTO> rowDataE = new ArrayList<>();
        rowDataE.add(new LoanDataValuesDTO("MP: Property ID", "3", "property", "3", 10));
        rowDataE.add(new LoanDataValuesDTO("MP: Loan Amount", "$660,000", "property", "3", 11));
        List<LoanDataValuesDTO> rowDataF = new ArrayList<>();
        rowDataF.add(new LoanDataValuesDTO("MP: Property ID", "4", "property", "4", 10));
        rowDataF.add(new LoanDataValuesDTO("MP: Loan Amount", "$330,000", "property", "4", 11));
        multiPropertyRows.add(rowDataC);
        multiPropertyRows.add(rowDataD);
        multiPropertyRows.add(rowDataE);
        multiPropertyRows.add(rowDataF);


        multiRowCustomReportDataSets.add(standardFindingRows);
        multiRowCustomReportDataSets.add(findingHistoryRows);
        multiRowCustomReportDataSets.add(radianRows);
        multiRowCustomReportDataSets.add(dataCompareRows);
        multiRowCustomReportDataSets.add(dataCompareSummaryRows);
        multiRowCustomReportDataSets.add(multiPropertyRows);
        return autoOutput(multiRowCustomReportDataSets, new ArrayList<>());
    }
    private static TestData fourGroupTest() {
        List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets = new ArrayList<>();
        List<List<LoanDataValuesDTO>> standardFindingRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData = new ArrayList<>();
        rowData.add(new LoanDataValuesDTO("SFR: Finding Name", "1-4", "finding", "1", 1));
        List<LoanDataValuesDTO> rowData2 = new ArrayList<>();
        rowData2.add(new LoanDataValuesDTO("SFR: Finding Name", "RMCa", "finding", "2", 1));
        List<LoanDataValuesDTO> rowData3 = new ArrayList<>();
        rowData3.add(new LoanDataValuesDTO("SFR: Finding Name", "RMCb", "finding", "3", 1));
        standardFindingRows.add(rowData);
        standardFindingRows.add(rowData2);
        standardFindingRows.add(rowData3);

        List<List<LoanDataValuesDTO>> findingHistoryRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData4 = new ArrayList<>();
        rowData4.add(new LoanDataValuesDTO("FHR: Finding Name", "RMCb", "finding", "3", 2));
        List<LoanDataValuesDTO> rowData5 = new ArrayList<>();
        rowData5.add(new LoanDataValuesDTO("FHR: Finding Name", "RMCa", "finding", "2", 2));
        findingHistoryRows.add(rowData4);
        findingHistoryRows.add(rowData5);

        List<List<LoanDataValuesDTO>> radianRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowDataA = new ArrayList<>();
        rowDataA.add(new LoanDataValuesDTO("Radian: Finding Name", "RMCb", "finding", "3", 3));
        rowDataA.add(new LoanDataValuesDTO("Radian: Section", "1.1 Section", "finding", "3", 4));
        radianRows.add(rowDataA);

        List<List<LoanDataValuesDTO>> dataCompareRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData6 = new ArrayList<>();
        rowData6.add(new LoanDataValuesDTO("DC: Field Name", "Number of Borrowers", "field", "1", 5));
        rowData6.add(new LoanDataValuesDTO("DC: Source", "the1008Page", "field", "1", 7));
        rowData6.add(new LoanDataValuesDTO("DC: Comment", "Rescinded", "field", "1", 8));
        List<LoanDataValuesDTO> rowData7 = new ArrayList<>();
        rowData7.add(new LoanDataValuesDTO("DC: Field Name", "Qualifying HLTV", "field", "2", 5));
        rowData7.add(new LoanDataValuesDTO("DC: Source", "Property", "field", "2", 7));
        rowData7.add(new LoanDataValuesDTO("DC: Comment", "Need explanation", "field", "2", 8));
        dataCompareRows.add(rowData6);
        dataCompareRows.add(rowData7);

        List<List<LoanDataValuesDTO>> dataCompareSummaryRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData8 = new ArrayList<>();
        rowData8.add(new LoanDataValuesDTO("DCS: Field Label", "NumberofBorrowers", "field", "1", 6));
        rowData8.add(new LoanDataValuesDTO("DCS: % Variance", "25%", "field", "1", 9));
        List<LoanDataValuesDTO> rowData9 = new ArrayList<>();
        rowData9.add(new LoanDataValuesDTO("DCS: Field Label", "QualifyingHLTV", "field", "2", 6));
        rowData9.add(new LoanDataValuesDTO("DCS: % Variance", "33%", "field", "2", 9));
        dataCompareSummaryRows.add(rowData8);
        dataCompareSummaryRows.add(rowData9);

        List<List<LoanDataValuesDTO>> multiPropertyRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowDataC = new ArrayList<>();
        rowDataC.add(new LoanDataValuesDTO("MP: Property ID", "1", "property", "1", 10));
        rowDataC.add(new LoanDataValuesDTO("MP: Loan Amount", "$500,000", "property", "1", 11));
        List<LoanDataValuesDTO> rowDataD = new ArrayList<>();
        rowDataD.add(new LoanDataValuesDTO("MP: Property ID", "2", "property", "2", 10));
        rowDataD.add(new LoanDataValuesDTO("MP: Loan Amount", "$440,000", "property", "2", 11));
        List<LoanDataValuesDTO> rowDataE = new ArrayList<>();
        rowDataE.add(new LoanDataValuesDTO("MP: Property ID", "3", "property", "3", 10));
        rowDataE.add(new LoanDataValuesDTO("MP: Loan Amount", "$660,000", "property", "3", 11));
        List<LoanDataValuesDTO> rowDataF = new ArrayList<>();
        rowDataF.add(new LoanDataValuesDTO("MP: Property ID", "4", "property", "4", 10));
        rowDataF.add(new LoanDataValuesDTO("MP: Loan Amount", "$330,000", "property", "4", 11));
        multiPropertyRows.add(rowDataC);
        multiPropertyRows.add(rowDataD);
        multiPropertyRows.add(rowDataE);
        multiPropertyRows.add(rowDataF);

        List<List<LoanDataValuesDTO>> borrowerReport = new ArrayList<>();
        List<LoanDataValuesDTO> rowDataQ = new ArrayList<>();
        rowDataQ.add(new LoanDataValuesDTO("B: Name", "Adam Bennett", "borrower", "1", 12));
        rowDataQ.add(new LoanDataValuesDTO("B: DOB", "10/03/1993", "borrower", "1", 13));
        List<LoanDataValuesDTO> rowDataX = new ArrayList<>();
        rowDataX.add(new LoanDataValuesDTO("B: Name", "John Developer", "borrower", "2", 12));
        rowDataX.add(new LoanDataValuesDTO("B: DOB", "01/01/1970", "borrower", "2", 13));
        borrowerReport.add(rowDataQ);
        borrowerReport.add(rowDataX);


        multiRowCustomReportDataSets.add(standardFindingRows);
        multiRowCustomReportDataSets.add(findingHistoryRows);
        multiRowCustomReportDataSets.add(radianRows);
        multiRowCustomReportDataSets.add(dataCompareRows);
        multiRowCustomReportDataSets.add(dataCompareSummaryRows);
        multiRowCustomReportDataSets.add(multiPropertyRows);
        multiRowCustomReportDataSets.add(borrowerReport);
        return autoOutput(multiRowCustomReportDataSets, new ArrayList<>());
    }
    private static TestData realTest() {
        List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets = new ArrayList<>();
        List<List<LoanDataValuesDTO>> standardFindingRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData = new ArrayList<>();
        rowData.add(new LoanDataValuesDTO("SFR: Queue", "Seller", "finding", "174964", 9));
        rowData.add(new LoanDataValuesDTO("Standard Findings Report", "Finding Name", "Credit", "finding", "174964", 10));
        List<LoanDataValuesDTO> rowData2 = new ArrayList<>();
        rowData2.add(new LoanDataValuesDTO("SFR: Queue", "Seller", "finding", "174963", 9));
        rowData2.add(new LoanDataValuesDTO("Standard Findings Report","Finding Name", "Credit", "finding", "174963", 10));
        List<LoanDataValuesDTO> rowData3 = new ArrayList<>();
        rowData3.add(new LoanDataValuesDTO("SFR: Queue", "Seller", "finding", "174965", 9));
        rowData3.add(new LoanDataValuesDTO("Standard Findings Report","Finding Name", "Compliance", "finding", "174965", 10));
        standardFindingRows.add(rowData);
        standardFindingRows.add(rowData2);
        standardFindingRows.add(rowData3);

        List<List<LoanDataValuesDTO>> findingHistoryRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData4 = new ArrayList<>();
        rowData4.add(new LoanDataValuesDTO("Findings History Report", "Finding Name", "1-4 Family Rider is Missing", "finding", "174965", 13));
        List<LoanDataValuesDTO> rowData5 = new ArrayList<>();
        rowData5.add(new LoanDataValuesDTO("Findings History Report", "Finding Name", "RMC: Max LTV/CLTV/HCLTV 90%", "finding", "174963", 13));
        List<LoanDataValuesDTO> rowDataX = new ArrayList<>();
        rowDataX.add(new LoanDataValuesDTO("Findings History Report", "Finding Name", "RMC: Product Type does not meet overlay requirements", "finding", "174964", 13));
        findingHistoryRows.add(rowData4);
        findingHistoryRows.add(rowData5);
        findingHistoryRows.add(rowDataX);

        List<List<LoanDataValuesDTO>> radianRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowDataR = new ArrayList<>();
        rowDataR.add(new LoanDataValuesDTO("Radian Overlays Report", "Finding Name", "RMC: Product Type does not meet overlay requirements", "finding", "174964", 8));
        List<LoanDataValuesDTO> rowDataR2 = new ArrayList<>();
        rowDataR2.add(new LoanDataValuesDTO("Radian Overlays Report", "Finding Name", "RMC: Max LTV/CLTV/HCLTV 90%", "finding", "174963", 8));
        radianRows.add(rowDataR);
        radianRows.add(rowDataR2);

        multiRowCustomReportDataSets.add(standardFindingRows);
        multiRowCustomReportDataSets.add(findingHistoryRows);
        multiRowCustomReportDataSets.add(radianRows);
        return autoOutput(multiRowCustomReportDataSets, new ArrayList<>());
    }
    private static TestData txTest() {
        List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets = new ArrayList<>();
        List<List<LoanDataValuesDTO>> standardFindingRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData = new ArrayList<>();
        rowData.add(new LoanDataValuesDTO("Standard Findings Report", "SFR: Queue", "Seller", "finding", "175283", 9));
        rowData.add(new LoanDataValuesDTO("Standard Findings Report", "Finding Name", "Compliance", "finding", "175283", 10));
        List<LoanDataValuesDTO> rowData2 = new ArrayList<>();
        rowData2.add(new LoanDataValuesDTO("Standard Findings Report", "SFR: Queue", "Seller", "finding", "68043", 9));
        rowData2.add(new LoanDataValuesDTO("Standard Findings Report","Finding Name", "Property", "finding", "68043", 10));
        standardFindingRows.add(rowData);
        standardFindingRows.add(rowData2);

        List<List<LoanDataValuesDTO>> findingHistoryRows = new ArrayList<>();
        List<String> groupKeys = List.of("53052", "53567", "53562", "175283", "53566", "53565", "53564", "53563", "53568", "53559", "53561", "53558", "53560", "68043");
        List<String> values = List.of("Note rate > 8%", "Missing borrower's date of birth", "The Note is Missing", "1-4 Family Rider is Missing", "Default comment Finding", "DC Finfing", "No Default Comment", "NDC Finding",
                "Bennett Found", "First Payment Date is not the first of the month", "Maturity Date is not the first of the month", "Term and Amortization Term do not match",
                "Calculated Initial Payment and Initial Principal and Interest Payment do not match", "Appraised value unsupported");
        for (int i = 0; i < 14; i++) {
            List<LoanDataValuesDTO> rowDat = new ArrayList<>();
            rowDat.add(txDV(values.get(i), groupKeys.get(i)));
            findingHistoryRows.add(rowDat);
        }

        List<List<LoanDataValuesDTO>> radianRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowDataR = new ArrayList<>();
        rowDataR.add(new LoanDataValuesDTO("Radian Overlays Report", "Finding Name", "", "finding", "", 8));
        radianRows.add(rowDataR);

        multiRowCustomReportDataSets.add(standardFindingRows);
        multiRowCustomReportDataSets.add(findingHistoryRows);
        multiRowCustomReportDataSets.add(radianRows);
        return autoOutput(multiRowCustomReportDataSets, new ArrayList<>());
    }
    private record TestData(List<ColumnHeader> headers, List<List<List<LoanDataValuesDTO>>> basicReportData, List<List<List<LoanDataValuesDTO>>> forcedMultiLoanReportData) {}
    private static TestData dataCompareSummaryTest() {
        List<List<List<LoanDataValuesDTO>>> basicReportData = new ArrayList<>();
        List<List<List<LoanDataValuesDTO>>> forcedMultiLoanReportData = new ArrayList<>();
        List<List<LoanDataValuesDTO>> dataCompareRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData = new ArrayList<>();
        rowData.add(new LoanDataValuesDTO("Data Compare Report", "Loan ID", "BNT99140", "field", "PropertyAddress", 1, 1));
        rowData.add(new LoanDataValuesDTO("Data Compare Report", "Field Name", "Property Address", "field", "PropertyAddress", 2, 1));
        rowData.add(new LoanDataValuesDTO("Data Compare Report", "Source", "tape", "field", "PropertyAddress", 3, 1));
        List<LoanDataValuesDTO> rowData5 = new ArrayList<>();
        rowData5.add(new LoanDataValuesDTO("Data Compare Report", "Loan ID", "BNT99140", "field", "PropertyCity", 1, 1));
        rowData5.add(new LoanDataValuesDTO("Data Compare Report", "Field Name", "Property City", "field", "PropertyCity", 2, 1));
        rowData5.add(new LoanDataValuesDTO("Data Compare Report", "Source", "tape", "field", "PropertyCity", 3, 1));
        List<LoanDataValuesDTO> rowData2 = new ArrayList<>();
        rowData2.add(new LoanDataValuesDTO("Data Compare Report", "Loan ID", "BNT99142", "field", "PropertyCity", 1, 2));
        rowData2.add(new LoanDataValuesDTO("Data Compare Report", "Field Name", "Property City", "field", "PropertyCity", 2, 2));
        rowData2.add(new LoanDataValuesDTO("Data Compare Report", "Source", "the1008Page", "field", "PropertyCity", 3, 2));
        List<LoanDataValuesDTO> rowData6 = new ArrayList<>();
        rowData6.add(new LoanDataValuesDTO("Data Compare Report", "Loan ID", "BNT99142", "field", "PropertyAddress", 1, 2));
        rowData6.add(new LoanDataValuesDTO("Data Compare Report", "Field Name", "Property Address", "field", "PropertyAddress", 2, 2));
        rowData6.add(new LoanDataValuesDTO("Data Compare Report", "Source", "the1008Page", "field", "PropertyAddress", 3, 2));
        dataCompareRows.add(rowData);
        dataCompareRows.add(rowData5);
        dataCompareRows.add(rowData2);
        dataCompareRows.add(rowData6);

        List<List<LoanDataValuesDTO>> dataCompareSummaryRows = new ArrayList<>();
        List<LoanDataValuesDTO> rowData3 = new ArrayList<>();
        List<Integer> allLoansCompared = List.of(1, 2);
        rowData3.add(new LoanDataValuesDTO("Data Compare Summary Report", "Loans With Discrepancy", "8", "field", "PropertyAddress", 4, allLoansCompared));
        rowData3.add(new LoanDataValuesDTO("Data Compare Summary Report", "Total Times Compared", "60", "field", "PropertyAddress", 5, allLoansCompared));
        rowData3.add(new LoanDataValuesDTO("Data Compare Summary Report", "% Variance", "13.33%", "field", "PropertyAddress", 6, allLoansCompared));
        List<LoanDataValuesDTO> rowData4 = new ArrayList<>();
        rowData4.add(new LoanDataValuesDTO("Data Compare Summary Report", "Loans With Discrepancy", "4", "field", "PropertyCity", 4, allLoansCompared));
        rowData4.add(new LoanDataValuesDTO("Data Compare Summary Report", "Total Times Compared", "60", "field", "PropertyCity", 5, allLoansCompared));
        rowData4.add(new LoanDataValuesDTO("Data Compare Summary Report", "% Variance", "6.67%", "field", "PropertyCity", 6, allLoansCompared));
        dataCompareSummaryRows.add(rowData3);
        dataCompareSummaryRows.add(rowData4);

        basicReportData.add(dataCompareRows);
        forcedMultiLoanReportData.add(dataCompareSummaryRows);

        return autoOutput(basicReportData, forcedMultiLoanReportData);
    }


    // Testing Utilities
    private static LoanDataValuesDTO r1(int i) {
        return new LoanDataValuesDTO("Finding Name", "1-4 Family Rider is Missing" + i, "finding", i+"", 2);
    }
    private static LoanDataValuesDTO txDV(String value, String groupKey) {
        return LoanDataValuesDTO.builder()
                .reportHeaderName("Finding Name")
                .reportName("Findings History Report")
                .groupKey(groupKey)
                .groupingKey("finding")
                .value(value)
                .order(13)
                .build();
    }
    private static void extractHeadersFromTestData(List<List<List<LoanDataValuesDTO>>> reportData, HashSet<ColumnHeader> headers) {
        for (List<List<LoanDataValuesDTO>> list : reportData) {
            for (List<LoanDataValuesDTO> row : list) {
                for (LoanDataValuesDTO cell : row) {
                    headers.add(new ColumnHeader(cell.getReportName(), cell.getReportHeaderName(), cell.getOrder(), cell.getGroupingKey()));
                }
            }
        }
    }
    private static TestData autoOutput(List<List<List<LoanDataValuesDTO>>> basicReports, List<List<List<LoanDataValuesDTO>>> forcedMultiLoanReports) {
        HashSet<ColumnHeader> headers = new HashSet<>();
        extractHeadersFromTestData(basicReports, headers);
        extractHeadersFromTestData(forcedMultiLoanReports, headers);
        List<ColumnHeader> sorted = headers.stream().sorted().toList();
        return new TestData(sorted, basicReports, forcedMultiLoanReports);
    }

    // Logic
    private record ColumnHeader(String identifier, String value, int order, String groupingKey) implements Comparable<ColumnHeader> {

        @Override
        public int compareTo(ColumnHeader o) {
            return Comparator.comparing(ColumnHeader::order).compare(this, o);
        }
    }
    private record GroupKeyValue(String identifier, String groupingKey, String key, String value, int order, String header, Integer loanRecordId) implements Comparable<GroupKeyValue> {
        @Override
        public int compareTo(GroupKeyValue o) {
            return Comparator.comparing(GroupKeyValue::order).compare(this, o);
        }
    }
    private record GroupedRow(String groupingKey, Integer rowIndex) {}
    private record LoanGroupingKey(String groupingKey, Integer loanRecordId) {}
    private static List<List<LoanDataValuesDTO>> groupSortMultiRowReportData(List<List<List<LoanDataValuesDTO>>> data) {
        // Variables
        List<List<LoanDataValuesDTO>> output = new ArrayList<>();
        HashSet<ColumnHeader> headers = new HashSet<>();
        LinkedHashSet<String> matchKeyCheck = new LinkedHashSet<>();
        LinkedHashSet<GroupKeyValue> groupKeyValues = new LinkedHashSet<>();
        HashMap<String, Integer> groupingIteration = new HashMap<>();
        HashMap<String, Integer> groupingKeyRolloverMap = new HashMap<>();
        HashMap<String, Integer> rowsRemainingUntilRollover = new HashMap<>();
        HashMap<String, Integer> remainingRowsResetMap = new HashMap<>();
        HashMap<GroupedRow, String> groupKeyForRow = new HashMap<>();
        HashMap<GroupedRow, Integer> loanRecordIdForRow = new HashMap<>();
        HashMap<Integer, List<LoanDataValuesDTO>> rowsByColumnIndex = new HashMap<>();
        HashMap<String, LinkedHashSet<String>> uniqueKeysByGroupingKey = new HashMap<>();
        HashMap<LoanGroupingKey, LinkedHashSet<String>> uniqueKeysByLoanRecordAndGroupingKey = new HashMap<>();
        HashMap<String, LinkedHashSet<Integer>> uniqueLoanRecordIdsByGroupingKey = new HashMap<>();
        HashMap<String, List<String>> iterableGroupKeysByGroupingKey = new HashMap<>();
        HashMap<LoanGroupingKey, List<String>> iterableGroupKeysByLoanRecordAndGroupingKey = new HashMap<>();
        HashMap<String, List<Integer>> iterableLoanRecordIdsByGroupingKey = new HashMap<>();
        HashSet<Integer> uniqueLoanRecordIds = new HashSet<>();
        int loanRecordIteration = 0;

        // Setup
        for (List<List<LoanDataValuesDTO>> list : data) {
            for (List<LoanDataValuesDTO> row : list) {
                for (LoanDataValuesDTO cell : row) {
                    matchKeyCheck.add(cell.getGroupingKey());
                    uniqueLoanRecordIds.add(cell.getLoanRecordId());
                    groupKeyValues.add(new GroupKeyValue(cell.getReportName(), cell.getGroupingKey(), cell.getGroupKey(), cell.getValue(), cell.getOrder(), cell.getReportHeaderName(), cell.getLoanRecordId()));
                    headers.add(new ColumnHeader(cell.getReportName(), cell.getReportHeaderName(), cell.getOrder(), cell.getGroupingKey()));
                }
            }
        }
        List<Integer> iterableLoanRecordIds = new ArrayList<>(uniqueLoanRecordIds);
        List<ColumnHeader> sorted = headers.stream().sorted().toList();
        groupKeyValues.stream().sorted().toList().forEach(key -> {
            if (key.key() != null && !key.value().trim().isEmpty()) {
                LoanGroupingKey loanGroupingKey = new LoanGroupingKey(key.groupingKey(), key.loanRecordId());
                LinkedHashSet<String> list = uniqueKeysByGroupingKey.getOrDefault(key.groupingKey(), new LinkedHashSet<>());
                LinkedHashSet<String> lrList = uniqueKeysByLoanRecordAndGroupingKey.getOrDefault(loanGroupingKey, new LinkedHashSet<>());
                LinkedHashSet<Integer> loanIdList = uniqueLoanRecordIdsByGroupingKey.getOrDefault(key.groupingKey(), new LinkedHashSet<>());
                list.add(key.key());
                loanIdList.add(key.loanRecordId());
                lrList.add(key.key());
                uniqueKeysByGroupingKey.put(key.groupingKey(), list);
                uniqueKeysByLoanRecordAndGroupingKey.put(loanGroupingKey, lrList);
                uniqueLoanRecordIdsByGroupingKey.put(key.groupingKey(), loanIdList);
            }
        });
        for (var entry : uniqueKeysByGroupingKey.entrySet()) {
            List<String> list = iterableGroupKeysByGroupingKey.getOrDefault(entry.getKey(), new ArrayList<>());
            list.addAll(entry.getValue());
            iterableGroupKeysByGroupingKey.put(entry.getKey(), list);
        }
        for (var entry : uniqueKeysByLoanRecordAndGroupingKey.entrySet()) {
            List<String> list = iterableGroupKeysByLoanRecordAndGroupingKey.getOrDefault(entry.getKey(), new ArrayList<>());
            list.addAll(entry.getValue());
            iterableGroupKeysByLoanRecordAndGroupingKey.put(entry.getKey(), list);
        }
        for (var entry : uniqueLoanRecordIdsByGroupingKey.entrySet()) {
            List<Integer> list = iterableLoanRecordIdsByGroupingKey.getOrDefault(entry.getKey(), new ArrayList<>());
            list.addAll(entry.getValue());
            iterableLoanRecordIdsByGroupingKey.put(entry.getKey(), list);
        }
        List<String> matchKeys = matchKeyCheck.stream().toList();
        String currentMatchKey = matchKeys.get(0);
        int numberOfRowsToRolloverAfter = uniqueKeysByGroupingKey.get(currentMatchKey).size();
        int uniqueKeysSoFar = 0;
        for (String groupKey : matchKeyCheck) {
            groupingIteration.put(groupKey, 0);
            if (groupKey.equalsIgnoreCase(currentMatchKey)) {
                groupingKeyRolloverMap.put(groupKey, numberOfRowsToRolloverAfter);
                rowsRemainingUntilRollover.put(groupKey, 1);
                remainingRowsResetMap.put(groupKey, 1);
                uniqueKeysSoFar += uniqueKeysByGroupingKey.get(groupKey).size();
            } else {
                groupingKeyRolloverMap.put(groupKey, uniqueKeysSoFar);
                rowsRemainingUntilRollover.put(groupKey,uniqueKeysSoFar);
                remainingRowsResetMap.put(groupKey, uniqueKeysSoFar);
                uniqueKeysSoFar *= uniqueKeysByGroupingKey.get(groupKey).size();
            }
        }

        // Main Loop
        int rows = countRows(data);
        int columns = sorted.size();
        for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
            rowsByColumnIndex.put(columnIndex, new ArrayList<>());
            List<LoanDataValuesDTO> row = rowsByColumnIndex.get(columnIndex);
            ColumnHeader column = sorted.get(columnIndex);
            HashSet<Integer> loanRecordsSoFarThisColumn = new HashSet<>();
            for (int rowIndex = 0, rollOverCounter = 0; rowIndex < rows; rowIndex++) {
                String group = column.groupingKey();
                List<Integer> loanRecordIds = iterableLoanRecordIdsByGroupingKey.get(group);
                GroupedRow groupedRow = new GroupedRow(group, rowIndex);
                int lrIndex = loanRecordIteration;
                Integer loanRecordId = loanRecordIdForRow.getOrDefault(groupedRow, lrIndex >= loanRecordIds.size() ? null : loanRecordIds.get(lrIndex));
                List<String> groupKeys = iterableGroupKeysByGroupingKey.get(group);
                LoanGroupingKey loanGroupingKey = new LoanGroupingKey(group, loanRecordId);
                List<String> lrGroupKeys = iterableGroupKeysByLoanRecordAndGroupingKey.get(loanGroupingKey);
                List<GroupKeyValue> groupMatches = groupKeyValues.stream().filter(g -> g.identifier().equalsIgnoreCase(column.identifier()) && g.groupingKey().equalsIgnoreCase(group)).toList();
                int rolloverRows = groupingKeyRolloverMap.get(group);
                int index = groupingIteration.get(group);
                String groupKey = groupKeyForRow.getOrDefault(groupedRow, groupKeys.get(index));

                List<GroupKeyValue> matches = groupMatches.stream().filter(c -> c.key().equalsIgnoreCase(groupKey)).toList();
                if (matches.size() > 1) {
                    matches = matches.stream().filter(m -> (loanRecordId == null || m.loanRecordId().equals(loanRecordId)) && m.header().equalsIgnoreCase(column.value())).toList();
                }
                String cellValue = !matches.isEmpty() ? matches.get(0).value() : "";
                row.add(rowIndex, LoanDataValuesDTO.builder()
                        .reportHeaderName(column.value())
                        .value(cellValue)
                        .groupingKey(group)
                        .groupKey(groupKey)
                        .order(column.order())
                        .reportName(column.identifier())
                        .loanRecordId(loanRecordId)
                        .build()
                );
                if (matches.isEmpty() && !groupKeyForRow.containsKey(groupedRow)) {
                    groupKeyForRow.put(groupedRow, groupKey);
                }
                if (matches.isEmpty() && !loanRecordIdForRow.containsKey(groupedRow)) {
                    loanRecordIdForRow.put(groupedRow, loanRecordId);
                }

                loanRecordIteration++;
                if (loanRecordIteration >= iterableLoanRecordIds.size()) {
                    loanRecordIteration = 0;
                    if (currentMatchKey.equalsIgnoreCase(group)) {
                        groupingIteration.compute(group, (k, v) -> v == null ? 1 : v + 1);
                        if (groupingIteration.get(group) >= uniqueKeysByGroupingKey.get(group).size()) {
                            groupingIteration.put(group, 0);
                        }
                    }
                    for (var entry : rowsRemainingUntilRollover.entrySet()) {
                        rowsRemainingUntilRollover.compute(entry.getKey(), (k, v) -> v == null ? 0 : v - 1);
                    }
                    rollOverCounter = rollover(rollOverCounter, rolloverRows, currentMatchKey, group,
                            matchKeys, rowsRemainingUntilRollover, groupingIteration,
                            uniqueKeysByGroupingKey, remainingRowsResetMap);
                }
            }
        }

        // Prepare output
        for (int i = 0; i < rows; i++) {
            List<LoanDataValuesDTO> row = new ArrayList<>();
            for (var entry : rowsByColumnIndex.entrySet()) {
                row.add(entry.getValue().get(i));
            }
            output.add(row);
        }
        if (uniqueKeysByGroupingKey.keySet().size() == 1) {
            boolean allRowsHaveLoanRecordIds = true;
            for (var row : output) {
                for (var cell : row) {
                    if (cell.getLoanRecordId() == null || cell.getLoanRecordId() < 1) {
                        allRowsHaveLoanRecordIds = false;
                        break;
                    }
                }
            }
            if (allRowsHaveLoanRecordIds) {
                output.sort(Comparator.comparing(
                        (List<LoanDataValuesDTO> list) -> list == null || list.isEmpty() ? null : list.get(0).getLoanRecordId(),
                        Comparator.nullsLast(Comparator.naturalOrder())
                ));
            }
        }
        return output;
    }
    private static List<List<LoanDataValuesDTO>> addForcedMultiLoanColumnDataToRows(List<List<LoanDataValuesDTO>> rowValues, List<List<List<LoanDataValuesDTO>>> forcedMultiLoanRowValues) {
        for (var row : rowValues) {
            if (!row.isEmpty()) {
                var loanRecordId = row.get(0).getLoanRecordId();
                var matchingReport = forcedMultiLoanRowValues.stream().filter(report ->
                        report.stream().anyMatch(r ->
                                r.stream().anyMatch(cell -> {
                                    String cellGroupKey = cell != null ? cell.getGroupKey() : null;
                                    LoanDataValuesDTO row0 = r.get(0);
                                    String row0GroupKey = row0 != null ? row0.getGroupKey() : null;
                                    List<Integer> lrIds = cell != null ? cell.getLoanRecordIds() : null;
                                    return cell.getGroupKey().equalsIgnoreCase(r.get(0).getGroupKey()) && cell.getLoanRecordIds() != null && cell.getLoanRecordIds().contains(loanRecordId);
                                        }
                                        ))).toList();
                if (!matchingReport.isEmpty()) {
                    var matchingColumns = matchingReport.get(0).stream().filter(r -> r.stream().anyMatch(cell -> cell.getGroupKey().equalsIgnoreCase("PropertyAddress") && cell.getLoanRecordIds() != null && cell.getLoanRecordIds().contains(1))).toList();
                    if (!matchingColumns.isEmpty()) {
                        for (var col : matchingColumns.get(0)) {
                            row.add(LoanDataValuesDTO.builder()
                                    .reportHeaderName(col.getReportHeaderName())
                                    .loanRecordId(loanRecordId)
                                    .groupingKey(col.getGroupingKey())
                                    .groupKey(col.getGroupKey())
                                    .value(col.getValue())
                                    .order(col.getOrder())
                                    .loanRecordIds(col.getLoanRecordIds())
                                    .reportName(col.getReportName())
                                    .build()
                            );

                        }
                    }
                }
            }
            row.sort(Comparator.comparing(LoanDataValuesDTO::getOrder));
        }
        return rowValues;
    }
    private static int rollover(int rollOverCounter,
                                int rolloverRows,
                                String currentMatchKey,
                                String rowGroup,
                                List<String> matchKeys,
                                HashMap<String, Integer> rowsRemainingUntilRollover,
                                HashMap<String, Integer> groupingIteration,
                                HashMap<String, LinkedHashSet<String>> uniqueKeysByGroupingKey,
                                HashMap<String, Integer> remainingRowsResetMap) {
        rollOverCounter++;
        for (String g : matchKeys) {
            if (!g.equalsIgnoreCase(currentMatchKey)) {
                int remaining = rowsRemainingUntilRollover.get(g);
                if (remaining <= 0) {
                    groupingIteration.compute(g, (k, v) -> v == null ? 1 : v + 1);
                    if (groupingIteration.get(g) >= uniqueKeysByGroupingKey.get(g).size()) {
                        groupingIteration.put(g, 0);
                    }
                    rowsRemainingUntilRollover.put(g, remainingRowsResetMap.get(g));
                }
            }
        }
        if (rollOverCounter >= rolloverRows) {
            rollOverCounter = 0;
            if (currentMatchKey.equalsIgnoreCase(rowGroup)) {
                groupingIteration.put(rowGroup, 0);
                rowsRemainingUntilRollover.put(rowGroup, remainingRowsResetMap.get(rowGroup));
            }
        }
        return rollOverCounter;
    }
    private static int countRows(List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets) {
        // Use a HashMap to keep track of unique groupKeys for each groupingKey
        HashMap<String, HashMap<String, Boolean>> uniqueGroupKeys = new HashMap<>();
        HashSet<Integer> uniqueLoanIds = new HashSet<>();

        // Iterate over the multiRowCustomReportDataSets to populate uniqueGroupKeys
        for (List<List<LoanDataValuesDTO>> dataset : multiRowCustomReportDataSets) {
            for (List<LoanDataValuesDTO> rowGroup : dataset) {
                for (LoanDataValuesDTO row : rowGroup) {
                    String groupingKey = row.getGroupingKey();
                    String groupKey = row.getGroupKey();
                    uniqueLoanIds.add(row.getLoanRecordId());
                    if (groupingKey != null && !groupingKey.trim().isEmpty() && groupKey != null && !groupKey.trim().isEmpty()) {
                        uniqueGroupKeys.putIfAbsent(groupingKey, new HashMap<>());
                        uniqueGroupKeys.get(groupingKey).put(groupKey, true);
                    }
                }
            }
        }

        // Calculate the total number of rows by multiplying the counts of unique groupKeys for each groupingKey
        int totalRows = 1;
        for (var entry : uniqueGroupKeys.entrySet()) {
            totalRows *= entry.getValue().size();
        }

        return totalRows * uniqueLoanIds.size();
    }

    // Extras
    /*private static List<List<LoanDataValuesDTO>> formatMultiRowCustomReportData(List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets) {
        MultiRowGroupPhaseOneResult groupPhaseOneResult = groupSortMultiRowReportData(multiRowCustomReportDataSets);
        return addForcedMultiLoanColumnDataToRows(groupPhaseOneResult.normalMultiRowData(), groupPhaseOneResult.forcedMultiLoanRows());
    }*/
    private static class LoanDataValuesDTO {
        private String reportName;
        private String reportHeaderName;
        private String value;
        private String groupingKey;
        private String groupKey;
        private Integer order;
        private Integer originalOrder;
        private Integer loanRecordId;
        private List<Integer> loanRecordIds;

        public LoanDataValuesDTO() {}

        public LoanDataValuesDTO(String reportHeaderName, String value, String groupingKey, String groupKey, Integer order) {
            this.reportName = reportHeaderName;
            this.reportHeaderName = reportHeaderName;
            this.value = value;
            this.groupingKey = groupingKey;
            this.groupKey = groupKey;
            this.order = order;
            this.originalOrder = null;
        }

        public LoanDataValuesDTO(String reportName, String reportHeaderName, String value, String groupingKey, String groupKey, Integer order) {
            this.reportName = reportName;
            this.reportHeaderName = reportHeaderName;
            this.value = value;
            this.groupingKey = groupingKey;
            this.groupKey = groupKey;
            this.order = order;
            this.originalOrder = null;
        }

        public LoanDataValuesDTO(String reportName, String reportHeaderName, String value, String groupingKey, String groupKey, Integer order, Integer loanRecordId) {
            this.reportName = reportName;
            this.reportHeaderName = reportHeaderName;
            this.value = value;
            this.groupingKey = groupingKey;
            this.groupKey = groupKey;
            this.order = order;
            this.originalOrder = null;
            this.loanRecordId = loanRecordId;
        }

        public LoanDataValuesDTO(String reportName, String reportHeaderName, String value, String groupingKey, String groupKey, Integer order, List<Integer> loanRecordIds) {
            this.reportName = reportName;
            this.reportHeaderName = reportHeaderName;
            this.value = value;
            this.groupingKey = groupingKey;
            this.groupKey = groupKey;
            this.order = order;
            this.originalOrder = null;
            this.loanRecordIds = loanRecordIds;
        }

        public static class LDVBuilder {
            private final LoanDataValuesDTO instance;

            public LDVBuilder() {
                this.instance = new LoanDataValuesDTO();
            }

            public LoanDataValuesDTO build() {
                return this.instance;
            }

            public LDVBuilder reportHeaderName(String header) {
                this.instance.setReportHeaderName(header);
                return this;
            }

            public LDVBuilder value(String val) {
                this.instance.setValue(val);
                return this;
            }

            public LDVBuilder reportName(String id) {
                this.instance.setReportName(id);
                return this;
            }

            public LDVBuilder groupingKey(String gk) {
                this.instance.setGroupingKey(gk);
                return this;
            }

            public LDVBuilder groupKey(String gk) {
                this.instance.setGroupKey(gk);
                return this;
            }

            public LDVBuilder loanRecordId(Integer id) {
                this.instance.setLoanRecordId(id);
                return this;
            }

            public LDVBuilder loanRecordIds(List<Integer> ids) {
                this.instance.setLoanRecordIds(ids);
                return this;
            }

            public LDVBuilder order(int order) {
                this.instance.setOrder(order);
                return this;
            }
        }

        public static LDVBuilder builder() {
            return new LDVBuilder();
        }

/*
        public LoanDataValuesDTO(String header, String value) {
            this(header, value, null, null, null);
        }

        public LoanDataValuesDTO(String reportHeaderName, String value, int order) { this(reportHeaderName, value, null, null, order); }
*/

        @Override
        public String toString() {
            return value;
        }

        public String getReportHeaderName() {
            return reportHeaderName;
        }

        public String getValue() {
            return value;
        }

        public String getGroupingKey() {
            return groupingKey;
        }

        public String getGroupKey() {
            return groupKey;
        }

        public Integer getOrder() {
            return order;
        }

        public void setOrder(Integer order) {
            this.order = order;
        }

        public Integer getOriginalOrder() {
            return originalOrder;
        }

        public String getReportName() {
            return reportName;
        }

        public void setReportName(String reportName) {
            this.reportName = reportName;
        }

        public void setReportHeaderName(String reportHeaderName) {
            this.reportHeaderName = reportHeaderName;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public void setGroupingKey(String groupingKey) {
            this.groupingKey = groupingKey;
        }

        public void setGroupKey(String groupKey) {
            this.groupKey = groupKey;
        }

        public void setOriginalOrder(Integer originalOrder) {
            this.originalOrder = originalOrder;
        }

        public Integer getLoanRecordId() {
            return loanRecordId;
        }

        public void setLoanRecordId(Integer loanRecordId) {
            this.loanRecordId = loanRecordId;
        }

        public List<Integer> getLoanRecordIds() {
            return loanRecordIds;
        }

        public void setLoanRecordIds(List<Integer> loanRecordIds) {
            this.loanRecordIds = loanRecordIds;
        }
    }
}