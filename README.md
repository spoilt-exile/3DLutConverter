## 3DLutConverter

##### Version 1.1

This repository contains source code of the utility which converts HaldCLUT images 
into 3D Lut files (*.3dl). It supports only 16x16x16 cube for conversion. 
Main purpose of this is matching color correction between photo editing software and video.

### Plans
 - Create backward conversion from 3dl to HaldCLUT;
 - Dig into `.cube` format;
 - Build interface;
 - Launch it as separate web service;

### How to use
1. Download Java JRE from [oracle.com](http://www.oracle.com/technetwork/java/javase/downloads/jre8-downloads-2133155.html);
2. Download lutconverter.jar from this repository (see release section);
3. Download hald_4_inverted.png;
4. Apply color correction to it;
5. Save as different file (e.g. `example.png`);
6. Run command line: `java -jar lutconverter.jar example.png example.3dl`;
7. Apply converted 3D LUT and enjoy!

## Copyright and license terms

Utility distributed under terms of GNU LGPLv3 license.

**© Freax Software 2017**