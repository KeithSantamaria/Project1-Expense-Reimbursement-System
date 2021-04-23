package com.ex.persistence;

import com.ex.MongoConnector;
import com.ex.pojos.Paint;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import org.bson.Document;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public class PaintRepository implements Repository<Paint, ObjectId> {
    private MongoConnector connector;
    private MongoCollection<Paint> paints;

    public PaintRepository(MongoConnector connector) {
        this.connector = connector;
        this.paints = this.connector
                .getClient()
                .getDatabase("paintstore")
                .getCollection("paints", Paint.class);
    }
    @Override
    public Paint findById(ObjectId objectId) {
        return this.paints
                .find(eq("_id", objectId))
                .first();
    }

    @Override
    public Collection<Paint> findAll() {
        FindIterable<Paint> results = this.paints.find(); // I could return this and have the service convert to a collection
        return StreamSupport.stream(results.spliterator(), false)
                .collect(Collectors.toList());
    }
}
