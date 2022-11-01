package com.rainarmy.tools.xml;

import java.util.ArrayList;
import java.util.List;

public class XmlUtil {

    /**
     * 换行
     */
    private static final String CHANEROW = "\r\n";

    /**
     * 生成XML元素集合
     */
    private List<XmlElement> elements = new ArrayList<>();

    /**
     * 当前XML元素
     */
    private XmlElement element;

    /**
     * XML元素
     */
    public class XmlElement{

        private String name;

        private String content;

        private List<XmlElement> elements = new ArrayList<>();

        /**
         * 当前XML子元素
         */
        private XmlElement childElemnt;

        public void createChildElemnt(){
            childElemnt = new XmlElement();
            elements.add(childElemnt);
        }

        public void createChildElemnt(String name){
            childElemnt = new XmlElement(name);
            elements.add(childElemnt);
        }

        public void createChildElemnt(String name,String content){
            childElemnt = new XmlElement(name,content);
            elements.add(childElemnt);
        }

        public XmlElement getCurrentChildElemnt(){
            return childElemnt;
        }

        public XmlElement(){
        }

        public XmlElement(String name){
            this.name = name;
        }

        public XmlElement(String name,String content){
            this.name = name;
            this.content = content;
        }

        public String getName() {
            return name == null ? "" : name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content == null ? "" : content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<XmlElement> getElements() {
            return elements;
        }

        public void setElements(List<XmlElement> elements) {
            this.elements = elements;
        }
    }

    public void createElement(){
        element = new XmlElement();
        this.elements.add(element);
    }

    public void createRootElement(){
        this.elements.clear();
        element = new XmlElement();
        this.elements.add(element);
    }

    public void createRootElement(String name){
        this.elements.clear();
        element = new XmlElement(name);
        this.elements.add(element);
    }

    public void createElement(String name){
        element = new XmlElement(name);
        this.elements.add(element);
    }

    public void createElement(String name,String content){
        element = new XmlElement(name,content);
        this.elements.add(element);
    }

    public XmlElement getCurrentXmlElement(){
        return element;
    }

    public String createXml(){
        StringBuilder sb = new StringBuilder();
        for (XmlElement xml : elements){
            sb.append(createXmlElement(xml,0));
        }
        return sb.toString();
    }

    private String createXmlElement(XmlElement xml,int num){
        StringBuilder sb = new StringBuilder();
        sb.append(blanks(num) + "<" + xml.getName() + ">");
        boolean flag = false;
        if(xml.getElements().size() == 0 || !"".equals(xml.getContent())){
            sb.append(xml.getContent());
            flag = true;
        }else{
            sb.append(CHANEROW);
        }
        for (XmlElement x : xml.getElements()){
            sb.append(createXmlElement(x,num + 1));
        }
        sb.append((flag ? "" : blanks(num)) + "</" + xml.getName() + ">" + CHANEROW);
        return sb.toString();
    }

    /**
     * 设置空格数量
     * @param num
     * @return
     */
    private static String blanks(int num){
        return "";
    }

    public static String coding(){
        return "<?xml version=\"1.0\" encoding=\"gb18030\" ?>" + CHANEROW;
    }
}
