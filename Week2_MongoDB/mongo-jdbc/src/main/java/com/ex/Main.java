package com.ex;

import com.ex.pojos.Paint;
import com.mongodb.ConnectionString;
import com.mongodb.client.*;
import com.mongodb.MongoClientSettings;
import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.types.ObjectId;

import org.bson.codecs.pojo.PojoCodecProvider;

import static com.mongodb.client.model.Filters.eq;

public class Main {
    public static void main(String[] args) {
        try {
            ConnectionString connectionString = new ConnectionString("mongodb://localhost:27017/paintstore");
            CodecProvider codecProvider = PojoCodecProvider.builder().register("com.ex.pojos").build();
            CodecRegistry registry = CodecRegistries.fromRegistries(MongoClientSettings.getDefaultCodecRegistry(), CodecRegistries.fromProviders(codecProvider));
            MongoClientSettings settings = MongoClientSettings.builder()
                    .applyConnectionString(connectionString)
                    .retryWrites(true)
                    .codecRegistry(registry)
                    .build();
            MongoClient client = MongoClients.create(settings);
            MongoDatabase database = client.getDatabase("paintstore");
            MongoCollection<Paint> paintsCollection = database.getCollection("paints", Paint.class);

//            Paint paint = paintsCollection.find().filter(eq("_id", new ObjectId("607f2bda710464aeae4b062c"))).first();
//            System.out.println(paint);

//            Paint i = insertPaint(new Paint("test", "test", "test", 3.0f), paintsCollection);
//            System.out.println(i);

            deletePaint(new ObjectId("60808accb18f3b63542948e3"), paintsCollection);

        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static Paint insertPaint(Paint p, MongoCollection<Paint> paintCollection) {
        paintCollection.insertOne(p);
        return p;
    }

    static void deletePaint(ObjectId id, MongoCollection<Paint> paintCollection) {
        paintCollection.deleteOne(eq("_id", id));
    }
}
