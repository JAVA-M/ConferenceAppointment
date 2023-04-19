package com.mxf.utils;

/**
 * @author manxingfu
 * @date 2023/1/10
 * 生成一串UUID字符的单例类
 */
public class GeneratorName {
    private static volatile GeneratorName generatorName;

    private int number;
    private GeneratorName() {
        this.number = 1;
    }

    public static GeneratorName newInstance() {
        if (generatorName == null) {
            synchronized (GeneratorName.class) {
                if (generatorName == null) {
                    generatorName = new GeneratorName();
                }
            }
        }
        return generatorName;
    }

    public String generator(String suffix) {
        StringBuilder builder = new StringBuilder();
        builder.append("CA-").append(number++);

        if (suffix != null && !suffix.equals("")) {
            builder.append(".").append(suffix);
        }
        return builder.toString();
    }

    public String generator() {
        return generator("");
    }


}
