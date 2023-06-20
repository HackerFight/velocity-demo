package com.qiuguan.velocity.demo.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.runtime.RuntimeConstants;
import org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * @author qiuguan
 * @date 2023/06/20 20:29:05  星期二
 */
public class VelocityTest {

    public static void main(String[] args) throws IOException {

        //1.定义一个模板引擎
        VelocityEngine ve = new VelocityEngine();
        ve.setProperty(RuntimeConstants.RESOURCE_LOADER, "classpath");
        ve.setProperty("classpath.resource.loader.class", ClasspathResourceLoader.class.getName());

        ve.init();

        //2.指定一个模板
        Template t = ve.getTemplate("hello.vm");
        VelocityContext ctx = new VelocityContext();

        //设置字符串
        ctx.put("c", 15);
        ctx.put("name", "velocity");

        //设置集合
        List<String> temp = new ArrayList<>();
        temp.add("北京");
        temp.add("杭州");
        temp.add("海南");
        ctx.put( "list", temp);

        //设置对象
        ctx.put("person", new Person("秋官", 30, LocalDate.now()));

        //合并到模板中, 可以是文件中
        //FileWriter fw = new FileWriter("demo.html");
        //t.merge(ctx, fw);
        //fw.close();

        StringWriter sw = new StringWriter();
        t.merge(ctx, sw);
        System.out.println(sw);
    }

    @AllArgsConstructor
    @Data
    public static class Person {
        private String name;
        private int age;
        private LocalDate birthday;
    }
}
