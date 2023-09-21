package org.example;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Objects;

public class DevVersion {
    public static void main(String[] args) {
        List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets = fourGroupTest();
        List<List<LoanDataValuesDTO>> excelData = formatMultiRowCustomReportData(multiRowCustomReportDataSets);
        int rowNum = 1;
        for (var row : excelData) {
            for (var cell : row) {
                System.out.print(cell + " | ");
                rowNum++;
            }
            System.out.println();
        }
        System.out.println("\n\nRows: " + rowNum);
    }

    // Tests
    private static LoanDataValuesDTO r1(int i) {
        return new LoanDataValuesDTO("Finding Name", "1-4 Family Rider is Missing" + i, "finding", i+"", 2);
    }
    private static List<List<List<LoanDataValuesDTO>>> originalTest() {
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
        return multiRowCustomReportDataSets;
    }
    private static List<List<List<LoanDataValuesDTO>>> oneGroupTest() {
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
        return multiRowCustomReportDataSets;
    }
    private static List<List<List<LoanDataValuesDTO>>> twoGroupTest() {
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

        multiRowCustomReportDataSets.add(standardFindingRows);
        multiRowCustomReportDataSets.add(findingHistoryRows);
        multiRowCustomReportDataSets.add(radianRows);
        multiRowCustomReportDataSets.add(dataCompareRows);
        multiRowCustomReportDataSets.add(dataCompareSummaryRows);
        return multiRowCustomReportDataSets;
    }
    private static List<List<List<LoanDataValuesDTO>>> threeGroupTest() {
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
        return multiRowCustomReportDataSets;
    }
    private static List<List<List<LoanDataValuesDTO>>> fourGroupTest() {
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
        return multiRowCustomReportDataSets;
    }
    private static List<List<List<LoanDataValuesDTO>>> realTest() {
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
        return multiRowCustomReportDataSets;
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
    private static List<List<List<LoanDataValuesDTO>>> txTest() {
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
        return multiRowCustomReportDataSets;
    }

    // Logic
    // Logic
    private static int countRows(List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets) {
        // Use a HashMap to keep track of unique groupKeys for each groupingKey
        HashMap<String, HashMap<String, Boolean>> uniqueGroupKeys = new HashMap<>();

        // Iterate over the multiRowCustomReportDataSets to populate uniqueGroupKeys
        for (List<List<LoanDataValuesDTO>> dataset : multiRowCustomReportDataSets) {
            for (List<LoanDataValuesDTO> rowGroup : dataset) {
                for (LoanDataValuesDTO row : rowGroup) {
                    String groupingKey = row.getGroupingKey();
                    String groupKey = row.getGroupKey();
                    uniqueGroupKeys.putIfAbsent(groupingKey, new HashMap<>());
                    uniqueGroupKeys.get(groupingKey).put(groupKey, true);
                }
            }
        }

        // Calculate the total number of rows by multiplying the counts of unique groupKeys for each groupingKey
        int totalRows = 1;
        for (var entry : uniqueGroupKeys.entrySet()) {
            totalRows *= entry.getValue().size();
        }

        return totalRows;
    }
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    private record HeaderKey(String groupKey, ColumnHeader header) {
        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            HeaderKey headerKey = (HeaderKey) o;

            if (!Objects.equals(groupKey, headerKey.groupKey)) return false;
            return Objects.equals(header, headerKey.header);
        }

        @Override
        public int hashCode() {
            int result = groupKey != null ? groupKey.hashCode() : 0;
            result = 31 * result + (header != null ? header.hashCode() : 0);
            return result;
        }
    }
    private record ColumnHeader(String identifier, String value, int order, String groupingKey) implements Comparable<ColumnHeader> {

        @Override
        public int compareTo(ColumnHeader o) {
            return Comparator.comparing(ColumnHeader::order).compare(this, o);
        }
    }
    private record GroupKeyValue(String identifier, String groupingKey, String key, String value, int order, String header) implements Comparable<GroupKeyValue> {
        @Override
        public int compareTo(GroupKeyValue o) {
            return Comparator.comparing(GroupKeyValue::order).compare(this, o);
        }
    }
    private static List<List<LoanDataValuesDTO>> groupSortMultiRowReportData(List<List<List<LoanDataValuesDTO>>> data) {
        List<List<List<LoanDataValuesDTO>>> removeEmptiesTemporarily = new ArrayList<>();
        List<List<List<LoanDataValuesDTO>>> noEmptiesData = new ArrayList<>();
        for (var list : data) {
            if (list.stream().anyMatch(d -> d.stream().anyMatch(c -> c.getGroupKey() == null || c.getGroupKey().trim().isEmpty()))) {
                removeEmptiesTemporarily.add(list);
            } else {
                noEmptiesData.add(list);
            }
        }
        data = noEmptiesData;
        int outputIndex = 0;
        List<List<LoanDataValuesDTO>> output = new ArrayList<>();
        HashSet<ColumnHeader> headers = new HashSet<>();
        HashSet<ColumnHeader> emptyHeaders = new HashSet<>();
        LinkedHashSet<String> matchKeyCheck = new LinkedHashSet<>();
        LinkedHashSet<GroupKeyValue> groupKeyValues = new LinkedHashSet<>();
        HashMap<String, LinkedHashSet<String>> uniqueKeysByGroupingKey = new HashMap<>();
        HashMap<String, String> resetGroupKeys = new HashMap<>();
        HashMap<String, String> currentGroupKeyForGroupingKey = new HashMap<>();
        HashMap<String, String> correctCurrentGroupKeyForGroupingKey = new HashMap<>();
        HashMap<String, Integer> currentIterationIndexOfHeader = new HashMap<>();
        HashMap<String, Integer> uniqueKeysCountIterationMap = new HashMap<>();

        for (var list : data) {
            for (var row : list) {
                for (var cell : row) {
                    matchKeyCheck.add(cell.getGroupingKey());
                    groupKeyValues.add(new GroupKeyValue(cell.getReportName(), cell.getGroupingKey(), cell.getGroupKey(), cell.getValue(), cell.getOrder(), cell.getReportHeaderName()));
                    headers.add(new ColumnHeader(cell.getReportName(), cell.getReportHeaderName(), cell.getOrder(), cell.getGroupingKey()));
                }
            }
        }
        for (var list : removeEmptiesTemporarily) {
            for (var row : list) {
                for (var cell : row) {
                    emptyHeaders.add(new ColumnHeader(cell.getReportName(), cell.getReportHeaderName(), cell.getOrder(), cell.getGroupingKey()));
                }
            }
        }

        List<ColumnHeader> sorted = headers.stream().sorted().toList();
        List<ColumnHeader> sortedEmpty = emptyHeaders.stream().sorted().toList();
        List<GroupKeyValue> sortedGroupKeys = groupKeyValues.stream().sorted().toList();

        for (var key : sortedGroupKeys) {
            if (key.key() != null && !key.value().trim().isEmpty()) {
                LinkedHashSet<String> list = uniqueKeysByGroupingKey.getOrDefault(key.groupingKey(), new LinkedHashSet<>());
                list.add(key.key());
                uniqueKeysByGroupingKey.put(key.groupingKey(), list);
                uniqueKeysCountIterationMap.put(key.groupingKey(), list.size());
            }
            currentIterationIndexOfHeader.put(key.identifier(), 0);

        }
        for (var entry : uniqueKeysByGroupingKey.entrySet()) {
            String gk = "";
            for (var s : entry.getValue()) {
                gk = s;
                break;
            }
            resetGroupKeys.put(entry.getKey(), gk);
        }
        for (var key : sortedGroupKeys) {
            correctCurrentGroupKeyForGroupingKey.put(key.groupingKey(), resetGroupKeys.get(key.groupingKey()));
        }


        List<String> matchKeys = matchKeyCheck.stream().toList();
        String currentMatchKey = matchKeys.get(0);
        int numberOfRowsToRolloverAfter = uniqueKeysByGroupingKey.get(currentMatchKey).size();
        int rolloverRows = 0;

        int rows = countRows(data);
        int columns = sorted.size();
        HashSet<HeaderKey> alreadyCheckedForRollover = new HashSet<>();
        for (int rowIndex = 0; rowIndex < rows; rowIndex++) {
            List<LoanDataValuesDTO> row = new ArrayList<>();
            for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
                ColumnHeader header = sorted.get(columnIndex);
                String grouping = header.groupingKey();
                List<GroupKeyValue> matchingGroupingKeys = groupKeyValues.stream().filter(g -> g.groupingKey().equalsIgnoreCase(grouping)).toList();
                boolean allIntKeys = matchingGroupingKeys.stream().allMatch(k -> isInteger(k.key()));
                Comparator<GroupKeyValue> sortFunc = allIntKeys ? Comparator.comparingInt(item -> Integer.parseInt(item.key())) : Comparator.comparing(GroupKeyValue::key);
                List<GroupKeyValue> groupMatches = matchingGroupingKeys.stream()
                        //.sorted(sortFunc)
                        .filter(k -> k.identifier().equalsIgnoreCase(header.identifier()) && k.header().equalsIgnoreCase(header.value()))
                        .toList();

                String newKey = correctCurrentGroupKeyForGroupingKey.get(header.groupingKey());
                while (alreadyCheckedForRollover.contains(new HeaderKey(newKey, header)) && alreadyCheckedForRollover.size() < uniqueKeysByGroupingKey.values().stream().map(HashSet::size).mapToInt(Integer::intValue).sum() * data.size()) {
                    incrementHeader(header.groupingKey(), header, currentIterationIndexOfHeader, correctCurrentGroupKeyForGroupingKey, uniqueKeysByGroupingKey, resetGroupKeys);
                    newKey = correctCurrentGroupKeyForGroupingKey.get(header.groupingKey());
                    System.out.println("Looping\n\n");
                }

                boolean haventSeenHeaderKey = groupMatches.stream().anyMatch(g -> g.key().equalsIgnoreCase(currentGroupKeyForGroupingKey.getOrDefault(header.groupingKey(), null)));
                boolean outOfHeaderKey = currentIterationIndexOfHeader.get(header.identifier()) >= groupMatches.size();
                boolean matchingCorrectKey = haventSeenHeaderKey && groupMatches.stream().anyMatch(g -> correctCurrentGroupKeyForGroupingKey.get(header.groupingKey()).equalsIgnoreCase(g.key()));
                boolean matchingCurrentKey = haventSeenHeaderKey && groupMatches.stream().anyMatch(g -> currentGroupKeyForGroupingKey.get(header.groupingKey()).equalsIgnoreCase(g.key()));
                if (matchingCorrectKey || matchingCurrentKey) {
                    HashMap<String, String> matchedKeys = matchingCurrentKey ? currentGroupKeyForGroupingKey : correctCurrentGroupKeyForGroupingKey;
                    var matchingKeyEntries = groupMatches.stream().filter(g -> matchedKeys.get(header.groupingKey()).equalsIgnoreCase(g.key())).toList();
                    var l = matchingKeyEntries.get(0);
                    row.add(LoanDataValuesDTO.builder().reportHeaderName(l.header()).value(l.value()).groupingKey(l.groupingKey()).groupKey(l.key()).order(l.order()).build());
                    incrementHeader(currentMatchKey, header, currentIterationIndexOfHeader, correctCurrentGroupKeyForGroupingKey, uniqueKeysByGroupingKey, resetGroupKeys);
                    alreadyCheckedForRollover.add(new HeaderKey(l.key(), header));
                    continue;
                } else if (outOfHeaderKey) {
                    row.add(LoanDataValuesDTO.builder().reportHeaderName(header.value()).value("").groupingKey(header.groupingKey()).order(header.order()).build());
                    newKey = correctCurrentGroupKeyForGroupingKey.get(header.groupingKey());
                    alreadyCheckedForRollover.add(new HeaderKey(newKey, header));
                    currentGroupKeyForGroupingKey.put(header.groupingKey(), newKey);
                    uniqueKeysCountIterationMap.compute(header.groupingKey(), (k, v) -> v == null ? 0 : v - 1);
                    if (uniqueKeysCountIterationMap.get(header.groupingKey()) < 1) {
                        resetHeader(header, currentIterationIndexOfHeader, correctCurrentGroupKeyForGroupingKey, resetGroupKeys);
                    }
                    continue;
                }

                int currentMatchIndex = currentIterationIndexOfHeader.get(header.identifier());
                var currentMatch = groupMatches.get(currentMatchIndex);

                if (!currentGroupKeyForGroupingKey.containsKey(header.groupingKey())) {
                    currentGroupKeyForGroupingKey.put(header.groupingKey(), currentMatch.key());
                    row.add(LoanDataValuesDTO.builder().reportHeaderName(currentMatch.header()).value(currentMatch.value()).groupingKey(currentMatch.groupingKey()).groupKey(currentMatch.key()).order(currentMatch.order()).build());
                    incrementHeader(currentMatchKey, header, currentIterationIndexOfHeader, correctCurrentGroupKeyForGroupingKey, uniqueKeysByGroupingKey, resetGroupKeys);
                    alreadyCheckedForRollover.add(new HeaderKey(currentMatch.key(), header));
                } else if (groupMatches.stream().anyMatch(g -> currentGroupKeyForGroupingKey.get(header.groupingKey()).equalsIgnoreCase(g.key()))) {
                    var matchingKeyEntries = groupMatches.stream().filter(g -> currentGroupKeyForGroupingKey.get(header.groupingKey()).equalsIgnoreCase(g.key())).toList();
                    var l = matchingKeyEntries.get(0);
                    row.add(LoanDataValuesDTO.builder().reportHeaderName(l.header()).value(l.value()).groupingKey(l.groupingKey()).groupKey(l.key()).order(l.order()).build());
                    alreadyCheckedForRollover.add(new HeaderKey(l.key(), header));
                    incrementHeader(currentMatchKey, header, currentIterationIndexOfHeader, correctCurrentGroupKeyForGroupingKey, uniqueKeysByGroupingKey, resetGroupKeys);
                } else {
                    row.add(LoanDataValuesDTO.builder().reportHeaderName(currentMatch.header()).value("").groupingKey(currentMatch.groupingKey()).groupKey(currentMatch.key()).order(currentMatch.order()).build());
                    alreadyCheckedForRollover.add(new HeaderKey(currentMatch.key(), header));
                }
            }
            rolloverRows++;
            if (rolloverRows == numberOfRowsToRolloverAfter) {
                alreadyCheckedForRollover.clear();
                rolloverRows = 0;
                rollover(currentMatchKey, matchKeys, sorted, uniqueKeysCountIterationMap, currentIterationIndexOfHeader, uniqueKeysByGroupingKey, correctCurrentGroupKeyForGroupingKey, resetGroupKeys);
            }
            currentGroupKeyForGroupingKey.clear();
            output.add(outputIndex, new ArrayList<>(row));
            outputIndex++;
        }
        for (var list : output) {
            int totalHeadersSize = sortedEmpty.size() + sorted.size();
            if (list.size() < totalHeadersSize) {
                int difference = totalHeadersSize - list.size();
                for (int i = 0; i < difference && i < sortedEmpty.size(); i++) {
                    var empty = sortedEmpty.get(i);
                    list.add(LoanDataValuesDTO.builder().value("").reportName(empty.identifier()).reportHeaderName(empty.value()).groupingKey(empty.groupingKey()).order(empty.order()).build());
                    list.sort(Comparator.comparing(LoanDataValuesDTO::getOrder));
                }
            }
        }
        return output;
    }
    private static void resetHeader(ColumnHeader header,
                                    HashMap<String, Integer> currentIterationIndexOfHeader,
                                    HashMap<String, String> correctCurrentGroupKeyForGroupingKey,
                                    HashMap<String, String> resetGroupKeys) {
        currentIterationIndexOfHeader.put(header.identifier(), 0);
        correctCurrentGroupKeyForGroupingKey.put(header.groupingKey(), resetGroupKeys.get(header.groupingKey()));
    }
    private static void incrementHeader(String currentMatchKey,
                                        ColumnHeader header,
                                        HashMap<String, Integer> currentIterationIndexOfHeader,
                                        HashMap<String, String> correctCurrentGroupKeyForGroupingKey,
                                        HashMap<String, LinkedHashSet<String>> uniqueKeysByGroupingKey,
                                        HashMap<String, String> resetGroupKeys) {
        if (currentMatchKey.equalsIgnoreCase(header.groupingKey())) {
            currentIterationIndexOfHeader.compute(header.identifier(), (k, v) -> v == null || v == 0 ? 1 : v + 1);
            incrementCorrectGroupKey(header, currentIterationIndexOfHeader, correctCurrentGroupKeyForGroupingKey, uniqueKeysByGroupingKey, resetGroupKeys);
        }
    }
    private static void incrementCorrectGroupKey(ColumnHeader header,
                                                 HashMap<String, Integer> currentIterationIndexOfHeader,
                                                 HashMap<String, String> correctCurrentGroupKeyForGroupingKey,
                                                 HashMap<String, LinkedHashSet<String>> uniqueKeysByGroupingKey,
                                                 HashMap<String, String> resetGroupKeys) {
        int newIndex = currentIterationIndexOfHeader.get(header.identifier());
        int counter = 0;
        String groupKey = null;
        for (var s : uniqueKeysByGroupingKey.get(header.groupingKey())) {
            if (counter == newIndex) {
                groupKey = s;
                break;
            }
            counter++;
        }
        if (groupKey != null) {
            correctCurrentGroupKeyForGroupingKey.put(header.groupingKey(), groupKey);
        } else {
            correctCurrentGroupKeyForGroupingKey.put(header.groupingKey(), resetGroupKeys.get(header.groupingKey()));
        }
    }
    private static void rollover(String currentMatchKey,
                                 List<String> matchKeys,
                                 List<ColumnHeader> sorted,
                                 HashMap<String, Integer> uniqueKeysCountIterationMap,
                                 HashMap<String, Integer> currentIterationIndexOfHeader,
                                 HashMap<String, LinkedHashSet<String>> uniqueKeysByGroupingKey,
                                 HashMap<String, String> correctCurrentGroupKeyForGroupingKey,
                                 HashMap<String, String> resetGroupKeys) {
        for (String key : matchKeys) {
            if (key.equalsIgnoreCase(currentMatchKey)) {
                for (ColumnHeader header : sorted) {
                    if (header.groupingKey().equalsIgnoreCase(key)) {
                        //currentIterationIndexOfHeader.put(header.identifier(), 0);
                        resetHeader(header, currentIterationIndexOfHeader, correctCurrentGroupKeyForGroupingKey, resetGroupKeys);
                    }
                }
            } else {
                uniqueKeysCountIterationMap.compute(key, (k, v) -> v == null ? 0 : v - 1);
                if (uniqueKeysCountIterationMap.get(key) >= 1) {
                    for (ColumnHeader header : sorted) {
                        if (header.groupingKey().equalsIgnoreCase(key)) {
                            incrementHeader(header.groupingKey(), header, currentIterationIndexOfHeader, correctCurrentGroupKeyForGroupingKey, uniqueKeysByGroupingKey, resetGroupKeys);
                        }
                    }
                    break;
                } else {
                    int resetSize = uniqueKeysByGroupingKey.get(key).size();
                    uniqueKeysCountIterationMap.put(key, resetSize);
                    for (ColumnHeader header : sorted) {
                        if (header.groupingKey().equalsIgnoreCase(key)) {
                            resetHeader(header, currentIterationIndexOfHeader, correctCurrentGroupKeyForGroupingKey, resetGroupKeys);
                        }
                    }
                }
            }
        }
    }

    // Extras
    private static List<List<LoanDataValuesDTO>> formatMultiRowCustomReportData(List<List<List<LoanDataValuesDTO>>> multiRowCustomReportDataSets) {
        return groupSortMultiRowReportData(multiRowCustomReportDataSets);
    }
    private static class LoanDataValuesDTO {
        private String reportName;
        private String reportHeaderName;
        private String value;
        private String groupingKey;
        private String groupKey;
        private Integer order;
        private Integer originalOrder;

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
    }
}