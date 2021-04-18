package com.gson;

import com.gson.lucene.IndexFiles;
import com.gson.lucene.SearchFiles;
import org.apache.lucene.util.LuceneTestCase;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
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

}
