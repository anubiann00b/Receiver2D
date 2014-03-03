# build Java projects
SRC=src
BIN=bin
LIB=lib
DOC=doc
JAR="`basename \"$(PWD)\"`.jar"
MANIFEST=manifest.mf
JCC=javac
LIBJARS=$(shell find $(LIB) -type f -name '*.jar' | sed ':a;N;$$!ba;s/\n/:/g')
JFLAGS=-g
JARCC=jar
JARFLAGS=cfm
JFILES=$(shell find $(SRC) -type f -name '*.java')
SUBPACKAGES=$(shell cd $(SRC) && find . -mindepth 1 -maxdepth 1 -type d && cd ..)

all: $(JFILES)
	$(JCC) -classpath $(LIBJARS) $(JFLAGS) $(JFILES) -d $(BIN)
	$(JARCC) $(JARFLAGS) $(JAR) $(MANIFEST) -C $(BIN) .

javadoc: $(JFILES)
	javadoc -d $(DOC) -sourcepath $(SRC) -subpackages $(SUBPACKAGES)

clean: clean-javadoc
	@rm -f $(JAR)
	@rm -rf $(BIN)/*

clean-javadoc:
	@rm -rf $(DOC)

