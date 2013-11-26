@rem ==========================================================================
@rem This program is free software; you can redistribute it and/or
@rem modify it under the terms of the GNU General Public License
@rem as published by the Free Software Foundation; either version 2
@rem of the License, or (at your option) any later version.
@rem
@rem You should have received a copy of the GNU General Public License
@rem along with this program; if not, write to the Free Software
@rem Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, 
@rem USA.
@rem ==========================================================================

@rem This contains functions used by batch scripts.
@rem This file itself is not meant to be executed.
@rem You must pass a parameter that corresponds to a labeled section of the batch file.

goto %1

@rem Checks for needed environment space and increases it if necessary.
:check_env
  set ENVTEST=This just checks whether there is additional environment space.
  if "%ENVTEST%"=="" goto increase_env
  set ENVTEST=
goto end

@rem Increases environment space.
:increase_env
  echo Increasing environment space
  if not exist %comspec% goto nocomspec

:comspec
  @rem %comspec% points to an existing command interpreter
  %comspec% /E:4096 /C %2 %3 %4 %5
  goto end

:nocomspec
  @rem %comspec% is not set, trying command.com
  command /E:4096 /C %2 %3 %4 %5
goto end

@rem Sets the BASE variable to the directory above the bin directory.
:set_base
  pushd %~p0\..
  if %OS%'==Windows_NT' (for %%d in (.) do set BASE=%%~fd&goto done)
  echo @prompt set BASE=$P$_>%TEMP%.\#ETCD1.BAT
  %comspec% /c %temp%.\#etcd1.bat > %temp%.\#etcd2.bat
  call %temp%.\#etcd2.bat
  del %temp%.\#etcd?.bat
  :done
  popd
goto end

@rem Starts Program D using a given main class.
:start_programd

  @rem Set up the Program D variables.
  if "%quit%"=="" call "%0" setup_programd

  @rem Set up the Java environment.
  if "%quit%"=="" call "%0" setup_java

  @rem Concatenate all paths into the classpath to be used.
  set PROGRAMD_CLASSPATH=%PROGRAMD_LIBS%;%JS_LIB%;%SQL_LIB%

  @rem Change to the Program D directory and start the main class.
  pushd "%BASE%"
  %JVM_COMMAND% -classpath "%PROGRAMD_CLASSPATH%" -Xms%3 -Xmx%4 %2 -c %5

  @rem On exit, leave the base directory.
  :finished
  popd
goto end

@rem Sets up some variables used to run/build Program D.
:setup_programd

  @rem Set lib directories
  call "%0" setup_lib_dirs
  
  set PROGRAMD_MAIN_LIB=%DISTRIB%\programd-main.jar
  if exist "%PROGRAMD_MAIN_LIB%" goto check_other_programd_jars
  
  echo.
  echo I can't find your programd-main.jar file.  Have you compiled it?
  echo If you downloaded the source-only version but don't have
  echo a Java compiler, you can download a pre-compiled version from
  echo http://aitools.org/downloads/
  set quit=yes
  goto end
  
  :check_other_programd_jars
  @rem Define the other programd jars, but don't worry if they don't exist.
  set PROGRAMD_JS_LIB=%DISTRIB%\programd-rhino.jar
  
  @rem Set up external jars.
  call "%0" setup_other_libs
  
  set PROGRAMD_LIBS=%PROGRAMD_MAIN_LIB%;%PROGRAMD_JS_LIB%;%OTHER_LIBS%
goto end

:setup_other_libs
  
  set GETOPT_LIB=%LIBS%\gnu.getopt-1.0.10.jar
  if exist "%GETOPT_LIB%" goto set_log4j_lib
  echo.
  echo I can't find the gnu.getopt-1.0.10.jar that ships with Program D.
  set quit=yes
  goto end
  
  :set_log4j_lib
  set LOG4J_LIB=%WEBLIBS%\log4j-1.2.13.jar
  if exist "%LOG4J_LIB%" goto check_optional_components
  echo.
  echo I can't find the log4j-1.2.13.jar that ships with Program D.
  set quit=yes
  goto end

  :check_optional_components
  @rem Optional components:
  
  @rem Set LISTENER_LIBS to the location of your listener jars.
  @rem No warning is provided if they cannot be found (since they are optional).
  set AIM_LISTENER_LIBS=%LIBS%\aim-listener\aim-listener.jar;%LIBS%\aim-listener\jaimbot-lib-1.4.jar
  set IRC_LISTENER_LIBS=%LIBS%\irc-listener\irc-listener.jar;%LIBS%\irc-listener\pircbot.jar
  set YAHOO_LISTENER_LIBS=%LIBS%\yahoo-listener\yahoo-listener.jar;%LIBS%\yahoo-listener\ymsg_network_v0_61.jar
  set LISTENER_LIBS=%AIM_LISTENER_LIBS%;%IRC_LISTENER_LIBS%;%YAHOO_LISTENER_LIBS%

  @rem Set SQL_LIB to the location of your database driver.
  @rem No warning is provided if it cannot be found (since it is optional).
  set SQL_LIB=%LIBS%\mysql-connector-java-3.1.12-bin.jar

  @rem Set JS_LIB to the location of the Rhino JavaScript interpreter.
  @rem No warning is provided if it cannot be found (since it is optional).
  set JS_LIB=%WEBLIBS%\js.jar
  
  set OTHER_LIBS=%GETOPT_LIB%;%LOG4J_LIB%;%LISTENER_LIBS%;%SQL_LIB%;%JS_LIB%
goto end


@rem Sets up the lib directories.
:setup_lib_dirs
  set LIBS=%BASE%\lib
  set DISTRIB=%BASE%\distrib
  set WEBLIBS=%BASE%\WebContent\WEB-INF\lib
goto end

@rem Sets up a Java execution environment
@rem (or fails informatively).
:setup_java
  set quit=
  if "%quit%"=="" call "%0" set_java_vars
  if "%quit%"=="" call "%0" check_java_home
  if "%quit%"=="" call "%0" set_jvm_command
  @rem We don't check JVM version because
  @rem I don't know equivalent text manipulation
  @rem tools in DOS for parsing java -version output.
goto end


@rem Tries to find/set JAVA_HOME.
:set_java_vars
  @rem Try to find JAVA_HOME if it isn't already set.
  if defined JAVA_HOME goto end

  echo JAVA_HOME is not set in your environment.

  @rem Try the standard JDK 5.0 install location.
  if not exist c:\jdk1.5.0_06\bin\java.exe goto seek_known_javas

  set JAVA_HOME=c:Progra~1\Java\\jdk1.5.0_06\
  set JVM_COMMAND=%JAVA_HOME%\bin\java.exe
  goto successful

  :seek_known_javas
  @rem Common paths for compatible Java SDKs (or JREs) should go here.
  if exist d:Progra~1\Java\\jdk1.5.0_05\bin\java.exe set JAVA_HOME=d:\jdk1.5.0_05
  if exist d:Progra~1\Java\\jdk1.5.0_04\bin\java.exe set JAVA_HOME=d:\jdk1.5.0_04
  if exist d:Progra~1\Java\\jdk1.5.0_03\bin\java.exe set JAVA_HOME=d:\jdk1.5.0_03
  if exist d:Progra~1\Java\\jdk1.5.0_02\bin\java.exe set JAVA_HOME=d:\jdk1.5.0_02
  if exist d:Progra~1\Java\\jdk1.5.0_01\bin\java.exe set JAVA_HOME=d:\jdk1.5.0_01
   if not defined JAVA_HOME goto cannot_find

  :successful
  echo I have temporarily set JAVA_HOME to "%JAVA_HOME%".
  echo Please consider setting your JAVA_HOME variable globally.
  goto end

  :cannot_find
  echo I cannot find a java executable in your path.
  echo Please check that you hava a JDK 5.0 compatible SDK installed.
  set quit=yes
goto end


@rem Checks that JAVA_HOME points to a real directory.
:check_java_home
  if exist "%JAVA_HOME%" goto end
  
  echo I can't find your JAVA_HOME directory.
  echo ("%JAVA_HOME%" doesn't seem to exist.)
  echo Please be sure that a JDK 5.0 compatible JRE is installed
  echo and (even better) set the JAVA_HOME environment variable to point to
  echo the directory where it is installed.
  echo.
  set quit=yes
goto end

:set_jvm_command
  set JVM_COMMAND="%JAVA_HOME%\bin\java.exe" -server -Dlog4j.configuration="file:/%BASE%\conf\log4j.xml"
goto end

:end
