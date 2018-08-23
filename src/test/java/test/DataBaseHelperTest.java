package test;

import org.smart4j.framework.helper.DatabaseHelper;

import java.util.Set;

/**
 * @author tjj .
 */
public class DataBaseHelperTest {
    public static void main(String[] args) {
        String sql="select name from customer where  id=?";
      String s=   DatabaseHelper.query(sql,1);
        System.out.println(s);
        String sql2="select telephone from customer";
        Set<String> s2=   DatabaseHelper.querySet(sql2);
        System.out.println(s2);

    }
}
