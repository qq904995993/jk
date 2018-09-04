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
		//获取class对象
		JK jk = new JK();
		Class<?> jkClass = jk.getClass();				//1
		
		jkClass = JK.class;								//2
		
		jkClass = Class.forName("reflex.JK");			//3
		
		System.out.println("");
		System.out.println("-------------------------------调用无参构造方法--------------------------------");
		jk = (JK) jkClass.newInstance();																				
		
		System.out.println("");
		System.out.println("-------------------------------获取所有公用的构造方法及其参数类型--------------------------------");
		Constructor<?>[] constructors = jkClass.getConstructors();					//获取所有构造方法
		for(Constructor<?> constructor : constructors){
			System.out.println(constructor);
			Class<?>[] paramTypes = constructor.getParameterTypes();
			if(paramTypes != null && paramTypes.length > 0) {
				for(Class<?> paramType : paramTypes) {
					System.out.println(paramType.getName());						//获取参数类型
				}
			}
		}
       
        System.out.println("");
		System.out.println("-------------------------------获取所有构造方法及其参数类型--------------------------------");
		constructors = jkClass.getDeclaredConstructors();
        for(Constructor<?> constructor : constructors){
			System.out.println(constructor);
		}
        
        System.out.println("");
		System.out.println("-------------------------------获取某个构造方法--------------------------------");
        Constructor<?> constructor = jkClass.getDeclaredConstructor(String.class, int.class);
		System.out.println(constructor);
		constructor.setAccessible(true);	//暴力访问(忽略掉访问修饰符)
		jk = (JK) constructor.newInstance("ss", 1);
		
		System.out.println("");
		System.out.println("-------------------------------获取公用变量--------------------------------");
		Field[] fields = jkClass.getFields();
		for(Field field : fields){
			System.out.println(field);
		}	
		
		System.out.println("");
		System.out.println("-------------------------------获取所有变量--------------------------------");
		fields = jkClass.getDeclaredFields();
		for(Field field : fields){
			System.out.println(field);
		}	
		
		System.out.println("");
		System.out.println("-------------------------------获取名为name的公有变量并设置值--------------------------------");
		Field field = jkClass.getField("name");
		System.out.println(field);
		jk = (JK) jkClass.getConstructor().newInstance();
		field.set(jk, "刘德华");
		System.out.println(jk.name);
		
		System.out.println("");
		System.out.println("-------------------------------获取名为age的私有变量--------------------------------");
		field = jkClass.getDeclaredField("age");
		System.out.println(field);
		jk = (JK) jkClass.getConstructor().newInstance();
		field.setAccessible(true);//暴力反射，解除私有限定
		field.set(jk, 1);
		System.out.println(jk.age);
		
		System.out.println("");
		System.out.println("-------------------------------获取所有共用方法（含父类）--------------------------------");
		Method[] methods = jkClass.getMethods();
		for(Method method : methods){
			System.out.println(method);
		}
		
		System.out.println("");
		System.out.println("-------------------------------获取名为method的公用方法--------------------------------");
		Method method = jkClass.getMethod("method");
		System.out.println(method);
		method.invoke(jk);
		
		System.out.println("");
		System.out.println("-------------------------------获取所有方法--------------------------------");
		methods = jkClass.getDeclaredMethods();
		for(Method _method : methods){
			System.out.println(_method);
		}
		
		System.out.println("");
		System.out.println("-------------------------------获取名为method的方法--------------------------------");
		method = jkClass.getDeclaredMethod("method", String.class);
		System.out.println(method);
		method.setAccessible(true);//解除私有限定
		method.invoke(jk, "sss");
	}
}
