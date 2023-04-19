import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author manxingfu
 * @date 2023/2/24
 */
public class TestDemo {
    @Test
    public void test01() {
        ArrayList<String> list = new ArrayList<>();
        list.add("java");
        list.add("spring");
        list.add("mybatis");
        list.add("cloud");
        list.add("nacos");
        list.add("dubbo");
        list.add("mysql");
        list.add("redis");
        list.add("seata");
        list.add("rabbitmq");
        /*Random random = new Random();
        Random str = new Random();
        for (int i = 0; i < 30; i++) {
            int len = random.nextInt(10);
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < len; j++) {
                // char c = (char)(97 + str.nextInt());
                char c = (char) ('a' + str.nextInt(26));
                sb.append(c);
            }
            list.add(sb.toString());
        }*/

        String[] map = new String[8];
        for (String element: list) {
            int index = (element.hashCode() ^ (element.hashCode() >>> 16)) & (map.length - 1);
            // int index = element.hashCode() & (map.length - 1);
            // System.out.printf("key: %s, index: %d\n", element, index);
            if (map[index] == null) {
                map[index] = element;
                continue;
            }
            map[index] = map[index] + "->" + element;
        }

        System.out.println("=============分布情况==============");

        for (int i = 0; i < map.length; i++) {
            System.out.printf("map[%d]=%s\n", i, map[i]);
        }
    }

    @Test
    public void test02() {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        try {
            Date date = format.parse("2023-01-17 11");
            System.out.println(date.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void test03() {
        System.out.println(
                "(♥◠‿◠)ﾉﾞCA-认证中心启动成功!ლ(´ڡ`ლ)ﾞ\n" +
                        "  .g8\"\"\"bgd     db      \n" +
                        ".dP'     `M    ;MM:     \n" +
                        "dM'       `   ,V^MM.    \n" +
                        "MM           ,M  `MM    \n" +
                        "MM.          AbmmmqMA   \n" +
                        "`Mb.     ,' A'     VML  \n" +
                        "  `\"bmmmd'.AMA.   .AMMA.\n" +
                        "                        \n" +
                        "                        \n"
        );
    }

}
