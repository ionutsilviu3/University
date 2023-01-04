package TemaRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
    private static Pattern pattern;
    private static Matcher matcher;
    public static boolean isMatching(String regex, String cuvant)
    {
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(cuvant);
        return matcher.matches();
    }
}
