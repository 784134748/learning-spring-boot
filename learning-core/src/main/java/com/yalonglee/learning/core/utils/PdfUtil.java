package com.yalonglee.learning.core.utils;

import com.google.common.collect.Lists;
import com.lowagie.text.*;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

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

        createTitle(doc, "");
        createTitle(doc, "");
        createTitle(doc, "商品信息");
        List<String> list = Lists.newArrayList();
        list.add("供应商：温州庄吉有限公司");
        list.add("款式名称：欧式立体条纹衬衫");
        createKV(doc, list);
        List<String> list1 = Lists.newArrayList();
        list1.add("供应商编号：1234567890");
        list1.add("面料编号：ML00001");
        createKV(doc, list1);
        List<String> list2 = Lists.newArrayList();
        list2.add("款式品类：YP1001");
        list2.add("面料颜色：卡其色");
        createKV(doc, list2);
        createImage(doc);
        createTable(doc);

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
        //加粗
        Font boldFont = new Font(bfChinese, 15, Font.NORMAL);

        //段落（标题）
        Paragraph title = new Paragraph();
        //短语（标题内容）
        Phrase content = new Phrase();
        //块（标题内容信息）
        Chunk contentInfo = new Chunk(contentStr, boldFont);

        //缩进
        title.setIndentationLeft(-10.00F);
        //字体间距
        title.setSpacingBefore(10.00F);
        title.setSpacingAfter(10.00F);
        //边距

        content.add(contentInfo);
        title.add(content);
        doc.add(title);
    }

    /**
     * 创建属性键值对
     *
     * @param doc
     */
    private static void createKV(Document doc, List<String> contentStrs) throws IOException {

        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //正常
        Font textFont = new Font(bfChinese, 10, Font.NORMAL);

        //段落（属性键值对）
        Paragraph kv = new Paragraph();
        //短语（属性键值对内容）
        Phrase content = new Phrase();

        //缩进
        kv.setIndentationLeft(-10.00F);
        //字体间距
        kv.setSpacingAfter(15.00F);

        contentStrs.stream().forEach(contentStr -> {
            //块（属性键值对信息）
            Chunk contentInfo = new Chunk(contentStr, textFont);
            content.add(contentInfo);
        });
        kv.add(content);
        doc.add(kv);
    }

    private static void createTable(Document doc) throws IOException {

        //添加中文字体
        BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
        //正常
        Font textFont = new Font(bfChinese, 10, Font.NORMAL);

        //段落（属性键值对）
        Paragraph size = new Paragraph();
        size.setIndentationLeft(-10.00F);

        // 创建一个有4列的表格
        PdfPTable table = new PdfPTable(4);
        //设置列宽
        table.setTotalWidth(new float[]{60, 130, 60, 130});
        //锁定列宽
        table.setLockedWidth(true);

        PdfPCell cell;
        for (int i = 0; i < 4; i++) {
            cell = new PdfPCell(new Phrase("信用代码：", textFont));
            cell.setBorder(0);
            //设置可以居中
            cell.setUseAscender(true);
            //设置水平居中
            cell.setHorizontalAlignment(Cell.LEFT);
            //设置垂直居中
            cell.setVerticalAlignment(Cell.ALIGN_MIDDLE);
            table.addCell(cell);
        }
        size.add(table);
        doc.add(size);

    }

    /**
     * 创建图片
     */
    public static void createImage(Document doc) throws IOException {

        Image image = Image.getInstance("/Users/yalonglee/Desktop/二维码.png");
        //图片大小
        image.scaleToFit(100.00F, 100.00F);
        //图片位置
        image.setAbsolutePosition(450.00F, 650.00F);

        doc.add(image);
    }

}
