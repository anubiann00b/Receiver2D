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
JDOCCC=javadoc
JDOPTS=-d $(DOC) -classpath $(LIB) -sourcepath $(SRC)
LWJGLDOCS=http://www.lwjgl.org/javadoc/
ORACLEDOCS=http://docs.oracle.com/javase/7/docs/api/
JDFLAGS=-use -author -link $(LWJGLDOCS) -link $(ORACLEDOCS)

all: $(JFILES)
	@# use $(LIB), instead of $LIBJARS)
	$(JCC) -classpath $(LIB) $(JFLAGS) $(JFILES) -d $(BIN)
	$(JARCC) $(JARFLAGS) $(JAR) $(MANIFEST) -C $(BIN) .

javadoc: $(JFILES)
	$(JDOCCC) $(JDOPTS) $(JDFLAGS) -subpackages com

clean: clean-javadoc
	@rm -f $(JAR)
	@rm -rf $(BIN)/*

clean-javadoc:
	@rm -rf $(DOC)

