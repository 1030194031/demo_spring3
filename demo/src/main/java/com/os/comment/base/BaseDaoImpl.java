package com.os.comment.base;

import com.os.comment.handle.page.PageEntity;
import com.os.comment.handle.page.PageOL;
import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Administrator
 * 2015-5-12
 * @param <T>
 */
public class BaseDaoImpl implements BaseDao {
	@Resource
	private SqlSessionTemplate sqlSession;

	@Override
	public <T> T selectOne(String namespace, Object obj) {
		// TODO Auto-generated method stub
		return sqlSession.selectOne(namespace, obj);
	}

	@Override
	public int insert(String namespace, Object obj) {
		// TODO Auto-generated method stub
		return sqlSession.insert(namespace, obj);
	}

	@Override
	public <T> List<T> selectList(String namespace, Object obj) {
		// TODO Auto-generated method stub
		return sqlSession.selectList(namespace, obj);
	}

	@Override
	public int delete(String namespace, Object obj) {
		// TODO Auto-generated method stub
		return sqlSession.delete(namespace, obj);
	}

	@Override
	public int update(String namespace, Object obj) {
		// TODO Auto-generated method stub
		return sqlSession.update(namespace, obj);
	}

	@Override
	public <T> List<T> queryForPage(String namespace, Object obj, PageEntity page) {
		// TODO Auto-generated method stub
		// 查询总行数  
        Map<String, Object> map = new HashMap<String, Object>();  
        map.put("e", obj);  
        PageOL pageOL = new PageOL();
        pageOL.setStartPara((page.getCurrentPage() - 1) * page.getPageSize());
        pageOL.setLimitPara(page.getPageSize());  
        map.put("page", pageOL);  
          
        
        //查询总数
        Integer objectscount = this.selectOne(  
        		namespace+ "Count",  
                map);  
  
        if (objectscount == null || objectscount == 0) {  
            page.setTotalCount(0);
            int totalPageSize = (page.getTotalCount() - 1)  
                    / page.getPageSize() + 1;  
            page.setTotalPageSize(totalPageSize);  
            return null;  
        } else {  
            page.setTotalCount(objectscount);  
            int totalPageSize = (page.getTotalCount() - 1)  
                    / page.getPageSize() + 1;  
            page.setTotalPageSize(totalPageSize);  
            return this.selectList(namespace, map);  
        }  
	}
}
