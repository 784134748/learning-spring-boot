package com.yalonglee.learning.core.utils;

import com.google.common.collect.Maps;
import com.lowagie.text.Font;
import com.lowagie.text.Image;
import com.lowagie.text.Rectangle;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.apache.commons.lang3.StringUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;

public class PdfUtil {

    public static void main(String[] args) throws Exception {
        createPDF();

    }

    /**
     * 创建PDF文档
     *
     * @return
     * @throws Exception
     */
    public static String createPDF() throws Exception {

        //输出路径
        String outPath = "/Users/yalonglee/Desktop/abc.pdf";
        //设置纸张
        Rectangle rect = new Rectangle(PageSize.A4);
        //创建文档实例
        Document doc = new Document(rect);
        //创建输出流
        PdfWriter.getInstance(doc, new FileOutputStream(new File(outPath)));

        doc.open();
        doc.newPage();

        createImage(doc);

        createTitle(doc, "商品信息");
        LinkedHashMap<String, String> merchandiseInfo = Maps.newLinkedHashMap();
        merchandiseInfo.put("供应商", "温州庄吉有限公司");
        merchandiseInfo.put("款式名称", "欧式立体条纹衬衫");
        merchandiseInfo.put("供应商编号", "1234567890123456");
        merchandiseInfo.put("面料编号", "ML00001");
        merchandiseInfo.put("款式品类", "YP1001");
        merchandiseInfo.put("面料颜色", "卡其色");
        createTableForSeveralColumns(doc, 2, merchandiseInfo);

        createTitle(doc, "个性设计");
        LinkedHashMap<String, String> individualizationInfo = Maps.newLinkedHashMap();
        individualizationInfo.put("工艺", "常规半毛衬");
        individualizationInfo.put("手工", "部位手工");
        individualizationInfo.put("领型", "平驳领");
        individualizationInfo.put("前门扣", "单排一粒扣");
        individualizationInfo.put("驳领宽", "驳领宽7CM");
        individualizationInfo.put("后衩", "单开衩");
        individualizationInfo.put("上口袋", "2.3CM直手巾袋");
        individualizationInfo.put("下口袋", "斜1.2CM一字袋");
        individualizationInfo.put("卡袋", "无卡袋");
        individualizationInfo.put("挂面", "直挂面");
        individualizationInfo.put("袖衩", "假眼假衩钉扣");
        individualizationInfo.put("袖扣形状", "直角袖口");
        individualizationInfo.put("领角", "常规领角");
        individualizationInfo.put("半理/全量", "全量");
        individualizationInfo.put("半里形状", "空");
        individualizationInfo.put("半里工艺", "空");
        individualizationInfo.put("机票袋", "无");
        individualizationInfo.put("里袋扣", "右里袋三角里布锁眼钉扣");
        individualizationInfo.put("香水垫样式", "三角里料香水垫");
        individualizationInfo.put("笔袋", "左双开线笔袋");
        individualizationInfo.put("MP3袋", "无");
        individualizationInfo.put("领型描述", "不分割");
        createTableForSeveralColumns(doc, 4, individualizationInfo);

        createTitle(doc, "深度设计");
        LinkedHashMap<String, String> deepInfo = Maps.newLinkedHashMap();
        deepInfo.put("拱针", "0.15CM");
        deepInfo.put("拱针位置", "领子.门禁.手巾袋.袖衩");
        deepInfo.put("拱针线颜色", "顺色");
        deepInfo.put("插花眼形状", "机器方头假眼");
        deepInfo.put("插花眼颜色", "顺色");
        deepInfo.put("门禁纽扣", "FK3132BK");
        deepInfo.put("袖纽扣", "FK3132BK");
        deepInfo.put("大身里布", "FB250990");
        deepInfo.put("袖里布", "同大身里布");
        deepInfo.put("订品牌标位置", "机器订商标（右里袋下）");
        deepInfo.put("订面料标位置", "订左里袋上2CM");
        deepInfo.put("袖纽扣个数", "四扣");
        deepInfo.put("袖纽扣间距", "平扣");
        deepInfo.put("袖扣眼方向", "平");
        deepInfo.put("领标", "订领丝带");
        createTableForSeveralColumns(doc, 4, deepInfo);

        createTitle(doc, "高级设计");
        LinkedHashMap<String, String> seniorInfo = Maps.newLinkedHashMap();
        seniorInfo.put("珠边/嵌条", "0.15CM");
        seniorInfo.put("珠边颜色", "顺色");
        seniorInfo.put("嵌条颜色", "空");
        seniorInfo.put("订纽扣样式", "机器普通钉扣");
        seniorInfo.put("订纽扣颜色", "顺色");
        seniorInfo.put("门襟锁眼", "机器锁眼");
        seniorInfo.put("门襟全扣眼颜色", "顺色");
        seniorInfo.put("袖口锁眼", "机器锁眼");
        seniorInfo.put("袖口全扣眼颜色", "顺色");
        seniorInfo.put("订洗唛位置", "右边里袋");
        createTableForSeveralColumns(doc, 4, seniorInfo);

        createTitle(doc, "定制尺寸");
        LinkedHashMap<String, String> sizeInfo = Maps.newLinkedHashMap();
        sizeInfo.put("-", "参考尺寸;成品尺寸");
        sizeInfo.put("参考型号", "180/92A;");
        sizeInfo.put("前衣长(成)", "10.00;10.00");
        sizeInfo.put("后衣长", "10.00;10.00");
        sizeInfo.put("首扣距离", "10.00;10.00");
        sizeInfo.put("肩宽", "10.00;10.00");
        sizeInfo.put("袖长", "10.00;10.00");
        sizeInfo.put("胸围", "10.00;10.00");
        sizeInfo.put("中腰", "10.00;10.00");
        sizeInfo.put("下摆", "10.00;10.00");
        sizeInfo.put("年龄段", "10.00;10.00");
        sizeInfo.put("后腰节长", "10.00;10.00");
        sizeInfo.put("颈围", "10.00;10.00");
        sizeInfo.put("后背宽(成)", "10.00;10.00");
        sizeInfo.put("颈围(净)", "10.00;10.00");
        createSize(doc, sizeInfo);

        doc.close();
        return outPath;
    }

    /**
     * 创建标题
     *
     * @param doc
     */
    private static void createTitle(Document doc, String contentStr) throws IOException {

        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //正常
        Font textFont = new Font(bfChinese, 15, Font.NORMAL);

        //段落（标题）
        Paragraph title = new Paragraph();
        //短语（标题内容）
        Phrase content = new Phrase();
        //块（标题内容信息）
        Chunk contentInfo = new Chunk(contentStr, textFont);

        //缩进
        title.setIndentationLeft(-10.00F);
        //字体间距
        title.setSpacingBefore(20.00F);
        title.setSpacingAfter(10.00F);
        //边距

        content.add(contentInfo);
        title.add(content);
        doc.add(title);
    }

    /**
     * 创建尺寸
     *
     * @param doc
     * @param contents
     * @throws IOException
     */
    private static void createSize(Document doc, LinkedHashMap<String, String> contents) throws IOException {
        float maxWidth = 480.00F;
        float[] columns = getColumnWidths(contents);

        //单行单元格的内容
        LinkedHashMap<String, String> partContents = Maps.newLinkedHashMap();

        //出宽度预留8.00F给尾部
        float widths = 8.00F;
        Iterator iterator = contents.keySet().iterator();
        int index = 0;
        int sum = 0;
        for (float column : columns) {
            if ((widths > maxWidth) || sum + 1 == contents.size()) {
                //达到最大长度时先处理后重置
                //处理
                //单行单元格的宽度
                float[] partColumns = new float[index + 1];
                for (int i = 0; i < index; i++) {
                    partColumns[i] = columns[sum - index + i];
                }
                //尾部
                partColumns[index] = 8.00F;
                partContents.put("", ";");
                if (sum > index) {
                    createTitle(doc, "");
                }
                createTableForSeveralColumnsAuto(doc, partColumns, partContents);
                //重置
                index = 0;
                widths = 8.00F;
                partContents.clear();
            }
            widths += column;
            String key = (String) iterator.next();
            partContents.put(key, contents.get(key));
            index += 1;
            sum += 1;
        }
    }

    /**
     * 创建图片
     */
    private static void createImage(Document doc) throws IOException {

        Image image = Image.getInstance("/Users/yalonglee/Desktop/二维码.png");
        //图片大小
        image.scaleToFit(100.00F, 100.00F);
        //图片位置
        image.setAbsolutePosition(450.00F, 670.00F);

        doc.add(image);
    }

    /**
     * 固定布局
     *
     * @param contents
     * @throws IOException
     */
    private static void createTableForSeveralColumns(Document doc, int numColumns, LinkedHashMap<String, String> contents) throws IOException {

        //定义表格宽度
        float[] twoColumns = new float[]{260, 260};
        float[] threeColumns = new float[]{180, 180, 180};
        float[] fourColumns = new float[]{140, 140, 140, 140};
        //最大字符长度
        int length = 12;

        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //正常
        Font textFont = new Font(bfChinese, 10, Font.NORMAL);

        //段落（属性键值对）
        Paragraph size = new Paragraph();
        //缩进
        size.setIndentationLeft(0.00F);

        // 创建一个有4列的表格
        PdfPTable table = new PdfPTable(numColumns);
        table.setHorizontalAlignment(Cell.ALIGN_LEFT);
        //设置列宽
        switch (numColumns) {
            case 2:
                table.setTotalWidth(twoColumns);
                length = 22;
                break;
            case 3:
                table.setTotalWidth(threeColumns);
                length = 16;
                break;
            case 4:
                table.setTotalWidth(fourColumns);
                length = 12;
                break;
            default:
                break;

        }
        //锁定列宽
        table.setLockedWidth(true);

        PdfPCell cell;
        for (String key : contents.keySet()) {
            //短语（标题内容）
            Phrase content = new Phrase();
            //块（标题内容信息）
            Chunk keyStr = new Chunk(key + "：", textFont);
            Chunk valueStr = new Chunk(contents.get(key), textFont);
            if (contents.get(key).length() + key.length() > length) {
                valueStr.setTextRise(8.00F);
            }

            content.add(keyStr);
            content.add(valueStr);

            cell = new PdfPCell();
            cell.setNoWrap(true);
            cell.setBorder(0);
            cell.setFixedHeight(25.00F);
            //设置可以居中
            cell.setUseAscender(true);
            //设置水平居右
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
            //设置垂直居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cell.addElement(content);
            table.addCell(cell);
        }
        //补足单元格
        int remainder = contents.size() % numColumns;
        for (int i = 0; i < numColumns - remainder; i++) {
            //短语（标题内容）
            Phrase content = new Phrase();
            //块（标题内容信息）
            Chunk contentInfo = new Chunk("", textFont);
            Chunk contentInfo2 = new Chunk("", textFont);

            content.add(contentInfo);
            content.add(contentInfo2);

            cell = new PdfPCell();
            cell.setBorder(0);
            cell.setFixedHeight(25.00F);
            //设置可以居中
            cell.setUseAscender(true);
            //设置水平居右
            cell.setHorizontalAlignment(Cell.ALIGN_LEFT);
            //设置垂直居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            cell.addElement(content);
            table.addCell(cell);
        }

        size.add(table);
        doc.add(size);
    }

    /**
     * 自动布局
     *
     * @param columns
     * @param contents
     * @throws IOException
     */
    private static void createTableForSeveralColumnsAuto(Document doc, float[] columns, LinkedHashMap<String, String> contents) throws IOException {

        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //正常
        Font textFont = new Font(bfChinese, 10, Font.NORMAL);
        //斜体
        Font italicFont = new Font(bfChinese, 10, Font.ITALIC);

        //段落（属性键值对）
        Paragraph size = new Paragraph();
        //缩进
        size.setIndentationLeft(0.00F);

        // 创建表格
        PdfPTable table = new PdfPTable(contents.size());
        table.setHorizontalAlignment(Cell.ALIGN_LEFT);
        //设置列宽
        table.setTotalWidth(columns);
        //锁定列宽
        table.setLockedWidth(true);

        PdfPCell cell;
        int index = 0;
        for (String key : contents.keySet()) {
            //短语（标题内容）
            Phrase content = null;
            //第一个是斜体
            if (index == 0) {
                content = new Phrase(key, italicFont);
            } else {
                content = new Phrase(key, textFont);
            }
            cell = new PdfPCell(content);
            cell.setBorder(0);
            if (StringUtils.isNoneBlank(key)) {
                cell.setBorderWidthRight(0.01F);
            }
            cell.setFixedHeight(25.00F);
            //设置可以居中
            cell.setUseAscender(true);
            //设置水平居右
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
            //设置垂直居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            table.addCell(cell);
            index++;
        }

        for (String key : contents.keySet()) {
            //短语（标题内容）
            Phrase content = new Phrase(contents.get(key).substring(0, contents.get(key).indexOf(";")), textFont);

            cell = new PdfPCell(content);
            cell.setBorder(1);
            if (StringUtils.isNoneBlank(key)) {
                cell.setBorderWidthRight(0.01F);
            }
            cell.setBorderWidthTop(1.30F);
            cell.setFixedHeight(15.00F);
            //设置可以居中
            cell.setUseAscender(true);
            //设置水平居右
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
            //设置垂直居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            table.addCell(cell);
        }

        for (String key : contents.keySet()) {
            //短语（标题内容）
            Phrase content = new Phrase(contents.get(key).substring(contents.get(key).indexOf(";") + 1), textFont);

            cell = new PdfPCell(content);
            cell.setBorder(1);
            if (StringUtils.isNoneBlank(key)) {
                cell.setBorderWidthRight(0.01F);
            }
            cell.setFixedHeight(15.00F);
            //设置可以居中
            cell.setUseAscender(true);
            //设置水平居右
            cell.setHorizontalAlignment(Cell.ALIGN_CENTER);
            //设置垂直居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            table.addCell(cell);
        }
        size.add(table);
        doc.add(size);
    }

    /**
     * 获取字符串宽度
     *
     * @param contents
     * @return
     */
    private static float[] getColumnWidths(LinkedHashMap<String, String> contents) {
        //定义表格宽度
        float[] columns = new float[contents.size()];
        BufferedImage bufferedImage = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
        Graphics2D textG = bufferedImage.createGraphics();
        FontMetrics fm = textG.getFontMetrics();
        int i = 0;
        for (String key : contents.keySet()) {
            //最后一行留出空间
            if (StringUtils.isBlank(key)) {
                columns[i] = 8;
                i++;
                continue;
            }
            int keyLength = fm.stringWidth(key);
            int valueLength1 = fm.stringWidth(contents.get(key).substring(0, contents.get(key).indexOf(";")));
            int valueLength2 = fm.stringWidth(contents.get(key).substring(contents.get(key).indexOf(";") + 1));
            if (keyLength > valueLength1) {
                columns[i] = keyLength;
            } else {
                columns[i] = valueLength1;
            }
            if (valueLength2 > columns[i]) {
                columns[i] = valueLength2;
            }
            //限制宽度下限
            if (columns[i] < 36) {
                columns[i] = 36;
            }
            i++;
        }
        return columns;
    }

}
