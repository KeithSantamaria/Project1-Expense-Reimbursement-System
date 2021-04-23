package com.ex.services;

import com.ex.persistence.Repository;
import com.ex.pojos.Paint;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.Collection;

public class MongoPaintService implements MongoService<Paint> {

    private Repository<Paint, ObjectId> paintRepository;

    public MongoPaintService(Repository<Paint, ObjectId> paintRep) {
        this.paintRepository = paintRep;
    }

    @Override
    public ObjectId save(Paint obj) {
        return null;
    }

    @Override
    public Paint findOne(ObjectId id) {
        return this.paintRepository.findById(id);
    }

    @Override
    public Collection<Paint> find(Bson... filters) {
        return null;
    }

    @Override
    public void delete(ObjectId id) {

    }
}
