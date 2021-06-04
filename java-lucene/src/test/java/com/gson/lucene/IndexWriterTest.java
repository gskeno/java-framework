package com.gson.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.NIOFSDirectory;
import org.apache.lucene.util.PrintStreamInfoStream;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class IndexWriterTest {

    IndexWriter getIndexWriter() throws IOException, URISyntaxException {
        String indexDirStr = Paths.get(this.getClass().getResource("").toURI()) + "/indexPosition";
        System.out.println(indexDirStr);
        Directory dir = FSDirectory.open(Paths.get(indexDirStr));

        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        // Create a new index in the directory, removing any
        // previously indexed documents:
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        //是否打印输出
        iwc.setInfoStream(new PrintStreamInfoStream(System.out));
        //是否生成复合索引文件
        iwc.setUseCompoundFile(false);

        IndexWriter writer = new IndexWriter(dir, iwc);
        return writer;
    }

    @Test
    public void testCreateIndex() throws URISyntaxException, IOException {
        String indexDirStr = Paths.get(this.getClass().getResource("").toURI()) + "/indexPosition";
        System.out.println(indexDirStr);
        Directory dir = FSDirectory.open(Paths.get(indexDirStr));

        Analyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig iwc = new IndexWriterConfig(analyzer);
        // Create a new index in the directory, removing any
        // previously indexed documents:
        iwc.setOpenMode(IndexWriterConfig.OpenMode.CREATE);

        IndexWriter writer = new IndexWriter(dir, iwc);

        int i;
        // add 100 documents
        Random random = new Random();
        for (i = 0; i < 100; i++) {
            addDocWithIndex(writer, i);
            writer.commit();
        }
        writer.close();
    }

    static void addDocWithIndex(IndexWriter writer, int index) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("content", "aaa " + index, Field.Store.YES));
        doc.add(new TextField("id", "" + index, Field.Store.NO));
        writer.addDocument(doc);
    }

    /**
     * 测试只增加倒排索引
     */
    @Test
    public void testCreateInvertFieldDocIndex() throws IOException, URISyntaxException {
        IndexWriter writer = getIndexWriter();
        Document doc = new Document();
        doc.add(new TextField("info", "study play football ! hi boys", Field.Store.YES));
        writer.addDocument(doc);

        doc = new Document();
        doc.add(new TextField("info", "hi, every one, good play", Field.Store.YES));
        writer.addDocument(doc);

        doc = new Document();
        doc.add(new TextField("info", "play basketball is one good interest", Field.Store.YES));
        writer.addDocument(doc);


        writer.commit();
        writer.close();
    }
    /**
     * 测试生成DocValues, 用于排序、聚合等场景
     * @throws URISyntaxException
     * @throws IOException
     */
    @Test
    public void testCreateDocValuesIndex() throws URISyntaxException, IOException {
        IndexWriter writer = getIndexWriter();
        addNumericDocValues(writer);
        writer.commit();
        writer.close();
    }


    static void addNumericDocValues(IndexWriter writer) throws IOException {
        Document doc = new Document();
        doc.add(new NumericDocValuesField("chinese", 100L));
        doc.add(new NumericDocValuesField("math", 200L));
        doc.add(new NumericDocValuesField("english", 250L));
        //等价于 doc.add(new TextField("info", "study play", Field.Store.YES));
        doc.add(new TextField("info", "study", Field.Store.YES));
        doc.add(new TextField("info", "play", Field.Store.YES));

        writer.addDocument(doc);

        doc = new Document();
        doc.add(new NumericDocValuesField("chinese", 150L));
        doc.add(new NumericDocValuesField("math", 250L));
        doc.add(new NumericDocValuesField("english", 320L));
        doc.add(new TextField("info", "school", Field.Store.YES));
        writer.addDocument(doc);

        doc = new Document();
        doc.add(new NumericDocValuesField("chinese", 150L));
        doc.add(new NumericDocValuesField("math", 250L));
        doc.add(new NumericDocValuesField("english", 300L));
        doc.add(new TextField("info", "school", Field.Store.YES));
        writer.addDocument(doc);
    }


    /**
     * 普通的测试添加文档
     */
    @Test
    public void testAddDoc() throws IOException, URISyntaxException {
        IndexWriter writer = getIndexWriter();
        List<Document> docs = new ArrayList<>();

        Document doc = new Document();
        doc.add(new TextField("content", "china good", Field.Store.YES));
        doc.add(new StringField("id", "a", Field.Store.YES));
        docs.add(doc);

        doc = new Document();
        doc.add(new TextField("content", "people growth", Field.Store.YES));
        doc.add(new StringField("id", "b", Field.Store.YES));
        docs.add(doc);

        writer.addDocuments(docs);
    }

    /**
     * 简易版的add and commit
     * @throws IOException
     */
    @Test
    public void testAddAndCommit() throws IOException, URISyntaxException {
        //在test-classes目录下找索引文件
        String indexDir = Paths.get(this.getClass().getResource("").toURI()) + "/index";
        Directory directory =   FSDirectory.open(Paths.get(indexDir));

        IndexWriterConfig config = new IndexWriterConfig();
        IndexWriter writer = new IndexWriter(directory, config);

        Document doc = new Document();
        doc.add(new TextField("title", "Lucene - IndexWriter", Field.Store.YES));
        doc.add(new StringField("author", "you ning", Field.Store.YES));

        writer.addDocument(doc);
        writer.commit();
    }
}
