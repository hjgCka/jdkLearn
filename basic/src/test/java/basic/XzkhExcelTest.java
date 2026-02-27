package basic;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Description
 * @Author hjg
 * @Date 2025-12-29 8:36
 */
public class XzkhExcelTest {

    private final String[] mxdwArray = {"总考核电量（MWh）", "总返还电量（MWh）", "净收入量（MWh）", "自然月上网电量（MWh）",
            "考核费用（万元）", "返还费用（万元）", "净收入费用（万元）", "调度纪律考核（MWh）",
            "风电（光伏）脱网考核（MWh）", "发电计划考核（MWh）", "风（光）功率预测考核（MWh）", "有功功率调节能力（MWh）",
            "低电压穿越（MWh）", "动态无功补偿（MWh）", "AVC（MWh）", "继电保护及安全自动装置（MWh）",
            "自动化设备（MWh）", "通信设备（MWh）", "电压合格率（MWh）", "AGC（MWh）",
            "网络安全管理（MWh）", "运行数据报送（MWh）"};

    private final String[] jbdwArray = {"调度纪律（万元）", "发电计划（万元）", "风功率预测考核（万元）", "自动化考核（万元）",
            "单机信息（万元）", "技术管理月报（万元）", "AVC考核（万元）", "总考核费用（万元）",
            "当月上网电量（万kW）", "考核返还费用", "两个细则结算汇总", "结算电量"};

    private final String[] mddwArray = {"非计划停运考核", "风功率预测考核及管理考核", "调度曲线考核", "一次调频考核",
            "AGC考核", "无功电压考核", "AVC考核", "安全管理考核",
            "调度管理考核", "检修管理考核", "继电保护及安全自动装置考核", "技术指导和管理考核",
            "合计"};
    private final String[][] mddwSecondArray = {
            {"罚款", "罚款返回金额", "实际罚款", "按比例分摊金额", "实际补偿金额"},
            {"罚款", "罚款返回金额", "实际罚款"}, {"罚款", "罚款返回金额", "实际罚款"}, {"罚款", "罚款返回金额", "实际罚款"},
            {"罚款", "罚款返回金额", "实际罚款"}, {"罚款", "罚款返回金额", "实际罚款"}, {"罚款", "罚款返回金额", "实际罚款"},
            {"罚款", "罚款返回金额", "实际罚款", "存在问题未按期整改罚款", "未制定事故预案罚款", "无故不参加事故演习罚款"},
            {"罚款", "罚款返回金额", "实际罚款", "违背调度令罚款", "擅自改变涉网参数罚款", "事故汇报不及时罚款"},
            {"罚款", "罚款返回金额", "实际罚款"},{"罚款", "罚款返回金额", "实际罚款"},
            {"罚款", "罚款返回金额", "实际罚款", "通信管理考核", "自动化系统及设备管理考核", "励磁和PSS管理考核", "电厂高压侧设置管理考核", "水库调度管理考核"},
            {"补偿金额（补偿+补偿分摊）", "罚款金额（罚款+罚款返回）", "净收入（元）", "结算电量", "当月上网电量（万kW）"}
    };

    @Test
    public void test() {
        int result = 0;
        for(int i=0;i<mddwSecondArray.length;i++) {
            result += mddwSecondArray[i].length;
        }

        System.out.println("result = " + result);
    }


    @Test
    public void createDwExcel() throws IOException {
        createExcel(mxdwArray, "蒙西电网","D:/sxgk/nepms/2个细则/批量下载_蒙西电网.xlsx");
        createExcel(jbdwArray, "冀北电网","D:/sxgk/nepms/2个细则/批量下载_冀北电网.xlsx");

        createMddwExcel("蒙东电网","D:/sxgk/nepms/2个细则/批量下载_蒙东电网.xlsx");
    }

    private CellStyle createBasicBorderCellStyle(Workbook wb) {
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setBorderTop(CellStyle.BORDER_THIN);
        cellStyle.setBorderBottom(CellStyle.BORDER_THIN);
        cellStyle.setBorderLeft(CellStyle.BORDER_THIN);
        cellStyle.setBorderRight(CellStyle.BORDER_THIN);

        Font font = wb.createFont();
        font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
        cellStyle.setFont(font);

        cellStyle.setAlignment(CellStyle.ALIGN_CENTER);
        cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);

        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        cellStyle.setFillPattern(CellStyle.SOLID_FOREGROUND);

        return cellStyle;
    }

    private int calculateLengthColumnWidth(String str) {
        int length = 0;
        /*for(char c : str.toCharArray()) {
            if (c >= 0x4E00 && c <= 0x9FA5) {
                length += 2;
            } else {
                length += 1;
            }
        }*/
        length = str.length() * 2;
        return length*256 + 300;
    }

    private int calculateFixedColumnWidth() {
        // 12个字符
        return 12*2*256 + 300;
    }

    private void createExcel(String[] dwArray, String sheetName, String destFile) throws IOException {
        String orgFile = "D:/sxgk/nepms/2个细则/测试文件_批量下载的模板.xlsx";

        Path path = Paths.get(orgFile);
        InputStream inputStream = Files.newInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        workbook.setSheetName(0, sheetName);

        Sheet sheet = workbook.getSheetAt(0);

        Row headerRow = sheet.getRow(0);
        Row secondRow = sheet.getRow(1);
        int startIndex = 6;

        CellStyle borderStyle = createBasicBorderCellStyle(workbook);

        for(int i=0;i<dwArray.length;i++) {
            int colNum = startIndex + i;
            Cell cell = headerRow.createCell(colNum);
            cell.setCellValue(dwArray[i]);
            cell.setCellStyle(borderStyle);

            // 设置列宽
            int columnWidth = Math.max(calculateLengthColumnWidth(dwArray[i]), calculateFixedColumnWidth());
            sheet.setColumnWidth(colNum, columnWidth);

            //第二行的单元格也要设置样式
            secondRow.createCell(colNum).setCellStyle(borderStyle);

            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 1, colNum, colNum);
            sheet.addMergedRegion(cellRangeAddress);
        }

        //冻结，导出的文件没必要加上冻结
        sheet.createFreezePane(6, 2);

        OutputStream outputStream = Files.newOutputStream(Paths.get(destFile));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }

    private int calculanteAddedIndex(int index) {
        int result = 0;
        if (index == 0) {
            return result;
        } else {
            for (int i=0;i<index;i++) {
                result += mddwSecondArray[i].length;
            }
            return result;
        }
    }

    private void createMddwExcel(String sheetName, String destFile) throws IOException {
        String orgFile = "D:/sxgk/nepms/2个细则/测试文件_批量下载的模板.xlsx";

        Path path = Paths.get(orgFile);
        InputStream inputStream = Files.newInputStream(path);
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        workbook.setSheetName(0, sheetName);

        Sheet sheet = workbook.getSheetAt(0);

        Row headerRow = sheet.getRow(0);
        Row secondRow = sheet.getRow(1);
        int startIndex = 6;

        CellStyle borderStyle = createBasicBorderCellStyle(workbook);

        int defaultColumnWidth = calculateFixedColumnWidth();
        for(int i=0;i<mddwArray.length;i++) {
            int colNum = startIndex + i;
            if (i > 0) {
                colNum = startIndex + calculanteAddedIndex(i);
            }

            Cell cell = headerRow.createCell(colNum);
            cell.setCellValue(mddwArray[i]);
            cell.setCellStyle(borderStyle);

            //设置第二行
            String[] secondArray = mddwSecondArray[i];
            for (int j=0;j<secondArray.length;j++) {
                int secondColNum = colNum + j;
                Cell secondRowCell = secondRow.createCell(secondColNum);
                secondRowCell.setCellValue(secondArray[j]);
                secondRowCell.setCellStyle(borderStyle);

                // 设置列宽
                int secondColumnWidth = Math.max(calculateLengthColumnWidth(secondArray[j]), defaultColumnWidth);
                sheet.setColumnWidth(secondColNum, secondColumnWidth);
            }

            //合并一维列
            CellRangeAddress cellRangeAddress = new CellRangeAddress(0, 0, colNum, colNum + secondArray.length - 1);
            sheet.addMergedRegion(cellRangeAddress);
        }

        //冻结，导出的文件没必要加上冻结
        sheet.createFreezePane(6, 2);

        OutputStream outputStream = Files.newOutputStream(Paths.get(destFile));
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }
}
