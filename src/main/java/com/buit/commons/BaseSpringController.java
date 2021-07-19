package com.buit.commons;


import cn.hutool.core.util.StrUtil;
import com.buit.utill.RedisFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;

/**
* @ClassName: BaseSpringController
* @Description: 所有 的基础类
* @author 神算子
* @date 2020年4月26日 下午3:34:33
*
 */
public abstract class BaseSpringController extends BaseController{
	static final Logger logger = LoggerFactory.getLogger(BaseSpringController.class);

	/**
	 * 开发环境
	 */
	static final String DEVELOP_ENVIRONMENT = "dev";
	static final String ACTIVE = "spring.profiles.active";

	@Autowired  
    private Environment env;  
	@Autowired
    private HttpServletRequest request;
	@Autowired
    private RedisFactory redisFactory;

	/**
     * 获取登录用户信息
     */
    public SysUser getUser() {
        String token = request.getHeader("token");
        String refreshTokenKey = String.format("JWT_TOKEN::%s", token);		
        SysUser loginUser=redisFactory.getValue(refreshTokenKey, SysUser.class);

        //方便测试注意后期去掉
//		loginUser.setHospitalId(310112041);
        if (null == loginUser) {
			//如果开发环境 为了测试方便
           // if(env.getProperty(ACTIVE).startsWith(DEVELOP_ENVIRONMENT)) {
                loginUser =new SysUser();
                loginUser.setUserId(0);
                loginUser.setUserName("超级管理员");
                loginUser.setHospitalId(310112001);
                loginUser.setGroupId(1);
                loginUser.setDataVersion(1);
                loginUser.setDeptId(14);
                return loginUser;
            // }else {
            //     throw BaseException.create("ERROR_USER_0000");
            // }
        }
        return loginUser;
    }
	/**
	 * 获取请求方IP
	 *
	 * @return 客户端Ip
	 */
	public String getIpAddress(){
		String xff = request.getHeader("REQUEST-IP");
		if (StrUtil.isEmpty(xff)) {
			throw BaseException.create("ERROR_IP_0001");
		}else{
			return xff;
		}
	}

	public  String getMac(){
		String str = null;
		String mac = null;
		String ip=getIpAddress();
		try{
			Process p = Runtime.getRuntime().exec("nbtstat -A " + ip);
			InputStreamReader ir = new InputStreamReader(p.getInputStream(),"gbk");
			LineNumberReader input = new LineNumberReader(ir);
			for (; true;) {
				str = input.readLine();
				if (str != null) {
					if (str.indexOf("MAC 地址") > 1) {
						mac = str.substring(str.indexOf("MAC 地址") + 9);
						break;
					}
				}
			}
			System.out.println(mac);
		}catch(IOException e){
			e.printStackTrace();
		}

		return mac;
	}
}
