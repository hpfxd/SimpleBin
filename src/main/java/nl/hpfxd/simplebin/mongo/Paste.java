package nl.hpfxd.simplebin.mongo;

import com.github.luben.zstd.Zstd;
import com.github.luben.zstd.ZstdException;
import dev.morphia.annotations.*;
import lombok.Getter;
import lombok.Setter;
import nl.hpfxd.simplebin.SimpleBin;
import nl.hpfxd.simplebin.SyntaxHighlighting;
import org.bson.types.ObjectId;

import java.nio.charset.StandardCharsets;

@Entity(value = "pastes", noClassnameStored = true)
@Getter
@Setter
public class Paste {
    @Id private ObjectId id;
    private String name;
    private SyntaxHighlighting syntax;
    private int decompressedSize;
    private byte[] compressedContent;

    public void save() {
        SimpleBin.getDatabase().getDatastore().save(this);
    }

    public String getContent() {
        return new String(Zstd.decompress(this.compressedContent, this.decompressedSize), StandardCharsets.UTF_8);
    }

    public void setContent(String content) throws ZstdException {
        byte[] bytes = content.getBytes(StandardCharsets.UTF_8);
        this.compressedContent = Zstd.compress(bytes);
        this.decompressedSize = bytes.length;
    }
}
