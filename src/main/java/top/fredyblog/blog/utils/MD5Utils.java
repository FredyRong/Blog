package top.fredyblog.blog.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 * @author Fredy
 * @date 2020/5/5 17:24
 */
public class MD5Utils {

    public static String code(String s){
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
        md5.update(s.getBytes());
        byte[] digest = md5.digest();
        int i;
        StringBuffer buffer = new StringBuffer("");
        for(int offset = 0; offset < digest.length; offset++){
            i = digest[offset];
            if(i < 0){
                i += 256;
            }
            if(i < 16){
                buffer.append("0");
            }
            buffer.append(Integer.toHexString(i));
        }
        //32位加密
        return buffer.toString();
        //16位加密
        //return buffer.toString().substring(8, 24);
    }

//    public static void main(String[] args) {
//        System.out.println(MD5Utils.code("root"));
//    }
}
