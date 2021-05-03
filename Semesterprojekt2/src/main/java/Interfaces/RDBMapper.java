package Interfaces;

public interface RDBMapper {

    Object get(String ObjectId);
    void put(String ObjectId, Object o);
}
