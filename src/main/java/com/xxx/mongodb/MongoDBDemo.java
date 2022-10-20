package com.xxx.mongodb;

import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.ServerAddress;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MongoDBDemo {
    @Test
    // 连接数据库
    public void testConnectToDB() {
        this.getMGDatabase();
    }

    // 创建集合
    @Test
    public void testCreateCollection() {
        // 访问数据库
        MongoDatabase database = this.getMGDatabase();

        // 创建集合
        database.createCollection("tutorial");
        System.out.println("集合创建成功");
    }

    // 获取集合
    @Test
    public void testGetCollections() {
        this.getCollections();
    }

    // 插入文档
    @Test
    public void insertDoc() {
        MongoCollection<Document> collections = this.getCollections();
        Document document = new Document("title", "MongoDB")
                .append("description", "database")
                .append("likes", 100)
                .append("url", "http://www.biancheng.net/mongodb/")
                .append("by", "编程帮");

        collections.insertOne(document);
        System.out.println("文档插入成功");
    }

    // 查询文档
    @Test
    public void testQuery() {
        MongoCollection<Document> collections = this.getCollections();
        Document document1 = new Document("title", "MongoDB")
                .append("description", "database")
                .append("likes", 100)
                .append("url", "http://www.biancheng.net/mongodb/")
                .append("by", "编程帮");
        Document document2 = new Document("title", "html")
                .append("description", "database")
                .append("likes", 200)
                .append("url", "http://www.biancheng.net/html/")
                .append("by", "编程帮");
        List<Document> list = new ArrayList<Document>();
        list.add(document1);
        list.add(document2);
        collections.insertMany(list);

        // 获取 iterable 对象
        FindIterable<Document> iterable = collections.find();
        int i = 0;
        Iterator<Document> it = iterable.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
    }

    // 更新文档
    @Test
    public void testUpdateDoc() {
        MongoCollection<Document> collections = this.getCollections();
        ObjectId objectId = new ObjectId("63510fef64e20d06fdc0e361");
        collections.updateOne(Filters.eq("_id", objectId), Updates.set("likes", 155));
        System.out.println("文档更新成功～");

        // 更新后检索文档
        // 获取 iterable 对象
        FindIterable<Document> iterDoc = collections.find();
        int i = 1;
        // 获取迭代器
        Iterator<Document> it = iterDoc.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
    }

    // 删除文档
    @Test
    public void testDeleteDoc() {
        MongoCollection<Document> collections = this.getCollections();
        collections.deleteMany(Filters.eq("title", "MongoDB"));
        System.out.println("文档删除成功～");

        FindIterable<Document> iterable = collections.find();
        int i = 0;
        Iterator<Document> it = iterable.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
            i++;
        }
    }

    // 删除集合
    @Test
    public void testDeleteCollection() {
        MongoCollection<Document> collection = this.getCollections();
        // 删除集合
        collection.drop();
        this.getMGDatabase().listCollectionNames().forEach((Block<? super String>) System.out::println);
    }

    public MongoCollection<Document> getCollections() {
        MongoDatabase database = this.getMGDatabase();
        MongoCollection<Document> collection = database.getCollection("tutorial");
        System.out.println("集合选择成功:" + collection);
        return collection;
    }

    // 返回数据库连接
    public MongoDatabase getMGDatabase() {
        //连接服务器端的地址和端口号
        ServerAddress serverAddress = new ServerAddress("localhost",27017);

        //连接MongoDB的一个数据库
        MongoClient mongoClient = new MongoClient(serverAddress);

        MongoDatabase mongoDatabase = mongoClient.getDatabase("bianchengbang");
        System.out.println(mongoDatabase);

        //查看链接的MongoDB中的所有的库
        MongoIterable<String> listCollectionNames = mongoDatabase.listCollectionNames();
        for(String st:listCollectionNames) {
            System.out.println(st);
        }

        return mongoDatabase;
    }
}
