package com.gson.lucene;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.NumericDocValuesField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.InfoStream;
import org.apache.lucene.util.PrintStreamInfoStream;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Random;

public class IndexWriterTest {

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
            if (random.nextBoolean()) {
                writer.commit();
            }
        }
        writer.close();
    }
    static void addDocWithIndex(IndexWriter writer, int index) throws IOException {
        Document doc = new Document();
        doc.add(new TextField("content", "aaa " + index, Field.Store.YES));
        doc.add(new TextField("id", "" + index, Field.Store.NO));
        writer.addDocument(doc);
    }

    @Test
    public void testCreateDocValuesIndex() throws URISyntaxException, IOException {
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
        addNumericDocValues(writer);
        writer.commit();
        writer.close();
    }


    static void addNumericDocValues(IndexWriter writer) throws IOException {
        Document doc = new Document();
        doc.add(new NumericDocValuesField("chinese", 100L));
        doc.add(new NumericDocValuesField("math", 200L));
        doc.add(new NumericDocValuesField("english", 250L));
        doc.add(new TextField("info", "study", Field.Store.YES));

        writer.addDocument(doc);

        doc = new Document();
        doc.add(new NumericDocValuesField("chinese", 150L));
        doc.add(new NumericDocValuesField("math", 250L));
        doc.add(new NumericDocValuesField("english", 320L));
        doc.add(new TextField("info", "school", Field.Store.YES));


        writer.addDocument(doc);
    }

    @Test
    public void testShift(){
        System.out.println(0b1 <<14);
    }


}
