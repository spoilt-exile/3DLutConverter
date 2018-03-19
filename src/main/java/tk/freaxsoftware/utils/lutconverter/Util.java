/*
 * This file is part of LutConverter library.
 * 
 * Copyright (C) 2017 Freax Software
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3.0 of the License, or (at your option) any later version.
 * 
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library.
 */
package tk.freaxsoftware.utils.lutconverter;

/**
 * Util class.
 * @author Stanislav Nepochatov
 */
public class Util {
    
    /**
     * Return 3dl header which define LUT cube size and verticles mapped on color values.
     * @param cubeSize size of the cube (for example 16 for 16x16x16);
     * @param bitDepth color depth of the lut (8, 10 or even 16 bit);
     * @return header for 3dl file;
     */
    public static String get3dlHeader(int cubeSize, int bitDepth) {
        double maxValue = Math.pow(2, bitDepth) - 1;
        final double minValue = 0;
        double step = maxValue / (cubeSize - 1);
        String[] headerBuff = new String[cubeSize];
        headerBuff[0] = String.valueOf((int) minValue);
        headerBuff[cubeSize - 1] = String.valueOf((int) maxValue);
        double increment = 0;
        for (int index = 1; index < cubeSize - 1; index++) {
            increment += step;
            headerBuff[index] = String.valueOf((int) increment);
        }
        return String.join(" ", headerBuff);
    }
    
}
