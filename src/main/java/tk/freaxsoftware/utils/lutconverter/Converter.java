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

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import javax.imageio.ImageIO;

/**
 * Converter for luts. Allows to convert HaldCLUT png/tiff lut in 3dl format and backwards.
 * @author Stanislav Nepochatov
 */
public class Converter {
    
    private static final String LUT_HEADER_FORMAT = "# Converted by LutConverter\n"
            + "0 17 34 51 68 85 102 119 136 153 170 187 204 221 238 255\n";
    
    private static final int HALD_SIZE_LIMIT = 64;
    
    /**
     * Converts input HALD lut file in 3dl format and write it into output stream. Method doesn't close streams.
     * @param input stream of the input file;
     * @param output stream to output in the file;
     * @throws java.io.IOException
     * @throws tk.freaxsoftware.utils.lutconverter.ConvertException
     */
    public void convertHaldTo3dl(InputStream input, OutputStream output) throws IOException, ConvertException {
        BufferedImage haldImage = ImageIO.read(input);
        OutputStreamWriter outputStream = new OutputStreamWriter(output);
        if (haldImage.getHeight() != HALD_SIZE_LIMIT || haldImage.getWidth() != HALD_SIZE_LIMIT) {
            throw new ConvertException(ConvertException.Code.HALD_LUT_UNSUPPORTED_FORMAT, "Unable to convert HALD CLUT file, your file must be 4096x4096 pixels.");
        }
        outputStream.write(LUT_HEADER_FORMAT);
        int[] pixelData = new int[3];
        
        for (int hIndex = 0; hIndex < haldImage.getHeight(); hIndex++) {
            for (int wIndex = 0; wIndex < haldImage.getWidth(); wIndex++) {
                pixelData = haldImage.getData().getPixel(wIndex, hIndex, pixelData);
                System.out.println("Processing: " + wIndex + "x" + hIndex);
                outputStream.write(String.format("%d %d %d\n", pixelData[0], pixelData[1], pixelData[2]));
            }
        }
        
        outputStream.flush();
        outputStream.close();
    }
    
    /**
     * Convets file by input path from HALD lut to 3dl foramt and write it to file in destination.
     * @param pathInput input file path;
     * @param pathOutput output file path (will be created if not exist);
     * @throws java.io.FileNotFoundException
     * @throws tk.freaxsoftware.utils.lutconverter.ConvertException
     */
    public void convertHaldTo3dl(String pathInput, String pathOutput) throws FileNotFoundException, IOException, ConvertException {
        InputStream fileInput = new FileInputStream(pathInput);
        File outPutFile = new File(pathOutput);
        outPutFile.createNewFile();
        OutputStream fileOutputStream = new FileOutputStream(outPutFile);
        convertHaldTo3dl(fileInput, fileOutputStream);
        fileInput.close();
        fileOutputStream.flush();
        fileOutputStream.close();
    }
    
    /**
     * Converts input 3dl lut file in HALD format and write it into output stream. Method doesn't close streams.
     * @param input stream of the input file;
     * @param output stream to output in the file;
     */
    public void convert3dlToHald(InputStream input, OutputStream output) {
        
    }
    
    /**
     * Convets file by input path from 3dl lut to HALD foramt and write it to file in destination.
     * @param pathInput input file path;
     * @param pathOutput output file path (will be created if not exist);
     */
    public void convert3dlToHald(String pathInput, String pathOutput) {
        
    }
    
}
