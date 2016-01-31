package io;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * Created by 俊管 on 2015/9/13.
 */
public class DirList {
    public static void main(final String[] args) {
       // Logger logger = Logger.getLogger(DirList.class);
        File path = new File(".");
        String[] list;

            //logger.info(args[0]);
            list = path.list(new FilenameFilter() {
                private Pattern pattern = Pattern.compile("\\S*");
                @Override
                public boolean accept(File dir, String name) {
                    return pattern.matcher(name).matches();

                }
            });

        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String dirItem : list) {
           // logger.info(dirItem);
            System.out.println(dirItem);
        }
    }
}
