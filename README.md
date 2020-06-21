# Route Mapper

Simple Route Mapper Application which is used to map the route on mars
for the robot and also can be used for directing robotic arm to do some action
Also used to teach kids learn about programming in a fun way.


# Usage

### PreRequisites:
You need following this to be ready in order to run the application:
```
1. Source code
2. Scala 2.11.8
2. sbt 1.3.12
```

### Run

#### Running Tests
Just go to source code directory and run below command
````
// First run the tests so confirm there are no errors
sbt test
````
If no errors after running above command then run following

#### Running Application
````
sbt run
````

That's it!

You can use following commands to instruct the Application to move
cursor
````
Language defines the following commands:
FD <n units> - Moves Forward ‘n’ units doing some work
BK <n units> - Moves Backward ‘n’ units doing some work
RT <n degrees> - Right turn, for all n: 0 < n < 360
LT <n degrees> - Left turn, for all n: 0 < n < 360
REPEAT <n times> (<List of commands, comma separated>) - Repeats the
list of commands ‘n’ number of times.
JUMP FD <n unit> - Jumps ‘n’ units forward without doing any work.
JUMP BK <n unit> - Jumps ‘n’ units backward without doing any work.
````


## Author
Contributor: Myself

## Version History
* 0.1
    * Initial Release

## License

This project is licensed under the MIT License - see the LICENSE.md file for details