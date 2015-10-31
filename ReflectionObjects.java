/**
 * @author Sandeep Kumar
 *
 * 
 */
package project.srcCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ReflectionObjects {

	public static void main(String[] args){
		try	//Wise to use try-catch block to catch exceptions at runtime
		{
			// The name of the class which will be tested and whose methods will be invoked on runtime and chained
			String className = args[0];

			Class<?> reflectOn;
			reflectOn = Class.forName(className);	//A class object is created  to reflect on the class provided as an argument

			String name = reflectOn.getName();	// The name of the Class Under Test
			System.out.println("Reflecting on Class File " + name);

			//An array of classes that are members of the class represented by the Class Object
			Class<?>[] classes1 = reflectOn.getClasses();

			//Creating a List of class Objects. The list will consist of all the classes that are part of the Class Object
			List<Class<?>> classList = new ArrayList<Class<?>>();	

			classList.add(reflectOn);

			for(Class<?> c : classes1)
				classList.add(c);

			System.out.println("\nWhich Class would you like to reflect on\n");
			System.out.println("The classes available are:\n");

			int num = 1;

			// Iterating on Class List to take a user input, as to which class has to be constructed and whose methods are to be invoked
			for(Iterator<Class<?>> iter = classList.iterator(); iter.hasNext();)
			{
				System.out.println(num + ":" + iter.next());
				num++;
			}

			System.out.println("\nPlease enter the number for the class use wish to reflect on and do chaining for methods\n");
			Scanner readClass = new Scanner(System.in);	// Scanner object to get user input
			int classNumber = readClass.nextInt();

			//Retrieving the class from the List that the user wants to reflect
			Class<?> reflectOnClass = classList.get(classNumber - 1);

			//Gets the constructor for the class and creates an object for the class which the user wants to invoke methods of
			Constructor<?> construct = reflectOnClass.getConstructor();
			Object hw = construct.newInstance();

			Method methods[] = reflectOn.getDeclaredMethods();	//Retrieves all the methods of that particular class

			//A Map whose 'key' is the name of the method, and parameters of that method as 'value'
			Map<Method,List<Object>> methodMap = new HashMap<Method,List<Object>>();

			//A Map whose 'key' is the name of the method, and its return value as 'value' of the map
			Map <Method,Class<?>> methodReturn = new HashMap<Method,Class<?>>();

			//A list which consists of all the methods of the Class Under Test(CUT)
			List<Method> totalMethods = new ArrayList<Method>();

			//Iterating on all the methods, and adding the method names, parameters and return values to the respective maps
			for(Method i : methods)
			{
				Class<?> parameterTypes[] = i.getParameterTypes();	//Retrieves the parameters of the method
				String methodName = i.getName();	//Retrieves the method name
				Class<?> returnType = i.getReturnType();	//Retrieves return type of the method

				if (!methodMap.containsKey(methodName))	//If the method has already been added to map, it will be skipped
				{
					List<Object> para = new ArrayList<Object>();
					for(Object o : parameterTypes)
					{
						para.add(o);
					}
					methodMap.put(i, para);
					methodReturn.put(i, returnType);
					totalMethods.add(i);
				}
			}

			//The external file that will provide the input values for the methods
			Scanner inFile = new Scanner( new File("MyData.txt"));

			Float num1 = 1.23f;
			if(inFile.hasNextFloat())
				num1 = inFile.nextFloat();
			int num2 = 44;
			String s = ",a";
			if(inFile.hasNext())
			{
				s = inFile.next();
			}	
			if(inFile.hasNext())
			{
				num2 = inFile.nextInt();
			}

			//Iterating on every method in the Map that has method name and parameters
			for( Map.Entry<Method,List<Object>> m : methodMap.entrySet())
			{	
				Object value = null;	//This will be used to hold the return value which the methods will return
				List<Object> param = m.getValue();	//Retrieves the method parameters as a List from Map
				Method invokeMethod = m.getKey();	//Retrieves the method name from the Map
				System.out.println("\nCalling method " + invokeMethod);
				if(param.isEmpty())	//If the parameter of the method is empty, no parameters will passed for it
				{
					invokeMethod.invoke(hw);	//Invokes methods, 'hw' is the object of the class
				}

				//If the method has parameter of type int, an integer from the external file will be passed to it, same for float and char
				else if(param.get(0).equals(Integer.TYPE))
				{
					value = invokeMethod.invoke(hw,num2); //Second argument in the invoke method is the parameter passed to the method
				}
				else if(param.get(0).equals(Float.TYPE))
				{
					value = invokeMethod.invoke(hw, num1);
				}
				else if(param.get(0).equals(java.lang.Number.class))	//This has been used for the Generic Method in the CUT
				{
					value = invokeMethod.invoke(hw, num1);
				}
				else if(param.get(0).equals(Character.TYPE))
				{
					char c = s.charAt(0);
					value = invokeMethod.invoke(hw, c);
				}

				//Once a method has been invoked, all the remaining methods will be chained with it
				for(Iterator<Method> iter = totalMethods.iterator();iter.hasNext();)
				{
					if(value == null)	//Ensuring that no null value is passed to a method that accepts a parameter
						value = 1;
					Method nextMethod = iter.next();	//Retrieves the next method to be invoked
					if(!nextMethod.equals(invokeMethod)) // A method will not be chained with itself
					{
						Class<?>[] parameter = nextMethod.getParameterTypes();
						System.out.println("\n Chaining the method " + nextMethod + " with value = " + value);

						if(parameter.length == 0)	//If the method has no parameters, 'value' is not passed to it
						{
							nextMethod.invoke(hw);
						}
						//If the method to be chained is generic, appropriate type castings are done before passing 'value' to it
						else if(parameter[0].equals(java.lang.Number.class))
						{
							if(value instanceof Float)
								value = ((Float) value).intValue();
							else if(value instanceof Character)
							{
								char c = (char) value;
								int i1 = (int) c;
								value = i1;
							}
							value = nextMethod.invoke(hw, value);
						}

						/*If the method to be chained has parameter type of int, appropriate casting is done, 
						so that no illegal values are passed to it, same for float and char type parameter*/
						
						else if(parameter[0].equals(Integer.TYPE))
						{
							if(value instanceof Float)
								value = ((Float) value).intValue();
							else if(value instanceof Character)
							{
								char c = (char) value;
								int i1 = (int) c;
								value = i1;
							}
							value = nextMethod.invoke(hw, value);
						}
						else if(parameter[0].equals(Float.TYPE))
						{
							value = nextMethod.invoke(hw, value);
						}
						else if (parameter[0].equals(Character.TYPE))
						{
							if(value instanceof Integer)
							{
								int iValue = (int) value;
								char c = (char) iValue;
								value = nextMethod.invoke(hw, c);
							}
							else if(value instanceof Float)
							{
								float fValue = (float) value;
								char c = (char) fValue;
								value = nextMethod.invoke(hw,c);
							}
							else	//If parameter and 'value' both are of type char, no casting is done
							{
								value = nextMethod.invoke(hw, value);
							}
						}
					}
				}
			}
			inFile.close();	//Closing the scanner object for input parameters from external file
			readClass.close();	//Closing the scanner object for input from user for class to be tested
		}

		//Various exceptions that can be encountered during program execution
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		catch (NoSuchMethodException e) {
			e.printStackTrace();
		}
		catch (SecurityException e) {
			e.printStackTrace();
		}
		catch (InstantiationException e) {
			e.printStackTrace();
		}
		catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
		}
		catch(InvocationTargetException e)
		{
			e.printStackTrace();
		}
		catch(FileNotFoundException e)
		{
			e.printStackTrace();
		}
		// If none of the above exception is caught, that will be caught by the below one
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
}