package com.gson;

import com.gson.lucene.IndexFiles;
import com.gson.lucene.SearchFiles;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.IOUtils;
import org.apache.lucene.util.LuceneTestCase;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class TestDemo extends LuceneTestCase {
    private void testOneSearch(Path indexPath, String query, int expectedHitCount) throws Exception {
        PrintStream outSave = System.out;
        try {
            ByteArrayOutputStream bytes = new ByteArrayOutputStream();
            PrintStream fakeSystemOut = new PrintStream(bytes, false, Charset.defaultCharset().name());
            System.setOut(fakeSystemOut);
            SearchFiles.main(new String[]{"-query", query, "-index", indexPath.toString()});
            fakeSystemOut.flush();
            System.setOut(outSave);
            String output = bytes.toString(Charset.defaultCharset().name()); // intentionally use default encoding
            System.out.println("output=" + output);
            assertTrue("output=" + output, output.contains(expectedHitCount + " total matching documents"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.setOut(outSave);
        }
    }

    @Test
    public void testIndexSearch() throws Exception {
        Path docDir = getDataPath("test-files/docs");
        System.out.println("docDir" + docDir);
        String indexDirStr = Paths.get(this.getClass().getResource("").toURI()) + "/ContribDemoTest";
        System.out.println("indexDirStr=" + indexDirStr);
        Path indexDir = Paths.get(indexDirStr);
        IndexFiles.main(new String[]{"-create", "-docs", docDir.toString(), "-index", indexDir.toString()});
        testOneSearch(indexDir, "apache", 3);
        testOneSearch(indexDir, "patent", 8);
        testOneSearch(indexDir, "lucene", 0);
        testOneSearch(indexDir, "gnu", 6);
        testOneSearch(indexDir, "derivative", 8);
        testOneSearch(indexDir, "license", 13);
    }

    /**
     * https://lucene.apache.org/core/8_8_2/core/overview-summary.html#overview.description
     * @throws IOException
     * @throws ParseException
     */
    @Test
    public void testAddDocAndSearch() throws IOException, ParseException {
        Analyzer analyzer = new StandardAnalyzer();

        Path indexPath = Files.createTempDirectory("tempIndex");
        System.out.println("indexPath=" + indexPath);
        Directory directory = FSDirectory.open(indexPath);
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        IndexWriter iwriter = new IndexWriter(directory, config);
        Document doc = new Document();
        String text = "This is the text to be indexed.";
        doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
        iwriter.addDocument(doc);
        iwriter.close();

        // Now search the index:
        DirectoryReader ireader = DirectoryReader.open(directory);
        IndexSearcher isearcher = new IndexSearcher(ireader);
        // Parse a simple query that searches for "text":
        QueryParser parser = new QueryParser("fieldname", analyzer);
        Query query = parser.parse("text");
        ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;
        assertEquals(1, hits.length);
        // Iterate through the results:
        for (int i = 0; i < hits.length; i++) {
            //ScoreDoc.doc 表示文档id
            //ScoreDoc.score 表示文档相关度
            Document hitDoc = isearcher.doc(hits[i].doc);
            assertEquals("This is the text to be indexed.", hitDoc.get("fieldname"));
        }
        ireader.close();
        directory.close();
        IOUtils.rm(indexPath);
    }

}
