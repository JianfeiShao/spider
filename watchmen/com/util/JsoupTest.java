package com.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Ignore;
import org.junit.Test;

import com.zht.util.Student;

public class JsoupTest {
	
	@Ignore
	@Test
	public void test() throws Exception {
		File file = new File("E:\\html.txt");
		InputStreamReader isr = new InputStreamReader(
				new FileInputStream(file), "UTF-8");
		BufferedReader br = new BufferedReader(isr);
		String str;
		StringBuffer sb = new StringBuffer();
		while ((str = br.readLine()) != null) {
			// System.out.println(str);
			sb.append(str);
		}
		br.close();
		// System.out.println(sb.toString());
		Document doc = Jsoup.parse(sb.toString());
		Elements links = doc.select("a[href]");
		Element masthead = doc.select("div.list-item").first();
		// System.out.println(masthead.toString());

		String url = "http://gsxt.saic.gov.cn/zjgs/notice/view?uuid=ckWqpmOpcWNBplKVG5VjsETmwj3essFnX.2gYb9CKzg=&amp;tab=01";
		Document doc1 = Jsoup.connect(url).get();
		Elements content = doc1.select("tbody");// ("div.hide");//.first();
		for (Element element : content) {
			// System.out.println(element.toString());
			System.out.println(element.select("th[colspan]").first().html());
			Elements elementTh = element.select("th,td").not("th[colspan]");
			for (Element element2 : elementTh) {
				System.out.println("-->" + element2.select("th").html() + ":"
						+ element2.select("td").html());
			}
		}
	}

	@Test
	public void poiTest() throws Exception {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("抓取数据");
		HSSFRow row = sheet.createRow(0);
		HSSFCellStyle style = wb.createCellStyle();
		
		FileOutputStream fout = new FileOutputStream("E:/xxxxx.xls");
		wb.write(fout);
		fout.close();
	}
	
	private static List<Student> getStudent() throws Exception
	{
		List<Student> list = new ArrayList<Student>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
		Student user1 = new Student(1, "张三", 16, df.parse("1997-03-12"));
		Student user2 = new Student(2, "李四", 17, df.parse("1996-08-12"));
		Student user3 = new Student(3, "王五", 26, df.parse("1985-11-12"));
		list.add(user1);
		list.add(user2);
		list.add(user3);
		return list;
	}
}
