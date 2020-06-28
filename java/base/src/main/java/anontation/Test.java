package anontation;

import java.lang.reflect.Field;

@Service("service")
public class Test {
	
	@Autowired
	private String a;
	
	public static void main(String[] args) {
		Class<?> testClass = Test.class;
		boolean hasAnnotation = testClass.isAnnotationPresent(Service.class);
		if ( hasAnnotation ) {
			System.out.println(testClass.getName() + " has MyAnontation anontation!");
			Service myAnnotation = testClass.getAnnotation(Service.class);

			Field[] fields =  testClass.getDeclaredFields();
			for(Field field : fields) {
				if(field.isAnnotationPresent(Autowired.class)) {
					System.out.println(field.getName() + " has Autowired anontation!");
				}
			}
			
	        System.out.println("name:" + myAnnotation.value());
	    } 
	}
}
