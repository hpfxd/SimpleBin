package nl.hpfxd.simplebin.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import dev.morphia.Datastore;
import dev.morphia.Morphia;
import lombok.Getter;
import nl.hpfxd.simplebin.mongo.converters.SyntaxHighlightingConverter;

public class SimpleBinDatabase {
    @Getter private MongoClient mongoClient;
    @Getter private Morphia morphia;
    @Getter private Datastore datastore;

    public void init(String uri) {
        this.morphia = new Morphia();
        this.morphia.getMapper().getConverters().addConverter(new SyntaxHighlightingConverter());
        this.morphia.map(Paste.class);
        this.mongoClient = new MongoClient(new MongoClientURI(uri));
        this.datastore = morphia.createDatastore(mongoClient, "simplebin");
        this.datastore.ensureIndexes();
    }
}
