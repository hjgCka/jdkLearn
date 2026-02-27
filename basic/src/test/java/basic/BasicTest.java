package basic;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description
 * @Author hjg
 * @Date 2025-01-19 18:58
 */
public class BasicTest {

    public static final String ORGID = "NMG";
    public static final String DOMAINID = "SXMN_DOC_CATEGORY";

    @Test
    public void test() {
        System.out.println("hello");
    }

    @Test
    public void validExcelToSql() throws IOException {
        String originalFile = "D:/sxgk/工作需求文档/三峡蒙能文件类别_风电.sql";
        List<String> list = Files.readAllLines(Paths.get(originalFile));
        System.out.println(list.size());
        list.stream().forEach(System.out::println);
    }

    @Test
    public void parseExcel() throws IOException {
        String originalFile = "D:/sxgk/工作需求文档/三峡蒙能文件类别.xlsx";
        String destFile = "D:/sxgk/工作需求文档/维护三峡蒙能文件类别-v2.sql";
        try(InputStream inputStream = Files.newInputStream(Paths.get(originalFile));
            OutputStream outputStream = Files.newOutputStream(Path.of(destFile));
            OutputStreamWriter osw = new OutputStreamWriter(outputStream,StandardCharsets.UTF_8.name());
            BufferedWriter br = new BufferedWriter(osw);
        ) {
            //2007
            Workbook workbook = new XSSFWorkbook(inputStream);

            StringBuilder sb = new StringBuilder();
            sb.append("insert into ALNDOMAIN(DOMAINID, VALUE, DESCRIPTION, SITEID, ORGID, ALNDOMAINID, VALUEID) VALUES \n");

            Integer START_ALNDOMAINID_INDEX = 28200;

            sb.append(parseSheet(workbook, 0, "FDZ", 167, START_ALNDOMAINID_INDEX));
            sb.append(parseSheet(workbook, 1, "GFDZ", 394, START_ALNDOMAINID_INDEX+167-1));
            sb.append(parseSheet(workbook, 2, "CNDZ", 145, START_ALNDOMAINID_INDEX+167+394-2));
            sb.append(parseSheet(workbook, 3, "GRDZ", 141, START_ALNDOMAINID_INDEX+167+394+145-3));

            br.write(sb.toString());
        }
    }

    public StringBuilder parseSheet(Workbook workbook, int sheetIndex, String siteType, int maxRowIndex, Integer alndomainIdIndex) {
        int count = 0;
        StringBuilder sb = new StringBuilder();

        Sheet sheet = workbook.getSheetAt(sheetIndex);
        int startIndex = 1;
        DataFormatter formatter = new DataFormatter();
        for (int i = startIndex; i<maxRowIndex; i++) {
            Row row = sheet.getRow(i);
            boolean isHidden = row.getZeroHeight();
            if (isHidden) {
                System.out.println("row num = " + i);
                continue;
            }

            String value = formatter.formatCellValue(row.getCell(0)) + "-" + formatter.formatCellValue(row.getCell(1));
            int lastIndex = value.lastIndexOf("-");
            String parentId = value.substring(0, lastIndex);

            String desc = formatter.formatCellValue(row.getCell(3));
            desc = desc.replace("\n", "").replace("\r", "");

            sb.append("    (")
                        .append("'").append(DOMAINID).append("'").append(",")
                        .append("'").append(value).append("'").append(",")
                        .append("'").append(desc).append("'").append(",")
                        .append("'").append(parentId).append("'").append(",")
                        .append("'").append(ORGID).append("'").append(",")
                        .append(alndomainIdIndex++).append(",")
                        .append("'").append(DOMAINID).append("|").append(value).append("'")
                    .append("), ").append("\n");

            count++;
        }

        System.out.println(siteType + " 行数：" + count);

        return sb;
    }

    @Test
    public void parseSql() {
        String userid = "hu_jiangang";
        StringBuilder sql = new StringBuilder("  select*from ( select site.* ,(select DESCRIPTION from ORGANIZATION where ORGID = SITE.ORGID) as ORGDES" +
                " from site where siteid in( select siteid from SITEAUTH where groupname " +
                "  in( select groupname From groupuser where userid in( select userid From maxuser where loginid='"+userid+"'))) " +
                "  or (select count(1) allcount From maxgroup  maxg inner join  (select groupname From groupuser  " +
                "  where userid in( select userid From maxuser where loginid='"+userid+"')) ug " +
                "  on maxg.groupname=ug.groupname and maxg.authallsites=1)>0  ) st ");

        //功能优化-组合查询
        String sitedesParam = "安徽涡阳";
        List<String> conditions = new ArrayList<>();
        conditions.add("DESCRIPTION LIKE '%" + sitedesParam + "%'");

        if (!conditions.isEmpty()) {
            sql.append(" WHERE ").append(String.join(" AND ", conditions));
        }

        System.out.println(sql);
    }
}
