package com.qiuguan.velocity.demo.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.velocity.Template;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;
import org.apache.velocity.tools.ToolContext;
import org.apache.velocity.tools.ToolManager;

import java.io.FileWriter;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiuguan
 * @date 2023/06/20 23:49:14  星期二
 */
public class VelocityGenericToolsTest {

    public static void main(String[] args) throws Exception {

        //1.定义一个模板引擎
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        //2.配置tools
        ToolManager toolManager = new ToolManager();
        toolManager.configure("configtools.xml");

        //3.指定一个模板
        Template t = ve.getTemplate("hello2.vm");

        //4.用toolManager创建上下文
        ToolContext ctx = toolManager.createContext();
        //设置字符串
        ctx.put("x", 15);
        ctx.put("name", "velocity-generic-tools");
        ctx.put("a", 11);
        ctx.put("b", 3);

        //设置集合
        List<String> temp = new ArrayList<>();
        temp.add("北京");
        temp.add("杭州");
        temp.add("海南");
        ctx.put( "list", temp);

        //设置对象
        List<Person> personList = new ArrayList<>();
        personList.add(new Person("秋官", 30, 3));
        personList.add(new Person("李白", 45, 300));
        personList.add(new Person("王维", 26, 120));
        ctx.put("persons", personList);

        //合并到模板中, 可以是文件中
//        FileWriter fw = new FileWriter("demo.html");
//        t.merge(ctx, fw);
//        fw.close();

        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        System.out.println(sw);
    }

    @AllArgsConstructor
    @Data
    public static class Person {
        private String name;
        private int age;
        private int count;
    }
}
