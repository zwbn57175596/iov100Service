package com.iov100.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.imageio.stream.FileImageInputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author
 * @version 创建时间：2011-9-28 下午08:33:52
 * E-Mail:liaojifeng@163.com
 * 类说明：
 *
 *
 *
 */
@SuppressWarnings("unchecked")
public class PropertiesHelper {
    private static Log log = LogFactory.getLog(PropertiesHelper.class);
    private  InputStream inputStream = null;
    private  Properties props=null;
    //初始化
    public  PropertiesHelper(){
        try {
            inputStream = new FileInputStream(PropertiesHelper.getProjectPath()+"configure/config.properties");
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            log.error("升级程序配置文件找不到."+e.getMessage());
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       props = new Properties();
       try {   
           props.load(inputStream);     
       } catch (IOException e1) {   
        e1.printStackTrace();   
       }finally{}

    }
    
    /**
     * 获取某个属性
     */
    public String getProperty(String key){
        return props.getProperty(key);
    }
    /**
     * 获取所有属性，返回一个map,不常用
     * 可以试试props.putAll(t)
     */
    public Map getAllProperty(){
        Map map=new HashMap();
        Enumeration enu = props.propertyNames();
        while (enu.hasMoreElements()) {
            String key = (String) enu.nextElement();
            String value = props.getProperty(key);
            map.put(key, value);
        }
        return map;
    }
    /**
     * 在控制台上打印出所有属性，调试时用。
     */
    public void printProperties(){
        props.list(System.out);
    }
 
    
    /**
     * 关闭配置文件
     * @author JiF
     * @version 创建时间：2011-9-28 下午08:38:31
     * E-Mail:liaojifeng@163.com
     * 说明
     *
     *
     */
    public  void closePropertiesFile(){

       if (inputStream!=null){
           try {
            inputStream.close();
        } catch (IOException e) {

        }
       }
       
    }
    
    
    /**
     * 获取工程所在目录
     * @return
     * @throws UnsupportedEncodingException
     */
    private static String getProjectPath() throws UnsupportedEncodingException{
        URL url = PropertiesHelper.class.getProtectionDomain().getCodeSource().getLocation();
        String filePath = URLDecoder.decode(url.getPath(), "UTF-8");
        if(filePath.endsWith(".jar"))
            filePath = filePath.substring(0, filePath.lastIndexOf("/") + 1);
        return filePath;
    }
}