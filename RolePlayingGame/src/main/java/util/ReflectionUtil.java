package util;



import java.lang.reflect.Method;

public class ReflectionUtil {

	private ReflectionUtil()
	{
		
	}
	
	
	
	public static boolean isGetter(Method method){
		  if(!method.getName().startsWith("get"))   
			  return false;
		  if(method.getParameterTypes().length != 0)   
			  return false;  
		  if(void.class.equals(method.getReturnType())) 
			  return false;
		  return true;
		}

		public static boolean isSetter(Method method){
		  if(!method.getName().startsWith("set"))
			  return false;
		  if(method.getParameterTypes().length != 1)
			  return false;
		  return true;
		}
		
		
		public static Object getObjectFromType(Object data, Class<?> type) {
			if (type.equals(Integer.class) || type.equals(int.class))
				return Integer.parseInt(data.toString());
			else if (type.equals(Short.class) || type.equals(short.class))
				return Short.parseShort(data.toString());
			else if (type.equals(Long.class) || type.equals(long.class))
				return Long.parseLong(data.toString());
			else if (type.equals(Byte.class) || type.equals(byte.class))
				return Byte.parseByte(data.toString());
			else if (type.equals(Character.class) || type.equals(char.class))
				return data.toString().charAt(0);
			else if (type.equals(Double.class) || type.equals(double.class))
				return Double.parseDouble(data.toString());
			else if (type.equals(Float.class) || type.equals(float.class))
				return Float.parseFloat(data.toString());
			else
				return data.toString();
		}
}
