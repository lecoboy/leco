package com.greg.leco.demo.jvm.load;

import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author greg
 * @version 2021/10/15
 */
public class MyClassLoaderDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        MyClassLoader myClassLoader = new MyClassLoader();
        Class<?> c = myClassLoader.loadClass("OuterDefineClass.class");
    }

}

class MyClassLoader extends ClassLoader {

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        ByteOutputStream os = new ByteOutputStream();
        try {
            Files.copy(Paths.get("/Users/greg/workspace/javaDemo/" + name), os);
//            FileInputStream in = new FileInputStream(new File("/Users/greg/workspace/javaDemo/" + name));
//            ByteArrayOutputStream os = new ByteArrayOutputStream(in);
            byte[] bytes = os.getBytes();
            return defineClass(name, bytes, 0, bytes.length);
        } catch (IOException e) {
            throw new ClassNotFoundException("文件" + name + "未找到");
        }
    }
}
