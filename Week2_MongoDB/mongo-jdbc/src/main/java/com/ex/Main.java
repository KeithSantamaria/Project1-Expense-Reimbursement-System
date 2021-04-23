package com.ex;

import com.ex.persistence.PaintRepository;
import com.ex.persistence.Repository;
import com.ex.pojos.Paint;
import com.ex.services.MongoPaintService;
import com.ex.services.MongoService;
import com.mongodb.MongoClientSettings;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.types.ObjectId;

public class Main {
    public static void main(String[] args) {
        // this example follows the app-model pretty closely
        // notice I am creating the Objects at this level
        // exerting the control I need for writing testable code
        MongoConnector connector = new MongoConnector();
        connector.configure( () -> {
            CodecProvider codecProvider = PojoCodecProvider.builder().register("com.ex.pojos").build();
            CodecRegistry registry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(codecProvider));
            return MongoClientSettings.builder()
                    .applyConnectionString(connector.newConectionString("mongodb://localhost:27017/paintstore"))
                    .retryWrites(true)
                    .codecRegistry(registry)
                    .build();
        }).createClient();
        Repository<Paint, ObjectId> paintRepository = new PaintRepository(connector);
        MongoService paintService = new MongoPaintService(paintRepository);

        Paint p = (Paint)paintService.findOne(new ObjectId("607f2bda710464aeae4b062a")); // if you want to run this locally make sure you use an id from your paintstore database
        System.out.println(p);

    }
}
