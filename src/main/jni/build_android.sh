#!/bin/sh
ROOT_PATH=/usr/local/codetyphon
TYPHON_BIN_LIB=${ROOT_PATH}/binLibraries
TYPHON_PATH=${ROOT_PATH}/typhon
FPC=/usr/local/codetyphon/fpc/fpc64/bin/x86_64-linux/fpc
# generate output dir
mkdir -p lib/armeabi/
mkdir -p lib/armeabi-v7a/
mkdir -p lib/x86/
# arm
${FPC} -Tandroid -Parm  -Fl${TYPHON_BIN_LIB}/android-5.0-api21-arm -olib/armeabi/libwthapi.so wthapi.lpr
cp lib/armeabi/libwthapi.so lib/armeabi-v7a/libwthapi.so
# x86
${FPC} -Tandroid -Pi386  -Fl${TYPHON_BIN_LIB}/android-5.0-api21-i386 -olib/x86/libwthapi.so wthapi.lpr
# clean
find . -name "*.o" | xargs rm -f
find . -name "*.ppu" | xargs rm -f
find . -name "*.rsj" | xargs rm -f
find . -name "link.res" | xargs rm -f
find . -name "ppas.sh" | xargs rm -f
find . -name "linksyms.fpc" | xargs rm -f
