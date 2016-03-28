## The folder consists of following files :-

1) Class Under Test (CUT) : - Project.java
2) Test HarnEss (THE) : - ReflectionObjects.java and ReflectionObjects1.java for two different approaches (See the report for the explanations on these approaches 
and run the appropriate java file).
3) MyData.txt : - The external file which will be used to give input to the methods in the program.

##This file consists of the steps to be taken for running the programs and how they have to be used.

1) Open command prompt and navigate to the location of the folder containing this file
e.g. C:\> cd C:\Users\Sandeep\Desktop\project (This might not be the path in your system, please do not copy this. Instead 
please copy the path of the folder containing this file and navigate to it).
i.e. if you have placed the folder containing this file in say C:\Users\XYZ\Desktop\...\project, copy this path and navigate to it on command prompt.

2) Compile the CUT first by writing the following command in the command prompt

	javac -d . Project.java
	
If the compilation is successful, you will see a new folder named "project" inside the folder containing this file (ReadMe.txt).
The Project has a folder "srcCode", which in turn has the two class files of the CUT, namely : - Project$second.class and Project.class
This shows that the CUT was compiled perfectly.

3)Compile the THE as above. Based on the approach required, ReflectionObjects.java or ReflectionObjects1.java can be chosen for compilation.

	javac -d . ReflectionObjects.java
			OR
	javac -d . ReflectionObjects1.java
	
After successful compilation, our srcCode i.e. ..\project\Project1\srcCode\ folder will have a new class file namely ReflectionObjects.class 
or ReflectionObjects1.class (based on the approach chosen).

4) Give some input values inside the MyData.txt file. Please look into the report and see what are the ways in which the inputs can be given, and
some constraints on them.

5) After finishing all the 4 steps above, we are now ready to run over THE on CUT and invoke methods of CUT and chain them. To do so we will
have to run the class file ReflectionObjects.class or ReflectionObjects1.class (again, based on the approach chosen) and give the name of the CUT as 
an argument to it. Please run the following command in the command prompt and make sure you are in the right directory, if not, read step 1 of this file.
Run the following command on the command prompt. Project1.srcCode is the package of the two class files, so they must ne included in the command.

	java -cp . Project1.srcCode.ReflectionObjects Project1.srcCode.Project
			OR
	java -cp . Project1.srcCode.ReflectionObjects1 Project1.srcCode.Project
	
If the command does not run or give NoClassDefFoundError, please set the classpath variable for your java, and run again.

http://javarevisited.blogspot.com/2011/01/how-classpath-work-in-java.html

### Follow the above link on how to set the CLASSPATH and run the command again.

After the command runs successfuly, you will be asked to chose the classes inside the Class Object which needs to be tested. 
Press 1 or 2 and hit enter and watch methods being invoked and chained with other methods with the values of the previous methods 
as parameters to the next one.

#### In case there is any difficulty in running these commands or there is any need for clarifications, call me or email me on the below info, ANYTIME.

####Name: - Sandeep Kumar Anil Kumar
#### Phone : - +1 (312) 468-5815
####Email : - sanilk2@uic.edu

### Thanks ! :)
