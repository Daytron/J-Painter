# J-Painter &nbsp;  [![Build Status](https://travis-ci.org/Daytron/J-Painter.svg?branch=master)](https://travis-ci.org/Daytron/J-Painter) [![Dependency Status](https://www.versioneye.com/user/projects/544acc5fc310f908510000d0/badge.svg?style=flat)](https://www.versioneye.com/user/projects/544acc5fc310f908510000d0) [![License](http://img.shields.io/:license-mit-blue.svg)](https://raw.githubusercontent.com/Daytron/Flipit-Map-Creator/master/LICENSES/LICENSE) 

**J-Painter** is a simple paint-like Java application that enables user to draw various lines and shapes, apply colours and thickness for each shape, uses two types of footprints or brush heads and allows photo imports into the canvas.

 


Screenshot
----

![ScreenShot](https://github.com/Daytron/J-Painter/raw/master/screenshots/screenshot.png)

Requirements
-----------

To work things properly, J-Painter uses [WebLaF](https://github.com/mgarin/weblaf) 1.28 - an awesome look and feel theme from [mgarin](https://github.com/mgarin). WebLaF is released under GNU General Public License (GPLv3).

The application is tested to run on Java Version 7 Update 45 or above. There's no guarantee that it will run smoothy on older versions.


Setup
----
####For Non-Maven users: 
Include the  WebLaF jar file ```weblaf-complete-1.28.jar``` to your application classpath. For more information, please visit the WebLaF [wiki page](https://github.com/mgarin/weblaf/wiki/How-to-use-WebLaF).

####For Maven users:
Add these dependencies to your pom.xml
```
<dependency>
    <groupId>de.sciss</groupId>
    <artifactId>weblaf-core</artifactId>
    <version>1.28</version>
</dependency>

<dependency>
    <groupId>de.sciss</groupId>
    <artifactId>weblaf-ui</artifactId>
    <version>1.28</version>
</dependency>
```

Note: Currently weblaf-complete-1.28.jar is not available in Maven Central, but you can use weblaf-core and weblaf-ui for the same effect.
####Global Settings
Application's global parameters, settings and images' paths are located in the utility class, `GlobalSettingsManager`. Each path is extracted by the `ResourceLoader` class, found in `src\main\java\resources\` folder.

Build
----
For Maven
```
mvn clean compile assembly:single
```

Feedback
----
If you find any bugs or issues you can notify me [here](https://github.com/Daytron/J-Painter/issues) on GitHub. Any help is much appreciated. You can also email me if it is more convenient.


License
----

J-Painter is released under MIT License (c) 2014 Ryan Gilera.



