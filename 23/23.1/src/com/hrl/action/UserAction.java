package com.hrl.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.hrl.dao.IUserDao;
import com.hrl.model.User;
import com.hrl.util.JSONKit;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends DefaultAction {
	private static final long serialVersionUID = 1L;
	private User user = null;
	private IUserDao userDao;
	private boolean hasLogin = false;
	private String oldPassword = null;

	/**
	 * 杩涘叆鍒版敞鍐岀敤鎴烽〉闈�
	 * 
	 * @return
	 */
	public String register() {
		return "toRegisterPage";
	}

	/**
	 * 娣诲姞鐢ㄦ埛
	 * 
	 * @return
	 */
	public String addUser() {
		user.setRegisterTime(new Date());
		user.setIsAdmin("0");
		userDao.addUser(user);
		this.setCurrUser(user);
		this.setMsg("涓汉娉ㄥ唽鎴愬姛锛�");
		return "toSuccessPage";
	}

	/**
	 * 閫氳繃鐢ㄦ埛鍚嶆煡鎵剧敤鎴�
	 * 
	 * @return
	 */
	public String findUserByUserName() {
		this.user = this.userDao.getUserByUserName(this.user.getUserName());
		if (this.user != null) {
			JSONKit
					.outJSONInfo("{success:true,userExsist:true,'aaaaaa':'aaaaaa'}");
			return NONE;
		}
		JSONKit.outJSONInfo("{success:true}");
		return NONE;
	}
   /**
    * 閫�鍑虹郴缁�
    * @return
    */
	public String exitSys() {
		this.setCurrUser(null);
		return "exitSys";
	}
	/**
	 * 杩涘叆鍒颁慨鏀圭敤鎴烽〉闈�
	 * 
	 * @return
	 */
	public String toUpdatePage() {
		User user = this.getCurrUser();
		this.user = this.userDao.querySingleUser(user.getUserId());
		return "toUpdatePage";
	}

	/**
	 * 杩涘叆鍒扮櫥闄嗛〉闈�
	 * 
	 * @return
	 */
	public String toLoginPage() {
		return "toLoginPage";
	}

	/**
	 * 鐢ㄦ埛鐧诲綍
	 * 
	 * @return
	 */
	public String login() {
		User currUser = this.userDao.login(user);
		if (currUser != null) {
			this.setCurrUser(currUser);
			return "toForumPage";
		} else {
			this.setMsg("鐢ㄦ埛鍚嶆垨鑰呭瘑鐮佷笉姝ｇ‘");
			return "relogin";
		}
	}

	/**
	 * 鏇存柊鐢ㄦ埛淇℃伅
	 * 
	 * @return
	 */
	public String updateUser() {
		User user1 = this.userDao.getUserByUserName(this.user.getUserName());
		if (user1.getUserName() != null && !user1.getUserName().equals(this.getUser().getUserName())) {
			JSONKit.outJSONInfo("{success:false,msg:'鐢ㄦ埛鍚嶇О宸茬粡瀛樺湪'}");
			return NONE;
		}
		User user = this.userDao.querySingleUser(this.user.getUserId());
		if (!this.oldPassword.equals(user.getPassword())) {
			JSONKit.outJSONInfo("{success:false,msg:'鏃у瘑鐮佷笉瀵�'}");
			return NONE;
		}
		user.setUserName(this.user.getUserName());
		user.setPassword(this.user.getPassword());
		user.setEmail(this.user.getEmail());
		user.setTel(this.user.getTel());
		userDao.updateUser(user);
		this.setMsg("涓汉淇℃伅淇敼鎴愬姛锛�");
		JSONKit.outJSONInfo("{success:true,msg:'瀵嗙爜淇敼鎴愬姛'}");
		return NONE;
	}

	/**
	 * 杩涘叆鍒版垚鍔熼〉闈�
	 * 
	 * @return
	 */
	public String toSuccessPage() {
		return "toSuccessPage";
	}

	public IUserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isHasLogin() {
		return hasLogin;
	}

	public void setHasLogin(boolean hasLogin) {
		this.hasLogin = hasLogin;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

}
