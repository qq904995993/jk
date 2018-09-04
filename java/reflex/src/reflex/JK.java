package reflex;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class JK{
	
	public String name;
    private int age;
    int c;
    protected String d;
	
	public JK() {
		System.out.println("public new");
	}
	
	private JK(String name, int age) {
		System.out.println("private new 2 param");
    }
	
	JK(String name) {
		System.out.println("default new 1 param");
    }
	
	protected JK(String name, int age, int c) {
		System.out.println("protected new 3 param");
    }
	
	public void method() {
		System.out.println("public method");
	}
	
	private void method(String name) {
		System.out.println("private 1 param method");
	}
	
	void method(int age) {
		System.out.println("defualt 1 param method");
	}
	
	protected void method(String name, int age) {
		System.out.println("protected 2 param method");
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException, InvocationTargetException, NoSuchFieldException {
		//��ȡclass����
		JK jk = new JK();
		Class<?> jkClass = jk.getClass();				//1
		
		jkClass = JK.class;								//2
		
		jkClass = Class.forName("reflex.JK");			//3
		
		System.out.println("");
		System.out.println("-------------------------------�����޲ι��췽��--------------------------------");
		jk = (JK) jkClass.newInstance();																				
		
		System.out.println("");
		System.out.println("-------------------------------��ȡ���й��õĹ��췽�������������--------------------------------");
		Constructor<?>[] constructors = jkClass.getConstructors();					//��ȡ���й��췽��
		for(Constructor<?> constructor : constructors){
			System.out.println(constructor);
			Class<?>[] paramTypes = constructor.getParameterTypes();
			if(paramTypes != null && paramTypes.length > 0) {
				for(Class<?> paramType : paramTypes) {
					System.out.println(paramType.getName());						//��ȡ��������
				}
			}
		}
       
        System.out.println("");
		System.out.println("-------------------------------��ȡ���й��췽�������������--------------------------------");
		constructors = jkClass.getDeclaredConstructors();
        for(Constructor<?> constructor : constructors){
			System.out.println(constructor);
		}
        
        System.out.println("");
		System.out.println("-------------------------------��ȡĳ�����췽��--------------------------------");
        Constructor<?> constructor = jkClass.getDeclaredConstructor(String.class, int.class);
		System.out.println(constructor);
		constructor.setAccessible(true);	//��������(���Ե��������η�)
		jk = (JK) constructor.newInstance("ss", 1);
		
		System.out.println("");
		System.out.println("-------------------------------��ȡ���ñ���--------------------------------");
		Field[] fields = jkClass.getFields();
		for(Field field : fields){
			System.out.println(field);
		}	
		
		System.out.println("");
		System.out.println("-------------------------------��ȡ���б���--------------------------------");
		fields = jkClass.getDeclaredFields();
		for(Field field : fields){
			System.out.println(field);
		}	
		
		System.out.println("");
		System.out.println("-------------------------------��ȡ��Ϊname�Ĺ��б���������ֵ--------------------------------");
		Field field = jkClass.getField("name");
		System.out.println(field);
		jk = (JK) jkClass.getConstructor().newInstance();
		field.set(jk, "���»�");
		System.out.println(jk.name);
		
		System.out.println("");
		System.out.println("-------------------------------��ȡ��Ϊage��˽�б���--------------------------------");
		field = jkClass.getDeclaredField("age");
		System.out.println(field);
		jk = (JK) jkClass.getConstructor().newInstance();
		field.setAccessible(true);//�������䣬���˽���޶�
		field.set(jk, 1);
		System.out.println(jk.age);
		
		System.out.println("");
		System.out.println("-------------------------------��ȡ���й��÷����������ࣩ--------------------------------");
		Method[] methods = jkClass.getMethods();
		for(Method method : methods){
			System.out.println(method);
		}
		
		System.out.println("");
		System.out.println("-------------------------------��ȡ��Ϊmethod�Ĺ��÷���--------------------------------");
		Method method = jkClass.getMethod("method");
		System.out.println(method);
		method.invoke(jk);
		
		System.out.println("");
		System.out.println("-------------------------------��ȡ���з���--------------------------------");
		methods = jkClass.getDeclaredMethods();
		for(Method _method : methods){
			System.out.println(_method);
		}
		
		System.out.println("");
		System.out.println("-------------------------------��ȡ��Ϊmethod�ķ���--------------------------------");
		method = jkClass.getDeclaredMethod("method", String.class);
		System.out.println(method);
		method.setAccessible(true);//���˽���޶�
		method.invoke(jk, "sss");
	}
}
