package com.gson.lucene.analzer;

import org.apache.lucene.util.AttributeImpl;
import org.apache.lucene.util.AttributeReflector;

/**
 * 词性
 */
public final class PartOfSpeechAttributeImpl extends AttributeImpl
        implements PartOfSpeechAttribute {

    private PartOfSpeech pos = PartOfSpeech.Unknown;

    @Override
    public void setPartOfSpeech(PartOfSpeech pos) {
        this.pos = pos;
    }

    @Override
    public PartOfSpeech getPartOfSpeech() {
        return pos;
    }

    @Override
    public void clear() {
        pos = PartOfSpeech.Unknown;
    }

    @Override
    public void reflectWith(AttributeReflector reflector) {

    }

    @Override
    public void copyTo(AttributeImpl target) {
        ((PartOfSpeechAttribute) target).setPartOfSpeech(pos);
    }
}
