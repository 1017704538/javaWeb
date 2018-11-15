package com.lh.dao;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.lh.model.PersonType;
/**
 * 管理联系人类别的DAO类
 * @author LH
 */
public class PersonTypeDao {
	private static PersonTypeDao instance=null;
	/**
	 * 静态方法，返回一个PersonTypeDao实例
	 * @return
	 */
	public static PersonTypeDao getInstance(){
		if(instance==null){
			instance = new PersonTypeDao();
		}
		return instance;
	}
	/**
	 * 根据id查询类别对象
	 * @param id 类别编号
	 * @return
	 */
	public PersonType selectPersonTypeById(String id){
		PersonType type = null;
		Session session = null;
		try{
			session = SessionFactoryProvider.getSession();
			Query query=session.createQuery("from PersonType where typeId=:id");
			query.setString("id", id);
			if(query.list()!=null&&query.list().size()>0){
				type=(PersonType)query.list().get(0);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return type;
	}
	/**
	 * 保存或更新类别对象
	 * @param type 类别对象
	 * @return 成功true,失败false
	 */

public boolean saveOrUpdateType(PersonType type){
	boolean result=false;
	Session session=null;
	Transaction trans = null;
	try{
		session=SessionFactoryProvider.getSession();//创建Session对象
		trans=session.beginTransaction();			//创建事务
		trans.begin();								//开始事务
		session.saveOrUpdate(type);					//保存或更新对象
		trans.commit();								//提交事务
		result=true;
	}catch(Exception ex){
		if(trans!=null&&trans.isActive()){
			trans.rollback();						//出现异常，事务回滚
		}
		ex.printStackTrace();
	}finally{
		session.close();
	}
	return result;
}
	/**
	 * 删除类别
	 * @param type
	 * @return
	 */
	public boolean deleteType(PersonType type){
		boolean result=false;
		Session session=null;
		Transaction trans=null;
		try{
			session=SessionFactoryProvider.getSession();
			trans=session.beginTransaction();
			trans.begin();
			session.delete(type);
			trans.commit();
			result=true;
		}catch(Exception ex){
			if(trans!=null&&trans.isActive()){
				trans.rollback();
			}
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return result;
	}
	/**
	 * 查询类别信息，返回一个类别对象集合
	 * @return
	 */
	public List<PersonType> getType(){
		List<PersonType> list = null;
		Session session=null;
		try{
			session = SessionFactoryProvider.getSession();
			Query query=session.createQuery("From PersonType");
			list=query.list();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
	/**
	 * 查询类别信息，返回类别信息总数
	 * @return
	 */
	public int getTypeCount(){
		int count = 0;
		Session session=null;
		try{
			session = SessionFactoryProvider.getSession();
			Query query=session.createQuery("select count(id) from PersonType");
			if(query.list()!=null&&query.list().size()>0){
				count = Integer.parseInt(query.list().get(0).toString());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return count;
	}
	/**
	 * 分页查询类别信息
	 * @param first:查询的起始位置
	 * @param maxResult:每页查询的最多条数
	 * @return List:类别对象集合
	 */
	public List<PersonType> getTypePage(int first,int maxResult){
		List<PersonType> list = null;
		Session session=null;
		try{
			session = SessionFactoryProvider.getSession();
			Query query=session.createQuery("From PersonType");
			query.setFirstResult(first);
			query.setMaxResults(maxResult);
			list = query.list();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();
		}
		return list;
	}
}
