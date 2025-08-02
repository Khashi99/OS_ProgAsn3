

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


# # Just a makefile to save some typing :-)
# # Serguei A. Mokhov, mokhov@cs.concordia.ca
# # PA3
# # Use gmake.

# JAVAC=javac
# JFLAGS=-g
# JVM=java
# EXE=DiningPhilosophers

# ASMTDIRS := . common

# # Lists of all *.java and *.class files
# JAVAFILES := $(ASMTDIRS:%=%/*.java)
# CLASSES := $(ASMTDIRS:%=%/*.class)

# all: $(EXE).class
# 	@echo "Dining Philosophers Application has been built."

# $(EXE).class: $(JAVAFILES)
# 	$(JAVAC) $(JFLAGS) $(EXE).java

# run: all
# 	$(JVM) $(EXE)

# regression: all
# 	@for arg in 3 4 5; do $(JVM) $(EXE) $$arg; done

# clean:
# 	rm -f $(CLASSES) #* *~

# # EOF
