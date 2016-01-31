/**
 * Administrator
 * 2015-5-19
 */
package com.os.comment.utils;

import com.os.entity.user.User;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.*;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.SortField.Type;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleFragmenter;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.File;
import java.io.StringReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Administrator
 * 2015-5-19
 */
public class Lucene {
	
	// 分词器
    private static Analyzer analyzer_index = new StandardAnalyzer(Version.LUCENE_46);
	/**
	1.Analyze:分词器，要搜索，肯定要对搜索的内容进行分词吧。
    2.Directory:目录，即文件夹所在位置。
    3.IndexWriter:索引输出流，用来在制定目录创建索引文件。
    4.Document:源，索引是以源为单位进行索引的。
    5.Field:信息域，存储数据的最小单位，多个信息域组成一个源。
    6.DirectoryReader:用来读入索引文件流。
    7.IndexSearcher:搜索神器，用来搜索索引。
    8.TermQuery:搜索的内容对象
	 ***/
	public static void main(String[] args){
		//索引所在目录
		String pathFile="D://lucene/dir";
		int type=0;
///////////////////读取文件///////////////////////////////
		//文件读取目录
		//String filePath="D://lucene";	
		//String[] txtArray=getFiles(filePath,"txt");
		
///////////////////读取list///////////////////////////////	
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			//关键字
			String wordKey="三";
			List<User> userList=new ArrayList<>();
			User user=new User();user.setId(1l);user.setNickname("张三"); 
			User user1=new User();user1.setId(2l);user1.setNickname("三李四,张三");
			User user2=new User();user2.setId(3l);user2.setNickname("123");
			userList.add(user);userList.add(user1);userList.add(user2);
			if(userList!=null){
				//创建索引
				writer(pathFile,userList);
				//读取索引
				reader(pathFile,wordKey,0,0,3,1);//type:0name 1age ；；； sorter:0默认  1 根据时间排序
				//reader('索引保存路径','关键字','查询类型','排序类型','每页显示条数','当前页数')
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 建立索引
	 * @param pathFile 索引生成目录
	 * @param valueList 源数据集合
     */
	private static void writer(String pathFile,List<?> valueList){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			 Directory dir=null;
 			 long beginTime=System.currentTimeMillis();
 			 File file=new File(pathFile);
 			 if(!file.exists()){
 				 file.mkdirs();
 			 }
			 dir=FSDirectory.open(file);
			 IndexWriterConfig config=new IndexWriterConfig(Version.LUCENE_46,analyzer_index);
			 IndexWriter write=new IndexWriter(dir,config);
			 //删除所有索引
			 write.deleteAll();
			 List<User> userList=(List<User>) valueList;
			 for(User u:userList){
				 Document doc=new Document();
				 doc.add(new StringField("id",u.getId()+"", Store.YES));
				 doc.add(new TextField("name", u.getNickname(),Store.YES));
//				 doc.add(new TextField("age", u.getAge()+"",Store.YES));
//				 doc.add(new TextField("time",sdf.format( u.getTime()),Store.YES));
				/**
				 更新索引 write.updateDocument(new Term("content", "123"), doc);
				 //根据Term删除
			     write.deleteDocuments(new Term("id", 1));
				 //删除全部索引
				 write.deleteAll();
				**/
				 write.addDocument(doc);
			 }
			 long endTime=System.currentTimeMillis();
			 System.out.println("建立索引成功,用时:"+(endTime-beginTime)+"毫秒");
			 System.out.println("write里面有多少个索引:" + write.numRamDocs());
			 write.close();
			 dir.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 搜索索引
	 * @param pathFile 索引保存路径
	 * @param wordKey 关键字
	 * @param type 查询类型
	 * @param sorter 排序类型
	 * @param pageSize 每页显示条数
     * @param curPage 当前页数
     */
	private static void reader(String pathFile,String wordKey,int type,int sorter,int pageSize,int curPage){
		try {
			long beginTime=System.currentTimeMillis();
			 Directory dir=FSDirectory.open(new File(pathFile));
			 IndexReader reader=DirectoryReader.open(dir);
			 IndexSearcher searcher=new IndexSearcher(reader);
//			 Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_46);
			 QueryParser parser = null;
			 if(type==1){
				 parser = new QueryParser(Version.LUCENE_46, "age",analyzer_index);
			 }else{
				 parser = new QueryParser(Version.LUCENE_46, "name",analyzer_index);
			 }
			// 将关键字包装成Query对象
			Query query = parser.parse(wordKey);
			/**
			MUST和MUST：取得连个查询子句的交集。 
			MUST和MUST_NOT：表示查询结果中不能包含MUST_NOT所对应得查询子句的检索结果。
			MUST_NOT和MUST_NOT：无意义，检索无结果。   
			SHOULD与MUST、SHOULD与MUST_NOT：SHOULD与MUST连用时，无意义，结果为MUST子句的检索结果。与MUST_NOT连用时，功能同MUST。   
			SHOULD与SHOULD：表示“或”关系搜索，最终检索结果为所有检索子句的并集。
			**/
			 Sort sort=null;
			 // 排序
			 if(sorter==1){
				 sort=new Sort(new SortField("time", Type.STRING, true));//根据时间排序
			 }else{
				 sort=new Sort(new SortField(null, Type.SCORE,false));//根据相似度排序	 
			 }
//			 Sort sort=new Sort(new SortField("time", Type.STRING,true));//根据时间排序
			 //综合查询
//			Sort sort=new Sort(new SortField[]{new SortField("name", Type.STRING, true),new SortField("name", Type.tTRING, true)});
			 // 使用过滤器  
			 QueryWrapperFilter filter = new QueryWrapperFilter(new TermQuery(new Term("time", "2014-01-23"))); // 注意这里需要小写，使用Luke查看索引的時候就可以發現分詞器都把大寫轉換成小寫  
//			 TopDocs topDoc=searcher.search(query,filter,1000,sort);
			 TopDocs topDoc=searcher.search(query,1000,sort);
			 ScoreDoc[] hits =topDoc.scoreDocs;
			 if(hits==null||hits.length==0){
				 System.out.println("抱歉，没有你想要查找的内容");
			 }
			 else{
				 System.out.println("查询结果总数："+topDoc.totalHits);
			   //查询起始记录位置
		        int begin = pageSize * (curPage  - 1);
		        //查询终止记录位置
		        int end = Math.min(begin + pageSize, hits.length);
				 /////////高亮显示
				 for (int i=begin;i<end;i++) {
					 Document document = searcher.doc(hits[i].doc);
					 String content = document.get("name");
					 System.out.println("id:"+document.get("id")+"--name:"+document.get("name")+"--age:"+document.get("age")+"--time:"+document.get("time"));
					 if(type!=1){
					 // 高亮展示
					 SimpleHTMLFormatter simpleHTMLFormatter = new SimpleHTMLFormatter("<", ">");
					 Highlighter highlighter = new Highlighter(simpleHTMLFormatter,new QueryScorer(query));
					 highlighter.setTextFragmenter(new SimpleFragmenter(content.length()+1));
					 if (!"".equals(content)) {
						 TokenStream tokenStream = new StandardAnalyzer(Version.LUCENE_46).tokenStream(content, new StringReader(content));
						 String bestFragment = highlighter.getBestFragment(tokenStream, content);
						 System.out.println("高亮显示：" + "检索结果如下所示:");
						 System.out.println(bestFragment);
						 // 结束关键字高亮
						 System.out.println("-----------------");
					 }
				}
			}	
			 long endTime=System.currentTimeMillis();
			 System.out.println("查询结束:"+(endTime-beginTime)+"毫秒");
			 System.out.println("显示条数" +pageSize+"   "+"当前页数："+curPage+"/"+getTotalPage(pageSize, hits.length));
		 }
		 reader.close();
		 dir.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	/**
	 * 获取分页总页数
	 * @param pageSize 每页显示条数
	 * @param num 总条数
     * @return
     */
	private static int getTotalPage(int pageSize,int num){
		if(num%pageSize==0){
			 return num/pageSize;
		 }else{
			 return num/pageSize+1;
		 }
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	////////////////////////////读取文件///////////////////////////////////////////////////////
	
	/**
	 * 获取路径下的所有文件
	 * @return
	 */
//	private static String[] getFiles(String filePath,String file){
//		String txtList="";
//		try {
//			File fileDir =new File(filePath); 
//			File[] textFiles =  fileDir.listFiles();
//			for(int i=0;i<textFiles.length;i++){   
//				if(file.equals("txt")){
//					if(textFiles[i].isFile()&&textFiles[i].getName().endsWith(".txt")){
//						System.out.println(textFiles[i].getName()+"正在被索引。。。");
//						InputStream is = new FileInputStream(fileDir+"/"+textFiles[i].getName());
//						String txt= getTxt(is);
//						txtList+=txt+",";
//					}
//				}
//				else if(file.equals("pdf")){
//					if(textFiles[i].isFile()&&textFiles[i].getName().endsWith(".pdf")){
//						System.out.println(textFiles[i].getName()+"正在被索引。。。");
//						InputStream is = new FileInputStream(fileDir+"/"+textFiles[i].getName());
//						String txt= getPdf(is);
//						txtList+=txt+",";
//					}
//				}
//			
//			}
//			return txtList.substring(0,txtList.length()-1).split(",");
//		} catch (Exception e) {
//			// TODO: handle exception
//			System.out.println("请核对文件路径是否正确");
//			e.printStackTrace();
//		}
//		return null;
//	}
	
	/**
	 * 读取单个txt文本信息
	 * @param is
	 * @return
	 */
//	private static String getTxt(InputStream is) {  
//        StringBuilder content = new StringBuilder();  
//        try {  
//            BufferedReader br = new BufferedReader(new InputStreamReader(is, "gbk"));  
//            String s1 = null;  
//            while ((s1 = br.readLine()) != null) {  
//                content.append(s1 + "\r");  
//            }  
//            br.close();  
//            return content.toString();  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        }  
//        return null;  
//	}  
	/**
	 * 读取单个pdf
	 * @param is
	 * @return
	 */
//	public static String getPdf(InputStream is) {  
//        PDFParser parser;  
//        try {  
//            parser = new PDFParser(is);  
//            parser.parse();  
//            COSDocument cosDoc = parser.getDocument();  
//            if (cosDoc.isEncrypted()) {  
//                throw new RuntimeException("the file may be encrypted!");  
//            }  
//            PDFTextStripper stripper = new PDFTextStripper();  
//            return stripper.getText(new PDDocument(cosDoc));  
//        } catch (Exception e) {  
//            // TODO Auto-generated catch block  
//            e.printStackTrace();  
//        }  
//        return null;  
//    }  
}
