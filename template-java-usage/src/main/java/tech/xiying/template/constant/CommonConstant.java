package tech.xiying.template.constant;

/**
 * @ClassName CommonConstant
 * @Description 常量类
 * @Author shanghao5
 * @Date 2021/2/25 10:28
 **/
public class CommonConstant {

	public static final String PASSWORD_PREFIX = "{SSHA256}";
	public static final String LDAP_PERSON_DN = "ou=people";
	public static final String LDAP_ACCOUNT_PERSON_DN = "ou=people,";
	public static final String LDAP_PASSWORD_IDPROVIDER = "iam_password";
	public static final String LDAP_TO_OBJECT_CONVENT_GROUP = "ou=Group";
	public static final String LDAP_TO_OBJECT_CONVENT_ROLES = "ou=roles";
	/**
	 * ldap 默认dc
	 */
	public static final String LDAP_DEFAULT_DC = "iamDefaultDc";
	/**
	 * 运营管理员用户角色
	 */
	public static final String OPS_ADMIN_PREFIX = "cn=iam_ops_admin,ou=roles";
	/**
	 * 运营人员角色
	 */
	public static final String OPS_PREFIX = "cn=iam_ops,ou=roles";
	/**
	 * 租户管理员角色
	 */
	public static final String TENANT_ADMIN_PREFIX = "cn=admin,ou=roles";
	/**
	 * 租户用户角色
	 */
	public static final String TENANT_NORMALUSER_PREFIX = "cn=normalUser,ou=roles";
	/**
	 * 租户用户角色
	 */
	public static final String COMMON_IDPROVIDER_FILTER = " (&(objectClass=user)(userPrincipalName={0}))";
	/**
	 * 随机认证源ID的长度
	 */
	public static final Integer IDP_RANDOM_ID_LENGTH = Integer.valueOf(8);
	/**
	 * 系统管理员的uniqueID
	 */
	public static final String SUPER_ADMIN_UNIQUE_ID = "123456789012345678";
	/**
	 * 系统管理员的uniqueID
	 */
	public static final String SUPER_ADMIN_NAME = "Admin";
	/**
	 * 用户登录次数阈值
	 */
	public static final Integer USER_LOGIN_COUNT_IP_AND_NAME = 5;
	/**
	 * 验证码验证次数阀值
	 */
	public static final Integer LOGIN_VERIFY_CODE_THRESHOLD = 3;
	/**
	 * 用户默认锁定时间
	 */
	public static final long USER_LOCK_TIME = 5 * 60 * 1000;
	/**
	 * 验证码过期时间
	 */
	public static final long EXPIRE_TIME_VALUE = 60 * 60 * 1000;
	
	/**
	 * rsa_key缓存时间，单位天
	 */
	public static final long RSA_KEY_TIME_DAY_VALUE = 7;



}
