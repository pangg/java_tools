package com.xxx.spring.ioc5.context;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.Node;
import org.dom4j.io.SAXReader;
import java.io.File;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 实现极简IoC容器
 */
public class ClassPathXmlApplicationContext implements ApplicationContext{
    private Map iocContainer = new HashMap();

    public ClassPathXmlApplicationContext() {
        try {
            String filePath = this.getClass().getResource("/spring/ioc5/applicationContext.xml").getPath();
            filePath = URLDecoder.decode(filePath, "UTF-8");
            SAXReader reader = new SAXReader();
            Document document = reader.read(new File(filePath));
            List<Node> beans = document.getRootElement().selectNodes("bean");
            for (Node node : beans) {
                Element ele = (Element) node;
                String id = ele.attributeValue("id");
                String className = ele.attributeValue("class");
                Class c = Class.forName(className);
                Object obj = c.newInstance();
                List<Node> properties = ele.selectNodes("property");
                for (Node p : properties) {
                    Element property = (Element) p;
                    String proName = property.attributeValue("name");
                    String proValue = property.attributeValue("value");

                    String setMethodName = "set" + proName.substring(0,1).toUpperCase() + proName.substring(1);
                    System.out.println("准备执行" + setMethodName + "方法注入数据");
                    Method setMethod = c.getMethod(setMethodName, String.class);
                    setMethod.invoke(obj, proValue);   // 通过setter方法注入数据
                }

                iocContainer.put(id, obj);
            }
            System.out.println(iocContainer);
            System.out.println("IOC容器初始化完毕");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object getBean(String beanId) {
        return iocContainer.get(beanId);
    }
}
