J-Painter 
=========
By Ryan Gilera

**J-Painter** is simple paint-like Java application that enables user to draw various lines and shapes, apply colours and thickness for each shape, uses two-type footprints or brush heads and import photos and images into the canvas.

 


Screenshot
----

![ScreenShot](https://github.com/Daytron/J-Painter/raw/master/screenshots/screenshot.png)

Requirements
-----------

To work things properly, J-Painter uses [WebLaF](https://github.com/mgarin/weblaf) 1.28 - an awesome look and feel theme from [mgarin](https://github.com/mgarin). WebLaF is released under GNU General Public License (GPLv3).

The application is tested to run on Java Version 7 Update 45 or above. There's no guarantee that it will run smoothy on older versions.


Setup
----
Include the  WebLaF jar file ```weblaf-complete-1.28.jar``` to your application classpath. For more information, please visit the WebLaF [wiki page](https://github.com/mgarin/weblaf/wiki/How-to-use-WebLaF).

Application's global parameters, settings and images' paths are located in the utility class, `GlobalSettingsManager`. Each path is extracted by the `ResourceLoader` class, found in `res\` folder.


Feedback
----
If you find any bugs or issues you can notify me [here](https://github.com/Daytron/J-Painter/issues) on GitHub. Any help is much appreciated. You can also email me if it is more convenient.


License
----

J-Painter is released under MIT License (c) 2014 Ryan Gilera.



