package io.file;

import java.io.File;
import java.io.IOException;

/**
 *
 *
 * Created by 俊管 on 2015/9/21.
 */
public class ProcessFiles {
    //内部接口，使用策略设计模式，实现该接口，的process方法，可以让每一个遍历的文件
    //对象执行process（）方法
    public interface Strategy {
        void process(File file);
    }
    private Strategy strategy; //
    private String ext; //扩展名，也可以是正则表达式
    //构造函数，需要传递一个正则表达式，和一个Strategy接口的实例
    public ProcessFiles(String ext, Strategy strategy) {
        this.ext = ext;
        this.strategy = strategy;
    }


    public void start(String[] args) {
        try {
            if (args.length == 0) {
                processDirectoryTree(new File("."));
                return;
            }
            for (String arg : args) {
                File fileArg = new File(arg);
                if (fileArg.isDirectory()) {
                    processDirectoryTree(fileArg);
                } else {
                    //允许用户不输入文件后缀，当用户为输入后缀时，补齐后缀
                    if (!arg.endsWith("." + ext))
                        arg += "." + ext;
                    strategy.process(new File(arg).getCanonicalFile());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void processDirectoryTree(File root) throws IOException {
        //遍历  Directory.walk()方法得到所有文件，
        for (File file : Directory.walk(root.getAbsoluteFile(), ".*\\." + ext)) {
            strategy.process(file.getCanonicalFile());//执行Strategy的 process()方法
        }
    }
}
