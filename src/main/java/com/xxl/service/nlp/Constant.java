package com.xxl.service.nlp;

public interface Constant {

    /** 待训练的词典文件 */
    public static final String inputSerDictionarys = "data/dict/aifang-spec.dict" +
            ",data/dict/aifang-loupan.dict" +
            ",data/dict/aifang-metro.dict" +
            ",data/dict/aifang-other.dict" +
            ",data/dict/aifang-regions.dict" +
            ",data/dict/aifang-road.dict" +
            ",data/dict/aifang-synonyms.dict" ;
    /** 训练好的词典文件 */
    public static final String aifangSerDictionary = "data/aifang-chris.ser.gz";
    /** 原始词典文件 */
    public static final String serDictionary = "data/dict-chris8.ser.gz";
    /** 词典文件集合 */
    public static final String serDictionarys = serDictionary+","+aifangSerDictionary;

    /** 待分词文件  */
    public static final String segFile = "data/train/aifang-seg";
    /** 待训练的已分词文件  */
//	public static final String trainFiles = "data/train/aifang-ctb.train.seg,data/train/ctb6.train.seg";
    public static final String trainFiles = "data/train/aifang-ctb.train.seg";

    public static final String testFiles = "data/train/aifang-seg";

    /** 训练好的爱房CTB词库(序列化)  */
    public static final String aifangCTBClassifier = "data/aifang-ctb.ser.gz";
    /** 训练好的爱房CTB词库(未序列化)  */
    public static final String aifangCTBClassifierText = "data/aifang-ctb.gz";


    /** CTB原始词库  */
    public static final String CTBClassifier = "data/ctb/ctb.gz";
    /** PKU原始词  */
    public static final String PKUClassifier = "data/pku/pku.gz";


}
