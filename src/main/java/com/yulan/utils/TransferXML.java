package com.yulan.utils;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.ByteArrayInputStream;
import java.util.List;

public class TransferXML {

    public static void main(String[] args) throws Exception {
        SAXReader reader = new SAXReader();
        String xml =
                "<SCRIPT>\n" +
                "\tvar parentOrigin = \"*\"; //\"http://61.145.202.4\";    " +
                "\tparent.postMessage(\"库存94.1米，卷1=42.2米，卷2=1.9米，卷3=50.0米\", " +
                "parentOrigin);\n" +
                "</SCRIPT>";
        Document document = reader.read(new ByteArrayInputStream(xml.getBytes("utf-8")));//读取xml字符串，注意这里要转成输入流
        Element root = document.getRootElement();//获取根元素
        List<Element> childElements = root.elements();//获取当前元素下的全部子元素

        for (Element child : childElements) {//循环输出全部book的相关信息
            List<Element> scripts = child.elements();
            for (Element script : scripts) {
                String name = script.getName();//获取当前元素名
                String text = script.getText();//获取当前元素值
                System.out.println(name + ":" + text);
            }
        }

    }

}
