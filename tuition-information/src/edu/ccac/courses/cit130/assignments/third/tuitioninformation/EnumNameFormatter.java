package edu.ccac.courses.cit130.assignments.third.tuitioninformation;

/**
 * @author Jacob Rhiel <jacob.rhiel@gmail.com>
 * @created Jun 12, 2021
 */
public class EnumNameFormatter {

    public static String formatEnumName(String name) {
        String[] words = name.split("_");
        StringBuilder builder = new StringBuilder();
        for(int idx = 0; idx < words.length; idx++) {
            builder.append(words[idx].charAt(0)).append(words[idx].substring(1).toLowerCase());
            if(idx == 0 && words.length > 1)
                builder.append(" ");
        }
        return builder.toString();
    }

}
