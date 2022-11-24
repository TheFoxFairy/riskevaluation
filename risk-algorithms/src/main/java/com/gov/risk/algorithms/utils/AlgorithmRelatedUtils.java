package com.gov.risk.algorithms.utils;

import org.springframework.util.ResourceUtils;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class AlgorithmRelatedUtils {

    private final YamlReader yamlReader = new YamlReader();
    private final HashMap<String,Object> options;

    public AlgorithmRelatedUtils(){
        this.options = this.readYmlForOptions();
    }
    /*
        解析yml文件，读取algorithms选项
     */
    public HashMap<String,Object> readYmlForOptions(){
        HashMap<String, Object> options = this.yamlReader.getValueByKey("algorithms");
        return options;
    }

    /*
       检查算法文件是否存在
     */
    public File checkFileIsExists(String algorithmName){


        HashMap<String,Object> algorithms = (HashMap<String, Object>)this.options.get("filename");

        if(!algorithms.containsKey(algorithmName)){
            System.out.println("算法文件不存在，请检查相关配置文件application.yml以及algorithms目录下的算法文件");
            return null;
        }

        // 首先检查是否配置在yaml文件中
        HashMap<String,Object> algorithm = (HashMap<String, Object>) algorithms.get(algorithmName);
        String position = (String) this.options.get("basePosition") + "/" + (String) algorithm.get("position");
        File file = null;
        try {
            file = ResourceUtils.getFile("classpath:"+position);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        return file;
    }

    /*
        获取算法文件路径
     */
    public String getFilePath(String algorithmName){
        File file = this.checkFileIsExists(algorithmName);
        if(file == null) {
            return "文件不存在";
        }
        return file.getPath();
    }

    /*
        给定算法名称，调用相应算法
     */
    public void schedule(String algorithmName){
        // 获取算法文件路径
        String filename = this.getFilePath(algorithmName);

        String[] arguments = new String[] {"python",filename,"1"}; //指定命令、路径、传递的参数
        try {
            Process process = Runtime.getRuntime().exec(arguments);
            System.out.println(readProcessOutput(process.getInputStream()));
            process.waitFor();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }


    }
    /*
        读取python打印的数据（一般可以在成功后，获取打印数据，来判断下一步怎么做）
     */
    private static List<String> readProcessOutput(InputStream inputStream) throws IOException {
        try (BufferedReader output = new BufferedReader(new InputStreamReader(inputStream,"gbk"))) {
            return output.lines()
                    .collect(Collectors.toList());
        }
    }

    public static void main(String[] args) {
        AlgorithmRelatedUtils relatedUtils = new AlgorithmRelatedUtils();
        HashMap<String,Object> options = relatedUtils.readYmlForOptions();
        relatedUtils.schedule("algorithm1");
    }


}
