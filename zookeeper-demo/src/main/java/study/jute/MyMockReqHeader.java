package study.jute;

import lombok.Data;
import org.apache.jute.InputArchive;
import org.apache.jute.OutputArchive;
import org.apache.jute.Record;

import java.io.IOException;

/**
 * 描述：
 *
 * @Author wangjun
 * @Date 2020/10/14
 */
@Data
public class MyMockReqHeader implements Record {

    long sessionId;
    String type;

    public MyMockReqHeader() {

    }

    public MyMockReqHeader(long sessionId, String type) {
        this.sessionId = sessionId;
        this.type = type;
    }

    @Override
    public void serialize(OutputArchive archive, String tag) throws IOException {
        archive.startRecord(this, tag);
        archive.writeLong(sessionId, "sessionId");
        archive.writeString(type, "type");
        archive.endRecord(this, tag);
    }

    @Override
    public void deserialize(InputArchive archive, String tag) throws IOException {
        archive.startRecord(tag);
        this.sessionId = archive.readLong("sessionId");
        this.type = archive.readString("type");
        archive.endRecord(tag);
    }
}
