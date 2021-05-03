package com.gson.lucene;

import org.apache.lucene.analysis.tokenattributes.CharTermAttributeImpl;
import org.apache.lucene.analysis.tokenattributes.KeywordAttributeImpl;
import org.apache.lucene.analysis.tokenattributes.PackedTokenAttributeImpl;
import org.apache.lucene.util.AttributeSource;
import org.junit.Test;

public class AttributeSourceTest {

    @Test
    public void test(){
        CharTermAttributeImpl keywordAttributeImpl = new CharTermAttributeImpl();

        AttributeSource attributeSource = new AttributeSource();
        attributeSource.addAttributeImpl(keywordAttributeImpl);

    }
}
