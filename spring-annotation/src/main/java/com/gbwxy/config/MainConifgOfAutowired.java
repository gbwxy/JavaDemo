package com.gbwxy.config;

import com.gbwxy.dao.BookDao;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


/**
 * �Զ�װ��;
 * 		Spring��������ע�루DI������ɶ�IOC�������и��������������ϵ��ֵ��
 *
 *
 * Beanע�뵽IOC�����е�ʱ�����Ψһ��name�����֮Ϊid��
 * @Autowired ���Զ�ע���ʱ��ͨ��name�����֮Ϊid������bean
 *
 *
 * 1����@Autowired���Զ�ע�룺
 * 		1����Ĭ�����Ȱ�������ȥ�������Ҷ�Ӧ�����:applicationContext.getBean(BookDao.class);�ҵ��͸�ֵ
 * 		2��������ҵ������ͬ���͵�������ٽ����Ե�������Ϊ�����idȥ�����в���
 * 							applicationContext.getBean("bookDao")
 * 		3����@Qualifier("bookDao")��ʹ��@Qualifierָ����Ҫװ��������id��������ʹ��������
 * 		4�����Զ�װ��Ĭ��һ��Ҫ�����Ը�ֵ�ã�û�оͻᱨ��
 * 			����ʹ��@Autowired(required=false);
 * 		5����@Primary����Spring�����Զ�װ���ʱ��Ĭ��ʹ����ѡ��bean��
 * 				Ҳ���Լ���ʹ��@Qualifierָ����Ҫװ���bean������
 * 		BookService{
 * 			@Autowired
 * 			BookDao  bookDao;
 * 		}
 *
 * 2����Spring��֧��ʹ��@Resource(JSR250)��@Inject(JSR330)[java�淶��ע��]
 * 		@Resource:
 * 			���Ժ�@Autowiredһ��ʵ���Զ�װ�书�ܣ�Ĭ���ǰ���������ƽ���װ��ģ�
 * 			��@Autowired��Ĭ�ϰ��������ң�����ҵ�����ٰ���name�����֮Ϊid��ȥ��
 *
 * <example>���磺
 *
 *          @Resource
 *   	    private BookDao bookDao;
 *  	    �������ȥ��name�����֮Ϊid��ΪbookDao��bean
 *
 * 		    @Resource(name="bookDao2")
 * 	        private BookDao bookDao;
 * 	        �������ȥ��name�����֮Ϊid��ΪbookDao2��bean
 *</example>
 *
 * 			û����֧��@Primary����û��֧��@Autowired��reqiured=false��;
 * 		@Inject:
 * 			��Ҫ����javax.inject�İ�����Autowired�Ĺ���һ����û��required=false�Ĺ��ܣ�
 *  @Autowired:Spring����ģ� @Resource��@Inject����java�淶
 *
 * AutowiredAnnotationBeanPostProcessor:��������Զ�װ�书�ܣ�
 *
 * 3���� @Autowired:�����������������������ԣ����Ǵ������л�ȡ���������ֵ
 * 		1����[��ע�ڷ���λ��]��@Bean+���������������������л�ȡ;Ĭ�ϲ�д@AutowiredЧ����һ���ģ������Զ�װ��
 * 		2����[���ڹ�������]��������ֻ��һ���вι�����������вι�������@Autowired����ʡ�ԣ�����λ�õ�������ǿ����Զ��������л�ȡ
 * 		3�������ڲ���λ�ã�
 *
 * 4�����Զ��������Ҫʹ��Spring�����ײ��һЩ�����ApplicationContext��BeanFactory��xxx����
 * 		�Զ������ʵ��xxxAware���ڴ��������ʱ�򣬻���ýӿڹ涨�ķ���ע����������Aware��
 * 		��Spring�ײ�һЩ���ע�뵽�Զ����Bean�У�
 * 		xxxAware������ʹ��xxxProcessor��
 * 			ApplicationContextAware==��ApplicationContextAwareProcessor��
 *
 *
 * @author lfy
 *
 */


/**
 * @ComponentScan({"com.gbwxy.service","com.gbwxy.dao","com.gbwxy.dao2"})
 *
 * org.springframework.beans.factory.BeanDefinitionStoreException:
 * Failed to parse configuration class [com.gbwxy.config.MainConifgOfAutowired];
 * nested exception is org.springframework.context.annotation.ConflictingBeanDefinitionException:
 * Annotation-specified bean name 'bookDao' for bean class [com.gbwxy.dao2.BookDao] conflicts with existing,
 * non-compatible bean definition of same name and class [com.gbwxy.dao.BookDao]
 */
@Configuration
@ComponentScan({"com.gbwxy.service","com.gbwxy.dao","com.gbwxy.dao2"})
public class MainConifgOfAutowired {


    /**
     *
     *  ����������ʱ���ȼ��� �����ļ��������ļ��е�bean��Ȼ����ɨ��@ComponentScanע������İ�
	 *  �����������@Beanע����name��bookDao��bean��@Repository ע��Ͳ��������ã���ΪbeanFactory��map���Ѿ�����name��bookDao��bean
	 *
	 *  ���� BookService ע��������bean������@Repository  ע���bean
     */
    @Bean
	public BookDao bookDao(){
		BookDao bookDao = new BookDao();
		bookDao.setLable("2");
		return bookDao;
	}




}
