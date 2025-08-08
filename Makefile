# Top of Makefile — add this default‐empty variable
PHILOSOPHERS ?=

COMP = javac -g -Xlint:all
TASK1 = TASK1/*.java
TASK2 = TASK2/*.java
TASK3 = TASK3/*.java
TASK4 = TASK4/*.java
TASK5 = TASK5/*.java

RUN = java -XX:+PrintCommandLineFlags
RUN1 = -cp .. OS_PROGASN3.TASK1.DiningPhilosophers
RUN2 = -cp .. OS_PROGASN3.TASK2.DiningPhilosophers
RUN3 = -cp .. OS_PROGASN3.TASK3.DiningPhilosophers
RUN4 = -cp .. OS_PROGASN3.TASK4.DiningPhilosophers
RUN5 = -cp .. OS_PROGASN3.TASK5.DiningPhilosophers

all: clean comp1 comp2 comp3 comp4 comp5

clean:
	@echo "Allen is cleaning up"
	del /S *.class
# . -name "*.class" -print -delete
# 	powershell -Command "Write-Host 'cleaning .class files'"
# 	get-childitem * -include *.class -recurse | remove-item

comp%:
	$(COMP) $(TASK$*)

run%:
	$(MAKE) comp$*
	$(RUN) $(RUN$*)

.PHONY: all clean comp% run%
