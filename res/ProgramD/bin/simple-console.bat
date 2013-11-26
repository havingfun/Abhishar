@echo off
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

@rem This script launches a simple console-based version of Program D.
@rem You can pipe input to stdin, and output and error from stdout and stderr.

@rem Reset the quit variable.
set quit=

@rem Enter the bin directory.
pushd "%~p0"

@rem Check for needed environment space.
call common_functions.bat check_env %1 %2 %3 %4

@rem Get "base" directory (root of Program D installation)
if "%quit%"=="" call common_functions.bat set_base

@rem Configuration
set MAIN_CLASS=org.aitools.programd.configurations.SimpleConsole
set START_MEM=128m
set MAX_MEM=256m
set CORE_CONF=%BASE%\conf\core.xml

@rem Start Program D using the SimpleConsole main class.
if "%quit%"=="" call common_functions.bat start_programd %MAIN_CLASS% %START_MEM% %MAX_MEM% "%CORE_CONF%"

:end
@rem On exit, go back to the original directory.
popd