# test Receiver2D after building it

LIB=lib_natives # formerly "lib"
JAR=Receiver2D.jar
#LIBDIRS=$(find $LIB -type f -name '*.jar' | sed ':a;N;$!ba;s/\n/:/g')
OSNAMES=(linux mac solaris windows) # change flags below to suit OS
#FLAGS="-cp $LIBDIRS:$JAR -Djava.library.path=lib/lwjgl/natives_${OSNAMES[0]}/"

#java $FLAGS com.receiver2d.engine.tests.MainClass

LD_LIBRARY_PATH="$LIB/${OSNAMES[0]}/" java -jar $JAR
