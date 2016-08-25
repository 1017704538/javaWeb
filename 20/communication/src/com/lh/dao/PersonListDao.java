package com.lh.dao;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.lh.model.PersonInfo;
import com.lh.model.PersonType;
import com.lh.model.User;
/**
 * 处理通讯录信息的DAO类
 * @author LH
 * 
 */
public class PersonListDao {
	private static PersonListDao instance=null;
	/**
	 *单例模式，只有在该类的实例不存的情况下才
	 *返回一个PersonListDao实例
	 * @return
	 */
	public static PersonListDao getInstance(){
		if(instance==null) instance = new PersonListDao();
		return instance;
	}
	/**
	 * 保存通讯录信息
	 * @param person：传入的通讯信息实例
	 * @return boolean:保存成功true， 保存失败false 
	 */
	public boolean savePersonInfo(PersonInfo person){
		boolean result = false;    //result标记信息是否保存成功
		Session session = null;   //创建一个session实例
		Transaction trans = null; //创建一个事务
		try{
			session=SessionFactoryProvider.getSession();//创建Session实例
			trans=session.beginTransaction();//获取事务
			trans.begin();        //开始事务
			session.save(person); //保存对象
			trans.commit();      //提交事务
			result=true;         //保存成功，result值改为true
		}catch(Exception ex){
			//如果出现异常，事务回滚
			if(trans!=null&&trans.isActive()) trans.rollback();
			ex.printStackTrace();
		}finally{
			session.close();//关闭 Session
		}
		return result;//返回res标记结果
	}
	/**
	 * 根据通讯录编号返回一个PersonInfo实例
	 * @param id ：通讯录信息编号，对应表的主键
	 * @return 
	 */
	public PersonInfo selectPersonInfoById(String id){
		PersonInfo person = null;//创建通讯录信息实例
		Session session = null;  //创建session实例
		try{
			//从SessionFactory工厂类中获取Session实例
			session=SessionFactoryProvider.getSession();
			//根据唯一编号查找PersonInfo实例，该对象映射数据库的某一条信息
			Query query=session.createQuery("From PersonInfo where pId=:id");
			//传入的参数赋值给编号
			query.setString("id", id);
			if(query.list()!=null&&query.list().size()>0){
				//从query对象的List集合中获取PersonInfo实例
				person=(PersonInfo)query.list().get(0);
			
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();//关闭Session
		}
		return person;
	}
	/**
	 * 根据通讯录编号删除通讯信息
	 * @param id：通讯录信息编号，对应表的主键
	 * @return 删除成功true,删除失败false
	 */
	
public boolean deletePersonInfoById(String id){
	Session session=null; 
	Transaction trans=null;
	try{
		session=SessionFactoryProvider.getSession();//创建Session实例
		trans=session.beginTransaction();//创建事务
		trans.begin();//开始事务
		String hql = "delete from PersonInfo where pId=:id";//HQL删除语句
		Query query=session.createQuery(hql);//创建控制执行查询的Query对象
		query.setString("id", id);//HQL语句的参数赋值
		int i=query.executeUpdate();//执行删除，返回删除所影响的行数
		trans.commit();//提交事务
		if(i==1) 
			return true;
	}catch(Exception ex){
		if(trans!=null&&trans.isActive()) 
			trans.rollback();//出现异常将事务回滚	
		ex.printStackTrace();
	}finally{
		session.close();//关闭Session
	}
	return false;
}
	/**
	 * 返回所有通讯录信息
	 * @return PersonInfo对象集合
	 */
	public List<PersonInfo> getPersonListInfo(User user){
		List<PersonInfo> list = null;//用于存储PersonInfo对象的集合
		Session session=null;//创建Session
		try{
			//从SessionFactory工厂类中获取Session实例
			session=SessionFactoryProvider.getSession();
			//查询所有的通讯录信息
			Query query=session.createQuery("From PersonInfo where user=:user");
			query.setEntity("user", user);
			list=query.list();//获取PersonInfo对象集合
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();//关闭session
		}
		return list;
	}
	/**
	 * 分页查询某用户下的所有通讯录
	 * @param user
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	
	public List<PersonInfo> getPersonListInfo(User user,int firstResult,int maxResult,String flagOrder){
		List<PersonInfo> list = null;//声明用于存储PersonInfo对象的集合
		Session session=null;
		try{
			session=SessionFactoryProvider.getSession();//创建Session实例
			StringBuffer hql = new StringBuffer();
			hql.append("From PersonInfo where user=:user");
			if(flagOrder.equals("0")){
				hql.append(" order by pName desc");
			}
			else{
				hql.append(" order by pName asc");
			}
			Query query=session.createQuery(hql.toString());//创建Query对象
			query.setEntity("user", user);
			query.setFirstResult(firstResult);
			query.setMaxResults(maxResult);
			list=query.list();//获取PersonInfo对象集合
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();//关闭session
		}
		return list;
	}

	/**
	 * 返回某用户下通讯信息总条数，用于查询分页
	 * @return 
	 */

public int getInfoCount(User user){
	int count=0;
	Session session = null;
	try{	
		session = SessionFactoryProvider.getSession();//创建Session实例
		String hql = "select count(pId) from PersonInfo where user=:user";
		Query query =session.createQuery(hql);
		query.setEntity("user", user);
		if(query.list()!=null&&query.list().size()>0){//判断list是否为空
			//查询出的list集合只有一条信息，所以直接用list.get(0)转换的int值赋值给count
			count=Integer.parseInt(query.list().get(0).toString());
		}
	}catch(Exception ex){
		ex.printStackTrace();
	}finally{
		session.close();//关闭session
	}
	return count;
}
	/**
	 * 更新通讯录信息
	 * @param 通讯录信息对象
	 * @return 更新成功true,更新失败false
	 */
	public boolean updatePersonInfoById(PersonInfo personinfo){
		boolean result=false;
		Session session=null;//创建session
		Transaction trans=null;//创建事务
		try{
			//从SessionFactory工厂类中获取Session实例
			session = SessionFactoryProvider.getSession();
			trans=session.beginTransaction();//获取事务
			trans.begin();//开始事务
			session.update(personinfo);//更新对象信息
			trans.commit();//提交事务
			result=true;
		}catch(Exception ex){
			if(trans!=null&&trans.isActive()) trans.rollback();
			ex.printStackTrace();
		}finally{
			session.close();//关闭session
		}
		return result;
	}
	/**
	 * 根据姓名和类别查询信息
	 * @param user :用户对象
	 * @param name ：姓名
	 * @param type ：类别对象
	 * @return PersonInfo对象集合
	 */
	
	public List<PersonInfo> selectPersonInfo(User user,String name,PersonType type){
		List<PersonInfo> list = null;//声明用于存储查询出的PersonInfo对象的集合
		Session session = null;
		try{
			session= SessionFactoryProvider.getSession();	//创建Session实例
			StringBuffer hql=new StringBuffer();			//StringBuffer用于动态连接的HQL查询语句字符串
			hql.append("from PersonInfo where user=:user ");//定义查询该用户的所有讯友对象
			if(name!=null&&!name.equals(""))
				hql.append("and pName like :name ");		//姓名的模糊查询条件
			if(type!=null)
				hql.append("and type=:type ");				//类别条件		
			Query query=session.createQuery(hql.toString());//创建query对象,执行HQL语句
			query.setEntity("user", user);					//设置Where条件中的用户对象参数
			if(name!=null&&!name.equals(""))
				query.setString("name", "%"+name+"%");		//设置 Where条件中的姓名参数
			if(type!=null)
				query.setEntity("type", type);				//设置 Where条件中的类别对象参数
			list=query.list();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();//关闭session
		}
		return list;
	}
	/**
	 * 根据姓名和类别查询信息总数
	 * @param user :用户对象
	 * @param name ：姓名
	 * @param type ：类别对象
	 * @return PersonInfo对象集合
	 */
	public int selectPersonInfoCount(User user,String name,PersonType type){
		int count=0;
		Session session = null;//创建session
		try{
			//从SessionFactory工厂类中获取Session实例
			session= SessionFactoryProvider.getSession();
			//这里的StringBuffer用于动态连接的HQL查询语句字符串
			StringBuffer hql=new StringBuffer();
			hql.append("select count(pId) from PersonInfo where user=:user ");	
			//如果参数name有值，在hql语句尾加上name的条件
			if(name!=null&&!name.equals("")){
				hql.append("and pName like :name ");
			}
			//如果参数type有值，加上type条件
			if(type!=null){
				hql.append("and type=:type ");
			}
			//创建query,执行HQL语句
			Query query=session.createQuery(hql.toString());
			//设置用户对象
			query.setEntity("user", user);
			//设置姓名，根据姓名进行模糊查询
			if(name!=null&&!name.equals("")){
				query.setString("name", "%"+name+"%");
			}
			//设置类别
			if(type!=null){
				query.setEntity("type", type);	
			}
			//获取PersonInfo集合
			if(query.list()!=null&&query.list().size()>0){
				count=Integer.parseInt(query.list().get(0).toString());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();//关闭session
		}
		return count;
	}
	/**
	 * 分页查询某个用户下的通讯录信息
	 * @param user 注册的用户对象
	 * @param name 姓名
	 * @param type 类别
	 * @param first 查询的开始位置
	 * @param maxcount 查询的最大条数
	 * @return
	 */
	public List<PersonInfo> selectPersonInfo(User user, String name,PersonType type,int first,int maxcount,String orderFlag ){
		List<PersonInfo> list = null;//用于存储获取的PersonInfo对象
		Session session = null;//创建session
		try{
			//从SessionFactory工厂类中获取Session实例
			session= SessionFactoryProvider.getSession();
			//这里的StringBuffer用于动态连接的HQL查询语句字符串
			StringBuffer hql=new StringBuffer();
			hql.append("from PersonInfo where user=:user ");		
			//如果参数name有值，在hql语句尾加上name的条件
			if(name!=null&&!name.equals("")){
				hql.append("and pName like :name ");
			}
			//如果参数type有值，加上type条件
			if(type!=null){
				hql.append("and type=:type ");
			}
			
			if(orderFlag.equals("0")){
				hql.append(" order by pName desc");
			}
			else{
				hql.append(" order by pName asc");
			}
			//创建query,执行HQL语句
			Query query=session.createQuery(hql.toString());
			query.setEntity("user", user);
			//设置姓名，根据姓名进行模糊查询
			if(name!=null&&!name.equals("")){
				query.setString("name", "%"+name+"%");
			}
			//设置类别
			if(type!=null){
				query.setEntity("type", type);	
			}
			//设置查询的起始位置
			query.setFirstResult(first);
			//设置返回信息的最大条数
			query.setMaxResults(maxcount);
			//获取PersonInfo集合
			list=query.list();
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();//关闭session
		}
		return list;
	}
	/**
	 * 根据分页，查询数据的总条数
	 * @param user 用户对象
	 * @param name 姓名
	 * @param type 类别
	 * @param first 查询的开始位置
	 * @param maxcount 查询的最大条数
	 * @return
	 */
	public int selectPersonInfoCountByCondition(User user,String name,PersonType type,int first,int maxcount){
		int selectCount=0;
		Session session = null;//创建session
		try{
			//从SessionFactory工厂类中获取Session实例
			session= SessionFactoryProvider.getSession();
			//这里的StringBuffer用于动态连接的HQL查询语句字符串
			StringBuffer hql=new StringBuffer();
			hql.append("select count(pId) from PersonInfo where user=:user ");		
			//如果参数name有值，在hql语句尾加上name的条件
			if(name!=null&&!name.equals("")){
				hql.append("and pName like :name ");
			}
			//如果参数type有值，加上type条件
			if(type!=null){
				hql.append("and type=:type ");
			}
			//创建query,执行HQL语句
			Query query=session.createQuery(hql.toString());
			query.setEntity("user", user);
			//设置姓名，根据姓名进行模糊查询
			if(name!=null&&!name.equals("")){
				query.setString("name", "%"+name+"%");
			}
			//设置类别
			if(type!=null){
				query.setEntity("type", type);	
			}
			//设置查询的起始位置
			query.setFirstResult(first);
			//设置返回信息的最大条数
			query.setMaxResults(maxcount);
			if(query.list()!=null&&query.list().size()>0){
				//获取查询条数
				selectCount=Integer.parseInt(query.list().get(0).toString());
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}finally{
			session.close();//关闭session
		}
		return selectCount;
	}
}
