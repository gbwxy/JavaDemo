package study.zookeeper;

import org.apache.tomcat.util.codec.binary.Base64;

import java.security.MessageDigest;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/10/14
 */
public class ACL_Password {
    public static void main(String[] args) throws Exception {
        String usernameAndPassword = "moove:Moove";
        byte digest[] = MessageDigest.getInstance("SHA1").digest(usernameAndPassword.getBytes());
        Base64 base64 = new Base64();
        String encodeToString = base64.encodeToString(digest);
        System.out.println(encodeToString);
    }
}
