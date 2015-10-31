/**
 * @author Sandeep Kumar
 *
 * 
 */
package project.srcCode;
public class Project<T extends Number> {	// A generic Java Class called Project
	int y = 1;	//Global Variable
	public Project()	//Default Constructor
	{
		y = 2;
		f();
	}

	public void f()	//	Method with return type as void and no input parameters
	{
		System.out.println("\nInside outer class with value of y =" + y);
	}

	public int changeToInt(float f)	//	Method with return type of int and input parameter of type float
	{
		int i = (int) f;
		return i;	//	Type casting float to int and returning a int
	}

	public float changeToFloat(int i)	//	Method with return type of float and input parameter of type int
	{
		float f = (int) i;
		return f;	//	Type casting int to float and returning a float
	}

	public int valueOf(char s)	//	Method with return type of int and input parameter of type char
	{
		int i = (int) s;
		return i;	//	Type casting char to int and returning a int. Here the ascii value assoictaed with that character will be returned
	}

	public char valueOfInt(int i)	//	Method with return type of char and input parameter of type int
	{
		char c = (char) i;
		return c;	//	//	Type casting int to char and returning a char, which is associated with that ascii value.
	}

	public T numberGeneric(T a)	//	Method with return type of T and input parameter of type T, T is a Generic type which extends java.lang.Number
	{
		if (a instanceof Float) {
			return (T) (Float) (((Float)a) + 1);	// If 'a' is instance of type float or int, 1 will be added to 'a' and returned
		} else if ( a instanceof Integer)
		{
			return (T) (Integer) (((Integer)a) + 1);
		} else if (a == null) {
			throw new NullPointerException();
		} else {
			throw new IllegalArgumentException("Unexpected number type: " + a.getClass());
		}
	}

	public static class second extends Project	//	Static Nested Class
	{
		int y = 3;
		public second()	//	Default Constructor
		{
			f();
		}
		public void f()	//	Method with return type of void and no input parameter
		{
			System.out.println("\nInside inner class with value of y =" + y);
		}
	}
}
