package cn.tedu.spring.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.tedu.spring.dao.UserDao;
import cn.tedu.spring.entity.User;
import cn.tedu.spring.service.ex.UsernameTakenException;

@Service("userService")
public class UserServiceImpl 
	implements IUserService {
	
	@Resource(name="userDao")
	private UserDao userDao;

	public int register(User user) {
		// 先检查需要注册的用户名是否已经被占用
		String username = user.getUsername();
		User u = findUserByUsername(username);
		if (u == null) {
			// 根据用户名查询不到数据
			// 则用户名没有被占用
			// 则允许注册
			return userDao.register(user);
		} else {
			// 根据用户名查询到了数据
			// 则用户名已经被占用
			// 则不允许注册
			throw new 
				UsernameTakenException(
					"用户名已经被占用：" + username);
		}
	}

	public User findUserByUsername(
			String username) {
		return userDao
			.findUserByUsername(username);
	}

}






