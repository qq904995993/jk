package anontation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)		//ע����Ա������������е�ʱ�����ᱻ���ؽ��뵽 JVM �У������ڳ�������ʱ���Ի�ȡ������
@Target({ElementType.FIELD})			//ע��Ӧ��������������
public @interface Autowired {

}
