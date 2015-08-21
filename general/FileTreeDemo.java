package general;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created with GeneralTest.
 * User: IFT8
 * Date: 2014/10/2
 */
public class FileTreeDemo {
    public static void main(String[] args) {
        FileTree fop = new FileTree();
        String path;
        String outPath;
//        path = "F:\\U";
//        outPath = "D:\\ls.txt";
        path = (args.length == 0) ? getPath("请输入扫描路径：") : args[0];
        if (args.length == 2) {
            outPath = args[1];
        } else {
            outPath = getPath("请输入输出路径：");
        }
        long startTime = System.currentTimeMillis();
        fop.showDir(new File(path), outPath);
        System.out.println("用时：" + (System.currentTimeMillis() - startTime) + "ms");
    }

    private static String getPath(String string) {
        Scanner sc = new Scanner(System.in);
        System.out.println(string);
        return sc.next();
    }
}

class FileTree {
    private int depth = 0;
    private List<String> list = new ArrayList<String>();
    //StringBuilder 线程不同步 效率高
    private StringBuilder sb = new StringBuilder();

    public void scanDir(File file, boolean isShowHide) {
        File[] files = file.listFiles();
        if (depth++ == 0) {
            sb.append(file);
            list.add(sb.toString());
            sb.setLength(0);//这样清空效率最高
        }
        if (files != null) {
            for (File file1 : files) {
                for (int i = 1; i < depth; i++) {
                    sb.append("\t");
                }
                boolean isHide = file1.isHidden();
                //模式为不显示隐藏文件时跳过
                if (isHide && !isShowHide) {
                    continue;
                }
                sb.append("└─").append(file1.getName());
                if (file1.isDirectory()) {
                    sb.append("  <dir>");
                    list.add(sb.toString());
                    sb.setLength(0);//这样清空效率最高
                    changeSign();
                    scanDir(file1, isShowHide);
                } else {
                    list.add(sb.toString());
                    sb.setLength(0);//这样清空效率最高
                    changeSign();
                }
            }
        }
        depth--;//遍历完目录返回上一级
    }

    public void changeSign() {
        int preNum = list.size() - 1;
        StringBuilder preSB = new StringBuilder();
        char preSign;
        while (true) {
            if (preNum > 1) {
                preSB.append(list.get(--preNum));
                preSign = preSB.charAt(depth - 1);
                if (preSign != '\t' && preSign != '└') {
                    break;
                } else {
                    if (preSign == '\t') {
                        preSB.insert(depth - 1, '│');
                    }
                    if (preSign == '└') {
                        preSB.setCharAt(depth - 1, '├');
                    }
                    list.set(preNum, preSB.toString());
                    preSB.delete(0, preSB.length());
                }
            } else {
                break;
            }
        }
    }

    public void showDir(File file, String outPath) {
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outPath), "UTF-8"));
            scanDir(file,false);
            for (String s : list) {
                bw.write(s);
                bw.newLine();
                bw.flush();
                System.out.println(s);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

