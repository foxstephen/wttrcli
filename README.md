# Command line weather report using http://wttr.in
Provides a way to fetch weather report and parse into multiple reports, see below.

### Clone the repository
```bash
git clone https://github.com/StephenFox1995/wttrcli
```

### Use Package the project, go to project root and run:
```bash
mvn package
```

### To run
```bash
java -jar target/wttrcli-1.0.jar <options>
```

### The following options can be used:
```
--Usage--
	[l, location]	: The location to fetch the weather report.
	[o, option]	: An option for parsing the report, can be any of the following: 
                            all, tomorrow, live, aftertomorrow 
```

### Examples
```bash
java -jar target/wttrcli-1.0.jar -l Dublin -o all
```

```bash
java -jar target/wttrcli-1.0.jar -l Dublin -o live -o tomorrow
```

```bash
java -jar target/wttrcli-1.0.jar -l Madrid -o aftertomorrow
```

## TODO: 
- Add install script for command line