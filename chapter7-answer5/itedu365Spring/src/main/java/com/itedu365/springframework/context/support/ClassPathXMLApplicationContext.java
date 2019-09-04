package com.itedu365.springframework.context.support;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class ClassPathXMLApplicationContext {

    private List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();// 存放bean标签对象
    private Map<String, Object> objects = new HashMap<String, Object>(); // 存放实例化对象


    public ClassPathXMLApplicationContext(String fileName) {
        this.readXML(fileName);
        this.instanceBeans();
        try {
            this.instanceObject();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 返回对象
     *
     * @param key
     * @return
     */
    public Object getBean(String key) {
        return this.objects.get(key);
    }

    /**
     * 读取配置文件
     *
     * @param fileName
     */
    @SuppressWarnings("unchecked")
    private void readXML(String fileName) {
        SAXBuilder sb = new SAXBuilder();
        try {
            Document doc = sb.build(this.getClass().getClassLoader().getResourceAsStream(fileName)); //加载配置文件创建doc文档
            Element root = doc.getRootElement(); //获取根元素
            List<Element> childRoot = root.getChildren(); //获取根元素下的子元素
            for (Element e : childRoot) {
                if(e.getName().equals("bean")){     //处理bean标签
                    String id = e.getAttributeValue("id");
                    String clazz = e.getAttributeValue("class");
                    BeanDefinition bd = new BeanDefinition(id, clazz);
                    List<Element> proRoot = e.getChildren();
                    for (Element pe : proRoot) {
                        String name = pe.getAttributeValue("name");
                        String ref = pe.getAttributeValue("ref");
                        PropertyDefinition pd = new PropertyDefinition(name, ref);
                        bd.getPropertys().add(pd);
                    }
                    this.beanDefinitions.add(bd);
                }
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 实例化对象
     */
    private void instanceBeans() {
        for (BeanDefinition bd : this.beanDefinitions) {
            try {
                if (bd.getClazz() != null && !bd.getClazz().trim().equals(""))
                    this.objects.put(bd.getId(), Class.forName(bd.getClazz()).newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 实现对象依赖注入
     */
    private void instanceObject() {
        for (BeanDefinition beanDefinition : this.beanDefinitions) {
            Object bean = this.objects.get(beanDefinition.getId());
            if (bean != null && beanDefinition.getPropertys().size() != 0) {
                List<PropertyDefinition> pds = beanDefinition.getPropertys();
                for(PropertyDefinition pd : pds){
                    String name = pd.getName();
                    String ref = pd.getRef();
                    Object refObj = this.objects.get(ref);
                    Method setMethod;
                    try {
                        setMethod = bean.getClass().getMethod("set" + name.substring(0,1).toUpperCase() + name.substring(1,name.length()), refObj.getClass().getInterfaces());
                        setMethod.invoke(bean, new Object[]{refObj});
                    } catch (SecurityException e) {
                        e.printStackTrace();
                    } catch (NoSuchMethodException e) {
                        e.printStackTrace();
                    } catch (IllegalArgumentException e) {
                        e.printStackTrace();
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
