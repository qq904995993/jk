package anontation;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;


@Documented								//��ע���е�Ԫ�ذ����� Javadoc ��ȥ
@Retention(RetentionPolicy.RUNTIME)		//ע����Ա������������е�ʱ�����ᱻ���ؽ��뵽 JVM �У������ڳ�������ʱ���Ի�ȡ������
@Target({ElementType.TYPE})				//ע��Ӧ����������
@Inherited								//����ע��Ӧ��������
public @interface Service {
	String value();
}
