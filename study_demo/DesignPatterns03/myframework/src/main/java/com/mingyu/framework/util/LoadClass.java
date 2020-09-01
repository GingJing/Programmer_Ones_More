package com.mingyu.framework.util;

import org.apache.commons.lang3.ArrayUtils;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * 类加载工具类
 *
 * @date: 2020/8/21 8:46
 * @author: GingJingDM
 * @version: 1.0
 */
public class LoadClass {

    /**
     * 获取某个接口下所有实现这个接口的类
     *
     * @param c 接口类
     * @return Class列表
     */
    public static List<Class<?>> getAllClassByInterface(Class<?> c) {
        List<Class<?>> returnClassList = null;
        if (c.isInterface()) {
            // 当前包名
            String packageName = c.getPackage().getName();
            // 获取当前包下以及子包下所有的类
            List<Class<?>> classes = getClasses(packageName);
            if (classes != null) {
                returnClassList = new ArrayList<>();
                for (Class<?> aClass : classes) {
                    if (c.isAssignableFrom(aClass)) {
                        if (!c.equals(classes)) {

                            returnClassList.add(aClass);
                        }
                    }
                }
            }
        }
        return returnClassList;
    }

    /**
     * 获取某一类所在包的所有类名
     *
     * @param classLocation 类地址
     * @param packageName   包权限定名
     * @return 所有类名
     */
    public static String[] getPackageAllClassName(String classLocation, String packageName) {
        String[] packagePathSplit = packageName.split("[.]");
        String realClassLocation = classLocation;
        int packageLength = packagePathSplit.length;
        for (int i = 0; i < packageLength; i++) {
            realClassLocation = realClassLocation + File.separator + packagePathSplit[i];
        }
        File packageDir = new File(realClassLocation);
        if (packageDir.isDirectory()) {
            return packageDir.list();
        }
        return null;
    }

    /**
     * 从包中获取所有的Class
     * @param packageName 包权限定名
     * @return Class列表
     */
    public static List<Class<?>> getClasses(String packageName) {
        // 第一个Class类的集合
        List<Class<?>> classes = new ArrayList<>();

        // 是否循环迭代
        boolean recursive = true;

        // 获取包的名字并进行替换
        String packageDirName = packageName.replace('.', '/');

        // 定义一个枚举集合，循环处理此目录下的things
        Enumeration<URL> dirs;
        try {
            dirs = Thread.currentThread().getContextClassLoader().getResources(packageDirName);
            // 循环迭代下去
            while (dirs.hasMoreElements()) {
                URL url = dirs.nextElement();
                String protocol = url.getProtocol();
                // 如果是以文件的形式保存在服务器上
                if ("file".equals(protocol)) {
                    String filePath = URLDecoder.decode(url.getFile(), StandardCharsets.UTF_8.name());
                    findAndClassesInPackageByFile(packageName, filePath, recursive, classes);
                } else if ("jar".equals(protocol)) {
                    // 如果是jar包文件
                    // 定义一个JarFile
                    JarFile jarFile;
                    try {
                        //获取jar里的一个实体 可以是目录 和一些jar包里的其他文件 如META-INF等文件
                        jarFile = ((JarURLConnection)url.openConnection()).getJarFile();
                        Enumeration<JarEntry> entries = jarFile.entries();
                        // 循环迭代
                        while (entries.hasMoreElements()) {
                            JarEntry entry = entries.nextElement();
                            String name = entry.getName();
                            // 以'/'开头则截取
                            if (name.charAt(0) == '/') {
                                name = name.substring(1);
                            }
                            // 如果前半部分和定义的包名相同
                            if (name.startsWith(packageDirName)) {
                                int idx = name.lastIndexOf('/');
                                // 如果以"/"结尾，是一个包
                                if (idx != -1) {
                                    // 获取包名，把"/"替换成"."
                                    packageName = name.substring(0, idx).replace('/', '.');
                                }
                                // 如果可以迭代下去，并且是一个包
                                if ((idx != -1) || recursive) {
                                    // 如果是一个.class文件而且不是目录
                                    if (name.endsWith(".class") && !entry.isDirectory()) {
                                        String className = name.substring(packageName.length() + 1, name.length() - 6);
                                        try {
                                            classes.add(Class.forName(packageName + '.' + className));
                                        } catch (ClassNotFoundException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                }
                            }
                        }
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }


    /**
     * 以文件的形式来获取包下的所有Class
     *
     * @param packageName    包名
     * @param packagePath    包路径
     * @param recursive      是否递归
     * @param classes        装载Class的列表
     */
    public static void findAndClassesInPackageByFile(String packageName, String packagePath, final boolean recursive, List<Class<?>> classes) {
        // 获取包目录
        File dir = new File(packagePath);

        // 如果不存在或者不是目录直接返回
        if (!dir.exists() || !dir.isDirectory()) {
            return;
        }

        // 如果存在获取包下的所有文件包括目录
        // 自定义过滤规则：如果可以循环(包含子目录)或是以.class结尾的文件(编译好的java类文件)
        File[] filterFiles = dir.listFiles(pathname -> (recursive && pathname.isDirectory()) || (pathname.getName().endsWith(".class")));
        if (ArrayUtils.isEmpty(filterFiles)) {
            return;
        }
        // 循环所有文件
        for (File file : filterFiles) {
            if (file.isDirectory()) {
                findAndClassesInPackageByFile(
                        packageName + "." + file.getName(),
                        file.getAbsolutePath(),
                        recursive,
                        classes
                );
            } else {
                // 如果是java文件去掉后面的.class只留下类名
                String className = file.getName().substring(0, file.getName().length() - 6);
                try {
                    // 加入到集合中
                    classes.add(Class.forName(packageName + "." + className));
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
