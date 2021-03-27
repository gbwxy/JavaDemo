package study.jute;

import org.apache.jute.BinaryInputArchive;
import org.apache.jute.BinaryOutputArchive;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.ByteBuffer;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/10/14
 */
public class JuteTest {

    public static void main(String[] args) throws Exception {

        //序列化
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        BinaryOutputArchive boa = BinaryOutputArchive.getArchive(baos);
        MyMockReqHeader myMockReqHeader = new MyMockReqHeader(0x34224ecc9234el, "ping");
        System.out.println("============myMockReqHeader============");
        System.out.println(myMockReqHeader);
        myMockReqHeader.serialize(boa, "header");


        System.out.println("============transfer============");
        //传输-TCP
        ByteBuffer byteBuffer = ByteBuffer.wrap(baos.toByteArray());

        //反序列化
        ByteArrayInputStream bais = new ByteArrayInputStream(byteBuffer.array());
        BinaryInputArchive bia = BinaryInputArchive.getArchive(bais);
        MyMockReqHeader myMockReqHeader1 = new MyMockReqHeader();
        myMockReqHeader1.deserialize(bia, "header");
        System.out.println("============myMockReqHeader1============");
        System.out.println(myMockReqHeader1);
        bais.close();
        baos.close();

    }

}
